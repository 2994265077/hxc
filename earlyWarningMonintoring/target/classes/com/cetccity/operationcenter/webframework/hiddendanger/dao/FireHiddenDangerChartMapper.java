package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.StreetBuilderLevel;
import com.cetccity.operationcenter.webframework.hiddendanger.service.impl.FireHiddenDangerChartServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.dao
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/18 18:57
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/18 18:57
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Mapper
public interface FireHiddenDangerChartMapper {

    List<HashMap> selectBuildIdAndStreetCode();

    /**
     * 功能描述: <br>
     * 〈按 日期 统计建筑风险等级〉
     *
     * @param date 日期 yyyy-MM-dd
     * @param buildDangerLevels 风险等级范围枚举表
     * @return:java.util.List<com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel>
     * @Author: YHY
     * @Date: 2019/4/18 13:05
     */
    List<NameValueTypeModel> selectBuilderLevelCount(@NotNull @Param("date") String date, @Param("streetCode") String streetCode, @Param("buildDangerLevels") List<FireHiddenDangerChartServiceImpl.BuildDangerLevel> buildDangerLevels);

    /**
     * 功能描述: <br>
     * 〈按 日期和街道 统计建筑风险等级〉
     *
     * @param date 日期 yyyy-MM-dd
     * @param buildDangerLevels 风险等级范围枚举表
     * @return:java.util.List
     * @Author:YHY
     * @Date: 2019/4/18 13:11
     */
    List<StreetBuilderLevel> selectStreetBuilderLevelCount(@NotNull @Param("date") String date, @Param("streetCode") String streetCode, @Param("buildDangerLevels") List<FireHiddenDangerChartServiceImpl.BuildDangerLevel> buildDangerLevels);




    List<StreetBuilderLevel> selectStreetBuilderLevelCountByStreet(@NotNull @Param("date") String date, @Param("street_name") String streetName, @Param("buildDangerLevels") List<FireHiddenDangerChartServiceImpl.BuildDangerLevel> buildDangerLevels);

    /**
     * 功能描述: <br>
     * 〈按街道名称统计建筑风险数量〉
     *
     * @param date 日期
     * @param minScore 最低分数  大于此分数才统计
     * @return:java.util.List<com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel<java.lang.Integer>>
     * @Author:dongxin
     * @Date: 2019/5/7 9:59
     */
    List<NameValueTypeModel<Integer>> countDangerByDate(@NotNull @Param("date") String date, @Param("min_score") Integer minScore, @Param("streetCode") String streetCode);

    /**
     * 功能描述: <br>
     * 〈按社区名称统计建筑风险数量〉
     *
     * @param date 日期
     * @param minScore 最低分数  大于此分数才统计
     * @param streetName 街道限制
     * @return:java.util.List<com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel<java.lang.Integer>>
     * @Author:dongxin
     * @Date: 2019/5/7 10:02
     */
    List<NameValueTypeModel<Integer>> countDangerByDateAndStreet(@NotNull @Param("date") String date, @Param("min_score") Integer minScore, @NotNull @Param("street_name") String streetName);

}
