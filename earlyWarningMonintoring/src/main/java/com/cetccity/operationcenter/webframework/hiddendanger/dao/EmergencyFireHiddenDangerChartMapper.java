package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.dao
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/19 21:00
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/19 21:00
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Mapper
public interface EmergencyFireHiddenDangerChartMapper {

    /**
     * 统计五种数据:依次是
     * 避难场所、救援队伍数、风险源数、应急仓库数、应急物资数（需要乘以数量）
     * @return
     */
    List<NameValueTypeModel> count5Num(@Param("streetCode") String streetCode);

    /**
     * 按避难场所容纳量统计
     */
    List<NameValueTypeModel> queryEmergencyShelterCapacity(@Param("streetCode") String streetCode);

    /**
     * 危险源类别统计
     * @return
     */
    List<NameValueTypeModel<Integer>> queryEmergencyHarzardType(@Param("streetCode") String streetCode);

    /**
     * 应急仓库对应的物资数量
     */

    List<NameValueTypeModel> queryEmergencyHouseAndSource(@Param("streetCode") String streetCode);
}
