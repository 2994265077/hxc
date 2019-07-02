package com.cetccity.operationcenter.webframework.core.tools;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@UtilityClass
@Slf4j
public class LoadMyUtil {

    Map<String, String> requestparamMap = new HashMap<>();
    Map<String, String> paramMap = new HashMap<>();

    public List<NameValueModel> nameValueList(String name[], String value[]){
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        for(int i = 0;i<name.length;i++) {
            nameValueModel_list.add(NameValueModel.builder().name(name[i]).value(value[i]).build());
        }
        return nameValueModel_list;
    }

    public String getPropertiesVauleOfKey(String fileName, String key) {
        if(key==null){ return "未知";}
        InputStream inputStream = ESOperate.class.getResourceAsStream("/" + fileName + "");
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException e) {
        	log.error(e.toString());
        }
        String value = properties.getProperty(key);
        return value;
    }

    public String getMyTime(String type, int num) {
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

    public String checkMyVariable(String variable) {
        if ("".equals(variable)||variable==null){
            variable = "无";
        }
        return variable;
    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    //单位换算--小数点前移
    public String changeUnit(String str,int point_forward) {
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
    public Double exceptToPoint(double a,double b) {
        DecimalFormat format = new DecimalFormat("0.00");
        double k=a/b;//如果两个浮点数都有很多位数，那就转BigDecimal进行运算
        return Double.valueOf(format.format(k));
    }

    //计算百分比
    public String myPercent(int y, int z) {
        String baifenbi = "";// 接受百分比的值
        double baiy = y * 1.0;
        double baiz = z * 1.0;
        double fen = baiy / baiz;
        // NumberFormat nf = NumberFormat.getPercentInstance(); 注释掉的也是一种方法
        // nf.setMinimumFractionDigits( 2 ); 保留到小数点后几位
        DecimalFormat df1 = new DecimalFormat("##.00%"); // ##.00%
        // 百分比格式，后面不足2位的用0补齐
        // baifenbi=nf.format(fen);
        baifenbi = df1.format(fen);
        return baifenbi;
    }

    //保留小数点后三位数字
    public Double retainToPoint(double num,int digit) {
        String str = String.valueOf(num);
        int index = str.lastIndexOf(".");
        //小数点后的字符串
        String tt = str.substring(index + 1,str.length());//6666666
        if(tt.length()<4){
            return num;
        }
        int point = str.indexOf(".");
        String res = str.substring(0,point+digit+1);
        return Double.valueOf(res);
    }

    public Double retainToPoint(String num,int digit) {
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
