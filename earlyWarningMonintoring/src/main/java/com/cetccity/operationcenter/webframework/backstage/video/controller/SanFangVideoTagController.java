package com.cetccity.operationcenter.webframework.backstage.video.controller;

import com.cetccity.operationcenter.webframework.backstage.video.api.SanFangVideoTagApi;
import com.cetccity.operationcenter.webframework.backstage.video.service.SanFangVideoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:54 2019-07-31
 * Updater:     heliangming
 * Update_Date：9:54 2019-07-31
 * 更新描述:    heliangming 补充
 **/
@RestController
public class SanFangVideoTagController implements SanFangVideoTagApi{

    @Autowired
    SanFangVideoTagService sanFangVideoTagService;

    public String saveVideoTag(MultipartFile file){
        return sanFangVideoTagService.saveVideoTag(file);
    }
}
