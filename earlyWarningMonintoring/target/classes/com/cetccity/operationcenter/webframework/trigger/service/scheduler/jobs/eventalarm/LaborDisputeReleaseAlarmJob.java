package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.eventalarm;

import com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass.LaborDisputeAlarmTriggerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.eventalarm
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2019/1/24 10:36
 * @Updater: huangzezhou
 * @Update_Date: 2019/1/24 10:36
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Component
@Slf4j
public class LaborDisputeReleaseAlarmJob {

    /**
     * 注入 service
     */
    @Autowired
    private LaborDisputeAlarmTriggerServiceImpl laborDisputeAlarmTriggerService;

    /**
     * 任务调度 30m执行一次
     */
    //@Scheduled(cron = "*/10 * * * * ?")  //5s执行一次
    @Scheduled(cron = "0 0/30 * * * ?")  //30m执行一次
    public void execute(){
        laborDisputeAlarmTriggerService.triggerAlarm();
    }
}
