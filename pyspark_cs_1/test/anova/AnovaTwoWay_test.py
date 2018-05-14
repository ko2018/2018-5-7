# coding: utf-8
import Tsparkcore
from pyspark.sql import SQLContext, Row
import utils
from pandas import DataFrame

STD_SCHEMA_STRING = "idCode,organID,examTime,examID,examJsonMapStr";
CROWD_SCHEMA_STRING = "idCode,organID,examTime,examID"
HBASE_STANDARD = "cs_talent:base_standard"
HBASE_CROWD = "cs_talent:statics_crowd_details"

from statsmodels.formula.api import ols
from statsmodels.stats.anova import anova_lm
def calc_python_spark(ask_parm, crowd_id):
    ask_parm['D_F'] = ask_parm['D_F'].split(":")[0]
    option_dict, option_list = utils.option_dict_list(ask_parm['F_F'])
    arrs = ask_parm['F_F'].split(",")
    list = []
    for key in arrs:
        list.append(key.split(":")[0])
    ask_parm['F_F'] =','.join(list)
    D = ask_parm['D_F']
    F = ask_parm['F_F']

    F_Arrs = F.split(',')

    print "option_dict", option_dict
    option_list.append(D)
    arrs = F.split(",")
    for arr in arrs:
        option_list.append(arr)

    group1 = {}
    group1['parmId'] = 390
    group1['parmVal'] = "12-14"
    group1['parmType'] = 1

    group2 = {}
    group2['parmId'] = 390
    group2['parmVal'] = "12-14"
    group2['parmType'] = 1

    group_all = {}
    group_all["390"] = [group1, group2, group2]


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

    output_data_rdd = data_rdd.map(lambda kv: Tsparkcore.map_std_Row_test(kv[1], option_list))

    schemaStd = sqlctx.createDataFrame(output_data_rdd, samplingRatio=0.2)
    schemaStd.registerTempTable("base_standard")
    stdDataFrame = sqlctx.sql(
        "SELECT  codeID, organID, data_json, examTime, rowKey, examID FROM base_standard")

    cond = [crowdDataFrame.codeID == stdDataFrame.codeID, crowdDataFrame.organID == stdDataFrame.organID,
            crowdDataFrame.examID == stdDataFrame.examID]

    rsDataFrame = crowdDataFrame.join(stdDataFrame, cond, "inner").select(crowdDataFrame.codeID, crowdDataFrame.organID,
                                                                          crowdDataFrame.examID, stdDataFrame.examTime,
                                                                          stdDataFrame.data_json)

    rsDataFramePD= None
    # for key in group_all :
    #     groups = group_all[key]
    #     list = []
    #     k=0
    #
    #     for group in groups:
    #         rsDataFrameG = get_df_group(group, rsDataFrame)
    #         if rsDataFrameG is  None :
    #             continue
    #
    #         rsDataFrameG = rsDataFrameG.withColumn(key + "_1", rsDataFrameG.data_json["390"]*0 + k)
    #         if rsDataFramePD is None :
    #             rsDataFramePD = rsDataFrameG
    #         else :
    #             rsDataFramePD = rsDataFramePD.unionAll(rsDataFrameG)
    #
    #         print "k=", k
    #         k = k + 1
    #
    #     print "rsDataFramePD==》", rsDataFramePD

            # list.append(rsDataFrameG)

        # get_mult_group(list, key)



    formula_list = []
    rsDataFrameG1 = get_df_group(group_all["390"][0], rsDataFrame)
    rsDataFrameG2 = get_df_group(group_all["390"][1], rsDataFrame)
    rsDataFrameG3 = get_df_group(group_all["390"][2], rsDataFrame)
    frame = DataFrame()
    G1 = rsDataFrameG1.select(rsDataFrameG1.data_json["390"]).toPandas()
    G2 = rsDataFrameG2.select(rsDataFrameG2.data_json["390"]).toPandas()
    G3 = rsDataFrameG3.select(rsDataFrameG2.data_json["390"]).toPandas()


    frame['D'] = G1
    frame_Arrs = []
    frame_Arrs.append(G2)
    frame_Arrs.append(G3)
    i=0
    for key in  F_Arrs:
        i = i+1;
        formula_list.append('F'+str(i))
        frame['F'+str(i)] = frame_Arrs[i-1]

    formula = "D~ " + "+".join(formula_list)
    print 'formula', formula
    anova_results = anova_lm(ols(formula, frame).fit())


    result = {}
    import json
    result['1'] = anova_results.to_json()

    print "anova_results", anova_results
    sc.stop()
    return json.dumps(result, ensure_ascii=False)


def get_mult_group(list, key):
    formula_list = []

    frame = DataFrame()

    return


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