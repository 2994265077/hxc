package com.cetccity.operationcenter.webframework.backstage.video.service;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletRequest;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:35 2019-05-08
 * Updater:     heliangming
 * Update_Date：18:35 2019-05-08
 * 更新描述:    heliangming 补充
 **/
public interface DistrictVideoRelationService {

    /**
     * 查询视屏关联标签
     * @param tagName 标签名称
     * @param user 用户名
     * @param pageNum 页码
     * @param pageSize 行数
     * @return
     */
    HttpResponseModel<PageInfo> findVideoRelationTag(String tagName, String user, String street, Integer pageNum, Integer pageSize);


    HttpResponseModel findVideoRelationByCameraId(String cameraId, ServletRequest servletRequest);

    /**
     * 给视屏添加标签
     * @param rings 视屏编号
     * @param tagId 标签id
     */
    HttpResponseModel<Boolean> videoAddTag(String rings, Integer tagId, ServletRequest servletRequest);

    HttpResponseModel<Boolean> videoAddTagToCameraId(String cameraId, Integer tagId, ServletRequest servletRequest);
    /**
     * 删除有标签的视屏
     * @param object_ID object_ID
     */
    HttpResponseModel<Integer> videoRemoveTag(Integer object_ID);

    /**
     * 给视屏删除标签
     * @param cameraId 视屏编号
     * @param tagId 标签id
     */
    HttpResponseModel<Integer> videoRemoveTagToCameraId(String cameraId,Integer tagId);

    void loadMapVideoRelationByClassId(Integer classId);
}
