package com.cetccity.operationcenter.webframework.hiddendanger.api.map;

import com.cetccity.operationcenter.webframework.hiddendanger.api.model.PageInfo_LoadMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;

@Api(tags = "安全隐患一张图--分页")
@RequestMapping("/hiddendanger")
public interface PageInfoApi {
    @ApiOperation(value = "根据表名DB分页", notes =
            "安监-隐患分布--T_INSRECORD  \n" +
            "安监-纳管企业--QAJJ_PUCENTP_V  \n" +
            "安监-安监事故--QAJJ_REPACCIDENT_V  \n" +
            "消防-重大危险源--TB_ZHONGDAWEIXIANYUAN_002  \n" +
            "消防-隐患三色--T_XIAOFANG_BUWEI  \n" +
            "消防-隐患三色（红色）--T_XIAOFANG_BUWEI@hongse  \n" +
            "消防-隐患三色（橙色）--T_XIAOFANG_BUWEI@cengse  \n" +
            "消防-隐患三色（黄色）--T_XIAOFANG_BUWEI@huangse  \n" +
            "消防-消防栓--TB_XIAOHUOSHUAN_002  \n" +
            "消防-重点场所--ZHONGDIANCHANGSUO  \n" +
            "消防-建筑风险--T_BUILD_INFO_V  \n" +
            "三防-易涝点--IOT_DEVICE@neilaojiance  \n" +
            "三防-道路病害--TB_ROAD_DISEASE  \n"+
            "生态环境-空气--QHSJ_T_AIR_BASICINFO  \n"+
            "生态环境-水质--QHSJ_SURF_WATER_BASICINFO  \n"+
            "生态环境-污水源--QHSJ_ENTERPRISE_BASICINFO  \n"+
            "今日预警--ALARM_INFORMATION  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "当前页", name = "pageNum", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "页码", name = "pageSize", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "街道", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/pageInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    PageInfo_LoadMap findPageInfoByDb(@PathVariable("tableNameUrl") String tableNameUrl, String pageNum, String pageSize, String street) throws IOException;

    @ApiOperation(value = "根据表名DB分页", notes =
                    "城区风险评估--工业企业(2)URBAN_RISK@2  \n"+
                    "城区风险评估--人员密集场所(3)URBAN_RISK@3  \n"+
                    "城区风险评估--公共基础设施(4)URBAN_RISK@4  \n"+
                    "城区风险评估--其他风险单位(5)URBAN_RISK@5  \n"+
                    "安监-三小场所(小作坊PLACE_TYPE=60022)--BLK_SANXIAO_PLACE@60022  \n" +
                    "安监-三小场所(小档口PLACE_TYPE=60021)--BLK_SANXIAO_PLACE@60021  \n" +
                    "安监-三小场所(小娱乐场所PLACE_TYPE=60023)--BLK_SANXIAO_PLACE@60023  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "当前页", name = "pageNum", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "页码", name = "pageSize", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "街道", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/IconType/pageInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    PageInfo_LoadMap findPageInfoByDbOfURBAN_RISK(@PathVariable("tableNameUrl") String tableNameUrl,String pageNum, String pageSize,String street) throws IOException;

}
