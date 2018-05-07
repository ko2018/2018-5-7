from pyspark import SparkContext, SparkConf
import json
import re
from pyspark.sql import SQLContext, Row

keyConv = "org.apache.spark.examples.pythonconverters.ImmutableBytesWritableToStringConverter"
valueConv = "org.apache.spark.examples.pythonconverters.HBaseResultToStringConverter"
regex = re.compile(r'\\(?![/u"])')

def createSparkRDD(sc, table):
    baseconf = {
    "hbase.mapreduce.inputtable": table,
    "hbase.zookeeper.quorum": "192.168.6.89,192.168.6.90,192.168.6.88",
    }

    hbase_rdd = sc.newAPIHadoopRDD(
        "org.apache.hadoop.hbase.mapreduce.TableInputFormat",
        "org.apache.hadoop.hbase.io.ImmutableBytesWritable",
        "org.apache.hadoop.hbase.client.Result",
        keyConverter=keyConv,
        valueConverter=valueConv,
        conf=baseconf)
    return hbase_rdd

def createSparkContext(name):
    conf = SparkConf().setMaster("local").setAppName(name)
    sc = SparkContext(conf=conf)
    return sc


def filter_cols(colKey, equalVal):
    cols = colKey["qualifier"];
    return cols.__eq__(equalVal)


def filter_rows(rowkey, crowdID=None):
    if crowdID:
        crowdID = "C" + crowdID;
        if crowdID not in rowkey:
            return None
  
    return rowkey

def calHbaseRDD(hbase_rdd, colName, crowdID=None):

    hbase_rdd = hbase_rdd.flatMapValues(lambda v: v.split("\n")).mapValues(json.loads)
    hbase_rdd = hbase_rdd.filter(lambda x:filter_cols(x[1], colName))
    hbase_rdd = hbase_rdd.filter(lambda x:filter_rows(x[0], crowdID=crowdID))
    return hbase_rdd

def map_list_crowd(colKey):
    cols = colKey['row'].split(":")
    vals = colKey['value'].split(",")
    userID = cols[4]
    organID = cols[3]
    
    examList = []
    for val in vals:
        arrs = val.split(":")
        examID = arrs[0]
        examTime = arrs[1]
        comArrs = (organID + examID + examTime + userID, 1)
        examList.append(comArrs)
    return examList

def map_crowd_Row(colKey):
    cols = colKey['row'].split(":")
    vals = colKey['value'].split(":")
    return Row(codeID=cols[4], organID=cols[3], examID=vals[0], examTime=vals[1])

import utils
def map_std_Row(colKey, group, option_list):
    cols = colKey['row'].split(":")
    vals = colKey['value']
    if vals is not None :
        fixed = regex.sub(r"\\\\", vals)
        valsDict = json.loads(fixed)
        groupType = valsDict.has_key("_"+group["parmId"])
        if groupType :
            if group['parmType'] == 1 :
                valsDict["_"+group["parmId"]]
            
        valsDict_rs = {}
        for key in option_list:
            key_rs = "_" + key
            if(valsDict.has_key(key_rs) and utils.is_number(valsDict[key_rs])) :
                valsDict_rs[key] = float(valsDict[key_rs]);
        
              
    else :
        valsDict_rs = None
        groupType = None

    return Row(rowKey=colKey, codeID=cols[5], organID=cols[1], examID=cols[6], examTime=cols[2].replace("D", ""), 
                data_json=valsDict_rs,
               group_Type=groupType)


def map_std_Row_group(group, valsDict):
    key = group["groupDict"]
    if valsDict.has_key(key):
        vals = int(valsDict[key])
        return checkSatisfyValue(vals, group)
    return False
        

    

def map_list_std(colKey):
    cols = colKey['row'].split(":")
    vals = colKey['value']
    userID = cols[5]
    organID = cols[1]
    examTime = cols[2].replace("D", "")
    examID = cols[6]
    fixed = regex.sub(r"\\\\", vals)
    valsDict = json.loads(fixed)
    return [(organID + examID + examTime + userID, valsDict)]

def filter_cols_join(colKey, valsArrs):
    colDataArrs = colKey[1]
    if colDataArrs is None :
        return False
    for key in valsArrs:
        if key  in  colDataArrs:
            return True
    return False

  
def filter_cols_join_False(colKey, valsArrs, group):
    colDataArrs = colKey[1]
    if colDataArrs is None:
        return False
    flag = None
    fKey = "_"+group['groupDict']
    if fKey in colDataArrs:
        val = colDataArrs[fKey]
        if checkSatisfyValue(val, group):
            flag = 1
    if flag is None:
        return False
    for key in valsArrs:
        if key not in  colDataArrs:
            return False

    return True

def filter_cols_join_True(colKey, valsArrs, group):
    colDataArrs = colKey[1]
    if colDataArrs is None:
        return False
    flag = None
    fKey = "_"+group['groupDict']
    if fKey in colDataArrs:
        val = colDataArrs[fKey]
        if checkSatisfyValue(val, group):
            flag = 1
    if flag is None:
        return False
    for key in valsArrs:
        if key in  colDataArrs:
            return True

    return False


def checkSatisfyValue(val, group):
    stype = int(group['groupDictType'])
    if stype is 2 :
        arrs = group['groupParm'].split(",")
        for tmp in arrs :
            if val == tmp :
                return True
        return False
    elif  stype is 1 :
        arrs = group['groupParm'].split("-")
        valInt = int(val)
        upInt = int(arrs[1])
        lowInt = int(arrs[0])
        if valInt >= lowInt and  valInt < upInt :
            return True
        return False
    else :  
        return False
    
def map_list_crowd_std_join(rowkey, colKey, filterList):
    colDataArrs = colKey[1]
    dict_arr = {}
    for val in filterList:
        dict_arr[val] = colDataArrs[val]
    return [(rowkey, dict_arr)]

def print_Group_DB(row):
    groupID = row[0]
    groupName = row[1]
    groupDict = row[2]
    groupDictType = row[3]
    groupParm = row[4]
    return  {'groupID':groupID, 'groupName':groupName, 'groupDict':groupDict, 'groupDictType':groupDictType, 'groupParm':groupParm}
