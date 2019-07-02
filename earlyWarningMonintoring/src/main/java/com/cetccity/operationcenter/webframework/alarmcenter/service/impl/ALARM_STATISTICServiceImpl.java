package com.cetccity.operationcenter.webframework.alarmcenter.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.ALARM_INFO_CODEMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.ALARM_STATISTICMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFO_CODE;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_STATISTIC;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_STATISTICService;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

@Service("aLARM_STATISTICService")
public class ALARM_STATISTICServiceImpl implements ALARM_STATISTICService {

    @Autowired
    ALARM_STATISTICMapper aLARM_STATISTICMapper;

    @Autowired
    ALARM_INFO_CODEMapper aLARM_INFO_CODEMapper;

    public int insertALARM_STATISTIC(ALARM_STATISTIC aLARM_STATISTIC){
        return aLARM_STATISTICMapper.insertALARM_STATISTIC(aLARM_STATISTIC);
    }

    public List<ALARM_STATISTIC> getALARM_STATISTIC(ALARM_STATISTIC aLARM_STATISTIC){
        return aLARM_STATISTICMapper.getALARM_STATISTIC(aLARM_STATISTIC);
    }

    //趋势--默认
    public List<NameDataModel> getLowerOne()throws IOException {
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        ALARM_INFO_CODE aLARM_INFO_CODE = new ALARM_INFO_CODE();
        List<ALARM_INFO_CODE> ALARM_INFO_CODE_list = aLARM_INFO_CODEMapper.getRemovalALARM_INFO_CODE(aLARM_INFO_CODE);
        //alarmName=事件预警、安监预警、三防预警、消防预警、重点企业预警、城市生命线、生态环境预警、气象预警、卫计预警、舆情预警、地质预警
        for (ALARM_INFO_CODE aLARM_INFO_CODE_return:ALARM_INFO_CODE_list) {
            nameDataModel_list.add(getAlarmType(aLARM_INFO_CODE_return.getLV_1_NAME(),aLARM_INFO_CODE_return.getLV_1(),"ALARM_TYPE_LV1"));
        }
        return nameDataModel_list;
    }

    //趋势--默认--下钻
    public NameDataModel getLowerOneDrillDown(String alarm_Name_DrillDown,String alarm_type_code){
        NameDataModel nameDataModel = getAlarmType(alarm_Name_DrillDown,alarm_type_code,"ALARM_TYPE_LV2");
        return nameDataModel;
    }

    //趋势--增长率
    public List<NameDataModel> getLowerOneRate() throws IOException{
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        ALARM_INFO_CODE aLARM_INFO_CODE = new ALARM_INFO_CODE();
        List<ALARM_INFO_CODE> ALARM_INFO_CODE_list = aLARM_INFO_CODEMapper.getRemovalALARM_INFO_CODE(aLARM_INFO_CODE);
        //alarmName=事件预警、安监预警、三防预警、消防预警、重点企业预警、城市生命线、生态环境预警、气象预警、卫计预警、舆情预警、地质预警
        for (ALARM_INFO_CODE aLARM_INFO_CODE_return:ALARM_INFO_CODE_list) {
            nameDataModel_list.add(getAlarmTypeRate(aLARM_INFO_CODE_return.getLV_1_NAME(),aLARM_INFO_CODE_return.getLV_1()));
        }
        return nameDataModel_list;
    }

    public NameDataModel getAlarmType(String name ,String ALARM_CODE,String type){
        NameDataModel nameDataModel = new NameDataModel();
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        for(int i=11;i>-1;i--) {
            int num=0;
            String date = LoadMyUtil.getMyTime("MONTH",-i);
            ALARM_STATISTIC aLARM_STATISTIC = new ALARM_STATISTIC();
            aLARM_STATISTIC.setTIME(date);
            if("ALARM_TYPE_LV1".equals(type)){
                aLARM_STATISTIC.setALARM_TYPE_LV1(ALARM_CODE);
            }else if("ALARM_TYPE_LV2".equals(type)){
                aLARM_STATISTIC.setALARM_TYPE_LV2(ALARM_CODE);
            }
            List<ALARM_STATISTIC> aLARM_STATISTIC_list = aLARM_STATISTICMapper.getALARM_STATISTIC(aLARM_STATISTIC);
            for (ALARM_STATISTIC return_aLARM_STATISTIC:aLARM_STATISTIC_list) {
                num += Integer.parseInt(return_aLARM_STATISTIC.getALARM_NUM());
            }
            nameValueModel_list.add(NameValueModel.builder().name(date).value(String.valueOf(num)).build());
        }
        nameDataModel.setName(name);
        nameDataModel.setData(nameValueModel_list);
        return nameDataModel;
    }

    public NameDataModel getAlarmTypeRate(String name ,String ALARM_CODE){
        NameDataModel nameDataModel = new NameDataModel();
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        for(int i=11;i>-1;i--) {
            int num=0;
            int num_upper=0;
            String date = LoadMyUtil.getMyTime("MONTH",-i);
            ALARM_STATISTIC aLARM_STATISTIC = new ALARM_STATISTIC();
            aLARM_STATISTIC.setTIME(date);
            aLARM_STATISTIC.setALARM_TYPE_LV1(ALARM_CODE);
            List<ALARM_STATISTIC> aLARM_STATISTIC_list = aLARM_STATISTICMapper.getALARM_STATISTIC(aLARM_STATISTIC);
            for (ALARM_STATISTIC return_aLARM_STATISTIC:aLARM_STATISTIC_list) {
                num += Integer.parseInt(return_aLARM_STATISTIC.getALARM_NUM());
            }
            String date_upper = LoadMyUtil.getMyTime("MONTH",-i-1);
            ALARM_STATISTIC aLARM_STATISTIC_upper = new ALARM_STATISTIC();
            aLARM_STATISTIC_upper.setTIME(date_upper);
            aLARM_STATISTIC_upper.setALARM_TYPE_LV1(ALARM_CODE);
            List<ALARM_STATISTIC> aLARM_STATISTIC_list_upper = aLARM_STATISTICMapper.getALARM_STATISTIC(aLARM_STATISTIC_upper);
            for (ALARM_STATISTIC return_aLARM_STATISTIC:aLARM_STATISTIC_list_upper) {
                num_upper += Integer.valueOf(return_aLARM_STATISTIC.getALARM_NUM());
            }
            float rate;
            if(num_upper==0){
                rate = 0;
            }else {
                rate = (float)(num - num_upper) / num_upper;
            }
            nameValueModel_list.add(NameValueModel.builder().name(date).value(String.valueOf(rate)).build());
        }
        nameDataModel.setName(name);
        nameDataModel.setData(nameValueModel_list);
        return nameDataModel;
    }
}
