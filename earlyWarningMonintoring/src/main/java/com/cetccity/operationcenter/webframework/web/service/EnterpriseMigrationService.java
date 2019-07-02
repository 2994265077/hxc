package com.cetccity.operationcenter.webframework.web.service;

import com.cetccity.operationcenter.webframework.web.model.build.LoadMapBuildGrade;
import com.cetccity.operationcenter.webframework.web.model.incident.AlarmEnterpriseModel;
import java.util.List;

public interface EnterpriseMigrationService {

    List<AlarmEnterpriseModel> alarmEnterpriseMigration();

    List<LoadMapBuildGrade> alarmEnterpriseMigrationLoadMap();

    List<AlarmEnterpriseModel> alarmLaborDisputes();

    List<LoadMapBuildGrade> alarmLaborDisputesLoadMap();
}
