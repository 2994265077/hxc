package com.cetccity.operationcenter.webframework.hiddendanger.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TipDetailOfList;

public interface QAJJ_PUCENTP_VService {
    
    TipDetailOfList getQAJJ_PUCENTP_VDetailInformation(String id);

    MyPageInfoModel getQAJJ_GRID_VDetailInformationOfListRegulatoryAgency(String id, Integer pageNum, Integer pageSize);

    MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAssessment(String id, Integer pageNum, Integer pageSize);

    MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskDanger(String id, Integer pageNum, Integer pageSize);

    MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAlarm(String id, Integer pageNum, Integer pageSize);

    MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAccident(String id, Integer pageNum, Integer pageSize);
    
}
