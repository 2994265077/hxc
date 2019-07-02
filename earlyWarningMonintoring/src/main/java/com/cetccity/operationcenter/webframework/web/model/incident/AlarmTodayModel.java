package com.cetccity.operationcenter.webframework.web.model.incident;

public class AlarmTodayModel<T> {

    private Integer warningCount;

    private Integer remainCount;

    private T WarningType;

    private T typeOne;

    public Integer getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(Integer warningCount) {
        this.warningCount = warningCount;
    }

    public Integer getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(Integer remainCount) {
        this.remainCount = remainCount;
    }

    public T getWarningType() {
        return WarningType;
    }

    public void setWarningType(T warningType) {
        WarningType = warningType;
    }

    public T getTypeOne() {
        return typeOne;
    }

    public void setTypeOne(T typeOne) {
        this.typeOne = typeOne;
    }
}
