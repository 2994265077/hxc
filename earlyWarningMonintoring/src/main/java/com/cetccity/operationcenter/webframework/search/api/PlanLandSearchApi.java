package com.cetccity.operationcenter.webframework.search.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.api
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:57 2019-03-12
 * Updater:     heliangming
 * Update_Date：9:57 2019-03-12
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "搜索引擎--规土委搜索服务")
@RequestMapping("/search")
public interface PlanLandSearchApi {

    @ApiOperation(value = "搜索", notes = "地名地址搜索服务")
    @RequestMapping(value = "/plan/addressResolution",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel<List<SearchObjList>> addressResolution(String content, Integer pageNum, Integer pageSize) throws Exception;

    @ApiOperation(value = "搜索", notes = "综合搜索服务--建筑物面数据")
    @RequestMapping(value = "/plan/addressWebService",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    MyPageInfoModel<List<SearchObjList>> addressWebService(String content, Integer pageNum, Integer pageSize) throws IOException;
}
