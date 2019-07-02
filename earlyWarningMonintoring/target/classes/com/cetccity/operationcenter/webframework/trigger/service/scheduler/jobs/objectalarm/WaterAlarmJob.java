package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.objectalarm;

import com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass.WaterAlarmTriggerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.service.jobs
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2019/1/7 16:41
 * @Updater: huangzezhou
 * @Update_Date: 2019/1/7 16:41
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Component
@Slf4j
public class WaterAlarmJob {

    /**
     * 注入 service
     */
    @Autowired
    private WaterAlarmTriggerServiceImpl waterAlarmTriggerService;

    /**
     * 任务调度 30m执行一次
     */
    //@Scheduled(cron = "*/5 * * * * ?")  //5s执行一次
    @Scheduled(cron = "0 0/30 * * * ?")  //30m执行一次
    public void execute() {
        waterAlarmTriggerService.schedule();
    }
}
