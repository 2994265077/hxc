package com.cetccity.operationcenter.webframework.hiddendanger.tools.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.tools.ImageFileTool;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.IotVauleData;
import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotDeviceMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.UrbanRiskMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotEventMapper;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticTipModel;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.URBAN_RISK;
import com.cetccity.operationcenter.webframework.web.model.iot.IOT_DEVICE;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TipUtil {
	@Autowired
	private CommonInstance commonInstance;
    private static final Logger logger = LoggerFactory.getLogger(TipUtil.class);

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    IotDeviceMapper iOT_DEVICEMapper;

    @Autowired
    IotEventMapper iOT_EVENTMapper;

    @Autowired
    UrbanRiskMapper uRBAN_RISKMapper;

    public String loadMapTip(String tableName, String id) {
        String _source;
        String urlStr = "http://"+ commonInstance.getElasticsearchIp()+":"+ commonInstance.getElasticsearchPort()+"/"+tableName+"@31project_april/_doc/"+id+"";
        String source = null;
        try {
            source = UrlApiToSource.doJsonGet(urlStr);
            /**
             *json转为实体
             */
            ElasticTipModel elasticTipModel = JsonUtils.jsonToBean(source, ElasticTipModel.class);
            _source = elasticTipModel.get_source().toString();
        } catch (Exception e) {
            logger.info("没有数据......");
            _source = null;
        }
        return _source;
    }

    public LinkedHashMap loadMapTipToOracle(String tableName, String id) {
        String sql;
        if("IOT_DEVICE".equals(tableName)) {
            sql = "select * from "+tableName+" where DEVICE_CODE='"+id+"'";
        }else if("QHSJ_T_AIR_BASICINFO".equals(tableName)||"QHSJ_SURF_WATER_BASICINFO".equals(tableName)){
            sql = "select * from "+tableName+" where SITE_CODE='"+id+"'";
        }else if("QHSJ_ENTERPRISE_BASICINFO".equals(tableName)){
            sql = "select * from "+tableName+" where PSCODE='"+id+"'";
        }else if("VIDEO_POLICE".equals(tableName)){
            sql = "select * from "+tableName+" where GB_CODE='"+id+"'";
        }else if("BLK_SANXIAO_PLACE".equals(tableName)){
            sql = "select * from "+tableName+" where ID='"+id+"'";
        }else if("T_BUILD_INFO_V".equals(tableName)){
            sql = "select * from "+tableName+" where BUILDID='"+id+"'";
        } else {
            sql = "select * from "+tableName+" where ROWID='"+id+"'";
        }
        List<LinkedHashMap> list_obj = oracleOperateService.querySql(sql);
        LinkedHashMap<String,String> source = list_obj.get(0);
        return source;
    }

    public LinkedHashMap loadMapIotTip(String device_code) {
        LinkedHashMap map;
        IOT_DEVICE iOT_DEVICE = new IOT_DEVICE();
        iOT_DEVICE.setDEVICE_CODE(device_code);
        List<IOT_DEVICE> iOT_DEVICE_list = iOT_DEVICEMapper.getIOT_DEVICE_OBJ(iOT_DEVICE);
        if(iOT_DEVICE_list.isEmpty()){
             map = null;
        }else {
            String json = JsonUtils.beanToJson(iOT_DEVICE_list.get(0));
            Gson gson = new Gson();
            LinkedHashMap<String, String> maps = new LinkedHashMap<String, String>();
            map = gson.fromJson(json.toUpperCase(), maps.getClass());
        }
        return map;
    }

    public LinkedHashMap loadMapBuildTip(String tableName, String buildid) {
        LinkedHashMap map = new LinkedHashMap();
        String sql_build_info = "select \"BUILD_NAME\",\"BUILDID\",\"BUILD_ADDR\",\"JZQK\",\"JG_DATE\",\"ZDMJ\",\"BZCMJ\",\"DXMJ\",\"DSMJ\",\"JZMJ\",\"JZGD\",\"DSCS\",\"DXCS\" from \""+tableName+"\" where \"BUILDID\"='"+buildid+"'";
        String sql_build_ztzr = "select \"ZTZR_NAME\",\"ZTZR_TEL\" from T_BUILD_ZTZR_V where BUILD_ID = '"+buildid+"'";
        String sql_blk_building = "select \"SYYT\" from BLK_BUILDING where LDDM = '"+buildid+"'";
        List<LinkedHashMap> list_build_info = oracleOperateService.querySql(sql_build_info);
        List<LinkedHashMap> list_build_ztzr = oracleOperateService.querySql(sql_build_ztzr);
        List<LinkedHashMap> list_blk_building = oracleOperateService.querySql(sql_blk_building);
        LinkedHashMap build_map = new LinkedHashMap();
        LinkedHashMap build_ztzr_map = new LinkedHashMap();
        LinkedHashMap blk_build_map = new LinkedHashMap();
        if(list_build_info.size()>=1) {
            build_map = list_build_info.get(0);
            map.putAll(build_map);
        }else{
            build_map.put("BUILD_NAME","无数据");build_map.put("BUILDID","无数据");
            build_map.put("BUILD_ADDR","无数据");build_map.put("JZQK","无数据");
            build_map.put("JG_DATE","无数据");build_map.put("ZDMJ","无数据");
            build_map.put("BZCMJ","无数据");build_map.put("DXMJ","无数据");
            build_map.put("DSMJ","无数据");build_map.put("JZMJ","无数据");
            build_map.put("JZGD","无数据");build_map.put("DSCS","无数据");build_map.put("DXCS","无数据");
            map.putAll(build_map);
        }
        if(list_build_ztzr.size()>=1) {
            build_ztzr_map = list_build_ztzr.get(0);
            map.putAll(build_ztzr_map);
        }else{
            build_ztzr_map.put("ZTZR_NAME","无数据");build_ztzr_map.put("ZTZR_TEL","无数据");
            map.putAll(build_ztzr_map);
        }
        if(list_blk_building.size()>=1) {
            blk_build_map = list_blk_building.get(0);
            map.putAll(blk_build_map);
        }else{
            blk_build_map.put("SYYT","无数据");
            map.putAll(blk_build_map);
        }
        return map;
    }

    public NameDataModel tipOfChart(String tableName,String id){
        NameDataModel nameDataModel = new NameDataModel();
        String sql;
        if("URBAN_RISK".equals(tableName)) {
            List<URBAN_RISK> uRBAN_RISK_list = uRBAN_RISKMapper.getURBAN_RISKObj(id);
            List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
            String nameURBAN_RISK[] = {"发生事故后果顾严重性", "发生事故可能性", "人群暴露于危险环境程度"};
            String vauleURBAN_RISK[] = {uRBAN_RISK_list.get(0).getRISK_ASSESS_CSCORE(), uRBAN_RISK_list.get(0).getRISK_ASSESS_LSCORE(),
                    uRBAN_RISK_list.get(0).getRISK_ASSESS_ESCORE()};
            for (int i = 0; i < nameURBAN_RISK.length; i++) {
                nameValueModel_list.add(NameValueModel.builder().name(nameURBAN_RISK[i]).value(vauleURBAN_RISK[i]).build());
            }
            nameDataModel.setName("风险源情况");
            nameDataModel.setData(nameValueModel_list);
        }else if("QAJJ_PUCENTP_V".equals(tableName)){
            sql = "select INNERID from "+tableName+" where ROWID='"+id+"'";
            List<LinkedHashMap> list_obj = oracleOperateService.querySql(sql);
            String INNERID = (String)list_obj.get(0).get("INNERID");
            //关联巡查表--QAJJ_INSTROUBLEREGCHECK_V
            String sql_QAJJ_INSTROUBLEREGCHECK_V = "select INNERID from QAJJ_INSTROUBLEREGCHECK_V where ENTPRESULT = '0' AND INNERID='"+INNERID+"'";
            List<LinkedHashMap> list_QAJJ_INSTROUBLEREGCHECK_V = oracleOperateService.querySql(sql_QAJJ_INSTROUBLEREGCHECK_V);
            int count_QAJJ_INSTROUBLEREGCHECK_V = list_QAJJ_INSTROUBLEREGCHECK_V.size();
            //关联自查表--QAJJ_SCHTROUBLEREGCHECK_V
            String sql_QAJJ_SCHTROUBLEREGCHECK_V = "select INNERID from QAJJ_INSTROUBLEREGCHECK_V where INNERID='"+INNERID+"'";
            List<LinkedHashMap> list_QAJJ_SCHTROUBLEREGCHECK_V = oracleOperateService.querySql(sql_QAJJ_SCHTROUBLEREGCHECK_V);
            int count_QAJJ_SCHTROUBLEREGCHECK_V = list_QAJJ_SCHTROUBLEREGCHECK_V.size();
            int count_xuncha = count_QAJJ_INSTROUBLEREGCHECK_V+count_QAJJ_SCHTROUBLEREGCHECK_V;

            List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
            String nameURBAN_RISK[] = {"发生事故后果顾严重性", "发生事故可能性", "人群暴露于危险环境程度","巡查隐患"};
            String vauleURBAN_RISK[] = {"2","2","2",String.valueOf(count_xuncha*10)};
            for (int i = 0; i < nameURBAN_RISK.length; i++) {
                nameValueModel_list.add(NameValueModel.builder().name(nameURBAN_RISK[i]).value(vauleURBAN_RISK[i]).build());
            }
            nameDataModel.setName("风险源情况");
            nameDataModel.setData(nameValueModel_list);
        }
        return nameDataModel;
    }

    public String cityComponentImagetip(String tableName, String id) {
        String sql_img = "select PHOTO from "+tableName+" where ROWID = '"+id+"'";
        List<LinkedHashMap> list_build_info = oracleOperateService.querySql(sql_img);
        String imgPath = (String)list_build_info.get(0).get("PHOTO");
        ImageFileTool.getBuildImageFileUrl(imgPath).getData();
        return ImageFileTool.getBjImageFileUrl(imgPath).getData();
    }

    public IotVauleData iotThresholdTip(String DEVICE_CODE,String value) {
        String waterValue;
        if(LoadMyUtil.isInteger(value)){
            waterValue = value;
        }else{
            waterValue = "0";
        }
        IotVauleData iotVauleData = new IotVauleData();
        iotVauleData.setIotVaule(value+" cm");
        String waterName[] = {"当前水位","轻微积水","轻微内涝","严重内涝"};
        String waterLevel[] = {waterValue,"5","20","40"};
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        for (int i = 0;i<waterName.length;i++){
            nameValueModel_list.add(NameValueModel.builder().name(waterName[i]).value(waterLevel[i]).build());
        }
        iotVauleData.setData(nameValueModel_list);
        return iotVauleData;
    }
}
