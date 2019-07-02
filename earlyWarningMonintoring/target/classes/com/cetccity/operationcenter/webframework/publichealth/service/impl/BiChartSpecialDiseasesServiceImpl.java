package com.cetccity.operationcenter.webframework.publichealth.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.tools.DsDictUtil;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.publichealth.api.model.EMPLOYEE_NUM;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_ORG_VMapper;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_SDM_INFO_VMapper;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_ORG_V;
import com.cetccity.operationcenter.webframework.publichealth.service.BiChartSpecialDiseasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:48 2019-03-06
 * Updater:     heliangming
 * Update_Date：16:48 2019-03-06
 * 更新描述:    heliangming 补充
 **/

@Service
public class BiChartSpecialDiseasesServiceImpl implements BiChartSpecialDiseasesService {

    @Autowired
    YJJC_QWJJ_SDM_INFO_VMapper yJJC_QWJJ_SDM_INFO_VMapper;

    @Autowired
    YJJC_QWJJ_ORG_VMapper yJJC_QWJJ_ORG_VMapper;

    @Autowired
    DsDictUtil dsDictUtil;

    //12个月特殊病种数量走势
    public List<NameDataModel> specialDiseasesTrend(){
        List<NameDataModel> nameDataModel_list = new ArrayList<>();
        Map<String,String> trend = new HashMap<>();
        String num;
        //获取特殊病种
        String sql = "select CODE_IN_CHINESE, code from DS_VALUE_DICT where TABLE_NAME = 'YJJC_QWJJ_SDM_INFO_V' AND COLUMN_NAME = 'DIAG_TYPE'";
        List<LinkedHashMap> map_list = dsDictUtil.getDict(sql);
        for(int i=0;i<map_list.size();i++) {
            List<NameValueModel> nameValueModel_list = new ArrayList<>();
            trend.put("type",(String) map_list.get(i).get("CODE"));
            for (int j = 11; j >= 0; j--) {
                String time = LoadMyUtil.getMyTime("MONTH", -j);
                trend.put("time",time);
                List<EMPLOYEE_NUM> eMPLOYEE_NUM_list = yJJC_QWJJ_SDM_INFO_VMapper.specialDiseasesTrend(trend);
                if(eMPLOYEE_NUM_list.size()==0){
                    num = "0";
                }else {
                    num = eMPLOYEE_NUM_list.get(0).getNUM();
                }
                nameValueModel_list.add(NameValueModel.builder().name(time).value(num).build());
            }
            NameDataModel NameDataModel = new NameDataModel();
            NameDataModel.setName((String) map_list.get(i).get("CODE_IN_CHINESE"));
            NameDataModel.setData(nameValueModel_list);
            nameDataModel_list.add(NameDataModel);
        }
        return nameDataModel_list;
    }

    //各医院特殊病种统计
    public List<NameDataModel> specialDiseasesHospital(){
        List<NameDataModel> nameDataModel_list = new ArrayList<>();
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = getHospital();
        yJJC_QWJJ_ORG_V_list.forEach(yJJC_QWJJ_ORG_V->{
            NameDataModel nameDataModel = new NameDataModel();
            List<EMPLOYEE_NUM> eMPLOYEE_NUM_list = yJJC_QWJJ_SDM_INFO_VMapper.specialDiseasesHospital(yJJC_QWJJ_ORG_V.getORG_CODE());
            List<NameValueModel> nameValueModel_list = new ArrayList();
            eMPLOYEE_NUM_list.forEach(eMPLOYEE_NUM->{
                nameValueModel_list.add(NameValueModel.builder().name(eMPLOYEE_NUM.getChinese()).value(eMPLOYEE_NUM.getNUM()).build());
            });
            nameDataModel.setName(yJJC_QWJJ_ORG_V.getORG_NAME());
            nameDataModel.setData(nameValueModel_list);
            nameDataModel_list.add(nameDataModel);
        });
        return nameDataModel_list;
    }

    //获取福田区下辖的医院
    public List<YJJC_QWJJ_ORG_V> getHospital(){
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = yJJC_QWJJ_ORG_VMapper.getYJJC_QWJJ_ORG_VRemoval();
        return yJJC_QWJJ_ORG_V_list;
    }

    //所有特殊病种数量总和走势
    public List<NameValueModel> specialDiseasesNum(){
        List<EMPLOYEE_NUM> eMPLOYEE_NUM_list = yJJC_QWJJ_SDM_INFO_VMapper.getDIAG_TYPE_NUM();
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        eMPLOYEE_NUM_list.forEach(eMPLOYEE_NUM->{
            nameValueModel_list.add(NameValueModel.builder().name(eMPLOYEE_NUM.getChinese()).value(eMPLOYEE_NUM.getNUM()).build());
        });
        return nameValueModel_list;
    }

    //初诊、确证比例
    public List<NameValueModel> specialDiseasesProportion(){
        List<EMPLOYEE_NUM> eMPLOYEE_NUM_list = yJJC_QWJJ_SDM_INFO_VMapper.getDIAG_TYPE_CODE_NUM();
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        eMPLOYEE_NUM_list.forEach(eMPLOYEE_NUM->{
            nameValueModel_list.add(NameValueModel.builder().name(eMPLOYEE_NUM.getChinese()).value(eMPLOYEE_NUM.getNUM()).build());
        });
        return nameValueModel_list;
    }
}
