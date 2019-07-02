/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: LoginCacheConfiguration
 * Author:   YHY
 * Date:     2019/4/22 14:30
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.unifiedauth.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/22
 * @since 1.0.0
 */
@Configuration
public class LoginCacheConfiguration {

    @Bean("codeCache")
    @ConditionalOnMissingBean(name = "codeCache")
    public Cache<String, Object> cacheManagerWithCacheLoading(){
        Cache<String, Object> manualCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        return manualCache;
    }
    
    private static List<String> getNames(){
        List<String> names = new ArrayList<>(2);
        names.add("outLimit");
        names.add("notOutLimit");
        return names;
    }
}