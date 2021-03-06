package com.talent.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.common.HBConstant;
import com.talent.core.HBaseContextC;
import com.talent.mode.parm.HBTableParm;

import scala.Tuple2;

public class HBaseUtil {

    private static Logger logger = LoggerFactory.getLogger(HBaseUtil.class.getName());

    public static void creatTable(String tableName, String[] family) throws Exception {
        // parm check
        if (tableName == null || family == null) {
            throw new NullPointerException();
        }
        Connection conn = HBaseContextC.getIns().getConn();
        Admin admin = conn.getAdmin();
        TableName tName = TableName.valueOf(tableName);

        HTableDescriptor desc = new HTableDescriptor(tName);
        for (int i = 0; i < family.length; i++) {
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(family[i]);
            columnDescriptor.setMaxVersions(1);
            desc.addFamily(columnDescriptor);
        }
        try {
            if (admin.tableExists(tName)) {
                logger.warn("table：" + tableName + " already exist!");
            } else {
                admin.createTable(desc);
                logger.warn("success is ：" + tableName);
            }
        } catch (IOException e) {
            logger.error("creatTable {}", e.getMessage());
        } finally {
            if (admin != null)
                admin.close();
            if (conn != null)
                conn.close();
        }
    }

    public static void creatTable_preSplit(String nameSpace, String tableName, String[] family, byte[][] splitKey,
            int ttlTime) throws Exception {
        if (tableName == null || family == null) {// 进行参数检查
            throw new NullPointerException();
        }
        Admin admin = null;
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();
            admin = conn.getAdmin();
            NamespaceDescriptor[] nameDesc = admin.listNamespaceDescriptors();
            String nameSpaceItem = null;
            boolean isExist = false;
            for (NamespaceDescriptor item : nameDesc) {
                nameSpaceItem = item.getName();
                if (nameSpaceItem.equals(nameSpace)) {
                    isExist = true;
                    logger.info("namespace：" + nameSpace + "is already exist");
                    break;
                }
            }
            if (!isExist) {// 如果命名空间不存在，则创建命名空间
                logger.info("create namespace：" + nameSpace);
                NamespaceDescriptor descriptor = NamespaceDescriptor.create(nameSpace).build();
                admin.createNamespace(NamespaceDescriptor.create(descriptor).build());
            }
            HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(nameSpace + ":" + tableName));
            for (int i = 0; i < family.length; i++) {
                HColumnDescriptor columnDescriptor = new HColumnDescriptor(family[i]);
                columnDescriptor.setMaxVersions(1);
                columnDescriptor.setTimeToLive(ttlTime);
                desc.addFamily(columnDescriptor);
            }
            TableName tName = TableName.valueOf(nameSpace + ":" + tableName);
            if (admin.tableExists(tName)) {
                logger.warn("create：" + tableName + "is exist");
            } else {
                admin.createTable(desc, splitKey);
                logger.info("create：" + tableName);
            }
        } catch (Exception e) {
            logger.error("creatTable_preSplit {}", e.getMessage());
        } finally {
            if (admin != null)
                admin.close();
            if (conn != null)
                conn.close();
        }
    }

    public static void creatTable_preSplit(String tableName, String[] family, byte[][] splitKey, int ttlTime)
            throws Exception {
        if (tableName == null || family == null) {// 进行参数检查
            throw new NullPointerException();
        }
        Admin admin = null;
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();
            admin = conn.getAdmin();
            HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
            for (int i = 0; i < family.length; i++) {
                HColumnDescriptor columnDescriptor = new HColumnDescriptor(family[i]);
                columnDescriptor.setMaxVersions(1);
                columnDescriptor.setTimeToLive(ttlTime);
                desc.addFamily(columnDescriptor);
            }
            TableName tName = TableName.valueOf(tableName);
            if (admin.tableExists(tName)) {
                logger.warn(tableName + "is exist");
            } else {
                admin.createTable(desc, splitKey);
                logger.info("success：" + tableName);
            }
        } catch (Exception e) {
            logger.error("creatTable_preSplit {}", e.getMessage());
        } finally {
            admin.close();
            conn.close();
        }
    }

    public static void clearNameSpace() {
        Admin admin = null;
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();
            admin = conn.getAdmin();
            NamespaceDescriptor[] nameDesc = admin.listNamespaceDescriptors();

            for (NamespaceDescriptor item : nameDesc) {
                if (item.getName().contains("default"))
                    continue;
                admin.deleteNamespace(item.getName());
            }

        } catch (Exception e) {
            logger.error("clearNameSpace {}", e.getMessage());
        } finally {
            try {
                admin.close();
            } catch (IOException e) {
                logger.error("clearNameSpace {}", e.getMessage());
            }
        }
    }

    public static boolean putBatch(String tableName, List<HBTableParm> recordList) {
        Table table = null;
        boolean isSuccess = true;
        List<Put> puts = new ArrayList<Put>();
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();
            TableName tName = TableName.valueOf(tableName);
            table = conn.getTable(tName);
            for (HBTableParm record : recordList) {
                puts.add(record.getPut());
            }
            table.put(puts);

        } catch (IOException e) {
            isSuccess = false;
            logger.error("{}", e);
        } finally {
            try {
                puts.clear();
                table.close();
                if (conn != null)
                    conn.close();
            } catch (IOException e) {
                logger.error("{}", e);
            }
        }
        return isSuccess;
    }

    public static boolean putBatchTuple2(String tableName, Tuple2<String, Integer> tuple2, String knowledgeV) {
        Table table = null;
        boolean isSuccess = true;
        List<Put> puts = new ArrayList<Put>();
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();
            TableName tName = TableName.valueOf(tableName);
            table = conn.getTable(tName);

            Put p1 = new Put(Bytes.toBytes(tuple2._1 + "_" + knowledgeV));
            p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes("DIS_RESULT_SUM"),
                    Bytes.toBytes(tuple2._2));

            table.put(p1);

        } catch (IOException e) {
            isSuccess = false;
            logger.error("{}", e);
        } finally {
            try {
                puts.clear();
                if (table != null)
                    table.close();
                if (conn != null)
                    conn.close();
            } catch (IOException e) {
                logger.error("{}", e);
            }
        }
        return isSuccess;
    }

    public static boolean puts(String tableName, List<Put> puts) {
        Table table = null;
        boolean isSuccess = true;
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();
            TableName tName = TableName.valueOf(tableName);
            table = conn.getTable(tName);
            table.batch(puts);

        } catch (Exception e) {
            isSuccess = false;
            logger.error("put={}", e);
            System.exit(0);
        } finally {
            try {
                if (table != null)
                    table.close();
                if (conn != null)
                    conn.close();
            } catch (IOException e) {
                logger.error("put={}", e);
            }
        }
        return isSuccess;
    }

    public static String getOneRowOneColData(String tableName, String rowKey, String family, String colName) {

        String res = null;
        Get g = new Get(rowKey.getBytes());// 获取一条记录
        g.setCacheBlocks(false); // 关闭Hbase的cache
        Result rs = null;
        Table table = null;
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();
            TableName tName = TableName.valueOf(tableName);
            table = conn.getTable(tName);
            rs = table.get(g);

            if (rs.containsColumn(family.getBytes(), colName.getBytes())) {
                byte[] content = null;
                content = rs.getValue(family.getBytes(), colName.getBytes());
                if (content != null) {
                    res = new String(content);
                }
            }

        } catch (Exception e) {
            logger.error("getOneRowOneColData={}", e);
        } finally {
            try {
                table.close();// 关闭table
                conn.close();
            } catch (Exception e) {
                logger.error("getOneRowOneColData={}", e);
            }
        }
        return res;
    }

    public static Map<String, String> getOneRowMutiColData(String tableName, String rowKey, String family,
            List<String> colList) {

        Map<String, String> colDataMap = null;
        Get g = new Get(rowKey.getBytes());
        g.setCacheBlocks(false);
        Result rs = null;
        Table table = null;
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();
            TableName tName = TableName.valueOf(tableName);
            table = conn.getTable(tName);
            rs = table.get(g);
            String conStr = null;
            for (String colName : colList) {
                if (rs.containsColumn(family.getBytes(), colName.getBytes())) {
                    byte[] content = null;
                    content = rs.getValue(family.getBytes(), colName.getBytes());
                    if (content != null) {
                        conStr = new String(content);
                        if (colDataMap == null) {
                            colDataMap = new HashMap<String, String>();
                        }
                        if (!colDataMap.containsKey(colName)) {
                            colDataMap.put(colName, conStr);
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("getOneRowMutiColData={}", e);
        } finally {
            try {
                if (table != null) {
                    table.close();// 关闭table
                }
                if (conn != null)
                    conn.close();
            } catch (IOException e) {
                logger.error("getOneRowMutiColData={}", e);
            }
        }
        return colDataMap;
    }

    public static void deleteOneRow(String tableName, String rowKey) {
        Table table = null;
        try {
            Connection conn = HBaseContextC.getIns().getConn();
            TableName tName = TableName.valueOf(tableName);
            table = conn.getTable(tName);
            Delete delete = new Delete(rowKey.getBytes());
            table.delete(delete);
            table.close();
        } catch (IOException e) {
            logger.error("deleteOneRow={}", e);
        } finally {
            try {
                if (table != null) {
                    table.close();// 关闭table
                }
            } catch (IOException e) {
                logger.error("deleteOneRow={}", e);
            }
        }
    }

    public static void creatTableTest(String tableName, String[] family, byte[][] splitKey, int ttlTime)
            throws Exception {
        if (tableName == null || family == null) {// 进行参数检查
            throw new NullPointerException();
        }
        Admin admin = null;
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();
            admin = conn.getAdmin();
            HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
            for (int i = 0; i < family.length; i++) {
                HColumnDescriptor columnDescriptor = new HColumnDescriptor(family[i]);
                columnDescriptor.setMaxVersions(1);
                columnDescriptor.setTimeToLive(ttlTime);
                desc.addFamily(columnDescriptor);
            }
            TableName tName = TableName.valueOf(tableName);
            if (admin.tableExists(tName)) {
                logger.warn("create：" + tableName + "is exist!");
            } else {
                admin.createTable(desc, splitKey);
                logger.info("create success：" + tableName);
            }
        } catch (IOException e) {
            logger.error("creatTableTest={}", e);
        } finally {
            if (admin != null)
                admin.close();
            if (conn != null)
                conn.close();
        }
    }

    public static String convertScanToString(Scan scan) throws IOException {
        ClientProtos.Scan proto = ProtobufUtil.toScan(scan);
        return Base64.encodeBytes(proto.toByteArray());
    }

    public static void scanFailTableDB(Scan scan, String hbaseTableName) {
        Table table;
        List<String> glist = new ArrayList<String>();
        List<String> clist = new ArrayList<String>();
        try {
            Connection conn = HBaseContextC.getIns().getConn();

            TableName tName = TableName.valueOf(hbaseTableName);
            table = conn.getTable(tName);

            ResultScanner result = table.getScanner(scan);

            for (Result r : result) {

                String rowKey = new String(r.getRow());
                System.out.println(rowKey);
                Cell cell = r.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                        Bytes.toBytes(HBConstant.COL_FAIL_CROWD));
                if (cell != null) {
                    clist.add(new String(CellUtil.cloneValue(cell)));
                }
                cell = r.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                        Bytes.toBytes(HBConstant.COL_GROUP_LIST));
                if (cell != null) {
                    glist.add(new String(CellUtil.cloneValue(cell)));
                }
                System.out.println("====================");
            }
            result.close();

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("" + e);
        }

        CrowdDB.saveFailToPeopleDB(glist);
        GroupDB.saveFailToPeopleDB(glist);
    }

    public static void scanTableDB(Scan scan, String hbaseTableName) {
        Table table;

        try {
            Connection conn = HBaseContextC.getIns().getConn();

            TableName tName = TableName.valueOf(hbaseTableName);
            table = conn.getTable(tName);

            ResultScanner result = table.getScanner(scan);

            for (Result r : result) {

                String rowKey = new String(r.getRow());
                System.out.println(rowKey);
                Cell cell = r.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                        Bytes.toBytes(HBConstant.COL_DISEASE_TRUE));
                String crowdListStr = new String(CellUtil.cloneValue(cell));
                System.out.println("COL_DISEASE_TRUE==>" + crowdListStr);
                cell = r.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                        Bytes.toBytes(HBConstant.COL_DISEASE_FALSE));
                crowdListStr = new String(CellUtil.cloneValue(cell));
                System.out.println("COL_DISEASE_FALSE=>" + crowdListStr);
                cell = r.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                        Bytes.toBytes(HBConstant.COL_CROWD_LIST));
                crowdListStr = new String(CellUtil.cloneValue(cell));
                System.out.println("COL_CROWD_LIST=>" + crowdListStr);

                System.out.println("====================");
            }
            result.close();

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("" + e);
        }
    }

    /** 更新hbase std表 */
    public static void updateToHBaseStd(Map<Row, String[]> rsMap, String crowdID) {
        Table table = null;

        List<Put> puts = new ArrayList<Put>();
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();

            TableName tName = TableName.valueOf(HBConstant.BASE_TABLE);

            table = conn.getTable(tName);

            for (Row row : rsMap.keySet()) {
                Put p = new Put(Bytes.toBytes((String) row.getAs("rowKey")));

                String crowdList = "";
                String[] crowdArrs = rsMap.get(row);
                String crowdListStr = row.getAs("crowdListStr");
                if (crowdListStr == null || StringUtils.isEmpty(crowdListStr))
                    crowdList = crowdID + "-" + crowdArrs[2] + "-" + crowdArrs[1];
                else {
                    if (crowdListStr.contains(crowdID)) {
                        String[] arrs = crowdListStr.split(",");
                        for (int i = 0; i < arrs.length; i++) {
                            if (arrs[i].contains(crowdID)) {
                                arrs[i] = crowdID + "-" + crowdArrs[2] + "-" + crowdArrs[1];
                                break;
                            }

                        }
                        crowdList = String.join(",", arrs);
                    } else {
                        crowdList = crowdListStr + "," + crowdID + "-" + crowdArrs[2] + "-" + crowdArrs[1];
                    }

                }

                p.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_CROWD_LIST),
                        Bytes.toBytes(crowdList));
                puts.add(p);
            }
            table.batch(puts);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("hbase ---->{}", e);
        } finally {
            try {
                puts.clear();
                if (table != null)
                    table.close();
                if (conn != null)
                    conn.close();

            } catch (IOException e) {
                logger.error("hbase :{}", e);
            }
        }

    }

    public static void updateToHBaseGroup(Map<Row, int[]> rsMap, String groupID) {
        Table table = null;

        List<Put> puts = new ArrayList<Put>();
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();

            TableName tName = TableName.valueOf(HBConstant.BASE_TABLE);

            table = conn.getTable(tName);

            for (Row row : rsMap.keySet()) {
                Put p = new Put(Bytes.toBytes((String) row.getAs("rowKey")));

                String groupList = "";
                if (StringUtils.isEmpty((String) row.getAs("groupListStr")))
                    groupList = groupID;
                else {
                    String groupListStr = (String) row.getAs("groupListStr");
                    if (!groupListStr.contains(groupID)) {
                        groupList = groupListStr + "," + groupID;
                    }
                }

                p.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_GROUP_LIST),
                        Bytes.toBytes(groupList));
                puts.add(p);
            }
            table.batch(puts);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("hbase ---->{}", e);
        } finally {
            try {
                puts.clear();
                if (table != null)
                    table.close();
                if (conn != null)
                    conn.close();

            } catch (IOException e) {
                logger.error("hbase :{}", e);
            }
        }

    }

    /** 入库mysql失败，存入hbase fail表 */
    public static void saveToHBaseFail(Map<Row, String[]> rsMap, String crowdID) {
        Table table = null;

        List<Put> puts = new ArrayList<Put>();
        Connection conn = null;
        try {
            conn = HBaseContextC.getIns().getConn();

            TableName tName = TableName.valueOf(HBConstant.BASE_CROWD_FAIL_TABLE);

            table = conn.getTable(tName);

            for (Row row : rsMap.keySet()) {
                Put p = new Put(Bytes.toBytes(
                        TimeUtil.getNumForID(crowdID, 201) + ":" + crowdID + ":" + (String) row.getAs("datastdId")));
                String[] arrs = rsMap.get(row);
                p.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_FAIL_CROWD),
                        Bytes.toBytes(crowdID + "," + row.getAs("userCode") + "," + row.getAs("checkCode") + ","
                                + row.getAs("examTime") + "," + row.getAs("institutionId") + ","
                                + Integer.parseInt(arrs[0]) + "," + Integer.parseInt(arrs[1]) + "," + arrs[2] + ","
                                + row.getAs("datastdId")));
                puts.add(p);
            }
            table.batch(puts);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("hbase ---->{}", e);
        } finally {
            try {
                puts.clear();
                if (table != null)
                    table.close();
                if (conn != null)
                    conn.close();

            } catch (IOException e) {
                logger.error("hbase :{}", e);
            }
        }

    }

}
