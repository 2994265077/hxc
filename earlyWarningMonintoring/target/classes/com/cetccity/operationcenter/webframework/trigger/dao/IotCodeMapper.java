package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Package: com.cetc.cloud.datasynch.provider.dao.input
 * @Project: dataSyncher
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/12 9:26
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/12 9:26
 * @Update_Description: huangzezhou 补充
 * @Description: AlarmTriggerMapper
 **/
@Mapper
public interface IotCodeMapper{

    @Select("select OBJECT_ID,DEVICE_NAME,DEVICE_TYPE FROM IOT_CODE where device_type=#{device_code} ")
    IotCode queryIOT_CODE(String device_code);
}
