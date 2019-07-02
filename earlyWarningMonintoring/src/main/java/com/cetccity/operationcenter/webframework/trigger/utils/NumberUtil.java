package com.cetccity.operationcenter.webframework.trigger.utils;

import java.math.BigDecimal;

/**
 * @Package: com.cetc.cloud.alarm.trigger.utils
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2019/1/3 19:28
 * @Updater: huangzezhou
 * @Update_Date: 2019/1/3 19:28
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
public class NumberUtil {


    public static String double2Str(Double d){
        return BigDecimal.valueOf(d).toString();
    }

}
