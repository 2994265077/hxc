package com.cetccity.operationcenter.webframework.alarmcenter.dao;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFORMATION;
import org.apache.ibatis.annotations.Mapper;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface AlarmInformationMapper {

    List<ALARM_INFORMATION> getALARM_INFORMATION(ALARM_INFORMATION aLARM_INFORMATION);

    int getCount(ALARM_INFORMATION aLARM_INFORMATION);

    List<ALARM_INFORMATION> earlyInformationCenter(ALARM_INFORMATION aLARM_INFORMATION);

    List<ALARM_INFORMATION> alarmFour();

    List<HashMap> iotDeviceTrendThree();

}
