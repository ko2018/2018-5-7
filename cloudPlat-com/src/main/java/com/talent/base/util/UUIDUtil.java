package com.talent.base.util;

import java.util.UUID;

/**
 * 主键 生成
 * 
 * @author fwp
 *
 */
public class UUIDUtil {
	public static String getUUID(int maxlength) {
		UUID uuid = UUID.randomUUID();
		String sid = uuid.toString().replaceAll("-", "");
		if (sid.length() > maxlength) {
			sid = sid.substring(0, maxlength - 1);
		}
		return sid;
	}

	public static String getUUID() {
		return getUUID(32);
	}
	
	public static void main(String[] args)
    {
	    for (int i = 0; i < 5; i++)
	    {
	        System.out.println(getUUID(32));
	    }
    }
}
