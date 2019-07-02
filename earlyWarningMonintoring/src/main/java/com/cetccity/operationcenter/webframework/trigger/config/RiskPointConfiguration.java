/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: RiskPointConfiguration
 * Author:   YHY
 * Date:     2019/5/22 10:30
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.config;

import com.cetccity.operationcenter.webframework.trigger.converter.RiskPointConverter;
import com.cetccity.operationcenter.webframework.trigger.processor.RiskPointProcessor;
import com.cetccity.operationcenter.webframework.trigger.producer.RiskPointProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/22
 * @since 1.0.0
 */
@Configuration
public class RiskPointConfiguration {
    // 风险点安全生产
    @Bean("riskPointProcessor")
    public RiskPointProcessor riskPointProcessor() {
        RiskPointProcessor riskPointProcessor = new RiskPointProcessor(riskPointProducer());
        riskPointProcessor.addConverter(riskPointConverter());
        return riskPointProcessor;
    }

    @Bean
    public RiskPointProducer riskPointProducer() {
        return new RiskPointProducer();
    }


    @Bean
    public RiskPointConverter riskPointConverter() {
        return new RiskPointConverter();
    }
}