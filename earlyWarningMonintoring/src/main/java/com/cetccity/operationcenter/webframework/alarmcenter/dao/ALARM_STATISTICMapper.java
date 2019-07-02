package com.cetccity.operationcenter.webframework.alarmcenter.dao;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_STATISTIC;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ALARM_STATISTICMapper {

    int insertALARM_STATISTIC(ALARM_STATISTIC aLARM_STATISTIC);

    List<ALARM_STATISTIC> getALARM_STATISTIC(ALARM_STATISTIC aLARM_STATISTIC);

    int getSum(ALARM_STATISTIC aLARM_STATISTIC);
}
