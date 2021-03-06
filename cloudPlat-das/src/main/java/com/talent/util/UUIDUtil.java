package com.talent.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 主键 生成
 * 
 * @author fwp
 *
 */
public class UUIDUtil {
    private static Logger logger = LoggerFactory.getLogger("UUIDUtil");

    /** md5大写 */
    public static String getCode(String data) {
        try {
            return md5(data).toUpperCase();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "" + data.hashCode();
    }

    /** md5 */
    public static String md5(String str) throws Exception {
        byte[] bs = str.getBytes();
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(bs);
        String hex = new BigInteger(1, digest.digest()).toString(16);
        // 补齐BigInteger省略的前置0
        return new String(new char[32 - hex.length()]).replace("\0", "0") + hex;
    }
}
