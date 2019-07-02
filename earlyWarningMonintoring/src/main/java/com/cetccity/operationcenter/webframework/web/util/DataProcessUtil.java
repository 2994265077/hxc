package com.cetccity.operationcenter.webframework.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description：数据加工工具
 * Created by luolinjie on 2018/7/19.
 */
public class DataProcessUtil {

    /**
     * 填充不连续的日期
     * @param importData       被处理data必须是List<HashMap>类型
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param resKeyName    输出key的名称
     * @param resValueName  输出value的名称
     * @return
     * @throws ParseException
     */
    public static List<HashMap> fillOtherDay(List<HashMap> importData, String startTime, String endTime, String resKeyName, String resValueName) throws ParseException {
        ArrayList<HashMap> resList = new ArrayList<HashMap>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //计算结果集数量
        Calendar startInstance = Calendar.getInstance();
        startInstance.setTime(formatter.parse(startTime));

        Calendar endInstance = Calendar.getInstance();
        endInstance.setTime(formatter.parse(endTime));
        long dayNum = (endInstance.getTimeInMillis() - startInstance.getTimeInMillis()) / (24 * 3600 * 1000);


        for (int i = 0; i <= dayNum; i++) {
            boolean ifExists = false;
            //遍历判断当前遍历的日期，是否有结果集中匹配的数据
            String formmatedStr = formatter.format(startInstance.getTime());
            for (int j = 0; j < importData.size(); j++) {
                //如果结果集存在对应日期的数据，则拼接对应数值，如果不存在，则置零，拼接
                if (formmatedStr.equals(importData.get(j).get(resKeyName))) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(resKeyName, formmatedStr);
                    hashMap.put(resValueName, importData.get(j).get(resValueName));
                    resList.add(hashMap);
                    ifExists = true;
                    break;
                }
            }
            if (ifExists == false) {
                HashMap hashMap = new HashMap();
                hashMap.put(resKeyName, formmatedStr);
                hashMap.put(resValueName, 0);
                resList.add(hashMap);
            }
            startInstance.add(Calendar.DAY_OF_MONTH, 1);
        }
        return resList;
    }

    /**
     * 填充不连续的日期-自动获取起始日期，并填充
     * @param importData    要被处理的数据
     * @param dimention     维度 ：year：年  month：月  day：日
     * @param resKeyName    结果集key的key名称
     * @param resValueName  结果集value的key名称
     * @return
     * @throws ParseException
     */
    public static List<HashMap> fillOtherDay(List<HashMap> importData, SimpleDateFormat formatter,String dimention,String srcKeyName, String srcValueName,String resKeyName, String resValueName) throws ParseException {
        ArrayList<HashMap> resList = new ArrayList<HashMap>();

        //处理对象大小判断：list大小必须>=3
        if (!(importData.size()>=3)){
            return importData;
        }

        //todo: 获取最小时间
        String min_time = (String) importData.get(0).get(srcKeyName);
        //todo: 获取最大时间
        String max_time = (String) importData.get(importData.size()-1).get(srcKeyName);

        //计算结果集数量
        Calendar startInstance = Calendar.getInstance();
        startInstance.setTime(formatter.parse(min_time));

        Calendar endInstance = Calendar.getInstance();
        endInstance.setTime(formatter.parse(max_time));
        //维度--日
        long dayNum = (endInstance.getTimeInMillis() - startInstance.getTimeInMillis()) / (24 * 3600 * 1000);
        long monthNum = (endInstance.getTimeInMillis() - startInstance.getTimeInMillis()) / (30 * 24 * 3600 * 1000);
        long yearNum = (endInstance.getTimeInMillis() - startInstance.getTimeInMillis()) / (365 * 24 * 3600 * 1000);

        long num =0;
        if ("day".equals(dimention)){
            num = dayNum;
        }else if ("month".equals(dimention)){
            num = monthNum;
        }else if ("year".equals(dimention)){
            num = yearNum;
        }

        for (int i = 0; i <= num; i++) {
            boolean ifExists = false;
            //遍历判断当前遍历的日期，是否有结果集中匹配的数据
            String formmatedStr = formatter.format(startInstance.getTime());
            for (int j = 0; j < importData.size(); j++) {
                //如果结果集存在对应日期的数据，则拼接对应数值，如果不存在，则置零，拼接
                if (formmatedStr.equals(importData.get(j).get(resKeyName))) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(resKeyName, formmatedStr);
                    hashMap.put(resValueName, importData.get(j).get(resValueName));
                    resList.add(hashMap);
                    ifExists = true;
                    break;
                }
            }
            if (ifExists == false) {
                HashMap hashMap = new HashMap();
                hashMap.put(resKeyName, formmatedStr);
                hashMap.put(resValueName, 0);
                resList.add(hashMap);
            }
            if ("day".equals(dimention)){
                startInstance.add(Calendar.DAY_OF_MONTH, 1);
            }
            //维度--月
            if ("month".equals(dimention)){
                startInstance.add(Calendar.MONTH, 1);
            }
            //维度--年
            if ("year".equals(dimention)){
                startInstance.add(Calendar.YEAR, 1);
            }

        }

        return resList;
    }
    public static List<HashMap> fillOtherHour(List<HashMap> resData, String startTime, String endTime, String resKeyName, String resValueName) throws ParseException {
        ArrayList<HashMap> resList = new ArrayList<HashMap>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar startInstance = Calendar.getInstance();
        startInstance.setTime(formatter.parse(startTime));

        //计算结果集数量
        for (int i = 0; i < 24; i++) {
            boolean ifExists = false;
            //遍历判断当前遍历的日期，是否有结果集中匹配的数据
            String formmatedStr = formatter2.format(startInstance.getTime());
            for (int j = 0; j < resData.size(); j++) {
                //如果结果集存在对应日期的数据，则拼接对应数值，如果不存在，则置零，拼接
                if (formmatedStr.equals(resData.get(j).get(resKeyName))) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(resKeyName, formmatedStr.substring(11, 13));
                    hashMap.put(resValueName, resData.get(j).get(resValueName));
                    resList.add(hashMap);
                    ifExists = true;
                    break;
                }
            }

            if (ifExists == false) {
                HashMap hashMap = new HashMap();
                hashMap.put(resKeyName, formmatedStr.substring(11, 13));
                hashMap.put(resValueName, 0);
                resList.add(hashMap);
            }
            startInstance.add(Calendar.HOUR_OF_DAY, 1);
        }
        return resList;
    }
}
