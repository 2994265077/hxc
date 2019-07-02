package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.web.model.iot.IOT_DEVICE;
import com.cetccity.operationcenter.webframework.web.model.iot.IotDeviceModel;
import com.cetccity.operationcenter.webframework.web.model.iot.IotDeviceModelExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface IotDeviceMapper {

    int getIOT_DEVICE_Increment();

    List<IOT_DEVICE> getIOT_DEVICE_OBJ(IOT_DEVICE iOT_DEVICE);

    List<IOT_DEVICE> getIOT_DEVICE_list(@Param("DEVICE_CODE") String DEVICE_CODE);

    int insertIOT_DEVICE(IOT_DEVICE iOT_DEVICE);

    List<HashMap> iotDeviceTrendTwo();

    int deleteIOT_DEVICE(IOT_DEVICE iOT_DEVICE);

    long countByExample(IotDeviceModelExample example);

    int deleteByExample(IotDeviceModelExample example);

    int insert(IotDeviceModel record);

    int insertSelective(IotDeviceModel record);

    List<IotDeviceModel> selectByExample(IotDeviceModelExample example);

    int updateByExampleSelective(@Param("record") IotDeviceModel record, @Param("example") IotDeviceModelExample example);

    int updateByExample(@Param("record") IotDeviceModel record, @Param("example") IotDeviceModelExample example);
}
