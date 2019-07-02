package com.cetccity.operationcenter.webframework.unifiedauth.config;

import java.util.HashSet;
import java.util.Set;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.config
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/6 10:41
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/6 10:41
 * @Update_Description: huangzezhou 补充
 **/
public interface AuthorizationFilterConfig {
    //不需要拦截的URL
    Set<String> exceptUrls = new HashSet<String>(){{
        add("/*");    //根目录
        add("/unifiedauth/authentication/**");//认证接口
        // 验证码
        add("/code/image");
        // 物联网
        add("/usp/**");
        /**
         * swagger相关url
         */
        add("/document.html");
        /**
         * 综治ask\login
         */
        add("/oauth2ew/servlet/oauht");
        add("/oauth2ew/zhongzhi/login");

        add("/v2/api-docs");
        add("/webjars/**");
        add("/swagger-resources/**");
        add("//swagger-mg-ui/**");

        add("/document.html");
        add("/static/**");
        add("/xml/**");
    }};


    //需要做权限校验的url
    Set<String> permittUrls = new HashSet<String>(){{
        add("/hiddendanger/**");
        add("/rundisplay/**");
        add("/urbansign/**");
        add("/alarmcenter/**");
        add("/publicHealth/**");
        add("/search/**");
        add("/tableau/**");
/*        add("/admin/**");
*/        add("/environment/**");
    }};
}
