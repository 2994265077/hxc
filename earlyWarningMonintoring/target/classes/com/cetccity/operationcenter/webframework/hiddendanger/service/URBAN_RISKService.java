package com.cetccity.operationcenter.webframework.hiddendanger.service;

import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TipDetailOfList;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.web.model.incident.NameColorDataModel;
import com.cetccity.operationcenter.webframework.web.model.incident.NameColorXDataYDataModel;

import java.io.IOException;

public interface URBAN_RISKService {

    TipDetailOfList getURBAN_RISKDetailInformation(String id);

    MyPageInfoModel getURBAN_RISKDetailInformationOfListRegulatoryAgency(String id, Integer pageNum, Integer pageSize);

    MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAssessment(String id, Integer pageNum, Integer pageSize);

    MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskDanger(String id, Integer pageNum, Integer pageSize);

    MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAlarm(String id, Integer pageNum, Integer pageSize);

    MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAccident(String id, Integer pageNum, Integer pageSize);

    NameColorDataModel getChartOfRightOne(String street_code);

    NameColorDataModel getChartOfRightTwo(String street_code);

    NameColorXDataYDataModel getChartOfRightThree(String street_string)throws IOException;

    NameColorDataModel getChartOfRightFour(String street_code);
}
