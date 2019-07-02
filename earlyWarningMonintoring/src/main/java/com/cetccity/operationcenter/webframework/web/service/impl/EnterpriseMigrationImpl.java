package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.web.dao.EnterpriseMigrationMapper;
import com.cetccity.operationcenter.webframework.web.model.build.LoadMapBuildGrade;
import com.cetccity.operationcenter.webframework.web.model.incident.AlarmEnterpriseModel;
import com.cetccity.operationcenter.webframework.web.service.EnterpriseMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("enterpriseMigrationService")
public class EnterpriseMigrationImpl implements EnterpriseMigrationService {

    @Autowired
    EnterpriseMigrationMapper enterpriseMigrationMapper;
    public List<AlarmEnterpriseModel> alarmEnterpriseMigration(){
        List<AlarmEnterpriseModel> alarmEnterpriseModel_list = enterpriseMigrationMapper.alarmEnterpriseMigration();
        return alarmEnterpriseModel_list;
    }

    public List<AlarmEnterpriseModel> alarmLaborDisputes(){
        List<AlarmEnterpriseModel> alarmEnterpriseModel_list = enterpriseMigrationMapper.alarmLaborDisputes();
        return alarmEnterpriseModel_list;
    }

    public List<LoadMapBuildGrade> alarmEnterpriseMigrationLoadMap(){
        List<LoadMapBuildGrade> loadMapBuildGrade_list = new ArrayList<LoadMapBuildGrade>();
        List<AlarmEnterpriseModel> alarmEnterpriseModel_list = enterpriseMigrationMapper.alarmEnterpriseMigration();
        for (AlarmEnterpriseModel alarmEnterpriseModel:alarmEnterpriseModel_list) {
            LoadMapBuildGrade loadMapBuildGrade = new LoadMapBuildGrade();
            loadMapBuildGrade.setBLDG_NO(alarmEnterpriseModel.getBuildid());
            loadMapBuildGrade.setLevel(5);
            loadMapBuildGrade.setScore(alarmEnterpriseModel.getScore());
            if(alarmEnterpriseModel.getBuildid()!=null){
                loadMapBuildGrade_list.add(loadMapBuildGrade);
            }
        }
        return loadMapBuildGrade_list;
    }

    public List<LoadMapBuildGrade> alarmLaborDisputesLoadMap(){
        List<LoadMapBuildGrade> loadMapBuildGrade_list = new ArrayList<LoadMapBuildGrade>();
        List<AlarmEnterpriseModel> alarmLaborDisputesModel_list = enterpriseMigrationMapper.alarmLaborDisputes();
        for (AlarmEnterpriseModel alarmLaborDisputesModel:alarmLaborDisputesModel_list) {
            LoadMapBuildGrade loadMapBuildGrade = new LoadMapBuildGrade();
            loadMapBuildGrade.setBLDG_NO(alarmLaborDisputesModel.getBuildid());
            loadMapBuildGrade.setLevel(5);
            loadMapBuildGrade.setScore(alarmLaborDisputesModel.getScore());
            if(alarmLaborDisputesModel.getBuildid()!=null){
                loadMapBuildGrade_list.add(loadMapBuildGrade);
            }
        }
        return loadMapBuildGrade_list;
    }

}
