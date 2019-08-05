package com.cetccity.operationcenter.webframework.environment.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:41 2019-08-05
 * Updater:     heliangming
 * Update_Date：16:41 2019-08-05
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface CleanRiverDataArrangementMapper {

    List<HashMap> operationOne();

    List<String> operationTwo(@Param("name") String name);

    int operationThree(@Param("uid") String uid,@Param("uid_current") String uid_current);

    int operationFour(@Param("uid") String uid,@Param("uid_current") String uid_current);

    int deleteRepeatHold(@Param("uid_current") String uid_current);
}
