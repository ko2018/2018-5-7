package com.talent.core.table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.talent.common.SQLCrowd;
import com.talent.common.SQLGroup;
import com.talent.common.SQLStaticsYear;
import com.talent.core.global.Constant;
import com.talent.mode.conf.DBConnMode;
import com.talent.mode.conf.STableConfMode;

/** db ��ʼ�� */
public class DBTable {
    private static Logger logger = LoggerFactory.getLogger(DBTable.class.getName());

    private static Map<String, STableConfMode> tableInfoMap = null;

    private static DBConnMode dbConnMode = new DBConnMode();

    public static ComboPooledDataSource comboPool = null;

    public static void loadingConf() throws Exception {

        if (tableInfoMap == null) {
            tableInfoMap = new HashMap<String, STableConfMode>();

            STableConfMode mode = new STableConfMode(SQLCrowd.TABLE_CROWD, Constant.SQL_SELECT_FORM,
                    SQLCrowd.select_sql_ok);
            tableInfoMap.put(mode.getKey(), mode);
            mode = new STableConfMode(SQLCrowd.TABLE_FEATURE, Constant.SQL_SELECT_FORM, SQLCrowd.select_sql_feature);
            tableInfoMap.put(mode.getKey(), mode);

            mode = new STableConfMode(SQLCrowd.TABLE_CROWD, Constant.SQL_UPDATE, SQLCrowd.update_sql_crowd);
            tableInfoMap.put(mode.getKey(), mode);

            mode = new STableConfMode(SQLStaticsYear.TABLE_YEAR, Constant.SQL_REPLACE_INTO,
                    SQLStaticsYear.replace_year_table);
            tableInfoMap.put(mode.getKey(), mode);

            mode = new STableConfMode(SQLGroup.TABLE_GROUP, Constant.SQL_SELECT_FORM, SQLGroup.select_sql_group);
            tableInfoMap.put(mode.getKey(), mode);

            mode = new STableConfMode(SQLGroup.TABLE_GROUP_FEATURE, Constant.SQL_SELECT_FORM,
                    SQLGroup.select_sql_group_features);
            tableInfoMap.put(mode.getKey(), mode);

            mode = new STableConfMode(SQLGroup.TABLE_GROUP, Constant.SQL_UPDATE, SQLGroup.update_sql_group_status);
            tableInfoMap.put(mode.getKey(), mode);

            for (String key : tableInfoMap.keySet()) {
                System.out.println("key  == >" + key);
            }
        }

        // dbConnMode.loadingDB();
        loadingDBPool();
    }

    private static void loadingDBPool() throws Exception {
        if (comboPool != null)
            return;
        comboPool = new ComboPooledDataSource();
        comboPool.setDriverClass(dbConnMode.driverClass);
        comboPool.setJdbcUrl(dbConnMode.jdbcUrl);
        comboPool.setUser(dbConnMode.user);
        comboPool.setPassword(dbConnMode.password);
        comboPool.setMinPoolSize(Integer.parseInt(dbConnMode.minPoolSize));
        comboPool.setMaxPoolSize(Integer.parseInt(dbConnMode.maxPoolSize));
        comboPool.setMaxIdleTime(Integer.parseInt(dbConnMode.maxIdleTime));
        comboPool.setInitialPoolSize(Integer.parseInt(dbConnMode.initialPoolSize));
        comboPool.setCheckoutTimeout(Integer.parseInt(dbConnMode.checkoutTimeout));
        comboPool.setAcquireIncrement(Integer.parseInt(dbConnMode.acquireIncrement));
        comboPool.setIdleConnectionTestPeriod(Integer.parseInt(dbConnMode.idleConnectionTextPeriod));
        comboPool.setNumHelperThreads(Integer.parseInt(dbConnMode.numHelperThreads));
        comboPool.setUnreturnedConnectionTimeout(Integer.parseInt(dbConnMode.unreturnedConnectionTimeout));
        comboPool.setMaxStatements(Integer.parseInt(dbConnMode.maxStatements));
        comboPool.setMaxStatementsPerConnection(Integer.parseInt(dbConnMode.maxStatementsPerConnection));
        comboPool.setBreakAfterAcquireFailure(false);
        comboPool.setAcquireRetryAttempts(10);
        comboPool.setAcquireRetryDelay(1000);
        comboPool.setAutoCommitOnClose(false);
        comboPool.setAutomaticTestTable("Test");
        comboPool.setMaxIdleTime(60);
        comboPool.setIdleConnectionTestPeriod(18000);
    }

    public static void closeDB(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            logger.error("e ={}", e.getMessage());
        }

        try {
            if (ps != null)
                ps.close();

        } catch (Exception e) {
            logger.error("e ={}", e.getMessage());
        }

        try {
            if (conn != null)
                conn.close();

        } catch (Exception e) {
            logger.error("e ={}", e.getMessage());
        }

    }

    public static void closePoolDB() {
        if (comboPool != null)
            comboPool.close();
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            if (comboPool == null)
                loadingConf();
            connection = comboPool.getConnection();
        } catch (Exception e) {
            logger.error("Get connection from pool error.", e);
        }

        return connection;
    }

    public static STableConfMode getTableMode(String tableName, String tableType) {
        STableConfMode scM = tableInfoMap.get(tableName + "-" + tableType);
        if (scM == null) {
            logger.error("{} is not exist", tableName + "-" + tableType);
            return null;
        }

        return scM;
    }

}
