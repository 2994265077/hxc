package com.cetccity.operationcenter.webframework.backstage.video.service;

import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DISTRICT_CLASSNode;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.github.pagehelper.PageInfo;
import org.springframework.http.ResponseEntity;
import javax.servlet.ServletRequest;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.service
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:20 2019-05-08
 * Updater:     heliangming
 * Update_Date：11:20 2019-05-08
 * 更新描述:    heliangming 补充
 **/
public interface DistrictVideoTagService {

    /**
     *查询视频标签层级
     * @param servletRequest
     * @return
     */
    HttpResponseModel<List<DISTRICT_CLASSNode>> findVideoTagHierarchy(ServletRequest servletRequest);
    /**
     * 新增视屏标签组
     * @param tagGroupName 标签组名称
     * @param servletRequest servletRequest
     */
    ResponseEntity<Integer> addVideoTagGroup(String tagGroupName, ServletRequest servletRequest);

    /**
     * 新增视屏标签
     * @param tagGroupId 标签组ID
     * @param tagName 标签名称
     * @param servletRequest servletRequest
     */
    ResponseEntity<Integer> addVideoTag(Integer tagGroupId, String tagName, ServletRequest servletRequest);

    /**
     * 修改视屏标签
     * @param object_ID 主键
     * @param tagName 标签名称
     * @param status 状态
     */
    ResponseEntity<Boolean> updateVideoTag(Integer object_ID, String tagName, Integer status);

    /**
     * 查询视屏标签组
     * @param tagGroupName 标签名称
     * @param user 用户名
     * @param pageNum 页码
     * @param pageSize 行数
     */
    HttpResponseModel<PageInfo> findVideoTagGroup(String tagGroupName, String user, Integer pageNum, Integer pageSize);

    /**
     * 查询视屏标签
     * @param tagGroupId
     * @param tagName
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    HttpResponseModel<PageInfo> findVideoTag(String tagGroupId, String tagName, String user, Integer pageNum, Integer pageSize);

    /**
     * 删除视屏标签组
     * @param object_ID 主键id
     */
    ResponseEntity<Boolean> deleteVideoTagGroup(Integer object_ID);

    /**
     * 删除视屏标签
     * @param object_ID 主键id
     */
    ResponseEntity<Boolean> deleteVideoTag(Integer object_ID);
}
