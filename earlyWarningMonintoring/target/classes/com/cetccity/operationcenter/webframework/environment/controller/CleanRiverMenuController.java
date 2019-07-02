package com.cetccity.operationcenter.webframework.environment.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.environment.api.CleanRiverMenuApi;
import com.cetccity.operationcenter.webframework.environment.util.DrainMenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:08 2019-05-21
 * Updater:     heliangming
 * Update_Date：10:08 2019-05-21
 * 更新描述:    heliangming 补充
 **/
@RestController
public class CleanRiverMenuController implements CleanRiverMenuApi {

    @Autowired
    DrainMenuUtil drainMenuUtil;
    /**
     * 排水户菜单
     * @return
     */
    public List<NameValueUnitModel> drainHoldMenu(){
        return drainMenuUtil.drainHoldMenu();
    }

    /**
     * 排水设施菜单
     * @return
     */
    public List<NameValueUnitModel> drainFacilitiesMenu(){
        return drainMenuUtil.drainFacilitiesMenu();
    }
}
