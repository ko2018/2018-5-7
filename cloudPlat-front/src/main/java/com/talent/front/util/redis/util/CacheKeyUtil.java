package com.talent.front.util.redis.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.ognl.ASTProperty;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.talent.base.model.BaseEntity;
import com.talent.base.model.PageObject;
import com.talent.front.util.redis.annotation.CacheKey;
import com.talent.front.util.redis.annotation.CacheSpeList;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 缓存键 工具类 获取缓存键 的值
 * 
 * @author fwp
 *
 */
public class CacheKeyUtil {
	private static final Logger logger = LoggerFactory.getLogger(CacheKeyUtil.class);

	/**
	 * 得到缓存键值 Cache注解 keyMode=KEYMODE.CACHEKEY
	 * 
	 * @param call
	 * @return
	 * @throws OgnlException
	 */
	public static String getKeyByCacheKey(ProceedingJoinPoint call) throws OgnlException {
		Object[] args = call.getArgs();
		StringBuffer buf = new StringBuffer();
		Annotation[][] pas = ((MethodSignature) call.getSignature()).getMethod().getParameterAnnotations();
		for (int i = 0; i < pas.length; i++) {
			for (Annotation an : pas[i]) {
				if (an instanceof CacheKey) {
					CacheKey cacheKey = (CacheKey) an;
					String ognls = cacheKey.ognl();
					if (StringUtils.isEmpty(ognls)) {
						buf.append(".").append(args[i].toString());
					} else {
						String[] ognlArry = ognls.split(",");
						for (int j = 0; j < ognlArry.length; j++) {
							ASTProperty expression = (ASTProperty) Ognl.parseExpression(ognlArry[i]);
							buf.append(".").append(Ognl.getValue(expression, args[i]));
						}
					}
				}
			}
		}
		return buf.toString();
	}

	/**
	 * 得到缓存键值 Cache注解 keyMode=KEYMODE.BASIC
	 * 
	 * @param call
	 * @return
	 * @throws OgnlException
	 */
	public static String getKeyByBasic(ProceedingJoinPoint call) {
		Object[] args = call.getArgs();
		StringBuffer buf = new StringBuffer();
		for (Object arg : args) {
			if (arg instanceof String) {
				buf.append(".").append(arg);
			} else if (arg instanceof Integer || arg instanceof Long || arg instanceof Short) {
				buf.append(".").append(arg.toString());
			} else if (arg instanceof Boolean) {
				buf.append(".").append(arg.toString());
			}
		}
		return buf.toString();
	}

	/**
	 * CacheSpeObject得到缓存键值 Cache注解 keyMode=KEYMODE.All
	 * 
	 * @param call
	 * @return
	 * @throws OgnlException
	 */
	public static String getKeyByAll(ProceedingJoinPoint call, CacheSpeObject cache) {
		Object[] args = call.getArgs();
		StringBuilder buf = new StringBuilder();
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext();
		for (Object arg : args) {
			// 如果参数类型为PageObject 分页查询，按查询页 分页数 和排序方式 查询条件做为键值
			if (arg instanceof String) {
				String str = (String) arg;
				logger.info("ParameterName:" + getParameterName(call) + "   ::::::::   " + str.toString());
				context.setVariable(getParameterName(call), str.toString());
				String result = parser.parseExpression(cache.key()).getValue(context, String.class);
				if (cache.primary_key().equals(CacheSpeObject.PRIMARYKEY.NO)) {
					buf.append("_").append(result);
				} else {
					buf.append(result);
				}
			} else if (arg instanceof BaseEntity) {
				context.setVariable(getParameterName(call), (BaseEntity) arg);
				String result = parser.parseExpression(cache.key()).getValue(context, String.class);
				if (cache.primary_key().equals(CacheSpeObject.PRIMARYKEY.NO)) {
					buf.append("_").append(result);
				} else {
					buf.append(result);
				}
			}
		}
		logger.info("get key end:" + buf.toString());
		return buf.toString();
	}

	/**
	 * CacheSpeList得到缓存键值 Cache注解 keyMode=KEYMODE.All
	 * 
	 * @param call
	 * @return
	 * @throws OgnlException
	 */
	public static String getKeyByAll(ProceedingJoinPoint call, CacheSpeList cache) {
		Object[] args = call.getArgs();
		StringBuilder buf = new StringBuilder();
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext();
		for (Object arg : args) {
			// 如果参数类型为PageObject 分页查询，按查询条件做为键值
			if (arg instanceof PageObject) {
				PageObject pageObject = (PageObject) arg;
				logger.info("ParameterName:" + getParameterName(call) + "   ::::::::   " + pageObject.toString());
				context.setVariable(getParameterName(call), pageObject);
				String result = parser.parseExpression(cache.key()).getValue(context, String.class);
				buf.append("_").append(result);

				Map<String, Object> queryCondition = pageObject.getQueryCondition();
				if (queryCondition != null && queryCondition.size() > 0) {
					Map<String, Object> qcTree = new TreeMap<String, Object>(queryCondition);
					buf.append("_");
					for (String key : qcTree.keySet()) {
						buf.append(key).append(":").append(qcTree.get(key).toString());
					}
				}
			} else {
				buf.append(".").append(arg.toString());
			}
		}
		logger.info("get key end:" + buf.toString());
		return buf.toString();
	}

	public static Method getMethod(ProceedingJoinPoint pjp) {
		org.aspectj.lang.Signature sig = pjp.getSignature();
		MethodSignature msig = null;
		if (!(sig instanceof MethodSignature)) {
			throw new IllegalArgumentException("该注解只能用于方法");
		}
		msig = (MethodSignature) sig;
		Object target = pjp.getTarget();
		try {
			Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());

			// Class<?>[] parameterTypes = currentMethod.getParameterTypes();
			// for (Class<?> clas : parameterTypes) {
			// String parameterName = clas.getName();
			// System.out.println("参数名称:" + parameterName);
			// }
			return currentMethod;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getParameter(ProceedingJoinPoint pjp) {
		String str = "";
		Method method = getMethod(pjp);
		if (method != null) {
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (Class<?> clas : parameterTypes) {
				String parameterName = clas.getName();
				System.out.println("参数名称:" + parameterName);
				str = parameterName;
			}
		}
		return str;
	}

	private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

	public static String getParameterName(ProceedingJoinPoint pjp) {
		String str = "";
		Method method = getMethod(pjp);
		if (method != null) {
			String[] names = parameterNameDiscoverer.getParameterNames(method);
			if (names.length > 0)
				str = names[0];
		}
		return str;
	}
}
