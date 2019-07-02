package com.cetccity.operationcenter.webframework.hiddendanger.controller.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.detail.QAJJ_PUCENTP_VDetailApi;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TipDetailOfList;
import com.cetccity.operationcenter.webframework.hiddendanger.service.QAJJ_PUCENTP_VService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QAJJ_PUCENTP_VDetailController implements QAJJ_PUCENTP_VDetailApi {
    
    Logger logger = LoggerFactory.getLogger(UrbanRiskController.class);

    @Autowired
    QAJJ_PUCENTP_VService qAJJ_PUCENTP_VService;

    public TipDetailOfList getQAJJ_PUCENTP_VDetailInformation(String id){
        TipDetailOfList QAJJ_PUCENTP_VDetailOfList = qAJJ_PUCENTP_VService.getQAJJ_PUCENTP_VDetailInformation(id);
        return QAJJ_PUCENTP_VDetailOfList;
    }

    public MyPageInfoModel getQAJJ_GRID_VDetailInformationOfListRegulatoryAgency(String id, Integer pageNum, Integer pageSize){
        MyPageInfoModel pageInfo = qAJJ_PUCENTP_VService.getQAJJ_GRID_VDetailInformationOfListRegulatoryAgency(id,pageNum,pageSize);
        return pageInfo;
    }

    public MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAssessment(String id,Integer pageNum,Integer pageSize){
        MyPageInfoModel pageInfo = qAJJ_PUCENTP_VService.getQAJJ_PUCENTP_VDetailInformationOfListRiskAssessment(id,pageNum,pageSize);
        return pageInfo;
    }

    public MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskDanger(String id,Integer pageNum,Integer pageSize){
        MyPageInfoModel pageInfo = qAJJ_PUCENTP_VService.getQAJJ_PUCENTP_VDetailInformationOfListRiskDanger(id,pageNum,pageSize);
        return pageInfo;
    }

    public MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAlarm(String id,Integer pageNum,Integer pageSize){
        MyPageInfoModel pageInfo = qAJJ_PUCENTP_VService.getQAJJ_PUCENTP_VDetailInformationOfListRiskAlarm(id,pageNum,pageSize);
        return pageInfo;
    }

    public MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAccident(String id,Integer pageNum,Integer pageSize){
        MyPageInfoModel pageInfo = qAJJ_PUCENTP_VService.getQAJJ_PUCENTP_VDetailInformationOfListRiskAccident(id,pageNum,pageSize);
        return pageInfo;
    }

}
