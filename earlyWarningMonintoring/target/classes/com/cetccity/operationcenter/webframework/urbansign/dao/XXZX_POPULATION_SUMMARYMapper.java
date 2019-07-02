package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.urbansign.dao.entity.XXZX_POPULATION_SUMMARY;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.XXZX_POPULATION_SUMMARYNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:36 2019-05-24
 * Updater:     heliangming
 * Update_Date：9:36 2019-05-24
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface XXZX_POPULATION_SUMMARYMapper {

    List<HashMap> getUrbanSignRightOne();

}
