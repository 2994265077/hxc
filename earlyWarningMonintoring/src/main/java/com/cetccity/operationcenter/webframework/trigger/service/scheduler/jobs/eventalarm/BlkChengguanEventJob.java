/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: BlkChengguanEventJob
 * Author:   YHY
 * Date:     2019/5/13 10:28
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.eventalarm;

import com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass.BlkChengguanEventTriggerService;
import com.cetccity.operationcenter.webframework.trigger.processor.IotEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/13
 * @since 1.0.0
 */
@Component
public class BlkChengguanEventJob {

    @Autowired
    private BlkChengguanEventTriggerService blkChengguanEventTriggerService;
    @Autowired
    private IotEventProcessor waterLoggingTriggerService;
//    @Scheduled(cron = "0 0/15 * * * ?")
    public void doJob() {
        blkChengguanEventTriggerService.trigger();
    }
}