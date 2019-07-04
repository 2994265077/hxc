package com.cetccity.operationcenter.webframework.hiddendanger.api.detail;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.ZHUJIAN_BIANPO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api.detail
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:26 2019-07-03
 * Updater:     heliangming
 * Update_Date：15:26 2019-07-03
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "安全隐患一张图--建筑边坡详情")
@RequestMapping("/hiddendanger")
public interface ZhuJianBianPoDetailApi {

    @ApiOperation(value = "建筑边坡详情", notes = "建筑边坡详情")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "设备id=AAAVgtAAEAAEXS7AAB", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/bianpo/details",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    ZHUJIAN_BIANPO findDetails(String id);

}
