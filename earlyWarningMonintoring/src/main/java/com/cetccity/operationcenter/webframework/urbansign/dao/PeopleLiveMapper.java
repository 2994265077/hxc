package com.cetccity.operationcenter.webframework.urbansign.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.dao
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/7 17:45
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/7 17:45
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Mapper
public interface PeopleLiveMapper {

    List<HashMap> queryAll();


    @Select("select street_name from COMMUNITY_CODE where STREET_CODE = #{STREET_CODE} and rownum < 2")
    String queryStreetNameByCode(String StreetCode);
}
