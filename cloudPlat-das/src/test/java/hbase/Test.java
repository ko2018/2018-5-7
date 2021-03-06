package hbase;

import java.util.List;

import com.talent.common.SQLCrowd;
import com.talent.conf.GlobalConf;
import com.talent.core.table.HBaseTable;
import com.talent.mode.BaseDataStd;
import com.talent.mode.BaseStdJoinDiagItem;
import com.talent.util.DBUtil;

public class Test {

    private static String select_old = SQLCrowd.select_std_join_diag_item_sql_ok.replace("a.crowds", "NULL");

    public static void main(String[] args) throws Exception {
        GlobalConf.load(args);
        HBaseTable.loadingConf();
        // HBaseTable.createTables();

        // updateStdFromDBToHBase(SQLCrowd.count_std_join_diag_sql_ok,
        // SQLCrowd.select_std_join_diag_sql_ok,
        // BaseStdJoinDiag.class);

        updateStdFromDBToHBase(SQLCrowd.count_std_join_diag_item_sql_ok, SQLCrowd.select_std_join_diag_item_sql_ok,
                BaseStdJoinDiagItem.class);

    }

    private static void updateStdFromDBToHBase(String sql_count, String sql, Class<?> cls) {
        try {

            String dateStr = 2018 + "-" + 4 + "-" + 5;
            // String count = DBUtil.countIntoMode2(sql_count, dateStr,
            // dateStr);
            // int countInt = Integer.parseInt(count);
            int countInt = 240000;
            System.out.println("countInt1 === > " + countInt);
            for (int i = 2000; i < countInt;) {

                List<BaseDataStd> list = DBUtil.selectIntoModeCustom(i, sql, cls, dateStr, dateStr);
                DBUtil.Print(list);
                System.out.println("i  ==>" + i + ", end!!!");
                i = i + 1000;

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
}
