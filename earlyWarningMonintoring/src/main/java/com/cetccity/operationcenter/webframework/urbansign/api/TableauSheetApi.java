package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.TableauEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.api
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/10 9:09
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/10 9:09
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Api(tags = "城市体征--tableau周报")
public interface TableauSheetApi {

    @ApiOperation(value = "城市体征--tableau获取ticket", notes = "城市体征--tableau获取ticket，用于免登陆")
    @RequestMapping(value = "/tableau/ticket",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<String> getTicket();

    @ApiOperation(value = "城市体征--tableau获取周报列表", notes = "城市体征--tableau获取周报列表")
    @RequestMapping(value = "/tableau/list",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<List<TableauEntity>> getList();

    @ApiOperation(value = "城市体征--创建tableau周报名和url", notes = "城市体征--创建tableau周报名和url,注意需要传入view之后且？之前的url")
    @RequestMapping(value = "/tableau/insertSheet",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<Boolean> insertSheet(TableauEntity model);

    @ApiOperation(value = "城市体征--删除tableau周报名和url", notes = "城市体征--根据object_id删除tableau周报条目")
    @RequestMapping(value = "/tableau/deleteSheet",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<Boolean> deleteSheet(int object_id);
}
