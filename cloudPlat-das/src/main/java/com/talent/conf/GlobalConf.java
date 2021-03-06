package com.talent.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.core.table.DBTable;

/** ȫ�������ļ� */
public class GlobalConf {
    private static Logger logger = LoggerFactory.getLogger(GlobalConf.class.getName());

    public static void load(String[] args) {
        try {
            DBTable.loadingConf();
            logger.info("conf load ok!!!");
        } catch (Exception e) {

            logger.error(e.getMessage());
            System.exit(0);
        }

    }

}
