package com.talent.common;

/** SQL 分组常量 */
public class SQLGroup {
    /** 数据库 分组表 */
    public static final String TABLE_GROUP = "statics_custom_crowd_group";

    /** 数据库 分组特征表 */
    public static final String TABLE_GROUP_FEATURE = "statics_custom_crowd_group_features";

    /** 数据库 分组详情表 */
    public static final String TABLE_GROUP_PEOPLE = "statics_custom_crowd_group_people";

    /** 数据库 查询分组表 */
    public static String select_sql_group = "SELECT    a.id,    a.crowd_id,    a.g_name,  a.g_formula  ,a.filter_type   "
            + "     FROM" + "     statics_custom_crowd_group a  LEFT JOIN statics_custom_crowd b ON"
            + "     a.crowd_id = b.statics_pId AND   b.calc_status = '2'   WHERE    a.delete_status = 'N'"
            + "     AND a.g_type = '0'   ORDER BY    a.add_time LIMIT 0, 1";

    /** 数据库 查询分组表 */
    public static final String select_sql_group_features = "SELECT    a.group_id,"
            + "    a.feature_dict,   a.feature_name,    a.feature_val,  a.feature_flag,    c.sequence    FROM"
            + "    statics_custom_crowd_group_features a"
            + "    LEFT JOIN base_snl_rule_value b ON a.feature_name = b.snl_id"
            + "    LEFT JOIN base_value_type c ON c.valuetype_id = b.valuetype_id   WHERE"
            + "    a.group_id IN (?)   AND a.feature_flag LIKE '2_'  ORDER BY   a.feature_flag";

    /** 数据库 分组表统计 */
    public static final String update_sql_group_sum = " UPDATE statics_custom_crowd_group SET g_sum = ?"
            + "    , update_time = ?  WHERE   id = ?";

    /** 数据库 分组表统计 */
    public static final String update_sql_group_status = "UPDATE       statics_custom_crowd_group     SET   g_type = ?  "
            + ",    update_time = ?     WHERE id = ?  ";

    /** 数据库 更新分组详情表 */
    public static final String replace_into_group = "INSERT INTO statics_custom_crowd_group_people ("
            + "    key_id,    group_pId,    datastd_id,    user_code,"
            + "    exam_sum,   check_code,    institution_id,  "
            + "    crowd_type,    crowd_year,   add_time,   exam_index  )"
            + "    VALUES   (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /** 数据库 分组表测试 */
    public static final String select_sql_group_test = "SELECT    a.id,    a.crowd_id,    a.g_name,"
            + "    a.g_formula, a.filter_type  FROM    statics_custom_crowd_group a   WHERE  "
            + "    a.id='9cc8b1ae8ca64ad6b65a7d03d7f2d811'   LIMIT 0, 1";

    /** 数据库 更新 std表的分组列 */
    public static String update_to_std = "UPDATE base_data_std SET groups = ? , is_es = 'N' WHERE   datastd_id = ?";
}
