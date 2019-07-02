package com.cetccity.operationcenter.webframework.unifiedauth.config;

import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.MyAuthorizingRealm;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.config
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/14 10:02
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/14 10:02
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Configuration
public class SecurityManagerConfig {

    @Autowired
    MyAuthorizingRealm myAuthorizingRealm;

    @Bean
    public SecurityManager securityManager(){
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(myAuthorizingRealm);
        return defaultSecurityManager;
    }

}
