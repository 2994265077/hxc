package com.cetccity.operationcenter.webframework.publichealth.api;

import com.cetccity.operationcenter.webframework.publichealth.dao.entity.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.api
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:31 2019-02-27
 * Updater:     heliangming
 * Update_Date：11:31 2019-02-27
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "公共安全--医院详情")
@RequestMapping("/publicHealth")
public interface HospitalDetailApi {

    @ApiOperation(value = "公共安全--医院详情--门诊信息", notes = "公共安全--医院详情--门诊信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ORG_CODE--455744030", name = "ORG_CODE", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum--1", name = "pageNum", dataType = "int", paramType = "query", required = true , example="1"),
            @ApiImplicitParam(value = "pageSize--10", name = "pageSize", dataType = "int", paramType = "query", required = true , example="10")
    })
    @RequestMapping(value = "/hospital/outpatient/information",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    PageInfo<YJJC_QWJJ_OUT_VISITS_DAY_V> hospitalOutpatientInformation(String ORG_CODE, Integer pageNum, Integer pageSize);

    @ApiOperation(value = "公共安全--医院详情--住院信息", notes = "公共安全--医院详情--住院信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ORG_CODE--455744030", name = "ORG_CODE", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum--1", name = "pageNum", dataType = "int", paramType = "query", required = true, example="1"),
            @ApiImplicitParam(value = "pageSize--10", name = "pageSize", dataType = "int", paramType = "query", required = true, example="10")
    })
    @RequestMapping(value = "/hospital/hospitalization/information",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    PageInfo<YJJC_QWJJ_IN_VISITS_DAY_V> hospitalHospitalizationInformation(String ORG_CODE, Integer pageNum, Integer pageSize);

    @ApiOperation(value = "公共安全--医院详情--卫生人员", notes = "公共安全--医院详情--卫生人员")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ORG_CODE--455744030", name = "ORG_CODE", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum--1", name = "pageNum", dataType = "int", paramType = "query", required = true , example="1"),
            @ApiImplicitParam(value = "pageSize--10", name = "pageSize", dataType = "int", paramType = "query", required = true , example="10")
    })
    @RequestMapping(value = "/hospital/health/personnel",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    PageInfo<YJJC_QWJJ_EMPLOYEE_V> hospitalHealthPersonnel(String ORG_CODE, Integer pageNum, Integer pageSize);

    @ApiOperation(value = "公共安全--医院详情--科室信息", notes = "公共安全--医院详情--科室信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ORG_CODE--455744030", name = "ORG_CODE", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum--1", name = "pageNum", dataType = "int", paramType = "query", required = true, example="1"),
            @ApiImplicitParam(value = "pageSize--10", name = "pageSize", dataType = "int", paramType = "query", required = true, example="10")
    })
    @RequestMapping(value = "/hospital/department/information",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    PageInfo<YJJC_QWJJ_ORG_V> hospitalDepartmentInformation(String ORG_CODE, Integer pageNum, Integer pageSize);

    @ApiOperation(value = "公共安全--医院详情--特殊病种", notes = "公共安全--医院详情--特殊病种")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ORG_CODE--455744030", name = "ORG_CODE", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "pageNum--1", name = "pageNum", dataType = "int", paramType = "query", required = true, example="1"),
            @ApiImplicitParam(value = "pageSize--10", name = "pageSize", dataType = "int", paramType = "query", required = true, example="10")
    })
    @RequestMapping(value = "/hospital/special/diseases",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    PageInfo<YJJC_QWJJ_SDM_INFO_V> hospitalSpecialDiseases(String ORG_CODE, Integer pageNum, Integer pageSize);

}
