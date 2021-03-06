package com.talent;

import java.lang.management.ManagementFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.conf.GlobalConf;
import com.talent.core.global.GlobalUpdate;

/***
 * 项目入口函数
 * 
 * @author dell
 *
 */
public class MainEnter {
    protected static Logger logger = LoggerFactory.getLogger("MainEnter");

    public static void main(String[] args) {
        try {
            String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            logger.info("starting ....pid=" + pid);
            GlobalConf.load(args);

            if (args.length < 1) {
                logger.error("starting ....args=" + args);
                return;
            }

            System.out.println(pid);
            System.out.println(args[0]);

            GlobalUpdate.update(args[0]);
            logger.info("end ....pid=" + pid);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}
