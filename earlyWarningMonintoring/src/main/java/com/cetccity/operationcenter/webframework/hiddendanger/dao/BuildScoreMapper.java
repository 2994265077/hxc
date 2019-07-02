package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BUILD_SCORE;
import com.cetccity.operationcenter.webframework.web.model.build.BuildOfDangerModel;
import com.cetccity.operationcenter.webframework.web.model.build.LoadMapBuildGrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:07 2019-04-02
 * Updater:     heliangming
 * Update_Date：16:07 2019-04-02
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface BuildScoreMapper {

    List<BUILD_SCORE> getBuildScoreList(BUILD_SCORE bUILD_SCORE);

    int saveBuildScoreOfBuildid(BUILD_SCORE bUILD_SCORE);

    List<BuildOfDangerModel> topNDangerScore(@Param("size") long size, @Param("dateStr")String dateStr);

    List<LoadMapBuildGrade> loadMap(@Param("dateStr")String dateStr);

}
