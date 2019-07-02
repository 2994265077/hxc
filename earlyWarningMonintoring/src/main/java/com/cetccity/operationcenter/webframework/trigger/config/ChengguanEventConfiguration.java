/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ChengguanEventConfiguration
 * Author:   YHY
 * Date:     2019/5/22 10:31
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.config;

import com.cetccity.operationcenter.webframework.trigger.converter.ChengguanEventConverter;
import com.cetccity.operationcenter.webframework.trigger.processor.ChenguanEventProcessor;
import com.cetccity.operationcenter.webframework.trigger.producer.ChengguanEventProducer;
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
public class ChengguanEventConfiguration {
    // 城管 三小巡查
    @Bean("chenguanEventProcessor")
    public ChenguanEventProcessor chenguanEventProcessor() {
        ChenguanEventProcessor processor = new ChenguanEventProcessor(chengguanEventProducer());
        processor.addConverter(chengguanEventConverter());
        return processor;
    }

    @Bean
    public ChengguanEventProducer chengguanEventProducer() {
        return new ChengguanEventProducer();
    }



    @Bean
    public ChengguanEventConverter chengguanEventConverter() {
        return new ChengguanEventConverter();
    }
}