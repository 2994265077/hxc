package com.cetccity.operationcenter.webframework.core.frame.model;
/**********************************************************************
 *
 * Copyright (c) 2017 CETC Company
 * 中电科新型智慧城市研究院有限公司版权所有
 *
 * PROPRIETARY RIGHTS of CETC Company are involved in the
 * subject matter of this material. All manufacturing, reproduction, use,
 * and sales rights pertaining to this subject matter are governed by the
 * license agreement. The recipient of this software implicitly accepts
 * the terms of the license.
 * 本软件文档资料是中电科新型智慧城市研究院有限公司的资产，任何人士阅读和
 * 使用本资料必须获得相应的书面授权，承担保密责任和接受相应的法律约束。
 *
 *************************************************************************/

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 工程包名:   com.cetc.cloud.framework.api.model
 * 项目名称:   framework-plugin
 * 创建描述:   zhangliang 补充
 * Creator:     zhangliang
 * Create_Date: 2017/9/26
 * Updater:     zhangliang
 * Update_Date：2017/9/26
 * 更新描述:    zhangliang 补充
 **/
public class GenericModel implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：主键ID，自增
     *
     * 数据库字段信息:id Long
     */
    @JsonIgnore
    private long id;

    /**
     * 字段名称：创建作者
     *
     * 数据库字段信息:creator VARCHAR
     */
    @JsonIgnore
    private String creator;

    /**
     * 字段名称：更新作者
     *
     * 数据库字段信息:updater VARCHAR
     */
    @JsonIgnore
    private String updater;

    /**
     * 字段名称：创建时间
     *
     * 数据库字段信息:create_date VARCHAR
     */
    @JsonIgnore
    private String createDate;

    /**
     * 字段名称：更新时间
     *
     * 数据库字段信息:update_date VARCHAR
     */
    @JsonIgnore
    private String updateDate;

    /**
     * 字段名称：条目状态
     *
     * 数据库字段信息:state INT
     */
    @JsonIgnore
    private int state;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
