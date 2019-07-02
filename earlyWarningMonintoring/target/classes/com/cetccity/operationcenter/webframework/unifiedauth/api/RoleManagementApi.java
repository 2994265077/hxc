package com.cetccity.operationcenter.webframework.unifiedauth.api;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.RoleUpdateModel;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
 * @Create_Date: 2018/10/31 15:35
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/31 15:35
 * @Update_Description: huangzezhou 补充
 **/
@Api(tags = "角色管理")
@RequestMapping("/unifiedauth/rolemanagement")
public interface RoleManagementApi {
    @ApiOperation(value = "创建角色", notes = "注意：角色需要绑定到组织机构上，组织机构可以为空")
    @RequestMapping(value = "/role/create", method = RequestMethod.GET, produces = "application/json")
    @ApiImplicitParam( name = "permission_ids", value = "权限集合", paramType = "query", required = true, dataType = "Array[String]")
    @ResponseBody
    HttpResponseModel<Boolean> addRole(RoleEntity role, String[] permission_ids);

    @ApiOperation(value = "查询所有角色", notes = "查询所有角色")
    @RequestMapping(value = "/role/queryall", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<List<RoleEntity>> queryAllRoles(HttpServletRequest request);

    @ApiOperation(value = "查询角色详细信息", notes = "查询角色详细信息，包括对应的权限信息，支持分页")
    @ApiImplicitParam(name = "role_id", value = "角色名", paramType = "query", required = true, dataType = "string")
    @RequestMapping(value = "/role/info", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<List<PermissionEntity>> queryRoleInfo(String role_id);

    @ApiOperation(value = "更新角色", notes = "更新角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<Boolean> updateRole(RoleUpdateModel role);

}
