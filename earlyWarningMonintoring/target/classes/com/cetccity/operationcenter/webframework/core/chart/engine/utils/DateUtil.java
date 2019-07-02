package com.cetccity.operationcenter.webframework.core.chart.engine.utils;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Package: com.cetccity.operationcenter.webframework.core
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/26 9:37
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/26 9:37
 * @Update_Description: huangzezhou 补充
 * @Description: //时间处理工具
 **/
public class DateUtil {

    public final static String separator = "-"; //时间分隔符

    //获取最近几个月的序列，最老的时间放前面，最新的时间放后面。
    public static List<String> recentMonth(int n){
        if (n < 1) return null;
       String[] result = new String[n];

        Calendar current  = Calendar.getInstance();
        current.setTime(new Date());

        for (int i = 0; i < n; ++i){
            int year  = current.get(Calendar.YEAR);
            int month = current.get(Calendar.MONTH)+1;
            result[n - i - 1] = year + separator + month;
            current.add(Calendar.MONTH, -1);
        }
        return Arrays.asList(result);
    }

    /**
     * separate = -
     * 返回格式 年-月
     * @param date
     * @return
     */
    public static String getYearMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR)+separator+(calendar.get(Calendar.MONTH)+1);
    }


    //获取最近几天的序列，最老的时间放前面，最新的时间放后面。
    public static List<String> recentDay(int n){
        if (n < 1) return null;
        String[] result = new String[n];

        Calendar current  = Calendar.getInstance();
        current.setTime(new Date());

        for (int i = 0; i < n; ++i){
            int year  = current.get(Calendar.YEAR);
            int month = current.get(Calendar.MONTH)+1;
            int day = current.get(Calendar.DAY_OF_MONTH);
            result[n - i - 1] = year + separator + month + separator +day;
            current.add(Calendar.DAY_OF_MONTH, -1);
        }
        return Arrays.asList(result);
    }

    /**
     * separate = -
     * 返回格式 年-月-日
     * @param date
     * @return
     */
    public static String getYearMonthDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR)+separator+(calendar.get(Calendar.MONTH)+1) + separator + calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date format(String date1) throws ParseException {
        if (date1 == null)
            return null;
        if (date1.matches("-") && date1.matches(":")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(date1);
        }
        return null;
    }

}
