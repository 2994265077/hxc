package com.cetccity.operationcenter.webframework.web.model.citySign;

public class MacroEconomyFirst<T> {

    private T generalSituation;//宏观经济

    private String name;

    private T data;

    public T getGeneralSituation() {
        return generalSituation;
    }

    public void setGeneralSituation(T generalSituation) {
        this.generalSituation = generalSituation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
