package com.talent.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.common.SQLCrowd;
import com.talent.job.serial.CrowdDetails;
import com.talent.job.serial.CrowdFeature;
import com.talent.mode.CrowdDetailsDB;
import com.talent.mode.CrowdFeatureDB;
import com.talent.mode.ITable;

public class CrowdUtils {
    protected static Logger logger = LoggerFactory.getLogger("CrowdUtils");

    public static List<ITable> getCrowdList() {
        List<ITable> list = DBUtil.selectIntoMode(SQLCrowd.TABLE_CROWD, CrowdDetailsDB.class);
        if (list == null || list.size() < 1)
            return null;
        for (ITable t : list) {
            CrowdDetailsDB c = (CrowdDetailsDB) t;
            List<ITable> featureList = DBUtil.selectIntoMode(SQLCrowd.TABLE_FEATURE, CrowdFeatureDB.class,
                    c.getData().getCrowId());

            if (featureList != null) {

                Map<String, CrowdFeature> inFeatureMap = new HashMap<String, CrowdFeature>();
                Map<String, CrowdFeature> inDiseaseMap = new HashMap<String, CrowdFeature>();
                Map<String, CrowdFeature> inItemMap = new HashMap<String, CrowdFeature>();
                Map<String, CrowdFeature> outFeatureMap = new HashMap<String, CrowdFeature>();
                Map<String, CrowdFeature> outDiseaseMap = new HashMap<String, CrowdFeature>();
                Map<String, CrowdFeature> outItemMap = new HashMap<String, CrowdFeature>();
                String id, flag;
                for (ITable e : featureList) {
                    CrowdFeatureDB tmp = (CrowdFeatureDB) e;
                    id = tmp.getData().getFeatureID();
                    flag = tmp.getData().getFlag();
                    if (SQLCrowd.IN_FEATURE.equals(flag)) {
                        inFeatureMap.put(id, tmp.getData());
                    } else if (SQLCrowd.IN_DISEASE.equals(flag)) {
                        inDiseaseMap.put(id, tmp.getData());
                    } else if (SQLCrowd.IN_ITEM.equals(flag)) {
                        inItemMap.put(id, tmp.getData());
                    } else if (SQLCrowd.OUT_FEATURE.equals(flag)) {
                        outFeatureMap.put(id, tmp.getData());
                    } else if (SQLCrowd.OUT_DISEASE.equals(flag)) {
                        outDiseaseMap.put(id, tmp.getData());
                    } else if (SQLCrowd.OUT_ITEM.equals(flag)) {
                        outItemMap.put(id, tmp.getData());
                    }
                }

                c.getData().setInFeatrueMap(inFeatureMap);
                c.getData().setInDiseaseMap(inDiseaseMap);
                c.getData().setInItemMap(inItemMap);
                c.getData().setOutDiseaseMap(outDiseaseMap);
                c.getData().setOutFeatrueMap(outFeatureMap);
                c.getData().setOutItemMap(outItemMap);

            }

        }

        return list;
    }

    /** 原始数据 筛选 */
    public static Scan getScanStandard(CrowdDetails crowDetails) {
        Scan scan = SparkCalUtil.getScan();

        FilterList filters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        // 00:机构id:体检时间:体检唯一id:体检编号
        List<String> organList = crowDetails.getOrganList();
        String organRegx = organRegex(organList);
        RowFilter filter1 = SparkCalUtil.getFilter(organRegx);
        String dateRegx = null;
        if ("1".equals(crowDetails.getType())) {
            dateRegx = dateRegex(crowDetails.dateBeforeDate.toStr(), crowDetails.dateAfterExtDate.toStr());
        } else {
            dateRegx = dateRegex(crowDetails.dateBeforeDate.toStr(), crowDetails.dateAfterDate.toStr());
        }

        RowFilter filter2 = SparkCalUtil.getFilter(dateRegx);
        filters.addFilter(filter2);
        // filters.addFilter(SparkCalUtil.getFilter1("00076DD7F6053A9AF20715FFF787C204"));
        filters.addFilter(filter1);
        scan.setFilter(filters);
        return scan;
    }

    private static String organRegex(List<String> organList) {
        int len = organList.size();
        String strB = "";
        if (len == 1) {
            return String.format(":R%s:", organList.get(0));
        }
        for (int i = 0; i < len; i++) {
            strB += String.format("(:R%s:)", organList.get(i));
            if (i < len - 1) {
                strB += "|";
            }
        }
        return strB;
    }

    public static Scan getScanCrowdID(int[] arrs, String crowdID) {
        Scan scan = SparkCalUtil.getScan();
        String startRow = String.format("%03d:", arrs[0]);
        String stopRow = String.format("%03d:", arrs[1]);
        FilterList filters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        RowFilter filter1 = SparkCalUtil.getFilter("C" + crowdID);
        filters.addFilter(filter1);
        scan.setStartRow(Bytes.toBytes(startRow));
        scan.setStopRow(Bytes.toBytes(stopRow));
        scan.setFilter(filters);
        return scan;
    }

    private static String dateRegex(String dateBefore, String dateAfter) {
        int indexB = dateBefore.indexOf("-");
        String dateYearB = dateBefore.substring(0, indexB);
        int yearB = Integer.parseInt(dateYearB);
        int monthB = Integer.parseInt(dateBefore.substring(indexB + 1));
        int indexA = dateAfter.indexOf("-");
        String dateYearA = dateAfter.substring(0, indexA);
        int yearA = Integer.parseInt(dateYearA);
        int monthA = Integer.parseInt(dateAfter.substring(indexA + 1));
        String strB = "";
        for (int i = monthB; i <= 12; i++) {
            String m = String.format("%02d", i);
            strB += String.format("(:D%s-%s:)", yearB, m);
            strB += "|";

        }
        for (int i = yearB + 1; i < yearA; i++) {

            strB += String.format("(:D%s-)", i);
            strB += "|";

        }
        for (int i = 1; i <= monthA; i++) {
            String m = String.format("%02d", i);
            strB += String.format("(:D%s-%s:)", yearA, m);
            if (i < monthA) {
                strB += "|";
            }
        }

        return strB;
    }

}
