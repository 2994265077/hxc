package com.cetccity.operationcenter.webframework.hiddendanger.controller.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.KeyValueHasDetailModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.hiddendanger.api.map.TooltipApi;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.IotVauleData;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.hiddendanger.service.IotDetailsService;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipContentUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotEventMapper;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.IOT_EVENT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Component
@RestController
@Slf4j
public class TooltipController implements TooltipApi {

    Logger logger = LoggerFactory.getLogger(TooltipController.class);

    @Autowired
    TipUtil tipUtil;

    @Autowired
    IotEventMapper iOT_EVENTMapper;

    @Autowired
    IotDetailsService iotDetailsService;

    public Map summaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String id) {
        String tableName;
        Map return_map;
        KeyValueHasDetailModel u = TipContentUtil.getTipProperties(tableNameUrl);
        if(tableNameUrl.contains("@")){
            tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
        }else {
            tableName = tableNameUrl;
        }
        LinkedHashMap<String,String> map = tipUtil.loadMapTipToOracle(tableName,id);  //请求oracle
        List result = TipContentUtil.tipContent(tableName,u.getKey(),u.getValue(),map);
        return_map = Tooltip.toolTipListToMap(result, u.getHasDetailInfo());
        return_map.put("info_alert","0");
        return return_map;

    }

    public Map summaryInfoOfIconType(@PathVariable("tableNameUrl") String tableNameUrl, String id) {
        String tableName;
        Map return_map;
        KeyValueHasDetailModel u = TipContentUtil.getTipProperties(tableNameUrl);
        if(tableNameUrl.contains("@")){
            tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
        }else {
            tableName = tableNameUrl;
        }
        LinkedHashMap<String,String> map = tipUtil.loadMapTipToOracle(tableName, id);  //请求oracle
        List result = TipContentUtil.tipContent(tableName,u.getKey(),u.getValue(),map);
        return_map = Tooltip.toolTipListToMap(result, u.getHasDetailInfo());
        return_map.put("info_alert","0");
        return return_map;

    }

    public Map iotSummaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String id) {
        String tableName;
        boolean hasDetailInfo;
        Map return_map;
        try {
            String info_alert = "0";
            hasDetailInfo = false;
            List<LinkedHashMap> map_value_list = iotDetailsService.findIndexDetectionDetails(id);
            for(LinkedHashMap map_alarm:map_value_list) {
                if (map_alarm.get("alarm").equals(true)){
                    info_alert = "1";
                    //hasDetailInfo = true;
                    hasDetailInfo = false;
                    break;
                }
            }
            logger.info("TipOf-->"+tableNameUrl+"       "+"device_code-->"+id);
            String[] key = LoadMyUtil.getPropertiesVauleOfKey("tip.properties",ESOperate.dbName + "." + tableNameUrl + "Key").split(",");
            String[] value = LoadMyUtil.getPropertiesVauleOfKey("tip.properties",ESOperate.dbName + "." + tableNameUrl + "Value").split(",");
            if(tableNameUrl.contains("@")){
                tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
            }else {
                tableName = tableNameUrl;
            }
            LinkedHashMap<String,String> map = tipUtil.loadMapIotTip(id);
            List result = TipContentUtil.tipContent(tableName,key,value,map);
            for(LinkedHashMap<String,String> map_value:map_value_list){
                result.add("<hr/>"+map_value.get("DataName")+"值");
                result.add("<span style='color:white'>"+map_value.get("Value")+" "+map_value.get("unit")+"</span>");
            }
            return_map = Tooltip.toolTipListToMap(result, hasDetailInfo);
            return_map.put("info_alert",info_alert);
            return return_map;
        }catch (Exception e){
            log.error(e.toString());
            return null;
        }
    }

    public Map iot0026SummaryInfo(String id){
        String tableNameUrl = "IOT_DEVICE@0026";
        String tableName;
        String vaule="0";
        boolean hasDetailInfo;
        Map return_map = new HashMap();
        try {
            String info_alert = "0";
            hasDetailInfo = false;
            List<LinkedHashMap> map_value_list = iotDetailsService.findIndexDetectionDetails(id);
            for(LinkedHashMap map_alarm:map_value_list) {
                if (map_alarm.get("alarm").equals(true)){
                    info_alert = "1";
                    hasDetailInfo = true;
                    break;
                }
            }
            logger.info("TipOf-->"+tableNameUrl+"       "+"device_code-->"+id);
            String[] key = LoadMyUtil.getPropertiesVauleOfKey("tip.properties",ESOperate.dbName + "." + tableNameUrl + "Key").split(",");
            String[] value = LoadMyUtil.getPropertiesVauleOfKey("tip.properties",ESOperate.dbName + "." + tableNameUrl + "Value").split(",");

            tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));

            LinkedHashMap<String,String> map = tipUtil.loadMapIotTip(id);
            List result = TipContentUtil.tipContent(tableName,key,value,map);
            for(LinkedHashMap<String,String> map_value:map_value_list){
                result.add("<hr/>"+map_value.get("DataName")+"值");
                result.add("<span style='color:white'>"+map_value.get("Value")+" "+map_value.get("unit")+"</span>");
                vaule = map_value.get("Value");
            }
            return_map = Tooltip.toolTipListToMap(result, hasDetailInfo);
            return_map.put("info_alert",info_alert);
        }catch (Exception e){
            log.error(e.toString());
            vaule = "0";
        }
        IotVauleData iotVauleData = tipUtil.iotThresholdTip(id,vaule);
        return_map.put("waterAttribute",iotVauleData);
        PageHelper.startPage(1, 5);
        IOT_EVENT iOT_EVENT = new IOT_EVENT();
        iOT_EVENT.setDEVICE_CODE(id);
        List<IOT_EVENT> iOT_EVENT_list = iOT_EVENTMapper.pageInfoIOT_EVENT(iOT_EVENT);
        PageInfo<IOT_EVENT> pageInfo = new PageInfo(iOT_EVENT_list);
        return_map.put("pageInfo",pageInfo.getList());
        return return_map;
    }

    public Map buildSummaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String buildid) {
        String tableName;
        Map return_map;
        try {
            InputStream inputStream =ESOperate.class.getResourceAsStream("/tip.properties");
            Properties properties = new Properties();
            properties.load(new InputStreamReader(inputStream,"UTF-8"));
            logger.info("TipOf-->"+tableNameUrl);
            String tableKey = properties.getProperty(ESOperate.dbName + "." + tableNameUrl + "Key");
            String tableValue = properties.getProperty(ESOperate.dbName + "." + tableNameUrl + "Value");
            String[] key = tableKey.split(","); //注意分隔符是需要转译滴...
            String[] value = tableValue.split(",");
            logger.info("TipOfKey-->" + tableKey);
            logger.info("TipOfValue-->" + tableValue);
            if(tableNameUrl.contains("@")){
                tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
            }else {
                tableName = tableNameUrl;
            }
            LinkedHashMap<String,String> map = tipUtil.loadMapBuildTip(tableName, buildid);

            List result = TipContentUtil.tipContent(tableName,key,value,map);

            return_map = Tooltip.toolTipListToMap(result, true);
            return_map.put("info_alert","0");
            return return_map;
        }catch (Exception e){
            log.error(e.toString());
            return null;
        }
    }

    public Map chartSummaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String id) {
        String tableName;
        Map return_map;
        KeyValueHasDetailModel u = TipContentUtil.getTipProperties(tableNameUrl);
        if(tableNameUrl.contains("@")){
            tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
        }else {
            tableName = tableNameUrl;
        }
        LinkedHashMap<String,String> map = tipUtil.loadMapTipToOracle(tableName, id);  //请求oracle
        List result = TipContentUtil.tipContent(tableName,u.getKey(),u.getValue(),map);
        return_map = Tooltip.toolTipListToMap(result, true);
        return_map.put("info_alert","0");
        NameDataModel nameDataModel = tipUtil.tipOfChart(tableName,id);
        return_map.put("chart",nameDataModel);
        return return_map;
    }
}
