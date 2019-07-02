package com.cetccity.operationcenter.webframework.publichealth.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_OUT_VISITS_DAY_V;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.api
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:10 2019-02-28
 * Updater:     heliangming
 * Update_Date：9:10 2019-02-28
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "公共安全--门诊信息BI")
@RequestMapping("/publicHealth")
public interface BiChartOfOutpatientApi {

    @ApiOperation(value = "公共安全--门诊信息BI--各医院近一年门诊人次", notes = "YJJC_QWJJ_OUT_VISITS_DAY_V--OUTPATIENT_NO")
    @RequestMapping(value = "/outpatient/lately",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable("'publicHealth_outpatient_lately'")
    List<NameValueModel> outpatientLatelyYearn();

    @ApiOperation(value = "公共安全--门诊信息BI--各医院门诊人次--近两年记录总和", notes = "YJJC_QWJJ_OUT_VISITS_DAY_V--OUTPATIENT_NO")
    @RequestMapping(value = "/outpatient/hospital",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable("'outpatientHospital'")
    List<NameValueModel> outpatientHospital();

    @ApiOperation(value = "公共安全--门诊信息BI--近一个月各医院门诊人次", notes = "YJJC_QWJJ_OUT_VISITS_DAY_V--OUTPATIENT_NO")
    @RequestMapping(value = "/outpatient/near/month",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable("'outpatientHospitalNearMonth'")
    List<NameValueModel> outpatientHospitalNearMonth();

    @ApiOperation(value = "公共安全--门诊信息BI--医院各科室门诊人次", notes = "YJJC_QWJJ_OUT_VISITS_DAY_V--OUTPATIENT_NO")
    @RequestMapping(value = "/outpatient/hospital/department",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable("'outpatientHospitalDepartment'")
    NameDataModel outpatientHospitalDepartment();
}
