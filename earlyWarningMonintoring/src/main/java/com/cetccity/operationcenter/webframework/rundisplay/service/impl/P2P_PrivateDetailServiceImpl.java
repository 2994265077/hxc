package com.cetccity.operationcenter.webframework.rundisplay.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.P2P_PRIVATE_AGENCIESMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.P2P_PRIVATE_AGENCIES;
import com.cetccity.operationcenter.webframework.rundisplay.service.P2P_PrivateDetailService;
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
 * Create_Date: 16:51 2019-03-06
 * Updater:     heliangming
 * Update_Date：16:51 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Service
public class P2P_PrivateDetailServiceImpl implements P2P_PrivateDetailService {

    @Autowired
    P2P_PRIVATE_AGENCIESMapper p2P_PRIVATE_AGENCIESMapper;

    public P2P_PRIVATE_AGENCIES getPrivateDetailInformation(String id){
        P2P_PRIVATE_AGENCIES p2P_PRIVATE_AGENCIES = new P2P_PRIVATE_AGENCIES();
        List<P2P_PRIVATE_AGENCIES> p2P_PRIVATE_AGENCIES_list = p2P_PRIVATE_AGENCIESMapper.getPrivateDetail(id);
        if(p2P_PRIVATE_AGENCIES_list.size()!=0){
            return p2P_PRIVATE_AGENCIES_list.get(0);
        }else{
            return p2P_PRIVATE_AGENCIES;
        }
    }

    public List<NameDataModel> p2pPrivateInformation(String id){
        List<NameDataModel> nameDataModel_list = new ArrayList<>();
        P2P_PRIVATE_AGENCIES p2P_PRIVATE_AGENCIES = getPrivateDetailInformation(id);
        //私募机构详细信息
        NameDataModel nameDataModel_detailInformation = new NameDataModel();
        String top_detailInformation[] = {"机构名称","机构P码","注册资本(万元)","实缴资本(万元)","成立时间","通过时间",
                "登记时间","注册地","注册地址","办公省","办公市","办公区","办公详细地址","管理基金只数",
                "管理基金规模（总资产）","管理基金规模（净资产）","投资者人数","管理人币种","机构类型","业务类型",
                "组织形式","企业性质","控股类型","具有基金从业资格人数","员工人数","主要联系人姓名","联系人手机","主要联系人邮箱"};
        String topVaule_detailInformation[] = {p2P_PRIVATE_AGENCIES.getDEPT_NAME(),p2P_PRIVATE_AGENCIES.getORG_P_CODE(),
                p2P_PRIVATE_AGENCIES.getREGI_CAPITAL(), p2P_PRIVATE_AGENCIES.getREAL_CAPITAL(),
                p2P_PRIVATE_AGENCIES.getFOUND_DATE(), p2P_PRIVATE_AGENCIES.getPASS_DATE(),
                p2P_PRIVATE_AGENCIES.getREGISTER_DATE(), p2P_PRIVATE_AGENCIES.getREG_PLACE(),
                p2P_PRIVATE_AGENCIES.getREG_ADDRESS(), p2P_PRIVATE_AGENCIES.getPROVINCE(),
                p2P_PRIVATE_AGENCIES.getCITY(), p2P_PRIVATE_AGENCIES.getREGION(),
                p2P_PRIVATE_AGENCIES.getDETAIL_ADDRESS(), p2P_PRIVATE_AGENCIES.getMNG_FOUND_NUM(),
                p2P_PRIVATE_AGENCIES.getMNG_FOUND_SCALE_TOTAL(), p2P_PRIVATE_AGENCIES.getMNG_FOUND_SCALE_PURE(),
                p2P_PRIVATE_AGENCIES.getINVESTOR_NUM(), p2P_PRIVATE_AGENCIES.getMNG_CURRENCY(),
                p2P_PRIVATE_AGENCIES.getORG_TYPE(), p2P_PRIVATE_AGENCIES.getBUSINESS_TYPE(),
                p2P_PRIVATE_AGENCIES.getORG_FORM(), p2P_PRIVATE_AGENCIES.getENTP_NATURE(),
                p2P_PRIVATE_AGENCIES.getHOLD_TYPE(), p2P_PRIVATE_AGENCIES.getQUALIFIED_EMPLOYEES(),
                p2P_PRIVATE_AGENCIES.getSTAFF(), p2P_PRIVATE_AGENCIES.getMAIN_CONTACTOR_NAME(),
                p2P_PRIVATE_AGENCIES.getCONTACTOR_PHONE(), p2P_PRIVATE_AGENCIES.getCONTACTOR_EMAIL()};
        List<NameValueModel> detailInformation_list = new ArrayList<>();
        for (int i = 0;i<top_detailInformation.length;i++){
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(top_detailInformation[i]);
            nameValueModel.setValue(topVaule_detailInformation[i]);
            detailInformation_list.add(nameValueModel);*/
            detailInformation_list.add(NameValueModel.builder().name(top_detailInformation[i]).value(topVaule_detailInformation[i]).build());
        }
        nameDataModel_detailInformation.setName("私募机构详细信息");
        nameDataModel_detailInformation.setData(detailInformation_list);
        nameDataModel_list.add(nameDataModel_detailInformation);
        return nameDataModel_list;
    }
}
