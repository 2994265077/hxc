/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: TokenManagerAutoConfiguration
 * Author:   YHY
 * Date:     2019/4/19 14:11
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.unifiedauth.config;

import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.JwtServiceImpl;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.DefaultNoCacheTokenManager;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.TokenManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/19
 * @since 1.0.0
 */

@Configuration
public class TokenManagerAutoConfiguration {

    @ConditionalOnMissingBean(TokenManager.class)
    @ConditionalOnBean(JwtServiceImpl.class)
    @Bean
    public TokenManager defaultGuavaTokenManager() {
        return new DefaultNoCacheTokenManager();
    }

}