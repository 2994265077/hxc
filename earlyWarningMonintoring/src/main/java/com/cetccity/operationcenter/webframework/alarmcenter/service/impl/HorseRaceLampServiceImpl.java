package com.cetccity.operationcenter.webframework.alarmcenter.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.AlarmInformationMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFORMATION;
import com.cetccity.operationcenter.webframework.alarmcenter.service.HorseRaceLampService;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:46 2019-03-22
 * Updater:     heliangming
 * Update_Date：16:46 2019-03-22
 * 更新描述:    heliangming 补充
 **/
@Service
public class HorseRaceLampServiceImpl implements HorseRaceLampService {

    @Autowired
    AlarmInformationMapper aLARM_INFORMATIONMapper;

    public List<LoadMap> findLamp(){
        ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
        aLARM_INFORMATION.setALARM_STATE(1);//1:正在预警中;0:预警已结束
        aLARM_INFORMATION.setSEND_STATE(1);
        List<ALARM_INFORMATION> aLARM_INFORMATION_list = aLARM_INFORMATIONMapper.getALARM_INFORMATION(aLARM_INFORMATION);
        List<LoadMap> loadMap_list = new ArrayList<LoadMap>();
        aLARM_INFORMATION_list.stream().forEach(u->{
            String layerid = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties","31project_april." + u.getALARM_TYPE_LV2());
            LoadMap loadMap = LoadMap.builder().tableName("ALARM_INFORMATION").layerid(layerid)
                    .id(u.getID()).jd(u.getJD84()).wd(u.getWD84()).build();
            loadMap_list.add(loadMap);
        });
        return loadMap_list;
    }
}
