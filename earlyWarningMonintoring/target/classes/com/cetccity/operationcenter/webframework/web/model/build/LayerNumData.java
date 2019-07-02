package com.cetccity.operationcenter.webframework.web.model.build;

public class LayerNumData <T>{

    private  String layernum;  //总楼层

    private  T data;           //建筑各楼层实体数据

    public String getLayernum() {
        return layernum;
    }

    public void setLayernum(String layernum) {
        this.layernum = layernum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
