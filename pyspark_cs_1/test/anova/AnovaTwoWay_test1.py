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
    D = ask_parm['D_F']
    F = ask_parm['F_F']
    F_Group = ask_parm['F_Group']
    option_dict, option_list = utils.option_dict_list(F)

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

    rsDataFrame = crowdDataFrame.join(stdDataFrame, cond, "inner").select(stdDataFrame.data_json)
    rsDataFrameGArrs = {}

    for rowKey in rsDataFrame.collect():
        for colKey in option_list:
            rsDataFrameGArrs[colKey] = []
            if (rowKey.has_key(colKey)):
                rsDataFrameGArrs[colKey].append(rowKey[colKey])
            else:
                rsDataFrameGArrs[colKey].append(None)
    frame_Arrs = []
    for colKey in option_list :
        group_list = F_Group[colKey]
        rsDataFrameG = get_df_group(colKey, group_list, rsDataFrameGArrs)

    formula_list = []
    # rsDataFrameG1 = get_df_group(group_all["390"][0], rsDataFrame)
    # rsDataFrameG2 = get_df_group(group_all["390"][1], rsDataFrame)
    # rsDataFrameG3 = get_df_group(group_all["390"][2], rsDataFrame)
    frame = DataFrame()
    # G1 = rsDataFrameG1.select(rsDataFrameG1.data_json["390"]).toPandas()
    # G2 = rsDataFrameG2.select(rsDataFrameG2.data_json["390"]).toPandas()
    # G3 = rsDataFrameG3.select(rsDataFrameG2.data_json["390"]).toPandas()


    frame['D'] = G1

    i=0
    for key in  option_list:
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
    return None


def get_mult_group(list, key):
    formula_list = []

    frame = DataFrame()

    return


def get_df_group(key, groups, rsDataFrame, k):

    for group in groups :
        key = group['id']
        val = group['parmVal']
        type = group['parmType']

    rsDataFrameN = None
    if type is 1:
        # rsDataFrameN = rsDataFrame.filter(
        #     rsDataFrame["data_json"][key].__gt__("25.0") & rsDataFrame["data_json"][key].__lt__("30.0")).select(rsDataFrame.codeID, rsDataFrame.organID,
        #                                                                                                         rsDataFrame.examID, rsDataFrame.examTime,
        #                                                                                     rsDataFrame["data_json"])

        rsDataFrame = rsDataFrame.where(rsDataFrame["data_json"][key].__gt__("25.0") & rsDataFrame["data_json"][key].__lt__("30.0")).select(rsDataFrame["data_json"], (rsDataFrame.x1 + k).alias('x1'))
        print "rsDataFrame"
        # rsDataFrame.show()
        return rsDataFrame

    elif type is 2:
        rsDataFrameN = rsDataFrame.filter(
            (rsDataFrame.data_json[key] == 21) | (rsDataFrame.data_json[key] == 23)).select(rsDataFrame.codeID, rsDataFrame.organID,
                                                                                                                rsDataFrame.examID, rsDataFrame.examTime,
                                                                                            rsDataFrame["data_json"])
        return rsDataFrameN
    else :
        return rsDataFrameN