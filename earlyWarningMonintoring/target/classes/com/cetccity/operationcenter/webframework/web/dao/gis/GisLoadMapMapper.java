package com.cetccity.operationcenter.webframework.web.dao.gis;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GisLoadMapMapper {

    List<LoadMap> gisLoadMap(@Param("tableName") String tableName);

    List<LoadMap> loadMapByDBVedio();

}
