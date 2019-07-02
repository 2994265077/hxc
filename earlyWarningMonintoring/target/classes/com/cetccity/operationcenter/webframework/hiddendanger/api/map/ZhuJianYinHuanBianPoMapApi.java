package com.cetccity.operationcenter.webframework.hiddendanger.api.map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 12:02 2019-06-28
 * Updater:     heliangming
 * Update_Date：12:02 2019-06-28
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "住建隐患边坡--弹框")
@RequestMapping("/hiddendanger")
public interface ZhuJianYinHuanBianPoMapApi {

    @ApiOperation(value = "根据表名DB弹框", notes =
            "住建隐患边坡--ZHUJIAN_BIANPO  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--ZHUJIAN_BIANPO", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "主键--AAATdZAAEAABAHoAAA", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/ZHUJIAN_BIANPO/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    Map summaryInfo(String tableNameUrl, String id);
}
