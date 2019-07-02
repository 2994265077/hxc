package com.cetccity.operationcenter.webframework.web.model.situation;

import java.io.Serializable;

/**
 * Description：态势--风险指标
 * Created by luolinjie on 2018/4/27.
 */
public class RiskIndexModel implements Serializable {

    //建筑物风险评分
    private String buildingRisk;

    //基础网格人员密度
    private String gridPersonnelDensity;

    //粉尘涉爆企业
    private String dustExplosionEnterprises;

    //油汽站
    private String oilGasStation;

    //社区消防站密度
    private String communityFireStationDensity;

    //摄像头密度
    private String cameraDensity;

    //燃气站
    private String gasStation;

    //重大危险源
    private String majorHazard;

    //消防车密度
    private String fireTruckDensity;

    //消火栓
    private String fireHydrant;


    public String getBuildingRisk() {
        return buildingRisk;
    }

    public void setBuildingRisk(String buildingRisk) {
        this.buildingRisk = buildingRisk;
    }

    public String getGridPersonnelDensity() {
        return gridPersonnelDensity;
    }

    public void setGridPersonnelDensity(String gridPersonnelDensity) {
        this.gridPersonnelDensity = gridPersonnelDensity;
    }

    public String getDustExplosionEnterprises() {
        return dustExplosionEnterprises;
    }

    public void setDustExplosionEnterprises(String dustExplosionEnterprises) {
        this.dustExplosionEnterprises = dustExplosionEnterprises;
    }

    public String getOilGasStation() {
        return oilGasStation;
    }

    public void setOilGasStation(String oilGasStation) {
        this.oilGasStation = oilGasStation;
    }

    public String getCommunityFireStationDensity() {
        return communityFireStationDensity;
    }

    public void setCommunityFireStationDensity(String communityFireStationDensity) {
        this.communityFireStationDensity = communityFireStationDensity;
    }

    public String getCameraDensity() {
        return cameraDensity;
    }

    public void setCameraDensity(String cameraDensity) {
        this.cameraDensity = cameraDensity;
    }

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation;
    }

    public String getMajorHazard() {
        return majorHazard;
    }

    public void setMajorHazard(String majorHazard) {
        this.majorHazard = majorHazard;
    }

    public String getFireTruckDensity() {
        return fireTruckDensity;
    }

    public void setFireTruckDensity(String fireTruckDensity) {
        this.fireTruckDensity = fireTruckDensity;
    }

    public String getFireHydrant() {
        return fireHydrant;
    }

    public void setFireHydrant(String fireHydrant) {
        this.fireHydrant = fireHydrant;
    }
}
