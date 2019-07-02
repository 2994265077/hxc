///**
// * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
// * FileName: CacheConfig
// * Author:   YHY
// * Date:     2019/4/11 14:47
// * Description:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.cetccity.operationcenter.webframework.web.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.caffeine.CaffeineCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import lombok.Data;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈〉
// *
// * @author yhy
// * @create 2019/4/11
// * @since 1.0.0
// */
//@Configuration
//@ConfigurationProperties(prefix = "cache.guava")
//@Data
//public class CacheConfig {
//
//    private Integer expire = 300;
//    private String type = "access";
//
//    @Bean
//    public CacheManager cacheManager(){
///*        GuavaCacheManager cacheManager = new GuavaCacheManager();
//        if (0 < expire) {
//            if (Objects.nonNull(type) && "write".equals(type.toLowerCase())) {
//                cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(expire, TimeUnit.SECONDS));
//            } else {
//                cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(expire, TimeUnit.SECONDS));
//            }
//        }
//*/
//        return new CaffeineCacheManager();
//    }
//}