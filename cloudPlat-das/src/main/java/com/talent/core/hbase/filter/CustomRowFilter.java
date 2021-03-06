
package com.talent.core.hbase.filter;

import org.apache.hadoop.hbase.filter.ByteArrayComparable;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 自定义过滤类 */
public class CustomRowFilter {

    private static Logger logger = LoggerFactory.getLogger(CustomRowFilter.class.getName());

    public static RowFilter getRowKeyFilter(String express, CompareFilter.CompareOp comp) {

        return new RowFilter(comp, new RegexStringComparator(express));
    }

    public static RowFilter getRowKeyFilterSubStr(String express) {

        return new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(express));
    }

    public static Filter getMulRowColFilter(String... colNames) {
        int len = colNames.length;
        byte[][] prefixes = new byte[len][];
        for (int i = 0; i < len; i++) {
            prefixes[i] = Bytes.toBytes(colNames[i]);
        }

        return new MultipleColumnPrefixFilter(prefixes);
    }

    public static Filter getColValFilterNoEqual(String family, String colName, String colVal) {
        SubstringComparator comp = new SubstringComparator(colVal);
        return new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(colName),
                CompareFilter.CompareOp.NOT_EQUAL, comp);

    }

    public static Filter getColValFilterEqual(String family, String colName, String colVal) {

        return new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(colName), CompareFilter.CompareOp.EQUAL,
                Bytes.toBytes(colVal));

    }

    public static Filter getColValFilterRegex(String family, String colName, String colValRegex) {
        RegexStringComparator comp = new RegexStringComparator(colValRegex);
        return new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(colName), CompareFilter.CompareOp.EQUAL,
                comp);

    }

    public static Filter getColValFilterRegexNO(String family, String colName, String colValRegex) {
        RegexStringComparator comp = new RegexStringComparator(colValRegex);
        return new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(colName),
                CompareFilter.CompareOp.NOT_EQUAL, comp);

    }

    public static Filter getColValFilterSubStr(String family, String colName, String colVal) {
        SubstringComparator comp = new SubstringComparator(colVal); // 查找包含 1129
                                                                    // 的字符串
        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(colName),
                CompareFilter.CompareOp.EQUAL, comp);
        return filter;

    }

    public static Filter getColumnValueFilter(String family, String colName, String colVal) {
        return new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(colName), CompareFilter.CompareOp.EQUAL,
                new CustomNumberComparator(Bytes.toBytes(colVal)));
    }

    public static class CustomNumberComparator extends ByteArrayComparable {

        public CustomNumberComparator(byte[] value) {
            super(value);

        }

        @Override
        public byte[] toByteArray() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public int compareTo(byte[] value, int offset, int length) {
            // TODO Auto-generated method stub
            return 0;
        }

    }
}