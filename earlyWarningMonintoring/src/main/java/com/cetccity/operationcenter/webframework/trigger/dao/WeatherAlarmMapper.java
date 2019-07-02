package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.WeatherAlarm;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 气象预警 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-01-16
 */
public interface WeatherAlarmMapper{

    List<WeatherAlarm> querySourceData(Map map);
}
