package com.cetccity.operationcenter.webframework.search.service;

import com.cetccity.operationcenter.webframework.web.model.incident.NearbyResourcesModel;

import java.io.IOException;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:34 2019-03-28
 * Updater:     heliangming
 * Update_Date：10:34 2019-03-28
 * 更新描述:    heliangming 补充
 **/
public interface PolygonSearchService {

    List<NearbyResourcesModel> findPolygonResourceObj(String[] tableName_Obj, String poiJson) throws IOException;
}
