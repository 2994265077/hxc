package com.cetccity.operationcenter.webframework.publichealth.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.publichealth.api
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:07 2019-03-06
 * Updater:     heliangming
 * Update_Date：9:07 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "公共安全--住院信息BI")
@RequestMapping("/publicHealth")
public interface BiChartHospitalizationApi {

     @ApiOperation(value = "公共安全--住院信息BI--所有医院近一年住院人次", notes = "YJJC_QWJJ_IN_VISITS_DAY_V--OUTPATIENT_NO")
    @RequestMapping(value = "/hospitalization/lately",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> hospitalizationLatelyYearn();

    @ApiOperation(value = "公共安全--住院信息BI--各医院住院人次--近两年记录总和", notes = "YJJC_QWJJ_IN_VISITS_DAY_V--OUTPATIENT_NO")
    @RequestMapping(value = "/hospitalization/hospital",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> hospitalizationHospital();

    @ApiOperation(value = "公共安全--住院信息BI--近一个月各医院住院人次", notes = "YJJC_QWJJ_IN_VISITS_DAY_V--OUTPATIENT_NO")
    @RequestMapping(value = "/hospitalization/near/month",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> hospitalizationHospitalNearMonth();

    @ApiOperation(value = "公共安全--住院信息BI--医院各科室住院人次", notes = "YJJC_QWJJ_IN_VISITS_DAY_V--OUTPATIENT_NO")
    @RequestMapping(value = "/hospitalization/hospital/department",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel hospitalizationHospitalDepartment();
}
