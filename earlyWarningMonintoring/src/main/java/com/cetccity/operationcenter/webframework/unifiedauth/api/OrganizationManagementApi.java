package com.cetccity.operationcenter.webframework.unifiedauth.api;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.OrganizationModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/31 15:40
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/31 15:40
 * @Update_Description: huangzezhou 补充
 **/
@Api(tags = "组织管理")
@RequestMapping("/unifiedauth/rolemanagement")
public interface OrganizationManagementApi {

    @ApiOperation(value = "查询所有组织架构", notes = "查询所有组织架构")
    @RequestMapping(value = "/oraganization/queryall", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<List<OrganizationModel>> queryAllOrganizations(HttpServletRequest request);

}
