package com.cetccity.operationcenter.webframework.alarmcenter.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.ALARM_INFO_CODEMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFO_CODE;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_INFO_CODEService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameCodeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameTypeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ALARM_INFO_CODEServiceImpl implements ALARM_INFO_CODEService {

    @Autowired
    ALARM_INFO_CODEMapper aLARM_INFO_CODEMapper;

    public List<NameValueModel> getAlarmLV1Type(ALARM_INFO_CODE aLARM_INFO_CODE){
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        List<ALARM_INFO_CODE> aLARM_INFO_CODE_list = aLARM_INFO_CODEMapper.getRemovalALARM_INFO_CODE(aLARM_INFO_CODE);
        for(ALARM_INFO_CODE aLARM_INFO_CODE_return:aLARM_INFO_CODE_list){
            nameValueModel_list.add(NameValueModel.builder().name(aLARM_INFO_CODE_return.getLV_1_NAME()).value(aLARM_INFO_CODE_return.getLV_1()).build());
        }
        return nameValueModel_list;
    }

    public List<NameCodeDataModel> getAlarmCodeType(ALARM_INFO_CODE aLARM_INFO_CODE){
        List<NameCodeDataModel> nameCodeDataModel_list = new ArrayList<NameCodeDataModel>();
        List<ALARM_INFO_CODE> aLARM_INFO_CODE_list = aLARM_INFO_CODEMapper.getRemovalALARM_INFO_CODE(aLARM_INFO_CODE);
        for(ALARM_INFO_CODE aLARM_INFO_CODE_return:aLARM_INFO_CODE_list){
            NameCodeDataModel nameCodeDataModel = new NameCodeDataModel();
            nameCodeDataModel.setName(aLARM_INFO_CODE_return.getLV_1_NAME());
            nameCodeDataModel.setCode(aLARM_INFO_CODE_return.getLV_1());
            //得出LV_2
            ALARM_INFO_CODE aLARM_INFO_CODE_LV_2 = new ALARM_INFO_CODE();
            aLARM_INFO_CODE_LV_2.setLV_1(aLARM_INFO_CODE_return.getLV_1());
            List<ALARM_INFO_CODE> aLARM_INFO_CODE_list_LV_2 = aLARM_INFO_CODEMapper.getALARM_INFO_CODE(aLARM_INFO_CODE_LV_2);
            List<NameValueModel> nameValueModel_list_LV_2 = new ArrayList<NameValueModel>();
            for (ALARM_INFO_CODE aLARM_INFO_CODE_return_LV_2:aLARM_INFO_CODE_list_LV_2) {
                nameValueModel_list_LV_2.add(NameValueModel.builder()
                        .name(aLARM_INFO_CODE_return_LV_2.getLV_2_NAME())
                        .value(aLARM_INFO_CODE_return_LV_2.getLV_2())
                        .build());
            }
            nameCodeDataModel.setData(nameValueModel_list_LV_2);
            nameCodeDataModel_list.add(nameCodeDataModel);
        }
        return nameCodeDataModel_list;
    }

    public List<ALARM_INFO_CODE> getLowerOneDrillDown(ALARM_INFO_CODE aLARM_INFO_CODE){
        List<ALARM_INFO_CODE> aLARM_INFO_CODE_list = aLARM_INFO_CODEMapper.getLowerOneDrillDown(aLARM_INFO_CODE);
        return aLARM_INFO_CODE_list;
    }
}
