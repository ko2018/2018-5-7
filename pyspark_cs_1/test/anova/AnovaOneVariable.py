# coding: utf-8
import numpy
import time
import Tsparkcore
import math
from pyspark.sql import SQLContext, Row
import utils
from scipy import stats
import pandas as pd
from statsmodels.formula.api import ols
import statsmodels.api as sm
from statsmodels.stats.anova import anova_lm
from statsmodels.stats.multicomp import pairwise_tukeyhsd
import matplotlib.pyplot as plt
from statsmodels.stats.anova import anova_lm
from pandas import Series, DataFrame
import json

STD_SCHEMA_STRING = "idCode,organID,examTime,examID,examJsonMapStr";
CROWD_SCHEMA_STRING = "idCode,organID,examTime,examID"
# HBASE_STANDARD = "cs_talent:base_standard"
HBASE_STANDARD = "member"
HBASE_CROWD = "cs_talent:statics_crowd_details"


def calc_python_spark(ask_parm, crowd_id):
    group1 = ask_parm["group1"]
    group2 = ask_parm["group2"]

    print 'group1', group1, ', group2', group2

    option_dict, option_list = utils.option_dict_list(ask_parm['optionList'])
    print "option_dict", option_dict
    option_list.append("390")
    group1["parmId"] = "390"
    group2["parmId"] = "390"
    sc = Tsparkcore.createSparkContext("crowd_Pair_test")
    sqlctx = SQLContext(sc)

    hbase_rdd = Tsparkcore.createSparkRDD(sc, HBASE_CROWD)
    hbase_rdd = Tsparkcore.calHbaseRDD(hbase_rdd, "EXAM_LIST", crowdID=crowd_id)
    output_hbase_rdd = hbase_rdd.map(lambda kv: Tsparkcore.map_crowd_Row(kv[1]))

    schemaCrowd = sqlctx.createDataFrame(output_hbase_rdd, samplingRatio=0.2)
    schemaCrowd.registerTempTable("statics_crowd_details")
    crowdDataFrame = sqlctx.sql("SELECT codeID, organID, examID FROM statics_crowd_details")

    data_rdd = Tsparkcore.createSparkRDD(sc, HBASE_STANDARD)
    data_rdd = Tsparkcore.calHbaseRDD(data_rdd, "EXAM_JSON")

    output_data_rdd = data_rdd.map(lambda kv: Tsparkcore.map_std_Row(kv[1], group1, option_list))

    schemaStd = sqlctx.createDataFrame(output_data_rdd, samplingRatio=0.2)
    schemaStd.registerTempTable("base_standard")
    stdDataFrame = sqlctx.sql(
        "SELECT codeID, organID, examID, examTime, data_json, group_Type FROM base_standard").filter("group_Type=true")
    # stdDataFrame.show()

    cond = [crowdDataFrame.codeID == stdDataFrame.codeID, crowdDataFrame.organID == stdDataFrame.organID,
            crowdDataFrame.examID == stdDataFrame.examID]

    rsDataFrame = crowdDataFrame.join(stdDataFrame, cond, "inner").select(crowdDataFrame.codeID, crowdDataFrame.organID,
                                                                          crowdDataFrame.examID, stdDataFrame.examTime,
                                                                          stdDataFrame.data_json)

    rsDataFrameG1 = rsDataFrame.filter(stdDataFrame.data_json["390"] > 17).orderBy(crowdDataFrame.codeID,
                                                                                   stdDataFrame.examTime)

    rsDataFrameG2 = rsDataFrame.filter(stdDataFrame.data_json["390"] < 17).orderBy(crowdDataFrame.codeID,
                                                                                   stdDataFrame.examTime)

    rsDataFrameG1RDD = rsDataFrameG1.map(lambda p: p.data_json)
    rsDataFrameG2RDD = rsDataFrameG2.map(lambda p: p.data_json)

    rsDataFrameG1Arrs = get_key_arrs(rsDataFrameG1RDD, option_list)
    rsDataFrameG2Arrs = get_key_arrs(rsDataFrameG2RDD, option_list)
    print "rsDataFrameG1Arrs ", rsDataFrameG1Arrs
    print "rsDataFrameG2Arrs ", rsDataFrameG2Arrs

    result = {}
    for colKey in option_list:
        if colKey is "390":
            break
        name = option_dict[colKey]
        print "name =>", name
        result[name] = get_result_arrs(rsDataFrameG1Arrs[colKey], rsDataFrameG2Arrs[colKey])

    print result
    sc.stop()
    return json.dumps(result, ensure_ascii=False)


def get_result_arrs(rsDataFrameG1KeyArrs, rsDataFrameG2KeyArrs):
    result = {}

    data = {'value': rsDataFrameG1KeyArrs, 'group': rsDataFrameG2KeyArrs}
    frame = DataFrame(data)
    print frame
    anova_result = anova_lm(ols('value~C(group)', frame).fit())
    print anova_result
    print (anova_result.to_json())
    return (anova_result.to_json())


def get_key_arrs(rsDataFrameGRDD, option_list):
    rsDataFrameGArrs = {}
    for colKey in option_list:
        rsDataFrameGArrs[colKey] = []
    for rowKey in rsDataFrameGRDD.collect():
        for colKey in option_list:
            if(rowKey.has_key(colKey)):
                rsDataFrameGArrs[colKey].append(rowKey[colKey])
    return rsDataFrameGArrs