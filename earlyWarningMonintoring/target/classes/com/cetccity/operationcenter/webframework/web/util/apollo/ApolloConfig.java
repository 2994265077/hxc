package com.cetccity.operationcenter.webframework.web.util.apollo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
/**
 * 
 * @author zhutongyu
 * @date 2019年6月3日
 * @desc apollo
 */
@Configuration
@EnableApolloConfig
public class ApolloConfig {
	/**
	 * application-dev.properties文件读取
	 * @return
	 */
    @Bean
    public CommonInstance getCommonInstance() {
      return new CommonInstance();
    }
	
}
