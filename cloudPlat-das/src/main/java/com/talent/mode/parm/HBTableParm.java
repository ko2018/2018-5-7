package com.talent.mode.parm;

import org.apache.hadoop.hbase.client.Put;

import com.talent.mode.ITable;

/**
 * @author zhangqian
 * @time 2017年12月12日 下午12:07:24
 * @version 1.0v
 */
public abstract class HBTableParm {
    /** 列族 */
    protected String columnFamily;

    /** 分割split */
    protected int split;

    public HBTableParm(String columnFamily, int split) {
        this.columnFamily = columnFamily;
        this.split = split;
    }

    public abstract Put getPut();

    public abstract void setData(ITable e);

}
