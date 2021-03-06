package com.talent.util;

import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RowFilter;

import com.talent.common.HBConstant;
import com.talent.core.hbase.filter.CustomRowFilter;

/**
 * @author zhangqian
 * @time 2017��12��5�� ����7:47:52
 * @version 1.0v
 */
public class SparkCalUtil {

    public static Scan getScan() {
        Scan scan = new Scan();
        scan.setCaching(500);
        scan.setCacheBlocks(false);
        return scan;
    }

    public static FilterList getFilters(String regex) {
        FilterList filters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        RowFilter filter1 = CustomRowFilter.getRowKeyFilter(regex, CompareFilter.CompareOp.EQUAL);
        filters.addFilter(filter1);
        return filters;
    }

    public static RowFilter getFilter(String regex) {
        RowFilter filter1 = CustomRowFilter.getRowKeyFilter(regex, CompareFilter.CompareOp.EQUAL);
        return filter1;
    }

    public static RowFilter getFilter1(String regex) {
        RowFilter filter1 = CustomRowFilter.getRowKeyFilterSubStr(regex);
        return filter1;
    }

    public static Filter getColFilter(String regex) {
        Filter filter1 = CustomRowFilter.getColValFilterRegex(HBConstant.FAMILY_INFO, HBConstant.COL_CROWD_LIST, regex);
        return filter1;
    }

    public static Filter getColNOFilter(String regex) {
        Filter filter1 = CustomRowFilter.getColValFilterRegexNO(HBConstant.FAMILY_INFO, HBConstant.COL_DISEASE, regex);
        return filter1;
    }

}
