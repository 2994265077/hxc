package com.cetccity.operationcenter.webframework.hiddendanger.api.map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

@Api(tags = "安全隐患一张图--弹框")
@RequestMapping("/hiddendanger")
public interface TooltipApi {

    @ApiOperation(value = "根据表名DB弹框", notes =
                    "安监-隐患分布--T_INSRECORD  id=AAAUu6AAEAABzs7AAB  \n" +
                    "安监-安监事故--QAJJ_REPACCIDENT_V  id=AAAUvKAAEAAB0dTAAD  \n" +
                    "消防-重大危险源--TB_ZHONGDAWEIXIANYUAN_002  id=AAAUvLAAEAAB0dlAAK  \n" +
                    "消防-隐患三色--T_XIAOFANG_BUWEI  id=AAAUvMAAEAAB0dsAAD  \n" +
                    "消防-隐患三色（红色）--T_XIAOFANG_BUWEI@hongse  id=AAAUvMAAEAAB0dtAAF  \n" +
                    "消防-隐患三色（橙色）--T_XIAOFANG_BUWEI@cengse  id=AAAUvMAAEAAB0dsAAT  \n" +
                    "消防-隐患三色（黄色）--T_XIAOFANG_BUWEI@huangse  id=AAAUvMAAEAAB0dsAAE  \n" +
                    "消防-消防栓--TB_XIAOHUOSHUAN_002  id=AAAUvNAAEAAB0dzAAG  \n" +
                    "消防-重点场所--ZHONGDIANCHANGSUO  id=AAAUvPAAEAAB0v7AAD  \n" +
                    "消防-避难场所--EMERGENCY_SHELTER  id=AAAUxcAAEAAB4gVAAC  \n" +
                    "养老中心--FUTIAN_PENSION_CENTER  id=AAAUxQAAEAAB4ZsAAB  \n" +
                    "应急资源仓库--EMERGENCY_WAREHOUSE  id=AAAUxZAAEAAB4fMAAC  \n" +
                    "应急_救援队伍--EMERGENCY_RESCUE_TEAM  id=AAAUxbAAEAAB4gLAAE  \n" +
                    "应急_危险源和防范区--EMERGENCY_HARZARD  id=AAAUxaAAEAAB4fTAAD  \n" +
                    "教育场所--FUTIAN_SCHOOL  id=AAAUxJAAEAAB4R8AAA  \n" +
                    "燃气站--RANQIZHAN_001  id=  \n" +
                    "消防-建筑风险--T_BUILD_INFO_V  id=AAAUw6AAEAAB3fhAAC  \n" +
                    "三防-道路病害--TB_ROAD_DISEASE  id=AAAUrkAAEAABzdjAAB  \n"+
                    "生态环境-空气--QHSJ_T_AIR_BASICINFO  \n"+
                    "生态环境-水质--QHSJ_SURF_WATER_BASICINFO  \n"+
                    "生态环境-污水源--QHSJ_ENTERPRISE_BASICINFO  \n"+
                    "今日预警--ALARM_INFORMATION  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--TB_ROAD_DISEASE", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "主键--AAAUrkAAEAABzdjAAB", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/{tableNameUrl}/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    Map summaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String id);

    @ApiOperation(value = "根据表名DB弹框", notes =
                    "安监-风险隐患点--RISK_POINT  id=493EC789E94BC730E0530142BE0AE8C9  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--RISK_POINT", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "主键--AAATdZAAEAABAHoAAA", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/{tableNameUrl}/IconType/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    Map summaryInfoOfIconType(@PathVariable("tableNameUrl") String tableNameUrl, String id);

    @ApiOperation(value = "根据表名物联网DB弹框", notes =
            "物联网-烟雾传感器--IOT_DEVICE@0003  \n"+
            "物联网-电气火灾监测设备--IOT_DEVICE@0024 id=0024034418061788  \n+" +
            "物联网-压力传感器--IOT_DEVICE@0025 id=0025034418080023  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--IOT_DEVICE@0026", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "主键--0026024418070003", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/{tableNameUrl}/iot/IconType/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    Map iotSummaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String id);

    @ApiOperation(value = "根据表名物联网DB弹框", notes = "三防-易涝点--IOT_DEVICE@0026  id=0026014418070030  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--IOT_DEVICE@0026", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "主键--0026024418070003", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/IOT_DEVICE@0026/iot/IconType/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    Map iot0026SummaryInfo(String id);

    @ApiOperation(value = "根据表名建筑物DB弹框", notes = "根据表名建筑物DB弹框")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--t_build_info_v", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "建筑编码BuildId--4403040020080200012", name = "buildid", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/{tableNameUrl}/build/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    Map buildSummaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String buildid);

    @ApiOperation(value = "根据表名DB弹框", notes =
                    "城区风险评估--工业企业(2)URBAN_RISK@2  \n"+
                    "城区风险评估--人员密集场所(3)URBAN_RISK@3  \n"+
                    "城区风险评估--公共基础设施(4)URBAN_RISK@4  \n"+
                    "城区风险评估--其他风险单位(5)URBAN_RISK@5  \n"+
                    "安监-纳管企业--QAJJ_PUCENTP_V  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--URBAN_RISK@sanxiaoplace", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "主键--AAAScpAAEAAAgC8AAJ", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/{tableNameUrl}/IconType/chart/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    Map chartSummaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String id);
}
