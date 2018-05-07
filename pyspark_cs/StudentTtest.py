# coding:utf-8
import numpy
from decimal import Decimal
import time
import math
import threading
import Tsparkcore
import mysql_connect
import statistics
                
def print_StudentTtest_DB(row):
    uuId = row[0]
    crowID = row[1]
    confidence = row[2]
    dict_list = row[3]
    group_id1 = row[4]
    group_id2 = row[5]
    expect_flag = row[6]
    add_time = row[7]
    return  {'uuId':uuId, 'crowID':crowID, 'confidence':confidence, 'dict_list':dict_list, 
             's_group_id1':group_id1, 's_group_id2':group_id2,
             'expect_flag':expect_flag, 'add_time':add_time,}


    
    
def getStudentTTestDBAskCalcList():
    list_ask_queue = []
    conn = None
    cursor = None
    try:
        conn = mysql_connect.connect()
        sql = mysql_connect.get_student_ttest_sql_select()
        conn.autocommit = True
        cursor = conn.cursor()
        cursor.execute(sql)
        if cursor : 
            for row in cursor:
                list_ask_queue.append(print_StudentTtest_DB(row))
                print row
            
    except:
        import traceback
        traceback.print_exc()
        if conn :
            conn.rollback()
    
    finally:
        if cursor : 
            cursor.close()
        if conn:
            conn.close()
    return list_ask_queue

    
def getStudentTTestGroupDBList(group1, group2):
    list_group_queue = []
    try:
        conn = mysql_connect.connect()
        sql = mysql_connect.get_student_ttest_sql_group_select()
        conn.autocommit = True
        cursor = conn.cursor()
        cursor.execute(sql, (group1, group2,))
        
        if cursor :
            for row in cursor:
                group_test_row = Tsparkcore.print_Group_DB(row)
                list_group_queue.append(group_test_row)
                print row
            
    except:
        import traceback
        traceback.print_exc()
        conn.rollback()
    
    finally:
        if cursor :
            cursor.close()
        conn.close()
    return list_group_queue



def calc_python_spark(ask_parm, filterList, list_group_queue):
    confidence = ask_parm['confidence']
    crowd_id = ask_parm['crowID']
    expect_flag = ask_parm['expect_flag']
    
    sc = Tsparkcore.createSparkContext("crowd")
    hbase_rdd = Tsparkcore.createSparkRDD(sc, "cs_talent:statics_crowd_details")
    hbase_rdd = Tsparkcore.calHbaseRDD(hbase_rdd, "EXAM_LIST", crowdID=crowd_id)
    output_hbase_rdd = hbase_rdd.flatMap(lambda kv: Tsparkcore.map_list_crowd(kv[1]))
   
    data_rdd = Tsparkcore.createSparkRDD(sc, "cs_talent:base_standard")
    data_rdd = Tsparkcore.calHbaseRDD(data_rdd, "EXAM_JSON")
    output_data_rdd = data_rdd.flatMap(lambda kv: Tsparkcore.map_list_std(kv[1]))
    output_hbase_rdd_join = output_hbase_rdd.leftOuterJoin(output_data_rdd)
    group1 = list_group_queue[0];
    group2 = list_group_queue[1];
   

    if expect_flag == '1' :
        output_hbase_rdd_join_1 = output_hbase_rdd_join.filter(lambda kv: Tsparkcore.filter_cols_join_False(kv[1], filterList, group1))
        output_hbase_rdd_join_2 = output_hbase_rdd_join.filter(lambda kv: Tsparkcore.filter_cols_join_False(kv[1], filterList, group2))
    else :
        output_hbase_rdd_join_1 = output_hbase_rdd_join.filter(lambda kv: Tsparkcore.filter_cols_join_True(kv[1], filterList, group1))
        output_hbase_rdd_join_2 = output_hbase_rdd_join.filter(lambda kv: Tsparkcore.filter_cols_join_True(kv[1], filterList, group2))
    print "group2++++=",  group2
#     for key in output_hbase_rdd_join_2.collect():
#         print "--->", key
    print "group1++++1=",  group1    
    output_hbase_rdd_join_map_1 = output_hbase_rdd_join_1.flatMap(lambda kv: Tsparkcore.map_list_crowd_std_join(kv[0], kv[1], filterList))
    output_hbase_rdd_join_map_2 = output_hbase_rdd_join_2.flatMap(lambda kv: Tsparkcore.map_list_crowd_std_join(kv[0], kv[1], filterList))
    result_dict = {}
    
    
    for fKey in filterList:
        group1 = expect_flag_collect(fKey, output_hbase_rdd_join_map_1)
        
        group2 = expect_flag_collect(fKey, output_hbase_rdd_join_map_2)
    
        result_dict[fKey] = calc_student_ttest_result(numpy.array(group1), numpy.array(group2), confidence)
    
    sc.stop()   
    return result_dict


def calc_student_ttest_result(a, b, confidence):
    result = {}   
#     a = [1,3,5,17,9]
#     b = [12,4,6,8,10,41]

    result['group_1_N'] = len(a)
    result['group_2_N'] = len(b)
    if len(a) < 2 or len(b) < 2:
        result['group_1_mean'] = "-1";
        result['group_1_std'] = "-1";
        result['group_1_std_error']='-1';
        result['group_2_mean'] = "-1";
        result['group_2_std'] = "-1";
        result['group_2_std_error']='-1';
        result['group_unequal_low']="-1"
        result['group_unequal_up']="-1"
        result['group_equal_low']="-1"
        result['group_equal_up']="-1"
        result['group_equal_t']="-1"
        result['group_equal_p']="-1"
        result['group_equal_free_degree']="-1"
        result['group_unequal_t']="-1"
        result['group_unequal_p']="-1"
        result['group_unequal_free_degree']="-1"
        result['group_equal_mean_error']="-1"
        result['group_unequal_mean_error']="-1"
        result['F']="-1"
        result['sig']="-1"
        result['group_unequal_std_error']="-1"
        result['group_equal_std_error']="-1"
        return result
    
  
    mean1, _, stddev1, _, _, _ = statistics.stats(a, confidence)
    result['group_1_mean'] = utils.get_Decimal_float(mean1)
    result['group_1_std'] = utils.get_Decimal_float(stddev1)
    result['group_1_std_error'] = utils.get_Decimal_float(stddev1/math.sqrt(len(a)))
    
    mean2, _, stddev2, _, _, _ = statistics.stats(b, confidence)
    result['group_2_mean'] = utils.get_Decimal_float(mean2)
    result['group_2_std'] = utils.get_Decimal_float(stddev2)
    result['group_2_std_error'] = utils.get_Decimal_float(stddev2/math.sqrt(len(b)))
    
    import statsmodels.stats.api as sms
    cm = sms.CompareMeans(sms.DescrStatsW(a), sms.DescrStatsW(b))
    tconfint_diff = cm.tconfint_diff(alpha=1.0 - confidence, usevar='unequal')
    result['group_unequal_low'] = utils.get_Decimal_float(tconfint_diff[0])
    result['group_unequal_up'] = utils.get_Decimal_float(tconfint_diff[1])
    tconfint_diff = cm.tconfint_diff(alpha=1.0 - confidence, usevar='pooled')
    result['group_equal_low'] = utils.get_Decimal_float(tconfint_diff[0])
    result['group_equal_up'] = utils.get_Decimal_float(tconfint_diff[1])
    import statsmodels.api as sm
    ttest_int_result = sm.stats.ttest_ind(a, b, usevar='pooled')
    result['group_equal_t'] = utils.get_Decimal_float(ttest_int_result[0])
    result['group_equal_p'] = utils.get_Decimal_float(ttest_int_result[1])
    result['group_equal_free_degree'] = Decimal(ttest_int_result[2])
    ttest_int_result = sm.stats.ttest_ind(a, b, usevar='unequal')
    result['group_unequal_t'] = utils.get_Decimal_float(ttest_int_result[0])
    result['group_unequal_p'] = utils.get_Decimal_float(ttest_int_result[1])
    result['group_unequal_free_degree'] = utils.get_Decimal_float(ttest_int_result[2])
    result['group_equal_mean_error'] = result['group_1_mean'] - result['group_2_mean']
    result['group_unequal_mean_error'] = result['group_1_mean'] - result['group_2_mean']
    from scipy.stats import levene  
    ttest_levene = levene(a, b, center = 'trimmed')
    result['F'] = utils.get_Decimal_float(ttest_levene[0])
    result['sig'] = utils.get_Decimal_float(ttest_levene[1])
    
    result['group_unequal_std_error'] = utils.get_Decimal_float(math.sqrt(stddev1*stddev1/len(a) + stddev2*stddev2/len(b)))
    #error
    result['group_equal_std_error'] = utils.get_Decimal_float(math.sqrt(stddev1*stddev1/len(a) + stddev2*stddev2/len(b)))
    return result

import utils
def expect_flag_collect(fKey, output_hbase_rdd_join_map):
    slist = []
    for key in output_hbase_rdd_join_map.collect():
        print key[1] , key[1][fKey]
        if(key[1][fKey] and utils.is_number(key[1][fKey])) :
            slist.append(float(key[1][fKey]))
    return slist

def service():
    # 获取db ，任务队列
    list_ask_queue = getStudentTTestDBAskCalcList()
    print "StudentTtest, ", len(list_ask_queue)
    if list_ask_queue.__len__() <= 0:
        return
       
    for ask_parm in list_ask_queue:
        print ask_parm
      
        ask_parm['confidence'] = float(ask_parm['confidence']);
        
        dict_id_str = ask_parm["dict_list"];
        dict_id_Arrs = dict_id_str.split(',')
        filterList = []
        for dict_id in dict_id_Arrs:
            filterList.append("_"+dict_id);
        #   获取分组
        list_group_queue = getStudentTTestGroupDBList(ask_parm['s_group_id1'], ask_parm['s_group_id2'])
        if list_group_queue.__len__() < 2 :
            print "warning:", list_group_queue.__len__() 
            continue

        #     spark 计算分组
        result_dict = calc_python_spark(ask_parm, filterList, list_group_queue);
        print result_dict   
        uuId = ask_parm["uuId"]
        crowID = ask_parm["crowID"]
        for dict_id in dict_id_Arrs:
            #    计算结果保存与更新
            saveStudentTtestResultToDB(utils.create_id(uuId+crowID+dict_id), uuId, dict_id, result_dict["_"+dict_id])
            updateStudentTtestStatus(uuId)
        
    return

def serverHandler():

    while True :
  
        list_ask_queue = getStudentTTestDBAskCalcList()
        if list_ask_queue.__len__() <= 0:
            continue
       
        for ask_parm in list_ask_queue:
            print ask_parm
          
            ask_parm['confidence'] = float(ask_parm['confidence']);
            
            dict_id_str = ask_parm["dict_list"];
            dict_id_Arrs = dict_id_str.split(',')
            filterList = []
            for dict_id in dict_id_Arrs:
                filterList.append("_"+dict_id);
            list_group_queue = getStudentTTestGroupDBList(ask_parm['s_group_id1'], ask_parm['s_group_id2'])
            if list_group_queue.__len__() < 2 :
                print "warning:", list_group_queue.__len__() 
                continue
            
            
            result_dict = calc_python_spark(ask_parm, filterList, list_group_queue);
            print result_dict   
            uuId = ask_parm["uuId"]
            crowID = ask_parm["crowID"]
            for dict_id in dict_id_Arrs:
                saveStudentTtestResultToDB(utils.create_id(uuId+crowID+dict_id), uuId, dict_id, result_dict["_"+dict_id])
                updateStudentTtestStatus(uuId)
         
        time.sleep(10000)
    return

def updateStudentTtestStatus(uuID):
    try:
        conn = mysql_connect.connect()
      
        sql = mysql_connect.get_student_Ttest_update()
        conn.autocommit = True
        cursor = conn.cursor()
        
        cursor.execute(sql, ( time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time())),
                                           uuID)
                             )
        conn.commit()
      
    except:
        import traceback
        traceback.print_exc()
        conn.rollback()
    
    finally:
        cursor.close()
        conn.close()
    return

def saveStudentTtestResultToDB(idKey, uuId, dict_id, result_dict):       
    try:
        conn = mysql_connect.connect()
      
        sql = mysql_connect.get_student_Ttest_result_sql_replace()
        conn.autocommit = True
        cursor = conn.cursor()
        
        cursor.execute(sql, (idKey, 
                             uuId, dict_id,
                             result_dict['group_1_N'], result_dict['group_2_N'], 
                             result_dict['group_1_mean'], result_dict['group_2_mean'], 
                             result_dict['group_1_std'], result_dict['group_2_std'],
                             result_dict['group_1_std_error'],result_dict['group_2_std_error'],
                             result_dict['F'],result_dict['sig'],
                             result_dict['group_equal_t'],result_dict['group_unequal_t'],
                             result_dict['group_equal_free_degree'],result_dict['group_unequal_free_degree'],
                             result_dict['group_equal_p'],result_dict['group_unequal_p'],
                             result_dict['group_equal_mean_error'],result_dict['group_unequal_mean_error'],
                             result_dict['group_equal_std_error'],result_dict['group_unequal_std_error'],
                             result_dict['group_equal_low'],result_dict['group_equal_up'],
                             result_dict['group_unequal_low'],result_dict['group_unequal_up'],
                             time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))
                             ))
        conn.commit()
      
    except:
        import traceback
        traceback.print_exc()
        conn.rollback()
    
    finally:
        cursor.close()
        conn.close()
    return 

# 测试代
if __name__ == "__main__":
    f1 = threading.Thread(target=serverHandler)
    f1.start()
