/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: CommunityRoleService
 * Author:   YHY
 * Date:     2019/5/28 16:46
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.community.service;

import com.cetccity.operationcenter.webframework.backstage.community.dao.CommunityInfoMapper;
import com.cetccity.operationcenter.webframework.backstage.community.dao.CommunityRoleMapper;
import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo;
import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.RegionNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.RoleCommunity;
import com.cetccity.operationcenter.webframework.backstage.community.entity.StreetNode;
import com.cetccity.operationcenter.webframework.common.support.UserRolePermissionSupport;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.RoleMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.RoleEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.cetccity.operationcenter.webframework.backstage.community.util.CommunityTreeUtil.communityInfoTree;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/28
 * @since 1.0.0
 */
@Service
public class CommunityRoleService {
    @Autowired
    private CommunityRoleMapper communityRoleMapper;
    @Autowired
    private CommunityInfoMapper communityInfoMapper;
    @Autowired
    private UserRolePermissionSupport userRolePermissionSupport;

    public void save(RoleCommunity roleCommunity) {
        communityRoleMapper.delete(roleCommunity.getRoleId());
        if (CollectionUtils.isNotEmpty(roleCommunity.getCommunityIds())) {
            for (String communityId : roleCommunity.getCommunityIds()) {
                communityRoleMapper.save(roleCommunity.getRoleId(), communityId);
            }
        }

    }

    public boolean delete(String roleId) {
        long delete = communityRoleMapper.delete(roleId);
        return delete > 0;
    }

    public List<String> selectCommunityIdsByRole(String roleId) {
        return communityRoleMapper.selectCommunityIdsByRole(roleId);
    }

    public List<CommunityNode> selectCurrentUserCommunityName() {
        List<String> roleIds = userRolePermissionSupport.roleIdList();
        if (CollectionUtils.isEmpty(roleIds)) {
            return new LinkedList<>();
        }
        return communityRoleMapper.communityNamesByRole(roleIds);
    }

    public List<StreetNode> selectCurrentUserStreetName() {
        List<String> roleIds = userRolePermissionSupport.roleIdList();
        if (CollectionUtils.isEmpty(roleIds)) {
            return new LinkedList<>();
        }
        return communityRoleMapper.streetNamesByRole(roleIds);
    }

    public List<RegionNode> selectCurrentUserRegionName() {
        List<String> roleIds = userRolePermissionSupport.roleIdList();
        if (CollectionUtils.isEmpty(roleIds)) {
            return new LinkedList<>();
        }
        return communityRoleMapper.regionNamesByRole(roleIds);
    }

    public List<RegionNode> selectCurrentUserCommunityTree() {
        List<String> roleIds = userRolePermissionSupport.roleIdList();
        if (CollectionUtils.isEmpty(roleIds)) {
            return new LinkedList<>();
        }
        List<CommunityInfo> communityInfos = communityRoleMapper.communityTreeByRole(roleIds);
        return communityInfoTree(communityInfos, true);
    }

    public boolean hasAllCommunityPermission() {
        List<String> roleIds = userRolePermissionSupport.roleIdList();
        if (CollectionUtils.isEmpty(roleIds)) {
            return false;
        }
        long permissionCommunityCount = communityRoleMapper.countCommunityByRole(roleIds);
        return permissionCommunityCount > 0 && permissionCommunityCount == communityInfoMapper.countAll();
    }

}