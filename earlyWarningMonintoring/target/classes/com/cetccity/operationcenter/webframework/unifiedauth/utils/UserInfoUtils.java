package com.cetccity.operationcenter.webframework.unifiedauth.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cetccity.operationcenter.webframework.unifiedauth.dao.UserMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.service.model.UserRolePermissionEntity;

import io.jsonwebtoken.Claims;

/**
 * 
 * @author ZHUTONGYU Description: 根据token读取用户信息 2019年5月8日
 *
 */
@Component
public class UserInfoUtils {
	@Autowired
	private TokenManager<String> tokenManager;
	@Autowired
	private UserMapper userMapper;

	/**
	 * 根据token获取用户信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public UserRolePermissionEntity getUserInfo(HttpServletRequest request) throws Exception {
		Object obj = request.getSession().getAttribute("userInfo");
		if (obj != null) {
			return (UserRolePermissionEntity) obj;
		}
		return analysisToken(request);
	}

	/**
	 * 根据token获取用户Id
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getUserId(HttpServletRequest request) throws Exception {
		// 先从session中取
		Object obj = request.getSession().getAttribute("userInfo");
		if (obj != null) {
			return ((UserRolePermissionEntity) obj).getUser_id();
		}
		// 再解析token
		UserRolePermissionEntity userInfo = analysisToken(request);
		if (userInfo != null)
			return userInfo.getUser_id();
		return null;
	}

	/**
	 * 解析token(先取session，否则取 token)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private UserRolePermissionEntity analysisToken(HttpServletRequest request) throws Exception {
		String token = RequestUtil.getParam(request, "token");
		if (StringUtils.isEmpty(token))
			return null;
		if (!tokenManager.available(token)) {
			throw new RuntimeException("用户已退出系统");
		}
		Claims claims = tokenManager.deToken(token);
		String account = claims.getSubject();
		UserRolePermissionEntity userRolePermission = null;
		if (StringUtils.isNotBlank(account)) {
			UserEntity userInfo = userMapper.selectByAccount(account);
			// 将用户信息存入session，适合单实例使用
			if (userInfo != null) {
				userRolePermission = userMapper.queryUserAllInfo(userInfo.getUser_id());
				request.getSession().setAttribute("userRolePermission", userRolePermission);
			}
		}
		return userRolePermission;
	}

	public String getAccount(HttpServletRequest request) throws Exception {
		String token = RequestUtil.getParam(request, "token");
		if (StringUtils.isEmpty(token) || !tokenManager.available(token))
			return null;
		return tokenManager.deToken(token).getSubject();
	}
}
