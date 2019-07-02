package com.cetccity.operationcenter.webframework.search.service;

import java.io.IOException;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.service
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:28 2019-03-14
 * Updater:     heliangming
 * Update_Date：17:28 2019-03-14
 * 更新描述:    heliangming 补充
 **/
public interface PlanLandTipService {

    Map addressResolutionTip(String id)throws IOException;

    Map addressWebService_pointTip(String id)throws IOException;
}
