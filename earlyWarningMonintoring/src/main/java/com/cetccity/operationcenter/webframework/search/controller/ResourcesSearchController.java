package com.cetccity.operationcenter.webframework.search.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.tools.PropertiesLoadUtils;
import com.cetccity.operationcenter.webframework.core.tools.XsteamUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.MapConfig;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TopGroup;
import com.cetccity.operationcenter.webframework.search.api.ResourcesSearchApi;
import com.cetccity.operationcenter.webframework.search.api.model.TagMenuConfig;
import com.cetccity.operationcenter.webframework.search.api.model.TopGroupMenu;
import com.cetccity.operationcenter.webframework.search.service.ResourcesSearchService;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;
import com.cetccity.operationcenter.webframework.web.model.incident.NearbyResourcesModel;
import com.cetccity.operationcenter.webframework.web.util.Constant;
import com.cetccity.operationcenter.webframework.web.util.apollo.ApolloPropertiesLoadUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:12 2019-03-12
 * Updater:     heliangming
 * Update_Date：9:12 2019-03-12
 * 更新描述:    heliangming 补充
 **/
@RestController
public class ResourcesSearchController implements ResourcesSearchApi {

    @Autowired
    ResourcesSearchService searchResourcesService;

    @Autowired
    PropertiesLoadUtils propertiesLoadUtils;

    public MyPageInfoModel<List<SearchObjList>> findResourceObj(String content, Integer pageNum, Integer pageSize,String tag) throws IOException {
        MyPageInfoModel<List<SearchObjList>> pageInfo;
        if("".equals(tag)||tag==null){
            pageInfo = searchResourcesService.searchByEsObjOne(content, pageNum, pageSize,tag);
        }else{
            String[] tableName_Obj = tag.split(",");
            pageInfo = searchResourcesService.searchByEsObjOneToTag(content, pageNum, pageSize,tableName_Obj);
        }
        return pageInfo;
    }

    public List<NearbyResourcesModel> findNearResourceObj(String poi, String jd, String wd, String range) throws IOException{
        String[] tableName_Obj = poi.split(",");
        List<NearbyResourcesModel> nearbyResourcesModelList = searchResourcesService.searchByEsNearResourceObj(tableName_Obj,jd,wd,range);
        return nearbyResourcesModelList;
    }

    public TagMenuConfig findTagMenu(){
        // XML转为Java对象
        TagMenuConfig mapConfig =  (TagMenuConfig) XsteamUtil.toBean(TagMenuConfig.class, ApolloPropertiesLoadUtils.readApollo(Constant.SEARCH_TAG_MENU));
        List<TopGroupMenu> topGroupMenuList = mapConfig.getTopGroupMenuList();
        TagMenuConfig mapConfig_return = new TagMenuConfig();
        mapConfig_return.setLabel(mapConfig.getLabel());
        mapConfig_return.setTopGroupMenuList(topGroupMenuList);
        return mapConfig_return;
    }
}

