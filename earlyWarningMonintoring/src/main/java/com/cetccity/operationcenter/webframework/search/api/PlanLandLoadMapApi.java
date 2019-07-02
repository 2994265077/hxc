package com.cetccity.operationcenter.webframework.search.api;

import com.cetccity.operationcenter.webframework.web.model.build.LoadMapBuildGrade;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.api.map
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:11 2019-03-12
 * Updater:     heliangming
 * Update_Date：17:11 2019-03-12
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "搜索引擎--规土委搜索数据落图")
@RequestMapping("/search")
public interface PlanLandLoadMapApi {

    @ApiOperation(value = "落图", notes = "规土委搜索数据落图")
    @RequestMapping(value = "/AddressResolution/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LoadMap> loadMapResolution(String id);

    @ApiOperation(value = "落图", notes = "规土委综合搜索数据面数据落图")
    @RequestMapping(value = "/AddressWebService/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LoadMapBuildGrade> loadMapWebService(String id);

    @ApiOperation(value = "落图", notes = "规土委综合搜索数据面数据落图")
    @RequestMapping(value = "/AddressWebService/point/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LoadMap> loadMapWebServicePoint(String id);

}
