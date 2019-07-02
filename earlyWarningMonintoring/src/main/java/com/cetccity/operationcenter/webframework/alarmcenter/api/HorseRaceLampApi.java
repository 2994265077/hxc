package com.cetccity.operationcenter.webframework.alarmcenter.api;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.api
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:45 2019-03-22
 * Updater:     heliangming
 * Update_Date：16:45 2019-03-22
 * 更新描述:    heliangming 补充
 **/

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *跑马灯
 */
@Api(tags = "预警中心--跑马灯")
@RequestMapping("/alarmcenter")
public interface HorseRaceLampApi {

    @RequestMapping(value="/find/lamp", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LoadMap> findLamp();
}
