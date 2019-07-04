package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.ZHUJIAN_BIANPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:12 2019-07-03
 * Updater:     heliangming
 * Update_Date：17:12 2019-07-03
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface ZHUJIAN_BIANPOMapper {

    ZHUJIAN_BIANPO getObject(@Param("id") String id);
}
