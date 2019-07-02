package com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao;

import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.BuildSecurityManagementToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao
 * 项目名称:   futian-EarlyWarningMonitoring
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:17 2019-01-15
 * Updater:     heliangming
 * Update_Date：18:17 2019-01-15
 * 更新描述:    heliangming 补充
 **/
@Component
public class EscapeScoreToolToCache_SanXiao {

    @Autowired
    EscapeScoreTool_SanXiao escapeScoreTool_SanXiao;

    public Map<String ,String> buildEscapeScoreToCache(String buildid){
        Map<String ,String> map_Escape_score = new HashMap<>();
        double score_005;
        Map<String,Long> map = escapeScoreTool_SanXiao.buildscore_00500X(buildid);
        double score_005001 = map.get("score_005001");//儿童数量得分
        double score_005002 = map.get("score_005002");//老人数量得分
        double score_005003 = map.get("score_005003");//孕妇数量得分
        String value_005001 = String.valueOf(map.get("value_005001"));
        String value_005002 = String.valueOf(map.get("value_005002"));
        String value_005003 = String.valueOf(map.get("value_005003"));
        Integer value_005004;
        double score_005004; //消防巡查情况得分
        //消防巡查情况得分
        List<LinkedHashMap> list_build_fire_patrol_score = new ArrayList<>();
        List<LinkedHashMap> list_build_fire_patrol_cache = BuildSecurityManagementToCache.list_build_fire_patrol_information;
        for(int k=0;k<list_build_fire_patrol_cache.size();k++){
            if(buildid.equals(list_build_fire_patrol_cache.get(k).get("BUILD_ID"))){
                list_build_fire_patrol_score.add(list_build_fire_patrol_cache.get(k));
            }
        }
        Integer total = list_build_fire_patrol_score.size();
        score_005004 = escapeScoreTool_SanXiao.buildscore_005004(total);
        value_005004 = total;

        /*建筑基础属性得分 楼房年龄*0.35+建筑高度*0.2+耐火等级*0.2+隐患等级*0.15*/
        score_005 = escapeScoreTool_SanXiao.getEscapeAttributes(score_005001, score_005002, score_005003, score_005004);

        map_Escape_score.put("score_005001",String.valueOf(score_005001));
        map_Escape_score.put("value_005001",String.valueOf(value_005001));
        map_Escape_score.put("score_005002",String.valueOf(score_005002));
        map_Escape_score.put("value_005002",String.valueOf(value_005002));
        map_Escape_score.put("score_005003",String.valueOf(score_005003));
        map_Escape_score.put("value_005003",String.valueOf(value_005003));
        map_Escape_score.put("score_005004",String.valueOf(score_005004));
        map_Escape_score.put("value_005004",String.valueOf(value_005004));
        map_Escape_score.put("score_005",String.valueOf(score_005));
        return map_Escape_score;
    }
}
