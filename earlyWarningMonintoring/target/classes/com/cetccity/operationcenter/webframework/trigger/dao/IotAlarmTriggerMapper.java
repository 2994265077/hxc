package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotDevice;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotEvent;
import com.cetccity.operationcenter.webframework.trigger.entity.iot.IotEventUnion;
import com.cetccity.operationcenter.webframework.trigger.entity.ObjectBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
public interface IotAlarmTriggerMapper {

    @Select("select address alarm_object, object_id f_object_row_id, JD84, WD84, ADDRESS,REGION_CODE,STREET_CODE,STREET_NAME,COMMUNITY_CODE,COMMUNITY_NAME from IOT_DEVICE where address is not null and device_code=#{device_code} ")
    List<ObjectBaseInfo> queryBaseInfo(@Param("device_code") String device_code);

    @Select("select * FROM IOT_DEVICE where device_code=#{device_code} ")
    IotDevice queryDevice(String device_code);

    List<IotEvent> querySourceData(Map map_time);

    List<IotEventUnion> querySource(@Param("begin") LocalDateTime begin, @Param("data_code") String dataCode);

    List<IotEventUnion> querySourceByMinId(@Param("object_id") String id);
}
