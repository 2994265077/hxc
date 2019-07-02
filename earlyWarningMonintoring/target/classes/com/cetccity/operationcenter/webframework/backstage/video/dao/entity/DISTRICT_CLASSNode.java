package com.cetccity.operationcenter.webframework.backstage.video.dao.entity;

import lombok.Data;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.dao.entity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:04 2019-05-15
 * Updater:     heliangming
 * Update_Date：17:04 2019-05-15
 * 更新描述:    heliangming 补充
 **/
@Data
public class DISTRICT_CLASSNode extends DISTRICT_CLASS{

    private List<DISTRICT_CLASSNode> children;
 }
