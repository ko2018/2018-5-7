package hbase;import java.util.List;import com.talent.common.SQLCrowd;import com.talent.conf.GlobalConf;import com.talent.mode.BaseDataStd;import com.talent.mode.BaseStdJoinDiag;import com.talent.util.DBUtil;public class Test_statics_crowd {    public static void main(String[] args) {        GlobalConf.load(args);        try {            fromDbToHbase(SQLCrowd.count_std_join_diag_sql_ok, SQLCrowd.select_std_join_diag_sql_ok,                    BaseStdJoinDiag.class);        } catch (Exception e) {            e.printStackTrace();        }    }    private static void fromDbToHbase(String sql_count, String sql, Class<?> cls) throws Exception {        String count1 = DBUtil.countIntoMode2(sql_count, "2017-3-10", "2017-3-10");        int countInt1 = Integer.parseInt(count1);        System.out.println("countInt1 === > " + countInt1);        for (int i = 0; i < countInt1;) {            List<BaseDataStd> list = DBUtil.selectIntoModeCustom(i, sql, cls, "2017-3-10", "2017-3-10");            DBUtil.Print(list);            System.out.println("i  ==>" + i + ", end!!!");            i = i + 5000;        }    }}