/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: IotProcessorConfiguration
 * Author:   YHY
 * Date:     2019/5/22 10:25
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.config;

import com.cetccity.operationcenter.webframework.trigger.converter.iot.DangerAirConverter;
import com.cetccity.operationcenter.webframework.trigger.converter.iot.ElectricalFireConverter;
import com.cetccity.operationcenter.webframework.trigger.converter.iot.GeologyConverter;
import com.cetccity.operationcenter.webframework.trigger.converter.iot.HydrantPressureConverter;
import com.cetccity.operationcenter.webframework.trigger.converter.iot.SmokeDetectorConverter;
import com.cetccity.operationcenter.webframework.trigger.converter.iot.WaterLoggingConverter;
import com.cetccity.operationcenter.webframework.trigger.processor.IotEventProcessor;
import com.cetccity.operationcenter.webframework.trigger.producer.IotEventProducer;
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
public class IotEventConfiguration {


    @Bean("iotEventProcessor")
    public IotEventProcessor iotEventProcessor() {
        IotEventProcessor iotEventProcessor = new IotEventProcessor(iotProducer());
        iotEventProcessor.addConverter(waterLoggingConverter());
        iotEventProcessor.addConverter(geologyConverter());
        iotEventProcessor.addConverter(dangerAirConverter());
        iotEventProcessor.addConverter(smokeDetectorConverter());
        iotEventProcessor.addConverter(hydrantPressureConverter());
        iotEventProcessor.addConverter(electricalFireConverter());
        return iotEventProcessor;
    }

    @Bean
    public IotEventProducer iotProducer() {
        return new IotEventProducer();
    }

    @Bean
    public WaterLoggingConverter waterLoggingConverter() {
        return new WaterLoggingConverter();
    }

    @Bean
    public GeologyConverter geologyConverter() {
        return new GeologyConverter();
    }

    @Bean
    public DangerAirConverter dangerAirConverter() {
        return new DangerAirConverter();
    }

    @Bean
    public SmokeDetectorConverter smokeDetectorConverter() {
        return new SmokeDetectorConverter();
    }

    @Bean
    public HydrantPressureConverter hydrantPressureConverter() {
        return new HydrantPressureConverter();
    }

    @Bean
    public ElectricalFireConverter electricalFireConverter() {
        return new ElectricalFireConverter();
    }

}