package com.cetccity.operationcenter.webframework.search.service.impl;

import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.BuildOfDangerSoft;
import com.cetccity.operationcenter.webframework.search.service.PlanLandLoadMapService;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;
import com.cetccity.operationcenter.webframework.web.model.build.LoadMapBuildGrade;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:21 2019-03-12
 * Updater:     heliangming
 * Update_Date：17:21 2019-03-12
 * 更新描述:    heliangming 补充
 **/
@Service
public class PlanLandLoadMapServiceImpl implements PlanLandLoadMapService {

    @Autowired
    PlanLandSearchServiceImpl planLandSearchServiceImpl;

    @Autowired
    BuildOfDangerSoft buildOfDangerSoft;

    @Autowired
    OracleOperateService oracleOperateService;

    public List<LoadMap> loadMapResolution(String id){
        List<LoadMap> loadMap_list = new ArrayList<>();
        List<SearchObjList> searchObjList_list = planLandSearchServiceImpl.searchObjList_list;
        searchObjList_list.stream().filter(u->u.getId().equals(id)).forEach(u->{
            LoadMap loadMap = new LoadMap();
            loadMap.setId(u.getId());
            loadMap.setJd(u.getJd());
            loadMap.setWd(u.getWd());
            loadMap.setTableName(u.getTableName());
            loadMap.setLayerid(u.getLayerid());
            loadMap_list.add(loadMap);
        });
        return loadMap_list;
    }

    public List<LoadMapBuildGrade> loadMapWebService(String id){
        List<LoadMapBuildGrade> loadMapBuildGrade_list_return = new ArrayList<>();
        List<LoadMapBuildGrade> loadMapBuildGrade_list = buildOfDangerSoft.getBuildDangerLoadMap();
        loadMapBuildGrade_list.stream().filter(u->u.getBLDG_NO().equals(id)).forEach(u-> loadMapBuildGrade_list_return.add(u));
        return loadMapBuildGrade_list_return;
    }

    public List<LoadMap> loadMapWebServicePoint(String id){
        List<LoadMap> loadMap_list = new ArrayList<>();
        List<LoadMapBuildGrade> loadMapBuildGrade_list = buildOfDangerSoft.getBuildDangerLoadMap();
        String tableName,layerid,JD84,WD84;
        //根据建筑id获取中心点数据
        List<LinkedHashMap> map_list = oracleOperateService.querySql("select JD84,WD84 from BLK_BUILDING where LDDM = '"+id+"'");
        JD84 = (String) map_list.get(0).get("JD84");
        WD84 = (String) map_list.get(0).get("WD84");
        tableName = "AddressWebService";
        layerid = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", "31project_april.AddressWebService_point");
        loadMapBuildGrade_list.stream().filter(u -> u.getBLDG_NO().equals(id)).forEach(u -> {
                    LoadMap loadMap = new LoadMap();
                    loadMap.setId(id);
                    loadMap.setTableName(tableName);
                    loadMap.setLayerid(layerid);
                    loadMap.setJd(JD84);
                    loadMap.setWd(WD84);
                    loadMap_list.add(loadMap);
                });
        return loadMap_list;
    }

}
