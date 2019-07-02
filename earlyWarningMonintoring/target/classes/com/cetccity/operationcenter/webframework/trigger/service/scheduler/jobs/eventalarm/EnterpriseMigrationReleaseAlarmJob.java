package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.eventalarm;

import com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass.EnterpriseMigrationAlarmTriggerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Package: com.cetc.cloud.alarm.trigger.service.scheduler.jobs.eventalarm
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2019/1/24 10:34
 * @Updater: huangzezhou
 * @Update_Date: 2019/1/24 10:34
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Component
@Slf4j
public class EnterpriseMigrationReleaseAlarmJob {

    /**
     * 注入 service
     */
    @Autowired
    private EnterpriseMigrationAlarmTriggerServiceImpl enterpriseMigrationAlarmTriggerService;

    /**
     * 任务调度 30m执行一次
     */
    //@Scheduled(cron = "*/10 * * * * ?")  //5s执行一次
    @Scheduled(cron = "0 0/30 * * * ?")  //30m执行一次
    public void execute() {
        enterpriseMigrationAlarmTriggerService.triggerAlarm();
    }
}
