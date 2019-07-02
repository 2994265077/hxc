package com.cetccity.operationcenter.webframework.web.model.incident;

public class OneMenuModel<T> {
    private int num;
    private T data;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
