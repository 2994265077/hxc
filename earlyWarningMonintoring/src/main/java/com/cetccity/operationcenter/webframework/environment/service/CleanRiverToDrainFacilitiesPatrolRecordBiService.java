package com.cetccity.operationcenter.webframework.environment.service;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.environment.api.model.PatrolRecordRightFour;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:28 2019-05-30
 * Updater:     heliangming
 * Update_Date：17:28 2019-05-30
 * 更新描述:    heliangming 补充
 **/
public interface CleanRiverToDrainFacilitiesPatrolRecordBiService {

    List<NameValueModel> rightOne(String SEWERATE_ID);

    HttpResponseModel<ChartDetailModel> rightTwo(String SEWERATE_ID);

    HttpResponseModel<ChartDetailModel> rightThree(String street, String SEWERATE_ID, String hiddenDanger);

    MyPageInfoModel rightFour(String street, String SEWERATE_ID, Integer pageNum, Integer pageSize);
}
