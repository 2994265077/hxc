package com.cetccity.operationcenter.webframework.web.model.build;

public class HiddenDanger {

    private String unit;            //单位

    private String type;            //隐患类别

    private String dangerPart;      //隐患部位

    private String dangerPro;       //隐患问题

    private String dangerGrade;     //隐患等级

    private String eventGrade;      //主体责任

    private String progress;        //整改进度

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDangerPart() {
        return dangerPart;
    }

    public void setDangerPart(String dangerPart) {
        this.dangerPart = dangerPart;
    }

    public String getDangerPro() {
        return dangerPro;
    }

    public void setDangerPro(String dangerPro) {
        this.dangerPro = dangerPro;
    }

    public String getDangerGrade() {
        return dangerGrade;
    }

    public void setDangerGrade(String dangerGrade) {
        this.dangerGrade = dangerGrade;
    }

    public String getEventGrade() {
        return eventGrade;
    }

    public void setEventGrade(String eventGrade) {
        this.eventGrade = eventGrade;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
