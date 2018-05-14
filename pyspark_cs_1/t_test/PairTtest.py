# coding: utf-8
import numpy
import time
import Tsparkcore
import math
from pyspark.sql import SQLContext, Row
import utils


STD_SCHEMA_STRING = "idCode,organID,examTime,examID,examJsonMapStr";
CROWD_SCHEMA_STRING = "idCode,organID,examTime,examID"
# HBASE_STANDARD = "cs_talent:base_standard"
HBASE_STANDARD = "member"
HBASE_CROWD = "cs_talent:statics_crowd_details"

def calc_python_spark(ask_parm, crowd_id):
    group1=ask_parm["group1"]
    group2=ask_parm["group2"]
    g_confident = float(ask_parm["confident"])
    print 'group1', group1, ', group2', group2
    print 'g_confident', g_confident
    option_dict, option_list =utils.option_dict_list(ask_parm['optionList'])
#     print 'option_dict', option_dict
#     option_list = []
    option_list.append("390")
    print "option_dict", option_dict

    group1["parmId"] = "390"
    group2["parmId"] = "390"
    sc = Tsparkcore.createSparkContext("crowd_Pair_test")
    sqlctx = SQLContext(sc)

    hbase_rdd = Tsparkcore.createSparkRDD(sc, HBASE_CROWD)
    hbase_rdd = Tsparkcore.calHbaseRDD(hbase_rdd, "EXAM_LIST", crowdID=crowd_id)
    output_hbase_rdd = hbase_rdd.map(lambda kv: Tsparkcore.map_crowd_Row(kv[1]))
 
    schemaCrowd = sqlctx.createDataFrame(output_hbase_rdd, samplingRatio=0.2)
    schemaCrowd.registerTempTable("statics_crowd_details")
    crowdDataFrame = sqlctx.sql("SELECT codeID, organID, examID FROM statics_crowd_details");

    data_rdd = Tsparkcore.createSparkRDD(sc, HBASE_STANDARD)
    data_rdd = Tsparkcore.calHbaseRDD(data_rdd, "EXAM_JSON")

    output_data_rdd = data_rdd.map(lambda kv: Tsparkcore.map_std_Row(kv[1], group1, option_list))

    schemaStd = sqlctx.createDataFrame(output_data_rdd, samplingRatio=0.2)
    schemaStd.registerTempTable("base_standard")
    stdDataFrame = sqlctx.sql("SELECT codeID, organID, examID, examTime, data_json, group_Type FROM base_standard").filter("group_Type=true")   
    stdDataFrame.show()
  
    cond = [crowdDataFrame.codeID == stdDataFrame.codeID, crowdDataFrame.organID == stdDataFrame.organID,
            crowdDataFrame.examID == stdDataFrame.examID]
      
    rsDataFrame = crowdDataFrame.join(stdDataFrame, cond, "inner").select(crowdDataFrame.codeID, crowdDataFrame.organID,
                  crowdDataFrame.examID, stdDataFrame.examTime, stdDataFrame.data_json)
    
    rsDataFrameG1 = rsDataFrame.filter(stdDataFrame.data_json["390"]> 17).orderBy(crowdDataFrame.codeID, stdDataFrame.examTime)
   
    rsDataFrameG2 = rsDataFrame.filter(stdDataFrame.data_json["390"] < 17).orderBy(crowdDataFrame.codeID, stdDataFrame.examTime)
 
    rsDataFrameG1RDD = rsDataFrameG1.map(lambda p:p.data_json)
    rsDataFrameG2RDD = rsDataFrameG2.map(lambda p:p.data_json)
   
    rsDataFrameG1Arrs = get_key_arrs(rsDataFrameG1RDD, option_list)
    rsDataFrameG2Arrs = get_key_arrs(rsDataFrameG2RDD, option_list)
    print "rsDataFrameG1Arrs " , rsDataFrameG1Arrs
    print "rsDataFrameG2Arrs " , rsDataFrameG2Arrs
   
    result = {}
    for colKey in option_list:
        if colKey is "390" :
            break
        name = option_dict[colKey]
        print "name =>" , name
        result[name] = get_result_arrs(rsDataFrameG1Arrs[colKey], rsDataFrameG2Arrs[colKey], g_confident, name)

    import json
    print result
    print json.dumps(result,ensure_ascii=False)
    sc.stop()
    return json.dumps(result,ensure_ascii=False)

def get_result_arrs(rsDataFrameG1KeyArrs, rsDataFrameG2KeyArrs, confidence, name):
    count1 = rsDataFrameG1KeyArrs.__len__()
    count2 = rsDataFrameG2KeyArrs.__len__()
    if(count1 > 0 and count2 > 0):
        if(count1 > count2):
            rsDataFrameG1KeyArrs = rsDataFrameG1KeyArrs[0:count2]
            count1 = count2
        elif(count1 < count2):
            rsDataFrameG2KeyArrs = rsDataFrameG2KeyArrs[0:count1]
            count2 = count1
        # arr_rs = []
        # arr_rs.append("name:"+(name+"_group1"))
        arr_rs = {}
        arr_rs["name"] = (name+"_group1")
        key_base1 = calc_key_base(rsDataFrameG1KeyArrs, confidence, count1, arr_rs)
        
        # arr_rs = []
        # arr_rs.append("name:"+(name+"_group2"))
        arr_rs = {}
        arr_rs["name"] = (name+"_group2")
        key_base2 = calc_key_base(rsDataFrameG2KeyArrs, confidence, count1, arr_rs)
        
        # arr_rs = []
        arr_rs = {}
        # arr_rs.append("name:"+(name+"_group1" + " & " + name+"_group2"))
        arr_rs["name"] = (name+"_group1" + " & " + name+"_group2")
        key_pearsonr = calc_key_pearsonr(rsDataFrameG1KeyArrs, rsDataFrameG2KeyArrs, count1, arr_rs)
        
        # arr_rs = []
        # arr_rs.append("name:"+(name+"_group1" + "-" + name+"_group2"))
        arr_rs = {}
        arr_rs["name"] = (name+"_group1" + " - " + name+"_group2")
        key_pair = calc_key_pair(rsDataFrameG1KeyArrs, rsDataFrameG2KeyArrs, confidence, count1, arr_rs)
        arrs = {}
        arrs["base1"] = key_base1
        arrs["base2"] = key_base2
        arrs["pear"] = key_pearsonr
        arrs["pair"] = key_pair
        return arrs
    else :
        if(count1 > 1):
            key_base = calc_key_base(rsDataFrameG1KeyArrs, confidence)
            arrs = {}
            arrs["base"] = key_base
            return arrs
        if(count2 > 1):
            key_base = calc_key_base(rsDataFrameG2KeyArrs, confidence)
            arrs = {}
            arrs["base"] = key_base
            return arrs
            
        return None
from scipy import stats

def calc_key_pearsonr(rsDataFrameG1KeyArrs, rsDataFrameG2KeyArrs, count, arr_rs):
    r, p=stats.pearsonr(rsDataFrameG1KeyArrs, rsDataFrameG2KeyArrs)
    
    # arr_rs.append("N:"+ str(count))
    arr_rs["N"]= str(count)
    # arr_rs.append("r:"+ utils.get_Decimal(r))
    arr_rs["r"] = utils.get_Decimal(r)
    # arr_rs.append("p:"+ utils.get_Decimal(p))
    arr_rs["p"] = utils.get_Decimal(p)
    return arr_rs
import numpy as np
def calc_key_pair(rsDataFrameG1KeyArrs, rsDataFrameG2KeyArrs, confidence, count, arr_rs):
    rsDataFrame = np.array(rsDataFrameG1KeyArrs)-np.array(rsDataFrameG2KeyArrs)
    mean, _, stddev, _, _, confidence = utils.statistics.stats(rsDataFrame, 1.0 - confidence)
    
    # arr_rs.append("mean:"+ utils.get_Decimal(mean))
    arr_rs["mean"] = utils.get_Decimal(mean)
    # arr_rs.append("N:"+str(count))
    arr_rs["N"] = str(count)
    # arr_rs.append("stddev:"+utils.get_Decimal(stddev))
    arr_rs["stddev"]= utils.get_Decimal(stddev)
    # arr_rs.append("stddeverror:"+utils.get_Decimal(stddev/math.sqrt(len(rsDataFrame))))
    arr_rs["stddeverror"]= utils.get_Decimal(stddev / math.sqrt(len(rsDataFrame)))
    # arr_rs.append("conf_down:" + utils.get_Decimal(mean - confidence))
    arr_rs["conf_down"] = utils.get_Decimal(mean - confidence)
    # arr_rs.append("conf_up:" + utils.get_Decimal(mean + confidence))
    arr_rs["conf_up"] = utils.get_Decimal(mean + confidence)
    ttest_rel = stats.ttest_rel(rsDataFrameG1KeyArrs,rsDataFrameG2KeyArrs)
    # arr_rs.append("static_t:" + utils.get_Decimal(ttest_rel[0]))
    arr_rs["static_t"] = utils.get_Decimal(ttest_rel[0])
    # arr_rs.append("static_free:" + str(count-1))
    arr_rs["static_free"] =  str(count - 1)
    # arr_rs.append("static_p:" + utils.get_Decimal(ttest_rel[1]))
    arr_rs["static_p"] = utils.get_Decimal(ttest_rel[1])
    return arr_rs

def calc_key_base(rsDataFrameGKeyArrs, confidence, count, arr_rs):
    mean, _, stddev, _, _, confidence = utils.statistics.stats(rsDataFrameGKeyArrs, 1.0 - confidence)
    # arr_rs.append("mean:"+ utils.get_Decimal(mean))
    arr_rs["mean"] = utils.get_Decimal(mean)
    # arr_rs.append("N:"+str(count))
    arr_rs["N"] = str(count)
    # arr_rs.append("stddev:"+utils.get_Decimal(stddev))
    arr_rs["stddev"] = utils.get_Decimal(stddev)
    # arr_rs.append("stddeverror:"+utils.get_Decimal(stddev/math.sqrt(len(rsDataFrameGKeyArrs))))
    arr_rs["stddeverror"] = utils.get_Decimal(stddev / math.sqrt(len(rsDataFrameGKeyArrs)))
    return arr_rs
    
def get_key_arrs(rsDataFrameGRDD, option_list):
    rsDataFrameGArrs = {}
    for colKey in option_list:
        rsDataFrameGArrs[colKey] = []
    for rowKey in rsDataFrameGRDD.collect():
        for colKey in option_list:
            if(rowKey.has_key(colKey)):
                rsDataFrameGArrs[colKey].append(rowKey[colKey])
    return rsDataFrameGArrs





