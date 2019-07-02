package com.cetccity.operationcenter.webframework.backstage.video.controller;

import com.cetccity.operationcenter.webframework.backstage.video.api.DistrictVideoTagApi;
import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DISTRICT_CLASSNode;
import com.cetccity.operationcenter.webframework.backstage.video.service.DistrictVideoTagService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletRequest;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:19 2019-05-08
 * Updater:     heliangming
 * Update_Date：11:19 2019-05-08
 * 更新描述:    heliangming 补充
 **/
@RestController
public class DistrictVideoTagController implements DistrictVideoTagApi {

    @Autowired
    DistrictVideoTagService districtVideoTagService;

    public HttpResponseModel<List<DISTRICT_CLASSNode>> findVideoTagHierarchy(ServletRequest servletRequest){
        return districtVideoTagService.findVideoTagHierarchy(servletRequest);
    }

    public ResponseEntity<Integer> addVideoTagGroup(String tagGroupName,ServletRequest servletRequest) {
        return districtVideoTagService.addVideoTagGroup(tagGroupName,servletRequest);
    }

    public ResponseEntity<Integer> addVideoTag(Integer tagGroupId, String tagName, ServletRequest servletRequest){
        return districtVideoTagService.addVideoTag(tagGroupId, tagName, servletRequest);
    }

    public ResponseEntity<Boolean> updateVideoTag(Integer object_ID, String tagName, Integer status){
        return districtVideoTagService.updateVideoTag(object_ID, tagName, status);
    }

    public HttpResponseModel<PageInfo> findVideoTagGroup(String tagGroupName, String user, Integer pageNum, Integer pageSize){
        HttpResponseModel<PageInfo> pageInfo = districtVideoTagService.findVideoTagGroup(tagGroupName, user, pageNum, pageSize);
        return pageInfo;
    }

    public HttpResponseModel<PageInfo> findVideoTag(String tagGroupId, String tagName, String user, Integer pageNum, Integer pageSize){
        HttpResponseModel<PageInfo> pageInfo = districtVideoTagService.findVideoTag(tagGroupId, tagName, user, pageNum, pageSize);
        return pageInfo;
    }

    public ResponseEntity<Boolean> deleteVideoTagGroup(Integer object_ID){
        return districtVideoTagService.deleteVideoTagGroup(object_ID);
    }

    public ResponseEntity<Boolean> deleteVideoTag(Integer object_ID){
        return districtVideoTagService.deleteVideoTag(object_ID);
    }

}
