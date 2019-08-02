package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.dao
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/19 17:47
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/19 17:47
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Mapper
public interface IotHiddenDangerChartMapper {

    /**
     * 查询摄像头数量
     */
    List<NameValueTypeModel> countCameraAndIot(@Param("streetCode") String streetCode);

    /**
     * 查询传感器，摄像头数量
     */
    List<NameValueTypeModel> countIotByType(@Param("streetCode") String streetCode);

    long countIot();

    /**
     * 查询近6个月的报警信息
     */

    List<HashMap> queryAlarmAndTime();

    /**
     * 查询指定月份的报警信息统计数量，按月，按device_type  参数示例2018-10,符合yyyy-MM
     */
    List<BarOrLineModel> countDeviceEventByMonth(@Param("begin") YearMonth begin,@Param("end") YearMonth end, @Param("streetCode") String streetCode);
}
