package com.cetccity.operationcenter.webframework.alarmcenter.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.LAB_CITYMANAGE_EVENTMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.LAB_CITYMANAGE_EVENT;
import com.cetccity.operationcenter.webframework.alarmcenter.service.LabCityManageEventService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.VIDEO_POLICEMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.VIDEO_POLICE;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.web.util.UuIdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:33 2019-07-10
 * Updater:     heliangming
 * Update_Date：14:33 2019-07-10
 * 更新描述:    heliangming 补充
 **/
@Service
public class LabCityManageEventServiceImpl implements LabCityManageEventService {

    @Autowired
    AlarmInformationV1Mapper alarmInformationV1Mapper;

    @Autowired
    LAB_CITYMANAGE_EVENTMapper lAB_CITYMANAGE_EVENTMapper;

    @Autowired
    VIDEO_POLICEMapper vIDEO_POLICEMapper;

    @Transactional
    public HttpResponseModel<Integer> receiveLabCityManageEvent(LAB_CITYMANAGE_EVENT labCitymanageEvent){
        Long id = lAB_CITYMANAGE_EVENTMapper.getIncrementId();
        labCitymanageEvent.setObjectId(id);
        //生成报警
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = labCitymanageEvent.getLogTime().atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        //获取摄像头经纬度

        String CameraId = labCitymanageEvent.getCameraId();
        VIDEO_POLICE videoPolice = new VIDEO_POLICE();
        VIDEO_POLICE video = new VIDEO_POLICE();
        videoPolice.setCAMERAID(CameraId);
        List<VIDEO_POLICE> videoPoliceList = vIDEO_POLICEMapper.getVIDEO_POLICE(videoPolice);
        if(videoPoliceList.size() == 0) {
            return new HttpResponseModel(201,"无法获取到摄像头经纬度，报警失败",-1);
        }else{
            video = videoPoliceList.get(0);
        }
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setOBJECT_ID(alarmInformationV1Mapper.getSequenceId());
        alarmInformation.setF_ROW_ID(Double.valueOf(id));
        alarmInformation.setALARM_LEVEL("三级-黄");
        alarmInformation.setRELEASE_TIME(date);
        alarmInformation.setALARM_STATE(1);
        alarmInformation.setALARM_TYPE_LV1("006");
        alarmInformation.setALARM_TYPE_LV2("006003");
        alarmInformation.setORIGIN_TABLE_NAME("LAB_CITYMANAGE_EVENT");
        alarmInformation.setSEND_STATE(0);
        alarmInformation.setDISPOSAL_STATE(0);
        alarmInformation.setCONTENTS(video.getNAME()+"发生了"+geteventType(labCitymanageEvent.getEventType())+"报警");
        alarmInformation.setSYSTEM_ID(UuIdGeneratorUtil.getCetcCloudUuid("006003"));
        alarmInformation.setRELEASE_PERSON("实验室");
        alarmInformation.setALARM_OBJECT("视频监测报警");
        alarmInformation.setJD84(video.getJD84());
        alarmInformation.setWD84(video.getWD84());
        alarmInformation.setREGION_CODE(video.getREGION_CODE());
        alarmInformation.setSTREET_CODE(video.getSTREET_CODE());
        alarmInformation.setCOMMUNITY_CODE(video.getCOMMUNITY_CODE());
        alarmInformation.setADDRESS(video.getNAME());
        alarmInformationV1Mapper.insert(alarmInformation);
        return new HttpResponseModel(200,"SUCCESS",lAB_CITYMANAGE_EVENTMapper.receiveLabCityManageEvent(labCitymanageEvent));
    }


    public String geteventType(int type) {
        String eventType = "未知类型";
        switch (type){
            case 0 : eventType = "占道经营"; break;
            case 1 : eventType = "违规撑伞"; break;
            case 2 : eventType = "建筑垃圾"; break;
            case 3 : eventType = "垃圾满溢"; break;
            case 4 : eventType = "垃圾打包"; break;
            case 5 : eventType = "行人倒地"; break;
        }
        return eventType;
    }
}
