package com.cetccity.operationcenter.webframework.web.model.build;


public class BuildOfDangerModel {

    private  String buildName;

    private  String dangerScore;

    private  String dangerReason;

    private  String firePolice;

    private  String firePoliceTel;

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getDangerScore() {
        return dangerScore;
    }

    public void setDangerScore(String dangerScore) {
        this.dangerScore = dangerScore;
    }

    public String getDangerReason() {
        return dangerReason;
    }

    public void setDangerReason(String dangerReason) {
        this.dangerReason = dangerReason;
    }

    public String getFirePolice() {
        return firePolice;
    }

    public void setFirePolice(String firePolice) {
        this.firePolice = firePolice;
    }

    public String getFirePoliceTel() {
        return firePoliceTel;
    }

    public void setFirePoliceTel(String firePoliceTel) {
        this.firePoliceTel = firePoliceTel;
    }
}
