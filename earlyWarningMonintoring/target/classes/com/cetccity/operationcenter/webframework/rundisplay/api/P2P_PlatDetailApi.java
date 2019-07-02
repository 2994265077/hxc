package com.cetccity.operationcenter.webframework.rundisplay.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.P2P_PlatBasicInformation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.api
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:30 2019-03-06
 * Updater:     heliangming
 * Update_Date：11:30 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "运行展现--P2P详情")
@RequestMapping("/rundisplay")
public interface P2P_PlatDetailApi {

    @ApiOperation(value = "P2P平台详情", notes = "P2P平台详情--P2P_BUSINESS_GLOBAL")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--AAASv8AAEAAA6dkAAA", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/p2p/plat/information",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameDataModel> p2pPlatInformation(String id);

    @ApiOperation(value = "私募机构详情", notes = "私募机构--P2P_PRIVATE_AGENCIES")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--AAATT9AAEAAA/btAAI", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/p2p/private/information",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameDataModel> p2pPrivateInformation(String id);

    @ApiOperation(value = "其它涉众金融平台详情", notes = "其它涉众金融平台详情--P2P_OTHER_PLATFORM")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--AAASv8AAEAAA6dkAAA", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/p2p/other/information",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameDataModel> p2pOtherInformation(String id);

}
