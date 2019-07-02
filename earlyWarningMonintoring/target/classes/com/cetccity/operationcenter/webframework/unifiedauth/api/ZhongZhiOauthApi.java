package com.cetccity.operationcenter.webframework.unifiedauth.api;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.unifiedauth.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:05 2019-07-01
 * Updater:     heliangming
 * Update_Date：16:05 2019-07-01
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "综合治理平台认证逻辑")
@RequestMapping("/oauth2ew")
public interface ZhongZhiOauthApi {

    @ApiOperation(value = "综合治理请求", notes = "综合治理请求")
    @RequestMapping(value = "/servlet/oauht", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    void ask(HttpServletResponse response) throws Exception;

    @ApiOperation(value = "给综合治理我们的认证url", notes = "给综合治理我们的认证url")
    @RequestMapping(value = "/zhongzhi/login", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    void login(String code, String state, HttpServletResponse response)throws Exception;
}
