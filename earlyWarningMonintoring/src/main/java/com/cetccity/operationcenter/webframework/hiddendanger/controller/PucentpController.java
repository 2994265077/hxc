/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: PucentpController
 * Author:   YHY
 * Date:     2019/8/1 16:08
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.hiddendanger.service.PucentpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/8/1
 * @since 1.0.0
 */
@RestController
@Api(tags = "纳管企业 -- by 杨鸿远")
@RequestMapping("/hiddendanger/pucent")
public class PucentpController {

    @Autowired
    private PucentpService pucentpService;

    @GetMapping("/pie/street")
    @ApiOperation("纳管企业分街道统计")
    public HttpResponseModel<PieModel> pieByStreet(String street) {
        return HttpResponseModel.defaultSuccess(pucentpService.pieByStreet(street));
    }

    @GetMapping("/pie/community")
    @ApiOperation("纳管企业分社区统计")
    public HttpResponseModel<PieModel> pieByCommunity(String street) {
        return HttpResponseModel.defaultSuccess(pucentpService.pieByCommunity(street));
    }
}