package com.cetccity.operationcenter.webframework.rundisplay.controller;

import com.cetccity.operationcenter.webframework.core.tools.XsteamUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.MapConfig;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TopGroup;
import com.cetccity.operationcenter.webframework.rundisplay.api.RunDisplayApi;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.City;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.CityList;
import com.cetccity.operationcenter.webframework.web.util.Constant;
import com.cetccity.operationcenter.webframework.web.util.apollo.ApolloPropertiesLoadUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class RunDisplayController implements RunDisplayApi {

    @Override
    public List<City> loadXmlOfTest() throws Exception{
        // 读取XML文件
        Resource resource = new ClassPathResource("city.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) !=null) {
            buffer.append(line);
        }
        br.close();
        // XML转为Java对象
        CityList cityList = (CityList) XsteamUtil.toBean(CityList.class, buffer.toString());
        return cityList.getCityList();
    }

    @Override
    public MapConfig loadXmlOfFunction() throws Exception{
        // 读取XML文件
        // XML转为Java对象RUN_DISPLAY_MAP_CONFIG
        MapConfig mapConfig =  (MapConfig)XsteamUtil.toBean(MapConfig.class, ApolloPropertiesLoadUtils.readApollo(Constant.RUN_DISPLAY_MAP_CONFIG));
        List<TopGroup> topGroupList = mapConfig.getTopGroupList();
        String Name[] = {"城市部件","公共安全","民生服务","宏观经济","生态环境"};
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

}
