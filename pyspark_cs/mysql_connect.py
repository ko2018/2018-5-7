# coding=UTF-8
from mysql import connector



HOST_NAME='192.168.6.90'
HOST_PORT=3306
MYSQL_USER='curefun'
MYSQL_PASSWD='curefun.com'
MYSQL_DATABASE='cloudPlat'
CHARSET='utf8'


def connect():
    conn = connector.Connect(host= HOST_NAME,
                                     port= HOST_PORT,
                                     user= MYSQL_USER,
                                     passwd= MYSQL_PASSWD,
                                     database= MYSQL_DATABASE,
                                     charset= CHARSET)
    return conn

def get_sample_test_sql_select():
    sql = ('select id as uuId, \
                        s_crowd as crowdID, \
                        s_confidence as confidence, \
                        s_expect_val as expect_val,  \
                        s_dic_id as dic_id,  \
                        add_time \
                        from statics_ttest_sample  \
                        where delete_status = "0" \
                        and ISNULL(update_time) \
                        order by add_time ASC \
                        ')
    return sql

def get_student_ttest_sql_select():
    sql = ('select id as uuId, \
                        s_crowd as crowdID, \
                        s_confidence as confidence, \
                        s_dict_list as dict_list,  \
                        s_group_id1 as group_id1,  \
                        s_group_id2 as group_id2,  \
                        s_expect_flag as expect_flag,  \
                        add_time \
                        from statics_ttest_ind  \
                        where delete_status = "0" \
                        and ISNULL(update_time) \
                        order by add_time ASC \
                        ')
    return sql


def get_describe_analysisc_sql_select():
    sql = ('select id as uuId, \
                        crowd_id as crowdID, \
                        groupId as groupID, \
                        dict_list as dict_list,  \
                        type ,  \
                        option_list ,  \
                        sort ,  \
                        add_time \
                        from statics_describe_analysisc  \
                        where delete_status = "0" \
                        and ISNULL(update_time) \
                        order by add_time ASC \
                        ')
    return sql

def get_describe_analysis_sql_group_select():
    sql = ('select  a.id as groupID,  a.g_unit_name as groupName, a.g_unit_dict as groupDict,\
            d.sequence as groupDictType, a.g_unit_parm as groupParm \
            FROM statics_ttest_ind_group AS a \
            LEFT JOIN base_snl AS b ON a.g_unit_dict = b.dict_id \
            LEFT JOIN base_snl_rule_value c ON c.snl_id = b.snl_id \
            LEFT JOIN base_value_type d ON d.valuetype_id = c.valuetype_id \
            WHERE  \
            a.id = %s \
            GROUP BY a.id')
    return sql

def get_student_ttest_sql_group_select():
    sql = ('select  a.id as groupID,  \
            a.g_unit_name as groupName, a.g_unit_dict as groupDict,\
            d.sequence as groupDictType, a.g_unit_parm as groupParm \
            FROM statics_ttest_ind_group AS a \
            LEFT JOIN base_snl AS b ON a.g_unit_dict = b.dict_id \
            LEFT JOIN base_snl_rule_value c ON c.snl_id = b.snl_id \
            LEFT JOIN base_value_type d ON d.valuetype_id = c.valuetype_id \
            WHERE  \
            a.id = %s OR a.id = %s\
            GROUP BY a.id')
    return sql

def get_sample_test_update():
    sql = ('update   \
             statics_ttest_sample  \
             SET  update_time= %s  \
             WHERE  \
             ID = %s')
    return sql

def get_student_Ttest_update():
    sql = ('update   \
             statics_ttest_ind  \
             SET  update_time= %s  \
             WHERE  \
             ID = %s')
    return sql

def get_Describe_Analysis_update():
    sql = ('update   \
             statics_describe_analysisc  \
             SET  update_time= %s  \
             WHERE  \
             ID = %s')
    return sql


def get_sample_test_result_sql_replace():
    sql = ('replace into  \
             statics_ttest_sample_result( \
             id,  \
             s_id, \
             s_dict_id, \
             s_sum,  \
             s_mean, \
             s_std,  \
             s_std_dev,  \
             s_t, s_free_degree,  \
             s_p,  s_mean_dev,   \
             s_confidence_low,   \
             s_confidence_up,    \
             s_confidence,        \
             s_expect_val,    \
             add_time ) \
             values ( %s, %s, %s, %s, %s,\
                      %s, %s, %s, %s, %s, \
                      %s, %s, %s, %s, %s, %s)')
    return sql

def get_student_Ttest_result_sql_replace():
    sql = ('replace into  \
             statics_ttest_ind_result( \
             id,  \
             s_uuid, s_dict,\
             s_group_1_sum,  s_group_2_sum, \
             s_group1_mean,  s_group2_mean,\
             s_group1_std,  s_group2_std,\
             s_group1_std_error,  s_group2_std_error,\
             s_F, s_Sig,  \
             s_equal_t,  s_unequal_t,   \
             s_equal_free_degree,  s_unequal_free_degree, \
             s_equal_sig,   s_unequal_sig, \
             s_equal_mean,  s_unequal_mean,     \
             s_equal_std_error,  s_unequal_std_error,  \
             s_equal_confidence_low, s_equal_confidence_up, \
             s_unequal_confidence_low, s_unequal_confidence_up ,\
             add_time ) \
             values ( %s,\
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s, %s, \
                      %s)')
    return sql


def get_Describe_Analysis_result_sql_replace():
    sql = ('replace into  \
             statics_describe_analysisc_result( \
             id,  \
             uuid, dict,\
             dict_val,  \
             add_time ) \
             values ( %s,\
                      %s, %s, \
                      %s,  \
                      %s)')
    return sql

def update_math_in_out_sql_result():
    sql = ('UPDATE  \
             statics_math_in_out_data SET  \
             s_data_out = %s, \
             update_time= %s ,\
             exe_status=2 \
             WHERE  id = %s ' 
             )
    return sql

def get_math_in_out_sql_list():
    sql = ('select id as uuId, \
                        s_crowd as crowd, \
                        s_data_in as dataIn, \
                        s_type as sType, \
                        s_type_func as sTypeFunc \
                        from statics_math_in_out_data  \
                        where delete_status = "0" \
                        and ISNULL(exe_status) \
                        order by add_time ASC \
                        ')
    return sql

def print_output(output):
    for (k, v) in output:
        print (k, v)
    return