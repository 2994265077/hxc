package com.cetccity.operationcenter.webframework.alarmcenter.service;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.LAB_CITYMANAGE_EVENT;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:33 2019-07-10
 * Updater:     heliangming
 * Update_Date：14:33 2019-07-10
 * 更新描述:    heliangming 补充
 **/
public interface LabCityManageEventService {

    HttpResponseModel<Integer> receiveLabCityManageEvent(@RequestBody LAB_CITYMANAGE_EVENT labCitymanageEvent);
}
