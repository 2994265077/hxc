package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.web.dao.WEATHER_ALARMMapper;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.WEATHER_ALARM;
import com.cetccity.operationcenter.webframework.web.service.WEATHER_ALARMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service("wEATHER_ALARMService")
public class WEATHER_ALARMServiceImpl implements WEATHER_ALARMService {

    @Autowired
    WEATHER_ALARMMapper wEATHER_ALARMMapper;

    public List<WEATHER_ALARM> getWEATHER_ALARMOfToday(String time){
        String year = time.split("-")[0];
        String month = time.split("-")[1];
        String day = time.split("-")[2];
        String time_weather = year+"年"+month+"月"+day+"日";
        List<WEATHER_ALARM> WEATHER_ALARM_list = wEATHER_ALARMMapper.getWEATHER_ALARMOfToday(time_weather);
        return WEATHER_ALARM_list;
    }

}
