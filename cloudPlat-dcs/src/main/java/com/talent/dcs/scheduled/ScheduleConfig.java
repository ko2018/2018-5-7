/**
 * Project Name:cloudPlat-dcs
 * File Name:ScheduleConfig.java
 * Package Name:com.talent.dcs.scheduled
 * Date:2018年2月4日下午4:24:51
 * Copyright (c) 2018, curefun.com All Rights Reserved.
 *
*/

package com.talent.dcs.scheduled;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Description: <br/>
 * Date: 2018年2月4日 下午4:24:51 <br/>
 * 
 * @author fwp
 * @version
 * @see
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}
