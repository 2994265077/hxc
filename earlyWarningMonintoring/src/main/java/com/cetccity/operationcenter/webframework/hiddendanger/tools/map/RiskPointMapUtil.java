package com.cetccity.operationcenter.webframework.hiddendanger.tools.map;

import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:11 2019-03-29
 * Updater:     heliangming
 * Update_Date：15:11 2019-03-29
 * 更新描述:    heliangming 补充
 **/
@Component
public class RiskPointMapUtil {

    @Autowired
    OracleOperateService oracleOperateService;

    public List<IconTypeLoadMap> iconTypeLoadMapByRiskPoint(String tableName,String layerid,String Street,String id){
        List<IconTypeLoadMap> iconTypeLoadMap_list = new ArrayList<>();
        String sql;
        if(Street==null)Street="";
        if(!"".equals(Street)) {
            String StreetCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties", Street).split(",")[0];
            sql = "select ROWID,JD84,WD84,DANGER_LEVEL from " + tableName + " where REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        }else if(id != null){
            sql = "select ROWID,JD84,WD84,DANGER_LEVEL from " + tableName + " where ROWID = '"+id+"'";
        }else{
            sql = "select ROWID,JD84,WD84,DANGER_LEVEL from " + tableName + " where REGION_CODE = '440304'";
        }
        List<LinkedHashMap> map_list =  oracleOperateService.querySql(sql);
        Map<String,String> map_level = new HashMap();
        map_level.put("红","0");map_level.put("橙","1");
        map_level.put("黄","2");map_level.put("蓝","3");
        for (LinkedHashMap u:map_list) {
            if("".equals(u.get("DANGER_LEVEL"))||u.get("DANGER_LEVEL")==null){
                continue;
            }
            if("".equals(u.get("JD84"))||u.get("JD84")==null){
                continue;
            }
            if("".equals(u.get("WD84"))||u.get("WD84")==null){
                continue;
            }
            IconTypeLoadMap iconTypeLoadMap = new IconTypeLoadMap();
            iconTypeLoadMap.setTableName(tableName);
            iconTypeLoadMap.setLayerid(layerid);
            iconTypeLoadMap.setId((String) u.get("ROWID"));
            iconTypeLoadMap.setJd((String)u.get("JD84"));
            iconTypeLoadMap.setWd((String)u.get("WD84"));
            iconTypeLoadMap.setIconType(map_level.get(u.get("DANGER_LEVEL")));
            iconTypeLoadMap_list.add(iconTypeLoadMap);
        }
        return iconTypeLoadMap_list;
    }
}
