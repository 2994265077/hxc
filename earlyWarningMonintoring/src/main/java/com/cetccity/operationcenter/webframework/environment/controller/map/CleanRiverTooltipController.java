package com.cetccity.operationcenter.webframework.environment.controller.map;

import com.cetccity.operationcenter.webframework.environment.api.map.CleanRiverTooltipApi;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_PATROLMapper;
import com.cetccity.operationcenter.webframework.environment.dao.QJHH_SEWERAGE_INFOMapper;
import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_PATROL;
import com.cetccity.operationcenter.webframework.environment.dao.entity.QJHH_SEWERAGE_INFO;
import com.cetccity.operationcenter.webframework.environment.util.EnvironmentToolTipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.controller.map
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:35 2019-05-21
 * Updater:     heliangming
 * Update_Date：10:35 2019-05-21
 * 更新描述:    heliangming 补充
 **/
@RestController
public class CleanRiverTooltipController implements CleanRiverTooltipApi {

    @Autowired
    EnvironmentToolTipUtil environmentToolTipUtil;

    @Autowired
    QJHH_SEWERAGE_INFOMapper qJHH_SEWERAGE_INFOMapper;

    @Autowired
    QJHH_PATROLMapper qJHH_PATROLMapper;

    public Map summaryInfoToDrainHold(@PathVariable("tableNameUrl") String tableNameUrl, String id){
        return environmentToolTipUtil.summaryInfo(tableNameUrl,id);
    }

    public Map summaryInfoDrainFacilities(@PathVariable("tableNameUrl") String tableNameUrl, String id){
        Map map = environmentToolTipUtil.summaryInfo(tableNameUrl, id);
        //解析排水户 ToDo
        List<ArrayList> list = (List<ArrayList>)map.get("value");
        String drainHoldId = (String) list.get(0).get(1);
        String drainFacilitiesId = (String) list.get(1).get(1);
        List<QJHH_PATROL> patrolList = qJHH_PATROLMapper.getList(QJHH_PATROL.builder().FACILITY_ID(drainFacilitiesId).build());
        if(patrolList.size()==0){
            list.get(1).set(1,"正常");
        }else if("正常".equals(patrolList.get(0).getSTATUS())){
            list.get(1).set(1,"正常");
        }else {
            list.get(1).set(1,"不正常");
        }
        QJHH_SEWERAGE_INFO qJHH_SEWERAGE_INFO = qJHH_SEWERAGE_INFOMapper.getList(QJHH_SEWERAGE_INFO.builder().UID(drainHoldId).build()).get(0);
        list.get(0).set(1,qJHH_SEWERAGE_INFO.getNAME());
        map.put("title",qJHH_SEWERAGE_INFO.getNAME());
        return map;
    }

}
