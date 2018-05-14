import Tsparkcore
from pyspark.sql import SQLContext,Row


COL_CROWD_LIST = "COL_CROWD_LIST"
COL_EXAM_JSON = "EXAM_JSON"
HBASE_STANDARD = "cs_talent:base_standard"
CROWD_ID = None
def calc_python_spark(crowd_id):

    sc = Tsparkcore.createSparkContext("crowd")
    sqlctx = SQLContext(sc)

    hbase_rdd = Tsparkcore.createSparkRDD(sc, HBASE_STANDARD)
    print crowd_id
    CROWD_ID = crowd_id
    hbase_rdd = hbase_rdd.mapPartitions(filter_cols_new)
    # hbase_rdd = hbase_rdd.filter(lambda x:filter_cols( x[COL_CROWD_LIST], crowd_id))

    output = hbase_rdd.collect()
    for k in output:
      print  k
      break


    sc.stop()
    #

    # hbase_rdd = Tsparkcore.calHbaseRDD(hbase_rdd, COL_CROWD_LIST, crowdID=crowd_id)
    #
    #
    # output_hbase_rdd = hbase_rdd.map(lambda kv: Tsparkcore.map_crowd_Row(kv[1]))
    #
    # schemaCrowd = sqlctx.createDataFrame(output_hbase_rdd, samplingRatio=0.2)
    # schemaCrowd.registerTempTable("tableCrowd")
    # crowdDataFrame = sqlctx.sql("SELECT codeID, organID, examID FROM tableCrowd");

def flat_map_cols(v):

    return v


def filter_cols(colKey, crowdId):
    if crowdId in colKey:
        return True
    else:
        return False

def filter_cols_new(rdds):
    new_rdds = []
    for index, rdd in enumerate(rdds):
        values = rdd[1].split("\n")
        new_value = {}
        i=0
        keys = rdd[0].split(":")
        if len(keys) >= 6 :
            new_value["UUID"] = keys[5]
            new_value["EXAMID"] = keys[6]
            new_value["EXAMTIME"] = keys[2]
        for x in values:
            vals = eval(x)
            new_value[vals["qualifier"]] =vals["value"]
            i = i+1

    new_rdds.append(new_value)
    return new_rdds







if __name__ == "__main__":
    calc_python_spark("7fa5f7e6580140e59e071b3cc16a48e4")

