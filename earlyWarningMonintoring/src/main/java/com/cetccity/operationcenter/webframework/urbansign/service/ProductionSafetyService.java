package com.cetccity.operationcenter.webframework.urbansign.service;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:16 2019-06-03
 * Updater:     heliangming
 * Update_Date：10:16 2019-06-03
 * 更新描述:    heliangming 补充
 **/
public interface ProductionSafetyService {

    List<NameValueModel> xiaoSanProject(String type);

    HttpResponseModel<ChartDetailModel> sanXiaoPlaceToBar();

    HttpResponseModel<ChartDetailModel> riskPointToBar();
}
