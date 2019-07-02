package com.cetccity.operationcenter.webframework.urbansign.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:02 2019-05-31
 * Updater:     heliangming
 * Update_Date：17:02 2019-05-31
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface WEEKLY_EMERGENCY_CASEMapper {

    List<HashMap> rightOneToTime(Map map);

    HashMap rightOneToTimeRemoval(Map map);

    List<HashMap> rightOneToStreet(Map map);

    List<HashMap> rightOneToStreetRemoval(Map map);

    List<HashMap> rightOneToType(Map map);

    List<HashMap> rightOneToTypeRemoval(Map map);

}
