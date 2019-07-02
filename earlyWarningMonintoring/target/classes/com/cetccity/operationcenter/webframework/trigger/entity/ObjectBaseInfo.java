package com.cetccity.operationcenter.webframework.trigger.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Package: com.cetc.cloud.alarm.trigger.dao.entity
 * @Project: alarm-trigger
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/28 16:19
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/28 16:19
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 *
 * 主体基础信息
 *
 **/
@Data
public class ObjectBaseInfo {

    /**
     * 预警对象
     */
    @JsonProperty
    private String ALARM_OBJECT;

    /**
     * 预警对象的唯一主键
     */
    @JsonProperty
    private Double F_OBJECT_ROW_ID;

//    ===============================================
    /**
     * 经度84
     */
    @JsonProperty
    private String JD84;

    /**
     * 纬度84
     */
    @JsonProperty
    private String WD84;

    /**
     * 区代码
     */
    @JsonProperty
    private String REGION_CODE;

    /**
     * 街道代码
     */
    @JsonProperty
    private String STREET_CODE;

    /**
     * 社区代码
     */
    @JsonProperty
    private String COMMUNITY_CODE;

    @JsonProperty
    private String ADDRESS;

    @JsonProperty
    private String STREET_NAME;

    @JsonProperty
    private String COMMUNITY_NAME;

    /**
     * 楼栋代码
     */
    @JsonProperty
    private String LDDM;

    public void initBaseInfo(ObjectBaseInfo baseInfo){
        this.ALARM_OBJECT = baseInfo.getALARM_OBJECT();
        this.F_OBJECT_ROW_ID = baseInfo.getF_OBJECT_ROW_ID();
        this.JD84 = baseInfo.getJD84();
        this.WD84 = baseInfo.getWD84();
        this.ADDRESS = baseInfo.getADDRESS();
        this.REGION_CODE = baseInfo.getREGION_CODE();
        this.STREET_CODE = baseInfo.getSTREET_CODE();
        this.STREET_NAME = baseInfo.getSTREET_NAME();
        this.COMMUNITY_CODE = baseInfo.getCOMMUNITY_CODE();
        this.COMMUNITY_NAME = baseInfo.getCOMMUNITY_NAME();
        this.LDDM = baseInfo.getLDDM();
    }
}
