package com.talent.core.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.mode.conf.HTableConfMode;
import com.talent.mode.conf.STableConfMode;

/** ���ڷϵ� */
public class HiveSQL {

    private static Logger logger = LoggerFactory.getLogger(HiveSQL.class.getName());

    public static String HIVE_URL = "jdbc:hive2://192.168.0.231:10000/default";

    public static String HIVE_DIRVER = "org.apache.hive.jdbc.HiveDriver";

    public static String HIVE_USRNAME = "root";

    public static String HIVE_PASSWORD = "123456";

    public static Map<String, STableConfMode> map;

    public static Map<String, String> hiveMap;

    public static void initConf() throws Exception {
        // map = DomParseUtil.getTable();
        /*
         * hiveMap = FileUtil.readTxtList("conf/hive-conf.properties");
         * if(hiveMap.containsKey("HIVE_URL")) { HIVE_URL =
         * hiveMap.get("HIVE_URL"); } if(hiveMap.containsKey("HIVE_DIRVER")) {
         * HIVE_DIRVER = hiveMap.get("HIVE_DIRVER"); }
         * if(hiveMap.containsKey("HIVE_USRNAME")) { HIVE_USRNAME =
         * hiveMap.get("HIVE_USRNAME"); }
         * if(hiveMap.containsKey("HIVE_PASSWORD")) { HIVE_PASSWORD =
         * hiveMap.get("HIVE_PASSWORD"); }
         */
    }

    public static void createHTables(Map<String, HTableConfMode> tableInfoMap) {

    }

    private static Connection getConn() throws Exception {
        Class.forName(HIVE_DIRVER);
        Connection conn = DriverManager.getConnection(HIVE_URL, HIVE_USRNAME, HIVE_PASSWORD);
        return conn;
    }

    private static void closeConn(ResultSet res, Statement stmt, Connection conn) {

        try {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {

            logger.error("closeConn:" + e.getMessage());
        }
    }

    public static List<Map<String, String>> getSelectTableData(String tableName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        List<Map<String, String>> list = null;
        try {
            conn = getConn();
            list = new ArrayList<Map<String, String>>();

            String sql = map.get("SELECT_ALL").getTableSQL("htestname");
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                Map<String, String> map1 = new HashMap<String, String>();

                System.out.println("id: " + res.getString(1) + "\t type: " + res.getString(2));
            }

        } catch (Exception e) {

            logger.error("createHBaseHiveSQL:" + e.getMessage());
        } finally {

            closeConn(res, stmt, conn);
        }

        return list;
    }

    public static List<Map<String, String>> getSelectTableDataGroup(String tableName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        List<Map<String, String>> list = null;
        try {
            conn = getConn();
            list = new ArrayList<Map<String, String>>();

            String sql = map.get("SELECT_GROUP").getTableSQL("htestname");
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                Map<String, String> map1 = new HashMap<String, String>();

                System.out.println("id: " + res.getString(1) + "\t type: " + res.getString(2));
            }

        } catch (Exception e) {

            logger.error("createHBaseHiveSQL:" + e.getMessage());
        } finally {

            closeConn(res, stmt, conn);
        }

        return list;
    }

    public static List<Map<String, String>> getSelectTableDataOrder(String tableName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        List<Map<String, String>> list = null;
        try {
            conn = getConn();
            list = new ArrayList<Map<String, String>>();

            String sql = map.get("SELECT_ORDER").getTableSQL("htestname");
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                Map<String, String> map1 = new HashMap<String, String>();
                System.out.println("id: " + res.getString(1) + "\t type: " + res.getString(2));
            }

        } catch (Exception e) {
            logger.error("createHBaseHiveSQL:" + e.getMessage());
        } finally {

            closeConn(res, stmt, conn);
        }

        return list;
    }

    // info map<STRING,STRING>
    // ":key,info:"
    public static boolean createHBaseHiveSQL(String tableName, String colsType, String colsName) {

        Connection conn = null;
        Statement stmt = null;
        boolean flag = false;

        try {
            conn = getConn();
            String sql = map.get("CREATE").getTableSQL("H" + tableName, "rowkey string," + colsType, colsName,
                    tableName);
            stmt = conn.createStatement();
            stmt.execute(sql);
            flag = true;
        } catch (Exception e) {

            flag = false;
            logger.error("createHBaseHiveSQL:" + e.getMessage());
        } finally {

            closeConn(null, stmt, conn);

        }
        return flag;

    }

    public static boolean dropHBaseHiveSQL(String tableName) {
        Connection conn = null;
        Statement stmt = null;
        boolean flag = false;
        try {
            conn = getConn();

            String sql = map.get("DROP").getTableSQL("H" + tableName);
            stmt = conn.createStatement();
            stmt.execute(sql);
            flag = true;
        } catch (Exception e) {

            flag = false;
            logger.error("dropHBaseHiveSQL:" + e.getMessage());
        } finally {

            closeConn(null, stmt, conn);
        }
        return flag;
    }

    public static void main(String[] args) {

        /*
         * boolean flag = HiveSQL.createHBaseHiveSQL("testName"); flag =
         * HiveSQL.dropHBaseHiveSQL("testName"); System.out.println(flag);
         */
    }

    public int getHBaseTableSum(String tableName, String param) {

        return 0;
    }

    public int getHBaseTableSum(String tableName) {

        return 0;
    }

}
