package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.URBAN_RISK;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface UrbanRiskMapper {

    List<URBAN_RISK> getURBAN_RISKObj(@Param("id") String id);

    List<URBAN_RISK> getURBAN_RISKList(URBAN_RISK uRBAN_RISK);

    int getCount(Map map);
}
