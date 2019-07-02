package com.cetccity.operationcenter.webframework.alarmcenter.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.web.model.incident.NameCodeValueModel;

import java.util.List;

public interface AlarmTrendService {

    List<NameCodeValueModel> getLowerTwo(String year);

    List<NameValueModel> getLowerTwoDrillDown(String summary_code, String date);
}
