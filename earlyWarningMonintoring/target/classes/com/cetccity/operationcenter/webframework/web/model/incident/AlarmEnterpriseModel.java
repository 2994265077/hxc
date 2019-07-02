package com.cetccity.operationcenter.webframework.web.model.incident;

public class AlarmEnterpriseModel {

    //企业名称
    private String enterpriseName;
    //预警时间
    private String alarmTime;

    private String buildid;

    private String score;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getBuildid() {
        return buildid;
    }

    public void setBuildid(String buildid) {
        this.buildid = buildid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
