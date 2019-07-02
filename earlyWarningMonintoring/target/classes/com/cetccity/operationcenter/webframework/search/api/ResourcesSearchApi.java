package com.cetccity.operationcenter.webframework.search.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.MapConfig;
import com.cetccity.operationcenter.webframework.search.api.model.TagMenuConfig;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;
import com.cetccity.operationcenter.webframework.web.model.incident.NearbyResourcesModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:11 2019-03-12
 * Updater:     heliangming
 * Update_Date：9:11 2019-03-12
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "搜索引擎--搜索引擎")
@RequestMapping("/search")
public interface ResourcesSearchApi {

    @ApiOperation(value = "搜索", notes = "搜索")
    @RequestMapping(value = "/resource",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--[小档口,公安视频,建筑风险]", name = "tag", dataType = "string", paramType = "query")
    })
    @ResponseBody
    MyPageInfoModel<List<SearchObjList>> findResourceObj(String content, Integer pageNum, Integer pageSize,String tag) throws IOException ;

    @ApiOperation(value = "搜索特定point点周边的实体--落图", notes = "搜索特定point点周边的实体")
    @RequestMapping(value = "/near/point",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--[BJ0301,BJ2124,IOT_DEVICE@0003,BJ0121,VIDEO_POLICE,BLK_SANXIAO_PLACE@60023]", name = "poi", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "经度--114.09462820727758", name = "jd", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "纬度--22.54998918105337", name = "wd", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(value = "距离--5", name = "range", dataType = "string", paramType = "query", required = true)
    })
    @ResponseBody
    List<NearbyResourcesModel> findNearResourceObj(String poi, String jd, String wd, String range) throws IOException;

    @ApiOperation(value = "tagMenu--菜单--searchTagMenu.xml", notes = "搜索特定point点的菜单")
    @RequestMapping(value = "/tag/menu",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    TagMenuConfig findTagMenu()throws Exception;

}
