package com.cetccity.operationcenter.webframework.urbansign.api.map;

import com.cetccity.operationcenter.webframework.urbansign.api.model.HeatMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "城市体征--落图")
@RequestMapping("/urbansign")
public interface UrbansignLoadMapApi {

    @ApiOperation(value = "人口基本面--人口密度落图", notes = "人口基本面--人口密度落图-表名(BLK_POPULATION)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "类型--tag(0-人口密度、1-户籍人口密度、2-流动人口密度)", name = "tag", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/density/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HeatMap populationDensity(String street, String tag);

    @ApiOperation(value = "城市基本面--法人--法人人口热力图", notes = "城市基本面--法人--法人人口热力图  -表名(BLK_LEGAL_PERSON)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/legalPerson/num/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HeatMap LegalPersonThermalPower(String street);

    @ApiOperation(value = "楼栋基本面--楼栋数量颜色落图", notes = "楼栋基本面--楼栋数量颜色落图-表名(BLK_BUILDING)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/buildBasic/num/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HeatMap buildNumGradientLoadMap(String street);

    @ApiOperation(value = "楼栋基本面--房屋数量颜色落图", notes = "楼栋基本面--房屋数量颜色落图-表名(BLK_HOUSE)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/house/num/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HeatMap houseNumGradientLoadMap(String street);

    @ApiOperation(value = "城市部件基本面--部件数量颜色落图", notes = "城市部件基本面--部件数量颜色落图-表名(所有BJ**表count)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/component/num/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HeatMap componentNumGradientLoadMap(String street);

}
