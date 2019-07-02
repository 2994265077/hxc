package com.cetccity.operationcenter.webframework.alarmcenter.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.api.AlarmOfTodayApi;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFO_CODE;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_INFORMATIONService;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_INFO_CODEService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameCodeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.web.model.incident.*;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFORMATION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
public class AlarmOfTodayController implements AlarmOfTodayApi {

    @Autowired
    ALARM_INFORMATIONService aLARM_INFORMATIONService;

    @Autowired
    ALARM_INFO_CODEService aLARM_INFO_CODEService;

    public List<NameValueModel> LeftOne(){
        List<NameValueModel> nameValueModel_list = aLARM_INFORMATIONService.LeftOne();
        return nameValueModel_list;
    }

    public List<NameCodeValueModel> LeftTwo(String date) throws IOException {
        List<NameCodeValueModel> nameCodeValueModel_list = aLARM_INFORMATIONService.LeftTwo(date);
        return nameCodeValueModel_list;
    }

    /*public List<LinkedHashMap> LeftThreeLoadMap(@PathVariable("alarm_code") String alarm_code)throws IOException{
        List<LinkedHashMap> month_list = aLARM_INFORMATIONService.LeftThreeLoadMap(alarm_code);
        return month_list;
    }*/

    public List<NameCodeDataModel> earlyType(){
        ALARM_INFO_CODE aLARM_INFO_CODE = new ALARM_INFO_CODE();
        List<NameCodeDataModel> nameCodeDataModel_list = aLARM_INFO_CODEService.getAlarmCodeType(aLARM_INFO_CODE);
        return nameCodeDataModel_list;
    }

    public List<NameValueModel> earlyDisposalState(){
        List<NameValueModel> nameValueModel_list = aLARM_INFORMATIONService.earlyDisposalState();
        return nameValueModel_list;
    }

    public EarlyWarningCenter earlyInformationCenter(@RequestBody ALARM_INFORMATION aLARM_INFORMATION) {
        EarlyWarningCenter earlyWarningCenter = aLARM_INFORMATIONService.earlyInformationCenter(aLARM_INFORMATION);
        return earlyWarningCenter;
    }

    public List earlyCenterListDetail(String id) {
        List list = aLARM_INFORMATIONService.earlyCenterListDetail(id);
        return list;
    }

    public NameDataModel fourAlarm() {
        NameDataModel nameDataModel = aLARM_INFORMATIONService.fourAlarm();
        return nameDataModel;
    }

}
