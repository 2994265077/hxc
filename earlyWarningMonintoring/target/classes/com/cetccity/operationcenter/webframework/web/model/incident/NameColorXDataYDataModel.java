package com.cetccity.operationcenter.webframework.web.model.incident;

public class NameColorXDataYDataModel<T> {

    private  String name;

    private  T color;

    private  T xdata;

    private  T ydata;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getColor() {
        return color;
    }

    public void setColor(T color) {
        this.color = color;
    }

    public T getXdata() {
        return xdata;
    }

    public void setXdata(T xdata) {
        this.xdata = xdata;
    }

    public T getYdata() {
        return ydata;
    }

    public void setYdata(T ydata) {
        this.ydata = ydata;
    }
}
