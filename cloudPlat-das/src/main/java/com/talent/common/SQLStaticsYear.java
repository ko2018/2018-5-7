package com.talent.common;

/** SQL 统计年月常量 */
public class SQLStaticsYear {
    public static final String TABLE_YEAR = "statics_year_records";

    /** 过期 废掉 */
    public static String replace_year_table = "REPLACE INTO statics_year_records( id,"
            + "            diseaseId,       sum_health,    sum_ill,"
            + "            sum_year,        update_time       ) values (?,?,?,?,?,? )";
}
