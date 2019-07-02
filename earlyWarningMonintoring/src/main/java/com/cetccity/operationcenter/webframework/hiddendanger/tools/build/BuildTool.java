/*
package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.*;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.BUILD_SCORE_WEIGHTMapper;
import com.cetccity.operationcenter.webframework.web.dao.BLK_LEGAL_PERSONMapper;
import com.cetccity.operationcenter.webframework.web.dao.T_BUILD_CHECKDAY_DANGERMapper;
import com.cetccity.operationcenter.webframework.web.model.build.*;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.BLK_LEGAL_PERSON;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.T_BUILD_CHECKDAY_DANGER;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.web.util.DataOfProperityUtil;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@CacheConfig(cacheNames = "buildDetail")
public class BuildTool {

    Logger logger = LoggerFactory.getLogger(BuildTool.class);

    @Autowired
    TipUtil tipUtil;

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    BuildBasicToolToCache buildBasicToolToCache;

    @Autowired
    BuildBasicToCache buildBasicToCache;

    */
/*@Autowired
    BuildSecurityManagementTool buildSecurityManagementTool;*//*


    @Autowired
    BuildSecurityManagementToolToCache buildSecurityManagementToolToCache;

    @Autowired
    BuildScoreTool buildScoreTool;

    @Autowired
    BuildPurposeToolToCache buildPurposeToolToCache;

    @Autowired
    BuildPurposeToCache buildPurposeToCache;

    @Autowired
    BuildSurroundingEnvironmentToCache buildSurroundingEnvironmentToCache;

    @Autowired
    BuildSurroundingEnvironmentToolToCache buildSurroundingEnvironmentToolToCache;

    @Autowired
    BLK_LEGAL_PERSONMapper bLK_LEGAL_PERSONMapper;

    @Autowired
    T_BUILD_CHECKDAY_DANGERMapper t_BUILD_CHECKDAY_DANGERMapper;

    @Autowired
    BUILD_SCORE_WEIGHTMapper bUILD_SCORE_WEIGHTMapper;

    @Cacheable
    public NameDataModel buildBasic(String tableName, String buildid) {
        NameDataModel nameDataModel = new NameDataModel();
        DataOfProperityUtil dataOfProperityUtil_xiaoan = new DataOfProperityUtil();
        DataOfProperityUtil dataOfProperityUtil_blk = new DataOfProperityUtil();

        LinkedHashMap<String,String> map = tipUtil.loadMapBuildTip(tableName, buildid);
        */
/*基本信息模块*//*

        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        NameDataModel nameDataModel01 = new NameDataModel();
        nameDataModel01.setName("基本信息");
        List<NameValueModel> nameValueModel_list01 = new ArrayList<NameValueModel>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if("".equals(value)||value==null){
                map.put(key,"无数据");
            }
        }
        //建筑情况
        String JZQK = DataOfProperityUtil.dataDictionary("dataDictionary_xiaoan.properties", "t_build_info", "JZQK", map.get("JZQK"));
        if("".equals(JZQK)||JZQK==null){JZQK = "无数据";}
        //建筑用途
        String SYYT = DataOfProperityUtil.dataDictionary("dataDictionary_blkdata.properties", "BLK_BUILDING", "SYYT", map.get("SYYT"));
        if("".equals(SYYT)||SYYT==null){SYYT = "无数据";}
        String Name[] = {"建筑名称","建筑编码","建筑地址","建筑情况","建筑用途","竣工时间","应急联系人","联系方式"};
        String Value[] = {map.get("BUILD_NAME"),map.get("BUILDID"),map.get("BUILD_ADDR"),JZQK,SYYT,map.get("JG_DATE"),
                map.get("ZTZR_NAME"),map.get("ZTZR_TEL")};
        for(int i = 0;i<Name.length;i++){
            NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(Name[i]);
            nameValueModel.setValue(Value[i]);
            nameValueModel_list01.add(nameValueModel);
        }
        nameDataModel01.setData(nameValueModel_list01);
        nameDataModel_list.add(nameDataModel01);

        */
/*建筑物体量及架构信息模块*//*

        NameDataModel nameDataModel02 = new NameDataModel();
        nameDataModel02.setName("建筑物体量及架构信息");
        List<NameValueModel> nameValueModel_list02 = new ArrayList<NameValueModel>();
        String Name2[] = {"占地面积","标准层面积","地下面积","地上面积","建筑面积","建筑高度","地上层数","地下层数"};
        String Value2[] = {map.get("ZDMJ"),map.get("BZCMJ"),map.get("DXMJ"),map.get("DSMJ"),map.get("JZMJ"),
                map.get("JZGD"),map.get("DSCS"),map.get("DXCS")};
        for(int j=0;j<Name2.length;j++){
            NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(Name2[j]);
            nameValueModel.setValue(Value2[j]);
            nameValueModel_list02.add(nameValueModel);
        }
        nameDataModel02.setData(nameValueModel_list02);
        nameDataModel_list.add(nameDataModel02);
        nameDataModel.setName(map.get("BUILD_NAME"));
        nameDataModel.setData(nameDataModel_list);
        return nameDataModel;
    }

    //@Cacheable
    */
/*public NameDataModel buildRisk(String tableName, String buildid){
        ScoreTotleAttribute scoreTotleAttribute = new ScoreTotleAttribute();
        *//*
*/
/*一、获取建筑基础属性 30%*//*
*/
/*
        LinkedHashMap map_basic_attribute = new LinkedHashMap();
        List<Map> scoreValue_basic_list = new ArrayList<Map>();
        *//*
*/
/*1.1、楼栋年龄评分模块*//*
*/
/*
        Double score_001001 = Double.valueOf(buildBasicToolToCache.buildBasicScoreToCache(buildid).get("score_001001"));
        String value_001001 = buildBasicToolToCache.buildBasicScoreToCache(buildid).get("value_001001");
        *//*
*/
/*1.2、建筑高度评分模块*//*
*/
/*
        Double score_001002 = Double.valueOf(buildBasicToolToCache.buildBasicScoreToCache(buildid).get("score_001002"));
        String value_001002 = buildBasicToolToCache.buildBasicScoreToCache(buildid).get("value_001002");
        *//*
*/
/*1.3、耐火等级模块*//*
*/
/*
        Double score_001003 = Double.valueOf(buildBasicToolToCache.buildBasicScoreToCache(buildid).get("score_001003"));
        String value_001003 = buildBasicToolToCache.buildBasicScoreToCache(buildid).get("value_001003");
        *//*
*/
/*1.4、隐患等级模块*//*
*/
/*
        Double score_001004 = Double.valueOf(buildBasicToolToCache.buildBasicScoreToCache(buildid).get("score_001004"));
        String value_001004 = buildBasicToolToCache.buildBasicScoreToCache(buildid).get("value_001004");

        *//*
*/
/*二、建筑火灾安全管理 50%*//*
*/
/*
        LinkedHashMap map_security_management_attribute = new LinkedHashMap();
        List<Map> scoreValue_security_management_list = new ArrayList<Map>();
        *//*
*/
/*2.1、火灾报警器情况 25%*//*
*/
/*
        Double score_002001 = Double.valueOf(buildSecurityManagementToolToCache.buildSecurityManagementScoreToCache(buildid).get("score_002001"));
        String value_002001 = buildSecurityManagementToolToCache.buildSecurityManagementScoreToCache(buildid).get("value_002001");
        *//*
*/
/*2.2、消防控制室 25%*//*
*/
/*
        Double score_002002 = Double.valueOf(buildSecurityManagementToolToCache.buildSecurityManagementScoreToCache(buildid).get("score_002002"));
        String value_002002 = buildSecurityManagementToolToCache.buildSecurityManagementScoreToCache(buildid).get("value_002002");
        *//*
*/
/*2.3、消防设备完好度 25%*//*
*/
/*
        Double score_002003 = Double.valueOf(buildSecurityManagementToolToCache.buildSecurityManagementScoreToCache(buildid).get("score_002003"));
        String value_002003 = buildSecurityManagementToolToCache.buildSecurityManagementScoreToCache(buildid).get("value_002003");
        *//*
*/
/*2.4、消防巡查 25%*//*
*/
/*
        Double score_002004 = Double.valueOf(buildSecurityManagementToolToCache.buildSecurityManagementScoreToCache(buildid).get("score_002004"));
        String value_002004 = buildSecurityManagementToolToCache.buildSecurityManagementScoreToCache(buildid).get("value_002004");

        *//*
*/
/*三、获取建筑用途信息 10%*//*
*/
/*
        Double score_003001 = Double.valueOf(buildPurposeToolToCache.buildPurposeScoreToCache(buildid).get("score_003001"));
        String value_003001 = buildPurposeToolToCache.buildPurposeScoreToCache(buildid).get("value_003001");

        *//*
*/
/*四、获取建筑周边环境信息 10%*//*
*/
/*
        LinkedHashMap map_surrounding_environment_attribute = new LinkedHashMap();
        List<Map> scoreValue_surrounding_environment_list = new ArrayList<Map>();
        *//*
*/
/*4.1、天气状况 50%*//*
*/
/*
        Double score_004001 = Double.valueOf(buildSurroundingEnvironmentToolToCache.buildSurroundingEnvironmentScoreToCache().get("score_004001"));
        String value_004001 = buildSurroundingEnvironmentToolToCache.buildSurroundingEnvironmentScoreToCache().get("value_004001");
        *//*
*/
/*4.2、温差 30%*//*
*/
/*
        Double score_004002 = Double.valueOf(buildSurroundingEnvironmentToolToCache.buildSurroundingEnvironmentScoreToCache().get("score_004002"));
        String value_004002 = buildSurroundingEnvironmentToolToCache.buildSurroundingEnvironmentScoreToCache().get("value_004002");
        *//*
*/
/*4.3、风级 20%*//*
*/
/*
        Double score_004003 = Double.valueOf(buildSurroundingEnvironmentToolToCache.buildSurroundingEnvironmentScoreToCache().get("score_004003"));
        String value_004003 = buildSurroundingEnvironmentToolToCache.buildSurroundingEnvironmentScoreToCache().get("value_004003");

        NameDataModel nameDataModel_return = new NameDataModel();
        Map<String ,String> Attribute_Two = new HashMap();
        Attribute_Two.put("score_001001",String.valueOf(score_001001));Attribute_Two.put("value_001001",value_001001);
        Attribute_Two.put("score_001002",String.valueOf(score_001002));Attribute_Two.put("value_001002",value_001002);
        Attribute_Two.put("score_001003",String.valueOf(score_001003));Attribute_Two.put("value_001003",value_001003);
        Attribute_Two.put("score_001004",String.valueOf(score_001004));Attribute_Two.put("value_001004",value_001004);
        Attribute_Two.put("score_002001",String.valueOf(score_002001));Attribute_Two.put("value_002001",value_002001);
        Attribute_Two.put("score_002002",String.valueOf(score_002002));Attribute_Two.put("value_002002",value_002002);
        Attribute_Two.put("score_002003",String.valueOf(score_002003));Attribute_Two.put("value_002003",value_002003);
        Attribute_Two.put("score_002004",String.valueOf(score_002004));Attribute_Two.put("value_002004",value_002004);
        Attribute_Two.put("score_003001",String.valueOf(score_003001));Attribute_Two.put("value_003001",value_003001);
        Attribute_Two.put("score_004001",String.valueOf(score_004001));Attribute_Two.put("value_004001",value_004001);
        Attribute_Two.put("score_004002",String.valueOf(score_004002));Attribute_Two.put("value_004002",value_004002);
        Attribute_Two.put("score_004003",String.valueOf(score_004003));Attribute_Two.put("value_004003",value_004003);
        String sql_Attribute_One = "select MAIN_ID,NAME,PID FROM BUILD_SCORE_WEIGHT WHERE PID = '0'";
        List<LinkedHashMap> list_Attribute_One = oracleOperateService.querySql(sql_Attribute_One);
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        for (LinkedHashMap map_Attribute_One:list_Attribute_One) {
            NameDataModel nameDataModel = new NameDataModel();
            nameDataModel.setName((String)map_Attribute_One.get("NAME"));
            String main_id = (String)map_Attribute_One.get("MAIN_ID");
            String sql_Attribute_Two = "select MAIN_ID,NAME,PID FROM BUILD_SCORE_WEIGHT WHERE PID = '"+main_id+"'";
            List<LinkedHashMap> list_Attribute_Two = oracleOperateService.querySql(sql_Attribute_Two);
            List<NameScoreValueModel> nameScoreValueModel_list = new ArrayList<NameScoreValueModel>();
            for (LinkedHashMap map_Attribute_Two:list_Attribute_Two) {
                NameScoreValueModel nameScoreValueModel = new NameScoreValueModel();
                nameScoreValueModel.setName((String)map_Attribute_Two.get("NAME"));
                nameScoreValueModel.setScore(Double.valueOf(Attribute_Two.get("score_"+(String)map_Attribute_Two.get("MAIN_ID"))));
                nameScoreValueModel.setValue(Attribute_Two.get("value_"+(String)map_Attribute_Two.get("MAIN_ID")));
                nameScoreValueModel_list.add(nameScoreValueModel);
            }
            nameDataModel.setData(nameScoreValueModel_list);
            nameDataModel_list.add(nameDataModel);
        }
        nameDataModel_return.setName("建筑风险评分总分: "+buildScoreTool.getBuildScore(buildid).getDangerScore());
        nameDataModel_return.setData(nameDataModel_list);
        return nameDataModel_return;
    }*//*


    //@Cacheable
    public MyPageInfoModel buildHiddenDanger(String buildid, Integer pageNum, Integer pageSize){
        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String before_time = LoadMyUtil.getMyTime("DATE",-30)+" 00:00:00";
        String current_time = LoadMyUtil.getMyTime("SECOND",0);
        PageHelper.startPage(pageNum, pageSize);
        T_BUILD_CHECKDAY_DANGER t_BUILD_CHECKDAY_DANGER = new T_BUILD_CHECKDAY_DANGER();
        t_BUILD_CHECKDAY_DANGER.setBUILD_ID(buildid);
        t_BUILD_CHECKDAY_DANGER.setStart_time(before_time);
        t_BUILD_CHECKDAY_DANGER.setEnd_time(current_time);
        List<T_BUILD_CHECKDAY_DANGER> t_BUILD_CHECKDAY_DANGER_list = t_BUILD_CHECKDAY_DANGERMapper.getT_BUILD_CHECKDAY_DANGER(t_BUILD_CHECKDAY_DANGER);
        PageInfo<T_BUILD_CHECKDAY_DANGER> pageInfo = new PageInfo(t_BUILD_CHECKDAY_DANGER_list);

        String top[] = {"核查日期","核查人","整改状态","巡查日期","巡查员","录入日期"};
        List<String[]> body_list = new ArrayList();
        for (T_BUILD_CHECKDAY_DANGER t_BUILD_CHECKDAY_DANGER_return:t_BUILD_CHECKDAY_DANGER_list) {
            String tbody[] = {t_BUILD_CHECKDAY_DANGER_return.getHC_TIME(),
                    t_BUILD_CHECKDAY_DANGER_return.getHCR(),
                    t_BUILD_CHECKDAY_DANGER_return.getZG_STATUS(),
                    t_BUILD_CHECKDAY_DANGER_return.getCHECK_TIME(),
                    t_BUILD_CHECKDAY_DANGER_return.getUSERNAME(),
                    t_BUILD_CHECKDAY_DANGER_return.getLRDATE()};
            body_list.add(tbody);
        }
        TheadTbodyModel theadTbodyModel = new TheadTbodyModel();
        theadTbodyModel.setThead(top);
        theadTbodyModel.setTbody(body_list);

        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setList(theadTbodyModel);
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        return pageInfo_return;
    }

    public EnterpriseModel buildEnterprise(String buildid,Integer pageNum,Integer pageSize){
        EnterpriseModel enterpriseModel = new EnterpriseModel();
        PageHelper.startPage(pageNum, pageSize);
        BLK_LEGAL_PERSON bLK_LEGAL_PERSON = new BLK_LEGAL_PERSON();
        bLK_LEGAL_PERSON.setLDDM(buildid);
        List<BLK_LEGAL_PERSON> bLK_LEGAL_PERSON_list = bLK_LEGAL_PERSONMapper.getBLK_LEGAL_PERSON(bLK_LEGAL_PERSON);
        PageInfo<BLK_LEGAL_PERSON> pageInfo = new PageInfo(bLK_LEGAL_PERSON_list);
        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        List<LinkedHashMap> map_build_enterprise_list = new ArrayList<LinkedHashMap>();
        for (BLK_LEGAL_PERSON bLK_LEGAL_PERSON_return:pageInfo.getList()) {
            LinkedHashMap map_build_enterprise = new LinkedHashMap();
            map_build_enterprise.put("企业名称",  bLK_LEGAL_PERSON_return.getJGMC());
            map_build_enterprise.put("企业类型",  bLK_LEGAL_PERSON_return.getJGXX());
            map_build_enterprise.put("法人代表", bLK_LEGAL_PERSON_return.getFZRXM());
            map_build_enterprise.put("更新时间",  bLK_LEGAL_PERSON_return.getGXSJ());
            map_build_enterprise_list.add(map_build_enterprise);
        }
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setList(map_build_enterprise_list);
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setPageSize(pageInfo.getPageSize());

        enterpriseModel.setRegisterLegalPerson(pageInfo.getTotal());
        enterpriseModel.setLegalPerson(pageInfo.getTotal());
        enterpriseModel.setOtherPerson(0);
        enterpriseModel.setAlarmList(pageInfo_return);

        return enterpriseModel;
    }

    //@Cacheable
    public EnterpriseModel buildEnterprise(String tableName,String buildid,Integer page,Integer size){
        EnterpriseModel enterpriseModel = new EnterpriseModel();
        MyPageInfoModel pageInfo = new MyPageInfoModel();
        String sql_enterprise_total = "select JGMC from \""+tableName+"\" where 1=1 and \"LDDM\" ='"+buildid+"'";
        List<LinkedHashMap> list_build_enterprise_total = oracleOperateService.querySql(sql_enterprise_total);
        //String sql_build_enterprise = "SELECT * FROM (SELECT rowres.*, ROWNUM RN FROM (select JGMC,JGXX,FZRXM,GXSJ from \""+tableName+"\" where 1=1 and \"LDDM\" ='"+buildid+"' )rowres WHERE ROWNUM <='"+size+"'+'"+page+"')WHERE RN >'"+page+"'";
        String sql_build_enterprise = "select JGMC,JGXX,FZRXM,GXSJ from \""+tableName+"\" where 1=1 and \"LDDM\" ='"+buildid+"'";

        List<LinkedHashMap> list_build_enterprise = oracleOperateService.querySql(sql_build_enterprise);
        int total = list_build_enterprise_total.size();

        List<LinkedHashMap> map_build_enterprise_list = new ArrayList<LinkedHashMap>();
        for (LinkedHashMap map_enterprise:list_build_enterprise) {
            LinkedHashMap map_build_enterprise = new LinkedHashMap();
            map_build_enterprise.put("企业名称",  map_enterprise.get("JGMC"));
            map_build_enterprise.put("企业类型",  map_enterprise.get("JGXX"));
            map_build_enterprise.put("法人代表", map_enterprise.get("FZRXM"));
            map_build_enterprise.put("更新时间",  map_enterprise.get("GXSJ"));
            map_build_enterprise_list.add(map_build_enterprise);
        }
        pageInfo.setTotal(total);
        pageInfo.setPageNum(page/size+1);
        pageInfo.setList(map_build_enterprise_list);
        pageInfo.setPages(total%size==0?total/size:total/size+1);
        pageInfo.setPageSize(size);

        enterpriseModel.setRegisterLegalPerson(total);
        enterpriseModel.setLegalPerson(total);
        enterpriseModel.setOtherPerson(0);
        enterpriseModel.setAlarmList(pageInfo);

        return enterpriseModel;
    }

    @Cacheable
    public LayerNumData buildHouse(String tableName, String buildid) {
        LayerNumData layerNumData = new LayerNumData();
        List<LayerData> layerData_list = new ArrayList<LayerData>();

        String sql_build_info = "select \"BUILD_NAME\",\"BUILDID\",\"BUILD_ADDR\",\"JZQK\",\"JG_DATE\",\"ZDMJ\",\"BZCMJ\",\"DXMJ\",\"DSMJ\",\"JZMJ\",\"JZGD\",\"DSCS\",\"DXCS\" from T_BUILD_INFO_V where \"BUILDID\"='"+buildid+"'";
        List<LinkedHashMap> list_build_info = oracleOperateService.querySql(sql_build_info);
        LinkedHashMap map_build = list_build_info.get(0);
        String DSCS = (String)map_build.get("DSCS");
        Integer floor = Integer.valueOf(DSCS);
        layerNumData.setLayernum(DSCS);
        */
/*获取到建筑楼层floor*//*

        for(int i =1;i<floor+1;i++){
            List<BuildHouseModel> buildHouseModel_list = new ArrayList<BuildHouseModel>();
            LayerData layerData = new LayerData();
            layerData.setLayer(String.valueOf(i));
            String sql_floor = "select * from \""+tableName+"\" where LDDM = '"+buildid+"' and FWSSLC = '"+i+"' order by FWDM";
            List<LinkedHashMap> list_build_floor = oracleOperateService.querySql(sql_floor);

            */
/*获取楼层floor的房间信息*//*

            for (LinkedHashMap house_map: list_build_floor) {
                List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
                List pop_list = new ArrayList();
                BuildHouseModel buildHouseModel = new BuildHouseModel();

                buildHouseModel.setCode((String) house_map.get("SQDM"));
                buildHouseModel.setNum((String) house_map.get("FH"));

                List<NameValueModel> nameValueModel_list01 = new ArrayList<NameValueModel>();
                NameDataModel nameDataModel01 = new NameDataModel();
                NameDataModel nameDataModel02 = new NameDataModel();

                    nameDataModel01.setName("房屋信息");
                    NameValueModel nameValueModel01 = new NameValueModel();
                    nameValueModel01.setName("房屋编码");
                    nameValueModel01.setValue((String) house_map.get("FWDM"));
                    nameValueModel_list01.add(nameValueModel01);
                    NameValueModel nameValueModel02 = new NameValueModel();
                    nameValueModel02.setName("房屋性质");
                    nameValueModel02.setValue((String) house_map.get("GLFL"));
                    nameValueModel_list01.add(nameValueModel02);
                    NameValueModel nameValueModel03 = new NameValueModel();
                    nameValueModel03.setName("房屋用途");
                    nameValueModel03.setValue((String) house_map.get("GLFL"));
                    nameValueModel_list01.add(nameValueModel03);

                    nameDataModel01.setData(nameValueModel_list01);

                    nameDataModel02.setName("人口信息");
                    String sql_house_pop = "select XM,LXFS,SFZH,JG,HJS from BLK_POPULATION where HID = '"+house_map.get("FWDM")+"'";
                    List<LinkedHashMap> list_build_house_pop = oracleOperateService.querySql(sql_house_pop);
                    for (LinkedHashMap map_house_pop : list_build_house_pop) {
                        LinkedHashMap map_pop = new LinkedHashMap();
                        map_pop.put("name", map_house_pop.get("XM"));
                        map_pop.put("tel", map_house_pop.get("LXFS"));
                        map_pop.put("idcode", map_house_pop.get("SFZH"));
                        pop_list.add(map_pop);
                    }
                    nameDataModel02.setData(pop_list);
                    nameDataModel_list.add(nameDataModel01);
                    nameDataModel_list.add(nameDataModel02);
                buildHouseModel.setData(nameDataModel_list);
                buildHouseModel_list.add(buildHouseModel);
            }
            layerData.setData(buildHouseModel_list);
            layerData_list.add(layerData);
        }
        layerNumData.setData(layerData_list);
        return layerNumData;
    }
}
*/
