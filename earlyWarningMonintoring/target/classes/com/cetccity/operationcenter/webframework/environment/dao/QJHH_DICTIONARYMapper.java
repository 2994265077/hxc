package com.cetccity.operationcenter.webframework.environment.dao;

import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_DICTIONARY;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:11 2019-05-27
 * Updater:     heliangming
 * Update_Date：15:11 2019-05-27
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface QJHH_DICTIONARYMapper {

    List<QJHH_DICTIONARY> getList(QJHH_DICTIONARY qJHH_DICTIONARY);

}
