package com.cetccity.operationcenter.webframework.environment.util;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.KeyValueHasDetailModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_FACILITY_INFOMapper;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_SEWERAGE_INFOMapper;
import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_SEWERAGE_INFO;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipContentUtil;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.util
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:36 2019-05-23
 * Updater:     heliangming
 * Update_Date：9:36 2019-05-23
 * 更新描述:    heliangming 补充
 **/
@Component
public class EnvironmentToolTipUtil {

    @Autowired
    QJHH_SEWERAGE_INFOMapper qJHH_SEWERAGE_INFOMapper;

    @Autowired
    QJHH_FACILITY_INFOMapper qJHH_FACILITY_INFOMapper;

    public Map summaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String id) {
        String tableName;
        Map return_map;
        KeyValueHasDetailModel u = TipContentUtil.getTipProperties(tableNameUrl);
        if(tableNameUrl.contains("@")) {
            tableName = tableNameUrl.substring(0, tableNameUrl.indexOf("@"));
        }else {
            tableName = tableNameUrl;
        }
        LinkedHashMap<String,String> map = loadMapTip(tableName,id);  //请求oracle
        List result = TipContentUtil.tipContent(tableName,u.getKey(),u.getValue(),map);
        return_map = Tooltip.toolTipListToMap(result, u.getHasDetailInfo());
        return_map.put("info_alert","0");
        return return_map;
    }

    public LinkedHashMap<String,String> loadMapTip(String tableName,String id){
        List<LinkedHashMap> list = new ArrayList<>();
        switch(tableName) {
            case ("QJHH_SEWERAGE_INFO"): list = qJHH_SEWERAGE_INFOMapper.getTip(id); break;
            case ("QJHH_FACILITY_INFO"): list = qJHH_FACILITY_INFOMapper.getTip(id); break;
        }
        if(list.size()!=0){
            return list.get(0);
        }else {
            return null;
        }
    }
}
