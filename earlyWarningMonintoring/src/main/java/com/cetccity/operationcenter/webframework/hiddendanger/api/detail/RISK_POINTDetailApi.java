package com.cetccity.operationcenter.webframework.hiddendanger.api.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api.detail
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:00 2019-03-29
 * Updater:     heliangming
 * Update_Date：10:00 2019-03-29
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "安全隐患一张图--风险隐患点详情")
@RequestMapping("/hiddendanger")
public interface RISK_POINTDetailApi {

    /**
     * 风险隐患点详情
     * @param id 主键id
     * @return
     */
    @ApiOperation(value = "风险隐患点详情", notes = "风险隐患点详情 表名RISK_POINT")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "主键id=AAATdZAAEAABAHDAAF", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/risk/point/details", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel findRISK_POINTDetail(String id);

    /**
     * 风险隐患点下钻
     * @return
     */
    @ApiOperation(value = "风险隐患点下钻", notes = "风险隐患点下钻 表名RISK_POINT")
    @RequestMapping(value = "/risk/point/DrillDown", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    HashMap<String, Object> riskPointDrillDown();




}
