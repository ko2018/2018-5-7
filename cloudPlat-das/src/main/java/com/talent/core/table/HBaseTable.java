package com.talent.core.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.mode.conf.HTableConfMode;
import com.talent.util.DomParseUtil;
import com.talent.util.HBaseUtil;

/** hbase ��ʼ�� */
public class HBaseTable {

    public static Map<String, HTableConfMode> tableInfoMap;

    public static Map<String, Integer> tableSplitMap = new HashMap<String, Integer>();

    public static Map<String, String> tableNamespMap = new HashMap<String, String>();

    private static Logger logger = LoggerFactory.getLogger(HBaseTable.class.getName());

    public static void loadingConf() throws Exception {
        if (tableInfoMap != null)
            return;
        tableInfoMap = DomParseUtil.getTableInfo();

    }

    public static void createTables() {

        HTableConfMode tableInfo = null;
        Integer split;
        for (String key : tableInfoMap.keySet()) {
            tableInfo = tableInfoMap.get(key);
            split = tableInfo.getSplit();
            create(tableInfo.getNameSpace(), tableInfo.getTableName(), split, tableInfo.getFamily(),
                    tableInfo.getTtl());
            logger.info(key + ", " + tableInfo.getTableName());
            try {
                if (!tableNamespMap.containsKey(tableInfo.getTableName())) {
                    tableNamespMap.put(tableInfo.getTableName(), tableInfo.getNameSpace());
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (!tableSplitMap.containsKey(tableInfo.getTableName())) {
                tableSplitMap.put(tableInfo.getTableName(), tableInfo.getSplit());
            }

        }
    }

    public static void create(String nameSpace, String tableName, int split, String[] family, int ttl) {
        try {
            if (split != 1) {
                createSplit(nameSpace, tableName, split, family, ttl);
            } else {
                HBaseUtil.creatTable(nameSpace + ":" + tableName, family);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("hbase exception{}", ex);
        }
    }

    public static void createSplit(String nameSpace, String tableName, int split, String[] family, int ttl) {
        try {
            List<byte[]> regions = new ArrayList<byte[]>();
            String startKey = null;
            for (int index = 1; index < split; index++) {
                if (split <= 10) {
                    startKey = String.valueOf(index);
                } else if (split <= 100) {
                    startKey = String.format("%02d", index);
                } else if (split <= 1000) {
                    startKey = String.format("%03d", index);
                }
                regions.add(Bytes.toBytes(startKey));
            }
            HBaseUtil.creatTable_preSplit(nameSpace, tableName, family, regions.toArray(new byte[0][]), ttl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        String tableName = null;
        for (Map.Entry<String, HTableConfMode> entry : tableInfoMap.entrySet()) {
            HTableConfMode tableInfo = entry.getValue();
            tableName = entry.getKey();
            logger.info("drop table " + tableInfo.getNameSpace() + ":" + tableName);
            // HBaseUtil.dropTable(tableInfo.getNameSpace() + ":" + tableName);
        }

    }

    public static void clearNameSpace() {
        HBaseUtil.clearNameSpace();
    }

}
