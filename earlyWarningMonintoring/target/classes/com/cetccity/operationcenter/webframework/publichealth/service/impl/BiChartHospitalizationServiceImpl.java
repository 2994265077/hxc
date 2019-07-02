package com.cetccity.operationcenter.webframework.publichealth.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_IN_VISITS_DAY_VMapper;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_ORG_VMapper;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_ORG_V;
import com.cetccity.operationcenter.webframework.publichealth.service.BiChartHospitalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:10 2019-03-06
 * Updater:     heliangming
 * Update_Date：9:10 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Service
public class BiChartHospitalizationServiceImpl implements BiChartHospitalizationService {

    @Autowired
    YJJC_QWJJ_IN_VISITS_DAY_VMapper yJJC_QWJJ_IN_VISITS_DAY_VMapper;

    @Autowired
    YJJC_QWJJ_ORG_VMapper yJJC_QWJJ_ORG_VMapper;

    //所有医院近一年住院人次
    public List<NameValueModel> hospitalizationLatelyYearn(){
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        for (int i = 11;i>=0;i--) {
            String month = LoadMyUtil.getMyTime("MONTH",-i);
            String count = yJJC_QWJJ_IN_VISITS_DAY_VMapper.getLatelyYearnCount(month);
            nameValueModel_list.add(NameValueModel.builder().name(month).value(String.valueOf(count)).build());
        }
        return nameValueModel_list;
    }

    //各医院住院人次--近两年记录总和
    public List<NameValueModel> hospitalizationHospital(){
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        //获取福田区下辖的医院
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = getHospital();
        Map map_time = new HashMap();
        String currentTime = LoadMyUtil.getMyTime("SECOND",0);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -2);
        map_time.put("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
        map_time.put("endTime",currentTime);
        yJJC_QWJJ_ORG_V_list.stream().forEach(u->{
            map_time.put("ORG_CODE",u.getORG_CODE());
            String num = yJJC_QWJJ_IN_VISITS_DAY_VMapper.getHospitalTwoYearCount(map_time);
            nameValueModel_list.add(NameValueModel.builder().name(u.getORG_NAME()).value(num).build());
        });
        return nameValueModel_list;
    }

    //近一个月各医院住院人次
    public List<NameValueModel> hospitalizationHospitalNearMonth(){
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        //获取福田区下辖的医院
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = getHospital();
        Map map_time = new HashMap();
        String currentTime = LoadMyUtil.getMyTime("SECOND",0);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        map_time.put("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
        map_time.put("endTime",currentTime);
        yJJC_QWJJ_ORG_V_list.stream().forEach(u->{
            map_time.put("ORG_CODE",u.getORG_CODE());
            String num = yJJC_QWJJ_IN_VISITS_DAY_VMapper.getHospitalTwoYearCount(map_time);
            nameValueModel_list.add(NameValueModel.builder().name(u.getORG_NAME()).value(num).build());
        });
        return nameValueModel_list;
    }

    public NameDataModel hospitalizationHospitalDepartment(){
        //获取当前时间
        String currentTime = LoadMyUtil.getMyTime("HOUR",0);
        //获取一年前的今天时间
        String lastTime = String.valueOf(Integer.valueOf(currentTime.split("-")[0])-1)+currentTime.substring(4,19);
        //获取医院列表
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = getHospital();
        List<NameDataModel> nameDataModelList = new ArrayList<>();
        for (YJJC_QWJJ_ORG_V u: yJJC_QWJJ_ORG_V_list) {
            Map map = new HashMap();
            map.put("currentTime",currentTime);
            map.put("lastTime",lastTime);
            map.put("ORG_CODE",u.getORG_CODE());
            List<HashMap> list = yJJC_QWJJ_IN_VISITS_DAY_VMapper.hospitalizationHospitalDepartment(map);
            nameDataModelList.add(NameDataModel.builder().name(u.getORG_NAME()).data(list).build());
        }
        return NameDataModel.builder().name("医院各科室住院人次--最近一年的数据").data(nameDataModelList).build();
    }

    //获取福田区下辖的医院
    public List<YJJC_QWJJ_ORG_V> getHospital(){
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = yJJC_QWJJ_ORG_VMapper.getYJJC_QWJJ_ORG_VRemoval();
        return yJJC_QWJJ_ORG_V_list;
    }
}
