/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: CommunityRoleController
 * Author:   YHY
 * Date:     2019/5/28 16:42
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.community.controller;

import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.RegionNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.RoleCommunity;
import com.cetccity.operationcenter.webframework.backstage.community.entity.StreetNode;
import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityRoleService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/28
 * @since 1.0.0
 */
@RequestMapping("/backstage/auth/community/role")
@RestController
@Api(tags = "后台管理--角色社区管理")
public class CommunityRoleController {

    @Autowired
    private CommunityRoleService communityRoleService;
    @Autowired
    private CommunityInfoService communityInfoService;

    @ApiOperation(value = "给角色添加社区")
    @PostMapping("/")
    public HttpResponseModel roleCommunity(@RequestBody @Validated RoleCommunity roleCommunity) {
        communityRoleService.save(roleCommunity);
        return HttpResponseModel.defaultSuccess("操作成功");
    }

    @ApiOperation(value = "根据角色id查询社区")
    @GetMapping("/{role_id}")
    public HttpResponseModel<List<String>> communities(@PathVariable("role_id") String roleId) {
        return HttpResponseModel.defaultSuccess(communityRoleService.selectCommunityIdsByRole(roleId));
    }

    @ApiOperation(value = "清空角色所有社区权限")
    @DeleteMapping("/{role_id}")
    public HttpResponseModel deleteRoleCommunities(@PathVariable("role_id") String roleId) {
        return HttpResponseModel.defaultSuccess(communityRoleService.delete(roleId));
    }

    @ApiOperation(value = "查看当前用户管辖社区名称")
    @GetMapping("/community")
    public HttpResponseModel<List<CommunityNode>> communityNodes() {
        return HttpResponseModel.defaultSuccess(communityRoleService.selectCurrentUserCommunityName());
    }

    @ApiOperation(value = "查看当前用户管辖街道名称")
    @GetMapping("/street")
    public HttpResponseModel<List<StreetNode>> streetNodes() {
        return HttpResponseModel.defaultSuccess(communityRoleService.selectCurrentUserStreetName());
    }

    @ApiOperation(value = "查看当前用户管辖区名称")
    @GetMapping("/region")
    public HttpResponseModel<List<RegionNode>> regionNodes() {
        return HttpResponseModel.defaultSuccess(communityRoleService.selectCurrentUserRegionName());
    }

    @ApiOperation(value = "当前用户有权限的社区树")
    @GetMapping("/user/tree")
    public HttpResponseModel<List<RegionNode>> currentCommunityTree() {
        return HttpResponseModel.defaultSuccess(communityRoleService.selectCurrentUserCommunityTree());
    }

    @ApiOperation(value = "当前用户是否有全区社区权限")
    @GetMapping("/user/super")
    public HttpResponseModel<Boolean> hasAllCommunityPermission() {
        return HttpResponseModel.defaultSuccess(communityRoleService.hasAllCommunityPermission());
    }

    @ApiOperation(value = "街道社区树")
    @GetMapping("/tree")
    public HttpResponseModel<List<RegionNode>> communityTree() {
        return HttpResponseModel.defaultSuccess(communityInfoService.communityTree());
    }





}