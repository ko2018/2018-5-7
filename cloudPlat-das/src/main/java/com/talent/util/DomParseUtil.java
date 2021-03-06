package com.talent.util;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.mode.conf.HTableConfMode;

public class DomParseUtil {
    private static Logger log = LoggerFactory.getLogger(DomParseUtil.class.getName());

    public static String getHostAndPort() {
        Document doc = null;
        try {
            doc = ZKUtils.readDocZkConfig();
        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        }
        Element source = doc.select("Redis").first();
        String cluster_nodes = source.select("[name=cluster_nodes]").val();
        return cluster_nodes;
    }

    public static Map<String, HTableConfMode> getTableInfo() {

        Document doc = null;
        try {
            doc = ZKUtils.readDocHBaseConfig();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        Map<String, HTableConfMode> tableInfoMap = new HashMap<String, HTableConfMode>();
        try {
            for (int i = 1; i <= 1; i++) {

                Element source = doc.select("table" + i).first();
                String tableName = source.select("[name=hbase_table]").val();
                String family = source.select("[name=family]").val();
                String namespace = source.select("[name=namespace]").val();
                String split = source.select("[name=split]").val();
                String ttl = source.select("[name=ttl]").val();
                HTableConfMode tableInfo = new HTableConfMode();
                tableInfo.setTableName(tableName);
                tableInfo.setFamily(new String[] { family });
                tableInfo.setNameSpace(namespace);
                tableInfo.setSplit(Integer.parseInt(split));
                tableInfo.setTtl(Integer.parseInt(ttl));
                tableInfoMap.put(tableInfo.getTableName(), tableInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

        }
        return tableInfoMap;
    }

}