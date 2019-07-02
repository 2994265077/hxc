package com.cetccity.operationcenter.webframework.rundisplay.api;

import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DISTRICT_CLASSNode;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:51 2019-06-05
 * Updater:     heliangming
 * Update_Date：17:51 2019-06-05
 * 更新描述:    heliangming 补充
 **/
@Api(tags = "运行展现")
@RequestMapping("/rundisplay")
public interface VideoTagMenuApi {

    @ApiOperation(value = "查询视频标签层级", notes = "查询视频标签层级")
    @RequestMapping(value = "find/video/tag/hierarchy", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<List<DISTRICT_CLASSNode>> getVideoTagMenu(ServletRequest servletRequest);

}
