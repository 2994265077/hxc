package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:39 2019-05-29
 * Updater:     heliangming
 * Update_Date：17:39 2019-05-29
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface SanXiaoPlaceMapMapper {

    List<HashMap> loadMapByDBAlarm(Map map);
}
