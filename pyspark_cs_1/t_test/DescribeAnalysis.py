# coding: utf-8
import numpy
import time
import threading
import Tsparkcore
from utils import mysql_connect
import pandas as pd
import math

MEAN_1='1'  
MIDDLE_2='2'
MODE_3='3' 
TOTAL_4='4' 
STD_11='11' 
MIN_12='12' 
VAR_13='13' 
MAX_14='14'
FIELD_15='15' 
PERCENT_Q_5 = '5'
PERCENT_U_6 = '6'
PERCENT_P_7 = '7'
MEAN_STD_ERROR_16='16'
PARTIAL_W_21='21' 
PARTIAL_K_22='22' 

def print_DescribeAnalysis_DB(row):
    uuId = row[0]
    crowID = row[1]
    groupID = row[2]
    dict_list = row[3]
    utype = row[4]
    optinal_list = row[5]
    sort = row[6]

    return  {'uuId':uuId, 'crowID':crowID, 'dict_list':dict_list, 
             'groupID':groupID,'type':utype,'optinal_list':optinal_list,
             'sort':sort
            }
    
def getDescribeAnalysiscList():
    list_ask_queue = []
    cursor = None
    conn = None
    try:
        conn = mysql_connect.connect()
        sql = mysql_connect.get_describe_analysisc_sql_select()
        conn.autocommit = True
        cursor = conn.cursor()
        cursor.execute(sql)
        if cursor : 
            for row in cursor:
                list_ask_queue.append(print_DescribeAnalysis_DB(row))
                print row
            
    except:
        import traceback
        traceback.print_exc()
        if conn :
            conn.rollback()
    
    finally:
        if cursor : 
            cursor.close()
        if conn :
            conn.close()
    return list_ask_queue


def getDescribeAnalysisDBList(group):
    list_group_queue = []
    try:
        conn = mysql_connect.connect()
        sql = mysql_connect.get_describe_analysis_sql_group_select()
        conn.autocommit = True
        cursor = conn.cursor()
        cursor.execute(sql, (group,))

        for row in cursor:
            group_test_row = Tsparkcore.print_Group_DB(row)
            list_group_queue.append(group_test_row)
            print row
            
    except:
        import traceback
        traceback.print_exc()
        conn.rollback()
    
    finally:
        cursor.close()
        conn.close()
    return list_group_queue

def calc_python_spark(ask_parm, filterList, group):

    crowd_id = ask_parm['crowID']
    optinal_list = ask_parm['optinal_list']
    utype = ask_parm["type"]
    sc = Tsparkcore.createSparkContext("crowd")
    hbase_rdd = Tsparkcore.createSparkRDD(sc, "cs_talent:statics_crowd_details")
    hbase_rdd = Tsparkcore.calHbaseRDD(hbase_rdd, "EXAM_LIST", crowdID=crowd_id)
    output_hbase_rdd = hbase_rdd.flatMap(lambda kv: Tsparkcore.map_list_crowd(kv[1]))
   
#     utils.print_output(output_hbase_rdd.collect()) 
    
    data_rdd = Tsparkcore.createSparkRDD(sc, "cs_talent:base_standard")
    data_rdd = Tsparkcore.calHbaseRDD(data_rdd, "EXAM_JSON")
    output_data_rdd = data_rdd.flatMap(lambda kv: Tsparkcore.map_list_std(kv[1]))
    output_hbase_rdd_join = output_hbase_rdd.leftOuterJoin(output_data_rdd)
#     utils.print_output(output_hbase_rdd_join.collect()) 
    output_hbase_rdd_join_1 = output_hbase_rdd_join.filter(lambda kv: Tsparkcore.filter_cols_join_True(kv[1], filterList, group))
    
    if output_hbase_rdd_join_1.isEmpty() :
        sc.stop()
        print "rdd isEmpty"
        return None
    result_dict = {}
    
    output_hbase_rdd_join_map_1 = output_hbase_rdd_join_1.flatMap(lambda kv: Tsparkcore.map_list_crowd_std_join(kv[0], kv[1], filterList))
    for fKey in filterList:
        result_dict[fKey] = {}
        group = group_collect(fKey, output_hbase_rdd_join_map_1)
        if utype == '0' :
            result_dict[fKey] = utils.to_json(calc_d_a_result_type_0(numpy.array(group), optinal_list))
        else :
            result_dict[fKey][0] = utils.to_json(calc_d_a_result_type_2(numpy.array(group), optinal_list))
            result_dict[fKey][1] = utils.to_json(calc_d_a_result_type_1(numpy.array(group)))
#             result_dict[fKey][1] = utils.to_json(calc_d_a_result_type_1_test(numpy.array(group)))
#             print "result_dict[fKey][1] ==>" + result_dict[fKey][1]
        print fKey, result_dict[fKey] 
        
    sc.stop()
    return result_dict

import utils
import collections

def calc_describe_analysis_percent(dataset):
    counter = collections.Counter(dataset)
    sort=sorted(counter.items(),key=lambda e:e[1],reverse=True)
    return dict(sort)

def calc_d_a_result_type_1(dataset):
    counter = collections.Counter(dataset)
    tmp = dict(counter)
   
    sort=sorted(tmp.items(),key=lambda e:e[1],reverse=True)
    tmp = dict(sort)
    N = len(dataset) *1.0
#     print tmp
    k=0
    print "tmp=====", tmp
    tmp_rs = {}
    for key in tmp :
        val = tmp[key]
#         print key, tmp[key]
        k = k + tmp[key]
#         print tmp[key]*1.0/N, k*1.0/N
        t_dict = {}
        t_dict["1"] = val
        t_dict["2"] = val*1.0/N*100
        t_dict["3"] = t_dict["2"]
        t_dict["4"] = k*1.0/N*100
        tmp_rs[key*1.0] = t_dict
    t_dict_N = {}
    t_dict_N["1"] = len(dataset)
    t_dict_N["2"] = 100.0
    t_dict_N["3"] = 100.0
    tmp_rs["N"] = t_dict_N
    tmp_rs["TYPE"] = "1"
    return tmp_rs


def calc_d_a_result_type_1_test(dataset):
    counter = collections.Counter(dataset)
    tmpCount = dict(counter)
   
    sort=sorted(tmpCount.items(),key=lambda e:e[1],reverse=True)
    tmpCount = dict(sort)
    N = len(dataset) *1.0
#     print tmp
    k=0
    print "tmp=====", tmpCount
    tmp_rs = []
    for key in tmpCount :
        val = tmpCount[key]
#         print key, tmp[key]
        k = k + tmpCount[key]
#         print tmp[key]*1.0/N, k*1.0/N
        t_dict = []
        
#         t_dict["1"] = val
        t_dict.append({"1":val})
#         t_dict["2"] = val*1.0/N*100
        t_dict.append({"2":val*1.0/N*100})
#         t_dict["3"] = t_dict["2"]
        t_dict.append({"3":val*1.0/N*100})
#         t_dict["4"] = k*1.0/N*100
        t_dict.append({"4":k*1.0/N*100})
        tmp_rs[key*1.0] = t_dict
        
    t_dict_N = []
#     t_dict_N["1"] = len(dataset)
    t_dict_N.append({"1":len(dataset)})
#     t_dict_N["2"] = 100.0
    t_dict_N.append({"2":100.0})
#     t_dict_N["3"] = 100.0
    t_dict_N.append({"3":100.0})
    tmp_rs["N"] = t_dict_N
    tmp_rs["TYPE"] = "1"

    return tmp_rs


def calc_d_a_result_type_0(dataset, optinal_list):
    sDict = {}
    if optinal_list  is  None : 
        return None
    optArrs = optinal_list.split(",")
#     dataset = [1.0,12.0,3.0,4.0,5.0,6.0,17.0,8.0,9.0,10.0]
    N = len(dataset)
    df = pd.Series(dataset);
#     print "optArrs", optArrs
#     print len(dataset)
    percent_dict = set()

    for optKey in optArrs :
      
        if optKey == MEAN_1 :
            sDict[optKey] = utils.get_Decimal(df.mean())
            continue
        if optKey == MIDDLE_2:
            sDict[optKey] = utils.get_Decimal(df.median())
            continue
        if optKey == TOTAL_4:
            sDict[optKey] = utils.get_Decimal(df.sum())
            continue
        if optKey == STD_11:
            sDict[optKey] = utils.get_Decimal(df.std())
            continue
        if optKey == MIN_12:
            sDict[optKey] = utils.get_Decimal(df.min())
            continue
        if optKey == VAR_13:
            sDict[optKey] = utils.get_Decimal(df.var())
            continue
        if optKey == MAX_14:
            sDict[optKey] = utils.get_Decimal(df.max())
            continue
        if optKey == FIELD_15:
            sDict[optKey] = utils.get_Decimal(df.max() - df.min())
            continue
        if optKey == MEAN_STD_ERROR_16:
            sDict[optKey] = utils.get_Decimal( df.std()/math.sqrt(df.__len__()))
            continue
        if optKey == PARTIAL_W_21:
            sDict[optKey] = utils.get_Decimal(df.skew())
           
            if(N <= 2) :
                sDict[optKey + str("-0")] = "inf"
            else :
                m =  6*N*(N-1)*1.0
                p = ((N-2)*(N+1)*(N+3))*1.0
                sDict[optKey + str("-0")] = utils.get_Decimal(math.sqrt(m / p))
            continue
        if optKey == PARTIAL_K_22:
            sDict[optKey] = utils.get_Decimal(df.kurt())
            if(N <= 3) :
                sDict[optKey + str("-0")] = "inf"
            else : 
                k = 24*N*math.pow(N-1, 2)*1.0 
                y = (N-2)*(N-3)*(N+3)*(N+5)*1.0
                sDict[optKey + str("-0")] = utils.get_Decimal(math.sqrt(k / y))
            continue
        if optKey == PERCENT_Q_5:
#             sDict[PERCENT_Q_5] = 25,50,75
            percent_dict.add(25)
            percent_dict.add(50)
            percent_dict.add(75)
            continue
        optKey_str = str(optKey)
        if optKey_str.__contains__('6=') :
            step = int(optKey_str.split("=")[1]) 
            arrlist = utils.floatrange(0.0,100.0, step+1)
            len_arr = len(arrlist)
            arrlist = arrlist[1:len_arr-1]
            for tt in arrlist :
                percent_dict.add(tt)
            continue
        if optKey_str.__contains__('7=') :
            val = optKey_str.split("=")[1]
            val = val.replace('[', '').replace(']', '')
            print "optKey_str-------->", optKey_str, val
            for vv in val.split(","):
                percent_dict.add(float(vv))
#             sDict[PERCENT_P_7] = 
            continue

    sDict['N'] = str(N)
    sDict['N_N'] = str(N)
    sDict['TYPE'] = "0"
   
    sDict[P] = list(percent_dict)
    print "percent_dict=========>", sDict[P]
    return  sDict

P = 'P'
def calc_d_a_result_type_2(dataset, optinal_list):
    sDict = calc_d_a_result_type_0(dataset, optinal_list)
    if sDict is None :
        return None
    
    sDict['TYPE'] = "2"
    if sDict.__contains__(P):
#         print sDict[P]
        vList = list(sDict[P])
        vList = sorted(vList)
#         rlist = {}
        print dataset
        v_dict = {}
        for val in vList :
            print 'val', val
            v_dict[float(val)]= utils.get_Decimal(utils.get_percent(dataset, float(val)))
#             rlist.append(v_dict)
        sDict[P] = v_dict   
    
    print "sDict[P] =====>", sDict[P]
    return sDict

def group_collect(fKey, output_hbase_rdd_join_map):
    slist = []
    for key in output_hbase_rdd_join_map.collect():
#         print key[1] , key[1][fKey]
        if(key[1][fKey] and utils.is_number(key[1][fKey])) :
            slist.append(float(key[1][fKey]))
    return slist


def service():
    list_ask_queue = getDescribeAnalysiscList()
    print "DescribeAnalysis, ", len(list_ask_queue)
    if list_ask_queue.__len__() <= 0:
        return
   
    for ask_parm in list_ask_queue:
        print ask_parm

        dict_id_str = ask_parm["dict_list"];
        dict_id_Arrs = dict_id_str.split(',')
        filterList = []
        for dict_id in dict_id_Arrs:
            filterList.append("_"+dict_id);
        list_group_queue = getDescribeAnalysisDBList(ask_parm['groupID'])
        if list_group_queue.__len__() < 1 :
            print "warning:", list_group_queue.__len__() 
            continue            

        
        result_dict = calc_python_spark(ask_parm, filterList, list_group_queue[0]);
        print " result_dict", result_dict
        uuId = ask_parm["uuId"]
        crowID = ask_parm["crowID"]
        utype = ask_parm["type"]
        print result_dict   
        if result_dict is None :
            print crowID, uuId
            updateDescribeAnalysisStatus(uuId)
            continue
       
        print  "utype--", utype  
        for dict_id in dict_id_Arrs:
            try:
                if(utype == "0") :   
                    saveDescribeAnalysisResultToDB(utils.create_id(uuId+crowID+dict_id), uuId, dict_id, result_dict["_"+dict_id])
                    updateDescribeAnalysisStatus(uuId)
                else :
                    saveDescribeAnalysisResultToDB(utils.create_id(uuId+crowID+dict_id+"0"), uuId, dict_id, result_dict["_"+dict_id][0])
                    saveDescribeAnalysisResultToDB(utils.create_id(uuId+crowID+dict_id+"1"), uuId, dict_id, result_dict["_"+dict_id][1])
                    updateDescribeAnalysisStatus(uuId)
            except ZeroDivisionError,e:
                print e.message
            
            print "done", uuId, crowID, dict_id
    return

def serverHandler():

    list_ask_queue = getDescribeAnalysiscList()
    if list_ask_queue.__len__() <= 0:
       return
    
    for ask_parm in list_ask_queue:
        print ask_parm
    
        dict_id_str = ask_parm["dict_list"];
        dict_id_Arrs = dict_id_str.split(',')
        filterList = []
        for dict_id in dict_id_Arrs:
            filterList.append("_"+dict_id);
        list_group_queue = getDescribeAnalysisDBList(ask_parm['groupID'])
        if list_group_queue.__len__() < 1 :
            print "warning:", list_group_queue.__len__() 
            continue            
    
        result_dict = calc_python_spark(ask_parm, filterList, list_group_queue[0]);
        
        uuId = ask_parm["uuId"]
        crowID = ask_parm["crowID"]
        utype = ask_parm["type"]
        print "result_dict", result_dict   
        if result_dict is None :
            print "crowID", crowID, "uuId", uuId
            updateDescribeAnalysisStatus(uuId)
            continue
       
        print  "utype--", utype  
        for dict_id in dict_id_Arrs:
        
            try:
                if(utype == "0") :   
                    saveDescribeAnalysisResultToDB(utils.create_id(uuId+crowID+dict_id), uuId, dict_id, result_dict["_"+dict_id])
                    updateDescribeAnalysisStatus(uuId)
                else :
                    saveDescribeAnalysisResultToDB(utils.create_id(uuId+crowID+dict_id+"0"), uuId, dict_id, result_dict["_"+dict_id][0])
                    saveDescribeAnalysisResultToDB(utils.create_id(uuId+crowID+dict_id+"1"), uuId, dict_id, result_dict["_"+dict_id][1])
                    updateDescribeAnalysisStatus(uuId)
            except ZeroDivisionError,e:
                print e.message
            
            print "done", uuId, crowID, dict_id
    
    return             
    


def updateDescribeAnalysisStatus(uuID):
    conn = None
    cursor = None
    try:
        conn = mysql_connect.connect()
      
        sql = mysql_connect.get_Describe_Analysis_update()
        conn.autocommit = True
        cursor = conn.cursor()
        
        cursor.execute(sql, ( time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time())),
                                           uuID)
                             )
        conn.commit()
      
    except:
        import traceback
        traceback.print_exc()
        if conn:
            conn.rollback()
    
    finally:
        if cursor : 
            cursor.close()
        if conn :
            conn.close()
    return

def saveDescribeAnalysisResultToDB(idKey, uuId, dict_id, result_dict):    
    if result_dict is None :
        return   
    conn = None
    cursor = None
    try:
        conn = mysql_connect.connect()
      
        sql = mysql_connect.get_Describe_Analysis_result_sql_replace()
        conn.autocommit = True
        cursor = conn.cursor()
        
        cursor.execute(sql, (idKey, 
                             uuId, dict_id,
                             result_dict,
                             time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))
                             ))
        conn.commit()
      
    except:
        import traceback
        traceback.print_exc()
        if conn :
            conn.rollback()
    
    finally:
        if cursor:
            cursor.close()
        if conn :
            conn.close()
    return 

if __name__ == "__main__":
    f1 = threading.Thread(target=serverHandler)
    f1.start()

