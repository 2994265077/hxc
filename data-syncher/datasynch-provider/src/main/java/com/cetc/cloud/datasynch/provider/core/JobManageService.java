package com.cetc.cloud.datasynch.provider.core;

import com.cetc.cloud.datasynch.api.model.DsScheduleModel;
import com.cetc.cloud.datasynch.provider.template.impl.DsScheduleRunnableTemplate;
import com.cetc.cloud.datasynch.provider.template.OuterJobRunnableTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

/**
 * Description：定时任务管理器
 * Created by luolinjie on 2018/10/9.
 */
@Service("jobManageService")
@Slf4j
public class JobManageService {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private Map<String, Future> futures = new ConcurrentHashMap<String, Future>();

    public Map<String, Future> getRunningFutures() {
        return futures;
    }

    static boolean isinitialized = false;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(16);
        threadPoolTaskScheduler.setThreadNamePrefix("DataSyncher--");
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        /**需要实例化线程*/
        threadPoolTaskScheduler.initialize();
        isinitialized = true;
        return threadPoolTaskScheduler;
    }

    /**
     * 根据传入的jobId和run方法的执行体创建内容
     */
    public int startScheduledJob(DsScheduleModel dsScheduleModel) {

        DsScheduleRunnableTemplate runnableInstance;
        if (dsScheduleModel.getSrcDs() == 0) {
            //创建定时任务
            runnableInstance = new DsScheduleRunnableTemplate(dsScheduleModel);
        } else {
            runnableInstance = new DsScheduleRunnableTemplate(dsScheduleModel);
        }

        String cron = dsScheduleModel.getCronExpression();

        if (null == cron) {
            try {
                ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(runnableInstance, new Date());
                /**将定时任务记录在内存中，供其他功能查询*/
                futures.put(dsScheduleModel.getId() + "-" + dsScheduleModel.getTargetTableName(), future);
                log.info("\n【job:" + dsScheduleModel.getId() + "--started!");
                log.info("cron:" + dsScheduleModel.getCronExpression());
                log.info("source:" + dsScheduleModel.getSource());
                log.info("target:" + dsScheduleModel.getTargetTableName() + "】");
                return dsScheduleModel.getId();
            } catch (Exception e) {
                log.info("job:" + dsScheduleModel.getId() + "--started error! please check your cron expression!");
                return -1;
            }
        } else {
            try {
                //创建定时任务并启动
                ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(runnableInstance, new CronTrigger(cron));

                /**将定时任务记录在内存中，供其他功能查询*/
                futures.put(dsScheduleModel.getId() + "-" + dsScheduleModel.getTargetTableName(), future);
                log.info("\n【job:" + dsScheduleModel.getId() + "--started!");
                log.info("cron:" + dsScheduleModel.getCronExpression());
                log.info("source:" + dsScheduleModel.getSource());
                log.info("target:" + dsScheduleModel.getTargetTableName() + "】");
                return dsScheduleModel.getId();
            } catch (Exception e) {
                log.info("job:" + dsScheduleModel.getId() + "--started error! please check your cron expression!");
                return -1;
            }
        }
    }

    public boolean startOuterScheduledJob(String jobName,OuterJobRunnableTemplate runnableInstance, CronTrigger trigger) {
        try {
            //创建定时任务并启动
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(runnableInstance, trigger);
            futures.put("jobName:" + jobName, future);
            /**将定时任务记录在内存中，供其他功能查询*/
            return true;
        } catch (Exception e) {
            log.info("job:" + "--started error! please check your cron expression!");
            return false;
        }
    }

    /**
     * 启动一次性任务
     */
    public int startOnceJob(DsScheduleModel dsScheduleModel) {
        DsScheduleRunnableTemplate runnableInstance;
        //创建定时任务
        if (dsScheduleModel.getSrcDs() == 0) {
            runnableInstance = new DsScheduleRunnableTemplate(dsScheduleModel);
        } else {
            runnableInstance = new DsScheduleRunnableTemplate(dsScheduleModel);
        }

        try {
            //创建定时任务并启动
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(runnableInstance, new Date());
            /**将定时任务记录在内存中，供其他功能查询*/
            futures.put(dsScheduleModel.getId() + "-" + dsScheduleModel.getTargetTableName(), future);
            log.info("job:" + dsScheduleModel.getId() + "--started!");
            log.info("cron: Trigger Now");
            log.info("source:" + dsScheduleModel.getSource());
            log.info("target:" + dsScheduleModel.getTargetTableName());
            return dsScheduleModel.getId();
        } catch (Exception e) {
            log.info("job:" + dsScheduleModel.getId() + "--started error! please check your cron expression!");
            return -1;
        }
    }

    /**
     * 通过jobID取消定时任务
     *
     * @param jobID stop:1:正常停止
     *              0：还在运行
     *              -1：异常
     */
    public int removeJob(int jobID) {
        if (threadPoolTaskScheduler != null) {
            ScheduledFuture<?> future = (ScheduledFuture<?>) futures.get(jobID);
            if (!future.isDone()) {
                return 0;
            } else {
                boolean cancel = future.cancel(true);
                if (cancel) {
                    log.info("job:" + jobID + "--stopped!");
                    futures.remove(jobID);
                    return 1;
                } else {
                    log.info("job:" + jobID + "--stopping job occurs error!");
                    return -1;
                }
            }
        }
        return -1;
    }

    //

    /**
     * 通过jobID修改定时规则并重新启动（需要重新传入运行实体）
     *
     * @param jobID            -- 任务标识符
     * @param cron             -- cron表达式
     * @param runnableInstance -- 带有run方法的执行实体
     *                         cron表达式： "\*"/"10 * * * * *
     */
    public String changeJob(int jobID, String cron, Runnable runnableInstance) {
        removeJob(jobID);// 先停止，再开启
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(runnableInstance, new CronTrigger(cron));
        System.out.println("DynamicTask.startCron10()");
        return "changeCron10";
    }

}
