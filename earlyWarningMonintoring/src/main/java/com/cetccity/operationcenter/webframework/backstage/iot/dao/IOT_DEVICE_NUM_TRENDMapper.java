package com.cetccity.operationcenter.webframework.backstage.iot.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.iot.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:19 2019-06-10
 * Updater:     heliangming
 * Update_Date：15:19 2019-06-10
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface IOT_DEVICE_NUM_TRENDMapper {

    List<HashMap> iotDeviceTrendOne();
}
