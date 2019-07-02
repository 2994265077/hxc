package com.cetccity.operationcenter.webframework.backstage.video.api;

import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DISTRICT_CLASSNode;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletRequest;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:27 2019-05-08
 * Updater:     heliangming
 * Update_Date：9:27 2019-05-08
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "后台管理--视屏标签管理")
@RequestMapping("/backstage")
public interface DistrictVideoTagApi {

    /**
     * 查询视频标签层级
     *
     * @param servletRequest
     * @return
     */
    @ApiOperation(value = "查询视频标签层级", notes = "查询视频标签层级")
    @RequestMapping(value = "find/video/tag/hierarchy", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<List<DISTRICT_CLASSNode>> findVideoTagHierarchy(ServletRequest servletRequest);

    /**
     * @param tagGroupName   标签组名称
     * @param servletRequest servletRequest
     */
    @ApiOperation(value = "新增视屏标签组", notes = "新增视屏标签组")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "tagGroupName--行政区域", name = "tagGroupName", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/video/tag/group/add", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Integer> addVideoTagGroup(String tagGroupName, ServletRequest servletRequest);

    /**
     * @param tagGroupId     标签组ID
     * @param tagName        标签名称
     * @param servletRequest servletRequest
     */
    @ApiOperation(value = "新增视屏标签", notes = "新增视屏标签")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "tagGroupId--行政区域", name = "tagGroupId", dataType = "Integer", paramType = "query", required = true, example = "1"),
            @ApiImplicitParam(value = "tagName--行政区域", name = "tagName", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/video/tag/add", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Integer> addVideoTag(Integer tagGroupId, String tagName, ServletRequest servletRequest);

    /**
     * @param object_ID      object_ID
     * @param tagName 标签名称
     * @param status  1-启用、0-停用
     */
    @ApiOperation(value = "修改视屏标签名称", notes = "修改视屏标签名称")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "object_ID--主键", name = "object_ID", dataType = "int", paramType = "query", required = true, example = "1"),
            @ApiImplicitParam(value = "tagName--标签名称、标签名称", name = "tagName", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "status--状态", name = "status", dataType = "int", paramType = "query", required = true, example = "1")
    })
    @RequestMapping(value = "/video/tag/update", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Boolean> updateVideoTag(Integer object_ID, String tagName, Integer status);

    /**
     * @param tagGroupName 标签名称
     * @param user         用户名
     * @param pageNum      页码
     * @param pageSize     行数
     */
    @ApiOperation(value = "查询视屏标签组", notes = "查询视屏标签组")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "tagName--标签名称", name = "tagGroupName", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "user--用户名", name = "user", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "pageNum--页码", name = "pageNum", dataType = "Integer", paramType = "query", required = true, example = "1"),
            @ApiImplicitParam(value = "pageSize--行数", name = "pageSize", dataType = "Integer", paramType = "query", required = true, example = "10")
    })
    @RequestMapping(value = "find/video/tag/group", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel<PageInfo> findVideoTagGroup(String tagGroupName, String user, Integer pageNum, Integer pageSize);

    @ApiOperation(value = "查询视屏标签", notes = "查询视屏标签")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "tagGroupId--标签组id", name = "tagGroupId", dataType = "string", paramType = "true"),
            @ApiImplicitParam(value = "tagName--标签名称", name = "tagName", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "user--用户名", name = "user", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "pageNum--页码", name = "pageNum", dataType = "Integer", paramType = "query", required = true, example = "1"),
            @ApiImplicitParam(value = "pageSize--行数", name = "pageSize", dataType = "Integer", paramType = "query", required = true, example = "10")
    })
    @RequestMapping(value = "find/video/tag", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel<PageInfo> findVideoTag(String tagGroupId, String tagName, String user, Integer pageNum, Integer pageSize);

    /**
     * 删除视屏标签组
     *
     * @param object_ID 标签object_ID
     */
    @ApiOperation(value = "删除视屏标签组", notes = "删除视屏标签组")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "object_ID--object_ID", name = "object_ID", dataType = "Integer", paramType = "query", required = true, example = "1")
    })
    @RequestMapping(value = "delete/video/tag/group", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Boolean> deleteVideoTagGroup(Integer object_ID);

    /**
     * 删除视屏标签
     *
     * @param object_ID 标签object_ID
     */
    @ApiOperation(value = "删除视屏标签", notes = "删除视屏标签")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "object_ID--标签object_ID", name = "object_ID", dataType = "Integer", paramType = "query", required = true, example = "1")
    })
    @RequestMapping(value = "delete/video/tag", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Boolean> deleteVideoTag(Integer object_ID);

}
