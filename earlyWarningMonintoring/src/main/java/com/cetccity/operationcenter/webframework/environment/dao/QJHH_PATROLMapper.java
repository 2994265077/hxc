package com.cetccity.operationcenter.webframework.environment.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.environment.api.model.PatrolRecordRightFour;
import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_PATROL;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:36 2019-05-27
 * Updater:     heliangming
 * Update_Date：17:36 2019-05-27
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface QJHH_PATROLMapper {

    List<QJHH_PATROL> getList(QJHH_PATROL qJHH_PATROL);

    List<NameValueModel> rightOne(Map map);

    List<HashMap> loadMap(@Param("currentMonth") String currentMonth);

    List<HashMap> rightTwo(Map map);

    List<HashMap> rightThreeNoStreet(Map map);

    List<HashMap> rightThreeHasStreet(Map map);

    List<PatrolRecordRightFour> rightFour(Map map);

    List<HashMap> rightFive(Map map);

    int rightFourCount(Map map);

    //List<HashMap> rightFour(Map map);
}
