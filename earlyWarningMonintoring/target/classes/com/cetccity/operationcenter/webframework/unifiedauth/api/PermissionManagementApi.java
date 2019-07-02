package com.cetccity.operationcenter.webframework.unifiedauth.api;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api
 * @Project: 31project-Apr4
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/9 11:41
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/9 11:41
 * @Update_Description: huangzezhou 补充
 **/
@Api(tags = "权限管理服务")
@RequestMapping("/unifiedauth/permissionmanagement")
public interface PermissionManagementApi {

    @ApiOperation(value = "查询所有权限", notes = "查询所有权限")
    @RequestMapping(value = "/permission/queryall", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<List<PermissionEntity>> queryAllPermissions();

    @ApiOperation(value = "查询所有权限", notes = "按树形结构返回所有权限")
    @RequestMapping(value = "/permission/tree", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<List<PermissionNode>> queryAllPermissionsTree();
}
