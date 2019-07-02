package com.cetccity.operationcenter.webframework.core.chart.engine.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.core.chart.engine
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/4 11:03
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/4 11:03
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
public interface ChartGeneralMapper {

    /**
     * 按天计算平均值
     * @param tbName 表名
     * @param computeCol 数值计算字段名
     * @param dateCol 时间字段名
     * @param decimal 小数位数
     * @return List<HashMap> 两个字段(value, month)
     */
    List<HashMap> avgColumnGroupByDay(
            String tbName,
            String computeCol,
            String dateCol,
            int decimal);


}
