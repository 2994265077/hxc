package com.cetccity.operationcenter.webframework.alarmcenter.dao;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.DUTY_TABLE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:47 2019-03-15
 * Updater:     heliangming
 * Update_Date：17:47 2019-03-15
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface DUTY_TABLEMapper {

    List<DUTY_TABLE> getDUTY_TABLE(DUTY_TABLE dUTY_TABLE);

    int getSEQ_DUTY_TABLE();

    int save(DUTY_TABLE dUTY_TABLE);

    int delete(@Param("fileCode") String fileCode);

}
