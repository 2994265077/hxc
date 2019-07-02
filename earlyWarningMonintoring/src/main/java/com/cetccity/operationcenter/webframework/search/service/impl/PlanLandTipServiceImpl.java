package com.cetccity.operationcenter.webframework.search.service.impl;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.search.service.PlanLandTipService;
import com.cetccity.operationcenter.webframework.urbansign.tools.UrbanMapReturnUtil;
import com.cetccity.operationcenter.webframework.web.model.SearchObjList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.search.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:28 2019-03-14
 * Updater:     heliangming
 * Update_Date：17:28 2019-03-14
 * 更新描述:    heliangming 补充
 **/
@Service
public class PlanLandTipServiceImpl implements PlanLandTipService {

    @Autowired
    PlanLandSearchServiceImpl planLandSearchServiceImpl;

    @Autowired
    UrbanMapReturnUtil urbanMapReturnUtil;

    public Map addressResolutionTip(String id) throws IOException {
        List<SearchObjList> searchObjList_list = planLandSearchServiceImpl.searchObjList_list;
        LinkedHashMap<String, String> map = new LinkedHashMap();
        searchObjList_list.stream().filter(u -> u.getId().equals(id)).forEach(u -> {
            map.put("NAME", u.getName());
            map.put("ADDRESS", u.getAddress());
        });
        Map return_map;
        String tableKey = LoadMyUtil.getPropertiesVauleOfKey("tip.properties",ESOperate.dbName + "." + "AddressResolution" + "Key");
        String tableValue = LoadMyUtil.getPropertiesVauleOfKey("tip.properties",ESOperate.dbName + "." + "AddressResolution" + "Value").toUpperCase();
        String HasDetail = "false";
        Boolean hasDetailInfo = Boolean.valueOf(HasDetail);
        String[] key = tableKey.split(","); //注意分隔符是需要转译滴...
        String[] value = tableValue.split(",");

        List result = urbanMapReturnUtil.tipContent(key, value, map);
        return_map = Tooltip.toolTipListToMap(result, hasDetailInfo);
        return_map.put("info_alert", "0");
        return return_map;
    }

    public Map addressWebService_pointTip(String id)throws IOException{
        List<SearchObjList> searchObjList_list = planLandSearchServiceImpl.webService_list;
        LinkedHashMap<String, String> map = new LinkedHashMap();
        searchObjList_list.stream().filter(u -> u.getId().equals(id)).forEach(u -> {
            map.put("NAME", u.getName());
            map.put("ADDRESS", u.getAddress());
        });
        Map return_map;
        InputStream inputStream = ESOperate.class.getResourceAsStream("/tip.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream, "UTF-8"));

        String tableKey = properties.getProperty(ESOperate.dbName + "." + "AddressResolution" + "Key");
        String tableValue = properties.getProperty(ESOperate.dbName + "." + "AddressResolution" + "Value").toUpperCase();
        String HasDetail = "false";
        Boolean hasDetailInfo = Boolean.valueOf(HasDetail);
        String[] key = tableKey.split(","); //注意分隔符是需要转译滴...
        String[] value = tableValue.split(",");

        List result = urbanMapReturnUtil.tipContent(key, value, map);
        return_map = Tooltip.toolTipListToMap(result, hasDetailInfo);
        return_map.put("info_alert", "0");
        return return_map;
    }

}
