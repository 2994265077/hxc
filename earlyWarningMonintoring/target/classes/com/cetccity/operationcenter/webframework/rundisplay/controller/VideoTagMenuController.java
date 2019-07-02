package com.cetccity.operationcenter.webframework.rundisplay.controller;

import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DISTRICT_CLASSNode;
import com.cetccity.operationcenter.webframework.backstage.video.service.DistrictVideoTagService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.rundisplay.api.VideoTagMenuApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:53 2019-06-05
 * Updater:     heliangming
 * Update_Date：17:53 2019-06-05
 * 更新描述:    heliangming 补充
 **/
@RestController
public class VideoTagMenuController implements VideoTagMenuApi{

    @Autowired
    DistrictVideoTagService districtVideoTagService;

    public HttpResponseModel<List<DISTRICT_CLASSNode>> getVideoTagMenu(ServletRequest servletRequest){
        return districtVideoTagService.findVideoTagHierarchy(servletRequest);
    }
}
