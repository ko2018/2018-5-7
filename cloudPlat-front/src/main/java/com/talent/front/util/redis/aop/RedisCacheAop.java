package com.talent.front.util.redis.aop;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.ognl.OgnlException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.talent.base.exception.BusinessException;
import com.talent.base.exception.ErrorCode;
import com.talent.base.model.BaseEntity;
import com.talent.front.util.redis.annotation.CacheSpeObject;
import com.talent.front.util.redis.annotation.CacheVersion;
import com.talent.front.util.redis.util.CacheKeyUtil;

@Aspect
@Component
public class RedisCacheAop {
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheAop.class);

	@Autowired
	private RedisTemplate redisTemplate; // 使用RedisTemplate<Serializable, Object>启动报错

	/**
	 * 在方法上有注解为 CacheSpeObject 的方法上执行 ，将对象放入缓存 或更新缓存 ；只要用于非id查询对象
	 * 
	 * @param call
	 * @param cache
	 * @return
	 */
	@Around("@annotation(cache)")
	public Object get(ProceedingJoinPoint call, CacheSpeObject cache) {
		try {
			String key = getKey(call, cache);
			logger.info("add redis key:" + key);
			if (cache.OPER().equals(CacheSpeObject.OPER.UPDATE)) {
				return updateObject(key, call, cache);
			} else if (cache.OPER().equals(CacheSpeObject.OPER.DELETE)) {
				return deleteObject(key, call, cache);
			} else {
				return selectObject(key, call, cache);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.UNKNOW_ERROR);
		}
	}

	private Object selectObject(String key, ProceedingJoinPoint call, CacheSpeObject cache) {
		try {
			ValueOperations<Serializable, Object> valueOper = redisTemplate.opsForValue();
			if (cache.primary_key().equals(CacheSpeObject.PRIMARYKEY.NO)) {
				Object id = valueOper.get(key); // 从缓存获取数据
				logger.info("get redis value id:" + (String) id);
				if (id != null) {
					Object object = valueOper.get((String) id);
					logger.info("get redis value object:" + object);
					if (object != null) {
						redisTemplate.expire(key, cache.expire(), TimeUnit.SECONDS);
						redisTemplate.expire(String.valueOf(id), cache.expire(), TimeUnit.SECONDS);
						return object; // 如果有数据,则直接返回
					}
				}
			} else {
				Object object = valueOper.get(key);
				logger.info("get redis value object:" + object);
				if (object != null) {
					redisTemplate.expire(key, cache.expire(), TimeUnit.SECONDS);
					return object; // 如果有数据,则直接返回
				}
			}

			BaseEntity be = (BaseEntity) call.proceed(); // 跳过缓存,到后端查询数据
			if (be != null) {
				if (cache.expire() <= 0) { // 如果没有设置过期时间,则无限期缓存
					if (cache.primary_key().equals(CacheSpeObject.PRIMARYKEY.NO)) {
						valueOper.set(key, be.get_Id());
						valueOper.set(be.get_Id(), be);
					} else {
						valueOper.set(key, be);
					}
				} else { // 否则设置缓存时间
					if (cache.primary_key().equals(CacheSpeObject.PRIMARYKEY.NO)) {
						System.out.println("AAAAAAAAAa: " + be.get_Id());
						valueOper.set(key, be.get_Id(), cache.expire(), TimeUnit.SECONDS);
						valueOper.set(be.get_Id(), be, cache.expire(), TimeUnit.SECONDS);
					} else {
						valueOper.set(key, be, cache.expire(), TimeUnit.SECONDS);
					}
				}
			}

			return be;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.UNKNOW_ERROR);
		}
	}

	private Object updateObject(String key, ProceedingJoinPoint call, CacheSpeObject cache) {
		try {
			ValueOperations<Serializable, Object> valueOper = redisTemplate.opsForValue();
			BaseEntity be = (BaseEntity) call.proceed(); // 跳过缓存,到后端查询数据
			if (be != null) {
				if (cache.expire() <= 0) { // 如果没有设置过期时间,则无限期缓存
					if (cache.primary_key().equals(CacheSpeObject.PRIMARYKEY.NO)) {
						valueOper.set(key, be.get_Id());
						valueOper.set(be.get_Id(), be);
					} else {
						valueOper.set(key, be);
					}
				} else { // 否则设置缓存时间
					if (cache.primary_key().equals(CacheSpeObject.PRIMARYKEY.NO)) {
						valueOper.set(key, be.get_Id(), cache.expire(), TimeUnit.SECONDS);
						valueOper.set(be.get_Id(), be, cache.expire(), TimeUnit.SECONDS);
					} else {
						valueOper.set(key, be, cache.expire(), TimeUnit.SECONDS);
					}
				}
			}

			return be;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.UNKNOW_ERROR);
		}
	}

	private Object deleteObject(String key, ProceedingJoinPoint call, CacheSpeObject cache) {
		try {
			ValueOperations<Serializable, Object> valueOper = redisTemplate.opsForValue();
			if (cache.primary_key().equals(CacheSpeObject.PRIMARYKEY.NO)) {
				Object id = valueOper.get(key); // 从缓存获取数据
				logger.info("get redis value id:" + (String) id);
				if (id != null) {
					Object object = valueOper.get((String) id);
					logger.info("get redis value object:" + object);
					if (object != null) {
						redisTemplate.delete(String.valueOf(id));
						redisTemplate.delete(key);
					} else {
						redisTemplate.delete(key);
					}
				}
			} else {
				redisTemplate.delete(key);
			}

			Object be = call.proceed(); // 跳过缓存,到后端查询数据

			return be;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.UNKNOW_ERROR);
		}
	}

	private String getKey(ProceedingJoinPoint call, CacheVersion cacheVersion) {
		if (StringUtils.isEmpty(cacheVersion.value())) {
			throw new BusinessException(ErrorCode.UNKNOW_ERROR);
		}
		String key = cacheVersion.value() + "_version";
		return key;
	}

	/**
	 * 获取缓存数据的键
	 * 
	 * @param call
	 * @param cache
	 * @return
	 * @throws OgnlException
	 */
	private String getKey(ProceedingJoinPoint call, CacheSpeObject cacheSpeObject) throws OgnlException {
		String key = cacheSpeObject.value();
		if (cacheSpeObject.primary_key().equals(CacheSpeObject.PRIMARYKEY.NO)) {
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			String root = webApplicationContext.getServletContext().getInitParameter("webAppRootKey");
			if (root != null && !root.equals("")) {
				key = root + "_" + key;
			}
		}
		if (StringUtils.isEmpty(cacheSpeObject.value())) {
			throw new BusinessException(ErrorCode.UNKNOW_ERROR);
		}
		if (cacheSpeObject.keyMode() == CacheSpeObject.KEYMODE.CACHEKEY) {
			key += CacheKeyUtil.getKeyByCacheKey(call);
		} else if (cacheSpeObject.keyMode() == CacheSpeObject.KEYMODE.BASIC) {
			key += CacheKeyUtil.getKeyByBasic(call);
		} else if (cacheSpeObject.keyMode() == CacheSpeObject.KEYMODE.ALL) {
			if (cacheSpeObject.primary_key().equals(CacheSpeObject.PRIMARYKEY.NO)) {
				key += CacheKeyUtil.getKeyByAll(call, cacheSpeObject);
			} else {
				key = CacheKeyUtil.getKeyByAll(call, cacheSpeObject);
			}
		}
		return key;
	}

}
