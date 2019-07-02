package com.cetccity.operationcenter.webframework.environment.controller.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.environment.api.map.CleanRiverLoadMapApi;
import com.cetccity.operationcenter.webframework.environment.util.EnvironmentLoadMapUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.controller.map.ALL_LoadMapController;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.controller.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:03 2019-05-21
 * Updater:     heliangming
 * Update_Date：10:03 2019-05-21
 * 更新描述:    heliangming 补充
 **/
@Slf4j
@RestController
public class CleanRiverLoadMapController implements CleanRiverLoadMapApi {

    @Autowired
    EnvironmentLoadMapUtil environmentLoadMapUtil;

    public List<LoadMap> LoadMapToDrainHold(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id) throws IOException {
        return environmentLoadMapUtil.LoadMapToDrainHold(tableNameUrl, street,id);
    }

    public List<IconTypeLoadMap> LoadMapDrainFacilities(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id) throws IOException {
        return environmentLoadMapUtil.LoadMapDrainFacilities(tableNameUrl, street, id);
    }
}
