package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoadMapMapper {

    List<LoadMap> loadMap(@Param("tableName") String tableName);

    List<LoadMap> loadMapByDBVedio();

    List<LoadMap> loadMapByVideoCodeDB(@Param("video_code") String video_code);

}
