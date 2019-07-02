/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: SafeProduceTriggerJob
 * Author:   YHY
 * Date:     2019/5/8 17:07
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.eventalarm;

import com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass.SafeProduceTriggerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/8
 * @since 1.0.0
 */
@Component
public class SafeProduceTriggerJob {
    @Autowired
    private SafeProduceTriggerServiceImpl safeProduceTriggerService;

//    @Scheduled(cron = "0 0 0 * * ?")
    public void doJob() {
        safeProduceTriggerService.triggerAlarm();
    }
}