package com.cetccity.operationcenter.webframework.unifiedauth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.unifiedauth.api.AuthorizationApi;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import com.cetccity.operationcenter.webframework.unifiedauth.service.Impl.AuthorizationServiceImpl;
import com.cetccity.operationcenter.webframework.unifiedauth.service.model.UserRolePermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.UserInfoUtils;

import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class AuthorizationController implements AuthorizationApi {

	@Autowired
	AuthorizationServiceImpl authorizationService;
	@Autowired
	private UserInfoUtils userInfoUtils;

	@Override
	public HttpResponseModel<Boolean> validatePermission(String token, String[] permissions) {
		return authorizationService.validatePermission(token, permissions);
	}

	@Override
	public HttpResponseModel<Boolean> validatePermissionByUrl(String token, String uri, HttpServletRequest request) {
		if (StringUtils.isEmpty(uri))
			return null;
		try {
			UserRolePermissionEntity rolePermission = userInfoUtils.getUserInfo(request);
			if (rolePermission != null && !Collections.isEmpty(rolePermission.getAllPermissions())
					&& rolePermission.getAllPermissions().contains(uri)) {
				return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE, true);
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		return new HttpResponseModel<Boolean>(AuthCode.SYS_SUCCESS_CODE, false);
	}

	public HttpResponseModel<Map<String, Boolean>> batchValidatePermissionByUrl(String token, String urls[],
			HttpServletRequest request) {
		if (urls == null || urls.length == 0)
			return null;
		Map<String, Boolean> returnMap = new HashMap<String, Boolean>();
		try {
			UserRolePermissionEntity rolePermission = userInfoUtils.getUserInfo(request);
			Set<String> PermissionSet = rolePermission.getAllPermissions();
			for(String u: urls){
				returnMap.put(u, PermissionSet.contains(u));
			}
			return new HttpResponseModel<Map<String, Boolean>>( returnMap);
			
		} catch (Exception e) {
			log.error(e.toString());
		}
		return new HttpResponseModel<Map<String, Boolean>>( returnMap);
	}
}
