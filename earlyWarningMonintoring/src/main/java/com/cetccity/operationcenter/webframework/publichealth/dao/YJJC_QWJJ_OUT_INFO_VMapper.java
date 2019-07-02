package com.cetccity.operationcenter.webframework.publichealth.dao;

import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_OUT_INFO_V;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.dao
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:57 2019-02-27
 * Updater:     heliangming
 * Update_Date：11:57 2019-02-27
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface YJJC_QWJJ_OUT_INFO_VMapper {

    List<YJJC_QWJJ_OUT_INFO_V> getYJJC_QWJJ_OUT_INFO_V(YJJC_QWJJ_OUT_INFO_V yJJC_QWJJ_OUT_INFO_V);

}
