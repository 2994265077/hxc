/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: BuildScoreAvgJob
 * Author:   YHY
 * Date:     2019/4/3 15:20
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.task;

import com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass.BuildScoreMonthAvgTriggerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/3
 * @since 1.0.0
 */
@Component
@Slf4j
public class BuildScoreAvgJob {

    @Autowired
    private BuildScoreMonthAvgTriggerServiceImpl service;

    // 每月1号执行一次
    @Scheduled(cron = "0 0 0 1 * ? ")
    public void execute() {
        log.info("建筑风险分数平均值每月统计开始执行");
        long start = System.currentTimeMillis();
        service.triggerAlarm();
        long end = System.currentTimeMillis();
        log.info("建筑风险分数平均值每月统计执行完成，共耗时：{}ms", start - end);
    }
}