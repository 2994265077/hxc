package com.cetccity.operationcenter.webframework.urbansign.api.model;

public class NameTodayDataModel<T> {

    private String name;

    private T today;

    private T data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getToday() {
        return today;
    }

    public void setToday(T today) {
        this.today = today;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
