package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.RoleUpdateModel;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.RoleMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.RolePermissionsMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RolePermissionRelationVo;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.UuidUtil;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.service.Impl
 * @Project: futian1
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/6 17:49
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/6 17:49
 * @Update_Description: huangzezhou 补充
 **/
@Service
@Transactional
public class RoleManagementServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(RoleManagementServiceImpl.class);

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RolePermissionsMapper rolePermissionsMapper;


    @Autowired
    AuthCode authCode;


    public HttpResponseModel<Boolean> addRole(RoleEntity role, String[] permission_ids) {
        try {
            int row = roleMapper.insertRole(role);
            return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE, row == 1);
        }catch (Exception e){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                logger.error("user add role error: "+objectMapper.writeValueAsString(role)+objectMapper.writeValueAsString(permission_ids),e);
            } catch (JsonProcessingException e1) {
                logger.error("objectMapper error!\n", e1);
            }
            return new HttpResponseModel<Boolean>(authCode.CREATE_ROLE_ERROR);
        }
    }

    public HttpResponseModel<List<RoleEntity>> queryAllRoles() {
        return new HttpResponseModel<List<RoleEntity>>(SysCode.SYS_SUCCESS_CODE, roleMapper.queryAll());
    }

    public PageInfo<RoleEntity> pageAllRoles(PageParam page) {
        Page<RoleEntity> roleEntities = PageHelper.startPage(page);
        roleMapper.queryAll();
        return PageInfo.of(roleEntities);
    }


    public HttpResponseModel<Boolean> updateRole(final RoleUpdateModel role) {
        roleMapper.update(role);
        return new HttpResponseModel<Boolean>(SysCode.SYS_SUCCESS_CODE);
    }

    public void updateRelation(RolePermissionRelationVo permissionRelationVo) {
        rolePermissionsMapper.delete(permissionRelationVo.getRoleId());
        if (CollectionUtils.isNotEmpty(permissionRelationVo.getPermissionIds())) {
            HashMap map = new HashMap(){{
                put("role_id", permissionRelationVo.getRoleId());
                put("permission_ids", permissionRelationVo.getPermissionIds());
            }};
            rolePermissionsMapper.insertRolePermissions(map);
        }

    }


    public HttpResponseModel<List<PermissionEntity>> queryRoleInfo(String role_id){
        List<PermissionEntity> permissionEntities = roleMapper.queryRoleInfo(role_id);
        return new HttpResponseModel(SysCode.SYS_SUCCESS_CODE, permissionEntities);
    }

    public List<String> queryPermissionIds(String roleId) {
        return roleMapper.queryPermissionIds(roleId);
    }

}
