package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.api.SocialSecurityApi;
import com.cetccity.operationcenter.webframework.urbansign.service.SocialSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:57 2019-05-31
 * Updater:     heliangming
 * Update_Date：16:57 2019-05-31
 * 更新描述:    heliangming 补充
 **/
@RestController
public class SocialSecurityController implements SocialSecurityApi{

    @Autowired
    SocialSecurityService socialSecurityService;

    public List<String> rightOneMenu(){
        List<String> collectList = Stream.of("自然灾害类","社会安全类","事故灾难类","公共卫生类","其他类").collect(Collectors.toList());
        return collectList;
    }

    public HttpResponseModel<ChartDetailModel> rightOneToXName(String securityName, String x){
        return socialSecurityService.rightOneToXName(securityName, x);
    }

    public HttpResponseModel<ChartDetailModel> rightTwo(){
        return socialSecurityService.rightTwo();
    }

    public HttpResponseModel<ChartDetailModel> rightThree(){
        return socialSecurityService.rightThree();
    }

    public String rightFour(){
        return socialSecurityService.rightFour();
    }
}

