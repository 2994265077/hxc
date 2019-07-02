package com.cetccity.operationcenter.webframework.hiddendanger.api;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.api
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2019/2/19 15:13
 * @Updater: huangzezhou
 * @Update_Date: 2019/2/19 15:13
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/

@Api(tags = "安全隐患一张图--三小场所详情--隐患事件--详情")
@RequestMapping("/hiddendanger/threesmall/event")
public interface ThreeSmallEventDetailApi {

    @ApiOperation(value = "获取ticket", notes = "获取三小场所隐患事件请求，所需的ticket")
    @RequestMapping(value = "/getTicket",method = RequestMethod.GET)
    HttpResponseModel<String> getTicket();

}