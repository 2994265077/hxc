package com.cetccity.operationcenter.webframework.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @author ZHUTONGYU
 * Description: 福田OA数据源配置
 * 2019年6月6日
 *
 */
@Configuration
@MapperScan(basePackages = "com.cetccity.operationcenter.webframework.oa.mapper", sqlSessionFactoryRef = "oasqlSessionFactory")
public class MybatisOaConfig {

}
