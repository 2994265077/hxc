package com.cetccity.operationcenter.webframework.alarmcenter.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.api.AlarmLoadMapApi;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_INFORMATIONService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class AlarmLoadMapController implements AlarmLoadMapApi {

    @Autowired
    ALARM_INFORMATIONService aLARM_INFORMATIONService;

    public List<LoadMap> alarmLoadMapLV2(@PathVariable("alarm_code") String alarm_code, String street, String date,String id,String startTime,String endTime, String type, String level)throws IOException {
        List<LoadMap> map_list = aLARM_INFORMATIONService.alarmLoadMapLV2(alarm_code,street,date,id,startTime,endTime, type,  level);
        return map_list;
    }

    public List<IconTypeLoadMap> alarmLoadMap002002(String street, String date, String id, String startTime, String endTime, String type, String level)throws IOException{
        List<IconTypeLoadMap> map_list = aLARM_INFORMATIONService.alarmLoadMap002002(street,date,id,startTime,endTime, type, level);
        return map_list;
    }


    public List<LoadMap> alarmLoadMapLeftTwo(@PathVariable("alarm_code") String alarm_code,String date, String type, String level)throws IOException{
        List<LoadMap> map_list = aLARM_INFORMATIONService.alarmLoadMapLeftTwo(alarm_code,date, type, level);
        return map_list;
    }

}
