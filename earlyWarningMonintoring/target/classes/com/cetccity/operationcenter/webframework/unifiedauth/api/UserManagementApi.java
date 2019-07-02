package com.cetccity.operationcenter.webframework.unifiedauth.api;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.SimplePageInfoModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserQueryModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserUpdateModel;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.api
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/31 14:58
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/31 14:58
 * @Update_Description: huangzezhou 补充
 **/
@Api(tags = "用户管理逻辑")
@RequestMapping("/unifiedauth/usermanagement")
public interface UserManagementApi {

    @ApiOperation(value = "管理员创建用户/修改用户信息/禁用用户", notes = "注意：创建用户接口，也可以用来覆盖修改用户信息，也可以用于禁用/启用用户")
    @ApiImplicitParam(name = "role_ids", value = "角色编号: 1,2", required = true, paramType = "query", dataType = "Array[String]")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    HttpResponseModel<Boolean> createUser(@RequestBody UserEntity userEntity, HttpServletRequest request);

    @ApiOperation(value = "管理员创建用户/修改用户信息/禁用用户", notes = "注意：创建用户接口，也可以用来覆盖修改用户信息，也可以用于禁用/启用用户")
    @ApiImplicitParam(name = "role_ids", value = "角色编号: 1,2", required = true, paramType = "query", dataType = "Array[String]")
    @RequestMapping(value = "/user/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    HttpResponseModel<Boolean> updateUser(@RequestBody UserUpdateModel userUpdateModel)throws Exception;

    @ApiOperation(value = "按条件查询用户", notes = "按条件查询用户")
    @RequestMapping(value = "/user/query", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    HttpResponseModel<SimplePageInfoModel> queryUser(UserQueryModel userQueryModel, HttpServletRequest request);
}
