package com.cetccity.operationcenter.webframework.hiddendanger.controller.detail;

import com.cetccity.operationcenter.webframework.hiddendanger.api.detail.ZhuJianBianPoDetailApi;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.ZHUJIAN_BIANPO;
import com.cetccity.operationcenter.webframework.hiddendanger.service.ZHUJIAN_BIANPOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.controller.detail
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:28 2019-07-03
 * Updater:     heliangming
 * Update_Date：15:28 2019-07-03
 * 更新描述:    heliangming 补充
 **/
@RestController
public class ZhuJianBianPoDetailController implements ZhuJianBianPoDetailApi {

    @Autowired
    ZHUJIAN_BIANPOService zHUJIAN_BIANPOService;

    public ZHUJIAN_BIANPO findDetails(String id){
        ZHUJIAN_BIANPO object =  zHUJIAN_BIANPOService.getObject(id);
        return object;
    }
}
