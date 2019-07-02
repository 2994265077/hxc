/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: CommunityInfoController
 * Author:   YHY
 * Date:     2019/5/16 11:32
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.community.controller;

import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo;
import com.cetccity.operationcenter.webframework.backstage.community.entity.RegionNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.RoleCommunity;
import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
@RestController
@RequestMapping("/street")
@Api(tags = "后台管理--社区、街道管理")
public class CommunityInfoController {

    @Autowired
    private CommunityInfoService communityInfoService;

    @ApiOperation(value = "查询所有街道" ,notes = "查询所有街道")
    @GetMapping("/")
    public HttpResponseModel<List<CommunityInfo>> streets() {
        return HttpResponseModel.defaultSuccess(communityInfoService.queryStreets());
    }

    @ApiOperation(value = "查询所有社区" ,notes = "查询所有社区")
    @GetMapping("/community")
    public HttpResponseModel<List<CommunityInfo>> communities() {
        return HttpResponseModel.defaultSuccess(communityInfoService.queryCommunities());
    }




}