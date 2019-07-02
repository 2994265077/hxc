package com.cetccity.operationcenter.webframework.hiddendanger.api.model;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import lombok.Data;

import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api.model
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:26 2019-03-04
 * Updater:     heliangming
 * Update_Date：15:26 2019-03-04
 * 更新描述:    heliangming 补充
 **/
@Data
public class ThreeSmallPageInfoModel<T> extends MyPageInfoModel {

    private Map type;

}
