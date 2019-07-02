package com.cetccity.operationcenter.webframework.hiddendanger.api.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TipDetailOfList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "安全隐患一张图--纳管企业详情")
@RequestMapping("/hiddendanger")
public interface QAJJ_PUCENTP_VDetailApi {

    @ApiOperation(value = "纳管企业详情--基本信息and责任主体", notes = "纳管企业详情--基本信息and责任主体  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAATRiAAEAACJqkAAC", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/QAJJ_PUCENTP_V/detail/information",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    TipDetailOfList getQAJJ_PUCENTP_VDetailInformation(String id);

    @ApiOperation(value = "纳管企业详情--监管机构", notes = "纳管企业详情--监管机构  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAATRiAAEAACJqkAAC", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum=1", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "pageSize=3", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/QAJJ_PUCENTP_V/detail/information/regulatory/agency",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel getQAJJ_GRID_VDetailInformationOfListRegulatoryAgency(String id, Integer pageNum, Integer pageSize);

    @ApiOperation(value = "纳管企业详情--风险评估--<暂时不开发>", notes = "纳管企业详情--风险评估  \n" +
            "风险评估--{\"评估人\",\"风险类型\",\"风险等级\",\"风险分值\",\"审核人\",\"审核时间\",\"状态\"}--AUDITOR,PLACE_TYPE,RISK_ASSESS_LV,RISK_ASSESS_CSCORE,RISK_ASSESS_MAN_NAME,RISK_ASSESS_AUDIT_INTRO,RISK_ASSESS_AUDIT_INTRO  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAATRiAAEAACJqkAAC", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum=1", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "pageSize=3", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/QAJJ_PUCENTP_V/detail/information/risk/assessment",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAssessment(String id,Integer pageNum,Integer pageSize);

    @ApiOperation(value = "纳管企业详情--风险隐患", notes = "纳管企业详情--风险隐患  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAATRiAAEAACJqkAAC", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum=1", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "pageSize=3", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/QAJJ_PUCENTP_V/detail/information/risk/danger",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskDanger(String id,Integer pageNum,Integer pageSize);

    @ApiOperation(value = "纳管企业详情--风险预警", notes = "纳管企业详情--风险预警  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAATRiAAEAACJqkAAC", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum=1", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1") ,
            @ApiImplicitParam(value = "pageSize=3", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/QAJJ_PUCENTP_V/detail/information/risk/aAlarm",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAlarm(String id,Integer pageNum,Integer pageSize);


    @ApiOperation(value = "纳管企业详情--风险事故", notes = "纳管企业详情--风险事故")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAATRiAAEAACP6kAAA", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum=1", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "pageSize=3", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/QAJJ_PUCENTP_V/detail/information/risk/accident",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel getQAJJ_PUCENTP_VDetailInformationOfListRiskAccident(String id,Integer pageNum,Integer pageSize);
    
}
