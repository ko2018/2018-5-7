package com.talent.core;

import com.talent.core.table.HBaseTable;

/** 过期废弃掉 */
public class ServerStart {

    public static void main(String[] args) throws Exception {
        /** 创建hbase表 */
        HBaseTable.createTables();

    }

}
