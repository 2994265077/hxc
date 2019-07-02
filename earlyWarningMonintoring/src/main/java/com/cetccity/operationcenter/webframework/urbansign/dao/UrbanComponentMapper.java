package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.dao
 * @Project: futian
 * @Creator: huangzezhou
 * @Create_Date: 2019/6/24 10:03
 * @Updater: huangzezhou
 * @Update_Date: 2019/6/24 10:03
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Mapper
public interface UrbanComponentMapper {

    List<NameValueModel> countDeptComponent(@Param("streetName") String streetName, @Param("tableNames") List<String> tableNames);

    List<NameValueModel> countStreetDeptComponent(@Param("tableNames") List<String> tableNames);

    List<NameValueTypeModel<Integer>> countStreetCodeDeptComponent(@Param("tableNames") List<String> tableNames);

    List<NameValueModel> countCommunityDeptComponent(@Param("streetName") String streetName, @Param("tableNames") List<String> tableNames);

}
