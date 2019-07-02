package com.cetccity.operationcenter.webframework.search.service;

import com.cetccity.operationcenter.webframework.web.model.build.LoadMapBuildGrade;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.service
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:21 2019-03-12
 * Updater:     heliangming
 * Update_Date：17:21 2019-03-12
 * 更新描述:    heliangming 补充
 **/
public interface PlanLandLoadMapService {

    List<LoadMap> loadMapResolution(String id);

    List<LoadMapBuildGrade> loadMapWebService(String id);

    List<LoadMap> loadMapWebServicePoint(String id);
}
