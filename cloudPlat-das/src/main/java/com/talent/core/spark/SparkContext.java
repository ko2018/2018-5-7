package com.talent.core.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** spark ������ʼ�� */
public class SparkContext {

    private static Logger logger = LoggerFactory.getLogger(SparkContext.class.getName());

    public static boolean IS_DEBUG = false;

    public static JavaSparkContext createSparkContext(String appName) {
        JavaSparkContext jsc = null;
        try {
            SparkConf conf = new SparkConf().setAppName(appName);
            // IS_DEBUG = true;
            if (IS_DEBUG) {
                conf.setMaster("local");
            }
            conf.set("spark.kryo.registrator", "com.talent.job.serial.CSRegistrator");
            conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
            conf.set("spark.Kryoserializer.buffer.max", "10m");
            conf.set("spark.shuffle.service.enabled", "true");
            conf.set("spark.sql.shuffle.partitions", "1000");
            jsc = new JavaSparkContext(conf);
            jsc.setLocalProperty("spark.scheduler.pool", "false");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("" + e);
            System.exit(0);
            return null;
        }

        return jsc;
    }

}
