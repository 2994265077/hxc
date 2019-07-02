/*
package com.cetccity.operationcenter.webframework.hiddendanger.tools;

import com.cetccity.operationcenter.webframework.web.model.build.BuildScoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class BuildScoreDangerTool {

    @Autowired
    BuildScoreComment buildScoreComment;

    public BuildScoreModel getBuildScore(String buildid) {
        BuildScoreModel buildScoreModel = new BuildScoreModel();
        buildScoreComment.getBuildScoreAttribute(buildid);
        float TOTALSCORE = Float.valueOf(buildScoreComment.map_BuildScoreAttribute.get("TOTALSCORE")); //总分
        String dangerGrader;
        List<Map> map_score_list = new ArrayList<>();
        String buildAttribute[] = {"建筑基础属性","建筑火灾安全管理","建筑用途","天气","周边危险品","消防救援能力","社会消防管理力量"};

        String weather_quota = buildScoreComment.map_BuildScoreAttribute.get("weather_quota"); //天气（10%）
        String build_purpose_quota = buildScoreComment.map_BuildScoreAttribute.get("build_purpose_quota");//建筑用途（10%）-BUILDERUSESCORE
        String basic_quota = buildScoreComment.map_BuildScoreAttribute.get("basic_quota");//建筑基础属性 30%-
        String security_management_quota = buildScoreComment.map_BuildScoreAttribute.get("security_management_quota");//建筑火灾安全管理50%

        String buildAttributeVaule[] = {basic_quota,security_management_quota,build_purpose_quota,weather_quota,"0","0","0"};

        for(int i = 0;i<buildAttribute.length;i++){
            Map map_attribute = new HashMap();
            map_attribute.put(buildAttribute[i],buildAttributeVaule[i]);
            map_score_list.add(map_attribute);
        }
        */
/*20分以下      低风险        楼栋较新，消防措施配备较全，每天巡查，周边环境较好          列入监控范围
        20-60分      中低风险       楼栋较新，消防措施配备不齐全                            引导配备齐全消防设备
        60-70分      中风险        楼栋旧，消防设置配备相对齐全                             加强监控和巡查
        70-80分      中高风险       楼栋旧，消防设置配备相对齐全， 无巡查，火灾隐患周边有高危    引导整改
        80-100分     高风险         楼栋旧，消防配备不齐全，周边有 高危隐患，无巡查             与楼主或物业约谈*//*

        if (TOTALSCORE <= 20) {
            dangerGrader = "低风险";
            buildScoreModel.setMainDanger("楼栋较新，消防措施配备较全，每天巡查，周边环境较好--列入监控范围");
        } else if (TOTALSCORE > 20 && TOTALSCORE <= 60) {
            dangerGrader = "中低风险";
            buildScoreModel.setMainDanger("楼栋较新，消防措施配备不齐全--引导配备齐全消防设备");
        } else if(TOTALSCORE >60 && TOTALSCORE <= 70){
            dangerGrader = "中风险";
            buildScoreModel.setMainDanger("楼栋旧，消防设置配备相对齐全--加强监控和巡查");
        }   else if(TOTALSCORE >70 && TOTALSCORE < 80){
            dangerGrader = "中高风险";
            buildScoreModel.setMainDanger("楼栋旧，消防设置配备相对齐全， 无巡查，火灾隐患周边有高危--引导整改");
        }else{
            dangerGrader = "高风险";
            buildScoreModel.setMainDanger("楼栋旧，消防配备不齐全，周边有 高危隐患，无巡查--与楼主或物业约谈");
        }
        buildScoreModel.setDangerScore(String.valueOf(TOTALSCORE));
        buildScoreModel.setDangerGrader(dangerGrader);
        buildScoreModel.setTable(map_score_list);
        return buildScoreModel;
    }

}
*/
