package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.P2P_PRIVATE_AGENCIES;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.dao
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:54 2019-03-06
 * Updater:     heliangming
 * Update_Date：16:54 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface P2P_PRIVATE_AGENCIESMapper {

    List<P2P_PRIVATE_AGENCIES> getPrivateDetail(@Param("id") String id);
}
