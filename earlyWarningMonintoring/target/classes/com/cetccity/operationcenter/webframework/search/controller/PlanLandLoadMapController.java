package com.cetccity.operationcenter.webframework.search.controller;

import com.cetccity.operationcenter.webframework.search.api.PlanLandLoadMapApi;
import com.cetccity.operationcenter.webframework.search.service.PlanLandLoadMapService;
import com.cetccity.operationcenter.webframework.web.model.build.LoadMapBuildGrade;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.controller.map
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:11 2019-03-12
 * Updater:     heliangming
 * Update_Date：17:11 2019-03-12
 * 更新描述:    heliangming 补充
 **/
@RestController
public class PlanLandLoadMapController implements PlanLandLoadMapApi {

    @Autowired
    PlanLandLoadMapService planLandLoadMapService;

    public List<LoadMap> loadMapResolution(String id){
        return planLandLoadMapService.loadMapResolution(id);
    }

    public List<LoadMapBuildGrade> loadMapWebService(String id){
        return planLandLoadMapService.loadMapWebService(id);
    }

    public List<LoadMap> loadMapWebServicePoint(String id){
        return planLandLoadMapService.loadMapWebServicePoint(id);
    }
}
