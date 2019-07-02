package com.cetc.cloud.datasynch.provider.controller;

import com.cetc.cloud.datasynch.api.service.GetSignedParamRemoteService;
import com.cetc.cloud.datasynch.provider.service.impl.SignParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *  获取签名后的参数
 * Created by Administrator on 2019/5/29.
 */
@RestController
@Slf4j
public class GetSignedParamController implements GetSignedParamRemoteService {

    @Autowired
    SignParamService signParamService;

    @Override
    public String getSignedParam(String appkey,String content, String code, String secretKey,String format){
        if ("json".equalsIgnoreCase(format)){
            return signParamService.getSignedParam(appkey,content, code, secretKey).toJSONString();
        }else {
            return signParamService.getSignedParam_bulk(appkey, content, code, secretKey);
        }
    }

}
