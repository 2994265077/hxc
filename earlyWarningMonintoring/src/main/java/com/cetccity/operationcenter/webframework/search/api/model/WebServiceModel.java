package com.cetccity.operationcenter.webframework.search.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.api.model
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:55 2019-03-12
 * Updater:     heliangming
 * Update_Date：15:55 2019-03-12
 * 更新描述:    heliangming 补充
 **/
@Data
public class WebServiceModel {

    @JsonProperty("BBASEBUILDINGCODE")
    private WebServiceModel_Two BBASEBUILDINGCODE;
    @JsonProperty("BBASEBUILDINGNAME")
    private WebServiceModel_Two BBASEBUILDINGNAME;
    @JsonProperty("BT3_BUILDING_FULL_ADDR")
    private WebServiceModel_Two BT3_BUILDING_FULL_ADDR;
    @JsonProperty("BT1_BUILDING_FULL_ADDR")
    private WebServiceModel_Two BT1_BUILDING_FULL_ADDR;
    //百度--其它地址
    @JsonProperty("HOUSE_DETAIL_HOUSE_FULL_ADDR")
    private WebServiceModel_Two HOUSE_DETAIL_HOUSE_FULL_ADDR;
    //百度--房屋短地址
    @JsonProperty("HOUSE_DETAIL_HOUSE_SHORT_ADDR")
    private WebServiceModel_Two HOUSE_DETAIL_HOUSE_SHORT_ADDR;
    //百度--房屋编码
    @JsonProperty("HOUSE_HOUSE_CODE")
    private WebServiceModel_Two HOUSE_HOUSE_CODE;
    //百度--详细地址
    @JsonProperty("HOUSE_STANDARD_HOUSE_FULL_ADDR")
    private WebServiceModel_Two HOUSE_STANDARD_HOUSE_FULL_ADDR;
    @JsonProperty("COMMCODE")
    private WebServiceModel_Two COMMCODE;
    @JsonProperty("ID")
    private WebServiceModel_Two ID;
    @JsonProperty("LOCATION")
    private WebServiceModel_Two LOCATION;
}
