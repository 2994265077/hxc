package com.cetccity.operationcenter.webframework.rundisplay.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.service
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:51 2019-03-06
 * Updater:     heliangming
 * Update_Date：16:51 2019-03-06
 * 更新描述:    heliangming 补充
 **/
public interface P2P_PrivateDetailService {

    List<NameDataModel> p2pPrivateInformation(String id);
}
