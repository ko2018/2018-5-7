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

from statsmodels.formula.api import ols
from statsmodels.stats.anova import anova_lm
def calc_python_spark(ask_parm, crowd_id):
    ask_parm['D'] = '9'
    ask_parm['F'] = '390,9'
    ask_parm['A'] = '390,9'
    ask_parm['C'] = '390,9'
    ask_parm['WLS'] = '390,9'

    D = ask_parm['D']
    F_Arrs = ask_parm['F'].split(',')
    option_dict, option_list = utils.option_dict_list(ask_parm['optionList'])
    print "option_dict", option_dict

    sc = Tsparkcore.createSparkContext("crowd_ANOVA_TWO_WAY")
    sqlctx = SQLContext(sc)

    hbase_rdd = Tsparkcore.createSparkRDD(sc, HBASE_CROWD)
    hbase_rdd = Tsparkcore.calHbaseRDD(hbase_rdd, "EXAM_LIST", crowdID=crowd_id)
    output_hbase_rdd = hbase_rdd.map(lambda kv: Tsparkcore.map_crowd_Row(kv[1]))

    schemaCrowd = sqlctx.createDataFrame(output_hbase_rdd, samplingRatio=0.2)
    schemaCrowd.registerTempTable("statics_crowd_details")
    crowdDataFrame = sqlctx.sql("SELECT codeID, organID, examID FROM statics_crowd_details")

    data_rdd = Tsparkcore.createSparkRDD(sc, HBASE_STANDARD)
    data_rdd = Tsparkcore.calHbaseRDD(data_rdd, "EXAM_JSON")

    output_data_rdd = data_rdd.map(lambda kv: Tsparkcore.map_std_Row_Two_Way_anova(kv[1], option_list))

    schemaStd = sqlctx.createDataFrame(output_data_rdd, samplingRatio=0.2)
    schemaStd.registerTempTable("base_standard")
    stdDataFrame = sqlctx.sql(
        "SELECT  codeID, organID, data_json, examTime, rowKey, examID, has_Type FROM base_standard").filter('has_Type=True')

    # print stdDataFrame.show()

    cond = [crowdDataFrame.codeID == stdDataFrame.codeID, crowdDataFrame.organID == stdDataFrame.organID,
            crowdDataFrame.examID == stdDataFrame.examID]

    rsDataFrame = crowdDataFrame.join(stdDataFrame, cond, "inner").select(crowdDataFrame.codeID, crowdDataFrame.organID,
                                                                          crowdDataFrame.examID, stdDataFrame.examTime,
                                                                          stdDataFrame.data_json)
    list = []
    i = 0;
    list.append(rsDataFrame.data_json[D].alias('D'))
    formula_list = []

    for key in  F_Arrs:
        i = i+1;
        formula_list.append('F'+str(i))
        list.append(rsDataFrame.data_json[key].alias('F'+str(i)))
    df_D = rsDataFrame.select(list).toPandas()

    print 'df_D', df_D

    formula = "D~ " + "+".join(formula_list)
    print 'formula', formula
    anova_results = anova_lm(ols(formula, df_D).fit())
    print anova_results

    result = {}

    import json
    result['1']=anova_results

    sc.stop()
    return json.dumps(result, ensure_ascii=False)




def get_key_arrs(rsDataFrameGRDD, option_list):
    rsDataFrameGArrs = {}
    for colKey in option_list:
        rsDataFrameGArrs[colKey] = []
    for rowKey in rsDataFrameGRDD.collect():
        for colKey in option_list:
            if(rowKey.has_key(colKey)):
                rsDataFrameGArrs[colKey].append(rowKey[colKey])
    return rsDataFrameGArrs


if __name__ == "__main__":
    ask_parm = {}
    ask_parm['optionList'] = "30:身高,1609:内脏脂肪水平,9:性别,4:体检编号,390:aa"
    calc_python_spark(ask_parm, '1')
