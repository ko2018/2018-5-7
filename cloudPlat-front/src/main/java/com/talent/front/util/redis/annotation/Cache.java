package com.talent.front.util.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
	enum KEYMODE {
		CACHEKEY, BASIC, ALL;
	}

	enum OPER {
		SELECT, UPDATE, DELETE,
	}

	String value() default "";

	KEYMODE keyMode() default KEYMODE.ALL;

	OPER OPER() default OPER.SELECT;
}
