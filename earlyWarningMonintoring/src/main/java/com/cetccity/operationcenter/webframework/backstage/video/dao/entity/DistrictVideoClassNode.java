package com.cetccity.operationcenter.webframework.backstage.video.dao.entity;

import lombok.Data;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:27 2019-05-16
 * Updater:     heliangming
 * Update_Date：17:27 2019-05-16
 * 更新描述:    heliangming 补充
 **/
@Data
public class DistrictVideoClassNode extends DistrictVideoClass{

    private String TAGNAME;

    private String ADDRESS;

    private String JD84;

    private String WD84;

    private String STREET_CODE;
}
