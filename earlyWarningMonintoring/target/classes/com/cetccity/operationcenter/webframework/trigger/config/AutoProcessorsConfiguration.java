/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: IotConverterConfiguration
 * Author:   YHY
 * Date:     2019/5/14 17:22
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.config;

import com.cetccity.operationcenter.webframework.trigger.common.Enums;
import com.cetccity.operationcenter.webframework.trigger.processor.auto.AlarmAutoCancelProcessor;
import com.cetccity.operationcenter.webframework.trigger.processor.auto.AlarmAutoSendProcessor;
import com.cetccity.operationcenter.webframework.trigger.processor.auto.RiskPointAutoSendProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/14
 * @since 1.0.0
 */
@Configuration
public class AutoProcessorsConfiguration {

    // 延时触发 和 延时取消
    @Bean
    public AlarmAutoCancelProcessor alarmAutoCancelProcessor() {
        return new AlarmAutoCancelProcessor(Enums.AlarmAutoProcessTypeEnum.AUTO_CANCEL);
    }

    @Bean
    public AlarmAutoSendProcessor alarmAutoSendProcessor() {
        return new AlarmAutoSendProcessor(Enums.AlarmAutoProcessTypeEnum.AUTO_SEND);
    }

    @Bean
    public RiskPointAutoSendProcessor riskPointAutoSendProcessor() {
        return new RiskPointAutoSendProcessor();
    }

}