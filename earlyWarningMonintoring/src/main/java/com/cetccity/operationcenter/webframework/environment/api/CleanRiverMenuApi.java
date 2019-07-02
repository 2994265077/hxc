package com.cetccity.operationcenter.webframework.environment.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:26 2019-05-20
 * Updater:     heliangming
 * Update_Date：14:26 2019-05-20
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "生态环境--环境治理分析--清洁护河菜单")
@RequestMapping("/environment")
public interface CleanRiverMenuApi {

    @ApiOperation(value = "清洁护河--排水户菜单", notes = "清洁护河--排水户菜单")
    @RequestMapping(value = "/drain/hold/menu",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueUnitModel> drainHoldMenu();

    @ApiOperation(value = "清洁护河--排水设施菜单", notes = "清洁护河--排水设施菜单")
    @RequestMapping(value = "/drain/facilities/menu",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueUnitModel> drainFacilitiesMenu();

}
