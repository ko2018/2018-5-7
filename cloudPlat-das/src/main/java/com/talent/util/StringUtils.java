package com.talent.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * @author zhangqian
 * @time 2017年12月12日 上午11:54:12
 * @version 1.0v
 */
public class StringUtils {

    private static Logger logger = Logger.getLogger(FileUtil.class.getName());

    public static String getCode(String data) {
        try {
            return md5(data).toUpperCase();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "" + data.hashCode();
    }

    public static String md5(String str) throws Exception {
        byte[] bs = str.getBytes();
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(bs);
        String hex = new BigInteger(1, digest.digest()).toString(16);
        // 补齐BigInteger省略的前置0
        return new String(new char[32 - hex.length()]).replace("\0", "0") + hex;
    }

    public static Map<String, String> jsonToObject(String jsonStr) throws Exception {

        return JacksonUtil.readValue(jsonStr, Map.class);
    }

    public static final String[] getSplit(String str, String delim) {
        if (str == null || delim == null) {
            return null;
        }
        StringTokenizer token = new StringTokenizer(str, delim);
        int num = token.countTokens();
        String[] result = new String[num];
        int i = 0;
        while (token.hasMoreTokens()) {
            result[i++] = token.nextToken();
        }
        return result;
    }
}
