package com.cetccity.operationcenter.webframework.web.config;
/**********************************************************************
 * Copyright (c) 2019 CETC Company
 * 中电科新型智慧城市研究院有限公司版权所有
 * <p>
 * PROPRIETARY RIGHTS of CETC Company are involved in the
 * subject matter of this material. All manufacturing, reproduction, use,
 * and sales rights pertaining to this subject matter are governed by the
 * license agreement. The recipient of this software implicitly accepts
 * the terms of the license.
 * 本软件文档资料是中电科新型智慧城市研究院有限公司的资产，任何人士阅读和
 * 使用本资料必须获得相应的书面授权，承担保密责任和接受相应的法律约束。
 *************************************************************************/

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 
 * @author ZHUTONGYU
 * Description: 连接福田OA数据源
 * 2019年6月6日
 *
 */

@Configuration
public class OaHikariDataSourceConfig {

    @Bean(name = "oa")
    @ConfigurationProperties(prefix = "spring.oa")
    @Qualifier("oa")
    public DataSource oaDataDource() {
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean(name = "oaJdbcTemplate")
    @Qualifier("oa")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("datasource")DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    @Bean(name="oasqlSessionFactory")
    @Qualifier("oa")
    public SqlSessionFactory oasqlSessionFactory() throws Exception {
        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);//-自动使用驼峰命名属性映射字段   userId    user_id
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(oaDataDource());
        return factoryBean.getObject();
    }


    @Bean(name="oasqlSessionTemplate")
    @Qualifier("oa")
    public SqlSessionTemplate sqlSessionTemplatePrimary() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(oasqlSessionFactory());
        return template;
    }


    @Bean(name = "oaTransactionManager")
    @Qualifier("oa")
    public DataSourceTransactionManager oaTransactionManager(@Qualifier("oa") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
