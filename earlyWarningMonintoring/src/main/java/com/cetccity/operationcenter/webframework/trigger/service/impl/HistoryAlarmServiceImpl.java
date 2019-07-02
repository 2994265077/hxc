package com.cetccity.operationcenter.webframework.trigger.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.ALARM_INFO_CODEMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFORMATION;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFO_CODE;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_STATISTIC;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_INFORMATIONService;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_STATISTICService;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.core.tools.ObjectIdIncrement;
import com.cetccity.operationcenter.webframework.web.util.UuIdGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.trigger.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:43 2019-04-01
 * Updater:     heliangming
 * Update_Date：16:43 2019-04-01
 * 更新描述:    heliangming 补充
 **/
@Service
@Slf4j
public class HistoryAlarmServiceImpl {

    @Autowired
    ALARM_INFO_CODEMapper aLARM_INFO_CODEMapper;

    @Autowired
    ALARM_STATISTICService aLARM_STATISTICService;

    @Autowired
    ALARM_INFORMATIONService aLARM_INFORMATIONService;

    @Autowired
    ObjectIdIncrement objectIdIncrement;

    public void schedule() {
        ALARM_INFO_CODE aLARM_INFO_CODE = new ALARM_INFO_CODE();
        List<ALARM_INFO_CODE> aLARM_INFO_CODE_list = aLARM_INFO_CODEMapper.getALARM_INFO_CODE(aLARM_INFO_CODE);
        String date = LoadMyUtil.getMyTime("DATE",0);
        ALARM_STATISTIC aLARM_STATISTIC_find = new ALARM_STATISTIC();
        aLARM_STATISTIC_find.setTIME(date);
        List<ALARM_STATISTIC> aLARM_STATISTIC_find_list = aLARM_STATISTICService.getALARM_STATISTIC(aLARM_STATISTIC_find);
        log.info("-----aLARM_INFO_CODE_list-----");
        if(aLARM_STATISTIC_find_list.isEmpty()) {
            for (ALARM_INFO_CODE aLARM_INFO_CODE_return:aLARM_INFO_CODE_list) {
                ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
                aLARM_INFORMATION.setALARM_TYPE_LV2(aLARM_INFO_CODE_return.getLV_2());
                aLARM_INFORMATION.setALARM_STATE(1);//1:正在预警中;0:预警已结束
                //aLARM_INFORMATION.setSEND_STATE(1);
                List<ALARM_INFORMATION> aLARM_INFORMATION_list = aLARM_INFORMATIONService.getALARM_INFORMATION(aLARM_INFORMATION);
                ALARM_STATISTIC aLARM_STATISTIC = new ALARM_STATISTIC();
                aLARM_STATISTIC.setUUID(UuIdGeneratorUtil.getCetcCloudUuid(aLARM_INFO_CODE_return.getLV_2()));
                aLARM_STATISTIC.setTIME(date);
                aLARM_STATISTIC.setLV_1_NAME(aLARM_INFO_CODE_return.getLV_1_NAME());
                aLARM_STATISTIC.setLV_2_NAME(aLARM_INFO_CODE_return.getLV_2_NAME());
                aLARM_STATISTIC.setALARM_TYPE_LV1(aLARM_INFO_CODE_return.getLV_1());
                aLARM_STATISTIC.setALARM_TYPE_LV2(aLARM_INFO_CODE_return.getLV_2());
                aLARM_STATISTIC.setALARM_NUM(String.valueOf(aLARM_INFORMATION_list.size()));
                aLARM_STATISTIC.setCANCEL_ALARM("0");
                int objectId = objectIdIncrement.increment("ALARM_STATISTIC");
                aLARM_STATISTIC.setOBJECT_ID(objectId);
                aLARM_STATISTICService.insertALARM_STATISTIC(aLARM_STATISTIC);
            }
        }else{
            log.info("--已有当天预警数量统计数据，无需重复添加");
        }
    }
}
