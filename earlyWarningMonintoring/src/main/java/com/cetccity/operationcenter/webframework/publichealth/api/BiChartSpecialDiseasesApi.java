package com.cetccity.operationcenter.webframework.publichealth.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.publichealth.api.model.EMPLOYEE_NUM;
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
 * Create_Date: 16:44 2019-03-06
 * Updater:     heliangming
 * Update_Date：16:44 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "公共安全--特殊病种信息BI")
@RequestMapping("/publicHealth")
public interface BiChartSpecialDiseasesApi {

    @ApiOperation(value = "公共安全--卫生人员信息BI--12个月特殊病种数量走势", notes = "YJJC_QWJJ_SDM_INFO_V--count")
    @RequestMapping(value = "/special/diseases/trend",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameDataModel> specialDiseasesTrend();

    @ApiOperation(value = "公共安全--卫生人员信息BI--各医院特殊病种统计", notes = "YJJC_QWJJ_SDM_INFO_V--count")
    @RequestMapping(value = "/special/diseases/hospital",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameDataModel> specialDiseasesHospital();

    @ApiOperation(value = "公共安全--卫生人员信息BI--所有特殊病种数量总和走势", notes = "YJJC_QWJJ_SDM_INFO_V--count")
    @RequestMapping(value = "/special/diseases/num",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> specialDiseasesNum();

    @ApiOperation(value = "公共安全--卫生人员信息BI--所有初诊、确证比例", notes = "YJJC_QWJJ_SDM_INFO_V--count")
    @RequestMapping(value = "/special/diseases/proportion",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> specialDiseasesProportion();
}
