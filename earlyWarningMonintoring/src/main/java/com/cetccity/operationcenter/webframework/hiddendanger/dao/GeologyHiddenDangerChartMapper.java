package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.dao
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/20 10:06
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/20 10:06
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Mapper
public interface GeologyHiddenDangerChartMapper {

    /**
     * 查询地质隐患总数
     * @return
     */
    int countAll(@Param("streetCode") String streetCode);

    /**
     * 按隐患类型统计数量
     * @return
     */
    List<NameValueTypeModel> countHiddenType(@Param("streetCode") String streetCode);

    /**
     * 巡查类型
     * @return
     */
    List<NameValueTypeModel> countCheckType(@Param("streetCode") String streetCode);

    /**
     * 预测危险性
     * @return
     */
    List<NameValueTypeModel> countForecastDanger(@Param("streetCode") String streetCode);

}
