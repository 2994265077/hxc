package com.cetccity.operationcenter.webframework.alarmcenter.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.api.AlarmPageInfoApi;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_INFORMATIONService;
import com.cetccity.operationcenter.webframework.web.model.incident.AlarmTodayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AlarmPageInfoController implements AlarmPageInfoApi {

    @Autowired
    ALARM_INFORMATIONService aLARM_INFORMATIONService;

    public List<AlarmTodayType> LeftThree(@PathVariable("alarm_code") String alarm_code,String date, String type, String typeV2,String level){
        List<AlarmTodayType> alarmTodayType_list = aLARM_INFORMATIONService.LeftThree(alarm_code,date, type, typeV2, level);
        return alarmTodayType_list;
    }

}
