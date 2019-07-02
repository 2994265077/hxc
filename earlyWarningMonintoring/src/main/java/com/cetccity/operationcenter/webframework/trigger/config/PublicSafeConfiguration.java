/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: PublicSafeConfiguration
 * Author:   YHY
 * Date:     2019/5/22 16:12
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.config;

import com.cetccity.operationcenter.webframework.trigger.converter.DiarrheaConverter;
import com.cetccity.operationcenter.webframework.trigger.converter.FeverConverter;
import com.cetccity.operationcenter.webframework.trigger.converter.InfluenzaConverter;
import com.cetccity.operationcenter.webframework.trigger.converter.KnifeConverter;
import com.cetccity.operationcenter.webframework.trigger.processor.KnifeProcessor;
import com.cetccity.operationcenter.webframework.trigger.processor.WeijiAggraProcessor;
import com.cetccity.operationcenter.webframework.trigger.producer.weiji.KnifeProducer;
import com.cetccity.operationcenter.webframework.trigger.producer.weiji.WeijiAggraProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br> 
 * 〈公共安全配置〉
 *
 * @author yhy
 * @create 2019/5/22
 * @since 1.0.0
 */
@Configuration
public class PublicSafeConfiguration {

    @Bean("knifeProcessor")
    public KnifeProcessor knifeProcessor() {
        KnifeProcessor knifeProcessor = new KnifeProcessor(knifeProducer());
        knifeProcessor.addConverter(knifeConverter());
        return knifeProcessor;
    }

    @Bean
    public KnifeProducer knifeProducer() {
        return new KnifeProducer();
    }

    @Bean
    public KnifeConverter knifeConverter() {
        return new KnifeConverter();
    }

    @Bean("weijiAggraProcessor")
    public WeijiAggraProcessor weijiAggraProcessor() {
        WeijiAggraProcessor weijiAggraProcessor = new WeijiAggraProcessor(weijiAggraProducer());
        weijiAggraProcessor.addConverter(diarrheaConverter());
        weijiAggraProcessor.addConverter(feverConverter());
        weijiAggraProcessor.addConverter(influenzaConverter());
        return weijiAggraProcessor;
    }

    @Bean
    public DiarrheaConverter diarrheaConverter() {
        return new DiarrheaConverter();
    }

    @Bean
    public FeverConverter feverConverter() {
        return new FeverConverter();
    }

    @Bean
    public InfluenzaConverter influenzaConverter() {
        return new InfluenzaConverter();
    }

    @Bean
    public WeijiAggraProducer weijiAggraProducer() {
        return new WeijiAggraProducer();
    }

}