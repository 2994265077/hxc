package com.cetccity.operationcenter.webframework.backstage.iot.service;

import com.cetccity.operationcenter.webframework.backstage.iot.dao.entity.ALARM_PUSH_AVILABLE;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.iot.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:16 2019-06-04
 * Updater:     heliangming
 * Update_Date：17:16 2019-06-04
 * 更新描述:    heliangming 补充
 **/
public interface IotDeviceService {

    HttpResponseModel iotDeviceTop();

    HttpResponseModel iotDeviceTrendOne();

    HttpResponseModel iotDeviceTrendTwo();

    HttpResponseModel iotDeviceTrendThree();

    HttpResponseModel iotDeviceAttributeList(Integer pageNum, Integer pageSize);

}
