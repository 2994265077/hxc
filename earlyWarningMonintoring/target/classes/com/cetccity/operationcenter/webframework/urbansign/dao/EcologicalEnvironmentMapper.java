package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.urbansign.dao.entity.QHSJ_RIVERWATER_QUALITY_YEAR;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.dao
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/26 10:44
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/26 10:44
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Mapper
public interface EcologicalEnvironmentMapper {

    List<HashMap> querySiteValueByMonth();

    /**
     * 污染源废水站点
     * @return
     */
    List<HashMap> queryEnterpriseValueAndTime();

    /**
     * 空气
     * @return
     */
    List<HashMap> queryAirValueAndTime();

    /**
     * 空气--天
     * @return
     */
    List<HashMap> queryAirValueAndDay();

    /**
     * 污染源废水站点
     * @return
     */
    List<HashMap> querySurfWaterValueAndTime();

    /**
     * 空气监测站超标统计
     * @return
     */
    List<HashMap> airExcessByStationDay(Map map);

    List<HashMap> airExcessByStationMonth(Map map);

    List<HashMap> stationDistribution();

    List<HashMap> riverWaterQualityByQuarter1(@Param("year") String year);
    List<HashMap> riverWaterQualityByQuarter2(@Param("year") String year);
    List<HashMap> riverWaterQualityByQuarter3(@Param("year") String year);
    List<HashMap> riverWaterQualityByQuarter4(@Param("year") String year);

    List<QHSJ_RIVERWATER_QUALITY_YEAR> riverWaterDropDown();

}
