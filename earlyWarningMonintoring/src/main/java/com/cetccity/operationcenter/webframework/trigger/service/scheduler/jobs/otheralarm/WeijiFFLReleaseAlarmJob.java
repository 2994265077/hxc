package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.otheralarm;

import com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass.WeijiFFLAlarmTriggerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.otheralarm
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:04 2019-03-27
 * Updater:     heliangming
 * Update_Date：11:04 2019-03-27
 * 更新描述:    heliangming 腹泻、发热、流感预警
 **/
@Component
@Slf4j
public class WeijiFFLReleaseAlarmJob {

    @Autowired
    WeijiFFLAlarmTriggerServiceImpl weijiFFLAlarmTriggerService;

    //@Scheduled(cron = "*/10 * * * * ?")  //5s执行一次
//    @Scheduled(cron = "0 0/30 * * * ?")  //30m执行一次
    public void execute() throws Exception {
        weijiFFLAlarmTriggerService.triggerAlarm();
    }
}
