package com.cetccity.operationcenter.webframework.unifiedauth.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.utils
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/7 15:52
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/7 15:52
 * @Update_Description: huangzezhou 补充
 **/
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ApplicationContextUtil.applicationContext == null) {
            ApplicationContextUtil.applicationContext = applicationContext;
        }
    }
}
