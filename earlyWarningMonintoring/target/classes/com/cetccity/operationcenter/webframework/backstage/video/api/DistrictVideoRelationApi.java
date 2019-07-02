package com.cetccity.operationcenter.webframework.backstage.video.api;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:20 2019-05-08
 * Updater:     heliangming
 * Update_Date：18:20 2019-05-08
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "后台管理--视屏关联标签管理")
@RequestMapping("/backstage")
public interface DistrictVideoRelationApi {

    /**查询视屏关联标签
     * @param tagName 标签名称
     * @param user 用户名
     * @param pageNum 页码
     * @param pageSize 行数
     */
    @ApiOperation(value = "查询视屏关联标签", notes = "查询视屏关联标签")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "tagName--标签名称", name = "tagName", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "user--用户名", name = "user", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "pageNum--页码", name = "pageNum", dataType = "Integer", paramType = "query", required = true , example="1"),
            @ApiImplicitParam(value = "pageSize--行数", name = "pageSize", dataType = "Integer", paramType = "query", required = true , example="10")
    })
    @RequestMapping(value = "find/video/relationTag", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<PageInfo> findVideoRelationTag(String tagName, String user, String street, Integer pageNum, Integer pageSize);

    /**查询视屏关联标签
     * @param cameraId 视屏编号
     */
    @ApiOperation(value = "根据视屏cameraId查询关联的标签", notes = "根据视屏cameraId查询关联的标签")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "cameraId--视屏编号", name = "cameraId", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "find/video/cameraId", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel findVideoRelationByCameraId(String cameraId, ServletRequest servletRequest);

    /**
     * @param rings 多边形json串
     * @param tagId 标签id
     */
    @ApiOperation(value = "给视屏添加标签", notes = "给视屏添加标签")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "多边形json串--[[114.0542109969207, 22.53130933811889],\n" +
                    "[114.0541429988314, 22.530011857282457],\n" +
                    "[114.05393974956479, 22.52872859191758],\n" +
                    "[114.05360347596238, 22.52747360174814],\n" +
                    "[114.05313786230812, 22.526260636709058],\n" +
                    "[114.05254800996265, 22.52510298629913],\n" +
                    "[114.05184038147169, 22.524013333978505],\n" +
                    "[114.05102272976107, 22.523003618206026],\n" +
                    "[114.05010401319404, 22.522084901638998],\n" +
                    "[114.04909429742156, 22.521267249928382],\n" +
                    " [114.04800464510093, 22.52055962143742],\n" +
                    " [114.04684699469101, 22.519969769091944],\n" +
                    " [114.04563402965192, 22.519504155437687],\n" +
                    " [114.0443790394825, 22.519167881835276],\n" +
                    " [114.04309577411762, 22.51896463256867],\n" +
                    " [114.04179829328118, 22.518896634479372],\n" +
                    " [114.04050081244473, 22.51896463256867],\n" +
                    " [114.03921754707986, 22.519167881835276],\n" +
                    " [114.03796255691043, 22.519504155437687],\n" +
                    " [114.03674959187134, 22.519969769091944],\n" +
                    " [114.03559194146142, 22.52055962143742],\n" +
                    " [114.03450228914079, 22.521267249928382],\n" +
                    " [114.0334925733683, 22.522084901638998],\n" +
                    " [114.03257385680128, 22.523003618206026],\n" +
                    " [114.03175620509066, 22.524013333978505],\n" +
                    " [114.0310485765997, 22.52510298629913],\n" +
                    " [114.03045872425423, 22.526260636709058],\n" +
                    " [114.02999311059997, 22.52747360174814],\n" +
                    " [114.02965683699756, 22.52872859191758],\n" +
                    " [114.02945358773096, 22.530011857282457],\n" +
                    " [114.02938558964165, 22.53130933811889],\n" +
                    " [114.02945358773096, 22.532606818955326],\n" +
                    " [114.02965683699756, 22.533890084320202],\n" +
                    " [114.02999311059997, 22.53514507448964],\n" +
                    " [114.03045872425423, 22.536358039528725],\n" +
                    " [114.0310485765997, 22.53751568993865],\n" +
                    " [114.03175620509066, 22.538605342259277],\n" +
                    " [114.03257385680128, 22.539615058031757],\n" +
                    " [114.0334925733683, 22.540533774598785],\n" +
                    " [114.03450228914079, 22.5413514263094],\n" +
                    " [114.03559194146142, 22.54205905480036],\n" +
                    " [114.03674959187134, 22.54264890714584],\n" +
                    " [114.03796255691043, 22.543114520800096],\n" +
                    " [114.03921754707986, 22.543450794402506],\n" +
                    " [114.04050081244473, 22.54365404366911],\n" +
                    " [114.04179829328118, 22.54372204175841],\n" +
                    " [114.04309577411762, 22.54365404366911],\n" +
                    " [114.0443790394825, 22.543450794402506],\n" +
                    " [114.04563402965192, 22.543114520800096],\n" +
                    " [114.04684699469101, 22.54264890714584],\n" +
                    " [114.04800464510093, 22.54205905480036],\n" +
                    " [114.04909429742156, 22.5413514263094],\n" +
                    " [114.05010401319404, 22.540533774598785],\n" +
                    " [114.05102272976107, 22.539615058031757],\n" +
                    " [114.05184038147169, 22.538605342259277],\n" +
                    " [114.05254800996265, 22.53751568993865],\n" +
                    " [114.05313786230812, 22.536358039528725],\n" +
                    " [114.05360347596238, 22.53514507448964],\n" +
                    " [114.05393974956479, 22.533890084320202],\n" +
                    " [114.0541429988314, 22.532606818955326],\n" +
                    " [114.0542109969207, 22.53130933811889],\n" +
                    " [114.0542109969207, 22.53130933811889]]", name = "rings", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "tagId--标签id", name = "tagId", dataType = "int", paramType = "query", required = true , example="1")
    })
    @RequestMapping(value = "/video/add/tag/rings", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel<Boolean> videoAddTag(String rings, Integer tagId, ServletRequest servletRequest);

    @ApiOperation(value = "给单个视屏添加标签", notes = "给单个视屏添加标签")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "gbCode--视屏编号", name = "gbCode", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "tagId--标签id", name = "tagId", dataType = "int", paramType = "query", required = true , example="1")
    })
    @RequestMapping(value = "/video/add/tag/cameraId", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel<Boolean> videoAddTagToCameraId(String cameraId, Integer tagId, ServletRequest servletRequest);

    /**
     * 删除有标签的视屏
     * @param object_ID object_ID
     */
    @ApiOperation(value = "删除有标签的视屏", notes = "删除有标签的视屏")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "object_ID--object_ID", name = "object_ID", dataType = "int", paramType = "query", required = true , example="1")
    })
    @RequestMapping(value = "/video/remove/tag", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel<Integer> videoRemoveTag(Integer object_ID);

    /**
     * 给视屏删除标签
     * @param cameraId 视屏编号
     * @param tagId 标签id
     */
    @ApiOperation(value = "给视屏删除标签", notes = "给视屏删除标签")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "cameraId--视屏编号", name = "cameraId", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "tagId--标签id", name = "tagId", dataType = "int", paramType = "query", required = true , example="1")
    })
    @RequestMapping(value = "/video/remove/tag/cameraId", method = RequestMethod.GET)
    @ResponseBody
    HttpResponseModel<Integer> videoRemoveTagToCameraId(String cameraId,Integer tagId);
}
