package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.P2P_BUSINESS_GLOBAL;
import com.cetccity.operationcenter.webframework.web.model.citySign.UrbanBasicLegalPersonLeftOne;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 城市基本面
 */
@Api(value = "城市体征--法人基本面", tags = "城市体征--法人基本面")
@RequestMapping("/urbansign")
public interface UrbanBasicLegalPersonApi {

    @ApiOperation(value = "城市基本面--法人--企业总数", notes = "城市基本面--法人--企业总数-表名(BLK_LEGAL_PERSON)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(440304001002可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/legalPerson/leftOne",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<UrbanBasicLegalPersonLeftOne> leftOne(String street, String community) throws IOException ;

    @ApiOperation(value = "城市基本面--法人--各街道企业分布", notes = "城市基本面--法人--各街道企业分布-表名(BLK_LEGAL_PERSON)")
    @RequestMapping(value = "/legalPerson/leftTwo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> leftTwo() throws IOException ;

    @ApiOperation(value = "城市基本面--法人--企业各行分类数量", notes = "城市基本面--法人--企业各行业分类数量-表名(BLK_LEGAL_PERSON--HYFL)")
    @RequestMapping(value = "/legalPerson/rightOne",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<Map.Entry<String, Integer>> rightOne();

    @ApiOperation(value = "城市基本面--法人--企业类型数量", notes = "城市基本面--法人--企业类型数量-表名(BLK_LEGAL_PERSON--JGXX)")
    @RequestMapping(value = "/legalPerson/rightTwo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    void rightTwo();

    /*1、各街道平台占比（柱状图）*/
    @ApiOperation(value = "城市基本面--法人--各街道P2P平台占比", notes = "城市基本面--法人--各街道P2P平台占比  -表名(P2P_BUSINESS_ADDR--streetcode)")
    @RequestMapping(value = "/legalPerson/rightThree",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> rightThree();

    /*2、全区平台风险类型占比（饼图）*/
    @ApiOperation(value = "城市基本面--法人--全区平台风险类型占比", notes = "城市基本面--法人--全区平台风险类型占比  -表名(P2P_BUSINESS_GLOBAL--RISK_TYPE)五种类型--出险、未出险、停业、立案、非P2P平台")
    @RequestMapping(value = "/legalPerson/rightFour",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> rightFour();

    /*3、各平台冒烟指数Top10*/
    @ApiOperation(value = "城市基本面--法人--各平台冒烟指数Top10", notes = "城市基本面--法人--各平台冒烟指数Top10  -表名(P2P_BUSINESS_GLOBAL--SMOKE_INDEX)若RISK_TYPE=立案，则SMOKE_INDEX=100")
    @RequestMapping(value = "/legalPerson/rightFive",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<P2P_BUSINESS_GLOBAL> rightFive();

    /*4、待偿还金额Top10*/
    @ApiOperation(value = "城市基本面--法人--待偿还金额Top10", notes = "城市基本面--法人--待偿还金额Top10  -表名(P2P_BUSINESS_GLOBAL--TOTAL_COMPENSATION)单位-亿元")
    @RequestMapping(value = "/legalPerson/rightSix",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<P2P_BUSINESS_GLOBAL> rightSix();

}
