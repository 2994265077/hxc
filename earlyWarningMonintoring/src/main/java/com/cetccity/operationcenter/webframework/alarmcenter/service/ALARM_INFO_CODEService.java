package com.cetccity.operationcenter.webframework.alarmcenter.service;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFO_CODE;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameCodeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameTypeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import java.util.List;

public interface ALARM_INFO_CODEService {

    List<NameValueModel> getAlarmLV1Type(ALARM_INFO_CODE aLARM_INFO_CODE);

    List<NameCodeDataModel> getAlarmCodeType(ALARM_INFO_CODE aLARM_INFO_CODE);

    List<ALARM_INFO_CODE> getLowerOneDrillDown(ALARM_INFO_CODE aLARM_INFO_CODE);
}
