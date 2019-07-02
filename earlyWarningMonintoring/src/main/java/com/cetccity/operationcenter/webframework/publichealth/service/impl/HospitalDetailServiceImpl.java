package com.cetccity.operationcenter.webframework.publichealth.service.impl;

import com.cetccity.operationcenter.webframework.publichealth.dao.*;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.*;
import com.cetccity.operationcenter.webframework.publichealth.service.HospitalDetailService;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_SDM_INFO_VMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:38 2019-02-27
 * Updater:     heliangming
 * Update_Date：11:38 2019-02-27
 * 更新描述:    heliangming 补充
 **/
@Service
public class HospitalDetailServiceImpl implements HospitalDetailService {

    @Autowired
    YJJC_QWJJ_OUT_VISITS_DAY_VMapper yJJC_QWJJ_OUT_VISITS_DAY_VMapper;

    @Autowired
    YJJC_QWJJ_IN_VISITS_DAY_VMapper yJJC_QWJJ_IN_VISITS_DAY_VMapper;

    @Autowired
    YJJC_QWJJ_EMPLOYEE_VMapper yJJC_QWJJ_EMPLOYEE_VMapper;

    @Autowired
    YJJC_QWJJ_ORG_VMapper yJJC_QWJJ_ORG_VMapper;

    @Autowired
    YJJC_QWJJ_SDM_INFO_VMapper yJJC_QWJJ_SDM_INFO_VMapper;

    public PageInfo<YJJC_QWJJ_OUT_VISITS_DAY_V> hospitalOutpatientInformation(String ORG_CODE,Integer pageNum,Integer pageSize){
        //TODO:1.查询judge表中满足条件的记录(分页)
        PageHelper.startPage(pageNum, pageSize);
        YJJC_QWJJ_OUT_VISITS_DAY_V yJJC_QWJJ_OUT_VISITS_DAY_V = new YJJC_QWJJ_OUT_VISITS_DAY_V();
        yJJC_QWJJ_OUT_VISITS_DAY_V.setORG_CODE(ORG_CODE);
        PageInfo<YJJC_QWJJ_OUT_VISITS_DAY_V> pageInfo = new PageInfo(yJJC_QWJJ_OUT_VISITS_DAY_VMapper.getYJJC_QWJJ_OUT_VISITS_DAY_V(yJJC_QWJJ_OUT_VISITS_DAY_V));
        return pageInfo;
    }

    public PageInfo<YJJC_QWJJ_IN_VISITS_DAY_V> hospitalHospitalizationInformation(String ORG_CODE, Integer pageNum, Integer pageSize){
        //TODO:1.查询judge表中满足条件的记录(分页)
        PageHelper.startPage(pageNum, pageSize);
        YJJC_QWJJ_IN_VISITS_DAY_V yJJC_QWJJ_IN_VISITS_DAY_V = new YJJC_QWJJ_IN_VISITS_DAY_V();
        yJJC_QWJJ_IN_VISITS_DAY_V.setORG_CODE(ORG_CODE);
        PageInfo<YJJC_QWJJ_IN_VISITS_DAY_V> pageInfo = new PageInfo(yJJC_QWJJ_IN_VISITS_DAY_VMapper.getYJJC_QWJJ_IN_VISITS_DAY_V(yJJC_QWJJ_IN_VISITS_DAY_V));
        return pageInfo;
    }

    public PageInfo<YJJC_QWJJ_EMPLOYEE_V> hospitalHealthPersonnel(String ORG_CODE, Integer pageNum, Integer pageSize){
        //TODO:1.查询judge表中满足条件的记录(分页)
        PageHelper.startPage(pageNum, pageSize);
        YJJC_QWJJ_EMPLOYEE_V yJJC_QWJJ_EMPLOYEE_V = new YJJC_QWJJ_EMPLOYEE_V();
        yJJC_QWJJ_EMPLOYEE_V.setORG_CODE(ORG_CODE);
        PageInfo<YJJC_QWJJ_EMPLOYEE_V> pageInfo = new PageInfo(yJJC_QWJJ_EMPLOYEE_VMapper.getYJJC_QWJJ_EMPLOYEE_V(yJJC_QWJJ_EMPLOYEE_V));
        return pageInfo;
    }

    public PageInfo<YJJC_QWJJ_ORG_V> hospitalDepartmentInformation(String ORG_CODE, Integer pageNum, Integer pageSize){
        //TODO:1.查询judge表中满足条件的记录(分页)
        PageHelper.startPage(pageNum, pageSize);
        YJJC_QWJJ_ORG_V yJJC_QWJJ_ORG_V = new YJJC_QWJJ_ORG_V();
        yJJC_QWJJ_ORG_V.setORG_CODE(ORG_CODE);
        PageInfo<YJJC_QWJJ_ORG_V> pageInfo = new PageInfo(yJJC_QWJJ_ORG_VMapper.getYJJC_QWJJ_ORG_V(yJJC_QWJJ_ORG_V));
        return pageInfo;
    }

    public PageInfo<YJJC_QWJJ_SDM_INFO_V> hospitalSpecialDiseases(String ORG_CODE, Integer pageNum, Integer pageSize){
        //TODO:1.查询judge表中满足条件的记录(分页)
        PageHelper.startPage(pageNum, pageSize);
        YJJC_QWJJ_SDM_INFO_V yJJC_QWJJ_SDM_INFO_V = new YJJC_QWJJ_SDM_INFO_V();
        yJJC_QWJJ_SDM_INFO_V.setORG_CODE(ORG_CODE);
        PageInfo<YJJC_QWJJ_SDM_INFO_V> pageInfo = new PageInfo(yJJC_QWJJ_SDM_INFO_VMapper.getYJJC_QWJJ_SDM_INFO_V(yJJC_QWJJ_SDM_INFO_V));
        return pageInfo;
    }
}
