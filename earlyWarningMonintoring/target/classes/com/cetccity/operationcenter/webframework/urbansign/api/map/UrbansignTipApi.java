package com.cetccity.operationcenter.webframework.urbansign.api.map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Api(tags = "城市体征--弹框")
@RequestMapping("/urbansign")
public interface UrbansignTipApi {

    @ApiOperation(value = "人口基本面--人口密度落图", notes = "人口基本面--人口密度落图-表名(BLK_POPULATION)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--440304005", name = "id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "类型--tag(0-人口密度、1-户籍人口密度、2-流动人口密度)", name = "tag", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/density/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    Map populationDensityTip(String id,String tag);

    @ApiOperation(value = "城市基本面--法人--法人人口热力图", notes = "城市基本面--法人--法人人口热力图  -表名(BLK_LEGAL_PERSON)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--440304005", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/legalPerson/num/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    Map LegalPersonThermalPowerTip(String id);

    @ApiOperation(value = "城市基本面--楼栋--楼栋数量热力图", notes = "城市基本面--楼栋--楼栋数量热力图  -表名(BLK_BUILDING)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--440304005", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/buildBasic/num/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    Map buildNumGradientTip(String id);

    @ApiOperation(value = "城市基本面--房屋--房屋数量热力图", notes = "城市基本面--房屋--房屋数量热力图  -表名(BLK_HOUSE)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--440304005", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/house/num/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    Map houseNumGradientTip(String id);

    @ApiOperation(value = "城市基本面--房屋--房屋数量热力图", notes = "城市基本面--房屋--房屋数量热力图  -表名(所有BJ**表)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id--440304005", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/component/num/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    Map componentNumGradientTip(String id);
}
