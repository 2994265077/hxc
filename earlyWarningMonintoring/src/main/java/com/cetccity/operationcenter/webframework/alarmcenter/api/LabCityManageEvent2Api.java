package com.cetccity.operationcenter.webframework.alarmcenter.api;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.LAB_CITYMANAGE_EVENT;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:13 2019-07-10
 * Updater:     heliangming
 * Update_Date：17:13 2019-07-10
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "预警中心--视频分析推送数据Api2")
@RequestMapping("/usp")
public interface LabCityManageEvent2Api {

    @ApiOperation(value = "推送数据")
    @RequestMapping(value = "/receiveCityManageEvent",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    HttpResponseModel<Integer> receiveLabCityManageEvent(@RequestBody LAB_CITYMANAGE_EVENT labCitymanageEvent);

}
