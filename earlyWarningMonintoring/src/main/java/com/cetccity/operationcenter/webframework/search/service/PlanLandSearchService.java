package com.cetccity.operationcenter.webframework.search.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;

import java.io.IOException;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.service
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:08 2019-03-12
 * Updater:     heliangming
 * Update_Date：10:08 2019-03-12
 * 更新描述:    heliangming 补充
 **/
public interface PlanLandSearchService {

    MyPageInfoModel<List<SearchObjList>> searchByAddressResolutionObjOne(String content, Integer pageNum, Integer pageSize)throws Exception;

    MyPageInfoModel<List<SearchObjList>> searchByAddressWebServiceObjOne(String content, Integer pageNum, Integer pageSize);
}
