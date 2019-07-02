package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.task;

import com.cetccity.operationcenter.webframework.trigger.service.impl.HistoryAlarmServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 每天计算当天正在预警中的每类（lv2）预警数量
 * ALARM_STATISTIC
 */
@Component
@Slf4j
public class HistoryAlarmJob {

    @Autowired
    HistoryAlarmServiceImpl historyAlarmServiceImpl;

    /**
     * 每天凌晨1点执行一次
     */
    @Scheduled(cron="0 0 23 * * ? ")   //每天凌晨1点执行一次
    public void execute(){
        historyAlarmServiceImpl.schedule();
    }
}
