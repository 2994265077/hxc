package com.cetc.cloud.datasynch.provider.util;

/**
 * @Package: com.cetc.cloud.alarm.trigger.utils
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/19 14:55
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/19 14:55
 * @Update_Description: huangzezhou 补充
 * @Description: //测试工具，用来计算程序运行时间
 **/
public class SpendTimeUtil {

    public static String hhMMss(long millis){
        long second = millis/1000;
        return second/3600 + " h" + second/60%60 +" m" + second%60 +" s";
    }

}
