/**
 *
 * Created by HZZ on 2017/11/14.
 *
 */
package com.justplay1994.github.db2es.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 工程包名:    com.cetc.cloud.command.provider.config
 * 项目名称:    db-statement-plugin
 * 创建描述:    zhangliang 补充
 * Creator:     zhangliang
 * Create_Date: 2017/10/10
 * Updater:     zhangliang
 * Update_Date：2017/10/10
 * 更新描述:    zhangliang 补充
 **/
@Configuration
//@MapperScan(basePackages = "com.cetccity.operationcenter.webframework.web.dao", sqlSessionTemplateRef  = "sqlSessionTemplatePrimary")
public class PrimaryHiKariDataSourceConfig {

    @Bean(name = "DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        DataSource dataSource = DataSourceBuilder.create().build();
        System.out.println(dataSource);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean()throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(primaryDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:dao/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(primaryDataSource());
    }
}
