package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.urbansign.dao.entity.XIAOSAN_PROJECT;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:46 2019-06-03
 * Updater:     heliangming
 * Update_Date：14:46 2019-06-03
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface XIAOSAN_PROJECTMapper {

    List<XIAOSAN_PROJECT> getList(@Param("type") String type);
}
