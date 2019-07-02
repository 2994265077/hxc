package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.core.tools.PropertiesLoadUtils;
import com.cetccity.operationcenter.webframework.hiddendanger.api.MapConfigApi;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.MapConfig;
import com.cetccity.operationcenter.webframework.core.tools.XsteamUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TopGroup;
import com.cetccity.operationcenter.webframework.web.util.Constant;
import com.cetccity.operationcenter.webframework.web.util.apollo.ApolloPropertiesLoadUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MapConfigController implements MapConfigApi {

    @Autowired
    PropertiesLoadUtils propertiesLoadUtils;

    @Override
    public MapConfig loadXmlOfMapConfig() throws Exception{
        // XML转为Java对象
        MapConfig mapConfig =  (MapConfig) XsteamUtil.toBean(MapConfig.class, ApolloPropertiesLoadUtils.readApollo(Constant.MAP_CONFIG));
        return mapConfig;
    }

    @Override
    public MapConfig loadXmlOfMapConfigWangxi() throws Exception{
        // XML转为Java对象
        MapConfig mapConfig =  (MapConfig)XsteamUtil.toBean(MapConfig.class, ApolloPropertiesLoadUtils.readApollo(Constant.MAP_CONFIG));
        List<TopGroup> topGroupList = mapConfig.getTopGroupList();
        String Name[] = {"安监类图层","消防类图层","地质类图层","物联监测","城区风险评估","生态环境","环境治理","视频监测","应急资源类"};
        List<TopGroup> topGroupList_return = new ArrayList<TopGroup>();
        for (TopGroup topGroup:topGroupList) {
            for (int i = 0;i<Name.length;i++) {
                if(Name[i].equals(topGroup.getLabel())){
                    topGroupList_return.add(topGroup);
                }
            }
        }
        MapConfig mapConfig_return = new MapConfig();
        mapConfig_return.setLabel(mapConfig.getLabel());
        mapConfig_return.setTopGroupList(topGroupList_return);
        return mapConfig_return;
    }

    public MapConfig loadXmlOfMapConfigNearly() throws Exception{
        // XML转为Java对象
        MapConfig mapConfig =  (MapConfig) XsteamUtil.toBean(MapConfig.class, ApolloPropertiesLoadUtils.readApollo(Constant.NEARLY_SEARCH_MAP_CONFIG));
        return mapConfig;
    }

    public Resource loadXmlOfMapConfig_xml() throws Exception{
        return ApolloPropertiesLoadUtils.readApolloToResource(Constant.MAP_CONFIG);
    }
}
