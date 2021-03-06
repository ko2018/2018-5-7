package com.talent.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.common.SQLCrowd;
import com.talent.common.SQLGroup;
import com.talent.core.table.DBTable;
import com.talent.job.serial.RowKey;

import scala.Tuple2;

public class GroupDB {
    protected static Logger logger = LoggerFactory.getLogger("GlobalStaticsGroupJob");

    /** 保存table结果 */
    public static long resultToSave(JavaPairRDD<RowKey, Iterable<Row>> pRdd, String groupID) {
        pRdd.foreachPartition(new VoidFunction<Iterator<Tuple2<RowKey, Iterable<Row>>>>() {

            @Override
            public void call(Iterator<Tuple2<RowKey, Iterable<Row>>> t) throws Exception {

                Map<Row, int[]> rsMap = new HashMap<Row, int[]>();

                while (t.hasNext()) {
                    Tuple2<RowKey, Iterable<Row>> next = t.next();
                    int sum = next._1.sum;
                    Iterator<Row> iters = next._2.iterator();
                    int i = 0;
                    while (iters.hasNext()) {
                        i++;
                        rsMap.put(iters.next(), new int[] { sum, i });
                    }
                    if (rsMap.size() > 500) {
                        save(rsMap, groupID);
                        rsMap.clear();
                    }
                }
                if (rsMap.size() > 0) {
                    save(rsMap, groupID);
                    rsMap.clear();
                }

            };

        });

        JavaRDD<Long> tRDD = pRdd.mapPartitions(new FlatMapFunction<Iterator<Tuple2<RowKey, Iterable<Row>>>, Long>() {

            @Override
            public Iterable<Long> call(Iterator<Tuple2<RowKey, Iterable<Row>>> t) throws Exception {
                AtomicLong rs = new AtomicLong();
                while (t.hasNext()) {
                    rs.addAndGet(t.next()._1.sum);

                }
                List<Long> list = new ArrayList<Long>();
                list.add(rs.get());
                return list;
            }

        });

        if (tRDD.isEmpty())
            return 0l;

        long sum = tRDD.reduce(new Function2<Long, Long, Long>() {

            @Override
            public Long call(Long v1, Long v2) throws Exception {
                // TODO Auto-generated method stub
                return v1 + v2;
            }
        });
        return sum;

    }

    public static void save(Map<Row, int[]> rsMap, String groupID) {
        /** 保存table结果 到 hbase std */
        HBaseUtil.updateToHBaseGroup(rsMap, groupID);
        /** 保存table结果 到group */
        saveToGroupPeopleDB(rsMap, groupID);
        /** 保存table结果 到std */
        updateToStdDB(rsMap, groupID);
    }

    private static void updateToStdDB(Map<Row, int[]> rsMap, String groupID) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {

            conn = DBTable.getConnection();
            conn.setAutoCommit(false);

            stat = conn.prepareStatement(SQLGroup.update_to_std);

            for (Row row : rsMap.keySet()) {
                int i = 1;
                String groupListStr = (String) row.getAs("groupListStr");
                if (StringUtils.isEmpty(groupListStr)) {
                    groupListStr = groupID;
                } else {
                    if (!groupListStr.contains(groupID)) {
                        groupListStr += "," + groupID;
                    }
                }
                stat.setString(i++, groupListStr);
                stat.setString(i++, (String) row.getAs("datastdId"));
                stat.addBatch();
            }
            stat.executeBatch();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("" + SQLCrowd.update_to_std);
        } finally {
            DBTable.closeDB(conn, stat, null);
        }
    }

    private static void saveToGroupPeopleDB(Map<Row, int[]> rsMap, String groupID) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DBTable.getConnection();

            conn.setAutoCommit(false);
            stat = conn.prepareStatement(SQLGroup.replace_into_group);
            // "
            // + " key_id, group_pId, datastd_id, user_code,"
            // + " exam_sum, check_code, institution_id, "
            // + " crowd_type, crowd_year, add_time, exam_index
            for (Row row : rsMap.keySet()) {
                int i = 1;
                stat.setString(i++,
                        groupID + "_" + (String) row.getAs("examTime") + "_" + (String) row.getAs("checkCode"));

                // group_pId,
                stat.setString(i++, groupID);
                // group_user_code,
                stat.setString(i++, (String) row.getAs("datastdId"));
                stat.setString(i++, (String) row.getAs("userCode"));
                int[] arrs = rsMap.get(row);
                stat.setInt(i++, arrs[0]);
                stat.setString(i++, (String) row.getAs("checkCode"));
                stat.setString(i++, (String) row.getAs("institutionId"));
                stat.setString(i++, (String) row.getAs("type"));
                stat.setString(i++, (String) row.getAs("examTime"));
                stat.setTimestamp(i++, new Timestamp(new Date().getTime()));
                stat.setInt(i++, arrs[1]);

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

    /** 保存 统计 记录 结果 表 */
    public static void saveFailToPeopleDB(List<String> rsList) {
        if (rsList.size() < 1)
            return;
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = DBTable.getConnection();
            conn.setAutoCommit(false);
            stat = conn.prepareStatement(SQLCrowd.replace_to_people);
            // Bytes.toBytes(crowdID + "," + row.getAs("userCode") + "," +
            // row.getAs("checkCode") + ","
            // + row.getAs("examTime") + "," + row.getAs("institutionId") + ","
            // + Integer.parseInt(arrs[0]) + "," + Integer.parseInt(arrs[1]) +
            // "," + arrs[2] + ","
            // + row.getAs("datastdId")));
            for (String row : rsList) {
                int i = 1;
                String[] arrs = row.split(",");
                String crowdID = arrs[0];
                String userCode = arrs[1];
                String checkCode = arrs[2];
                String examTime = arrs[3];
                String institutionId = arrs[4];
                String arrs0 = arrs[5];
                String arrs1 = arrs[6];
                String arrs2 = arrs[6];
                String datastdId = arrs[7];
                stat.setString(i++, crowdID + "_" + datastdId);

                // crowd_pId,
                stat.setString(i++, crowdID);
                // crowd_user_code,
                stat.setString(i++, userCode);
                stat.setString(i++, checkCode);
                stat.setString(i++, examTime);
                stat.setString(i++, institutionId);

                stat.setInt(i++, Integer.parseInt(arrs0));
                stat.setInt(i++, Integer.parseInt(arrs1));
                stat.setString(i++, arrs2);
                stat.setString(i++, datastdId);
                stat.setTimestamp(i, new Timestamp(new Date().getTime()));
                stat.addBatch();
            }
            stat.executeBatch();
            conn.commit();

        } catch (Exception e) {
            logger.error("入库失败 ！！！" + e.getMessage());
            e.printStackTrace();
        } finally {
            DBTable.closeDB(conn, stat, null);
        }
    }

}
