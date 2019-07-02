/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MacroEconomy
 * Author:   YHY
 * Date:     2019/6/3 9:39
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.MacroEconomyDimensions;
import com.cetccity.operationcenter.webframework.urbansign.service.impl.MacroEconomyServiceV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/3
 * @since 1.0.0
 */
@RestController
@Api(tags = "城市体征--宏观经济 2019-06-03版")
@RequestMapping("/urbansign/economy/macro")
public class MacroEconomyControllerV2 {
    @Autowired
    private MacroEconomyServiceV2 macroEconomyServiceV2;

    @ApiOperation(value = "查询", notes = "深圳各季度各区生产总值对比")
    @GetMapping("/{type}")
    public HttpResponseModel<Map<String, Object>> cityProduct(@PathVariable("type") String type) {
        return HttpResponseModel.defaultSuccess(macroEconomyServiceV2.queryByCode(type));
    }

    @ApiOperation(value = "查询图表类型列表", notes = "查询图表类型列表")
    @GetMapping("/types")
    public HttpResponseModel<MacroEconomyDimensions[]> types() {
        return HttpResponseModel.defaultSuccess(MacroEconomyDimensions.values());
    }



}