package com.cetc.cloud.datasynch.provider.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 清洁护河 小程序专用签名工具
 * Created by Administrator on 2019/5/29.
 */
public class SignUtil_CleanRiver extends SignUtil{

    /**
     * 将传入的参数，转换为 要求的特殊类型
     * @param map
     * @return
     */
    public static String getProcessedParamString(JSONObject map,String sercretKey) {

        if (!map.isEmpty()) {
            List<String> keys = new ArrayList<String>(map.keySet());
            Collections.sort(keys);
            StringBuilder paramString = new StringBuilder();
            paramString.append(sercretKey);
            for (String key : keys) {
                paramString.append(key);
                paramString.append(map.get(key));
            }
            paramString.append(sercretKey);
            return paramString.toString();
        }
        return null;
    }
    public static JSONObject getHttpParams(String httpParamExpression) {
        JSONObject httpQueryParams = new JSONObject();
        if (null != httpParamExpression) {
            String[] paramKeyValues = httpParamExpression.split("&");

            for (int i = 0; i < paramKeyValues.length; i++) {
                String[] split = paramKeyValues[i].split(":");
                if (split.length == 2) {
                    String key = split[0];
                    String value = split[1];
                    httpQueryParams.put(key, value);
                } else {
                    continue;
                }
            }
        }
        return httpQueryParams;
    }


}
