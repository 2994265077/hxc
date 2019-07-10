package com.cetccity.operationcenter.webframework.alarmcenter.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.api.LabCityManageEventApi;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.LAB_CITYMANAGE_EVENT;
import com.cetccity.operationcenter.webframework.alarmcenter.service.LabCityManageEventService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 12:04 2019-07-10
 * Updater:     heliangming
 * Update_Date：12:04 2019-07-10
 * 更新描述:    heliangming 补充
 **/
@RestController
public class LabCityManageEventController implements LabCityManageEventApi {

    @Autowired
    LabCityManageEventService labCityManageEventService;

    public HttpResponseModel<Integer> receiveLabCityManageEvent(@RequestBody LAB_CITYMANAGE_EVENT labCitymanageEvent){
        return labCityManageEventService.receiveLabCityManageEvent(labCitymanageEvent);
    }

}
