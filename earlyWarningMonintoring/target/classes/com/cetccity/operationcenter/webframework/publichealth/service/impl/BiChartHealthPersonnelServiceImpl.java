package com.cetccity.operationcenter.webframework.publichealth.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.publichealth.api.model.EMPLOYEE_NUM;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_EMPLOYEE_VMapper;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_ORG_VMapper;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_EMPLOYEE_V;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_ORG_V;
import com.cetccity.operationcenter.webframework.publichealth.service.BiChartHealthPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:09 2019-03-06
 * Updater:     heliangming
 * Update_Date：10:09 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Service
public class BiChartHealthPersonnelServiceImpl implements BiChartHealthPersonnelService {

    @Autowired
    YJJC_QWJJ_EMPLOYEE_VMapper yJJC_QWJJ_EMPLOYEE_VMapper;

    @Autowired
    YJJC_QWJJ_ORG_VMapper yJJC_QWJJ_ORG_VMapper;

    //获取福田区下辖的医院
    public List<YJJC_QWJJ_ORG_V> getHospital(){
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = yJJC_QWJJ_ORG_VMapper.getYJJC_QWJJ_ORG_VRemoval();
        return yJJC_QWJJ_ORG_V_list;
    }

    public List<NameValueModel> healthPersonnelNum(){
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        List<YJJC_QWJJ_ORG_V> yJJC_QWJJ_ORG_V_list = getHospital();
        yJJC_QWJJ_ORG_V_list.forEach(yJJC_QWJJ_ORG_V->{
            YJJC_QWJJ_EMPLOYEE_V yJJC_QWJJ_EMPLOYEE_V = new YJJC_QWJJ_EMPLOYEE_V();
            yJJC_QWJJ_EMPLOYEE_V.setORG_CODE(yJJC_QWJJ_ORG_V.getORG_CODE());
            int num = yJJC_QWJJ_EMPLOYEE_VMapper.getCount(yJJC_QWJJ_EMPLOYEE_V);
            nameValueModel_list.add(NameValueModel.builder().name(yJJC_QWJJ_ORG_V.getORG_NAME()).value(String.valueOf(num)).build());
        });
        return nameValueModel_list;
    }

    //所有医院的编制情况
    public List<NameValueModel> healthPersonnelAuthorizedNum(){
        List<EMPLOYEE_NUM> aUTHORIZED_NUM_list = yJJC_QWJJ_EMPLOYEE_VMapper.getAUTHORIZED_NUM();
        return returnNameValueModel(aUTHORIZED_NUM_list);
    }

    //各医院医师执业类别情况
    public List<NameValueModel> healthPersonnelDOCTOR_TYPENum(){
        List<EMPLOYEE_NUM> aUTHORIZED_NUM_list = yJJC_QWJJ_EMPLOYEE_VMapper.getDOCTOR_TYPE_NUM();
        return returnNameValueModel(aUTHORIZED_NUM_list);
    }

    //各医院专业技术职务（聘）情况
    public List<NameValueModel> healthPersonnelJOB_LEVEL_CODENum(){
        List<EMPLOYEE_NUM> aUTHORIZED_NUM_list = yJJC_QWJJ_EMPLOYEE_VMapper.getJOB_LEVEL_CODE_NUM();
        return returnNameValueModel(aUTHORIZED_NUM_list);
    }

    //各医院从事的专业类别情况
    public List<NameValueModel> healthPersonnelCSZYLBDMNum(){
        List<EMPLOYEE_NUM> aUTHORIZED_NUM_list = yJJC_QWJJ_EMPLOYEE_VMapper.getCSZYLBDMNum();
        return returnNameValueModel(aUTHORIZED_NUM_list);
    }

    public List<NameValueModel> returnNameValueModel(List<EMPLOYEE_NUM> eMPLOYEE_NUM_list) {
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        eMPLOYEE_NUM_list.forEach(eMPLOYEE_NUM -> {
            nameValueModel_list.add(NameValueModel.builder().name(eMPLOYEE_NUM.getChinese()).value(eMPLOYEE_NUM.getNUM()).build());
        });
        return nameValueModel_list;
    }
}
