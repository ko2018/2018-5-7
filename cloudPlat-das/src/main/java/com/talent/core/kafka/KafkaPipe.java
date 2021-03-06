package com.talent.core.kafka;

/** kafka 常量 */
public class KafkaPipe {

    /** topic */
    public static String TOPIC_SOURCE = "dataStd_es_d";

    /** zookeeper 集群 */
    public static String KAFKA_SERVER_URL = "host89:9092,host88:9092,host90:9092";

    /** kafka 端口 */
    public static int KAFKA_SERVER_PORT = 9092;

    /** 字符 */
    public static int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;

    /** 链接超时 */
    public static int CONNECTION_TIMEOUT = 100000;

}
