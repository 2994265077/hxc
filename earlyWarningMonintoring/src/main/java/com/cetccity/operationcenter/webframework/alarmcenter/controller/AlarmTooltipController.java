package com.cetccity.operationcenter.webframework.alarmcenter.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.api.AlarmTooltipApi;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.LAB_CITYMANAGE_EVENTMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.LAB_CITYMANAGE_EVENT;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.hiddendanger.controller.map.TooltipController;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipContentUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@RestController
@Slf4j
public class AlarmTooltipController implements AlarmTooltipApi {

    @Autowired
    TooltipController tooltipController;

    @Autowired
    TipUtil tipUtil;

    @Autowired
    LAB_CITYMANAGE_EVENTMapper lAB_CITYMANAGE_EVENTMapper;

    public Map summaryInfo(@PathVariable("LV_2") String LV_2, String id) {
        String tableName = "ALARM_INFORMATION";
        Map return_map;
        try {
            InputStream inputStream =ESOperate.class.getResourceAsStream("/tip.properties");
            Properties properties = new Properties();
            properties.load(new InputStreamReader(inputStream,"UTF-8"));
            String tableKey = properties.getProperty(ESOperate.dbName + "." + tableName + "Key");
            String tableValue = properties.getProperty(ESOperate.dbName + "." + tableName + "Value").toUpperCase();
            String HasDetail = properties.getProperty(ESOperate.dbName + "." + tableName + "HasDetail");
            Boolean hasDetailInfo = Boolean.valueOf(HasDetail);
            String[] key = tableKey.split(","); //注意分隔符是需要转译滴...
            String[] value = tableValue.split(",");

            LinkedHashMap<String,String> map = tipUtil.loadMapTipToOracle(tableName,id);  //请求oracle
            map.put("RELEASE_TIME",map.get("RELEASE_TIME").substring(0,19));
            List result = TipContentUtil.tipContent(tableName,key,value,map);
            if(LV_2.equals("006003")){
                String objId = (String) result.get(result.size()-1);
                LAB_CITYMANAGE_EVENT labCitymanageEvent = lAB_CITYMANAGE_EVENTMapper.getList(objId).get(0);
                result.add("<a target=\"_blank\" href=\""+labCitymanageEvent.getImageUrl()+"\" style=\"color:#5fb6ff\">查看图片</a>");
                result.add("<a target=\"_blank\" href=\""+labCitymanageEvent.getVideoUrl()+"\" style=\"color:#a2d0ec\">视频回放</a>");
            }
            return_map = Tooltip.toolTipListToMap(result, hasDetailInfo);
            return_map.put("info_alert","0");
            return_map.put("jump",jumpState(LV_2));
            return_map.put("object_id", map.get("OBJECT_ID"));
            return return_map;
        }catch (Exception e){
        	log.error(e.toString());
            return null;
        }
    }

    public boolean jumpState(String LV_2){
        Boolean res;
        if("005003".equals(LV_2)){
            res = true;
        }else{
            res = false;
        }
        return res;
    }
}
