package com.cetccity.operationcenter.webframework.rundisplay.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.P2P_OTHER_PLATFORMMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.P2P_OTHER_PLATFORM;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.P2P_PRIVATE_AGENCIES;
import com.cetccity.operationcenter.webframework.rundisplay.service.P2P_OtherDetailService;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.P2P_BUSINESS_GLOBAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:17 2019-03-06
 * Updater:     heliangming
 * Update_Date：17:17 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Service
public class P2P_OtherDetailServiceImpl implements P2P_OtherDetailService {

    @Autowired
    P2P_OTHER_PLATFORMMapper p2P_OTHER_PLATFORMMapper;

    public P2P_OTHER_PLATFORM getPrivateDetailInformation(String id){
        P2P_OTHER_PLATFORM p2P_OTHER_PLATFORM = new P2P_OTHER_PLATFORM();
        List<P2P_OTHER_PLATFORM> p2P_OTHER_PLATFORM_list = p2P_OTHER_PLATFORMMapper.getOtherDetail(id);
        if(p2P_OTHER_PLATFORM_list.size()!=0){
            return p2P_OTHER_PLATFORM_list.get(0);
        }else{
            return p2P_OTHER_PLATFORM;
        }
    }

    public List<NameDataModel> p2pOtherInformation(String id){
        List<NameDataModel> nameDataModel_list = new ArrayList<>();
        P2P_OTHER_PLATFORM p2P_OTHER_PLATFORM = getPrivateDetailInformation(id);
        //平台基本信息
        NameDataModel nameDataModel_basicInformation = new NameDataModel();
        String top_basicInformation[] = { "平台名称", "成立时间", "平台网站", "平台上线时间", "网站状态", "平台状态"};
        String topVaule_basicInformation[] = {p2P_OTHER_PLATFORM.getPLATFORM_NAME(),p2P_OTHER_PLATFORM.getFOUND_DATE(),
                p2P_OTHER_PLATFORM.getPLAT_WEBSITE(), p2P_OTHER_PLATFORM.getUPDATE_TIME(),
                p2P_OTHER_PLATFORM.getWEBSITE_STATUS(), p2P_OTHER_PLATFORM.getRUNNING_STATUS()};
        List<NameValueModel> basicInformation_list = new ArrayList<>();
        for (int i = 0;i<top_basicInformation.length;i++){
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(top_basicInformation[i]);
            nameValueModel.setValue(topVaule_basicInformation[i]);
            basicInformation_list.add(nameValueModel);*/
            basicInformation_list.add(NameValueModel.builder().name(top_basicInformation[i]).value(topVaule_basicInformation[i]).build());
        }
        nameDataModel_basicInformation.setName("平台基本信息");
        nameDataModel_basicInformation.setData(basicInformation_list);
        nameDataModel_list.add(nameDataModel_basicInformation);
        //运营主体企业信息
        NameDataModel nameDataModel_operationSubject = new NameDataModel();
        String top_operationSubject[] = {"企业名称","统一信用代码","注册资本","实缴资本","注册地","注册地址","经营地","经营地址","所属街道"};
        String topVaule_operationSubject[] = {p2P_OTHER_PLATFORM.getORG_NAME(),p2P_OTHER_PLATFORM.getSOCAIL_CREDIT_CODE(),
                p2P_OTHER_PLATFORM.getREGISTER_CAPITAL(), p2P_OTHER_PLATFORM.getREAL_CAPITAL(),
                p2P_OTHER_PLATFORM.getREG_ADDR(), p2P_OTHER_PLATFORM.getREG_ADDRESS(),
                p2P_OTHER_PLATFORM.getOPER_ADDR(), p2P_OTHER_PLATFORM.getOPER_ADDRESS(),
                p2P_OTHER_PLATFORM.getSTREET_NAME()};
        List<NameValueModel> operationSubject_list = new ArrayList<>();
        for (int i = 0;i<top_operationSubject.length;i++){
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(top_operationSubject[i]);
            nameValueModel.setValue(topVaule_operationSubject[i]);
            operationSubject_list.add(nameValueModel);*/
            operationSubject_list.add(NameValueModel.builder().name(top_operationSubject[i]).value(topVaule_operationSubject[i]).build());
        }
        nameDataModel_operationSubject.setName("运营主体企业信息");
        nameDataModel_operationSubject.setData(operationSubject_list);
        nameDataModel_list.add(nameDataModel_operationSubject);

        //相关责任人
        NameDataModel nameDataModel_personliable = new NameDataModel();
        String top_personliable[] = {"股东背景","实际控制人","法定代表人","董事长","财务总监","技术总监","信息对接人"};
        String topVaule_personliable[] = {
                p2P_OTHER_PLATFORM.getSHAREHOLDER_BACK(),p2P_OTHER_PLATFORM.getCONTROLLER_NAME(),
                p2P_OTHER_PLATFORM.getLEGAL_PERSON_INFO(), p2P_OTHER_PLATFORM.getCHAIRMAN_INFO(),
                p2P_OTHER_PLATFORM.getCFO_INFO(), p2P_OTHER_PLATFORM.getCTO_INFO(),
                p2P_OTHER_PLATFORM.getCONTACTOR_INFO()};
        List<NameValueModel> personliable_list = new ArrayList<>();
        for (int i = 0;i<top_personliable.length;i++){
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(top_personliable[i]);
            nameValueModel.setValue(topVaule_personliable[i]);
            personliable_list.add(nameValueModel);*/
            personliable_list.add(NameValueModel.builder().name(top_personliable[i]).value(topVaule_personliable[i]).build());
        }
        nameDataModel_personliable.setName("相关责任人信息");
        nameDataModel_personliable.setData(personliable_list);
        nameDataModel_list.add(nameDataModel_personliable);
        //借款和出借情况
        NameDataModel nameDataModel_borrow = new NameDataModel();
        String top_borrow[] = {"存管银行","经营情况","借款人总数","借款人总余额","排查情况","1万以下人数","1万以下投资额","1-5万人数",
                "1-5万投资金额","5-10万人数","5-10万投资金额","10-50万人数","10-50万投资金额","50万以上人数","50万以上投资金额",
                "深圳本地人数","深圳本地人数占比","深圳以外人数","深圳以外人数占比","出借人结构基本情况"};
        String topVaule_borrow[] = {
                p2P_OTHER_PLATFORM.getKEPT_BANK(),p2P_OTHER_PLATFORM.getRUNNING_STATUS(),
                p2P_OTHER_PLATFORM.getTOTAL_BORROWER(), p2P_OTHER_PLATFORM.getBORROWER_BALANCE(),
                p2P_OTHER_PLATFORM.getINVEST_SITUATION(), p2P_OTHER_PLATFORM.getNUM_UNDER_1W(),
                p2P_OTHER_PLATFORM.getINVEST_UNDER_1W(), p2P_OTHER_PLATFORM.getNUM_1W_5W(),
                p2P_OTHER_PLATFORM.getINVEST_1W_5W(), p2P_OTHER_PLATFORM.getNUM_5W_10W(),
                p2P_OTHER_PLATFORM.getINVEST_5W_10W(), p2P_OTHER_PLATFORM.getNUM_10W_15W(),
                p2P_OTHER_PLATFORM.getINVEST_10W_50W(), p2P_OTHER_PLATFORM.getNUM_UP_50W(),
                p2P_OTHER_PLATFORM.getINVEST_UP_50W(), p2P_OTHER_PLATFORM.getNUM_SZ_LOCAL(),
                p2P_OTHER_PLATFORM.getNUM_SZ_LOCAL_RATE(), p2P_OTHER_PLATFORM.getNUM_OUT_SZ(),
                p2P_OTHER_PLATFORM.getNUM_OUT_SZ_RATE(), p2P_OTHER_PLATFORM.getLENDER_STRUC_INFO()};
        List<NameValueModel> borrow_list = new ArrayList<>();
        for (int i = 0;i<top_borrow.length;i++){
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(top_borrow[i]);
            nameValueModel.setValue(topVaule_borrow[i]);
            borrow_list.add(nameValueModel);*/
            borrow_list.add(NameValueModel.builder().name(top_borrow[i]).value(topVaule_borrow[i]).build());
        }
        nameDataModel_borrow.setName("借款和出借情况");
        nameDataModel_borrow.setData(borrow_list);
        nameDataModel_list.add(nameDataModel_borrow);
        //风险评估
        NameDataModel nameDataModel_risk = new NameDataModel();
        String top_risk[] = {"风险类别","风险等级","疑似风险点","冒烟指数","跟进警官","专案人员","街道经办人",
                "行业性质","经营地标签","注册地标签","是否在市名单"};
        String topVaule_risk[] = {
                p2P_OTHER_PLATFORM.getRISK_TYPE(),p2P_OTHER_PLATFORM.getRISK_LEVEL(),
                p2P_OTHER_PLATFORM.getSUSPECT_RISK(), p2P_OTHER_PLATFORM.getSMOKE_INDEX(),
                p2P_OTHER_PLATFORM.getFOLLOW_POLICEMAN(), p2P_OTHER_PLATFORM.getSPECIAL_CASE_PERSON(),
                p2P_OTHER_PLATFORM.getSTREET_HANDLER(), p2P_OTHER_PLATFORM.getINDUSTRY_NATURE(),
                p2P_OTHER_PLATFORM.getBUSINESS_TAG(), p2P_OTHER_PLATFORM.getREGISTER_TAG(),
                p2P_OTHER_PLATFORM.getIS_IN_CITYLIST()};
        List<NameValueModel> risk_list = new ArrayList<>();
        for (int i = 0;i<top_risk.length;i++){
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(top_risk[i]);
            nameValueModel.setValue(topVaule_risk[i]);
            risk_list.add(nameValueModel);*/
            risk_list.add(NameValueModel.builder().name(top_risk[i]).value(topVaule_risk[i]).build());
        }
        nameDataModel_risk.setName("风险评估");
        nameDataModel_risk.setData(risk_list);
        nameDataModel_list.add(nameDataModel_risk);

        return nameDataModel_list;
    }
}
