package com.cetccity.operationcenter.webframework;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.cetccity.operationcenter.webframework.backstage.log.util.EnableLogging;

@EnableLogging
@SpringBootApplication
@EnableCaching(proxyTargetClass=true)
@EnableTransactionManagement //开启事务
@EnableConfigurationProperties
@EnableAspectJAutoProxy(exposeProxy = true) //开启AOP，暴露动态代理


public class ApplicationEarlyWarningMonitoring extends SpringBootServletInitializer implements WebMvcConfigurer {
   
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationEarlyWarningMonitoring.class);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationEarlyWarningMonitoring.class).run(args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException { }
    
    /**
     * SpringBoot1.x访问静态文件只需要把文件放入static中即可，在SpringBoot2中需要配置addResourceHandlers
     * 
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
       registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}

