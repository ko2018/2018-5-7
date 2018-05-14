package com.talent.job.serial;import java.io.IOException;import java.io.Serializable;import java.util.List;import org.apache.hadoop.hbase.TableName;import org.apache.hadoop.hbase.client.Connection;import org.apache.hadoop.hbase.client.Put;import org.apache.hadoop.hbase.client.Table;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import com.clearspring.analytics.util.Lists;import com.talent.core.HBaseContextC;import com.talent.util.TimeUtil;//hbase存储对象public class SaveDataHBaseImpl implements Serializable {    protected static Logger logger = LoggerFactory.getLogger(SaveDataHBaseImpl.class);    private static final long serialVersionUID = -3708407644718429832L;    public static boolean putBaseDataBatchN(String tableName, String crowdID, List<BaseDataN> baseDataList) {        Table table = null;        if (baseDataList == null)            return false;        if (baseDataList.size() < 1)            return false;        try {            Connection conn = HBaseContextC.getIns().getConn();            TableName tName = TableName.valueOf(tableName);            table = conn.getTable(tName);            List<Put> putLists = Lists.newArrayList();            for (BaseDataN tmpData : baseDataList) {                String startKey = TimeUtil.getNumForID(tmpData.idCode, 201);                // 00:人群id:体检id_体检时间:机构:体检唯一id                // String rowKey = String.format(Constant.ROW_KEY_FORMAT_CROWD,                // startKey, crowdID,                // tmpData.examID + "_" + tmpData.examTime, tmpData.organID,                // tmpData.idCode);                // Put put = new Put(Bytes.toBytes(rowKey));                // put.addColumn(Bytes.toBytes(Constant.FAMILY_INFO),                // Bytes.toBytes(Constant.BASE_COL_EXAM_LIST),                // Bytes.toBytes(tmpData.examID + ":" + tmpData.examTime));                // put.addColumn(Bytes.toBytes(Constant.FAMILY_INFO),                // Bytes.toBytes(Constant.BASE_COL_EXAM_LIST_SUM),                // Bytes.toBytes("" + tmpData.rank));                // putLists.add(put);            }            table.put(putLists);        } catch (IOException e) {            logger.error("{}", e);            return false;        } finally {            try {                table.close();            } catch (IOException e) {                logger.error("{}", e);            }        }        return true;    }}