package com.cetccity.operationcenter.webframework.web.service;

import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.WEATHER_ALARM;

import java.util.List;

public interface WEATHER_ALARMService {

    List<WEATHER_ALARM> getWEATHER_ALARMOfToday(String time);

}
