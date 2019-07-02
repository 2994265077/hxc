package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.IOT_MONITORING_DATA_ENCODE;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IotMonitoringDataEncodeMapper {

    List<IOT_MONITORING_DATA_ENCODE> getIOT_MONITORING_DATA_ENCODE(IOT_MONITORING_DATA_ENCODE iOT_MONITORING_DATA_ENCODE);

}
