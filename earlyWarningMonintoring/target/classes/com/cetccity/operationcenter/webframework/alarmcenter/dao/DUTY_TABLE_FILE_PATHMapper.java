package com.cetccity.operationcenter.webframework.alarmcenter.dao;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.DUTY_TABLE_FILE_PATH;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.dao
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:10 2019-03-14
 * Updater:     heliangming
 * Update_Date：11:10 2019-03-14
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface DUTY_TABLE_FILE_PATHMapper {

    int getSEQ();

    List<DUTY_TABLE_FILE_PATH> getDUTY_TABLE_FILE_PATH(@Param("fileCode") String fileCode);

    int insert(DUTY_TABLE_FILE_PATH dUTY_TABLE_FILE_PATH);

    int deleteFile(@Param("fileCode")String fileCode);


}
