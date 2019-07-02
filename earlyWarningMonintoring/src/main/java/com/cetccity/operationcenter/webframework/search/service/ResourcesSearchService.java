package com.cetccity.operationcenter.webframework.search.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;
import com.cetccity.operationcenter.webframework.web.model.incident.NearbyResourcesModel;

import java.io.IOException;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.service
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:26 2019-03-12
 * Updater:     heliangming
 * Update_Date：9:26 2019-03-12
 * 更新描述:    heliangming 补充
 **/
public interface ResourcesSearchService {

    //一级对象搜索（提供搜索内容）
    MyPageInfoModel<List<SearchObjList>> searchByEsObjOne(String content, Integer page, Integer size,String tag)throws IOException;

    //一级对象搜索（提供搜索内容和指定的tag索引）
    //MyPageInfoModel<List<SearchObjList>> searchByEsObjTag(String content,Integer page,Integer size,String[] tableName_Obj);
    MyPageInfoModel<List<SearchObjList>> searchByEsObjOneToTag(String content,Integer pageNum,Integer pageSize,String[] tableName_Obj)throws IOException;
    //二级对象搜索（提供搜索内容和point点的周边资源）[已废弃]
    //MyPageInfoModel<List<SearchObjList>> searchByEsObj(String content, Integer page, Integer size, String jd, String wd);

    //特定point点周边的实体
    List<NearbyResourcesModel> searchByEsNearResourceObj(String[] tableName_Obj, String jd, String wd, String range)throws IOException;
}
