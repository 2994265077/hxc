package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.web.dao.LoadMapMapper;
import com.cetccity.operationcenter.webframework.web.dao.gis.GisLoadMapMapper;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.web.service.LoadMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loadMapService")
public class LoadMapServiceImpl implements LoadMapService{

    @Autowired
    LoadMapMapper loadMapMapper;

    @Autowired
    GisLoadMapMapper gisLoadMapMapper;

    public List<LoadMap> loadMap(String tableName){
        return loadMapMapper.loadMap(tableName);
    }

    public List<LoadMap> gisLoadMap(String tableName){
        return gisLoadMapMapper.gisLoadMap(tableName);
    }

    public List<LoadMap> loadMapByDBVedio(){
        return loadMapMapper.loadMapByDBVedio();
    }

    public List<LoadMap> loadMapByVideoCodeDB(String video_code){
        return loadMapMapper.loadMapByVideoCodeDB(video_code);
    }
}
