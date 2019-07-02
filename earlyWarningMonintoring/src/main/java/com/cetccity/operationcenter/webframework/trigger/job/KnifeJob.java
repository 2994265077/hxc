/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: KnifeJob
 * Author:   YHY
 * Date:     2019/5/30 10:44
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.job;

import com.cetccity.operationcenter.webframework.trigger.core.processor.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/30
 * @since 1.0.0
 */
@Component
public class KnifeJob {

    @Autowired
    @Qualifier("knifeProcessor")
    public Processor process;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void doJob() {
        process.process();
    }

}