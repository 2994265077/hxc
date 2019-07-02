/*
package com.cetccity.operationcenter.webframework.hiddendanger.controller.map;

import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.map.HiddendangerIndexMapApi;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.web.model.incident.NearbyResourcesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HiddendangerIndexMapController implements HiddendangerIndexMapApi {

    @Autowired
    ALL_LoadMapController aLL_LoadMapController;

    public List<NearbyResourcesModel> findIconTypeByDB(String street) throws IOException{
       */
/* "物联网-烟雾传感器--IOT_DEVICE@0003  \n"+
                "物联网-电气火灾监测设备--IOT_DEVICE@0024  \n"+
                "物联监测--气体传感器--IOT_DEVICE@0021  \n"+
                "物联监测--地质灾害监测设备--IOT_DEVICE@0023  \n"+
                "物联网-消防栓水压传感器--IOT_DEVICE@0025  \n"+
                "物联网-内涝监测设备--易涝点--水位传感器--IOT_DEVICE@0026  \n"+
                "安监-三小场所(小作坊PLACE_TYPE=60022)--BLK_SANXIAO_PLACE@60022  \n" +
                "安监-三小场所(小档口PLACE_TYPE=60021)--BLK_SANXIAO_PLACE@60021  \n" +
                "安监-三小场所(小娱乐场所PLACE_TYPE=60023)--BLK_SANXIAO_PLACE@60023  \n"*//*

        List<NearbyResourcesModel> nearbyResourcesModel_list = new ArrayList<>();
        String tableName[] = {"BLK_SANXIAO_PLACE@60021","BLK_SANXIAO_PLACE@60022","BLK_SANXIAO_PLACE@60023",
        "IOT_DEVICE@0021","IOT_DEVICE@0023","IOT_DEVICE@0024","IOT_DEVICE@0025","IOT_DEVICE@0026"};
        for (int i = 0;i<tableName.length;i++) {
            List<IconTypeLoadMap> loadMap_list = aLL_LoadMapController.findIconTypeByDB(tableName[i],street,null,null,null,null);
            List<IconTypeLoadMap> save = new ArrayList<>();
            for (IconTypeLoadMap iconTypeLoadMap:loadMap_list) {
                if("1".equals(iconTypeLoadMap.getIconType())){
                    save.add(iconTypeLoadMap);
                }
            }
            if(!save.isEmpty()) {
                NearbyResourcesModel nearbyResourcesModel = new NearbyResourcesModel();
                nearbyResourcesModel.setObj_Name(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", tableName[i]));
                nearbyResourcesModel.setTableName(tableName[i]);
                nearbyResourcesModel.setLayerid(save.get(0).getLayerid());
                nearbyResourcesModel.setCount(save.size());
                nearbyResourcesModel.setData(save);
                nearbyResourcesModel_list.add(nearbyResourcesModel);
            }
        }
        return nearbyResourcesModel_list;
    }

}
*/
