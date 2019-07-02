package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.FINANCIAL_WARNING;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.trigger.dao
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:21 2019-03-11
 * Updater:     heliangming
 * Update_Date：16:21 2019-03-11
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface FINANCIAL_WARNINGMapper {

    List<FINANCIAL_WARNING> querySourceData(Map map);
}
