package com.cetccity.operationcenter.webframework.alarmcenter.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.AlarmInformationMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.ALARM_INFO_CODEMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFO_CODE;
import com.cetccity.operationcenter.webframework.alarmcenter.service.ALARM_INFORMATIONService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.urbansign.api.model.NameValueDataModel;
import com.cetccity.operationcenter.webframework.web.model.incident.*;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFORMATION;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service("aLARM_INFORMATIONService")
public class ALARM_INFORMATIONServiceImpl implements ALARM_INFORMATIONService {

    @Autowired
    AlarmInformationMapper aLARM_INFORMATIONMapper;

    @Autowired
    ALARM_INFO_CODEMapper aLARM_INFO_CODEMapper;

    @Autowired
    OracleOperateService oracleOperateService;

    public List<ALARM_INFORMATION> getALARM_INFORMATION(ALARM_INFORMATION aLARM_INFORMATION){
        List<ALARM_INFORMATION> aLARM_INFORMATION_list = aLARM_INFORMATIONMapper.getALARM_INFORMATION(aLARM_INFORMATION);
        return aLARM_INFORMATION_list;
    }

    public List<NameValueModel> LeftOne(){
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        String date = LoadMyUtil.getMyTime("DATE",0);
        ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
        aLARM_INFORMATION.setALARM_STATE(1);//1:正在预警中;0:预警已结束
        //aLARM_INFORMATION.setSEND_STATE(1);
        int count = aLARM_INFORMATIONMapper.getCount(aLARM_INFORMATION);
        nameValueModel_list.add(NameValueModel.builder().name("异常事件").value(String.valueOf(count)).build());
        ALARM_INFORMATION aLARM_INFORMATION_today = new ALARM_INFORMATION();
        aLARM_INFORMATION_today.setRELEASE_TIME(date);
        //aLARM_INFORMATION_today.setSEND_STATE(1);
        int count_today = aLARM_INFORMATIONMapper.getCount(aLARM_INFORMATION_today);
        nameValueModel_list.add(NameValueModel.builder().name("今日新增").value(String.valueOf(count_today)).build());
        return nameValueModel_list;
    }

    public List<NameCodeValueModel> LeftTwo(String date)throws IOException {
        List<NameCodeValueModel> nameCodeValueModel_list = new ArrayList<NameCodeValueModel>();
        //String date = LoadMyUtil.getMyTime("DATE",0);
        ALARM_INFO_CODE aLARM_INFO_CODE = new ALARM_INFO_CODE();
        List<ALARM_INFO_CODE> aLARM_INFO_CODE_list = aLARM_INFO_CODEMapper.getRemovalALARM_INFO_CODE(aLARM_INFO_CODE);
        for (ALARM_INFO_CODE aLARM_INFO_CODE_return:aLARM_INFO_CODE_list) {
            ALARM_INFORMATION aLARM_INFORMATION_today = new ALARM_INFORMATION();
            if(StringUtils.isEmpty(date)){
                aLARM_INFORMATION_today.setALARM_STATE(1);//1:正在预警中;0:预警已结束
                //aLARM_INFORMATION_today.setSEND_STATE(1);
            }else{
                aLARM_INFORMATION_today.setRELEASE_TIME(date);
                //aLARM_INFORMATION_today.setSEND_STATE(1);
            }
            aLARM_INFORMATION_today.setALARM_TYPE_LV1(aLARM_INFO_CODE_return.getLV_1());
            long count_today = aLARM_INFORMATIONMapper.getCount(aLARM_INFORMATION_today);
            NameCodeValueModel nameCodeValueModel = new NameCodeValueModel();
            nameCodeValueModel.setName(aLARM_INFO_CODE_return.getLV_1_NAME());
            nameCodeValueModel.setCode(aLARM_INFO_CODE_return.getLV_1());
            nameCodeValueModel.setValue(String.valueOf(count_today));
            nameCodeValueModel.setLayerid(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties","31project_april."+aLARM_INFO_CODE_return.getLV_1()));
            nameCodeValueModel_list.add(nameCodeValueModel);
        }
         return nameCodeValueModel_list;
    }

    public List<AlarmTodayType> LeftThree(String alarm_code,String date){
        List<AlarmTodayType> alarmTodayType_list = new ArrayList<AlarmTodayType>();
        //String date = LoadMyUtil.getMyTime("DATE",0);
        ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
        aLARM_INFORMATION.setALARM_TYPE_LV1(alarm_code);
        String sql = "select LV_1, LV_1_NAME, LV_2, LV_2_NAME  from ALARM_INFO_CODE";
        List<LinkedHashMap> map_list = oracleOperateService.querySql(sql);
        Map<String,String> map_alarm_code = new HashMap();
        for (LinkedHashMap map_alarm:map_list){
            map_alarm_code.put((String) map_alarm.get("LV_2"),(String)map_alarm.get("LV_2_NAME"));
        }
        if(StringUtils.isEmpty(date)){
            aLARM_INFORMATION.setALARM_STATE(1);//1:正在预警中;0:预警已结束
            //aLARM_INFORMATION.setSEND_STATE(1);
        }else{
            aLARM_INFORMATION.setRELEASE_TIME(date);
            //aLARM_INFORMATION.setSEND_STATE(1);
        }
        String sql2 = "select ALARM_TYPE_LV2 from ALARM_PUSH_AVILABLE";
        List<LinkedHashMap> map_list_push = oracleOperateService.querySql(sql2);
        Set set = new HashSet();
        map_list_push.stream().forEach(u->set.add(u.get("ALARM_TYPE_LV2")));
        List<ALARM_INFORMATION> aLARM_INFORMATION_list = aLARM_INFORMATIONMapper.getALARM_INFORMATION(aLARM_INFORMATION);
        aLARM_INFORMATION_list.forEach(u->{
            AlarmTodayType alarmTodayType = new AlarmTodayType();
            alarmTodayType.setId(u.getID());
            alarmTodayType.setLayerid(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName+"."+u.getALARM_TYPE_LV2()));
            alarmTodayType.setCode(u.getALARM_TYPE_LV2());
            alarmTodayType.setType(map_alarm_code.get(u.getALARM_TYPE_LV2()));
            alarmTodayType.setContent(u.getCONTENTS());
            alarmTodayType.setTime(u.getRELEASE_TIME());
            alarmTodayType.setCompany(u.getALARM_OBJECT());
            alarmTodayType.setSystemid(u.getSYSTEM_ID());
            //alarmTodayType.setNeedPush(set.contains(u.getALARM_TYPE_LV2()));
            alarmTodayType.setHasPush(set.contains(u.getALARM_TYPE_LV2()) && 1== (u.getSEND_STATE()==null? 0:u.getSEND_STATE()) ? true : false);
            alarmTodayType.setShowDetail("004003".equals(u.getALARM_TYPE_LV2()));
            alarmTodayType_list.add(alarmTodayType);
        });
        return alarmTodayType_list;
    }

    public List<LinkedHashMap> LeftThreeLoadMap(String alarm_code) throws IOException{
        List<LinkedHashMap> list_month = new ArrayList<LinkedHashMap>();
        for (int i=0;i<30;i++) {
            String date = LoadMyUtil.getMyTime("DATE",-i);
            LinkedHashMap map_month_loadmap_list = new LinkedHashMap();
            List<NearbyResourcesModel> nearbyResourcesModelList = new ArrayList<NearbyResourcesModel>();
            ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
            aLARM_INFORMATION.setALARM_TYPE_LV1(alarm_code);
            aLARM_INFORMATION.setRELEASE_TIME(date);
            List<ALARM_INFORMATION> aLARM_INFORMATION_list = aLARM_INFORMATIONMapper.getALARM_INFORMATION(aLARM_INFORMATION);
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            for (ALARM_INFORMATION aLARM_INFORMATION_return : aLARM_INFORMATION_list) {
                map.put(aLARM_INFORMATION_return.getALARM_TYPE_LV2(), aLARM_INFORMATION_return.getALARM_OBJECT());
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                int count = 0;
                ALARM_INFORMATION aLARM_INFORMATION2 = new ALARM_INFORMATION();
                aLARM_INFORMATION2.setALARM_TYPE_LV2(entry.getKey());
                aLARM_INFORMATION2.setRELEASE_TIME(date);
                List<ALARM_INFORMATION> aLARM_INFORMATION_list2 = aLARM_INFORMATIONMapper.getALARM_INFORMATION(aLARM_INFORMATION2);
                String layerid = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties","31project_april." + aLARM_INFORMATION_list2.get(0).getALARM_TYPE_LV2());
                List<LoadMap> loadMap_list = new ArrayList<LoadMap>();
                for (ALARM_INFORMATION aLARM_INFORMATION3 : aLARM_INFORMATION_list2) {
                    LoadMap loadMap = new LoadMap();
                    loadMap.setTableName("ALARM_INFORMATION");
                    loadMap.setLayerid(layerid);
                    loadMap.setId(aLARM_INFORMATION3.getID());
                    loadMap.setJd(aLARM_INFORMATION3.getJD84());
                    loadMap.setWd(aLARM_INFORMATION3.getWD84());
                    loadMap_list.add(loadMap);
                    count++;
                }
                NearbyResourcesModel nearbyResourcesModel = new NearbyResourcesModel();
                nearbyResourcesModel.setCount(count);
                nearbyResourcesModel.setTableName("ALARM_INFORMATION");
                nearbyResourcesModel.setLayerid(layerid);
                nearbyResourcesModel.setData(loadMap_list);
                nearbyResourcesModelList.add(nearbyResourcesModel);
            }
            map_month_loadmap_list.put(date,nearbyResourcesModelList);
            list_month.add(map_month_loadmap_list);
        }
            return list_month ;
    }

    public List<LoadMap> alarmLoadMapLV2(String alarm_code,String street,String date,String id,String startTime,String endTime){
        List<LoadMap> loadMap_list = new ArrayList<LoadMap>();
        String layerid = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName+"."+alarm_code);
        String streetCode = null;
        if(StringUtils.isNotEmpty(street)) {
            streetCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0];
        }
        ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
        aLARM_INFORMATION.setALARM_TYPE_LV2(alarm_code);
        //aLARM_INFORMATION.setALARM_STATE(1);//1:正在预警中;0:预警已结束
        //aLARM_INFORMATION.setSEND_STATE(1);
        aLARM_INFORMATION.setSTREET_CODE(streetCode);
        aLARM_INFORMATION.setRELEASE_TIME(date);
        aLARM_INFORMATION.setStartTime(startTime);
        aLARM_INFORMATION.setEndTime(endTime);
        aLARM_INFORMATION.setID(id);
        List<ALARM_INFORMATION> aLARM_INFORMATION_list = aLARM_INFORMATIONMapper.getALARM_INFORMATION(aLARM_INFORMATION);
        for(ALARM_INFORMATION u:aLARM_INFORMATION_list) {
            loadMap_list.add(LoadMap.builder().id(u.getID()).tableName("ALARM_INFORMATION")
                    .layerid(layerid).jd(u.getJD84()).wd(u.getWD84()).build());
        }
        return loadMap_list;
    }

    public List<IconTypeLoadMap> alarmLoadMap002002(String street, String date, String id, String startTime, String endTime){
        String alarm_code = "002002";
        List<IconTypeLoadMap> loadMap_list = new ArrayList<IconTypeLoadMap>();
        String layerid = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName+"."+alarm_code);
        String streetCode = null;
        if(StringUtils.isNotEmpty(street)) {
            streetCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0];
        }
        ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
        aLARM_INFORMATION.setALARM_TYPE_LV2(alarm_code);
        aLARM_INFORMATION.setALARM_STATE(1);//1:正在预警中;0:预警已结束
        //aLARM_INFORMATION.setSEND_STATE(1);
        aLARM_INFORMATION.setSTREET_CODE(streetCode);
        aLARM_INFORMATION.setRELEASE_TIME(date);
        aLARM_INFORMATION.setStartTime(startTime);
        aLARM_INFORMATION.setEndTime(endTime);
        aLARM_INFORMATION.setID(id);
        List<ALARM_INFORMATION> aLARM_INFORMATION_list = aLARM_INFORMATIONMapper.getALARM_INFORMATION(aLARM_INFORMATION);

        Map<String,String> map_level = new HashMap();
        map_level.put("红","0");map_level.put("橙","1");
        map_level.put("黄","2");map_level.put("蓝","3");

        aLARM_INFORMATION_list.stream().filter(u->ObjectUtils.isNotEmpty(u.getJD84())).collect(Collectors.toList()).forEach(u->{
            loadMap_list.add(IconTypeLoadMap.builder().id(u.getID()).layerid(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties","31project_april.002002"))
                    .tableName("ALARM_INFORMATION").iconType(map_level.get(u.getALARM_LEVEL()))
                    .jd(u.getJD84()).wd(u.getWD84()).build());
        });
        return loadMap_list;
    }

    public List<LoadMap> alarmLoadMapLeftTwo(String alarm_code,String date_jump){
        List<LoadMap> loadMap_list = new ArrayList<LoadMap>();
        String layerid = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName+"."+alarm_code);
        ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
        aLARM_INFORMATION.setALARM_TYPE_LV1(alarm_code);
        //aLARM_INFORMATION.setALARM_STATE(1);
        aLARM_INFORMATION.setRELEASE_TIME(date_jump);
        List<ALARM_INFORMATION> aLARM_INFORMATION_list = aLARM_INFORMATIONMapper.getALARM_INFORMATION(aLARM_INFORMATION);
        aLARM_INFORMATION_list.stream().forEach(u->loadMap_list.add(LoadMap.builder().id(u.getID()).tableName("ALARM_INFORMATION")
        .layerid(layerid).jd(u.getJD84()).wd(u.getWD84()).build()));
        return loadMap_list;
    }

    public EarlyWarningCenter earlyInformationCenter(ALARM_INFORMATION aLARM_INFORMATION){
        if(StringUtils.isNotEmpty(aLARM_INFORMATION.getStreet())){
            aLARM_INFORMATION.setSTREET_CODE(LoadMyUtil.getPropertiesVauleOfKey("street.properties",aLARM_INFORMATION.getStreet()).split(",")[0]);
        }
        PageHelper.startPage(aLARM_INFORMATION.getPageNum(), aLARM_INFORMATION.getPageSize());
        if(StringUtils.isNotEmpty(aLARM_INFORMATION.getStartTime()) && StringUtils.isNotEmpty(aLARM_INFORMATION.getEndTime())){
        aLARM_INFORMATION.setStartTime(aLARM_INFORMATION.getStartTime()+" 00:00:00");
        aLARM_INFORMATION.setEndTime(aLARM_INFORMATION.getEndTime()+" 23:59:59"); }
        List<ALARM_INFORMATION> aLARM_INFORMATION_list = aLARM_INFORMATIONMapper.earlyInformationCenter(aLARM_INFORMATION);
        PageInfo<ALARM_INFORMATION> pageInfo = new PageInfo(aLARM_INFORMATION_list);

        aLARM_INFORMATION.setALARM_STATE(1);//1:正在预警中;0:预警已结束
        //aLARM_INFORMATION.setSEND_STATE(1);
        List<ALARM_INFORMATION> aLARM_INFORMATION_list_ing = aLARM_INFORMATIONMapper.earlyInformationCenter(aLARM_INFORMATION);
        PageInfo<ALARM_INFORMATION> pageInfo_ing = new PageInfo(aLARM_INFORMATION_list_ing);

        Integer disposalStatus = 0;
        for (ALARM_INFORMATION pageInfo_return:pageInfo.getList()) {
            disposalStatus = pageInfo_return.getDISPOSAL_STATE();
            pageInfo_return.setStreetName(LoadMyUtil.getPropertiesVauleOfKey("street.properties",pageInfo_return.getSTREET_CODE()));
            pageInfo_return.setCommunityName(LoadMyUtil.getPropertiesVauleOfKey("street.properties",pageInfo_return.getCOMMUNITY_CODE()));
            pageInfo_return.setDisposalStatusStr( disposalStatus == 0 ? "未推送"
                    : disposalStatus == 1 ? "已分拨"
                    : disposalStatus == 2 ? "已办结"
                    : disposalStatus == 3 ? "已归档"
                    : disposalStatus == 50 ? "已挂起" : disposalStatus == 80 ? "已作废" : "");
        }
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        nameValueModel_list.add(NameValueModel.builder().name("报警事件").value(String.valueOf(pageInfo.getTotal())).build());
        nameValueModel_list.add(NameValueModel.builder().name("报警中").value(String.valueOf(pageInfo_ing.getTotal())).build());
        nameValueModel_list.add(NameValueModel.builder().name("报警结束").value(String.valueOf(pageInfo.getTotal()-pageInfo_ing.getTotal())).build());
        //处置状态
        List<NameCodeValueModel> NameValueModel_list = new ArrayList<NameCodeValueModel>();
        List<NameValueModel> Disposal_list = earlyDisposalState();
        for(NameValueModel nameValueModel_Disposal:Disposal_list) {
            NameCodeValueModel nameCodeValueModel = new NameCodeValueModel();
            aLARM_INFORMATION.setALARM_STATE(null);
            aLARM_INFORMATION.setDISPOSAL_STATE(Integer.valueOf(nameValueModel_Disposal.getValue()));
            List<ALARM_INFORMATION> aLARM_INFORMATION_list_DisposalStatus = aLARM_INFORMATIONMapper.earlyInformationCenter(aLARM_INFORMATION);
            PageInfo<ALARM_INFORMATION> pageInfo_disposal = new PageInfo(aLARM_INFORMATION_list_DisposalStatus);
            nameCodeValueModel.setName(nameValueModel_Disposal.getName());
            nameCodeValueModel.setValue(String.valueOf(pageInfo_disposal.getTotal()));
            nameCodeValueModel.setCode(nameValueModel_Disposal.getValue());
            NameValueModel_list.add(nameCodeValueModel);
        }
        EarlyWarningCenter earlyWarningCenter = new EarlyWarningCenter();
        earlyWarningCenter.setCenter(nameValueModel_list);
        earlyWarningCenter.setDisposalStatus(NameValueModel_list);
        earlyWarningCenter.setList(pageInfo);
        return earlyWarningCenter;
    }

   public List earlyCenterListDetail(String id){
       //查询预警code
       ALARM_INFO_CODE aLARM_INFO_CODE = new ALARM_INFO_CODE();
       List<ALARM_INFO_CODE> aLARM_INFO_CODE_list = aLARM_INFO_CODEMapper.getALARM_INFO_CODE(aLARM_INFO_CODE);
       Map<String,String> map_code = new HashMap();
       aLARM_INFO_CODE_list.forEach(u->{
           map_code.put(u.getLV_2(),u.getLV_2_NAME());
       });
       ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
       aLARM_INFORMATION.setID(id);
       List<ALARM_INFORMATION> aLARM_INFORMATION_list = aLARM_INFORMATIONMapper.earlyInformationCenter(aLARM_INFORMATION);
       List list_all = new ArrayList();
       NameValueDataModel nameValueDataModel_detail = new NameValueDataModel();
       String detailName[] = {"事件类型","事件名称","事件等级","地址","发布时间","事件详情","发布人","发送状态","处置状态","取消时间"};

       String StreetName = LoadMyUtil.getPropertiesVauleOfKey("street.properties",aLARM_INFORMATION_list.get(0).getSTREET_CODE());
       String CommunityName = LoadMyUtil.getPropertiesVauleOfKey("street.properties",aLARM_INFORMATION_list.get(0).getSTREET_CODE());
       //cacleTime = aLARM_INFORMATION_list.get(0).getCANCEL_TIME().substring(0, 19);
       String releaseTime = aLARM_INFORMATION_list.get(0).getRELEASE_TIME().substring(0, 19);
       String cacleTime = String.valueOf(aLARM_INFORMATION_list.get(0).getCANCEL_TIME());

       Integer sendStatus = aLARM_INFORMATION_list.get(0).getSEND_STATE();
       Integer disposalStatus = aLARM_INFORMATION_list.get(0).getDISPOSAL_STATE();
       String detailName_value[] = {map_code.get(aLARM_INFORMATION_list.get(0).getALARM_TYPE_LV2()),aLARM_INFORMATION_list.get(0).getALARM_OBJECT(),
               aLARM_INFORMATION_list.get(0).getALARM_LEVEL(),aLARM_INFORMATION_list.get(0).getAddress(),
               releaseTime,aLARM_INFORMATION_list.get(0).getALARM_OBJECT()+releaseTime+",在福田区"+StreetName+CommunityName+"社区,"+aLARM_INFORMATION_list.get(0).getAddress()+"发生"+aLARM_INFORMATION_list.get(0).getCONTENTS()+"报警",
               aLARM_INFORMATION_list.get(0).getRELEASE_PERSON(),sendStatus == 0 ? "未推送" : sendStatus == 1 ? "已推送" : "",
               disposalStatus == 0 ? "未处置"
                       : disposalStatus == 1 ? "已分拨"
                       : disposalStatus == 2 ? "已办结"
                       : disposalStatus == 3 ? "已归档"
                       : disposalStatus == 50 ? "已挂起" : disposalStatus == 80 ? "已作废" : "", cacleTime};
       List<NameValueModel> nameValueModel_detail_list = new ArrayList<NameValueModel>();
       for(int i=0;i<detailName.length;i++){
           nameValueModel_detail_list.add(NameValueModel.builder().name(detailName[i]).value(detailName_value[i]).build());
       }
       nameValueDataModel_detail.setName("详细信息");//1:正在预警中;0:预警已结束
       String status;
       if(aLARM_INFORMATION_list.get(0).getALARM_STATE()==0){
           status = "报警结束";
       }else{
           status = "正在报警中";
       }
       nameValueDataModel_detail.setValue(status);
       nameValueDataModel_detail.setData(nameValueModel_detail_list);
       NameDataModel nameDataModel_attribute = new NameDataModel();
       String attributeName[] = {"事件编号","渠道","更新时间"};
       String attribute_value[] = {aLARM_INFORMATION_list.get(0).getALARM_TYPE_LV2(),
               aLARM_INFORMATION_list.get(0).getCHANNEL(),aLARM_INFORMATION_list.get(0).getYJJC_UPDATE_TIME().substring(0,19)};
       List<NameValueModel> nameValueModel_attribute_list = new ArrayList<NameValueModel>();
       for(int i=0;i<attributeName.length;i++){
           nameValueModel_attribute_list.add(NameValueModel.builder().name(attributeName[i]).value(attribute_value[i]).build());
       }
       nameDataModel_attribute.setName("属性信息");
       nameDataModel_attribute.setData(nameValueModel_attribute_list);

       list_all.add(nameValueDataModel_detail);
       list_all.add(nameDataModel_attribute);
       return list_all;
    }

    /**
     * 将逻辑改为统计四大类预警数量
     * update_user: heliangming
     * update_time 20190325
     * @return
     */
    public NameDataModel fourAlarm(){
        //String alarm_four_lv1[] = {"003","001"};//安全生产、消防预警、自然灾害
        List<ALARM_INFORMATION> aLARM_INFORMATION_list_all = new ArrayList<ALARM_INFORMATION>();
        aLARM_INFORMATION_list_all = aLARM_INFORMATIONMapper.alarmFour();
        String sql = "select ALARM_TYPE_LV2 from ALARM_PUSH_AVILABLE where IS_ENABLED = '1'";
        List<LinkedHashMap> map_list_push = oracleOperateService.querySql(sql);
        Set set = new HashSet();
        map_list_push.stream().forEach(u->set.add(u.get("ALARM_TYPE_LV2")));
        NameDataModel nameDataModel = new NameDataModel();
        List<Map<String,Object>> list_map = new ArrayList();
        aLARM_INFORMATION_list_all.stream().forEach(u->{
            LinkedHashMap<String,Object> map = new LinkedHashMap();
            String alarmCode = u.getALARM_TYPE_LV2();
            map.put("id",u.getID());
            map.put("systemid",u.getSYSTEM_ID());
            map.put("layerid",LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties","31project_april."+alarmCode));
            map.put("预警对象",u.getALARM_OBJECT());
            map.put("预警内容",u.getCONTENTS());
            map.put("时间",u.getRELEASE_TIME());
            map.put("NeedPush",set.contains(u.getALARM_TYPE_LV2()) && "1".equals(u.getSEND_STATE()) ? true : false);
            list_map.add(map);
        });
        nameDataModel.setName(list_map.size()+"项预警");
        nameDataModel.setData(list_map);
        return nameDataModel;
    }

    public List<NameValueModel> earlyDisposalState() {
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        String DisposalStatus[] = {"0", "1", "2", "3", "50", "80"};//"0:未处置;1:已分拨;2:已办结;3:已归档;50:挂起;80:作废"
        String DisposalName[] = {"未处置", "已分拨", "已办结", "已归档", "挂起", "作废"};
        for(int i = 0;i<DisposalStatus.length;i++){
            nameValueModel_list.add(NameValueModel.builder().name(DisposalName[i]).value(DisposalStatus[i]).build());
        }
        return nameValueModel_list;
    }
}
