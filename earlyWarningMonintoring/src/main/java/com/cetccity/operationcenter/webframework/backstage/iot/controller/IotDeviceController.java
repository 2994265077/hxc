package com.cetccity.operationcenter.webframework.backstage.iot.controller;

import com.cetccity.operationcenter.webframework.backstage.iot.api.IotDeviceApi;
import com.cetccity.operationcenter.webframework.backstage.iot.dao.entity.ALARM_PUSH_AVILABLE;
import com.cetccity.operationcenter.webframework.backstage.iot.service.IotDeviceService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotHiddenDangerChartMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.iot.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:34 2019-06-04
 * Updater:     heliangming
 * Update_Date：10:34 2019-06-04
 * 更新描述:    heliangming 补充
 **/
@RestController
public class IotDeviceController implements IotDeviceApi {

    @Autowired
    IotDeviceService iotDeviceService;

    @Autowired
    IotHiddenDangerChartMapper iotHiddenDangerChartMapper;

    public HttpResponseModel iotDeviceTop(){
        return iotDeviceService.iotDeviceTop();
    }

    public HttpResponseModel iotDeviceTrendOne(){
        return iotDeviceService.iotDeviceTrendOne();
    }

    public HttpResponseModel iotDeviceTrendTwo(){
        return iotDeviceService.iotDeviceTrendTwo();
    }

    public HttpResponseModel iotDeviceTrendThree(){
        return iotDeviceService.iotDeviceTrendThree();
    }

    public HttpResponseModel iotDeviceAttributeList(Integer pageNum, Integer pageSize){
        return iotDeviceService.iotDeviceAttributeList(pageNum, pageSize);
    }
}

