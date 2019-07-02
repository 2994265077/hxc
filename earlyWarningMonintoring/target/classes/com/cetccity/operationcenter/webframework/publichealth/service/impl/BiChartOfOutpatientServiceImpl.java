package com.cetccity.operationcenter.webframework.publichealth.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_ORG_VMapper;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_OUT_VISITS_DAY_VMapper;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_ORG_V;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_OUT_VISITS_DAY_V;
import com.cetccity.operationcenter.webframework.publichealth.service.BiChartOfOutpatientService;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:45 2019-02-28
 * Updater:     heliangming
 * Update_Date：9:45 2019-02-28
 * 更新描述:    heliangming 补充
 **/
@Service
public class BiChartOfOutpatientServiceImpl implements BiChartOfOutpatientService {

    @Autowired
    YJJC_QWJJ_OUT_VISITS_DAY_VMapper yJJC_QWJJ_OUT_VISITS_DAY_VMapper;

    @Autowired
    YJJC_QWJJ_ORG_VMapper yJJC_QWJJ_ORG_VMapper;

    //获取福田区下辖的医院
    public List<YJJC_QWJJ_ORG_V> getHospital(){
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = yJJC_QWJJ_ORG_VMapper.getYJJC_QWJJ_ORG_VRemoval();
        return yJJC_QWJJ_ORG_V_list;
    }

    //根据医院获取科室部门列表
    public List<YJJC_QWJJ_ORG_V> getDepartmentOfHospital(String ORG_CODE){
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = yJJC_QWJJ_ORG_VMapper.getYJJC_QWJJ_ORG_VDepartmentRemoval(ORG_CODE);
        return yJJC_QWJJ_ORG_V_list;
    }

    //各医院近一年门诊人次
    public List<NameValueModel> outpatientLatelyYearn(){
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = getHospital();
        Map<String,String> map = new HashMap<>();
        for (int i=11;i>=0;i--){
            Integer num = 0;
            String time = LoadMyUtil.getMyTime("MONTH",-i);
            map.put("time",time);
            for(YJJC_QWJJ_ORG_V yJJC_QWJJ_ORG_V:yJJC_QWJJ_ORG_V_list){
                map.put("ORG_CODE",yJJC_QWJJ_ORG_V.getORG_CODE());
                String count = yJJC_QWJJ_OUT_VISITS_DAY_VMapper.getSumOfOutpatient(map);
                if(count==null){
                    count = "0";
                }
                num += Integer.parseInt(count);
            }
            nameValueModel_list.add(NameValueModel.builder().name(map.get("time")).value(String.valueOf(num)).build());
        }
        return nameValueModel_list;
    }

    //各医院门诊人次--近两年记录总和
    public List<NameValueModel> outpatientHospital(){
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = getHospital();
        Integer num = 0;
        Map map = new HashMap();
        for (YJJC_QWJJ_ORG_V yJJC_QWJJ_ORG_V:yJJC_QWJJ_ORG_V_list) {
            for (int i= 1; i>=0; i--) {
                map.put("time", LoadMyUtil.getMyTime("YEAR", -i));
                map.put("ORG_CODE", yJJC_QWJJ_ORG_V.getORG_CODE());
                num += Integer.parseInt(yJJC_QWJJ_OUT_VISITS_DAY_VMapper.getSumOfOutpatient(map));
            }
            nameValueModel_list.add(NameValueModel.builder().name(yJJC_QWJJ_ORG_V.getORG_NAME()).value(String.valueOf(num)).build());
        }
        return nameValueModel_list;
    }

    //近一个月各医院门诊人次
    public List<NameValueModel> outpatientHospitalNearMonth(){
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = getHospital();
        //寻找有数据的最近一个月
        String nearTime = yJJC_QWJJ_OUT_VISITS_DAY_VMapper.getLearlyMonthRowNum();
        int num = 0;
        Map map = new HashMap();
        map.put("time", nearTime.substring(0,7));
        for(YJJC_QWJJ_ORG_V yJJC_QWJJ_ORG_V:yJJC_QWJJ_ORG_V_list){
            map.put("ORG_CODE", yJJC_QWJJ_ORG_V.getORG_CODE());
            num = Integer.parseInt(yJJC_QWJJ_OUT_VISITS_DAY_VMapper.getSumOfOutpatient(map));
            nameValueModel_list.add(NameValueModel.builder().name(yJJC_QWJJ_ORG_V.getORG_NAME()).value(String.valueOf(num)).build());
        }
        return nameValueModel_list;
    }

    //医院各科室门诊人次--最近一年的数据
    public NameDataModel outpatientHospitalDepartment(){
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
            List<HashMap> list = yJJC_QWJJ_OUT_VISITS_DAY_VMapper.outpatientHospitalDepartment(map);
            nameDataModelList.add(NameDataModel.builder().name(u.getORG_NAME()).data(list).build());
        }
        return NameDataModel.builder().name("医院各科室门诊人次--最近一年的数据").data(nameDataModelList).build();
    }
}
