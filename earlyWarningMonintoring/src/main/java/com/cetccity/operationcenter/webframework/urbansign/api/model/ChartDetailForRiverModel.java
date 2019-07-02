package com.cetccity.operationcenter.webframework.urbansign.api.model;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import lombok.Data;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.core.chart.engine.model
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:39 2019-05-16
 * Updater:     heliangming
 * Update_Date：9:39 2019-05-16
 * 更新描述:    heliangming 补充
 **/
@Data
public class ChartDetailForRiverModel<T> extends ChartDetailModel {

    T homologous;

    T dropdown;
}
