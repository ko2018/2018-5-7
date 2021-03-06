package com.talent.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.core.global.Constant;
import com.talent.job.serial.CrowdDetails;
import com.talent.job.serial.CrowdFeature;
import com.talent.job.serial.YandM;
import com.talent.mode.BaseDataStdMode;

public class MRUtils {
    protected static Logger logger = LoggerFactory.getLogger("MRUtils");

    // 1是数值型, 2是枚举型, 4是文本型， null是无类型 3是分类型
    private static String VALUE_1 = "1", VALUE_2 = "2", VALUE_4 = "4", VALUE_NULL = null, VALUE_3 = "3";

    public static List<String> getYearSort(List<String> list) {
        Collections.sort(list, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                int v1 = Integer.parseInt(o1);
                int v2 = Integer.parseInt(o2);
                if (v1 < v2)
                    return -1;
                if (v1 == v2)
                    return 0;

                return 1;
            }
        });

        return list;
    }

    public final static String B_TRUE = "true", B_FALSE = "false", B_NULL = "null";

    // 不患病，患病，null， 空白
    private static String DIS_0 = "0", DIS_1 = "1", DIS_2 = "2", DIS_3 = "3";

    public static boolean filtersFeature(Row data, final CrowdDetails crowDetails, String script) throws Exception {
        try {
            Map<String, CrowdFeature> map = crowDetails.getInFeatrueMap();
            CrowdFeature cf = null;
            String type = null, dictID = null;
            scala.collection.immutable.Map dataMaps = null;
            for (String key : map.keySet()) {
                cf = map.get(key);
                type = cf.getFeatureType();
                dictID = cf.getDictionaryID();
                if (VALUE_1.equals(type)) {
                    dataMaps = data.getAs("dataMaps");
                    if (!dataMaps.get(dictID).isEmpty()) {

                        try {
                            int val = Integer.parseInt((String) dataMaps.get(dictID).get());
                            if (val >= cf.getLowLimit() && val <= cf.getUpLimit())
                                script = script.replace(key, B_TRUE);
                            else
                                script = script.replace(key, B_FALSE);
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                            script = script.replace(key, B_FALSE);
                        }
                    } else {
                        script = script.replace(key, B_FALSE);
                    }

                    continue;
                }
                if (VALUE_2.equals(type)) {
                    dataMaps = data.getAs("dataMaps");
                    if (!dataMaps.get(dictID).isEmpty()) {
                        String val = (String) dataMaps.get(dictID).get();
                        boolean flag = false;
                        for (String tmp : cf.getValList()) {
                            if (tmp.equals(val)) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag)
                            script = script.replace(key, B_TRUE);
                        else
                            script = script.replace(key, B_FALSE);

                    } else {
                        script = script.replace(key, B_FALSE);
                    }
                    continue;
                }
                if (VALUE_3.equals(type)) {

                    continue;
                }
                if (VALUE_4.equals(type)) {
                    continue;
                }
            }
            scala.collection.immutable.Map tmap = null;
            map = crowDetails.getInDiseaseMap();
            for (String key : map.keySet()) {
                cf = map.get(key);
                String flag = B_FALSE;
                for (String tmp : cf.getValList()) {
                    if (DIS_0.equals(tmp)) {
                        tmap = data.getAs("diseaseTrue");

                        if (!tmap.get(key).isEmpty()) {
                            flag = B_TRUE;
                        }

                    } else if (DIS_1.equals(tmp)) {

                        tmap = data.getAs("diseaseFalse");
                        if (!tmap.get(key).isEmpty()) {
                            flag = B_TRUE;
                        }
                    } else if (DIS_2.equals(tmp)) {
                        tmap = data.getAs("diseaseNull");
                        if (!tmap.get(key).isEmpty()) {
                            flag = B_TRUE;
                        }
                    } else if (DIS_3.equals(tmp)) {
                        tmap = data.getAs("diseaseBlank");
                        if (!tmap.get(key).isEmpty()) {
                            flag = B_TRUE;
                        }
                    }
                    script = script.replace(key, flag);
                }

            }
            boolean isChose = false;

            map = crowDetails.getInItemMap();
            for (String key : map.keySet()) {
                cf = map.get(key);
                String flag = B_FALSE;
                for (String tmp : cf.getValList()) {
                    if (DIS_0.equals(tmp)) {
                        tmap = data.getAs("itemTrue");

                        if (!tmap.get(key).isEmpty()) {
                            flag = B_TRUE;
                        }

                    } else if (DIS_1.equals(tmp)) {

                        tmap = data.getAs("itemFalse");
                        if (!tmap.get(key).isEmpty()) {
                            flag = B_TRUE;
                        }
                    } else if (DIS_2.equals(tmp)) {
                        tmap = data.getAs("itemNull");
                        if (!tmap.get(key).isEmpty()) {
                            flag = B_TRUE;
                        }
                    } else if (DIS_3.equals(tmp)) {
                        tmap = data.getAs("itemBlank");
                        if (!tmap.get(key).isEmpty()) {
                            flag = B_TRUE;
                        }
                    }
                    script = script.replace(key, flag);
                }

            }

            isChose = ScriptEngineUtil.executeScript(script);
            return isChose;
        } catch (Exception e) {
            logger.error("script=" + script);
            logger.error(e.getMessage());
            return false;
        }

    }

    // 判断纳入与结局规则逻辑
    public static String filtersFeatures(BaseDataStdMode data, final Map<String, CrowdFeature> map,
            final Map<String, CrowdFeature> disMap, final Map<String, CrowdFeature> itemMap, String script)
            throws Exception {
        try {
            if (StringUtils.isEmpty(script))
                return B_TRUE;
            CrowdFeature cf = null;
            String type = null, dictID = null;
            Map dataMaps = data.getDataMaps();
            ;
            Map<String, String> rsMap = new HashMap<String, String>();
            for (String key : map.keySet()) {
                cf = map.get(key);
                type = cf.getFeatureType();
                dictID = cf.getDictionaryID();

                if (VALUE_1.equals(type)) {

                    if (!dataMaps.containsKey(key) && StringUtils.isNotEmpty((String) dataMaps.get(key))) {
                        try {
                            int val = Integer.parseInt((String) dataMaps.get(dictID));
                            if (val >= cf.getLowLimit() && val <= cf.getUpLimit())
                                rsMap.put(key, B_TRUE);
                            else
                                rsMap.put(key, B_FALSE);
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                            rsMap.put(key, B_FALSE);
                        }
                    } else {
                        rsMap.put(key, B_NULL);
                    }

                    continue;
                } else if (VALUE_2.equals(type)) {
                    if (!dataMaps.containsKey(key) && StringUtils.isNotEmpty((String) dataMaps.get(key))) {
                        String val = (String) dataMaps.get(dictID);
                        boolean flag = false;
                        for (String tmp : cf.getValList()) {
                            if (tmp.equals(val)) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag)
                            rsMap.put(key, B_TRUE);
                        else
                            rsMap.put(key, B_FALSE);

                    } else {
                        rsMap.put(key, B_NULL);
                    }
                    continue;
                } else {
                    rsMap.put(key, B_TRUE);
                }
            }
            Map tmap = null;

            for (String key : disMap.keySet()) {
                cf = disMap.get(key);
                String flag = B_TRUE;
                for (String tmp : cf.getValList()) {
                    if (DIS_0.equals(tmp)) {
                        tmap = data.getDiseaseTrue();
                    } else if (DIS_1.equals(tmp)) {
                        tmap = data.getDiseaseFalse();
                    } else if (DIS_2.equals(tmp)) {
                        tmap = data.getDiseaseNull();
                        flag = B_NULL;
                    } else if (DIS_3.equals(tmp)) {
                        tmap = data.getDiseaseBlank();
                    } else {
                        continue;
                    }
                    if (tmap.containsKey(key)) {
                        rsMap.put(key, flag);
                    } else {
                        rsMap.put(key, B_FALSE);
                    }
                }

            }
            boolean isChose = false;

            for (String key : itemMap.keySet()) {
                cf = itemMap.get(key);
                String flag = B_TRUE;
                for (String tmp : cf.getValList()) {
                    if (DIS_0.equals(tmp)) {
                        tmap = data.getItemTrue();
                    } else if (DIS_1.equals(tmp)) {
                        tmap = data.getItemFalse();
                    } else if (DIS_2.equals(tmp)) {
                        tmap = data.getItemNull();
                        flag = B_NULL;
                    } else if (DIS_3.equals(tmp)) {
                        tmap = data.getItemBlank();
                    } else {
                        continue;
                    }
                    if (tmap.containsKey(key)) {
                        rsMap.put(key, flag);
                    } else {
                        rsMap.put(key, B_FALSE);
                    }
                }

            }
            String script_tmp = script;
            for (String key : rsMap.keySet()) {
                String val = rsMap.get(key);
                if (val.equals(B_NULL)) {
                    val = B_FALSE;
                }
                script_tmp = script_tmp.replace(key, val);
            }
            isChose = ScriptEngineUtil.executeScript(script_tmp);
            if (isChose)
                return B_TRUE;
            script_tmp = script;
            for (String key : rsMap.keySet()) {
                String val = rsMap.get(key);
                if (val.equals(B_NULL)) {
                    val = B_TRUE;
                } else {
                    val = B_FALSE;
                }
                script_tmp = script_tmp.replace(key, val);
            }
            isChose = ScriptEngineUtil.executeScript(script_tmp);
            if (isChose)
                return B_NULL;
            else
                return B_FALSE;
        } catch (Exception e) {
            logger.error("script=" + script);
            logger.error(e.getMessage());
            return B_FALSE;
        }

    }

    // 判断纳入与结局规则逻辑
    public static String filtersFeature(Row data, final Map<String, CrowdFeature> map,
            final Map<String, CrowdFeature> disMap, final Map<String, CrowdFeature> itemMap, String script)
            throws Exception {
        try {
            CrowdFeature cf = null;
            String type = null, dictID = null;
            scala.collection.immutable.Map dataMaps = null;
            Map<String, String> rsMap = new HashMap<String, String>();
            for (String key : map.keySet()) {
                cf = map.get(key);
                type = cf.getFeatureType();
                dictID = cf.getDictionaryID();
                dataMaps = data.getAs("dataMaps");
                if (dataMaps.get(dictID).isEmpty() || "".equals(dataMaps.get(dictID).get())) {
                    rsMap.put(key, B_NULL);
                    continue;
                }
                String rsVal = (String) dataMaps.get(dictID).get();
                if (B_NULL.equalsIgnoreCase(rsVal)) {
                    rsMap.put(key, B_NULL);
                    continue;
                }
                if (VALUE_1.equals(type)) {

                    try {
                        double val = Double.parseDouble(rsVal);
                        if (val >= cf.getLowLimit() && val <= cf.getUpLimit())
                            rsMap.put(key, B_TRUE);
                        else
                            rsMap.put(key, B_FALSE);
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                        rsMap.put(key, B_FALSE);
                    }

                    continue;
                } else if (VALUE_2.equals(type) || VALUE_3.equals(type)) {
                    boolean flag = false;
                    for (String tmp : cf.getValList()) {
                        if (tmp.equals(rsVal)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag)
                        rsMap.put(key, B_TRUE);
                    else
                        rsMap.put(key, B_FALSE);

                    continue;

                } else if (VALUE_4.equals(type)) {

                    if (cf.getFeatureVal().equals(rsVal)) {
                        rsMap.put(key, B_TRUE);
                    } else {
                        rsMap.put(key, B_FALSE);

                    }
                    continue;
                } else {
                    rsMap.put(key, B_TRUE);
                }
            }
            scala.collection.immutable.Map tmap = null;

            for (String key : disMap.keySet()) {
                cf = disMap.get(key);
                String flag = B_FALSE;

                for (String tmp : cf.getValList()) {
                    if (DIS_0.equals(tmp)) {
                        tmap = data.getAs("diseaseFalse");
                    } else if (DIS_1.equals(tmp)) {
                        tmap = data.getAs("diseaseTrue");
                    } else if (DIS_2.equals(tmp)) {
                        tmap = data.getAs("diseaseNull");
                        if (tmap != null && !tmap.get(tmp).isEmpty()) {
                            flag = B_NULL;
                        }
                        continue;
                    } else if (DIS_3.equals(tmp)) {
                        tmap = data.getAs("diseaseBlank");
                    } else {
                        continue;
                    }
                    if (tmap != null && !tmap.get(key).isEmpty()) {
                        flag = B_TRUE;
                    }
                }
                rsMap.put(key, flag);

            }
            boolean isChose = false;

            for (String key : itemMap.keySet()) {
                cf = itemMap.get(key);
                String flag = B_FALSE;
                for (String tmp : cf.getValList()) {
                    if (DIS_0.equals(tmp)) {
                        tmap = data.getAs("itemFalse");
                    } else if (DIS_1.equals(tmp)) {
                        tmap = data.getAs("itemTrue");
                    } else if (DIS_2.equals(tmp)) {
                        tmap = data.getAs("itemNull");
                        if (tmap != null && !tmap.get(tmp).isEmpty()) {
                            flag = B_NULL;
                        }
                        continue;
                    } else if (DIS_3.equals(tmp)) {
                        tmap = data.getAs("itemBlank");
                    } else {
                        continue;
                    }
                    if (tmap != null && !tmap.get(key).isEmpty()) {
                        flag = B_TRUE;
                    }
                }
                rsMap.put(key, flag);
            }
            String script_tmp = script;
            for (String key : rsMap.keySet()) {
                String val = rsMap.get(key);
                if (B_NULL.equalsIgnoreCase(val)) {
                    val = B_FALSE;
                    script_tmp = script_tmp.replaceAll(Constant.REGEX_PRE + key, B_FALSE).replaceAll(key, B_FALSE);
                } else {
                    script_tmp = script_tmp.replaceAll(key, val);
                }

            }
            if (Pattern.matches(Constant.REGEX_MATCH, script_tmp))
                return B_FALSE;
            isChose = ScriptEngineUtil.executeScript(script_tmp);
            if (isChose)
                return B_TRUE;
            script_tmp = script;

            for (String key : rsMap.keySet()) {
                String val = rsMap.get(key);
                if (val.equals(B_NULL)) {
                    val = B_TRUE;
                    script_tmp = script_tmp.replaceAll(Constant.REGEX_PRE + key, B_TRUE).replaceAll(key, B_TRUE);
                } else {
                    val = B_FALSE;
                    script_tmp = script_tmp.replaceAll(Constant.REGEX_PRE + key, B_FALSE).replaceAll(key, B_FALSE);
                }

            }
            script_tmp = script_tmp.replaceAll("\\&", "A").replaceAll("\\|", "B");
            script_tmp = script_tmp.replaceAll("AA", "||").replaceAll("BB", "&&");
            isChose = ScriptEngineUtil.executeScript(script_tmp);
            if (isChose)
                return B_NULL;
            else
                return B_FALSE;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("script=" + script);
            logger.error(e.getMessage());
            return B_FALSE;
        }

    }

    public static YandM getEndDate(String exameTime, YandM timeWindow, YandM followTime) {
        YandM date = new YandM();
        String[] arrs = exameTime.split("-");
        if (arrs.length > 0) {
            date.y = Integer.parseInt(arrs[0]);
            date.m = Integer.parseInt(arrs[1]);
        }
        date.y += timeWindow.y + followTime.y;
        date.m += timeWindow.m + followTime.m;
        if (date.m >= 12) {
            date.y += (int) (date.m / 12);
            date.m = date.m - (int) (date.m / 12) * 12;
        }
        return date;
    }

    public static YandM getYandM(String exameTime) {
        YandM date = new YandM();
        String[] arrs = exameTime.split("-");
        if (arrs.length > 0) {
            date.y = Integer.parseInt(arrs[0]);
            date.m = Integer.parseInt(arrs[1]);
        }
        if (date.m >= 12) {
            date.y += (int) (date.m / 12);
            date.m = date.m - (int) (date.m / 12) * 12;
        }
        return date;
    }

    public static YandM addYandM(String exameTime, YandM add) {
        YandM date = new YandM();
        String[] arrs = exameTime.split("-");
        if (arrs.length > 0) {
            date.y = Integer.parseInt(arrs[0]);
            date.m = Integer.parseInt(arrs[1]);
        }
        date.y = date.y + add.y;
        date.m = date.m + add.m;
        if (date.m >= 12) {
            date.y += (int) (date.m / 12);
            date.m = date.m - (int) (date.m / 12) * 12;
        }
        return date;
    }

    public static YandM addYandM(YandM date, YandM add) {
        YandM date1 = new YandM();
        date1.y = date.y + add.y;
        date1.m = date.m + add.m;
        if (date1.m >= 12) {
            date1.y += (int) (date1.m / 12);
            date1.m = date1.m - (int) (date1.m / 12) * 12;
        }
        return date1;
    }

    public static boolean lessThanTime(YandM date, YandM endDate) {
        if (endDate.y > date.y) {
            return true;
        } else if (endDate.y == date.y) {
            if (endDate.m >= date.m) {
                return true;
            }
        }
        return false;
    }

    public static boolean moreThanTime(YandM date, YandM endDate) {
        if (date.y > endDate.y) {
            return true;
        } else if (date.y == endDate.y) {
            if (date.m >= endDate.m) {
                return true;
            }
        }
        return false;
    }

    /** 【TIME】 是否超过了纳入的截止时间【T】,即：【TIME】 < 【T】 */
    public static boolean lessThanTime(String exameTime, YandM timeWindow, YandM followTime, YandM endDate) {
        int year = 0, month = 0;
        String[] arrs = exameTime.split("-");
        if (arrs.length > 0) {
            year = Integer.parseInt(arrs[0]);
            month = Integer.parseInt(arrs[1]);
        }
        year += timeWindow.y + followTime.y;
        month += timeWindow.m + followTime.m;

        if (endDate.y > year) {
            return true;
        } else if (endDate.y == year) {
            if (endDate.m >= month) {
                return true;
            }
        }

        return false;
    }

    /** 【TIME】 是否超过了纳入的截止时间【T】,即：【TIME】 < 【T】 */
    public static boolean lessThanTime(String exameTime, YandM endDate) {
        int year = 0, month = 0;
        String[] arrs = exameTime.split("-");
        if (arrs.length > 0) {
            year = Integer.parseInt(arrs[0]);
            month = Integer.parseInt(arrs[1]);
        }

        if (endDate.y > year) {
            return true;
        } else if (endDate.y == year) {
            if (endDate.m >= month) {
                return true;
            }
        }

        return false;
    }

}
