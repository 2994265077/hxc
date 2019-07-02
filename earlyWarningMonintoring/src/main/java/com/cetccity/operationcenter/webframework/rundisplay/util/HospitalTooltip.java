package com.cetccity.operationcenter.webframework.rundisplay.util;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.KeyValueHasDetailModel;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipContentUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipUtil;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_ORG_VMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.rundisplay.util
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:43 2019-06-14
 * Updater:     heliangming
 * Update_Date：15:43 2019-06-14
 * 更新描述:    heliangming 补充
 **/
@Component
public class HospitalTooltip {

    @Autowired
    YJJC_QWJJ_ORG_VMapper yJJC_QWJJ_ORG_VMapper;

    public Map summaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String id) {
        String tableName = tableNameUrl;
        Map return_map;
        KeyValueHasDetailModel u = TipContentUtil.getTipProperties(tableNameUrl);
        LinkedHashMap<String,String> map = yJJC_QWJJ_ORG_VMapper.summaryInfo(id);
        List result = TipContentUtil.tipContent(tableName,u.getKey(),u.getValue(),map);
        return_map = Tooltip.toolTipListToMap(result, u.getHasDetailInfo());
        return_map.put("info_alert","0");
        return return_map;
    }
}
