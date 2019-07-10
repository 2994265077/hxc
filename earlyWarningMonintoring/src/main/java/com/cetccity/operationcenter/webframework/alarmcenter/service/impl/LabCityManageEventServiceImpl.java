package com.cetccity.operationcenter.webframework.alarmcenter.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.LAB_CITYMANAGE_EVENTMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.LAB_CITYMANAGE_EVENT;
import com.cetccity.operationcenter.webframework.alarmcenter.service.LabCityManageEventService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:33 2019-07-10
 * Updater:     heliangming
 * Update_Date：14:33 2019-07-10
 * 更新描述:    heliangming 补充
 **/
@Service
public class LabCityManageEventServiceImpl implements LabCityManageEventService {

    @Autowired
    LAB_CITYMANAGE_EVENTMapper lAB_CITYMANAGE_EVENTMapper;

    public HttpResponseModel<Integer> receiveLabCityManageEvent(LAB_CITYMANAGE_EVENT labCitymanageEvent){
        return new HttpResponseModel(200,"SUCCESS",lAB_CITYMANAGE_EVENTMapper.receiveLabCityManageEvent(labCitymanageEvent));
    }
}
