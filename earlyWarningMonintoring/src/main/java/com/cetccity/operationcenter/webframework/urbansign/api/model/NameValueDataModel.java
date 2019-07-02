package com.cetccity.operationcenter.webframework.urbansign.api.model;

public class NameValueDataModel<T> {

    private String name;

    private String value;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
