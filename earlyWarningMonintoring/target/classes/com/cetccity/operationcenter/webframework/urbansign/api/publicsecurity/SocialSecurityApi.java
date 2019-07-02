package com.cetccity.operationcenter.webframework.urbansign.api.publicsecurity;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.api.publicsecurity
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:40 2019-05-31
 * Updater:     heliangming
 * Update_Date：16:40 2019-05-31
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "城市体征KPI--公共安全--社会安全")
@RequestMapping("/urbansign")
public interface SocialSecurityApi {

    @ApiOperation(value = "社会安全--社会安全事件", notes = "社会安全--社会安全事件")
    @RequestMapping(value = "/social/security/rightOne",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel publicSecurity();
}
