package com.cetccity.operationcenter.webframework.hiddendanger.api.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.LinkedHashMap;
import java.util.List;

@Api(tags = "安全隐患一张图--物联网详情")
@RequestMapping("/hiddendanger")
public interface IotIndexDetectionDetailsApi {

    @ApiOperation(value = "查询设备记录", notes = "根据设备id设备记录 表名iot_event")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "设备id=0025034418080023", name = "device_code", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/iot/IndexDetectionDetails",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LinkedHashMap> findIndexDetectionDetails(String device_code) throws Exception;

    @ApiOperation(value = "查询设备记录", notes = "根据设备id设备记录 表名iot_event")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "页行数", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10"),
            @ApiImplicitParam(value = "设备id=0025034418080023", name = "device_code", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "预警编号=00251002", name = "alarm_type_code", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/iot/IndexDetectionDetails/pageInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel findIndexDetectionDetailsPageInfo(Integer pageNum, Integer pageSize, String device_code, String alarm_type_code) throws Exception;

}
