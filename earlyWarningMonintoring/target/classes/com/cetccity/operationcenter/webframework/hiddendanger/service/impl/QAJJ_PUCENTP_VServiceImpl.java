package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TheadTbodyModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.RiskHiddenDanger;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TipDetailOfList;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.QajjGridVMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.QajjPucentpVMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.QajjRepaccidentVMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.RiskHiddenDangerMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.QAJJ_GRID_V;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.QAJJ_PUCENTP_V;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.QAJJ_REPACCIDENT_V;
import com.cetccity.operationcenter.webframework.hiddendanger.service.QAJJ_PUCENTP_VService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QAJJ_PUCENTP_VServiceImpl implements QAJJ_PUCENTP_VService {
    
    @Autowired
    QajjPucentpVMapper qAJJ_PUCENTP_VMapper;

    @Autowired
    QajjGridVMapper qAJJ_GRID_VMapper;

    @Autowired
    RiskHiddenDangerMapper riskHiddenDangerMapper;

    @Autowired
    QajjRepaccidentVMapper qAJJ_REPACCIDENT_VMapper;

    public TipDetailOfList getQAJJ_PUCENTP_VDetailInformation(String id){
        TipDetailOfList qAJJ_PUCENTP_VDetailOfList = new TipDetailOfList();
        QAJJ_PUCENTP_V qAJJ_PUCENTP_V = new QAJJ_PUCENTP_V();
        qAJJ_PUCENTP_V.setROWID(id);
        List<QAJJ_PUCENTP_V> qAJJ_PUCENTP_V_list = qAJJ_PUCENTP_VMapper.getQAJJ_PUCENTP_V(qAJJ_PUCENTP_V);
        //纳管企业详情--基本信息
        LinkedHashMap map_basic = new LinkedHashMap();
        LinkedHashMap map_location = new LinkedHashMap();
        map_location.put("lon",qAJJ_PUCENTP_V_list.get(0).getJD84());
        map_location.put("lat",qAJJ_PUCENTP_V_list.get(0).getWD84());
        map_basic.put("危险源名称",qAJJ_PUCENTP_V_list.get(0).getENTPNAME());
        map_basic.put("机构名称","安监局");
        map_basic.put("风险地址",qAJJ_PUCENTP_V_list.get(0).getAREACODE());
        map_basic.put("详细地址",qAJJ_PUCENTP_V_list.get(0).getADDRESS());
        map_basic.put("负责人",qAJJ_PUCENTP_V_list.get(0).getPOLICYNAME());
        map_basic.put("联系电话",qAJJ_PUCENTP_V_list.get(0).getPOLICYTEL());
        //map_basic.put("经纬度",map_location);
        //责任主体
        LinkedHashMap map_SubjectOfResponsibility = new LinkedHashMap();
        map_SubjectOfResponsibility.put("主体名称",qAJJ_PUCENTP_V_list.get(0).getENTPNAME());
        map_SubjectOfResponsibility.put("负责人",qAJJ_PUCENTP_V_list.get(0).getPOLICYNAME());
        map_SubjectOfResponsibility.put("联系电话",qAJJ_PUCENTP_V_list.get(0).getPOLICYTEL());
        map_SubjectOfResponsibility.put("主体地址",qAJJ_PUCENTP_V_list.get(0).getADDRESS());

        qAJJ_PUCENTP_VDetailOfList.setBuildName(qAJJ_PUCENTP_V_list.get(0).getENTPNAME());
        qAJJ_PUCENTP_VDetailOfList.setBasicDetail(map_basic);
        qAJJ_PUCENTP_VDetailOfList.setSubjectDetail(map_SubjectOfResponsibility);
        return qAJJ_PUCENTP_VDetailOfList;
    }
    //监管机构--关联表--QAJJ_GRID_V的AREACODE
    public MyPageInfoModel getQAJJ_GRID_VDetailInformationOfListRegulatoryAgency(String id, Integer pageNum, Integer pageSize){
        QAJJ_PUCENTP_V qAJJ_PUCENTP_V = new QAJJ_PUCENTP_V();
        qAJJ_PUCENTP_V.setROWID(id);
        List<QAJJ_PUCENTP_V> qAJJ_PUCENTP_V_list = qAJJ_PUCENTP_VMapper.getQAJJ_PUCENTP_V(qAJJ_PUCENTP_V);
        String areaCode = qAJJ_PUCENTP_V_list.get(0).getAREACODE();
        QAJJ_GRID_V qAJJ_GRID_V = new QAJJ_GRID_V();
        qAJJ_GRID_V.setCOMMUNITY_CODE(areaCode);
        List<QAJJ_GRID_V> qAJJ_GRID_V_list = qAJJ_GRID_VMapper.getQAJJ_GRID_V(qAJJ_GRID_V);

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<QAJJ_GRID_V> pageInfo = new PageInfo(qAJJ_GRID_V_list);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String regulatoryAgency[] = {"机构名称","联系人","联系电话","所在社区"};
        List<String[]> regulatoryAgency_list = new ArrayList();
        for (QAJJ_GRID_V qAJJ_GRID_V_return:qAJJ_GRID_V_list) {
            String RegulatoryAgency_tbody[] = {"安监局",qAJJ_GRID_V_return.getGRIDPERSON(),qAJJ_GRID_V_return.getGRIDPHONE(),qAJJ_GRID_V_return.getCOMMUNITY_NAME()+"社区"};
            regulatoryAgency_list.add(RegulatoryAgency_tbody);
        }
        TheadTbodyModel theadTbodyModel_regulatoryAgency = new TheadTbodyModel();
        theadTbodyModel_regulatoryAgency.setThead(regulatoryAgency);
        theadTbodyModel_regulatoryAgency.setTbody(regulatoryAgency_list);
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setList(theadTbodyModel_regulatoryAgency);
        return pageInfo_return;
    }

    //风险评估关联表--QAJJ_HPOINTANDHSOURCE_V
    public MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAssessment(String id,Integer pageNum,Integer pageSize){
        QAJJ_PUCENTP_V qAJJ_PUCENTP_V = new QAJJ_PUCENTP_V();
        qAJJ_PUCENTP_V.setROWID(id);
        List<QAJJ_PUCENTP_V> QAJJ_PUCENTP_V_list = qAJJ_PUCENTP_VMapper.getQAJJ_PUCENTP_V(qAJJ_PUCENTP_V);

        PageHelper.startPage(pageNum, pageSize);
        QAJJ_PUCENTP_V qAJJ_PUCENTP_V_return = new QAJJ_PUCENTP_V();
        //QAJJ_PUCENTP_V_return.setDANGER_NAME(QAJJ_PUCENTP_V_list.get(0).getDANGER_NAME());
        List<QAJJ_PUCENTP_V> QAJJ_PUCENTP_V_list_return = qAJJ_PUCENTP_VMapper.getQAJJ_PUCENTP_V(qAJJ_PUCENTP_V_return);
        PageInfo<QAJJ_PUCENTP_V> pageInfo = new PageInfo(QAJJ_PUCENTP_V_list_return);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String riskAssessment[] = {"评估人","风险点类型ID","风险等级","风险分值","审核人","审核时间","状态"};
        List<String[]> riskAssessment_list = new ArrayList();
        for (QAJJ_PUCENTP_V QAJJ_PUCENTP_V:QAJJ_PUCENTP_V_list_return) {
            /*String riskAssessment_tbody[] = {"系统评分",QAJJ_PUCENTP_V.getPLACE_TYPE(),QAJJ_PUCENTP_V.getRISK_ASSESS_LV(),
                    QAJJ_PUCENTP_V.getRISK_ASSESS_CSCORE(),QAJJ_PUCENTP_V.getRISK_ASSESS_MAN_NAME(),
                    QAJJ_PUCENTP_V.getRISK_ASSESS_AUDIT_INTRO(),QAJJ_PUCENTP_V.getRISK_ASSESS_AUDIT_INTRO()};
            riskAssessment_list.add(riskAssessment_tbody);*/
        }
        TheadTbodyModel theadTbodyModel_riskAssessment = new TheadTbodyModel();
        theadTbodyModel_riskAssessment.setThead(riskAssessment);
        theadTbodyModel_riskAssessment.setTbody(riskAssessment_list);
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setList(theadTbodyModel_riskAssessment);
        return pageInfo_return;
    }

    //风险隐患--QAJJ_INSTROUBLEREGCHECK_V和QAJJ_INSRECORD_V
    public MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskDanger(String id, Integer pageNum, Integer pageSize){
        QAJJ_PUCENTP_V qAJJ_PUCENTP_V = new QAJJ_PUCENTP_V();
        qAJJ_PUCENTP_V.setROWID(id);
        List<QAJJ_PUCENTP_V> qAJJ_PUCENTP_V_list = qAJJ_PUCENTP_VMapper.getQAJJ_PUCENTP_V(qAJJ_PUCENTP_V);
        String INNERID = qAJJ_PUCENTP_V_list.get(0).getINNERID();
        RiskHiddenDanger riskHiddenDanger = new RiskHiddenDanger();
        riskHiddenDanger.setINNERID(INNERID);
        List<RiskHiddenDanger> riskHiddenDanger_list = riskHiddenDangerMapper.getRiskHiddenDanger(riskHiddenDanger);

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<RiskHiddenDanger> pageInfo = new PageInfo(riskHiddenDanger_list);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String riskDanger[] = {"隐患内容","整改措施见说明","企业名称","检查时间","完成时间/复查时间"};
        List<String[]> riskDanger_list = new ArrayList();
        for (RiskHiddenDanger RiskHiddenDanger:riskHiddenDanger_list) {
            String riskDanger_tbody[] = {RiskHiddenDanger.getINSPECTCONTENT(),RiskHiddenDanger.getIMPROVESTEP(),
                    RiskHiddenDanger.getENTPNAME(),RiskHiddenDanger.getCHECKDATE(),
                    RiskHiddenDanger.getFINISHDATE()};
            riskDanger_list.add(riskDanger_tbody);
        }
        TheadTbodyModel theadTbodyModel_riskDanger = new TheadTbodyModel();
        theadTbodyModel_riskDanger.setThead(riskDanger);
        theadTbodyModel_riskDanger.setTbody(riskDanger_list);
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setList(theadTbodyModel_riskDanger);
        return pageInfo_return;
    }
    //风险预警
    public MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAlarm(String id, Integer pageNum, Integer pageSize){
        QAJJ_PUCENTP_V qAJJ_PUCENTP_V = new QAJJ_PUCENTP_V();
        qAJJ_PUCENTP_V.setROWID(id);
        List<QAJJ_PUCENTP_V> QAJJ_PUCENTP_V_list = qAJJ_PUCENTP_VMapper.getQAJJ_PUCENTP_V(qAJJ_PUCENTP_V);

        PageHelper.startPage(pageNum, pageSize);
        QAJJ_PUCENTP_V QAJJ_PUCENTP_V_return = new QAJJ_PUCENTP_V();
        //QAJJ_PUCENTP_V_return.setDANGER_NAME(QAJJ_PUCENTP_V_list.get(0).getDANGER_NAME());
        List<QAJJ_PUCENTP_V> QAJJ_PUCENTP_V_list_return = qAJJ_PUCENTP_VMapper.getQAJJ_PUCENTP_V(QAJJ_PUCENTP_V_return);
        PageInfo<QAJJ_PUCENTP_V> pageInfo = new PageInfo(QAJJ_PUCENTP_V_list_return);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String riskAlarm[] = {"预警人","预警原因","预警时间","开始时间","结束时间"};
        List<String[]> riskAlarm_list = new ArrayList();
        for (QAJJ_PUCENTP_V QAJJ_PUCENTP_V:QAJJ_PUCENTP_V_list_return) {
            /*String riskAlarm_tbody[] = {QAJJ_PUCENTP_V.getRISK_ASSESS_MAN_NAME(),QAJJ_PUCENTP_V.getRISK_ASSESS_TYPE_NAMES(),
                    QAJJ_PUCENTP_V.getRISK_ASSESS_AUDIT_DATE(), QAJJ_PUCENTP_V.getRISK_ASSESS_DATE(),QAJJ_PUCENTP_V.getRISK_ASSESS_DATE()};
            riskAlarm_list.add(riskAlarm_tbody);*/
        }
        TheadTbodyModel theadTbodyModel_riskAlarm = new TheadTbodyModel();
        theadTbodyModel_riskAlarm.setThead(riskAlarm);
        theadTbodyModel_riskAlarm.setTbody(riskAlarm_list);
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setList(theadTbodyModel_riskAlarm);
        return pageInfo_return;
    }
    //风险事故--QAJJ_REPACCIDENT_V和JOIN QAJJ_PUCENTP_V
    public MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAccident(String id, Integer pageNum, Integer pageSize){
        QAJJ_PUCENTP_V qAJJ_PUCENTP_V = new QAJJ_PUCENTP_V();
        qAJJ_PUCENTP_V.setROWID(id);
        List<QAJJ_PUCENTP_V> qAJJ_PUCENTP_V_list = qAJJ_PUCENTP_VMapper.getQAJJ_PUCENTP_V(qAJJ_PUCENTP_V);

        PageHelper.startPage(pageNum, pageSize);
        QAJJ_REPACCIDENT_V qAJJ_REPACCIDENT_V = new QAJJ_REPACCIDENT_V();
        qAJJ_REPACCIDENT_V.setINNERID(qAJJ_PUCENTP_V_list.get(0).getINNERID());
        List<QAJJ_REPACCIDENT_V> QAJJ_REPACCIDENT_V_list_return = qAJJ_REPACCIDENT_VMapper.getQAJJ_REPACCIDENT_V(qAJJ_REPACCIDENT_V);
        PageInfo<QAJJ_REPACCIDENT_V> pageInfo = new PageInfo(QAJJ_REPACCIDENT_V_list_return);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String riskAccident[] = {"填报单位负责人","事故原因","事故概况","事故发生时间","事故发生地点","事故单位地址","事故类型","事故等级"};
        List<String[]> riskAccident_list = new ArrayList();
        for (QAJJ_REPACCIDENT_V qAJJ_REPACCIDENT_V_return:QAJJ_REPACCIDENT_V_list_return) {
            String riskAccident_tbody[] = {qAJJ_REPACCIDENT_V_return.getLEADER(),qAJJ_REPACCIDENT_V_return.getREASONCODE(),
                    qAJJ_REPACCIDENT_V_return.getSURVEYDESC(), qAJJ_REPACCIDENT_V_return.getACCIDENTTIME(),
                    qAJJ_REPACCIDENT_V_return.getACCIDENTARGEADDRESS(),qAJJ_REPACCIDENT_V_return.getADDRESS(),
                    qAJJ_REPACCIDENT_V_return.getTYPECODE(),qAJJ_REPACCIDENT_V_return.getACCIDENTLEVEL()
            };
            riskAccident_list.add(riskAccident_tbody);
        }
        TheadTbodyModel theadTbodyModel_riskAccident = new TheadTbodyModel();
        theadTbodyModel_riskAccident.setThead(riskAccident);
        theadTbodyModel_riskAccident.setTbody(riskAccident_list);
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setList(theadTbodyModel_riskAccident);
        return pageInfo_return;
    }
}
