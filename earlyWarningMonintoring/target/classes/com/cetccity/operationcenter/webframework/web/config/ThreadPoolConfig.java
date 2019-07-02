/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: ThreadPoolConfig
 * Author:   YHY
 * Date:     2019/4/16 17:04
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/16
 * @since 1.0.0
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(32);
        threadPoolTaskExecutor.setQueueCapacity(10000);
        threadPoolTaskExecutor.setCorePoolSize(16);
        threadPoolTaskExecutor.setKeepAliveSeconds(10);
        return threadPoolTaskExecutor;
    }
}