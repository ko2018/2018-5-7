from pyspark import SparkContext, SparkConf


conf1 = SparkConf().setMaster("local").setAppName("SparkSQL")
sc = SparkContext(conf=conf1)

baseconf = {
"hbase.mapreduce.inputtable": "cs_talent:statics_crowd_details",
"hbase.zookeeper.quorum": "192.168.6.89,192.168.6.90,192.168.6.88"
}

keyConv = "org.apache.spark.examples.pythonconverters.ImmutableBytesWritableToStringConverter"
valueConv = "org.apache.spark.examples.pythonconverters.HBaseResultToStringConverter"



hbase_rdd = sc.newAPIHadoopRDD(
        "org.apache.hadoop.hbase.mapreduce.TableInputFormat",
        "org.apache.hadoop.hbase.io.ImmutableBytesWritable",
        "org.apache.hadoop.hbase.client.Result",
        keyConverter=keyConv,
        valueConverter=valueConv,
        conf=baseconf )

hbase_rdd = hbase_rdd.filter(lambda x:x[0].split(":")[1].__eq__("C11db4a8c5ff548e29608e38961098755"))
hbase_rdd = hbase_rdd.filter(lambda x:x[0].split(":")[4].__eq__("A0043"))

dataconf = {
"hbase.mapreduce.inputtable": "cs_talent:base_standard",
"hbase.zookeeper.quorum": "192.168.6.89,192.168.6.90,192.168.6.88"
}

data_rdd = sc.newAPIHadoopRDD(
        "org.apache.hadoop.hbase.mapreduce.TableInputFormat",
        "org.apache.hadoop.hbase.io.ImmutableBytesWritable",
        "org.apache.hadoop.hbase.client.Result",
        keyConverter=keyConv,
        valueConverter=valueConv,
        conf=baseconf )

data_rdd = data_rdd.filter(lambda x:x[0].split(":")[1].__eq__("C11db4a8c5ff548e29608e38961098755"));
data_rdd = data_rdd.filter(lambda x:x[0].split(":")[4].__eq__("A0043"))

output = hbase_rdd.collect();

for (k, v) in output:
    print (k,v )
    
output = data_rdd.collect();


for (k, v) in output:
    print (k,v )

sc.stop()

