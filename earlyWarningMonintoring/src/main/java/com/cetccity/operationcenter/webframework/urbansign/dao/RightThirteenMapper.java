package com.cetccity.operationcenter.webframework.urbansign.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:48 2019-07-05
 * Updater:     heliangming
 * Update_Date：17:48 2019-07-05
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface RightThirteenMapper {

    List<HashMap> getRightThirteenOfOne(Map map);

    List<HashMap> getRightThirteenOfTwo(Map map);

    List<HashMap> getRightThirteenOfThree(Map map);

    List<HashMap> getRightThirteenOfFour(Map map);

    List<HashMap> getRightThirteenOfFive(Map map);

}
