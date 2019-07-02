package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.RiskPointMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.RISK_POINT;
import com.cetccity.operationcenter.webframework.hiddendanger.service.RISK_POINTDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:05 2019-03-29
 * Updater:     heliangming
 * Update_Date：10:05 2019-03-29
 * 更新描述:    heliangming 补充
 **/
@Service
public class RISK_POINTDetailServiceImpl implements RISK_POINTDetailService {

    @Autowired
    RiskPointMapper rISK_POINTMapper;

    public NameDataModel findRISK_POINTDetail(String id){
        NameDataModel nameDataModel = new NameDataModel();
        List<RISK_POINT> rISK_POINT_list = rISK_POINTMapper.findRISK_POINTDetail(id);
        RISK_POINT u = rISK_POINT_list.get(0);
        //1、风险点详情
        NameDataModel nameDataModel_1 = new NameDataModel();
        String name1[] = {"风险源名称","事故发生可能性","事故发生可能性","影响范围","影响人员","可能造成的财产损失","环境影响","社会影响"};
        String value1[] = {u.getDETAIL_NAME(),u.getINCIDT_POSSIBILITY(),u.getPRONE_ACC_TYPE(),u.getINFLUNCE_AREA(),
                u.getINFLUNCE_PEOPLE(),u.getPROPERTIE_LOSS(),u.getENV_INFLUNCE(),u.getSOCIAL_INFLUNCE()};
        List<NameValueModel> nameValueModel_list_1 = LoadMyUtil.nameValueList(name1,value1);
        nameDataModel_1.setName("风险点详情");
        nameDataModel_1.setData(nameValueModel_list_1);
        //2、风险评分
        NameDataModel nameDataModel_2 = new NameDataModel();
        String name2[] = {"维度","事故发生可能性（L）","人员暴露的频繁程度（E）","发生事故后果的严重性（C）","风险等级判定（L*E*C）"};
        String value2[] = {"数值",u.getINCIDT_POSSIBILITY(),u.getEXPOSE_FREQUNCE(),u.getRISK_SEVERITY(),u.getOPERATE_HAZARDOUS()};
        String unit2[] = {"等级","可能，但不经常","每天工作事件内暴露","3~9人死亡","极高"};
        List<NameValueUnitModel> nameValueUnitModel_list_2 = new ArrayList<>();
        for(int i=0;i<name2.length;i++){
            NameValueUnitModel nameValueUnitModel = new NameValueUnitModel();
            nameValueUnitModel.setName(name2[i]);
            nameValueUnitModel.setValue(value2[i]);
            nameValueUnitModel.setUnit(unit2[i]);
            nameValueUnitModel_list_2.add(nameValueUnitModel);
        }
        nameDataModel_2.setName("风险评分");
        nameDataModel_2.setData(nameValueUnitModel_list_2);
        //3、责任人
        NameDataModel nameDataModel_3 = new NameDataModel();
        String name3[] = {"单位名称","风险点负责人","风险点负责人电话","安全负责人","安全负责人电话","挂点专家","挂点专家电话","主管（监管）单位"};
        String value3[] = {u.getUNIT_NAME(),u.getPOINT_MANAGER(),u.getCONN_TEL(),u.getSECURITY_MANAGER(),
                u.getCONN_TEL_0(),u.getPOINT_EXPERT(),u.getCONN_TEL_1(),u.getSUPERVISE_UNIT()};
        List<NameValueModel> nameValueModel_list_3 = LoadMyUtil.nameValueList(name3,value3);
        nameDataModel_3.setName("责任人");
        nameDataModel_3.setData(nameValueModel_list_3);
        //4、措施与建议
        NameDataModel nameDataModel_4 = new NameDataModel();
        String name4[] = {"存在的隐患情况","应急处置措施","管控措施"};
        String value4[] = {u.getHIDDEN_DANGER(),u.getEMERGENCY_MEASURES(),u.getCONTROL_MEASURES()};
        List<NameValueModel> nameValueModel_list_4 = LoadMyUtil.nameValueList(name4,value4);
        nameDataModel_4.setName("风险点详情");
        nameDataModel_4.setData(nameValueModel_list_4);

        //拼接开始--------------------------
        List<NameDataModel> nameDataModel_list = new ArrayList<>();
        nameDataModel_list.add(nameDataModel_1);
        nameDataModel_list.add(nameDataModel_2);
        nameDataModel_list.add(nameDataModel_3);
        nameDataModel_list.add(nameDataModel_4);
        nameDataModel.setName(u.getDETAIL_ADDRESS());
        nameDataModel.setData(nameDataModel_list);
        return nameDataModel;
    }

    public HashMap<String,Object> riskPointDrillDown(){
        HashMap<String,Object> queryMap = rISK_POINTMapper.riskPointDrillDown();
        return queryMap;
    }
}
