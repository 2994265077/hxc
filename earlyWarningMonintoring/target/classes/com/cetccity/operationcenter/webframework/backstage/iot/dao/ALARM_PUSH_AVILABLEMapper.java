package com.cetccity.operationcenter.webframework.backstage.iot.dao;

import com.cetccity.operationcenter.webframework.backstage.iot.dao.entity.ALARM_PUSH_AVILABLE;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.iot.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:39 2019-06-14
 * Updater:     heliangming
 * Update_Date：10:39 2019-06-14
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface ALARM_PUSH_AVILABLEMapper {

    List<ALARM_PUSH_AVILABLE> iotDeviceAttributeList();
}
