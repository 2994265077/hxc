package com.cetc.cloud.datasynch.provider.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cetc.cloud.datasynch.provider.util.SignUtil;
import com.cetc.cloud.datasynch.provider.util.SignUtil_CleanRiver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 对输入参数进行签名
 * Created by Administrator on 2019/5/31.
 */
@Service
@Slf4j
public class SignParamService {
    final static String timeFormat = "yyyy-MM-dd HH:mm:ss";

    public JSONObject getSignedParam(String appkey, String content, String code, String secretKey) {
        //split params into HashMap
        JSONObject httpParams = new JSONObject();
        httpParams.put("appkey", appkey);
        httpParams.put("content", content);
        httpParams.put("code", code);
        Date time = new Date();
        String formatedTime = DateFormatUtils.format(time, timeFormat);
        httpParams.put("timestamp", formatedTime);
        String processedParam = SignUtil_CleanRiver.getProcessedParamString(httpParams, secretKey);
        String sign = SignUtil.getSign(processedParam);
        httpParams.put("sign", sign);
        log.info("sign:\n" + toBulkString(httpParams));

        return httpParams;
    }

    public String getSignedParam_bulk(String appkey, String content, String code, String secretKey) {
        JSONObject signedParam = getSignedParam(appkey, content, code, secretKey);
        return toBulkString(signedParam);
    }
    private String toBulkString(JSONObject params){
        Set<String> keySet = params.keySet();
        Iterator<String> iterator = keySet.iterator();
        int count = 0;
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()){
            String next = iterator.next();
            if (count==params.size()-1){
                String s = next + ":" + params.get(next);
                builder.append(s);
            }else {
                String s = next + ":" + params.get(next) + "\n";
                builder.append(s);
            }
        }
        return builder.toString();
    }
}
