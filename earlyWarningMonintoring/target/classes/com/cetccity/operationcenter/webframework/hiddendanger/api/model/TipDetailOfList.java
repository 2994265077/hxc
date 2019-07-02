package com.cetccity.operationcenter.webframework.hiddendanger.api.model;

import lombok.Data;

@Data
public class TipDetailOfList<T> {

    private String buildName;
    //基本信息
    private T basicDetail;
    //责任主体
    private T subjectDetail;
    //监管机构
    private T agencyList;
    //风险评估
    private T riskAssessmentList;
    //风险隐患
    private T riskList;
    //风险预警
    private T earlyWaringList;
    //风险事故
    private T accidentList;


}
