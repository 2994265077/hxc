package com.cetccity.operationcenter.webframework.urbansign.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:54 2019-06-03
 * Updater:     heliangming
 * Update_Date：10:54 2019-06-03
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface ROAD_ADMIN_VEHICLE_PERMITMapper {

    List<HashMap> rightTwo();

    List<HashMap> rightThree();
}
