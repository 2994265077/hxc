package com.cetccity.operationcenter.webframework.hiddendanger.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;

import java.util.HashMap;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:05 2019-03-29
 * Updater:     heliangming
 * Update_Date：10:05 2019-03-29
 * 更新描述:    heliangming 补充
 **/
public interface RISK_POINTDetailService {

    NameDataModel findRISK_POINTDetail(String id);

    HashMap<String, Object> riskPointDrillDown();
}
