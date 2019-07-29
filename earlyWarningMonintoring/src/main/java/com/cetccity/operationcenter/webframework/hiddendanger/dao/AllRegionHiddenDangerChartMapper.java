package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.ThreeSmallHiddenDangerInfo;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.NameValuePlus;
import com.cetccity.operationcenter.webframework.web.model.incident.AlarmTodayType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.dao
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/20 11:04
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/20 11:04
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Mapper
public interface AllRegionHiddenDangerChartMapper {

    /**
     * 统计隐患数量： 安监（三小事件数），地质
     * @return
     */
    HashMap<String, Object> countAll(@Param("streetCode") String streetCode);


    /**
     * 分街道统计三小场所事件
     */
    List<NameValueTypeModel<Integer>> queryThreeSmallEvent(@Param("streetCode") String streetCode);

    List<NameValueTypeModel<Integer>>  queryThreeSmallEventByStreetName(@Param("street_name") String streetName);


    /**
     * 地质数量统计
     */
    List<NameValueTypeModel<Integer>>  queryGeologyNumByStreet(@Param("streetCode") String streetCode);

    List<NameValueTypeModel<Integer>>  queryGeologyNumByStreetName(@Param("street_name") String streetName);


    /**
     * 查询三小场所隐患事件列表
     */
    List<ThreeSmallHiddenDangerInfo> queryThreeSmallHiddenDangerList(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("placeType")String placeType, @Param("streetCode") String streetCode);

    /**
     * 根据类型统计三小场所隐患事件
     */
    List<NameValuePlus> countThreeSmallDangerByType(@Param("streetCode") String streetCode,@Param("startTime")String startTime,@Param("endTime")String endTime);

    /**
     * 根据类型统计报警事件统计
     */
    List<NameValuePlus> countAlarmsByType(@Param("streetCode") String streetCode);

    /**
     *  根据systemId查询城管巡查事件图片
     * @param systemIds
     * @return union pic urls
     */
    List<NameValueModel> picsBySystemIds(@Param("systemIds") List<String> systemIds);

    /**
     * 根据类型查询报警事件类型
     */
    List<AlarmTodayType> queryAlarmByType(@Param("type") String typeV2, @Param("streetCode") String streetCode);

}
