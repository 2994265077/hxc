package com.cetccity.operationcenter.webframework.environment.service;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:11 2019-05-29
 * Updater:     heliangming
 * Update_Date：9:11 2019-05-29
 * 更新描述:    heliangming 补充
 **/
public interface CleanRiverToDrainFacilitiesBiService {

    List<NameDataModel> rightOne(String street, String SEWERATE_ID);

    HttpResponseModel<ChartDetailModel> rightTwo(String street, String SEWERATE_ID);

    HttpResponseModel<ChartDetailModel> rightThree(String street, String SEWERATE_ID);

    HttpResponseModel<ChartDetailModel> rightFour(String SEWERATE_ID);

    HttpResponseModel<ChartDetailModel> rightFive();

}
