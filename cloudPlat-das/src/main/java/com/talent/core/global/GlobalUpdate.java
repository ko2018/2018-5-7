package com.talent.core.global;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.common.SQLDisease;
import com.talent.conf.GlobalConf;
import com.talent.core.kafka.KafkaPipe;
import com.talent.job.GlobalStaticsCalcJobY;
import com.talent.job.GlobalStaticsCrowdJob;
import com.talent.job.GlobalStaticsGroupJob;
import com.talent.job.kafka.KafkaConsumerJob;
import com.talent.mode.BaseDataStd;
import com.talent.util.DBUtil;

/** 全局 更新 */
public class GlobalUpdate {

    protected static Logger logger = LoggerFactory.getLogger("GlobalUpdate");

    /**
     * TYPE_STD_DATA kafka 数据，TYPE_CROWD：1 人群统计，TYPE_MONTH_YEAR：2 疾病统计
     * ，TYPE_GROUP：3 分组统计
     */
    private static final String TYPE_STD_DATA = "0", TYPE_CROWD = "1", TYPE_MONTH_YEAR = "2", TYPE_GROUP = "3";

    public static void update(String flag) {
        try {
            switch (flag) {
            case TYPE_STD_DATA:
                new KafkaConsumerJob(KafkaPipe.TOPIC_SOURCE).start();
                break;
            case TYPE_CROWD:
                GlobalConf.load(null);
                staticsCrowdList();
                break;
            case TYPE_MONTH_YEAR:
                GlobalConf.load(null);
                // 统计年的多种疾病与该疾病不患病
                staticsMonthYear();
                break;
            case TYPE_GROUP:
                GlobalConf.load(null);
                staticsGroupsList();
                break;
            default:
                System.out.println("flag error !!!!" + flag);
            }

        } catch (Exception e) {

            logger.error("" + e);
        }
    }

    private static void staticsGroupsList() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {

                        GlobalStaticsGroupJob.execute();

                    } catch (Exception e) {

                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                }

            }
        }).start();

    }

    private static void staticsMonthYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23); // 控制时
        calendar.set(Calendar.MINUTE, 59); // 控制分
        calendar.set(Calendar.SECOND, 0); // 控制秒

        Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.err.println("开始执行" + new Date());
                updateFromDBToHBase();
                GlobalStaticsCalcJobY.execute();
            }
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
    }

    private static void updateFromDBToHBase() {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, -4);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String more_than_time = SQLDisease.more_than_time.replace("?", year + "-" + month + "-" + day);
        try {
            calHealth(SQLDisease.sql3_count + more_than_time,
                    SQLDisease.sql3 + more_than_time + SQLDisease.group_by_sql);
            calHealth(SQLDisease.sql4_count + more_than_time, SQLDisease.sql4 + more_than_time);
        } catch (Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    private static void updateStdFromDBToHBase(String sql_count, String sql, Class<?> cls) {
        try {
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DAY_OF_MONTH, -4);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String dateStr = year + "-" + month + "-" + day;
            String count = DBUtil.countIntoMode2(sql_count, dateStr, dateStr);
            int countInt = Integer.parseInt(count);
            System.out.println("countInt1 === > " + countInt);
            for (int i = 0; i < countInt;) {

                List<BaseDataStd> list = DBUtil.selectIntoModeCustom(i, sql, cls, dateStr, dateStr);
                DBUtil.Print(list);
                System.out.println("i  ==>" + i + ", end!!!");
                i = i + 5000;

            }
        } catch (Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    private static void calHealth(String sql_count, String sql) throws Exception {
        String count1 = DBUtil.countIntoMode2(sql_count);

        int countInt1 = Integer.parseInt(count1);
        System.out.println("countInt1 === > " + countInt1);
        for (int i = 0; i < countInt1;) {

            List<BaseDataStd> list = DBUtil.selectIntoMode2(i, sql);
            DBUtil.Print2(list);
            System.out.println("i  ==>" + i + ", end!!!");
            i = i + 5000;

        }
    }

    private static void staticsCrowdList() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {

                        GlobalStaticsCrowdJob.execute();

                    } catch (Exception e) {

                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                }

            }
        }).start();
    }
}
