package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.hiddendanger.api.map.SanXiaoPlaceMapApi;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.SanXiaoPlaceLoadMap;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.LoadMapAttribute;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.SanXiaoPlaceMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:21 2019-04-08
 * Updater:     heliangming
 * Update_Date：9:21 2019-04-08
 * 更新描述:    heliangming 补充
 **/
@RestController
public class SanXiaoPlaceMapController implements SanXiaoPlaceMapApi {

    @Autowired
    SanXiaoPlaceMapUtil sanXiaoPlaceMapUtil;

    public List<SanXiaoPlaceLoadMap> findSanXiaoByDB(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id, String alarm,String startTime,String endTime) throws IOException{
        List<SanXiaoPlaceLoadMap> loadMap_list;
        String layerid = LoadMapAttribute.getLoadMapAttribute(tableNameUrl).get("layerid");
        String tableName = "BLK_SANXIAO_PLACE";
        loadMap_list = sanXiaoPlaceMapUtil.sanXiaoLoadMapBySanXiaoPlace(tableName,layerid,street,id);
        return loadMap_list;
    }

}
