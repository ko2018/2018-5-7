package com.talent.front.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * BCrypt加密
 * 
 * @author Administrator
 *
 */
public class BCryptUtil {

	public static String encode(String password) {
		String hash = BCrypt.hashpw(password, BCrypt.gensalt());
		return hash;
	}

	public static boolean checkpw(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}

	public static void main(String[] args) {
		String p = BCryptUtil.encode("admin");
		System.out.println(p);
		System.out.println(BCrypt.checkpw("admin", p));
	}

}
