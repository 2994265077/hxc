package com.cetccity.operationcenter.webframework.trigger.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.dao
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2019/4/3 11:57
 * @Updater: huangzezhou
 * @Update_Date: 2019/4/3 11:57
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Mapper
public interface BuildScoreMonthAvgMapper {
    /**
     * 功能描述: <br>
     * 〈每月统计建筑风险分数平均值〉
     *
     * @param yearMonth 年月  格式(yyyy-MM)
     * @return:void
     * @Author:dongxin
     * @Date: 2019/4/3 11:59
     */
    void avgMonthScore(String yearMonth);

    /**
     * 功能描述: <br>
     * 〈根据年月删除数据〉
     * 删除符合条件年月的数据
     * @param yearMonth 年月  格式(yyyy-MM)
     * @return:void
     * @Author:dongxin
     * @Date: 2019/4/3 14:36
     */
    void deleteByYearMonth(String yearMonth);
}
