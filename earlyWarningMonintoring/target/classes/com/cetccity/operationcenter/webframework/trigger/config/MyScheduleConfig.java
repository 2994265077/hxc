package com.cetccity.operationcenter.webframework.trigger.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.core.scheduled
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:55 2019-03-08
 * Updater:     heliangming
 * Update_Date：17:55 2019-03-08
 * 更新描述:    定时器默认是单线程的，也就是说，如果有多个定时器的话，就会存在冲突以及延时，所以这个时候需要给任务调度配置线程池
 **/

@Configuration
@EnableScheduling
@ConditionalOnProperty("trigger.enable")
public class MyScheduleConfig implements SchedulingConfigurer {

    //配置任务调度定时器线程池
    @Override
    public void configureTasks(ScheduledTaskRegistrar task) {
        task.setScheduler(taskExecutor());
    }
    //Java中的线程池类有两个，分别是：ThreadPoolExecutor和ScheduledThreadPoolExecutor，这两个类都继承自ExecutorService

    @Bean
    public Executor taskExecutor(){
        return Executors.newScheduledThreadPool(8); //newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
    }

    /*@Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService taskExecutor(){
        return new ScheduledThreadPoolExecutor(100);
    }*/
}
