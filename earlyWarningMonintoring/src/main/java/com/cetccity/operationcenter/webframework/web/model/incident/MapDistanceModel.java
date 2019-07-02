package com.cetccity.operationcenter.webframework.web.model.incident;

import java.io.Serializable;

public class MapDistanceModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 字段名称：最小
     *
     * 数据库字段信息:minlng VARCHAR
     */
    private double minlng;
    /**
     * 字段名称：经度
     *
     * 数据库字段信息:maxlng VARCHAR
     */
    private double maxlng;
    /**
     * 字段名称：经度
     *
     * 数据库字段信息:minlat VARCHAR
     */
    private double minlat;
    /**
     * 字段名称：经度
     *
     * 数据库字段信息:maxlat VARCHAR
     */
    private double maxlat;

    public double getMinlng() {
        return minlng;
    }

    public void setMinlng(double minlng) {
        this.minlng = minlng;
    }

    public double getMaxlng() {
        return maxlng;
    }

    public void setMaxlng(double maxlng) {
        this.maxlng = maxlng;
    }

    public double getMinlat() {
        return minlat;
    }

    public void setMinlat(double minlat) {
        this.minlat = minlat;
    }

    public double getMaxlat() {
        return maxlat;
    }

    public void setMaxlat(double maxlat) {
        this.maxlat = maxlat;
    }
}
