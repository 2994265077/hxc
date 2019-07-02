package com.cetccity.operationcenter.webframework.environment.util;

import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.core.tools.PropertiesLoadUtils;
import com.cetccity.operationcenter.webframework.core.tools.XsteamUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.MapConfig;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TopGroup;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TopGroupTwo;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.LoadMapAttribute;
import com.cetccity.operationcenter.webframework.web.util.Constant;
import com.ctrip.framework.apollo.ConfigFile;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.util
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:20 2019-05-22
 * Updater:     heliangming
 * Update_Date：18:20 2019-05-22
 * 更新描述:    heliangming 补充
 **/
@Component
public class DrainMenuUtil {

    @Autowired
    PropertiesLoadUtils propertiesLoadUtils;

    public List<NameValueUnitModel> drainHoldMenu(){
        List<NameValueUnitModel> nameValueUnitModelList = new ArrayList<>();
        List<TopGroupTwo> list;
        try {
            list = loadXmlToMenuList().getTopGroupList().get(0).getTopGroupTwoList();
        }catch (Exception e){
            throw CetcCommonException.defaultException("读取文件中的group标签label=排水设施失败！");
        }
        list.stream().filter(u->"排水户".equals(u.getLabel())).collect(Collectors.toList()).stream().forEach(
                u->u.getLoadAttributeMapTwoList().stream().forEach(
                        s->nameValueUnitModelList.add(NameValueUnitModel.builder().name(s.getLabel()).value(s.getId()).unit(s.getIcon()).build())));
        return nameValueUnitModelList;
    }

    public List<NameValueUnitModel> drainFacilitiesMenu(){
        List<NameValueUnitModel> nameValueUnitModelList = new ArrayList<>();
        List<TopGroupTwo> list;
        try {
            list = loadXmlToMenuList().getTopGroupList().get(0).getTopGroupTwoList();
        }catch (Exception e){
            throw CetcCommonException.defaultException("读取文件中的group标签label=排水设施失败！");
        }
        list.stream().filter(u->"排水设施".equals(u.getLabel())).collect(Collectors.toList()).stream().forEach(
                u->u.getLoadAttributeMapTwoList().stream().forEach(
                        s->nameValueUnitModelList.add(NameValueUnitModel.builder().name(s.getLabel()).value(s.getId()).unit(s.getIconArray().split(",")[1]).build())));
        return nameValueUnitModelList;
    }

    public MapConfig loadXmlToMenuList() throws Exception{
        // XML转为Java对象
        MapConfig mapConfig =  (MapConfig) XsteamUtil.toBean(MapConfig.class, ConfigService.getConfigFile(Constant.MAP_CONFIG, ConfigFileFormat.XML).getContent());
        List<TopGroup> topGroupList = mapConfig.getTopGroupList();
        String Name[] = {"环境治理"};
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
