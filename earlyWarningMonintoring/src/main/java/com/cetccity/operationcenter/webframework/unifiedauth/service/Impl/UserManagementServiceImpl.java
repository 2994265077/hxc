package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl;

import com.cetccity.operationcenter.webframework.common.support.UserRolePermissionSupport;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.SimplePageInfoModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserQueryModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserQueryResultModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.UserUpdateModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.UserMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.UserRolesMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.RequestUtil;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.UuidUtil;
import com.cetccity.operationcenter.webframework.web.util.Constant;
import com.cetccity.operationcenter.webframework.web.util.MD5Encoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hutool.core.codec.Base64;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.service.Impl
 * @Project: unified-auth
 * @Description: //用户管理服务
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/2 9:38
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/2 9:38
 * @Update_Description: huangzezhou 补充
 **/
@Service
public class UserManagementServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(UserManagementServiceImpl.class);

    @Autowired
    private UserRolePermissionSupport userRolePermissionSupport;

    @Autowired
    AuthCode authCode;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRolesMapper userRolesMapper;

    @Autowired
    JwtServiceImpl jwtService;

    public HttpResponseModel<Boolean> createUser(UserEntity userEntity, String[] role_ids, HttpServletRequest request) {
        try {
        	//前台传过来的密码为base64转码后的，所以需要解码
        	String realPwd = Base64.decodeStr(userEntity.getPassword());
        	//校验密码格式是否正确
            if(StringUtils.isNotBlank(checkPwd(realPwd))){
                return new HttpResponseModel<Boolean>(authCode.PWD_ERROR);
            }
            //md5加密码存储
            userEntity.setPassword(MD5Encoder.encode(realPwd));
        	//创建唯一编号
            userEntity.setUser_id(UuidUtil.uuid());
            //获取创建用户编号
            String token = RequestUtil.getParam(request, "token");
            String account = jwtService.account(token);
            UserEntity entity = userMapper.selectByAccount(account);
            String create_user_id = entity.getUser_id();
            userEntity.setCreate_user_account(create_user_id);
            return ((UserManagementServiceImpl)AopContext.currentProxy()).createUser_Transactional(userEntity, role_ids);
        }catch (Exception e){
            ObjectMapper mapper = new ObjectMapper();
            try {
                if(e instanceof DuplicateKeyException){
                    //违反主键约束
                    logger.error("create user error: " + mapper.writeValueAsString(userEntity) + mapper.writeValueAsString(role_ids), e);
                    return new HttpResponseModel<Boolean>(authCode.ACCOUNT_EXIST_ERROR);
                }else if (e instanceof DataIntegrityViolationException){
                    //违反完整性约束
                    logger.error("create user error: " + mapper.writeValueAsString(userEntity) + mapper.writeValueAsString(role_ids), e);
                    return new HttpResponseModel<Boolean>(authCode.ROLE_NO_EXIST_ERROR);
                }else {
                        logger.error("create user error: " + mapper.writeValueAsString(userEntity) + mapper.writeValueAsString(role_ids), e);
                        return new HttpResponseModel<Boolean>(authCode.CREATE_USER_ERROR_CODE);
                }
            } catch (JsonProcessingException e1) {
                logger.error("objectMapper error!", e);
                return new HttpResponseModel<Boolean>(SysCode.UNKNOWN_ERROR_CODE);
            }
        }
    }
    
    /**
     * 密码校验
     * @return
     */
    public String checkPwd(String pwd){
        String regexZS = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,}$";
        if (!pwd.matches(regexZS)){
            return "密码必须为八位以上，且含大、小写字母、数字及特殊字符";
        }
        return null;
    }

    @Transactional  //异常则回滚
    public HttpResponseModel<Boolean> createUser_Transactional(UserEntity userEntity, String[] role_ids) {
        HttpResponseModel<Boolean> result;
        //事务提交：1.创建用户，2.创建用户与角色关联。如果失败，事务回滚。
        int insertResult = userMapper.insert(userEntity);
        HashMap map = new HashMap();
        map.put("user_id",userEntity.getUser_id());
        map.put("role_ids", Arrays.asList(role_ids));
        userRolesMapper.insertRolesByUser(map);
        result = new HttpResponseModel<Boolean>(
                SysCode.SYS_SUCCESS_CODE,
                BooleanUtils.toBoolean(insertResult)
        );
        return result;
    }

    /**
     * 修改用户与修改密码是共用一个接口
     * @param userUpdateModel
     * @param role_ids
     * @return 
     * @throws Exception
     */
    @Transactional
    public HttpResponseModel<Boolean> updateUser(UserUpdateModel userUpdateModel, String[] role_ids) throws Exception{
		//修改用户与修改密码是共用一个接口
    	if (StringUtils.isNotEmpty(userUpdateModel.getPassword())) {
			// 前台传过来的密码为base64转码后的，所以需要解码
			String realPwd = Base64.decodeStr(userUpdateModel.getPassword());
			// 校验密码格式是否正确
			if (StringUtils.isNotBlank(checkPwd(realPwd))) {
				return new HttpResponseModel<Boolean>(authCode.PWD_ERROR);
			}
			userUpdateModel.setPassword(MD5Encoder.encode(realPwd));
		}
        userMapper.update(userUpdateModel);
        if (Objects.nonNull(role_ids)) {
            userRolesMapper.deleteRolesByUser(userUpdateModel.getUser_id());
            HashMap map = new HashMap();
            map.put("user_id",userUpdateModel.getUser_id());
            map.put("role_ids", Arrays.asList(role_ids));
            userRolesMapper.insertRolesByUser(map);
        }
        return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE, true);
    }
    
    /**
     * 修改密码
     * @param userUpdateModel
     * @param role_ids
     * @return
     * @throws Exception
     */
    public HttpResponseModel<String> updatePwd(UserUpdateModel userUpdateModel) throws Exception{
	    // 前台传过来的密码为base64转码后的，所以需要解码
		String realPwd = Base64.decodeStr(userUpdateModel.getPassword());
		// 校验密码格式是否正确
		String checkMsg = checkPwd(realPwd);
		if (StringUtils.isNotBlank(checkMsg)) {
			return new HttpResponseModel<String>(AuthCode.OPERATE_FAILED, checkMsg);
		}
		userUpdateModel.setPassword(MD5Encoder.encode(realPwd));
        userMapper.update(userUpdateModel);
        return new HttpResponseModel<String>(SysCode.SYS_SUCCESS_CODE, " 修改成功");
    }

    public HttpResponseModel<SimplePageInfoModel> queryUser(UserQueryModel userQueryModel) {
        int pageNum = userQueryModel.getPageNum();
        int pageSize = userQueryModel.getPageSize();
        pageNum = pageNum == 0 ? 1 : pageNum;
        pageSize = pageSize == 0 ? 10 : pageSize;
        List<UserQueryResultModel> resultModels = userMapper.queryUser(userQueryModel);
        if (resultModels.size()>0 && pageSize > 0) {
            SimplePageInfoModel simplePageInfoModel = new SimplePageInfoModel();
            simplePageInfoModel.setTotal(userMapper.queryUserCount(userQueryModel));
            simplePageInfoModel.setPageSize(pageSize);
            simplePageInfoModel.setPageNum(pageNum);
            simplePageInfoModel.setPages((resultModels.size() - 1) / pageSize + 1);
            simplePageInfoModel.setList(resultModels);
            return new HttpResponseModel<SimplePageInfoModel>(
                    SysCode.SYS_SUCCESS_CODE, simplePageInfoModel
            );
        }else {
            return new HttpResponseModel<SimplePageInfoModel>();
        }

    }

    public UserEntity currentUser() {
        UserEntity source = userRolePermissionSupport.user();
        BeanCopier beanCopier = BeanCopier.create(UserEntity.class, UserEntity.class, false);
        UserEntity target = new UserEntity();
        beanCopier.copy(source, target,  null);
        target.setPassword(null);
        target.setId_card(null);
        target.setCreate_time(null);
        target.setUpdate_time(null);
        return target;
    }

}
