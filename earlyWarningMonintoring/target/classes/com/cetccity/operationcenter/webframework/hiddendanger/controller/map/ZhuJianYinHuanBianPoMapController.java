package com.cetccity.operationcenter.webframework.hiddendanger.controller.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.KeyValueHasDetailModel;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.hiddendanger.api.map.ZhuJianYinHuanBianPoMapApi;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipContentUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.controller.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 12:03 2019-06-28
 * Updater:     heliangming
 * Update_Date：12:03 2019-06-28
 * 更新描述:    heliangming 补充
 **/
@RestController
public class ZhuJianYinHuanBianPoMapController implements ZhuJianYinHuanBianPoMapApi {

    @Autowired
    TipUtil tipUtil;

    public Map summaryInfo(String tableNameUrl, String id){
        Map return_map;
        KeyValueHasDetailModel u = TipContentUtil.getTipProperties("ZHUJIAN_BIANPO");
        LinkedHashMap<String,String> map = tipUtil.loadMapTipToOracle("ZHUJIAN_BIANPO",id);  //请求oracle
        List result = TipContentUtil.tipContent(tableNameUrl,u.getKey(),u.getValue(),map);
        return_map = Tooltip.toolTipListToMap(result, u.getHasDetailInfo());
        return_map.put("info_alert","0");
        Map mapBianPoDetail = new HashMap();
        mapBianPoDetail.put("jd84",map.get("JD84"));
        mapBianPoDetail.put("wd84",map.get("WD84"));
        mapBianPoDetail.put("radius",map.get("RADIUS"));
        return_map.put("bianPoDetail",mapBianPoDetail);
        return return_map;
    }
}
