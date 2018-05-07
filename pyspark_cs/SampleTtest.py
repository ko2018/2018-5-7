import numpy
from scipy import stats
import statistics
import time
import math
import utils
import threading
import Tsparkcore
import mysql_connect


def calFinalResult(fKey, output_hbase_rdd_join_map):
    result = []
    for key in output_hbase_rdd_join_map.collect():
        if (key[1][fKey] and utils.is_number(key[1][fKey])) :
            result.append(float(key[1][fKey]))  
    return result

def saveSampleResultToDB(idKey, uuId, dict_id, result_dict):
    if result_dict is None :
        result_dict = {}
        result_dict['N'] = -1, 
        result_dict['mean']= -1, 
        result_dict['stddev']= -1,
        result_dict['stddev_error']= -1,
        result_dict['statistic']= -1,
        result_dict["s_free_degree"]= -1,#s_free_degree
        result_dict['pvalue']= -1,
        result_dict['mean_dev']= -1,
        result_dict['confidence_low']= -1,
        result_dict['confidence_up']= -1,
        result_dict['confidence']= -1,
        result_dict['popmean']= -1,
       
    try:
        conn = mysql_connect.connect()
      
        sql = mysql_connect.get_sample_test_result_sql_replace()
        conn.autocommit = True
        cursor = conn.cursor()
        
        cursor.execute(sql, (idKey, uuId, 
                             dict_id,result_dict['N'], 
                             result_dict['mean'], 
                             result_dict['stddev'],
                             result_dict['stddev_error'],
                             result_dict['statistic'],
                             result_dict["s_free_degree"],#s_free_degree
                             result_dict['pvalue'],
                             result_dict['mean_dev'],
                             result_dict['confidence_low'],
                             result_dict['confidence_up'],
                             result_dict['confidence'],
                             result_dict['popmean'],
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


def getSampleDBAskCalcList():
    list_ask_queue = []
    cursor = None
    try:
        conn = mysql_connect.connect()
        sql = mysql_connect.get_sample_test_sql_select()
        conn.autocommit = True
        cursor = conn.cursor()
        cursor.execute(sql)
        if cursor : 
            for row in cursor:
                list_ask_queue.append(print_Sample_DB(row))
                print row
        print list_ask_queue.__len__()
    except:
        import traceback
        traceback.print_exc()
        conn.rollback()
    
    finally:
        if cursor:
            cursor.close()
        conn.close()
    return list_ask_queue


def print_Sample_DB(row):
    uuId = row[0]
    crowID = row[1]
    confidence = row[2]
    popmean = row[3]
    dic_id = row[4]
    add_time = row[5]
    return  {'uuId':uuId, 'crowID':crowID, 'confidence':confidence, 'popmean':popmean, 'dic_id':dic_id, 'add_time':add_time}

    
def calc_python_spark(ask_parm, filterList):
    expect_popmean = ask_parm['popmean']
    expect_percent = ask_parm['confidence']
    crowd_id = ask_parm['crowID']
    sc = Tsparkcore.createSparkContext("crowd")
    hbase_rdd = Tsparkcore.createSparkRDD(sc, "cs_talent:statics_crowd_details")
    hbase_rdd = Tsparkcore.calHbaseRDD(hbase_rdd, "EXAM_LIST", crowdID=crowd_id)
    output_hbase_rdd = hbase_rdd.flatMap(lambda kv: Tsparkcore.map_list_crowd(kv[1]))
   
    data_rdd = Tsparkcore.createSparkRDD(sc, "cs_talent:base_standard")
    data_rdd = Tsparkcore.calHbaseRDD(data_rdd, "EXAM_JSON")
    output_data_rdd = data_rdd.flatMap(lambda kv: Tsparkcore.map_list_std(kv[1]))
 
    output_hbase_rdd_join = output_hbase_rdd.leftOuterJoin(output_data_rdd)
   
    output_hbase_rdd_join = output_hbase_rdd_join.filter(lambda kv: Tsparkcore.filter_cols_join(kv[1], filterList))
    output_hbase_rdd_join_map = output_hbase_rdd_join.flatMap(lambda kv: Tsparkcore.map_list_crowd_std_join(kv[0], kv[1], filterList))
    result_dict = {}
    for fKey in filterList:
        dataset1 = calFinalResult(fKey, output_hbase_rdd_join_map)
        if len(dataset1) < 1:
            continue
        dataset = numpy.array(dataset1)
#         dataset = [0,1,2,3,4,5,6,7,8,9]
        print dataset
        result_dict[fKey] = {}
        ttest_1samp = stats.ttest_1samp(dataset , popmean=expect_popmean)
        if ttest_1samp[0] == float('inf'):
            result_dict[fKey]["statistic"] = '0'
        else :
            result_dict[fKey]["statistic"] = utils.get_Decimal(ttest_1samp[0])
        result_dict[fKey]["pvalue"] = utils.get_Decimal(ttest_1samp[1])
        mean, _, stddev, _, _, confidence = statistics.stats(dataset, 1.0 - expect_percent)
        result_dict[fKey]["mean"] = utils.get_Decimal(mean)
        result_dict[fKey]["mean_dev"] = utils.get_Decimal(mean - expect_popmean)
        result_dict[fKey]["confidence_low"] = utils.get_Decimal(mean - confidence - expect_popmean)
        result_dict[fKey]["confidence_up"] = utils.get_Decimal(mean + confidence - expect_popmean)
        result_dict[fKey]["stddev"] = utils.get_Decimal(stddev)
        result_dict[fKey]["confidence"] = utils.get_Decimal(expect_percent)
        result_dict[fKey]["popmean"] = expect_popmean
        result_dict[fKey]["N"] = dataset.__len__()
        result_dict[fKey]["stddev_error"] = utils.get_Decimal(stddev/math.sqrt(len(dataset)))
        result_dict[fKey]["s_free_degree"] = dataset.__len__() - 1
        print result_dict[fKey]
    
    print result_dict
    sc.stop()
    return result_dict

def service():
    # 获取db ，任务队列
    list_ask_queue = getSampleDBAskCalcList()
    print "SampleTest, ", len(list_ask_queue)
    if list_ask_queue.__len__() <= 0:
        return
    for ask_parm in list_ask_queue:
        print ask_parm
        ask_parm['popmean'] = float(ask_parm['popmean']);
        ask_parm['confidence'] = float(ask_parm['confidence']);
        dic_id_str = ask_parm["dic_id"];
        dic_id_Arrs = dic_id_str.split(',')
        filterList = []
        for dic_id in dic_id_Arrs:
            filterList.append("_"+dic_id);
    
        print ask_parm
        #spark 计算结果
        result_dict = calc_python_spark(ask_parm, filterList)
        uuId = ask_parm["uuId"]
        crowID = ask_parm["crowID"]
        
        print result_dict

        for dic_id in dic_id_Arrs :
             
            if result_dict.__contains__("_"+dic_id) :
                result =  result_dict["_"+dic_id]
            else :
                result = None
            #  计算结果 入库
            saveSampleResultToDB(utils.create_id(uuId+crowID+dic_id), uuId, dic_id, result)
            #  更新状态
            updateSampleStatus(uuId)

    return
# 测试代码
def serverHandler():


    list_ask_queue = getSampleDBAskCalcList()
    for ask_parm in list_ask_queue:
        print ask_parm
        ask_parm['popmean'] = float(ask_parm['popmean']);
        ask_parm['confidence'] = float(ask_parm['confidence']);
        dic_id_str = ask_parm["dic_id"];
        dic_id_Arrs = dic_id_str.split(',')
        filterList = []
        for dic_id in dic_id_Arrs:
            filterList.append("_"+dic_id);
   
        print ask_parm
        result_dict = calc_python_spark(ask_parm, filterList)
        uuId = ask_parm["uuId"]
        crowID = ask_parm["crowID"]
       
        print result_dict
        for dic_id in dic_id_Arrs:
            saveSampleResultToDB(utils.create_id(uuId+crowID+dic_id), uuId, dic_id, result_dict["_"+dic_id])
            updateSampleStatus(uuId)

    
def updateSampleStatus(uuID):
    try:
        conn = mysql_connect.connect()
      
        sql = mysql_connect.get_sample_test_update()
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
        if cursor : 
            cursor.close()
        conn.close()
    return


if __name__ == "__main__":
    f1 = threading.Thread(target=serverHandler)
    f1.start()
    
