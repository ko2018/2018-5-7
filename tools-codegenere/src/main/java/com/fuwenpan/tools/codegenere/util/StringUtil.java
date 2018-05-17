package com.fuwenpan.tools.codegenere.util;

public class StringUtil {

	public static String upperFirstChar(String str) {
		  if (str != null && str.length() > 0) {
	            return str.substring(0, 1).toUpperCase() + str.substring(1);
	        } else {
	            return str;
	        }
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String lowerFirstChar(String str) {
		 if (str != null && str.length() > 0) {
	            return str.substring(0, 1).toLowerCase() + str.substring(1);
	        } else {
	            return str;
	        }
	}

}
