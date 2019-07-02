package com.cetccity.operationcenter.webframework.web.model.build;

public class LayerData <T>{

    private  String layer;    //当前楼层

    private  T data;

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
