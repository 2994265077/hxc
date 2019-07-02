package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QHSJ_AIR_MONITOR_DAY_DATA;
import com.cetccity.operationcenter.webframework.urbansign.api.model.NameValueDataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface QhsjAirMonitorDayDataMapper {

    List<QHSJ_AIR_MONITOR_DAY_DATA> getAirStationDataOfDay (Map<String,String> map_day);

    List<QHSJ_AIR_MONITOR_DAY_DATA> getAirStationValueOfDay(Map<String,String> map_day);

    List<NameValueTypeModel<List<NameValueModel>>> countAirAvgValue(@Param("begin") LocalDateTime begin);
}
