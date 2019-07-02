package com.cetccity.operationcenter.webframework.rundisplay.api.model;

import lombok.Data;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.api.model
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:47 2019-03-06
 * Updater:     heliangming
 * Update_Date：14:47 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Data
public class P2P_PlatBasicInformation {

    //平台名称
    private String platName;
    //成立时间
    private String foundDate;
    //平台网站
    private String webSite;
    //平台上线时间
    private String upTime;
    //网站状态
    private String webSiteStatus;
    //平台状态
    private String runStatus;
}
