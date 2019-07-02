package com.cetccity.operationcenter.webframework.alarmcenter.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.api.AlarmTrendApi;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFO_CODE;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_INFO_CODEService;
import com.cetccity.operationcenter.webframework.alarmcenter.service.AlarmTrendService;
import com.cetccity.operationcenter.webframework.web.model.incident.NameCodeValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_STATISTIC;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_STATISTICService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.*;

@RestController
public class AlarmTrendController implements AlarmTrendApi {

    @Autowired
    ALARM_INFO_CODEService aLARM_INFO_CODEService;

    @Autowired
    ALARM_STATISTICService aLARM_STATISTICService;

    @Autowired
    AlarmTrendService alarmTrendService;

    /*今日预警-预警趋势分析*/
    public List<NameValueModel> getLowerTop()throws IOException {
        ALARM_INFO_CODE aLARM_INFO_CODE = new ALARM_INFO_CODE();
        List<NameValueModel> nameDataModel_list = aLARM_INFO_CODEService.getAlarmLV1Type(aLARM_INFO_CODE);
        return nameDataModel_list;
    }

    /*今日预警-预警趋势分析*/
    public List<NameDataModel> getLowerOne()throws IOException {
        List<NameDataModel> nameDataModel_list = aLARM_STATISTICService.getLowerOne();
        return nameDataModel_list;
    }

    /*今日预警-预警趋势分析*/
    public List<NameDataModel> getLowerOneDrillDown(String alarm_Code)throws IOException {
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        ALARM_INFO_CODE aLARM_INFO_CODE = new ALARM_INFO_CODE();
        aLARM_INFO_CODE.setLV_1(alarm_Code);
        List<ALARM_INFO_CODE> aLARM_INFO_CODE_list = aLARM_INFO_CODEService.getLowerOneDrillDown(aLARM_INFO_CODE);
        for (ALARM_INFO_CODE aLARM_INFO_CODE_return:aLARM_INFO_CODE_list){
            NameDataModel nameDataModel = aLARM_STATISTICService.getLowerOneDrillDown(aLARM_INFO_CODE_return.getLV_2_NAME(),aLARM_INFO_CODE_return.getLV_2());
            nameDataModel_list.add(nameDataModel);
        }
        return nameDataModel_list;
    }

    /*今日预警-预警趋势分析*/
    public List<NameDataModel> getLowerOneRate()throws IOException {
        List<NameDataModel> nameDataModel_list = aLARM_STATISTICService.getLowerOneRate();
        return nameDataModel_list;
    }

    /*预警趋势-对比*/
    public List<NameCodeValueModel> getLowerTwo(String year){
        return alarmTrendService.getLowerTwo(year);
    }

    /*今日预警-企业外迁信息*/
    public List<NameValueModel> getLowerTwoDrillDown(String summary_code,String date)throws IOException{
        return alarmTrendService.getLowerTwoDrillDown(summary_code,date);
    }
}
