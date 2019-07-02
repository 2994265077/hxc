package com.cetccity.operationcenter.webframework.urbansign.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.dao
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/27 15:00
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/27 15:00
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Mapper
public interface CityComponentMapper {

    List<HashMap> queryTotalByStreet();

    List<HashMap> queryIotEventTypeAndDate();

    List<HashMap> countIotByDate(@Param("formatter") String formatter);

    //查询iot设备数量
    int countIot();

    List<HashMap> queryCameraNumByStreet();

    List<HashMap> queryCityComponentByStreet();

    List<HashMap> querySiteValueByMonth(@Param("begin") LocalDateTime begin);
}
