package com.cetccity.operationcenter.webframework.alarmcenter.dao;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFO_CODE;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ALARM_INFO_CODEMapper {

    List<ALARM_INFO_CODE> getALARM_INFO_CODE(ALARM_INFO_CODE aLARM_INFO_CODE);

    List<ALARM_INFO_CODE> getRemovalALARM_INFO_CODE(ALARM_INFO_CODE aLARM_INFO_CODE);

    List<ALARM_INFO_CODE> getLowerOneDrillDown(ALARM_INFO_CODE aLARM_INFO_CODE);

}
