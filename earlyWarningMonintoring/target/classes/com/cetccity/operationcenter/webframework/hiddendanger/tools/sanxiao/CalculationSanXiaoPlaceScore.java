package com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.*;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.SanXiaoTip;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.LecMethodUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.BuildBasicToCache;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;


/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools
 * 项目名称:   futian-EarlyWarningMonitoring
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 15:28 2019-01-15
 * Updater:     heliangming
 * Update_Date：15:28 2019-01-15
 * 更新描述:    heliangming 补充
 **/
@Component
public class CalculationSanXiaoPlaceScore {

    @Autowired
    BuildBasicToolToCache_SanXiao buildBasicToolToCacheSanXiao;

    @Autowired
    EmergencyScoreToolToCache_SanXiao emergencyScoreToolToCache_SanXiao;

    @Autowired
    BuildSurroundingEnvironmentToolToCache_SanXiao buildSurroundingEnvironmentToolToCacheSanXiao;

    @Autowired
    BuildLegal_PersonToolToCache_SanXiao buildLegal_PersonToolToCache_SanXiao;

    @Autowired
    EscapeScoreToolToCache_SanXiao escapeScoreToolToCache_SanXiao;

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    BuildBasicToCache buildBasicToCache;

    public NameDataModel calculationScore(String sanxiao_id,String buildid){
        Map<String ,String> Attribute_Two = new HashMap();//Map二级属性<名-值>
        /*一、获取建筑基础属性 30%*/
        String scoreBasic[] = {"score_001001","score_001002","score_001003","score_001004","score_001005"};
        String valueBasic[] = {"value_001001","value_001002","value_001003","value_001004","value_001005"};
        String scoreBasic_vaule[] ={"","","","",""};String valueBasic_vaule[] = {"","","","",""};
        for(int i=0;i<scoreBasic.length;i++){
            scoreBasic_vaule[i] = buildBasicToolToCacheSanXiao.buildBasicScoreToCache(buildid).get(scoreBasic[i]);
            valueBasic_vaule[i] = buildBasicToolToCacheSanXiao.buildBasicScoreToCache(buildid).get(valueBasic[i]);
            Attribute_Two.put(scoreBasic[i],scoreBasic_vaule[i]);Attribute_Two.put(valueBasic[i],valueBasic_vaule[i]);
        }

        /*二、环境风险 50%*/
        String scoreSurround[] = {"score_002001","score_002002","score_002003"};
        String valueSurround[] = {"value_002001","value_002002","value_002003"};
        String scoreSurround_vaule[] ={"","",""};String valueSurround_vaule[] = {"","",""};
        for (int i = 0;i<scoreSurround.length;i++){
            scoreSurround_vaule[i] = buildSurroundingEnvironmentToolToCacheSanXiao.buildSurroundingEnvironmentScoreToCache().get(scoreSurround[i]);
            valueSurround_vaule[i] = buildSurroundingEnvironmentToolToCacheSanXiao.buildSurroundingEnvironmentScoreToCache().get(valueSurround[i]);
            Attribute_Two.put(scoreSurround[i],scoreSurround_vaule[i]);Attribute_Two.put(valueSurround[i],valueSurround_vaule[i]);
        }

        /*三、物联网信息 10%*/

        /*四、法人风险 10%*/
        String scoreLegal[] = {"score_004001","score_004002","score_004003"};
        String valueLegal[] = {"value_004001","value_004002","value_004003"};
        String scoreLegal_vaule[] ={"","",""};String valueLegal_vaule[] = {"","",""};
        for(int i=0;i<scoreLegal.length;i++){
            scoreLegal_vaule[i] = buildLegal_PersonToolToCache_SanXiao.buildLegal_PersonScoreToCache(sanxiao_id).get(scoreLegal[i]);
            valueLegal_vaule[i] = buildLegal_PersonToolToCache_SanXiao.buildLegal_PersonScoreToCache(sanxiao_id).get(valueLegal[i]);
            Attribute_Two.put(scoreLegal[i],scoreLegal_vaule[i]);Attribute_Two.put(valueLegal[i],valueLegal_vaule[i]);
        }

        /*五、逃生难易度 10%*/
        String scoreEscape[] = {"score_005001","score_005002","score_005003","score_005004"};
        String valueEscape[] = {"value_005001","value_005002","value_005003","value_005004"};
        String scoreEscape_vaule[] ={"","","",""};String valueEscape_vaule[] = {"","","",""};
        for(int i =0;i<scoreEscape.length;i++){
            scoreEscape_vaule[i] = escapeScoreToolToCache_SanXiao.buildEscapeScoreToCache(buildid).get(scoreEscape[i]);
            valueEscape_vaule[i] = escapeScoreToolToCache_SanXiao.buildEscapeScoreToCache(buildid).get(valueEscape[i]);
            Attribute_Two.put(scoreEscape[i],scoreEscape_vaule[i]);Attribute_Two.put(valueEscape[i],valueEscape_vaule[i]);
        }

        /*六、事故应急处理能力 10%*/
        String scoreEmergency[] = {"score_006001","score_006002","score_006003","score_006004"};
        String valueEmergency[] = {"value_006001","value_006002","value_006003","value_006004"};
        String scoreEmergency_vaule[] ={"","","",""};String valueEmergency_vaule[] = {"","","",""};
        for(int i =0;i<scoreEmergency.length;i++){
            scoreEmergency_vaule[i] = emergencyScoreToolToCache_SanXiao.buildEmergencyScoreToCache(buildid).get(scoreEmergency[i]);
            valueEmergency_vaule[i] = emergencyScoreToolToCache_SanXiao.buildEmergencyScoreToCache(buildid).get(valueEmergency[i]);
            Attribute_Two.put(scoreEmergency[i],scoreEmergency_vaule[i]);Attribute_Two.put(valueEmergency[i],valueEmergency_vaule[i]);
        }

        NameDataModel nameDataModel_return = new NameDataModel();
        /* String sql_Attribute_One = "select MAIN_ID,NAME,PID FROM SAN_XIAO_PLACE_SCORE_WEIGHT WHERE (PID = '01' or PID = '02' or PID = '03')";
        List<LinkedHashMap> list_Attribute_One = oracleOperateService.querySql(sql_Attribute_One);
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        for (LinkedHashMap map_Attribute_One:list_Attribute_One) {
            if("003".equals(map_Attribute_One.get("MAIN_ID"))) continue;
            NameDataModel nameDataModel = new NameDataModel();
            nameDataModel.setName((String)map_Attribute_One.get("NAME"));
            String main_id = (String)map_Attribute_One.get("MAIN_ID");
            String sql_Attribute_Two = "select MAIN_ID,NAME,PID FROM SAN_XIAO_PLACE_SCORE_WEIGHT WHERE PID = '"+main_id+"'";
            List<LinkedHashMap> list_Attribute_Two = oracleOperateService.querySql(sql_Attribute_Two);
            List<NameScoreValueModel> nameScoreValueModel_list = new ArrayList<NameScoreValueModel>();
            for (LinkedHashMap map_Attribute_Two:list_Attribute_Two) {
                NameScoreValueModel nameScoreValueModel = new NameScoreValueModel();
                nameScoreValueModel.setName((String)map_Attribute_Two.get("NAME"));
                nameScoreValueModel.setScore(Double.valueOf(Attribute_Two.get("score_"+map_Attribute_Two.get("MAIN_ID"))));
                nameScoreValueModel.setValue(Attribute_Two.get("value_"+map_Attribute_Two.get("MAIN_ID")));
                nameScoreValueModel_list.add(nameScoreValueModel);
            }
            nameDataModel.setData(nameScoreValueModel_list);
            nameDataModel_list.add(nameDataModel);
        }*/
        NameMapDataModel nameMapDataModel = calculationTotalScore(sanxiao_id, buildid);
        String sql_Attribute_Three = "select MAIN_ID,NAME,PID FROM SAN_XIAO_PLACE_SCORE_WEIGHT WHERE PID = '0'";
        List<LinkedHashMap> list_Attribute_Three = oracleOperateService.querySql(sql_Attribute_Three);
        List<NameDataModel> nameDataModel_list_Three = new ArrayList<>();
        for(LinkedHashMap map_Attribute_Three : list_Attribute_Three){
            String main_id = (String)map_Attribute_Three.get("MAIN_ID");
            NameDataModel nameDataModel = new NameDataModel();
            nameDataModel.setName(map_Attribute_Three.get("NAME")+" "+nameMapDataModel.getMap().get("score_"+main_id));

            String sql_Attribute_Four = "select MAIN_ID,NAME,PID FROM SAN_XIAO_PLACE_SCORE_WEIGHT WHERE PID = '"+main_id+"'";
            List<LinkedHashMap> list_Attribute_Four = oracleOperateService.querySql(sql_Attribute_Four);
            List<NameDataModel> nameDataModelList = new ArrayList<>();
            for (LinkedHashMap map_Attribute_Four:list_Attribute_Four) {
                if("003".equals(map_Attribute_Four.get("MAIN_ID"))) continue;
                String main_id2 = (String)map_Attribute_Four.get("MAIN_ID");
                NameDataModel nameDataModel2 = new NameDataModel();
                nameDataModel2.setName(map_Attribute_Four.get("NAME")+" "+nameMapDataModel.getMap().get("score_"+main_id2));
                String sql_Attribute_Two = "select MAIN_ID,NAME,PID FROM SAN_XIAO_PLACE_SCORE_WEIGHT WHERE PID = '"+main_id2+"'";
                List<LinkedHashMap> list_Attribute_Five = oracleOperateService.querySql(sql_Attribute_Two);
                List<NameScoreValueModel> nameScoreValueModel_list = new ArrayList<>();
                for (LinkedHashMap map_Attribute_Five: list_Attribute_Five){
                    NameScoreValueModel nameScoreValueModel = new NameScoreValueModel();
                    nameScoreValueModel.setName((String)map_Attribute_Five.get("NAME"));
                    nameScoreValueModel.setScore(Double.valueOf(Attribute_Two.get("score_"+map_Attribute_Five.get("MAIN_ID"))));
                    nameScoreValueModel.setValue(Attribute_Two.get("value_"+map_Attribute_Five.get("MAIN_ID")));
                    nameScoreValueModel_list.add(nameScoreValueModel);
                }
                nameDataModel2.setData(nameScoreValueModel_list);
                nameDataModelList.add(nameDataModel2);
            }
            nameDataModel.setData(nameDataModelList);
            nameDataModel_list_Three.add(nameDataModel);
        }
        nameDataModel_return.setName("三小场所评分总分: "+nameMapDataModel.getName());
        nameDataModel_return.setData(nameDataModel_list_Three);
        return nameDataModel_return;
    }

    public SanXiaoTip calculationAttribute_OneScore(String sanxiao_id,String buildid){
        //获取三小场所名称
        String sql = "select NAME from BLK_SANXIAO_PLACE WHERE ID = '"+sanxiao_id+"'";
        oracleOperateService.querySql(sql);
        SanXiaoTip sanXiaoTip = new SanXiaoTip();
        NameDataModel nameDataModel = new NameDataModel();
        nameDataModel.setName("风险隐患评分"+calculationTotalScore(sanxiao_id,buildid).getName());
        nameDataModel.setData(calculationTotalScore(sanxiao_id,buildid).getData());
        sanXiaoTip.setTitle((String) oracleOperateService.querySql(sql).get(0).get("NAME"));
        sanXiaoTip.setInfo_alert("0");
        sanXiaoTip.setHasDetailInfo("true");
        sanXiaoTip.setData(nameDataModel);
        return sanXiaoTip;
    }

    public NameMapDataModel calculationTotalScore(String sanxiao_id,String buildid){
        double score_001 = Double.valueOf(buildBasicToolToCacheSanXiao.buildBasicScoreToCache(buildid).get("score_001"));
        double score_002 = Double.valueOf(buildSurroundingEnvironmentToolToCacheSanXiao.buildSurroundingEnvironmentScoreToCache().get("score_002"));
        double score_004 = Double.valueOf(buildLegal_PersonToolToCache_SanXiao.buildLegal_PersonScoreToCache(sanxiao_id).get("score_004"));
        double score_005 = Double.valueOf(escapeScoreToolToCache_SanXiao.buildEscapeScoreToCache(buildid).get("score_005"));
        double score_006 = Double.valueOf(emergencyScoreToolToCache_SanXiao.buildEmergencyScoreToCache(buildid).get("score_006"));
        Map mapReturn = new HashMap();
        mapReturn.put("score_001",LoadMyUtil.retainToPoint(score_001,2));
        mapReturn.put("score_002",LoadMyUtil.retainToPoint(score_002,2));
        mapReturn.put("score_004",LoadMyUtil.retainToPoint(score_004,2));
        mapReturn.put("score_005",LoadMyUtil.retainToPoint(score_005,2));
        mapReturn.put("score_006",LoadMyUtil.retainToPoint(score_006,2));
        String sql_Attribute_One = "select MAIN_ID,NAME,WEIGHT,PID FROM SAN_XIAO_PLACE_SCORE_WEIGHT WHERE (PID = '01' or PID = '02' or PID = '03')";
        List<LinkedHashMap> list_Attribute_One = oracleOperateService.querySql(sql_Attribute_One);
        Map<String,Double> map_weight = new HashMap();
        for (LinkedHashMap map:list_Attribute_One) {
            map_weight.put((String) map.get("MAIN_ID"),Double.valueOf((String) map.get("WEIGHT")));
        }
        /*Map<String,String> map_id_name = new HashMap();
        list_Attribute_One.stream().forEach(u-> map_id_name.put((String)u.get("MAIN_ID"),(String)u.get("NAME")));
        double score = score_001*map_weight.get("001")+score_002*map_weight.get("002")+score_004*map_weight.get("004")+score_005*map_weight.get("005")+score_006*map_weight.get("006");
        double score_return = LoadMyUtil.retainToPoint(score,2);*/

        double score_01 = score_001*map_weight.get("001") +score_002*map_weight.get("002")+score_004*map_weight.get("004");
        double score_02 = score_005*map_weight.get("005");
        double score_03 = score_006*map_weight.get("006");
        NameScoreDataModel nameScoreDataModel = LecMethodUtil.getLecScore(score_01 ,score_02 ,score_03);
        List<NameValueModel> list = (List<NameValueModel>)nameScoreDataModel.getData();
        //LEC法则分数值
        mapReturn.put("score_01",list.get(0).getValue());
        mapReturn.put("score_02",list.get(1).getValue());
        mapReturn.put("score_03",list.get(2).getValue());
        mapReturn.put("score",nameScoreDataModel.getScore());

        NameMapDataModel nameMapDataModel = new NameMapDataModel();
        nameMapDataModel.setName(nameScoreDataModel.getScore());
        nameMapDataModel.setMap(mapReturn);
        nameMapDataModel.setData(nameScoreDataModel.getData());
        return nameMapDataModel;
    }
}
