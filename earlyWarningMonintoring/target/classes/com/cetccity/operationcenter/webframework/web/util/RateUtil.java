package com.cetccity.operationcenter.webframework.web.util;

import java.util.LinkedHashMap;

public class RateUtil {

    public static LinkedHashMap<String,String> getLeftOneModel(Integer current_num, Integer upper_num){
        LinkedHashMap<String,String> map = new LinkedHashMap();
        double per;
        String trend;
        if(current_num == null){current_num = 0; }
        if(upper_num==0){
            per = 0.00;
            trend ="0";
        }else {
            per = (float) (current_num - upper_num) / (float) upper_num;
            if (current_num - upper_num > 0) {
                trend = "1";
            } else if (current_num - upper_num < 0) {
                trend = "-1";
            } else {
                trend = "0";
            }
        }
        String news_enterprise_rate = String.valueOf(per)+"%";
        map.put("rate",news_enterprise_rate);
        map.put("trend",trend);
        return map;
    }

}
