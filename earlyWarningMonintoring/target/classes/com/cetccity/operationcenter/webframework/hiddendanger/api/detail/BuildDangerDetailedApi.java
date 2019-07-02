package com.cetccity.operationcenter.webframework.hiddendanger.api.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.web.model.build.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.LinkedHashMap;
import java.util.List;

@Api(tags = "安全隐患一张图--块数据建筑物--详情")
@RequestMapping("/hiddendanger/{tableName}")
public interface BuildDangerDetailedApi {

    @ApiOperation(value = "建筑基本信息", notes = "根据建筑id查询建筑基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名T_BUILD_INFO_V", name = "tableName", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "建筑id--4403040030110900002", name = "buildid", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/build/basic",method = RequestMethod.GET)
    @ResponseBody
    NameDataModel buildBasic(@PathVariable("tableName") String tableName, String buildid);

    @ApiOperation(value = "建筑评分指标", notes = "根据建筑id查询建筑评分指标信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名T_BUILD_INFO_V", name = "tableName", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "建筑id--4403040020010100091", name = "buildid", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/build/risk",method = RequestMethod.GET)
    @ResponseBody
    NameDataModel buildRisk(@PathVariable("tableName") String tableName, String buildid);

    @ApiOperation(value = "中京消安数据-建筑隐患", notes = "根据建筑id查询建筑隐患信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名T_BUILD_CHECKDAY_DANGER", name = "tableName", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "建筑id--4403040020080200012", name = "buildid", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "当前页", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "页行数", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/build/hidden_danger",method = RequestMethod.GET)
    @ResponseBody
    MyPageInfoModel buildHiddenDanger(@PathVariable("tableName") String tableName, String buildid, Integer pageNum, Integer pageSize);

    @ApiOperation(value = "块数据-企业信息", notes = "根据建筑id查询企业信息信息--表名BLK_LEGAL_PERSON")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名BLK_LEGAL_PERSON", name = "tableName", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "建筑id--4403040020080200012", name = "buildid", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "当前页", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "页行数", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/build/enterprise",method = RequestMethod.GET)
    @ResponseBody
    EnterpriseModel buildEnterprise(@PathVariable("tableName") String tableName, String buildid, Integer pageNum, Integer pageSize);

    @ApiOperation(value = "块数据-建筑房间信息", notes = "根据建筑id查询键建筑房间信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名BLK_HOUSE", name = "tableName", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "建筑id--4403040020010300017", name = "buildid", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/blk/house",method = RequestMethod.GET)
    @ResponseBody
    LayerNumData blkHouseDetailed(@PathVariable("tableName") String tableName, String buildid);

    /*高风险建筑列表  tableName= t_build_info_v*/
    @ApiOperation(value = "块数据-高风险建筑列表", notes = "块数据-高风险建筑列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名T_BUILD_INFO_V", name = "tableName", dataType = "string", paramType = "path", required = true)
    })
    @RequestMapping(value = "/build/danger/rank",method = RequestMethod.GET)
    @ResponseBody
    List<BuildOfDangerModel> buildDangerDetailed(@PathVariable("tableName") String tableName);

    /*高中危建筑面落图  tableName= t_build_info_v*/
    @ApiOperation(value = "块数据-高中危建筑面落图", notes = "高中危建筑面落图")
    @RequestMapping(value = "/build/level/load_map",method = RequestMethod.GET)
    @ResponseBody
    List<LoadMapBuildGrade> buildDangerLoadMap();

    @ApiOperation(value = "建筑责任信息", notes = "根据建筑id查询建筑责任信息[表名--QXFJ_BUILD_ZTZR_V]")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "建筑id--4403040020010100091", name = "buildid", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/build/responsibility",method = RequestMethod.GET)
    @ResponseBody
    LinkedHashMap buildResponsibility(String buildid);

    @ApiOperation(value = "建筑平面图纸", notes = "根据建筑id查询建筑平面图纸信息[表名--QXFJ_BUILD_IMAGE_V]")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "建筑id--4403040020010100091", name = "buildid", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/build/graphicDrawings",method = RequestMethod.GET)
    @ResponseBody
    NameDataModel buildGraphicDrawings(String buildid);
}
