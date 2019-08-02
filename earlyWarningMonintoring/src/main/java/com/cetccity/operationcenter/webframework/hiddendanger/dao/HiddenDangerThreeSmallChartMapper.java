package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Hash;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.dao
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/17 16:48
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/17 16:48
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Mapper
public interface HiddenDangerThreeSmallChartMapper {

    List<NameValueTypeModel> countThreeSmallType(@Param("street") String street);

    List<BarOrLineModel> countAlarmByDateAndStreet(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("streetCode") String streetCode);

    List<NameValueTypeModel<Integer>> countThreeSmallEventByType(@Param("street") String street, @Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);

    List<NameValueTypeModel<Integer>> countUndisposedByType(@Param("eventTypes") List<String> eventTypes, @Param("street") String street, @Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);

    List<NameValueTypeModel<Integer>> countThreeSmallPlaceByStreet(@Param("street") String street, @Param("hasTrouble") String hasTrouble);

    List<NameValueTypeModel<Integer>> countThreeSmallPlaceByStreetAndDate(@Param("street") String street, @Param("hasTrouble") String hasTrouble, @Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);

}
