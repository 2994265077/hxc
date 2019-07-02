package com.cetccity.operationcenter.webframework.hiddendanger.api.model;

import lombok.Data;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api.model
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:26 2019-04-08
 * Updater:     heliangming
 * Update_Date：9:26 2019-04-08
 * 更新描述:    heliangming 补充
 **/
@Data
public class SanXiaoPlaceLoadMap {

    /**
     * 字段名称：表名
     *
     * 数据库字段信息:tableName VARCHAR
     */
    private String tableName;

    /**
     * 字段名称：全局唯一ID
     *
     * 数据库字段信息:layerid VARCHAR
     */
    private String layerid;
    /**
     * 字段名称：全局唯一ID
     *
     * 数据库字段信息:uuid VARCHAR
     */
    private String id;
    /**
     * 字段名称：经度
     *
     * 数据库字段信息:jd VARCHAR
     */
    private String jd;

    /**
     * 字段名称：纬度
     *
     * 数据库字段信息:wd VARCHAR
     */
    private String wd;

    /**
     * 字段名称：关联build
     *
     * 数据库字段信息:hasBuildId VARCHAR
     */
    private String hasBuildId;
}
