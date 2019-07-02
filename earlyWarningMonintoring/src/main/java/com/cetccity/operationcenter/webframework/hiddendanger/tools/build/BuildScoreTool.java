package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameCodeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameScoreValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.BuildScoreMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.BuildScoreWeightMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BUILD_SCORE;
import com.cetccity.operationcenter.webframework.web.model.build.BuildScoreModel;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Slf4j
@Component
public class BuildScoreTool {

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    BuildBasicToolToCache buildBasicToolToCache;

    @Autowired
    BuildSecurityManagementToolToCache buildSecurityManagementToolToCache;

    @Autowired
    BuildPurposeToolToCache buildPurposeToolToCache;

    @Autowired
    BuildSurroundingEnvironmentToolToCache buildSurroundingEnvironmentToolToCache;

    @Autowired
    BuildBasicToCache buildBasicToCache;

    @Autowired
    BuildSecurityManagementToCache buildSecurityManagementToCache;

    @Autowired
    BuildSurroundingEnvironmentToCache buildSurroundingEnvironmentToCache;

    @Autowired
    BuildPurposeToCache buildPurposeToCache;

    @Autowired
    BuildAttributeRateForOneToCache buildAttributeRateForOneToCache;

    @Autowired
    BuildScoreMapper bUILD_SCOREMapper;

    @Autowired
    BuildScoreWeightMapper bUILD_SCORE_WEIGHTMapper;

    static List<BUILD_SCORE> bUILD_SCORE_list = new ArrayList<>();

    public BuildScoreModel calculationBuildScore(String buildid) {

        String current = LoadMyUtil.getMyTime("DATE", 0);
        BuildScoreModel buildScoreModel = new BuildScoreModel();
        Map<String, String> map_basic_score = buildBasicToolToCache.buildBasicScoreToCache(buildid);
        Map<String, String> map_security_management_score = buildSecurityManagementToolToCache.buildSecurityManagementScoreToCache(buildid);
        Map<String, String> map_surroundingEnvironment_score = buildSurroundingEnvironmentToolToCache.buildSurroundingEnvironmentScoreToCache();
        Map<String, String> map_purpose_score = buildPurposeToolToCache.buildPurposeScoreToCache(buildid);

        /*各项指标得分开始*/
        //一、建筑基础属性 35% 评分
        double score_001 = Math.round(Double.valueOf(map_basic_score.get("score_001")));
        //1.1、建筑基础属性--楼栋年龄评分 50%
        double score_001001 = Double.valueOf(map_basic_score.get("score_001001"));
        String value_001001 = map_basic_score.get("value_001001");
        //1.2、建筑基础属性--建筑高度评分 10%
        double score_001002 = Double.valueOf(map_basic_score.get("score_001002"));
        String value_001002 = map_basic_score.get("value_001002");
        //1.3、建筑基础属性--耐火等级评分 20%
        double score_001003 = Double.valueOf(map_basic_score.get("score_001003"));
        String value_001003 = map_basic_score.get("value_001003");
        //1.4、建筑基础属性--隐患等级评分 10%
        double score_001004 = Double.valueOf(map_basic_score.get("score_001004"));
        String value_001004 = map_basic_score.get("value_001004");
        //二、建筑火灾安全管理 40%  评分
        double score_002 = Math.round(Double.valueOf(map_security_management_score.get("score_002")));
        //2.1、建筑火灾安全管理--火灾报警器情况 25%
        double score_002001 = Double.valueOf(map_security_management_score.get("score_002001"));
        String value_002001 = map_security_management_score.get("value_002001");
        //2.2、建筑火灾安全管理--消防控制室 25%
        double score_002002 = Double.valueOf(map_security_management_score.get("score_002002"));
        String value_002002 = map_security_management_score.get("value_002002");
        //2.3、建筑火灾安全管理--消防设备完好度 25%
        double score_002003 = Double.valueOf(map_security_management_score.get("score_002003"));
        String value_002003 = map_security_management_score.get("value_002003");
        //2.4、建筑火灾安全管理--消防巡查 25%
        double score_002004 = Double.valueOf(map_security_management_score.get("score_002004"));
        String value_002004 = map_security_management_score.get("value_002004");
        //三、建筑用途信息 15%
        double score_003 = Math.round(Double.valueOf(map_purpose_score.get("score_003")));
        //3.1、建筑用途信息--建筑用途 100%
        double score_003001 = Double.valueOf(map_purpose_score.get("score_003001"));
        String value_003001 = map_purpose_score.get("value_003001");
        //四、周边环境 10%
        double score_004 = Math.round(Double.valueOf(map_surroundingEnvironment_score.get("score_004")));
        //4.1、周边环境--天气-天气状况 50%
        double score_004001 = Double.valueOf(map_surroundingEnvironment_score.get("score_004001"));
        String value_004001 = map_surroundingEnvironment_score.get("value_004001");
        //4.2、周边环境--天气-温差 30%
        double score_004002 = Double.valueOf(map_surroundingEnvironment_score.get("score_004002"));
        String value_004002 = map_surroundingEnvironment_score.get("value_004002");
        //4.3、周边环境--天气-风级 20%
        double score_004003 = Double.valueOf(map_surroundingEnvironment_score.get("score_004003"));
        String value_004003 = map_surroundingEnvironment_score.get("value_004003");
        /*各项指标得分结束*/

        Integer score_build;
        String dangerGrader,dangerLevel,colour_level;

        //计算比例后的分数值
        Map<String,Float> map_attribute_build_One_information = buildAttributeRateForOneToCache.map_attribute_build_One_information;
        float rate_001 = map_attribute_build_One_information.get("001");  //建筑基础属性
        float rate_002 = map_attribute_build_One_information.get("002");//建筑火灾安全管理
        float rate_003 = map_attribute_build_One_information.get("003");//建筑用途
        float rate_004 = map_attribute_build_One_information.get("004");//周边环境

        Map<String,Float> map_attribute_build_basic_information = buildBasicToCache.map_attribute_build_basic_information;
        float attribute_001001 = map_attribute_build_basic_information.get("001001");
        float attribute_001002 = map_attribute_build_basic_information.get("001002");
        float attribute_001003 = map_attribute_build_basic_information.get("001003");
        float attribute_001004 = map_attribute_build_basic_information.get("001004");

        Map<String ,Float> map_attribute_build_SecurityManagement_information = buildSecurityManagementToCache.map_attribute_build_SecurityManagement_information;
        float attribute_002001 = map_attribute_build_SecurityManagement_information.get("002001");
        float attribute_002002 = map_attribute_build_SecurityManagement_information.get("002002");
        float attribute_002003 = map_attribute_build_SecurityManagement_information.get("002003");
        float attribute_002004 = map_attribute_build_SecurityManagement_information.get("002004");

        Map<String,Float> map_attribute_build_Purpose_information = buildPurposeToCache.map_attribute_build_Purpose_information;
        float attribute_003001 = map_attribute_build_Purpose_information.get("003001");

        Map<String,Float> map_attribute_build_SurroundingEnvironment_information = buildSurroundingEnvironmentToCache.map_attribute_build_Weather_information;
        float attribute_004001 = map_attribute_build_SurroundingEnvironment_information.get("004001");
        float attribute_004002 = map_attribute_build_SurroundingEnvironment_information.get("004002");
        float attribute_004003 = map_attribute_build_SurroundingEnvironment_information.get("004003");

        double score_age_rate = score_001001 * rate_001 * attribute_001001;
        double score_height_rate = score_001002 * rate_001 * attribute_001002;
        double score_fireResistance_rate = score_001003 * rate_001 * attribute_001003;
        double score_fireGrade_rate = score_001004 * rate_001 * attribute_001004;
        double score_fire_alarm_rate = score_002001 * rate_002 * attribute_002001;
        double score_fire_control_room_rate = score_002002 * rate_002 * attribute_002002;
        double score_fire_equipment_rate = score_002003 * rate_002 * attribute_002003;
        double score_fire_patrol_rate = score_002004 * rate_002 * attribute_002004;
        double score_build_purpose_rate = score_003001 * rate_003 * attribute_003001;
        double score_build_report_rate = score_004001 * rate_004 * attribute_004001;
        double score_build_temperatureDifference_rate = score_004002 * rate_004 * attribute_004002;
        double score_build_ws_rate = score_004003 * rate_004 * attribute_004003;

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("score_age", (int) score_age_rate);
        map.put("score_height", (int) score_height_rate);
        map.put("score_fireResistance", (int) score_fireResistance_rate);
        map.put("score_fireGrade", (int) score_fireGrade_rate);
        map.put("score_fire_alarm", (int) score_fire_alarm_rate);
        map.put("score_fire_control_room", (int) score_fire_control_room_rate);
        map.put("score_fire_equipment", (int) score_fire_equipment_rate);
        map.put("score_fire_patrol", (int) score_fire_patrol_rate);
        map.put("score_build_purpose", (int) score_build_purpose_rate);
        map.put("score_build_report", (int) score_build_report_rate);
        map.put("score_build_temperatureDifference", (int) score_build_temperatureDifference_rate);
        map.put("score_build_ws", (int) score_build_ws_rate);

        //map排序
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(
                map.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        /*System.out.println("--------------排序后--------------");
        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, Integer> ent = infoIds.get(i);
            System.out.println(ent.getKey() + "=" + ent.getValue());
        }*/

        Map.Entry<String, Integer> ent0 = infoIds.get(0);
        Map.Entry<String, Integer> ent1 = infoIds.get(1);
        Map<String, String> map_MainDanger = new HashMap<String, String>();
        map_MainDanger.put("score_age", "楼龄超过20年以上");
        map_MainDanger.put("score_height", "建筑高度超过100以上");
        map_MainDanger.put("score_fireResistance", "耐火等级为四级，风险等级较高");
        map_MainDanger.put("score_fireGrade", "隐患等级较高");
        map_MainDanger.put("score_fire_alarm", "消防设施种类较少");
        map_MainDanger.put("score_fire_control_room", "无消防控制室");
        map_MainDanger.put("score_fire_equipment", "建筑消防设备故障较多或无消防设备数据");
        map_MainDanger.put("score_fire_patrol", "消防巡查次数较少");
        map_MainDanger.put("score_build_purpose", "建筑用途有风险");
        map_MainDanger.put("score_build_report", "天气原因");
        map_MainDanger.put("score_build_temperatureDifference", "天气原因");
        map_MainDanger.put("score_build_ws", "天气原因");

        String mainDanger = map_MainDanger.get(ent0.getKey())+";"+map_MainDanger.get(ent1.getKey())+";";

        /*获取建筑基础属性 30%    建筑火灾安全管理 50%    建筑用途 10%    周边环境10%*/
        score_build = (int) (rate_001 * score_001 + rate_002 * score_002 + rate_003 * score_003 + rate_004 * score_004);

        //绿-1、蓝-2、黄-3、橙-4、红-5
        if (score_build <= 20) {
            dangerGrader = "低风险";dangerLevel = "1";colour_level = "0, 194, 18";
        } else if (score_build > 20 && score_build <= 60) {
            dangerGrader = "中低风险";dangerLevel = "2";colour_level = "0, 162, 254";
        } else if(score_build >60 && score_build <= 70){
            dangerGrader = "中风险";dangerLevel = "3";colour_level = "254, 254, 0";
        }   else if(score_build >70 && score_build < 80){
            dangerGrader = "中高风险";dangerLevel = "4";colour_level = "254, 162, 0";
        }else{
            dangerGrader = "高风险";dangerLevel = "5";colour_level = "254, 0, 0";
        }

        List<LinkedHashMap> map_score_list = new ArrayList<LinkedHashMap>();

        List<NameValueModel> list_name_build_One_information = buildAttributeRateForOneToCache.list_name_build_One_information;
        Map<String,String> map_score_one_attribute = new HashMap();
        map_score_one_attribute.put("score_001",String.valueOf(score_001));map_score_one_attribute.put("score_002",String.valueOf(score_002));
        map_score_one_attribute.put("score_003",String.valueOf(score_003));map_score_one_attribute.put("score_004",String.valueOf(score_004));
        map_score_one_attribute.put("score_005","0");map_score_one_attribute.put("score_006","0");
        for(NameValueModel nameValueModel:list_name_build_One_information){
            LinkedHashMap<String, String> map_return = new LinkedHashMap<String, String>();
            map_return.put("name",nameValueModel.getValue());
            map_return.put("value",map_score_one_attribute.get("score_"+nameValueModel.getName()));
            map_score_list.add(map_return);
        }

        buildScoreModel.setDangerScore(String.valueOf(score_build));
        buildScoreModel.setDangerGrader(dangerGrader);
        buildScoreModel.setColour(colour_level);
        buildScoreModel.setColourLevel(dangerLevel);
        buildScoreModel.setTable(map_score_list);
        if (score_build>=20) {
            buildScoreModel.setMainDanger(mainDanger);
        }else{
            buildScoreModel.setMainDanger("无隐患");
        }
        //入库部分
        List<NameDataModel> nameDataModel_list = new ArrayList<>();
        List<NameCodeDataModel> nameCodeDataModel_list = new ArrayList<>();
        //1、建筑基础属性
        NameCodeDataModel nameCodeDataModel_basic = new NameCodeDataModel();
        nameCodeDataModel_basic.setName("建筑基础属性");
        nameCodeDataModel_basic.setCode(String.valueOf(score_001));
        String attOneName[] = {"楼栋年龄","建筑高度","耐火等级","隐患等级"};
        Double attOneScore[] = {score_001001,score_001002,score_001003,score_001004};
        String attOneValue[] = {value_001001,value_001002,value_001003,value_001004};
        List<NameScoreValueModel> nameScoreValueModel_attOne = new ArrayList<>();
        for(int i =0;i<attOneName.length;i++) {
            NameScoreValueModel nameScoreValueModel = new NameScoreValueModel();
            nameScoreValueModel.setName(attOneName[i]);
            nameScoreValueModel.setScore(attOneScore[i]);
            nameScoreValueModel.setValue(attOneValue[i]);
            nameScoreValueModel_attOne.add(nameScoreValueModel);
        }
        nameCodeDataModel_basic.setData(nameScoreValueModel_attOne);
        nameCodeDataModel_list.add(nameCodeDataModel_basic);
        //2、建筑火灾安全管理
        NameCodeDataModel nameCodeDataModel_security = new NameCodeDataModel();
        nameCodeDataModel_security.setName("建筑火灾安全管理");
        nameCodeDataModel_security.setCode(String.valueOf(score_002));
        String attTwoName[] = {"火灾报警器情况","消防控制室","消防设备完好度","消防巡查"};
        Double attTwoScore[] = {score_002001,score_002002,score_002003,score_002004};
        String attTwoValue[] = {value_002001,value_002002,value_002003,value_002004};
        List<NameScoreValueModel> nameScoreValueModel_attTwo = new ArrayList<>();
        for(int i =0;i<attTwoName.length;i++) {
            NameScoreValueModel nameScoreValueModel = new NameScoreValueModel();
            nameScoreValueModel.setName(attTwoName[i]);
            nameScoreValueModel.setScore(attTwoScore[i]);
            nameScoreValueModel.setValue(attTwoValue[i]);
            nameScoreValueModel_attTwo.add(nameScoreValueModel);
        }
        nameCodeDataModel_security.setData(nameScoreValueModel_attTwo);
        nameCodeDataModel_list.add(nameCodeDataModel_security);
        //3、建筑用途信息
        NameCodeDataModel nameCodeDataModel_purpose = new NameCodeDataModel();
        nameCodeDataModel_purpose.setName("建筑用途");
        nameCodeDataModel_purpose.setCode(String.valueOf(score_003));
        String attThreeName[] = {"建筑用途"};
        Double attThreeScore[] = {score_003001};
        String attThreeValue[] = {value_003001};
        List<NameScoreValueModel> nameScoreValueModel_attThree = new ArrayList<>();
        for(int i =0;i<attThreeName.length;i++) {
            NameScoreValueModel nameScoreValueModel = new NameScoreValueModel();
            nameScoreValueModel.setName(attThreeName[i]);
            nameScoreValueModel.setScore(attThreeScore[i]);
            nameScoreValueModel.setValue(attThreeValue[i]);
            nameScoreValueModel_attThree.add(nameScoreValueModel);
        }
        nameCodeDataModel_purpose.setData(nameScoreValueModel_attThree);
        nameCodeDataModel_list.add(nameCodeDataModel_purpose);
        //4、周边环境
        NameCodeDataModel nameCodeDataModel_surroundingEnvironment = new NameCodeDataModel();
        nameCodeDataModel_surroundingEnvironment.setName("周边环境");
        nameCodeDataModel_surroundingEnvironment.setCode(String.valueOf(score_004));
        String attFourName[] = {"天气状况","温差","风级",};
        Double attFourScore[] = {score_004001,score_004002,score_004003};
        String attFourValue[] = {value_004001,value_004002,value_004003};
        List<NameScoreValueModel> nameScoreValueModel_attFour = new ArrayList<>();
        for(int i =0;i<attFourName.length;i++) {
            NameScoreValueModel nameScoreValueModel = new NameScoreValueModel();
            nameScoreValueModel.setName(attFourName[i]);
            nameScoreValueModel.setScore(attFourScore[i]);
            nameScoreValueModel.setValue(attFourValue[i]);
            nameScoreValueModel_attFour.add(nameScoreValueModel);
        }
        nameCodeDataModel_surroundingEnvironment.setData(nameScoreValueModel_attFour);
        nameCodeDataModel_list.add(nameCodeDataModel_surroundingEnvironment);
        //5、法人
        NameCodeDataModel nameCodeDataModel_legal = new NameCodeDataModel();
        nameCodeDataModel_legal.setName("法人");
        nameCodeDataModel_legal.setCode("0");
        String attFiveName[] = {"法人经营业务","是否有三小场所","消防隐患历史情况"};
        Double attFiveScore[] = {0.0,0.0,0.0};
        String attFiveValue[] = {"","",""};
        List<NameScoreValueModel> nameScoreValueModel_attFive = new ArrayList<>();
        for(int i =0;i<attFiveName.length;i++) {
            NameScoreValueModel nameScoreValueModel = new NameScoreValueModel();
            nameScoreValueModel.setName(attFiveName[i]);
            nameScoreValueModel.setScore(attFiveScore[i]);
            nameScoreValueModel.setValue(attFiveValue[i]);
            nameScoreValueModel_attFive.add(nameScoreValueModel);
        }
        nameCodeDataModel_legal.setData(nameScoreValueModel_attFive);
        nameCodeDataModel_list.add(nameCodeDataModel_legal);
        //5、物联网
        NameCodeDataModel nameCodeDataModel_iot = new NameCodeDataModel();
        nameCodeDataModel_iot.setName("物联网");
        nameCodeDataModel_iot.setCode("0");
        String attSixName[] = {"温感","电器监测","视频","粉尘报警"};
        Double attSixScore[] = {0.0,0.0,0.0,0.0};
        String attSixValue[] = {"","","",""};
        List<NameScoreValueModel> nameScoreValueModel_attSix = new ArrayList<>();
        for(int i =0;i<attSixName.length;i++) {
            NameScoreValueModel nameScoreValueModel = new NameScoreValueModel();
            nameScoreValueModel.setName(attSixName[i]);
            nameScoreValueModel.setScore(attSixScore[i]);
            nameScoreValueModel.setValue(attSixValue[i]);
            nameScoreValueModel_attSix.add(nameScoreValueModel);
        }
        nameCodeDataModel_iot.setData(nameScoreValueModel_attSix);
        nameCodeDataModel_list.add(nameCodeDataModel_iot);

        //-------入库
        NameCodeDataModel nameCodeDataModel = new NameCodeDataModel();
        nameCodeDataModel.setName("建筑风险评分详情");
        nameCodeDataModel.setCode(String.valueOf(score_build));
        nameCodeDataModel.setData(nameCodeDataModel_list);
        BUILD_SCORE save = new BUILD_SCORE();
        save.setBUILD_ID(buildid);
        save.setDANGER_LEVEL(dangerLevel);
        save.setSCORE(String.valueOf(score_build));
        save.setCALCULATION_TIME(current);
        save.setMAINDANGER(mainDanger);
        save.setSCORE_DETAIL(JsonUtils.beanToJson(nameCodeDataModel));
        bUILD_SCOREMapper.saveBuildScoreOfBuildid(save);

        return buildScoreModel;
    }

    public BuildScoreModel getBuildScore(String buildid) {

        Integer score_build;
        String dangerGrader,dangerLevel,colour_level;
        String current = LoadMyUtil.getMyTime("DATE", 0);
        List<LinkedHashMap>  buildList = oracleOperateService.querySql("select BUILD_NAME from T_BUILD_INFO_V where BUILDID = '"+buildid+"'");
        if(buildList == null || buildList.size() == 0){
        	return null;
        }
        String buildName = (String) buildList.get(0).get("BUILD_NAME");
        BUILD_SCORE bUILD_SCORE = new BUILD_SCORE();
        bUILD_SCORE.setBUILD_ID(buildid);
        bUILD_SCORE.setCALCULATION_TIME(current);
        List<BUILD_SCORE> bUILD_SCORE_list = bUILD_SCOREMapper.getBuildScoreList(bUILD_SCORE);
        BUILD_SCORE bUILD_SCORE_return = bUILD_SCORE_list.get(0);

        score_build = Integer.valueOf(bUILD_SCORE_return.getSCORE());
        //绿-1、蓝-2、黄-3、橙-4、红-5
        if (score_build <= 20) {
            dangerGrader = "低风险";dangerLevel = "1";colour_level = "0, 194, 18";
        } else if (score_build > 20 && score_build <= 60) {
            dangerGrader = "中低风险";dangerLevel = "2";colour_level = "0, 162, 254";
        } else if(score_build >60 && score_build <= 70){
            dangerGrader = "中风险";dangerLevel = "3";colour_level = "254, 254, 0";
        }   else if(score_build >70 && score_build < 80){
            dangerGrader = "中高风险";dangerLevel = "4";colour_level = "254, 162, 0";
        }else{
            dangerGrader = "高风险";dangerLevel = "5";colour_level = "254, 0, 0";
        }
        NameCodeDataModel nameCodeDataModel = JsonUtils.jsonToBean(bUILD_SCORE_return.getSCORE_DETAIL(),NameCodeDataModel.class);
        List<NameCodeDataModel> nameCodeDataModel_list = (List<NameCodeDataModel>)nameCodeDataModel.getData();

        List<LinkedHashMap> map_score_list = new ArrayList<LinkedHashMap>();
        for(Object obj : nameCodeDataModel_list){
            NameCodeDataModel u = JsonUtils.jsonToPojo(JsonUtils.objectToJson(obj),NameCodeDataModel.class);
            LinkedHashMap map = new LinkedHashMap();
            map.put("name", u.getName());
            map.put("value", u.getCode());
            map_score_list.add(map);
        }

        BuildScoreModel buildScoreModel = new BuildScoreModel();
        buildScoreModel.setBuildName(buildName);
        buildScoreModel.setDangerScore(String.valueOf(score_build));
        buildScoreModel.setDangerGrader(dangerGrader);
        buildScoreModel.setColour(colour_level);
        buildScoreModel.setColourLevel(dangerLevel);
        buildScoreModel.setTable(map_score_list);
        buildScoreModel.setMainDanger(bUILD_SCORE_return.getMAINDANGER());
        return buildScoreModel;
    }
}
