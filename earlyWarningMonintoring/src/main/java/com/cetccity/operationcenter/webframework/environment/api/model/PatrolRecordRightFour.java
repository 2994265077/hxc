package com.cetccity.operationcenter.webframework.environment.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.api.model
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:46 2019-05-30
 * Updater:     heliangming
 * Update_Date：18:46 2019-05-30
 * 更新描述:    heliangming 补充
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatrolRecordRightFour {

    private Date CHECK_TIME;

    private String SEWERATE_NAME;

    private String SEWERATE_ADDRESS;

    private String ContentDescription;

    private String FACILITY_NAME;

    private String FACILITY_ADDRESS;

    private String layerId;

    private String FACILITY_ID;

    private String JD84;

    private String WD84;

}
