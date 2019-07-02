package com.cetccity.operationcenter.webframework.urbansign.service;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:34 2019-05-10
 * Updater:     heliangming
 * Update_Date：9:34 2019-05-10
 * 更新描述:    heliangming 补充
 **/
public interface EcologicalEnvironmentService {

    /**
     * 生态环境-总统计
     * @return
     */
    HttpResponseModel<ChartDetailModel> totalEcological();

    /**
     * 河流水质状况-按季度
     * @return
     */
    HttpResponseModel<ChartDetailModel> riverWaterQualityByQuarter(String year, String quarter);

    /**
     * 空气质量指数-按天
     * @return
     */
    HttpResponseModel<ChartDetailModel> airAqiByDay();

    /**
     * 空气监测站超标统计
     * @param type 类型
     * @return
     */
    HttpResponseModel<ChartDetailModel> airExcessByStation(String type, int num_x, String station);

    /**
     * 监测站分布
     * @return
     */
    HttpResponseModel<ChartDetailModel> stationDistribution();
}
