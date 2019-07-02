package com.cetccity.operationcenter.webframework.rundisplay.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.rundisplay.api.P2P_PlatDetailApi;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.P2P_PlatBasicInformation;
import com.cetccity.operationcenter.webframework.rundisplay.service.P2P_OtherDetailService;
import com.cetccity.operationcenter.webframework.rundisplay.service.P2P_PlatDetailService;
import com.cetccity.operationcenter.webframework.rundisplay.service.P2P_PrivateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.controller
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:38 2019-03-06
 * Updater:     heliangming
 * Update_Date：11:38 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@RestController
public class P2P_PlatDetailController implements P2P_PlatDetailApi {

    @Autowired
    P2P_PlatDetailService p2P_PlatDetailService;

    @Autowired
    P2P_PrivateDetailService p2P_PrivateDetailService;

    @Autowired
    P2P_OtherDetailService p2P_OtherDetailService;

    public List<NameDataModel> p2pPlatInformation(String id){
        List<NameDataModel> nameDataModel_list = p2P_PlatDetailService.p2pPlatInformation(id);
        return nameDataModel_list;
    }

    public List<NameDataModel> p2pPrivateInformation(String id){
        List<NameDataModel> nameDataModel_list = p2P_PrivateDetailService.p2pPrivateInformation(id);
        return nameDataModel_list;
    }

    public List<NameDataModel> p2pOtherInformation(String id){
        List<NameDataModel> nameDataModel_list = p2P_OtherDetailService.p2pOtherInformation(id);
        return nameDataModel_list;
    }

}
