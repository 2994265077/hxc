package com.cetccity.operationcenter.webframework.alarmcenter.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.web.model.incident.*;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFORMATION;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public interface ALARM_INFORMATIONService {

    List<ALARM_INFORMATION> getALARM_INFORMATION(ALARM_INFORMATION aLARM_INFORMATION);

    List<NameValueModel> LeftOne();

    List<NameCodeValueModel> LeftTwo(String date)throws IOException;

    List<AlarmTodayType> LeftThree(String alarm_code,String date);

    List<LinkedHashMap> LeftThreeLoadMap(String alarm_code)throws IOException;

    List<LoadMap> alarmLoadMapLV2(String alarm_code, String street, String date, String id, String startTime, String endTime);

    List<IconTypeLoadMap> alarmLoadMap002002(String street, String date, String id, String startTime, String endTime);

    List<LoadMap> alarmLoadMapLeftTwo(String alarm_code,String date);

    EarlyWarningCenter earlyInformationCenter(ALARM_INFORMATION aLARM_INFORMATION);

    List earlyCenterListDetail(String id);

    NameDataModel fourAlarm();

    List<NameValueModel> earlyDisposalState();
}
