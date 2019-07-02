package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.ObjectBaseInfo;
import com.cetccity.operationcenter.webframework.trigger.entity.QhsjSfwMonitorHourData;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-12-21
 */
public interface QhsjSfwMonitorHourDataMapper {

    @Select("select SITE_NAME from QHSJ_SURF_WATER_BASICINFO where SITE_CODE=#{siteCode}")
    String querySiteName(String siteCode);

    /**
     * 查询该水质监测站的基础信息：经度、维度、地址、区代码、街道代码、社区代码
     * @param site_code
     * @return
     */
    @Select("select ADDRESS ALARM_OBJECT, OBJECT_ID F_OBJECT_ROW_ID, JD84, WD84, ADDRESS,REGION_CODE,STREET_CODE,STREET_NAME,COMMUNITY_CODE,COMMUNITY_NAME FROM QHSJ_SURF_WATER_BASICINFO where SITE_CODE = #{site_code}")
    ObjectBaseInfo queryBaseInfo(String site_code);


    /**
     * 查询水质监测站所有编号
     * @return
     */
    @Select("select SITE_CODE FROM QHSJ_SURF_WATER_BASICINFO ")
    List<String> queryAllBasicSiteCode();

    List<QhsjSfwMonitorHourData> querySourceData(Map map);

}
