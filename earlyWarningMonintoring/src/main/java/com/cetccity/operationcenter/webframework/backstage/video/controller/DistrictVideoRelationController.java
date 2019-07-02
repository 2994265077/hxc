package com.cetccity.operationcenter.webframework.backstage.video.controller;

import com.cetccity.operationcenter.webframework.backstage.video.api.DistrictVideoRelationApi;
import com.cetccity.operationcenter.webframework.backstage.video.service.DistrictVideoRelationService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletRequest;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:34 2019-05-08
 * Updater:     heliangming
 * Update_Date：18:34 2019-05-08
 * 更新描述:    heliangming 补充
 **/
@RestController
public class DistrictVideoRelationController implements DistrictVideoRelationApi {

    /**
     * 注入 districtVideoRelationService
     */
    @Autowired
    private DistrictVideoRelationService districtVideoRelationService;

    /**
     * 查询视屏关联标签
     * @param tagName 标签名称
     * @param user 用户名
     * @param pageNum 页码
     * @param pageSize 行数
     * @return
     */
    public HttpResponseModel<PageInfo> findVideoRelationTag(String tagName, String user, String street, Integer pageNum, Integer pageSize) {
        HttpResponseModel<PageInfo> pageInfo = districtVideoRelationService.findVideoRelationTag(tagName, user, street, pageNum, pageSize);
        return pageInfo;
    }

    /**
     * 查询视屏关联标签
     * @param cameraId 视屏编号
     * @return
     */
    public HttpResponseModel findVideoRelationByCameraId(String cameraId, ServletRequest servletRequest){
        return districtVideoRelationService.findVideoRelationByCameraId(cameraId, servletRequest);
    }

    /**
     * 给视屏添加标签
     * @param rings 视屏编号
     * @param tagId 标签id
     */
    public HttpResponseModel<Boolean> videoAddTag(String rings, Integer tagId, ServletRequest servletRequest) {
        return districtVideoRelationService.videoAddTag(rings, tagId, servletRequest);
    }

    public HttpResponseModel<Boolean> videoAddTagToCameraId(String cameraId, Integer tagId, ServletRequest servletRequest){
        return districtVideoRelationService.videoAddTagToCameraId(cameraId,  tagId, servletRequest);
    }

    /**
     * 删除有标签的视屏
     * @param object_ID object_ID
     */
    public HttpResponseModel<Integer> videoRemoveTag(Integer object_ID) {
        return districtVideoRelationService.videoRemoveTag(object_ID);
    }

    /**
     * 给视屏删除标签
     * @param cameraId 视屏编号
     * @param tagId 标签id
     */
    public HttpResponseModel<Integer> videoRemoveTagToCameraId(String cameraId,Integer tagId){
        return districtVideoRelationService.videoRemoveTagToCameraId(cameraId, tagId);
    }

}
