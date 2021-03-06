package com.talent.core.global;

/** 全局常量 */
public class Constant {

    public static final String KEY_SEPARATOR_C = ":", KEY_SEPARATOR_A = "/", KEY_SEPARATOR_B = "-",
            KEY_SEPARATOR_D = "=", KEY_SEPARATOR_S = ",";

    /** SQL 状态 */
    public static final String SQL_REPLACE_INTO = "REPLACE", SQL_INSERT_INTO = "INSERT", SQL_DELETE_FORM = "DELETE",
            SQL_SELECT_FORM = "SELECT", SQL_UPDATE = "UPDATE";

    public static String CONFIG_RAW_PATH = "conf/config_raw.xml";

    public static final String CUSTOM_TIME = "hbase.custom.time";

    /** 年龄层 性别 男 */
    public static final String SEX_M = "1";

    /** 年龄层 性别 女 */
    public static final String SEX_W = "2";

    /** 年龄层 */
    public static final String AGE_ALL = "0", AGE_7_17 = "1", AGE_18_40 = "2", AGE_41_65 = "3", AGE_66 = "4";

    // 第一步过滤, 属于人群过滤
    public static final String FIRST_FILTER = "1", IN_CROWD_FILTER = "2", NOT_IN_CROWD_FILTER = "3",
            OVER_WIND_TIME = "4";

    // 表达式匹配
    public static final String REGEX_MATCH = ".*[0-9].*";

    public static final String REGEX_MATCH_KAFKA = "_[0-9].*";

    // 过滤掉 中文表达式匹配
    public static final String REGEX_MATCH_ZW = "[A-Za-z0-9].*";

    // 非空判断逻辑
    public static final String REGEX_PRE = "!\\s*";
}
