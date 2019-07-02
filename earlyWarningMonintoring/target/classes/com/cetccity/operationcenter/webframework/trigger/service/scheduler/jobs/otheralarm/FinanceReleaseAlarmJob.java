package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.otheralarm;

import com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass.FinanceAlarmTriggerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 工程包名:   com.cetc.cloud.alarm.trigger.service.scheduler.jobs.eventalarm
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming --金融报警005003--更新时间长（数天）
 * Creator:     heliangming
 * Create_Date: 15:38 2019-03-11
 * Updater:     heliangming
 * Update_Date：15:38 2019-03-11
 * 更新描述:    heliangming 金融预警
 **/
@Component
@Slf4j
public class FinanceReleaseAlarmJob {

    @Autowired
    FinanceAlarmTriggerServiceImpl service;

    //@Scheduled(cron = "*/10 * * * * ?")  //10s执行一次
    @Scheduled(cron = "0 0/30 * * * ?")  //30m执行一次
    public void execute(){
        service.triggerAlarm();
    }
}
