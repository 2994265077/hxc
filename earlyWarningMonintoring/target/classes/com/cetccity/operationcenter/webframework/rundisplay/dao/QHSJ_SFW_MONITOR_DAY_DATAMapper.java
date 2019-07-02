package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QHSJ_SFW_MONITOR_DAY_DATA;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QHSJ_SFW_MONITOR_DAY_DATAMapper {

    List<QHSJ_SFW_MONITOR_DAY_DATA> getSFWStationDataOfDay(Map<String,String> map);
}
