package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATAMapper {

    List<QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA> getSFWStationDataOfTime(Map<String,String> map_time);

    List<NameValueUnitModel> currentValue(@Param("siteCode") String siteCode);

    List<QHSJ_SFW_MONITOR_HOUR_AND_DAY_DATA> getSFWStationDataOfDay(Map<String,String> map_time);
}
