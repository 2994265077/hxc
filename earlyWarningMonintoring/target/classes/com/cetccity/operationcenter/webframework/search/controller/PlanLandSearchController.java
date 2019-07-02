package com.cetccity.operationcenter.webframework.search.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.search.api.PlanLandSearchApi;
import com.cetccity.operationcenter.webframework.search.service.PlanLandSearchService;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.controller
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:08 2019-03-12
 * Updater:     heliangming
 * Update_Date：10:08 2019-03-12
 * 更新描述:    heliangming 补充
 **/
@RestController
public class PlanLandSearchController implements PlanLandSearchApi {

    @Autowired
    PlanLandSearchService planLandService;

    public MyPageInfoModel<List<SearchObjList>> addressResolution(String content, Integer pageNum, Integer pageSize) throws Exception {
        MyPageInfoModel<List<SearchObjList>> pageInfo;
        pageInfo = planLandService.searchByAddressResolutionObjOne(content, pageNum, pageSize);
        return pageInfo;
    }

    public MyPageInfoModel<List<SearchObjList>> addressWebService(String content, Integer pageNum, Integer pageSize) throws IOException {
        MyPageInfoModel<List<SearchObjList>> pageInfo;
        pageInfo = planLandService.searchByAddressWebServiceObjOne(content, pageNum, pageSize);
        return pageInfo;
    }
}
