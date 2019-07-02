package com.cetccity.operationcenter.webframework.urbansign.service;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:58 2019-05-31
 * Updater:     heliangming
 * Update_Date：16:58 2019-05-31
 * 更新描述:    heliangming 补充
 **/
public interface SocialSecurityService {

    HttpResponseModel<ChartDetailModel> rightOneToXName(String securityName, String x);

    HttpResponseModel<ChartDetailModel> rightTwo();

    HttpResponseModel<ChartDetailModel> rightThree();

    String rightFour();
}
