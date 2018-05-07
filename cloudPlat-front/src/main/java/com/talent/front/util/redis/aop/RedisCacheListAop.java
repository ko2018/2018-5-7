package com.talent.front.util.redis.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.ognl.OgnlException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.talent.base.constant.SortEnum;
import com.talent.base.exception.BusinessException;
import com.talent.base.exception.ErrorCode;
import com.talent.base.model.BaseEntity;
import com.talent.base.model.PageObject;
import com.talent.front.entity.SysUser;
import com.talent.front.util.redis.annotation.CacheSpeList;
import com.talent.front.util.redis.util.CacheKeyUtil;

@Aspect
@Component
public class RedisCacheListAop {
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheListAop.class);

	@Value("${spring.application.name}")
	String server_name;

	@Autowired
	private RedisTemplate redisTemplate; // 使用RedisTemplate<Serializable, Object>启动报错

	/**
	 * 主要用于对以 时间，价格，序号 排序的列表
	 * 
	 * @param call
	 * @param cache
	 * @return
	 */
	@Around("@annotation(cache)")
	public Object get(ProceedingJoinPoint call, CacheSpeList cache) {
		try {
			PageObject po = getPageObject(call, cache);
			String key = getKeyByList(call, cache);
			ValueOperations<Serializable, Object> valueOper = redisTemplate.opsForValue();
			ZSetOperations<Serializable, Object> zSetOper = redisTemplate.opsForZSet();

			boolean ishave = redisTemplate.hasKey(key);
			logger.info("add redis list key:" + key + "key  isexist:　" + String.valueOf(redisTemplate.hasKey(key)));
			String sortBy = null;
			String sortDir = null;
			if (ishave && po != null) {
				logger.info("key  exist:" + key);
				sortBy = po.getSortBy();
				sortDir = po.getSortDir();
				long size = redisTemplate.boundZSetOps(key).size();
				logger.info("po.getCurPage():" + po.getCurPage() + "   po.getPageSize():   " + po.getPageSize()
						+ "   size：     " + size);
				long length = po.getCurPage() * po.getPageSize();
				if (size >= length) {
					long start = (po.getCurPage() - 1) * po.getPageSize();
					long end = po.getCurPage() * po.getPageSize() - 1;
					logger.info("start:" + start + "   end:   " + end);
					Set<Object> objects = zSetOper.range(key, start, end);
					if (StringUtils.isNotEmpty(sortBy) && sortDir.equalsIgnoreCase(SortEnum.DESC.name())) {
						objects = zSetOper.reverseRange(key, start, end);
					}
					List<BaseEntity> list = new ArrayList<BaseEntity>();
					boolean cache_all = true; // 判断是否缓存里有所有的数据
					for (Object object : objects) {
						String id = (String) object;
						logger.info("exist id:" + id);
						if (redisTemplate.hasKey(id)) {
							BaseEntity be = (BaseEntity) valueOper.get(id);
							list.add(be);
						} else {
							cache_all = false;
							break;
						}
					}
					logger.info("cache_all:" + cache_all);
					if (cache_all)
						return list;
				}
			}

			@SuppressWarnings("unchecked")
			List<BaseEntity> list = (List<BaseEntity>) call.proceed(); // 跳过缓存,到后端查询数据
			for (BaseEntity be : list) {
				long sort = 1L; // 默认以id排序（实际应该是增加时间）
				if (be instanceof SysUser) {
					SysUser sysUser = (SysUser) be;
					if (StringUtils.isNotEmpty(sortBy)) {
						if (sortBy.equalsIgnoreCase("add_time")) {
							sort = sysUser.getAddTime().getTime();
						}
					} else {
						sort = sysUser.getAddTime().getTime();
					}
				}
				zSetOper.add(key, be.get_Id(), sort);
				valueOper.set(be.get_Id(), be, cache.expire(), TimeUnit.SECONDS);
			}
			if (!ishave && redisTemplate.hasKey(key)) {
				redisTemplate.expire(key, cache.expire(), TimeUnit.SECONDS);
			}
			logger.info("redis list key finish...");
			return list;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.UNKNOW_ERROR);
		}
	}

	private String getKeyByList(ProceedingJoinPoint call, CacheSpeList cacheSpeList) throws OgnlException {
		String pre = "";

		Method method = CacheKeyUtil.getMethod(call);
		Type returnType = method.getGenericReturnType();// 返回类型
		logger.info("返回类型:" + returnType.getTypeName());
		if (returnType instanceof ParameterizedType) { /**/
			/* 如果是泛型类型 */
			Type[] types = ((ParameterizedType) returnType).getActualTypeArguments();// 泛型类型列表
			for (Type type : types) {
				pre = type.getTypeName();
				logger.info("返回泛型类型：" + pre);
			}
		}
		if (!pre.equals("")) {
			pre = pre.substring(pre.lastIndexOf(".") + 1);
		}
		String root = server_name;

		String key = pre + "_" + cacheSpeList.value();
		if (root != null && !root.equals("")) {
			key = root + "_" + key;
		}

		if (StringUtils.isEmpty(cacheSpeList.value())) {
			throw new BusinessException(ErrorCode.UNKNOW_ERROR);
		}
		if (cacheSpeList.keyMode() == CacheSpeList.KEYMODE.CACHEKEY) {
			key += CacheKeyUtil.getKeyByCacheKey(call);
		} else if (cacheSpeList.keyMode() == CacheSpeList.KEYMODE.BASIC) {
			key += CacheKeyUtil.getKeyByBasic(call);
		} else if (cacheSpeList.keyMode() == CacheSpeList.KEYMODE.ALL) {
			key += CacheKeyUtil.getKeyByAll(call, cacheSpeList);
		}
		return key;
	}

	public static PageObject getPageObject(ProceedingJoinPoint call, CacheSpeList cache) {
		PageObject po = null;
		Object[] args = call.getArgs();
		for (Object arg : args) {
			if (arg instanceof PageObject) {
				po = (PageObject) arg;
				break;
			}
		}
		return po;
	}
}
