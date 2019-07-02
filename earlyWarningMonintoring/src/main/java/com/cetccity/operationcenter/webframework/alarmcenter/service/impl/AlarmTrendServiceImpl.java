package com.cetccity.operationcenter.webframework.alarmcenter.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.ALARM_INFO_CODEMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.ALARM_STATISTICMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFO_CODE;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_STATISTIC;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_STATISTICService;
import com.cetccity.operationcenter.webframework.alarmcenter.service.AlarmTrendService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.web.model.incident.NameCodeValueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class AlarmTrendServiceImpl implements AlarmTrendService {

    @Autowired
    ALARM_STATISTICService aLARM_STATISTICService;

    @Autowired
    ALARM_INFO_CODEMapper aLARM_INFO_CODEMapper;

    @Autowired
    ALARM_STATISTICMapper aLARM_STATISTICMapper;

    //一类--预警趋势对比
    public List<NameCodeValueModel> getLowerTwo(String year) {
        List<NameCodeValueModel> nameCodeValueModel_list = new ArrayList<NameCodeValueModel>();
        //事件预警、安监预警、三防预警、消防预警、重点企业预警、城市生命线、生态环境预警、气象预警、卫计预警、舆情预警、地质预警
        ALARM_INFO_CODE aLARM_INFO_CODE = new ALARM_INFO_CODE();
        List<ALARM_INFO_CODE> aLARM_INFO_CODE_list = aLARM_INFO_CODEMapper.getRemovalALARM_INFO_CODE(aLARM_INFO_CODE);
        for (ALARM_INFO_CODE aLARM_INFO_CODE_return:aLARM_INFO_CODE_list) {
            ALARM_STATISTIC aLARM_STATISTIC = new ALARM_STATISTIC();
            aLARM_STATISTIC.setALARM_TYPE_LV1(aLARM_INFO_CODE_return.getLV_1());
            aLARM_STATISTIC.setTIME(year);
            int sum = aLARM_STATISTICMapper.getSum(aLARM_STATISTIC);
            NameCodeValueModel nameCodeValueModel = new NameCodeValueModel();
            nameCodeValueModel.setName(aLARM_INFO_CODE_return.getLV_1_NAME());
            nameCodeValueModel.setCode(aLARM_INFO_CODE_return.getLV_1());
            nameCodeValueModel.setValue(String.valueOf(sum));
            nameCodeValueModel_list.add(nameCodeValueModel);
        }
        return nameCodeValueModel_list;
    }

    //二类--预警趋势对比
    public List<NameValueModel> getLowerTwoDrillDown(String summary_code, String date){
        //事件预警、安监预警、三防预警、消防预警、重点企业预警、城市生命线、生态环境预警、气象预警、卫计预警、舆情预警、地质预警
        ALARM_INFO_CODE aLARM_INFO_CODE = new ALARM_INFO_CODE();
        aLARM_INFO_CODE.setLV_1(summary_code);
        List<ALARM_INFO_CODE> aLARM_INFO_CODE_list = aLARM_INFO_CODEMapper.getLowerOneDrillDown(aLARM_INFO_CODE);
        LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        for (ALARM_INFO_CODE aLARM_INFO_CODE_return: aLARM_INFO_CODE_list) {
            ALARM_STATISTIC aLARM_STATISTIC = new ALARM_STATISTIC();
            aLARM_STATISTIC.setALARM_TYPE_LV2(aLARM_INFO_CODE_return.getLV_2());
            aLARM_STATISTIC.setTIME(date);
            int sum = aLARM_STATISTICMapper.getSum(aLARM_STATISTIC);
            nameValueModel_list.add(NameValueModel.builder().name(aLARM_INFO_CODE_return.getLV_2_NAME()).value(String.valueOf(sum)).build());
        }
        return nameValueModel_list;
    }
}
