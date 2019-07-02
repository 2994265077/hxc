package com.cetccity.operationcenter.webframework.trigger.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Package: com.cetc.cloud.alarm.trigger.utils
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/21 17:14
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/21 17:14
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
public class DateUtil {
    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        return Date.from(zdt.toInstant());
    }

    /**
     * String 转 date类型
     */

    public static Date str2Date(String str) throws ParseException {
        String[] pattern = new String[]{"yyyy-MM", "yyyyMM", "yyyy/MM",
                "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
                "yyyyMMddHHmmss",
                "yyyy-MM-ddHH:mm:ss",
                "yyyy/MM/ddHH:mm:ss",
        };
        return DateUtils.parseDate(str, pattern);
    }



    public static String date2Str(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    public static String spendTime(long startTime, long endTime){
        long spend = endTime - startTime;
        long second = spend/1000%60;
        long minite = spend/1000/60%60;
        return minite+"m"+second+"s";
    }

}
