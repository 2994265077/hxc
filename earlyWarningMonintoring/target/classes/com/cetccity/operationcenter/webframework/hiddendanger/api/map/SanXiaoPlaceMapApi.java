package com.cetccity.operationcenter.webframework.hiddendanger.api.map;

import com.cetccity.operationcenter.webframework.hiddendanger.api.model.SanXiaoPlaceLoadMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
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
 * Create_Date: 9:19 2019-04-08
 * Updater:     heliangming
 * Update_Date：9:19 2019-04-08
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "安全隐患一张图--三小场所落图")
@RequestMapping("/hiddendanger")
public interface SanXiaoPlaceMapApi {

    @ApiOperation(value = "根据表名DB落图", notes =
            "安监-三小场所(小作坊PLACE_TYPE=60022)--BLK_SANXIAO_PLACE@60022  \n" +
            "安监-三小场所(小档口PLACE_TYPE=60021)--BLK_SANXIAO_PLACE@60021  \n" +
            "安监-三小场所(小娱乐场所PLACE_TYPE=60023)--BLK_SANXIAO_PLACE@60023  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--BLK_SANXIAO_PLACE@60022", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "主键--id", name = "id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "报警--alarm=1", name = "alarm", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/sanxiao/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<SanXiaoPlaceLoadMap> findSanXiaoByDB(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id, String alarm,String startTime,String endTime) throws IOException;

}
