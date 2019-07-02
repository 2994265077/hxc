package com.cetccity.operationcenter.webframework.environment.service;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:21 2019-05-28
 * Updater:     heliangming
 * Update_Date：9:21 2019-05-28
 * 更新描述:    heliangming 补充
 **/
public interface CleanRiverToDrainHoldBiService {

    List<NameDataModel> rightOne(String street);

    HttpResponseModel<ChartDetailModel> rightTwo(String street);

    HttpResponseModel<ChartDetailModel> rightThree(String street);

    HttpResponseModel<ChartDetailModel> rightFour(String street);

}
