package com.cetccity.operationcenter.webframework.unifiedauth.controller;

import com.cetccity.operationcenter.webframework.backstage.log.util.LogAnnotation;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.UserManagementApi;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.SimplePageInfoModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserQueryModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserUpdateModel;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.UserManagementServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.controller
 * @Project: unified-auth
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/10/31 15:00
 * @Updater: huangzezhou
 * @Update_Date: 2018/10/31 15:00
 * @Update_Description: huangzezhou 补充
 **/
@RestController
public class UserManagementController implements UserManagementApi {

    private static final Logger logger = LoggerFactory.getLogger(UserManagementServiceImpl.class);

    @Autowired
    UserManagementServiceImpl userManagementService;

    @LogAnnotation(module="创建用户")
    @Override
    public HttpResponseModel<Boolean> createUser(@RequestBody UserEntity userEntity, HttpServletRequest request) {
        try {
            return userManagementService.createUser(userEntity, userEntity.getRole_ids(), request);
        }catch (Exception e){
            logger.error("create user error!\n",e);
            return new HttpResponseModel<Boolean>();
        }
    }

    @LogAnnotation(module="修改用户")
    @Override
    public HttpResponseModel<Boolean> updateUser(@RequestBody UserUpdateModel userUpdateModel)throws Exception {
        return userManagementService.updateUser(userUpdateModel, userUpdateModel.getRole_ids());
    }

    @LogAnnotation(module="查询用户")
    @Override
    public HttpResponseModel<SimplePageInfoModel> queryUser(UserQueryModel userQueryModel, HttpServletRequest request) {
        return userManagementService.queryUser(userQueryModel);
    }

    @ApiOperation(value = "当前用户信息", notes = "按条件查询用户")
    @GetMapping(value = "/user/current", produces = "application/json")
    @ResponseBody
    @LogAnnotation(module="当前用户信息")
    public HttpResponseModel<UserEntity> currentUser() {
        return HttpResponseModel.defaultSuccess(userManagementService.currentUser());
    }


}
