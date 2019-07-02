package com.cetccity.operationcenter.webframework.web.model.incident;

import lombok.Data;

import java.io.Serializable;

@Data
public class NearbyResourcesModel<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：id--图层
     *
     * 数据库字段信息:layerId VARCHAR
     */
    private String layerid;

    /**
     * 字段名称：id--图层
     *
     * 数据库字段信息:layerId VARCHAR
     */
    private String obj_Name;
    /**
     * 字段名称：表名
     *
     * 数据库字段信息:tableName VARCHAR
     */
    private String tableName;
    /**
     * 字段名称：数量
     *
     * 数据库字段信息:num VARCHAR
     */
    private int count;

    /**
     * 字段名称：数据
     *
     * 数据库字段信息:data VARCHAR
     */
    private T data;

}
