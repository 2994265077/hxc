package com.cetccity.operationcenter.webframework.unifiedauth.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cetccity.operationcenter.webframework.web.util.MD5Encoder;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cetccity.operationcenter.webframework.backstage.log.util.LogAnnotation;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.unifiedauth.api.UserManagementApi;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.SimplePageInfoModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserQueryModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserUpdateModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.AuthorizationServiceImpl;
import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.UserManagementServiceImpl;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.UserInfoUtils;

import cn.hutool.core.codec.Base64;
import io.swagger.annotations.ApiOperation;

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
    private UserManagementServiceImpl userManagementService;
    @Autowired
    private UserInfoUtils userInfoUtils;
    @Autowired
    private AuthorizationServiceImpl authorizationService;

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
    
    @LogAnnotation(module="个人修改密码")
    @ApiOperation(value = "个人修改密码", notes = "oldPwd, newPwd, reNewPwd密码使用base64.encode转码")
    @PostMapping("/updatePwd/{oldPwd}/{newPwd}/{reNewPwd}")
    public HttpResponseModel<String> updatePwd(@PathVariable("oldPwd") String oldPwd, @PathVariable("newPwd") String newPwd, @PathVariable("reNewPwd") String reNewPwd, HttpServletRequest req)throws Exception {
    	String account = userInfoUtils.getAccount(req);
    	String msg = null;
    	if(StringUtils.isEmpty(account))
    		msg =  "用户未登录！";
    	else if(StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(reNewPwd))
    		msg =  "旧密码、新密码、新密码确定都不能为空！";
    	else if(!newPwd.equals(reNewPwd))
    		msg =  "两次密码输入不一致！";
    	if(StringUtils.isNotEmpty(msg)){
        	return new HttpResponseModel<String>(AuthCode.OPERATE_FAILED, msg);
    	}
    	UserEntity user = authorizationService.queryUserEnityByAccount(account);
    	if(user == null){
        	return new HttpResponseModel<String>(AuthCode.OPERATE_FAILED, "用户信息不存在！");
    	}
    	if(!MD5Encoder.encode(Base64.decodeStr(oldPwd)).equals(user.getPassword())){
    		return new HttpResponseModel<String>(AuthCode.OPERATE_FAILED, "旧密码输入不正确！");
    	}
    	return userManagementService.updatePwd(new UserUpdateModel(user.getUser_id(), account,  newPwd));
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
