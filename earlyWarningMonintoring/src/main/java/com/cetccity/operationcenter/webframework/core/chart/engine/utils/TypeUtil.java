package com.cetccity.operationcenter.webframework.core.chart.engine.utils;

/**
 * @Package: com.cetccity.operationcenter.webframework.core.chart.engine
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/28 10:16
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/28 10:16
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
public class TypeUtil {

    public static int toInt(Object o){
        if (o == null || "".equals(String.valueOf(o))) return 0;
        return Integer.parseInt(String.valueOf(o));
    }
}
