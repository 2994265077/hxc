package com.cetccity.operationcenter.webframework.hiddendanger.tools.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.SanXiaoPlaceLoadMap;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.SanXiaoPlaceMapMapper;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:22 2019-04-08
 * Updater:     heliangming
 * Update_Date：9:22 2019-04-08
 * 更新描述:    heliangming 补充
 **/
@Component
public class SanXiaoPlaceMapUtil {

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    SanXiaoPlaceMapMapper sanXiaoPlaceMapMapper;

    public List<SanXiaoPlaceLoadMap> sanXiaoLoadMapBySanXiaoPlace(String tableName, String layerid, String Street, String id){
        List<SanXiaoPlaceLoadMap> sanXiaoPlaceLoadMap_list = new ArrayList<>();
        String sql;
        if(StringUtils.isNotEmpty(Street)) {
            String StreetCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties", Street).split(",")[0];
            sql = "select ROWID,JD84,WD84,LDDM from " + tableName + " where REGION_CODE = '440304' and STREET_CODE='" + StreetCode + "'";
        }else if(id != null){
            sql = "select ROWID,JD84,WD84,LDDM from " + tableName + " where ROWID = '"+id+"'";
        }else{
            sql = "select ROWID,JD84,WD84,LDDM from " + tableName + " where REGION_CODE = '440304'";
        }
        List<LinkedHashMap> map_list =  oracleOperateService.querySql(sql);

        for (LinkedHashMap u:map_list) {
            if("".equals(u.get("JD84"))||u.get("JD84")==null){
                continue;
            }
            if("".equals(u.get("WD84"))||u.get("WD84")==null){
                continue;
            }
            SanXiaoPlaceLoadMap sanXiaoPlaceLoadMap = new SanXiaoPlaceLoadMap();
            if("".equals(u.get("LDDM"))||u.get("LDDM")==null){
                sanXiaoPlaceLoadMap.setHasBuildId("-1");
            }else{
                sanXiaoPlaceLoadMap.setHasBuildId("1");
            }
            sanXiaoPlaceLoadMap.setTableName(tableName);
            sanXiaoPlaceLoadMap.setLayerid(layerid);
            sanXiaoPlaceLoadMap.setId((String) u.get("ROWID"));
            sanXiaoPlaceLoadMap.setJd((String)u.get("JD84"));
            sanXiaoPlaceLoadMap.setWd((String)u.get("WD84"));
            sanXiaoPlaceLoadMap_list.add(sanXiaoPlaceLoadMap);
        }
        return sanXiaoPlaceLoadMap_list;
    }

    public List<IconTypeLoadMap> sanXiaoLoadMapByDBAlarm(String tableName,String layerid,String column,String columnEntity,String StreetCode,String startTime,String endTime){
        tableName = tableName.toUpperCase();
        column = column.toUpperCase();
        List<IconTypeLoadMap> iconTypeLoadMap_list = new ArrayList<IconTypeLoadMap>();
        Map map = new HashMap();
        map.put("StreetCode",StreetCode);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put(column,columnEntity);
        List<HashMap> list = sanXiaoPlaceMapMapper.loadMapByDBAlarm(map);
        for(HashMap u:list) {
            iconTypeLoadMap_list.add(LoadMapAttribute.geticonTypeLoadMap(tableName, (String)u.get("ID"), (String)u.get("JD84"), (String)u.get("WD84"), layerid, "1"));
        }
        return iconTypeLoadMap_list;
    }
}
