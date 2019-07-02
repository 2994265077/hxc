/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ApiLogAutoConfiguration
 * Author:   YHY
 * Date:     2019/4/12 14:40
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.web.log.config;

import com.cetccity.operationcenter.webframework.web.log.consumer.ApiLogConsumer;
import com.cetccity.operationcenter.webframework.web.log.consumer.DefaultJdbcApiLogConsumer;
import com.cetccity.operationcenter.webframework.web.log.support.ApiLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/12
 * @since 1.0.0
 */
@Configuration
@Slf4j
public class ApiLogAutoConfiguration {

    @Bean
    @ConditionalOnBean({ApiLogAspect.class})
    @ConditionalOnMissingBean(ApiLogConsumer.class)
    public ApiLogConsumer defaultConsumer() {
        return new DefaultJdbcApiLogConsumer();
    }
}