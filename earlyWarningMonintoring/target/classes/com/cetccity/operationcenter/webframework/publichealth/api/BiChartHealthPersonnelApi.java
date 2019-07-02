package com.cetccity.operationcenter.webframework.publichealth.api;

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
 * Create_Date: 9:42 2019-03-06
 * Updater:     heliangming
 * Update_Date：9:42 2019-03-06
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "公共安全--卫生人员信息BI")
@RequestMapping("/publicHealth")
public interface BiChartHealthPersonnelApi {

    @ApiOperation(value = "公共安全--卫生人员信息BI--各医院卫生人员人次", notes = "YJJC_QWJJ_EMPLOYEE_V--count")
    @RequestMapping(value = "/healthPersonnel/num",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> healthPersonnelNum();

    @ApiOperation(value = "公共安全--卫生人员信息BI--各医院卫生人员编制情况", notes = "YJJC_QWJJ_EMPLOYEE_V--count")
    @RequestMapping(value = "/healthPersonnel/Authorized/num",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> healthPersonnelAuthorizedNum();

    @ApiOperation(value = "公共安全--卫生人员信息BI--各医院医师执业类别情况", notes = "YJJC_QWJJ_EMPLOYEE_V--count")
    @RequestMapping(value = "/healthPersonnel/DOCTOR_TYPE/num",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> healthPersonnelDOCTOR_TYPENum();

    @ApiOperation(value = "公共安全--卫生人员信息BI--各医院专业技术职务（聘）情况", notes = "YJJC_QWJJ_EMPLOYEE_V--count")
    @RequestMapping(value = "/healthPersonnel/JOB_LEVEL_CODE/num",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> healthPersonnelJOB_LEVEL_CODENum();

    @ApiOperation(value = "公共安全--卫生人员信息BI--各医院从事的专业类别情况", notes = "YJJC_QWJJ_EMPLOYEE_V--count")
    @RequestMapping(value = "/healthPersonnel/CSZYLBDM/num",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> healthPersonnelCSZYLBDMNum();
}
