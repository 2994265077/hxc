package com.cetccity.operationcenter.webframework.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.tools.ListToPageInfoUtil;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.search.api.model.ResolutionModel;
import com.cetccity.operationcenter.webframework.search.api.model.WebServiceModel;
import com.cetccity.operationcenter.webframework.search.service.PlanLandSearchService;
import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:09 2019-03-12
 * Updater:     heliangming
 * Update_Date：10:09 2019-03-12
 * 更新描述:    heliangming 补充
 **/
@Service
public class PlanLandSearchServiceImpl implements PlanLandSearchService {
	@Autowired
	private CommonInstance commonInstance;

    static List<SearchObjList> searchObjList_list = new ArrayList<>();

    static List<SearchObjList> webService_list = new ArrayList<>();

    public MyPageInfoModel<List<SearchObjList>> searchByAddressResolutionObjOne(String content, Integer pageNum, Integer pageSize)throws Exception{
        String addressSearchLink = commonInstance.getAddressSearch();
        String urlStr = addressSearchLink+"'"+content+"'";
        String source = UrlApiToSource.httpGet(urlStr);
        List<ResolutionModel> resolutionModel_list = JsonUtils.jsonToList(source,ResolutionModel.class);
        int id = 0;
        String layerid;
        layerid = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties","31project_april.AddressResolution");
        List<SearchObjList> searchObjList_new = new ArrayList<>();
        for (ResolutionModel u:resolutionModel_list) {
            SearchObjList searchObjList = new SearchObjList();
            searchObjList.setId(String.valueOf(++id));
            searchObjList.setTableName("AddressResolution");
            searchObjList.setJd(u.getLon());
            searchObjList.setWd(u.getLat());
            searchObjList.setName(u.getName());
            searchObjList.setAddress(u.getAddress());
            searchObjList.setLayerid(layerid);
            searchObjList_new.add(searchObjList);
        }
        searchObjList_list = searchObjList_new;
        MyPageInfoModel<List<SearchObjList>> pageInfo = (MyPageInfoModel<List<SearchObjList>>) ListToPageInfoUtil.listToPageInfo(searchObjList_list,pageNum,pageSize);
        return pageInfo;
    }

    public MyPageInfoModel<List<SearchObjList>> searchByAddressWebServiceObjOne(String content, Integer pageNum, Integer pageSize){
        MyPageInfoModel<List<SearchObjList>> pageInfo = new MyPageInfoModel();
        String comprehensiveSearchLink = commonInstance.getComprehensiveSearch();
        String urlStr = comprehensiveSearchLink+content+"&curPage=1&met=FULLQUERY_SEACHWEB_GO";
        String source = UrlApiToSource.httpGet(urlStr);
        Map<String,String> map_source = (Map) JSON.parse(source);
        String source_records = JsonUtils.objectToJson(map_source.get("records"));
        //source_records过滤<span class='hlShow'>和</span>和广东省深圳市
        source_records = source_records.replaceAll("<span class='hlShow'>","").replaceAll("</span>","").replaceAll("广东省深圳市","");
        List<WebServiceModel> webServiceModel_list = JsonUtils.jsonToList(source_records,WebServiceModel.class);
        String name,address,layerid;
        List<SearchObjList> webService_list_new = new ArrayList<>();
        layerid = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties","31project_april.AddressWebService");
        for (WebServiceModel u:webServiceModel_list) {
            if("".equals(u.getCOMMCODE().getColumnVal())) {
                continue;
            }
            if(u.getBBASEBUILDINGNAME()!=null){
                name = u.getBBASEBUILDINGNAME().getColumnVal();
            }else{
                name = u.getHOUSE_DETAIL_HOUSE_SHORT_ADDR().getColumnVal();
            }
            if(u.getBT3_BUILDING_FULL_ADDR()!=null){
                address = u.getBT3_BUILDING_FULL_ADDR().getColumnVal();
            }else{
                address = u.getHOUSE_STANDARD_HOUSE_FULL_ADDR().getColumnVal();
            }
            SearchObjList searchObjList = new SearchObjList();
            searchObjList.setId(u.getCOMMCODE().getColumnVal());
            searchObjList.setName(name);
            searchObjList.setLayerid(layerid);
            searchObjList.setAddress(address);
            webService_list_new.add(searchObjList);
        }
        webService_list = webService_list_new;
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(10);
        pageInfo.setPages(1);
        pageInfo.setList(webService_list);
        pageInfo.setTotal(webService_list.size());
        return pageInfo;
    }

}
