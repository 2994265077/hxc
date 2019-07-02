package com.cetccity.operationcenter.webframework.rundisplay.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;

import java.util.List;

public interface RunDisplayService {

    List<LoadMap> loadVideoMapByDB(String tableName, String layerid, String name,String id,String street);

    List<LoadMap> loadVideoMapByDBForStreet(String tableName,String layerid,String StreetCode,String name,String id,String street);
 }
