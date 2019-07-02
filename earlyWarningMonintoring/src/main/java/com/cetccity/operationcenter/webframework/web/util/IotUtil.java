package com.cetccity.operationcenter.webframework.web.util;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;

public class IotUtil {

    public static String GetAllDevices(String urlStr ) {



        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();

        map.put("device_type","0024");


        String response = IotApiUrl.doJsonPost(urlStr,JSON.toJSONString(map,false));


        return response;

    }




}
