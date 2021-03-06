package com.talent.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.common.HBConstant;
import com.talent.core.global.Constant;
import com.talent.core.table.DBTable;
import com.talent.job.serial.BaseDataN;
import com.talent.mode.BaseDataStd;
import com.talent.mode.ITable;
import com.talent.mode.conf.STableConfMode;
import com.talent.mode.parm.TableParm;

/**
 * @author zhangqian
 * @time 2017年12月7日 下午5:44:54
 * @version 1.0v
 */
public class DBUtil {
    private static Logger logger = LoggerFactory.getLogger("DBUtil");

    /** 更新table */
    public static void replaceIntoTable(String tableName, List<? extends TableParm> tableParms) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {

            conn = DBTable.getConnection();
            conn.setAutoCommit(false);

            STableConfMode tableModel = DBTable.getTableMode(tableName, Constant.SQL_REPLACE_INTO);
            String tableSql = tableModel.tableFormat;
            stat = conn.prepareStatement(tableSql);
            for (TableParm tParm : tableParms) {
                tParm.replaceToDB(stat);
                stat.addBatch();
            }

            stat.executeBatch();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("" + e);
        } finally {
            DBTable.closeDB(conn, stat, null);
        }
    }

    /** 更新table */
    public static void replaceIntoBaseDataTableN(String tableName, List<BaseDataN> tableParms, String crowdID) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DBTable.getConnection();

            conn.setAutoCommit(false);

            STableConfMode tableModel = DBTable.getTableMode(tableName, Constant.SQL_REPLACE_INTO);

            String tableSql = tableModel.tableFormat;
            stat = conn.prepareStatement(tableSql);
            for (BaseDataN tParm : tableParms) {
                tParm.replaceToDB(stat, crowdID);

            }

            stat.executeBatch();
            conn.commit();
            DBTable.closeDB(conn, stat, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("" + e);
        }
    }

    public static void updateIntoTable(String tableName, TableParm tableParm) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DBTable.getConnection();

            conn.setAutoCommit(false);

            STableConfMode tableModel = DBTable.getTableMode(tableName, Constant.SQL_UPDATE);
            String tableSql = tableModel.tableFormat;
            stat = conn.prepareStatement(tableSql);
            tableParm.replaceToDB(stat);

            stat.executeUpdate();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("" + e);
        } finally {
            DBTable.closeDB(conn, stat, null);
        }
    }

    public static void updateIntoTableSum(String sql, String crowdID, String sum) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DBTable.getConnection();

            conn.setAutoCommit(false);

            stat = conn.prepareStatement(sql);
            int i = 1;
            stat.setString(i++, sum);
            stat.setTimestamp(i++, new Timestamp(new Date().getTime()));
            stat.setString(i++, crowdID);
            stat.executeUpdate();
            conn.commit();
            DBTable.closeDB(conn, stat, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("" + e);
        }
    }

    public static List<BaseDataStd> selectIntoMode2(int limit, String sql1) {
        Connection conn = null;
        Statement stat = null;
        try {
            conn = DBTable.getConnection();

            conn.setAutoCommit(false);

            String sql = sql1 + " LIMIT " + limit + ", " + 5000;

            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            List<BaseDataStd> list = new ArrayList<BaseDataStd>();

            while (rs.next()) {

                BaseDataStd e = new BaseDataStd();
                e.setResultSet(rs);
                list.add(e);
                if (list.size() > 1000) {
                    Print2(list);
                    list.clear();
                }
            }

            conn.commit();

            return list;
        } catch (

        Exception e) {
            e.printStackTrace();
            logger.error("" + e);
        } finally {
            DBTable.closeDB(conn, stat, null);
        }

        return null;
    }

    public static String countIntoMode2(String sql, String... parms) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DBTable.getConnection();
            conn.setAutoCommit(false);

            stat = conn.prepareStatement(sql);
            if (parms != null) {
                for (int i = 0; i < parms.length; i++) {
                    stat.setString(i + 1, parms[i]);
                }
            }
            ResultSet rs = stat.executeQuery();

            String count = "0";
            while (rs.next()) {
                count = (rs.getString(1));
            }

            conn.commit();
            DBTable.closeDB(conn, stat, null);
            return count;
        } catch (Exception e) {
            System.out.println(sql);
            e.printStackTrace();
            logger.error("" + e);
        }

        return "0";
    }

    public static void Print2(List<BaseDataStd> list) throws ParseException {
        List<Put> puts = new ArrayList<Put>();
        for (BaseDataStd e : list) {
            puts.add(e.getPutDis());
        }
        HBaseUtil.puts("cs_talent:disease_diagnose_ext", puts);
    }

    public static void Print(List<BaseDataStd> list) throws ParseException {
        List<Put> puts = new ArrayList<Put>();
        for (BaseDataStd e : list) {
            puts.add(e.getPut());
        }
        HBaseUtil.puts(HBConstant.BASE_TABLE, puts);
    }

    public static List<ITable> selectIntoMode(String tableName, Class<?> cls, String... parms) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DBTable.getConnection();
            conn.setAutoCommit(false);

            STableConfMode model = DBTable.getTableMode(tableName, Constant.SQL_SELECT_FORM);
            String sql = model.getTableFormat();
            stat = conn.prepareStatement(sql);
            if (parms != null)
                model.setStatement(stat, parms);

            ResultSet rs = stat.executeQuery();
            List<ITable> list = new ArrayList<ITable>();
            while (rs.next()) {
                ITable dbT = (ITable) cls.newInstance();
                dbT.setResultSet(rs);
                list.add(dbT);
            }

            conn.commit();

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("" + e);
        } finally {
            DBTable.closeDB(conn, stat, null);
        }

        return null;
    }

    public static List<BaseDataStd> selectIntoModeCustom(int limit, String sql, Class<?> cls, String... parms) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DBTable.getConnection();
            conn.setAutoCommit(false);

            sql = sql + "  LIMIT " + limit + ", " + 1000;
            stat = conn.prepareStatement(sql);
            if (parms != null) {
                for (int i = 0; i < parms.length; i++) {
                    stat.setString(i + 1, parms[i]);
                }
            }

            ResultSet rs = stat.executeQuery();
            List<BaseDataStd> list = new ArrayList<BaseDataStd>();
            while (rs.next()) {
                BaseDataStd dto = (BaseDataStd) cls.newInstance();
                dto.setResultSet(rs);
                list.add(dto);
                if (list.size() > 1000) {
                    DBUtil.Print(list);
                    list.clear();
                }

            }

            conn.commit();
            DBTable.closeDB(conn, stat, null);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("" + e);
        }

        return null;
    }

}
