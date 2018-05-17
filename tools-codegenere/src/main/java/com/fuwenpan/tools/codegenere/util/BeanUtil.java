package com.fuwenpan.tools.codegenere.util;

public class BeanUtil {
	public static Object createInstance(String className) {
		try {
			Class cls = Class.forName(className);
			return cls.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
}
