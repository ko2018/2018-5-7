package com.talent.core;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** hbase 初始化容器 */
public class HBaseContextC {

    private static HBaseContextC mIns = null;

    protected static Logger logger = LoggerFactory.getLogger("HBaseContextC");

    /** hbase master 节点 */
    private String HBASE_MASTER = "192.168.6.89";

    /** hbase zookeeper节点 */
    private String HBASE_ZOOKEEPER_QUORUM = "192.168.6.88,192.168.6.89,192.168.6.90";

    /** hbase port 端口 */
    private String HBASE_ZOOKEEPER_PORT = "2181";

    public static HBaseContextC getIns() {
        if (mIns == null) {
            mIns = new HBaseContextC();

        }
        return mIns;
    }

    public Connection getConn() {
        try {
            return ConnectionFactory.createConnection(getConf());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public Configuration getConf() {

        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", HBASE_ZOOKEEPER_QUORUM);
        conf.set("hbase.zookeeper.property.clientPort", HBASE_ZOOKEEPER_PORT);
        conf.set("hbase.master", HBASE_MASTER);
        return conf;
    }

    public static void main(String[] args) {
        HBaseContextC.getIns();
    }

}
