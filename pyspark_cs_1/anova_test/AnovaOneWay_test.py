# coding: utf-8
import Tsparkcore
from pyspark.sql import SQLContext, Row
import utils
from statsmodels.formula.api import ols
from statsmodels.stats.anova import anova_lm
from pandas import DataFrame

STD_SCHEMA_STRING = "idCode,organID,examTime,examID,examJsonMapStr";
CROWD_SCHEMA_STRING = "idCode,organID,examTime,examID"
HBASE_STANDARD = "cs_talent:base_standard"
HBASE_CROWD = "cs_talent:statics_crowd_details"


def calc_python_spark(ask_parm, crowd_id):
    group1 = ask_parm["group1"]
    group2 = ask_parm["group2"]

    print 'group1', group1, ', group2', group2

    print "parmId", type(group1['parmId'])
    print "parmVal", type(group1['parmVal'])
    print "parmType", type(group1['parmType'])

    print "parmId", type(group2['parmId'])
    print "parmVal", type(group2['parmVal'])
    print "parmType", type(group2['parmType'])



    option_dict, option_list = utils.option_dict_list(ask_parm['optionList'])
    print "option_dict", option_dict

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

    output_data_rdd = data_rdd.map(lambda kv: Tsparkcore.map_std_Row_test(kv[1],  option_list))

    schemaStd = sqlctx.createDataFrame(output_data_rdd, samplingRatio=0.2)
    schemaStd.registerTempTable("base_standard")
    stdDataFrame = sqlctx.sql(
        "SELECT codeID, organID, examID, examTime, data_json FROM base_standard")



    cond = [crowdDataFrame.codeID == stdDataFrame.codeID, crowdDataFrame.organID == stdDataFrame.organID,
            crowdDataFrame.examID == stdDataFrame.examID]

    rsDataFrame = crowdDataFrame.join(stdDataFrame, cond, "inner").select(crowdDataFrame.codeID, crowdDataFrame.organID,
                                                                          crowdDataFrame.examID, stdDataFrame.examTime,
                                                                          stdDataFrame.data_json )

    rsDataFrameG1 = get_df_group(group1, rsDataFrame)
    rsDataFrameG2 = get_df_group(group2, rsDataFrame)

    result = {}

    for colKey in option_list:
        print "colKey", colKey
        name = option_dict[colKey]
        print "name =>", name, ", colKey=>"+colKey
        G1 = rsDataFrameG1.select(rsDataFrameG1.data_json["390"]).toPandas()
        G2 = rsDataFrameG2.select(rsDataFrameG2.data_json["390"]).toPandas()

        result[name] = get_result_arrs(G1, G2)

    import json
    print result

    sc.stop()
    return json.dumps(result, ensure_ascii=False)



def get_df_group(group, rsDataFrame):
    key = group['parmId']
    val = group['parmVal']
    type = group['parmType']
    key = "390"

    rsDataFrameN = None
    if type is 1:
        rsDataFrameN = rsDataFrame.filter(
            rsDataFrame["data_json"][key].__gt__("25.0") & rsDataFrame["data_json"][key].__lt__("35.0")).select(rsDataFrame["data_json"])
        return rsDataFrameN

    elif type is 2:
        rsDataFrameN = rsDataFrame.filter(
            (rsDataFrame.data_json[key] == 21) | (rsDataFrame.data_json[key] == 23)).select(rsDataFrame["data_json"])
        return rsDataFrameN
    else :
        return rsDataFrameN


def get_result_arrs(G1, G2):
    if G2.size < 1 or G1.size < 1:
        return None

    if (G2.size > G1.size):
        GLen = G1.size
        G2 = G2[0:GLen]
    elif (G2.size < G1.size):
        GLen = G2.size
        G1 = G1[0:GLen]

    result = {}

    data = {'value': G1, 'group': G2}
    frame = DataFrame()
    frame['value'] = G1.astype('category')
    frame['group'] = G2.astype('category')

    anova_result = anova_lm(ols('value~C(group)', frame).fit())
    print "anova_result", anova_result
    # print (anova_result.to_json())
    return (anova_result.to_json())


