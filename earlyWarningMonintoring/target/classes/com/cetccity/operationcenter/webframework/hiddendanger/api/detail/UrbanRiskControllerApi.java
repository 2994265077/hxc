package com.cetccity.operationcenter.webframework.hiddendanger.api.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TipDetailOfList;
import com.cetccity.operationcenter.webframework.web.model.incident.NameColorDataModel;
import com.cetccity.operationcenter.webframework.web.model.incident.NameColorXDataYDataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;

@Api(tags = "安全隐患一张图--城区风险详情")
@RequestMapping("/hiddendanger")
public interface UrbanRiskControllerApi {

    @ApiOperation(value = "城区风险详情--基本信息and责任主体", notes = "城区风险详情--基本信息and责任主体  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAAScpAAEAAAgC8AAH", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/URBAN_RISK/detail/information",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    TipDetailOfList getURBAN_RISKDetailInformation(String id);

    @ApiOperation(value = "城区风险详情--监管机构", notes = "城区风险详情--监管机构  \n" +
            "监管机构--{\"机构名称\",\"联系人\",\"联系电话\",\"地址\"}--ORG_NAME,ORG_CONTACTS,ORG_TEL,RISK_ADDRESS  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAAScpAAEAAAgC8AAH", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum=1", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "pageSize=3", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/URBAN_RISK/detail/information/regulatory/agency",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel getURBAN_RISKDetailInformationOfListRegulatoryAgency(String id, Integer pageNum, Integer pageSize);

    @ApiOperation(value = "城区风险详情--风险评估", notes = "城区风险详情--风险评估  \n" +
            "风险评估--{\"评估人\",\"风险类型\",\"风险等级\",\"风险分值\",\"审核人\",\"审核时间\",\"状态\"}--AUDITOR,PLACE_TYPE,RISK_ASSESS_LV,RISK_ASSESS_CSCORE,RISK_ASSESS_MAN_NAME,RISK_ASSESS_AUDIT_INTRO,RISK_ASSESS_AUDIT_INTRO  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAAScpAAEAAAgC8AAH", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum=1", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "pageSize=3", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/URBAN_RISK/detail/information/risk/assessment",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAssessment(String id,Integer pageNum,Integer pageSize);

    @ApiOperation(value = "城区风险详情--风险隐患", notes = "城区风险详情--风险隐患  \n" +
            "风险隐患--{\"隐患发现人\",\"隐患描述\",\"措施描述\",\"责任主体\",\"提交时间\",\"状态\"}--RISK_ASSESS_MAN_NAME,RISK_ASSESS_TYPE_NAMES,RISK_ASSESS_TYPE_NAME,LIABILITY_SUBJECT,RISK_ASSESS_AUDIT_DATE,RISK_ASSESS_AUDIT_DATE  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAAScpAAEAAAgC8AAH", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum=1", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "pageSize=3", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/URBAN_RISK/detail/information/risk/danger",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskDanger(String id,Integer pageNum,Integer pageSize);

    @ApiOperation(value = "城区风险详情--风险预警", notes = "城区风险详情--风险预警  \n" +
            "风险预警--{\"预警人\",\"预警原因\",\"预警时间\",\"开始时间\",\"结束时间\"}--RISK_ASSESS_MAN_NAME,RISK_ASSESS_TYPE_NAMES,RISK_ASSESS_AUDIT_DATE,RISK_ASSESS_DATE,RISK_ASSESS_DATE  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAAScpAAEAAAgC8AAH", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum=1", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "pageSize=3", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/URBAN_RISK/detail/information/risk/aAlarm",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAlarm(String id,Integer pageNum,Integer pageSize);


    @ApiOperation(value = "城区风险详情--风险事故", notes = "城区风险详情--风险事故  \n" +
            "风险事故--{\"发布人\",\"直接原因\",\"事故描述\",\"发生时间\"}--RISK_ASSESS_MAN_NAME,RISK_ASSESS_TYPE_NAMES,RISK_ASSESS_TYPE_NAMES,RISK_ASSESS_DATE"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id=AAAScpAAEAAAgC8AAH", name = "id", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum=1", name = "pageNum", dataType = "int", paramType = "query", required = true , example = "1"),
            @ApiImplicitParam(value = "pageSize=3", name = "pageSize", dataType = "int", paramType = "query", required = true , example = "10")
    })
    @RequestMapping(value = "/URBAN_RISK/detail/information/risk/accident",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAccident(String id,Integer pageNum,Integer pageSize);


    @ApiOperation(value = "城区风险图表", notes = "城区风险图表--one  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "street=南园街道", name = "street", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/URBAN_RISK/chart/right/one",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameColorDataModel getChartOfRightOne(String street)throws IOException ;

    @ApiOperation(value = "城区风险图表", notes = "城区风险图表--two  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "street=南园街道", name = "street", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/URBAN_RISK/chart/right/two",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameColorDataModel getChartOfRightTwo(String street)throws IOException;

    @ApiOperation(value = "城区风险图表", notes = "城区风险图表--three  \n")
    @RequestMapping(value = "/URBAN_RISK/chart/right/three",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameColorXDataYDataModel getChartOfRightThree()throws IOException;

    @ApiOperation(value = "城区风险图表", notes = "城区风险图表--four  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "street=南园街道", name = "street", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/URBAN_RISK/chart/right/four",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameColorDataModel getChartOfRightFour(String street)throws IOException;
}
