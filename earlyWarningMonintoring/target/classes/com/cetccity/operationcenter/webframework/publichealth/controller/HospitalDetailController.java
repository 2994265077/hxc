package com.cetccity.operationcenter.webframework.publichealth.controller;

import com.cetccity.operationcenter.webframework.publichealth.api.HospitalDetailApi;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.*;
import com.cetccity.operationcenter.webframework.publichealth.service.HospitalDetailService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.controller
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:36 2019-02-27
 * Updater:     heliangming
 * Update_Date：11:36 2019-02-27
 * 更新描述:    heliangming 补充
 **/
@RestController
public class HospitalDetailController implements HospitalDetailApi {

    @Autowired
    HospitalDetailService hospitalDetailService;

    public PageInfo<YJJC_QWJJ_OUT_VISITS_DAY_V> hospitalOutpatientInformation(String ORG_CODE,Integer pageNum,Integer pageSize){
        PageInfo<YJJC_QWJJ_OUT_VISITS_DAY_V> pageInfo = hospitalDetailService.hospitalOutpatientInformation(ORG_CODE, pageNum, pageSize);
        return pageInfo;
    }

    public PageInfo<YJJC_QWJJ_IN_VISITS_DAY_V> hospitalHospitalizationInformation(String ORG_CODE, Integer pageNum, Integer pageSize){
        PageInfo<YJJC_QWJJ_IN_VISITS_DAY_V> pageInfo = hospitalDetailService.hospitalHospitalizationInformation(ORG_CODE, pageNum, pageSize);
        return pageInfo;
    }

    public PageInfo<YJJC_QWJJ_EMPLOYEE_V> hospitalHealthPersonnel(String ORG_CODE, Integer pageNum, Integer pageSize){
        PageInfo<YJJC_QWJJ_EMPLOYEE_V> pageInfo = hospitalDetailService.hospitalHealthPersonnel(ORG_CODE, pageNum, pageSize);
        return pageInfo;
    }

    public PageInfo<YJJC_QWJJ_ORG_V> hospitalDepartmentInformation(String ORG_CODE, Integer pageNum, Integer pageSize){
        PageInfo<YJJC_QWJJ_ORG_V> pageInfo = hospitalDetailService.hospitalDepartmentInformation(ORG_CODE, pageNum, pageSize);
        return pageInfo;
    }

    public PageInfo<YJJC_QWJJ_SDM_INFO_V> hospitalSpecialDiseases(String ORG_CODE, Integer pageNum, Integer pageSize){
        PageInfo<YJJC_QWJJ_SDM_INFO_V> pageInfo = hospitalDetailService.hospitalSpecialDiseases(ORG_CODE, pageNum, pageSize);
        return pageInfo;
    }
}
