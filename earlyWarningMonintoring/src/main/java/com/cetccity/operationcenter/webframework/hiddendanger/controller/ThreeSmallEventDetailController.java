package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.core.tools.HttpClientUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.ThreeSmallEventDetailApi;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.controller
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2019/2/19 15:17
 * @Updater: huangzezhou
 * @Update_Date: 2019/2/19 15:17
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@RestController
@Slf4j
public class ThreeSmallEventDetailController implements ThreeSmallEventDetailApi {
    @Override
    public HttpResponseModel<String> getTicket() {

        String url = "http://10.190.55.62:8080/zz-yhdljq/v1/gwapi?Method=LoginByAccount&Account=limujun&Password=e10adc3949ba59abbe56e057f20f883e";

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer d7f91c2e-7922-3410-abc2-8695f0ae6103");

        try {
            String response = HttpClientUtil.getWithHeader(url, headers);
            return new HttpResponseModel<>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, getTicket(response));
        } catch (IOException e) {
            log.error(e.toString());
            return new HttpResponseModel<>();
        } catch (URISyntaxException e) {
            log.error(e.toString());
            return new HttpResponseModel<>();
        }
    }

    public String getTicket(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(response, Map.class);
        LinkedHashMap linkedHashMap = (LinkedHashMap) map.get("data");
        return String.valueOf(linkedHashMap.get("AccessToken"));
    }

}
