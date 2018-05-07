package com.talent.common;

/** SQL 常量 */
public class SQLCrowd {

    /** 数据库 人群表 */
    public static final String TABLE_CROWD = "statics_custom_crowd";

    /** 数据库 人群指标表 */
    public static final String TABLE_FEATURE = "statics_custom_crowd_features";

    /** 数据库 人群详情表 */
    public static final String TABLE_CROWD_PEOPLE = "statics_custom_crowd_people";

    /** CROWD_CALC_START 开始计算，CROWD_CALC_END 计算结束， CROWD_CALC_ERROR 计算错误 */
    public static final String CROWD_CALC_PREPARE = "0", CROWD_CALC_START = "1", CROWD_CALC_END = "2",
            CROWD_CALC_ERROR = "3";

    /**
     * IN_FEATURE 纳入特征， IN_DISEASE 纳入疾病，IN_ITEM 纳入数据项 ，OUT_FEATURE
     * 结局特征，OUT_DISEASE 结局疾病，OUT_ITEM 结局数据项，SAVE_FEATURE 关注指标，SAVE_DISEASE
     * 关注疾病，SAVE_ITEM关注数据项
     */
    public static final String IN_FEATURE = "1", IN_DISEASE = "2", IN_ITEM = "3", OUT_FEATURE = "11",
            OUT_DISEASE = "12", OUT_ITEM = "13", SAVE_FEATURE = "21", SAVE_DISEASE = "22", SAVE_ITEM = "23";

    /** 数据库 获取人群查询表 */
    public static String select_sql = "SELECT   statics_pId,   statics_p_name,   statics_in_time_start,   "
            + "                statics_in_time_end,  statics_in_formula, "
            + "                static_field_organ,   static_year_span,   static_year_time, type,statics_out_formula   "
            + "                FROM   statics_custom_crowd   "
            + "                WHERE   delete_status = 'N'   AND calc_status='0'   "
            + "                ORDER BY   add_time ASC   ";

    public static String limit_sql = " LIMIT  0, 1 ";

    /** 获取人群查询表 */
    public static String select_sql_ok = select_sql + limit_sql;

    /** 获取人群特征表 */
    public static String select_sql_feature = "SELECT  a.crowd_id,  a.feature_flag,"
            + "     a.feature_name,   a.feature_val,   b.dict_id,   c.sequence"
            + "     FROM    statics_custom_crowd_features a   "
            + "     LEFT JOIN base_snl_rule_value b ON a.feature_name = b.snl_id  "
            + "     LEFT JOIN base_value_type c ON c.valuetype_id = b.valuetype_id   WHERE"
            + "      crowd_id = ?  AND feature_flag NOT LIKE '2_' ORDER BY a.feature_flag ASC";

    /** 更新 人群表 */
    public static String update_sql_crowd = "UPDATE       statics_custom_crowd     SET   calc_status = ?  "
            + ",    update_time = ?     WHERE statics_pId = ?  ";

    /** 过期 废掉 */
    private static String select_std_join_diag_sql = "SELECT  a.datastd_id,  a.user_code,"
            + "    a.institution_id,    a.check_code,   a.data_object,"
            + "    b.multiple_diag_true,   b.multiple_diag_false,    b.multiple_diag_null,"
            + "    b.multiple_diag_blank,  a.crowds,  a.update_time,     b.update_time  FROM    base_data_std a"
            + "    LEFT JOIN base_diag_multiple b ON a.check_code = b.check_code  AND a.user_code = b.user_code"
            + "    AND a.institution_id = b.ins_id  WHERE    a.check_code = b.check_code"
            + "    AND a.user_code = b.user_code  AND a.institution_id = b.ins_id " + "    AND delete_status = 'N' ";

    /** 过期 废掉 */
    private static String count_std_join_diag_sql = "SELECT   COUNT(a.check_code)  FROM  "
            + "    base_data_std a  LEFT JOIN base_diag_multiple b ON a.check_code = b.check_code"
            + "    AND a.user_code = b.user_code  AND a.institution_id = b.ins_id  WHERE"
            + "    a.check_code = b.check_code  AND a.user_code = b.user_code  AND a.institution_id = b.ins_id  ";

    /** 更新 人群详情表 */
    public static String replace_to_people = "REPLACE INTO statics_custom_crowd_people( key_id,"
            + "        crowd_pId,      user_code,      check_code,   crowd_year,"
            + "        institution_id,    exam_sum, exam_index,  crowd_type,  datastd_id, update_time ) VALUES (  ?,?,?,?,?,?,?,?,?,?,?"
            + "        )";

    /** 更新 std表 人群列 */
    public static String update_to_std = "UPDATE base_data_std SET crowds = ? , is_es = 'N' WHERE   datastd_id = ?";

    public static String select_sql_test = "SELECT   statics_pId,   statics_p_name,   statics_in_time_start,   "
            + "       statics_in_time_end,  statics_in_formula, "
            + "       static_field_organ,   static_year_span,   static_year_time, type ,statics_out_formula  "
            + "       FROM   statics_custom_crowd   "
            + "       WHERE   delete_status = 'N'   AND type='0'   AND statics_pId = 'f0720091fddf4e9282538e90aa9091e9'"
            + "       ORDER BY   add_time ASC   ";

    /** 过期 废掉 */
    private static String count_std_join_diag_item_sql = "SELECT    COUNT(DISTINCT b.check_code)"
            + "   FROM  base_diag b "
            + "   LEFT  JOIN  base_data_std a ON a.delete_status = 'N' AND a.check_code = b.check_code"
            + "   AND a.user_code = b.user_code" + "   AND a.institution_id = b.ins_id" + "   WHERE"
            + "    a.check_code = b.check_code";

    /** 过期 废掉 */
    private static String select_std_join_diag_item_sql = "SELECT    a.datastd_id,"
            + "    a.user_code,    a.institution_id,    b.check_code,    a.data_object,"
            + "    GROUP_CONCAT(DISTINCT  b.item_true),    GROUP_CONCAT(DISTINCT  b.item_false),"
            + "    GROUP_CONCAT(DISTINCT  b.item_null),    GROUP_CONCAT(DISTINCT  b.item_blank)"
            + "    FROM    base_data_std a   LEFT JOIN base_diag b ON a.check_code = b.check_code"
            + "    AND a.user_code = b.user_code   " + "AND a.institution_id = b.ins_id    WHERE"
            + "    a.check_code = b.check_code    " + " AND a.user_code = b.user_code      "
            + "    AND a.institution_id = b.ins_id   " + "AND a.delete_status = 'N'    ";

    private static String and_data_between_sql = "    AND (a.update_time >= STR_TO_DATE(?, '%Y-%m-%d')"
            + "    OR b.update_time >= STR_TO_DATE(?, '%Y-%m-%d'))";

    /** 过期 废掉 */
    private static String and_data_between_sql_item = "    AND  b.update_time >= STR_TO_DATE(?, '%Y-%m-%d'))";

    /** 过期 废掉 */
    private static String and_order_by_sql = "    ORDER BY    a.update_time,b.update_time  ASC";

    /** 过期 废掉 */
    private static String and_group_by_sql = "     GROUP BY    a.check_code  ";

    /** 过期 废掉 */
    public static String select_std_join_diag_sql_ok = select_std_join_diag_sql + and_data_between_sql
            + and_order_by_sql;

    /** 过期 废掉 */
    public static String count_std_join_diag_sql_ok = count_std_join_diag_sql + and_data_between_sql;

    /** 过期 废掉 */
    public static String select_std_join_diag_item_sql_ok = select_std_join_diag_item_sql + and_data_between_sql
            + and_group_by_sql;

    /** 过期 废掉 */
    public static String count_std_join_diag_item_sql_ok = count_std_join_diag_item_sql + and_data_between_sql;

    /** 更新人群 统计临时结果 */
    public static String insert_crowd_change_sum = "REPLACE INTO statics_custom_crowd_change_sum (   id,"
            + "    crowd_id,   crowd_people,    crowd_records,  step, step_name, add_time  )"
            + "    VALUES (?,?,?,?,?,?,?)";

    /** 更新人群 统计总数 */
    public static String update_sql_crowd_sum = "UPDATE       statics_custom_crowd     SET   statics_sum = ?  "
            + ",    update_time = ?     WHERE statics_pId = ?  ";

}
