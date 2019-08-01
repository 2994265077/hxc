package com.cetccity.operationcenter.webframework.backstage.video.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:50 2019-07-31
 * Updater:     heliangming
 * Update_Date：9:50 2019-07-31
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "后台管理--三防视屏标签")
@RequestMapping("/backstage")
public interface SanFangVideoTagApi {

    @RequestMapping(value="sanfang/excel/to/db", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    String saveVideoTag(MultipartFile file);


}
