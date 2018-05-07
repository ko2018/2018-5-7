package com.talent.front.util.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此类的实现在主程序里（排序功能有很多具有侵入性）
 * 
 * @author fwp
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheSpeList {
	enum KEYMODE {
		CACHEKEY, BASIC, ALL;
	}

	enum OPER {
		SELECT, UPDATE, DELETE,
	}

	String value() default "";

	String key() default "";

	KEYMODE keyMode() default KEYMODE.ALL;

	OPER OPER() default OPER.SELECT;

	int expire() default 10 * 60; // 缓存多少秒,10分钟
}
