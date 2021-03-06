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
import java.io.Serializable;

/**
 * 工程包名:   com.cetc.cloud.framework.api.model
 * 项目名称:   framework-plugin
 * 创建描述:   zhangliang 补充
 * Creator:     zhangliang
 * Create_Date: 2017/10/25
 * Updater:     zhangliang
 * Update_Date：2017/10/25
 * 更新描述:    zhangliang 补充
 **/
public class CetcCloudHostModel implements Serializable {

    /**
     * 字段名称：远程主机IP地址
     *
     * 数据库字段信息:hostIp Long
     */
    private String hostIp;

    /**
     * 字段名称：远程主机端口
     *
     * 数据库字段信息:hostPort Int
     */
    private int hostPort;

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }
}
