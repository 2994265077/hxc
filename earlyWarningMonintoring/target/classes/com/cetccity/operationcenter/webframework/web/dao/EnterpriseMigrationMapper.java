package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.web.model.incident.AlarmEnterpriseModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EnterpriseMigrationMapper {

    List<AlarmEnterpriseModel> alarmEnterpriseMigration();

    List<AlarmEnterpriseModel> alarmLaborDisputes();

}
