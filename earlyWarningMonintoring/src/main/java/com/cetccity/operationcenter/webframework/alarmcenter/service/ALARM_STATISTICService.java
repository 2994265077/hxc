package com.cetccity.operationcenter.webframework.alarmcenter.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_STATISTIC;
import java.io.IOException;
import java.util.List;

public interface ALARM_STATISTICService {

    int insertALARM_STATISTIC(ALARM_STATISTIC aLARM_STATISTIC);

    List<ALARM_STATISTIC> getALARM_STATISTIC(ALARM_STATISTIC aLARM_STATISTIC);

    List<NameDataModel> getLowerOne() throws IOException;

    NameDataModel getLowerOneDrillDown(String alarm_Name_DrillDown,String alarm_type_code);

    List<NameDataModel> getLowerOneRate() throws IOException;
}
