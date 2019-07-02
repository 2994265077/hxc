package com.cetccity.operationcenter.webframework.hiddendanger.api.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
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

@Api(tags = "安全隐患一张图--落图")
@RequestMapping("/hiddendanger")
public interface ALL_LoadMapApi {

    @ApiOperation(value = "根据表名DB落图", notes =
            "安监-纳管企业--QAJJ_PUCENTP_V  \n" +
            "消防-隐患三色--T_XIAOFANG_BUWEI  \n" +
            "消防-隐患三色（红色）--T_XIAOFANG_BUWEI@hongse  \n" +
            "消防-隐患三色（橙色）--T_XIAOFANG_BUWEI@cengse  \n" +
            "消防-隐患三色（黄色）--T_XIAOFANG_BUWEI@huangse  \n" +
            "消防-消防栓--TB_XIAOHUOSHUAN_002  \n" +
            "消防-重点场所--ZHONGDIANCHANGSUO  \n" +
            "消防-避难场所--EMERGENCY_SHELTER  \n" +
            "养老中心--FUTIAN_PENSION_CENTER  \n" +
            "应急资源仓库--EMERGENCY_WAREHOUSE  \n" +
            "应急_救援队伍--EMERGENCY_RESCUE_TEAM  \n" +
            "应急_危险源和防范区--EMERGENCY_HARZARD  \n" +
            "教育场所--FUTIAN_SCHOOL  \n" +
            "地质类-道路病害--TB_ROAD_DISEASE  \n"+
            "地质类-建筑边坡--DANGER_SLOPE@0  \n"+
            "地质类-地灾隐患点--DANGER_SLOPE@1  \n"+
            "生态环境-空气--QHSJ_T_AIR_BASICINFO  \n"+
            "生态环境-地表水--QHSJ_SURF_WATER_BASICINFO  \n"+
            "生态环境-污水源--QHSJ_ENTERPRISE_BASICINFO  \n"+
            "公安视频--VIDEO_POLICE  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--TB_ROAD_DISEASE", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "主键--id", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LoadMap> findObjectByDB(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id) throws IOException;


    @ApiOperation(value = "搜索特定point点周边的实体--落图", notes = "搜索特定point点周边的实体")
    @RequestMapping(value = "/search/near/layer/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--(BJ0301,BJ2124,BJ0121,VIDEO_POLICE)", name = "tableName", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "经度--114.09462820727758", name = "jd", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "纬度--22.54998918105337", name = "wd", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "距离--5", name = "range", dataType = "string", paramType = "query", required = true)
    })
    @ResponseBody
    List<LoadMap> findNearResourceObj(String tableName, String jd,String wd, String range) throws IOException;


    @ApiOperation(value = "根据表名DB落图", notes = "城区风险评估--三小场所(1)URBAN_RISK@sanxiaoplace  \n"+
            "城区风险评估--工业企业(2)URBAN_RISK@2  \n"+
            "城区风险评估--人员密集场所(3)URBAN_RISK@3  \n"+
            "城区风险评估--公共基础设施(4)URBAN_RISK@4  \n"+
            "城区风险评估--其他风险单位(5)URBAN_RISK@5  \n"+
            "安监-三小场所(小作坊PLACE_TYPE=60022)--BLK_SANXIAO_PLACE@60022  \n" +
            "安监-三小场所(小档口PLACE_TYPE=60021)--BLK_SANXIAO_PLACE@60021  \n" +
            "安监-三小场所(小娱乐场所PLACE_TYPE=60023)--BLK_SANXIAO_PLACE@60023  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--URBAN_RISK", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "主键--id", name = "id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "报警--alarm=1", name = "alarm", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/IconType/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<IconTypeLoadMap> findIconTypeByDB(@PathVariable("tableNameUrl") String tableNameUrl, String street,String id,String alarm,String startTime,String endTime) throws IOException;

}
