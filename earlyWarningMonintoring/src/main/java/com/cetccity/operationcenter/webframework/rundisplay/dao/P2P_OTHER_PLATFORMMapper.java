package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.P2P_OTHER_PLATFORM;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.dao
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:06 2019-03-06
 * Updater:     heliangming
 * Update_Date：17:06 2019-03-06
 * 更新描述:    heliangming 补充
 **/
public interface P2P_OTHER_PLATFORMMapper {

    List<P2P_OTHER_PLATFORM> getOtherDetail(@Param("id") String id);
}
