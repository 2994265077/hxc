package com.cetccity.operationcenter.webframework.rundisplay.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.P2P_PlatBasicInformation;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.service
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:15 2019-03-06
 * Updater:     heliangming
 * Update_Date：14:15 2019-03-06
 * 更新描述:    heliangming 补充
 **/
public interface P2P_PlatDetailService {

    List<NameDataModel> p2pPlatInformation(String id);
}
