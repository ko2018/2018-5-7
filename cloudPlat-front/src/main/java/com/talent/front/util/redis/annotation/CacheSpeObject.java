package com.talent.front.util.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheSpeObject {
	enum KEYMODE {
		CACHEKEY, BASIC, ALL;
	}

	enum OPER {
		SELECT, UPDATE, DELETE,
	}

	enum PRIMARYKEY {
		YES, NO,
	}

	String value() default "";

	String key() default "";

	KEYMODE keyMode() default KEYMODE.ALL;

	OPER OPER() default OPER.SELECT;

	PRIMARYKEY primary_key() default PRIMARYKEY.YES; // key是否为主键

	int expire() default 60 * 30; // 缓存过期时间30分钟
}
