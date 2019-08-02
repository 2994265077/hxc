package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.dao
 * @Project: futian
 * @Creator: huangzezhou
 * @Create_Date: 2019/8/1 16:14
 * @Updater: huangzezhou
 * @Update_Date: 2019/8/1 16:14
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Mapper
public interface PucentpMapper {

    List<NameValueTypeModel> pieByStreet(@Param("street_code") String streetCode);

    List<NameValueTypeModel> pieByCommunity(@Param("street_code") String streetCode);

}
