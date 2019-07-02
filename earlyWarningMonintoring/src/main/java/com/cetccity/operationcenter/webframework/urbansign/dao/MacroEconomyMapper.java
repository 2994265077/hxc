package com.cetccity.operationcenter.webframework.urbansign.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.dao
 * @Project: futian
 * @Creator: huangzezhou
 * @Create_Date: 2019/6/3 9:45
 * @Updater: huangzezhou
 * @Update_Date: 2019/6/3 9:45
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Mapper
public interface MacroEconomyMapper {

    String queryValueByName(@Param("name") String name);

}
