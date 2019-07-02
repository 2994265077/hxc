package com.cetccity.operationcenter.webframework.web.model.build;

public class BuildHouseModel<T> {

    private String code;   //

    private String num;    //房号

    private  T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
