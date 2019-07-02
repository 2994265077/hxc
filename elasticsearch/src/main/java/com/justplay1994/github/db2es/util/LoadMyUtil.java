package com.justplay1994.github.db2es.util;

import com.justplay1994.github.db2es.service.es.ESOperate;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class LoadMyUtil {

    Map<String, String> requestparamMap = new HashMap<String, String>();
    Map<String, String> paramMap = new HashMap<String, String>();

    public static String getPropertiesVauleOfKey(String fileName, String key) {
        InputStream inputStream = ESOperate.class.getResourceAsStream("/" + fileName + "");
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value = properties.getProperty(key);
        return value;
    }

    public static String getMyTime(String type, int num) {
        String date = "";
        Calendar cal = Calendar.getInstance();
        if ("YEAR".equals(type)) {
            cal.add(Calendar.YEAR, num);
            date = new SimpleDateFormat("yyyy").format(cal.getTime());
        } else if ("MONTH".equals(type)) {
            cal.add(Calendar.MONTH, num);
            date = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
        } else if ("DATE".equals(type)) {
            cal.add(Calendar.DATE, num);
            date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        } else if ("HOUR".equals(type)) {
            cal.add(Calendar.HOUR, num);
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
        } else if ("MINUTE".equals(type)) {
            cal.add(Calendar.MINUTE, num);
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
        } else if ("SECOND".equals(type)) {
            cal.add(Calendar.SECOND, num);
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
        }
        return date;
    }

    public static String checkMyVariable(String variable) {
        if ("".equals(variable)||variable==null){
            variable = "无";
        }
        return variable;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    //单位换算--小数点前移
    public static String changeUnit(String str,int point_forward) {
        String str_retuen;
        String zero = "0";
        int t = str.indexOf(".");
        if(t>point_forward){
            str_retuen = String.valueOf(Double.valueOf(str)*Math.pow(10,point_forward));
        }else if(t==point_forward){
            str_retuen= "0." + str;
        } else{
            int n = point_forward - str.length()-1;
            for(int i = 0;i<n;i++){
                zero +="0";
            }
            str_retuen= "0." + zero + str;
        }
       return str_retuen;
    }

    //相除小数点后保留两位
    public static Double exceptToPoint(double a,double b) {
        DecimalFormat format = new DecimalFormat("0.00");
        double k=a/b;//如果两个浮点数都有很多位数，那就转BigDecimal进行运算
        return Double.valueOf(format.format(k));
    }

    //保留小数点后三位数字
    public static Double retainToPoint(double num,int digit) {
        String str = String.valueOf(num);
        int point = str.indexOf(".");
        String res = str.substring(0,point+digit+1);
        return Double.valueOf(res);
    }

    public static Double retainToPoint(String num,int digit) {
        int point = num.indexOf(".");
        String res = num.substring(0,point+digit+1);
        return Double.valueOf(res);
    }

    /**
     * 客户端请求，获取客户端传递的request.getParameterMap() 集合参数
     * 然后将集合参数转换成普通Map集合
     * @param map
     * @return map
     * @throws IOException
     */
    public Map getParameterMap(Map map) throws IOException{
        paramMap.putAll(map);
        paramMap.remove("password");
        paramMap.remove("passwords");
        paramMap.remove(null);

        /**
         * 先将request.getParameterMap() 转为普通Map
         * 然后再将转换的Map存入到新的Map
         */
        Iterator entries = paramMap.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            /**
             * 将传入的值进行转码操作
             */
            //value = new String(value.getBytes("ISO-8859-1"), "gbk");
            //System.out.print("----------"+name+"------"+value);
            requestparamMap.put(name, value);
        }
        return requestparamMap;

    }
}
