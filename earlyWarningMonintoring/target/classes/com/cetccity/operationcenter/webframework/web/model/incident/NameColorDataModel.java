package com.cetccity.operationcenter.webframework.web.model.incident;

public class NameColorDataModel<T> {

    private  String name;

    private  String value;

    private  T color;

    private  T data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public T getColor() {
        return color;
    }

    public void setColor(T color) {
        this.color = color;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
