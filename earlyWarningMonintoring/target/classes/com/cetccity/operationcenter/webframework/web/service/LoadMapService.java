package com.cetccity.operationcenter.webframework.web.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;

import java.util.List;

public interface LoadMapService {

    List<LoadMap> loadMap(String tableName);

    List<LoadMap> gisLoadMap(String tableName);

    List<LoadMap> loadMapByDBVedio();

    List<LoadMap> loadMapByVideoCodeDB(String video_code);

}
