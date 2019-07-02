package com.cetccity.operationcenter.webframework.web.model.incident;

import com.alibaba.fastjson.annotation.JSONField;

public class NearbyLocationModel<T> {
    /**
     * 字段名称：数量
     *
     * 数据库字段信息:UUID VARCHAR
     */
    @JSONField(name = "UUID")
    private String UUID;

    /**
     * 字段名称：地理信息
     *
     * 数据库字段信息:location VARCHAR
     */
    private T location;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public T getLocation() {
        return location;
    }

    public void setLocation(T location) {
        this.location = location;
    }
}
