package com.talent.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

import com.talent.common.HBConstant;
import com.talent.core.HBaseContextC;
import com.talent.core.global.Constant;
import com.talent.job.serial.CrowdDetails;
import com.talent.job.serial.CrowdFeature;
import com.talent.mode.BaseDataStdMode;

import scala.Tuple2;

public class CrowdMRUtils {

    /**
     * Description: <br/>
     * 
     * @author dell
     * @param scDD
     * @param scan1
     * @param hbaseTableName
     * @param sqlContext
     * @param tableName
     * @return
     */
    public static boolean getStdDataFrame(JavaSparkContext scDD, Scan scan, String hbaseTableName,
            SQLContext sqlContext, String tableName, final CrowdDetails crowDetails) {
        try {
            JavaRDD<BaseDataStdMode> rowRDD = getNormalStdRdd(scDD, scan, hbaseTableName, crowDetails).values();

            DataFrame peopleDataFrame = sqlContext.createDataFrame(rowRDD, BaseDataStdMode.class);
            peopleDataFrame.registerTempTable(tableName);
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

    public static JavaPairRDD<String, BaseDataStdMode> getNormalStdRdd(JavaSparkContext scDD, Scan scan,
            String hbaseTableName, final CrowdDetails crowDetails) {
        try {
            Configuration conf = HBaseContextC.getIns().getConf();
            conf.set(TableInputFormat.INPUT_TABLE, hbaseTableName);
            conf.set(TableInputFormat.SCAN, HBaseUtil.convertScanToString(scan));

            JavaPairRDD<ImmutableBytesWritable, Result> source = scDD.newAPIHadoopRDD(conf, TableInputFormat.class,
                    ImmutableBytesWritable.class, Result.class);

            JavaPairRDD<String, BaseDataStdMode> rowRDD = source
                    .mapToPair(new PairFunction<Tuple2<ImmutableBytesWritable, Result>, String, BaseDataStdMode>() {

                        private static final long serialVersionUID = 1L;

                        @Override
                        public Tuple2<String, BaseDataStdMode> call(Tuple2<ImmutableBytesWritable, Result> t)
                                throws Exception {
                            ImmutableBytesWritable ibw = t._1();
                            String rowKey = new String(ibw.get());
                            // 00:机构id:体检时间:体检唯一id:体检编号
                            // ROW_KEY_FORMAT = "%s:R%s:D%s:%s:%s";
                            String[] arrs = rowKey.split(Constant.KEY_SEPARATOR_C);
                            String organID = arrs[1].substring(1);
                            String examTime = arrs[2].substring(1);
                            String userCode = arrs[3];
                            String examID = arrs[4];
                            Result result = t._2();
                            BaseDataStdMode model = new BaseDataStdMode();

                            model.setInstitutionId(organID);
                            model.setCheckCode(examID);
                            model.setExamTime(examTime);
                            model.setUserCode(userCode);
                            model.setRowKey(rowKey);
                            Cell cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_JSON));
                            String examJson = new String(CellUtil.cloneValue(cell));
                            Map<String, String> dataMaps = JacksonUtil.readValue(examJson, Map.class);

                            Map<String, String> maps = new HashMap<>();
                            CrowdFeature cf = null;
                            String val = null;
                            Map<String, CrowdFeature> cfMap = crowDetails.getInFeatrueMap();
                            for (String key : cfMap.keySet()) {
                                cf = cfMap.get(key);
                                if (dataMaps.containsKey(cf.getDictionaryID())) {
                                    val = dataMaps.get(cf.getDictionaryID()).trim();
                                    if (!"".equals(val))
                                        maps.put(cf.getDictionaryID(), val);
                                }
                            }
                            model.setDataMaps(maps);
                            cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_DISEASE_TRUE));
                            String diseaseTrue = new String(CellUtil.cloneValue(cell));
                            Map<String, String> map = getMaps(diseaseTrue, crowDetails.getInDiseaseMap(),
                                    crowDetails.getOutDiseaseMap());
                            if (map != null)
                                model.setDiseaseTrue(map);

                            cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_DISEASE_FALSE));
                            String diseaseFalse = new String(CellUtil.cloneValue(cell));
                            map = getMaps(diseaseFalse, crowDetails.getInDiseaseMap(), crowDetails.getOutDiseaseMap());
                            if (map != null)
                                model.setDiseaseFalse(map);

                            cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_DISEASE_NULL));
                            String diseaseNull = new String(CellUtil.cloneValue(cell));
                            map = getMaps(diseaseNull, crowDetails.getInDiseaseMap(), crowDetails.getOutDiseaseMap());
                            if (map != null)
                                model.setDiseaseNull(map);

                            cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_DISEASE_BLANK));
                            String diseaseBlank = new String(CellUtil.cloneValue(cell));
                            map = getMaps(diseaseBlank, crowDetails.getInDiseaseMap(), crowDetails.getOutDiseaseMap());
                            if (map != null)
                                model.setDiseaseBlank(map);

                            cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_ITEM_TRUE));
                            String itemTrue = "";
                            if (cell != null)
                                itemTrue = new String(CellUtil.cloneValue(cell));
                            model.setItemTrue(getMaps(itemTrue));

                            cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_ITEM_FALSE));
                            String itemFalse = "";
                            if (cell != null)
                                itemFalse = new String(CellUtil.cloneValue(cell));
                            map = getMaps(itemFalse, crowDetails.getInItemMap(), crowDetails.getOutItemMap());
                            if (map != null)
                                model.setItemFalse(map);

                            cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_ITEM_NULL));
                            String itemNull = "";
                            if (cell != null)
                                itemNull = new String(CellUtil.cloneValue(cell));
                            map = getMaps(itemNull, crowDetails.getInItemMap(), crowDetails.getOutItemMap());
                            if (map != null)
                                model.setItemNull(map);

                            cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_ITEM_BLANK));
                            String itemBlank = "";
                            if (cell != null)
                                itemBlank = new String(CellUtil.cloneValue(cell));
                            map = getMaps(itemBlank, crowDetails.getInItemMap(), crowDetails.getOutItemMap());
                            if (map != null)
                                model.setItemBlank(map);

                            cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_CROWD_LIST));
                            String crowdListStr = new String(CellUtil.cloneValue(cell));
                            model.setCrowdListStr(crowdListStr);

                            cell = result.getColumnLatestCell(Bytes.toBytes(HBConstant.FAMILY_INFO),
                                    Bytes.toBytes(HBConstant.COL_STD_KEY_ID));
                            String stdKeyID = new String(CellUtil.cloneValue(cell));
                            model.setDatastdId(stdKeyID);
                            model.setExamTimeExt(dataMaps.get("_14"));

                            return new Tuple2<String, BaseDataStdMode>(userCode, model);
                        }

                    });
            return rowRDD;

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }

    }

    private static Map<String, String> getMaps(String listStr) {
        String[] arrs = listStr.split(",");
        HashMap<String, String> maps = new HashMap<String, String>();
        for (String arr : arrs) {
            if ("".equals(arr.trim()))
                continue;
            maps.put(arr, "1");
        }

        return maps;
    }

    private static Map<String, String> getMaps(String listStr, Map<String, CrowdFeature> inMaps,
            Map<String, CrowdFeature> outMaps) {
        String[] arrs = listStr.split(",");
        HashMap<String, String> maps = new HashMap<String, String>();
        for (String arr : arrs) {
            if ("".equals(arr.trim()))
                continue;
            if (inMaps != null && inMaps.containsKey(arr)) {
                maps.put(arr, "1");
                continue;
            }
            if (outMaps != null && outMaps.containsKey(arr)) {
                maps.put(arr, "1");
                continue;
            }

        }
        if (maps.size() < 1)
            return null;
        return maps;
    }

}
