package com.cetccity.operationcenter.webframework.hiddendanger.controller.map;

import com.cetccity.operationcenter.webframework.hiddendanger.api.map.RiskPointMapApi;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.LoadMapAttribute;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.RiskPointMapUtil;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.controller.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:58 2019-03-29
 * Updater:     heliangming
 * Update_Date：14:58 2019-03-29
 * 更新描述:    heliangming 补充
 **/
@RestController
@CacheConfig(cacheNames = "loadMap")
@Slf4j
public class RiskPointMapController implements RiskPointMapApi {

    @Autowired
    RiskPointMapUtil riskPointMapUtil;

    public List<IconTypeLoadMap> findIconTypeByRISK_POINT(String street, String id) throws IOException {
        String tableName = "RISK_POINT";
        List<IconTypeLoadMap> loadMap_list;
        String layerid = LoadMapAttribute.getLoadMapAttribute(tableName).get("layerid");
        loadMap_list = riskPointMapUtil.iconTypeLoadMapByRiskPoint(tableName,layerid,street,id);
        return loadMap_list;
    }
}
