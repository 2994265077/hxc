package com.cetccity.operationcenter.webframework.hiddendanger.api.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:57 2019-03-29
 * Updater:     heliangming
 * Update_Date：14:57 2019-03-29
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "安全隐患一张图--应急管理局--风险隐患点落图")
@RequestMapping("/hiddendanger")
public interface RiskPointMapApi {

    /**
     * 风险隐患点落图
     * @param street 街道
     * @param id 主键
     * @return
     * @throws IOException IOException
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--福保街道", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "主键--AAATdZAAEAABAHHAAA", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/RISK_POINT/IconType/selectByExample", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    List<IconTypeLoadMap> findIconTypeByRISK_POINT(String street, String id) throws IOException;

}
