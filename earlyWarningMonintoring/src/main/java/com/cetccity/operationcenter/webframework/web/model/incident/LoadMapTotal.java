package com.cetccity.operationcenter.webframework.web.model.incident;

public class LoadMapTotal<T> {

    private String total;

    private T loadMap;


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public T getLoadMap() {
        return loadMap;
    }

    public void setLoadMap(T loadMap) {
        this.loadMap = loadMap;
    }
}
