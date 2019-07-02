/*
package com.cetccity.operationcenter.webframework.hiddendanger.tools;

import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.T_XF_BUILDER_PFJGMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.T_XF_BUILDER_PFJG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BuildScoreComment {

    @Autowired
    T_XF_BUILDER_PFJGMapper t_XF_BUILDER_PFJGMapper;

    public static Map<String, String> map_BuildScoreAttribute;

    public void getBuildScoreAttribute(String buildid) {
        Map<String, String> map_BuildScoreAttribute_inside = new HashMap<String, String>();
        T_XF_BUILDER_PFJG t_XF_BUILDER_PFJG = new T_XF_BUILDER_PFJG();
        t_XF_BUILDER_PFJG.setBUILDERID(buildid);
        List<T_XF_BUILDER_PFJG> t_XF_BUILDER_PFJG_list = t_XF_BUILDER_PFJGMapper.getT_XF_BUILDER_PFJG(t_XF_BUILDER_PFJG);
        if (!t_XF_BUILDER_PFJG_list.isEmpty()) {
            //分数模块
            float WEATHERSCORE = t_XF_BUILDER_PFJG_list.get(0).getWEATHERSCORE();  //天气状况（50%）
            float TEMPERATURESCORE = t_XF_BUILDER_PFJG_list.get(0).getTEMPERATURESCORE();  //温度（30%）
            float WINDLEVELSCORE = t_XF_BUILDER_PFJG_list.get(0).getWINDLEVELSCORE(); //风级（20%）
            float BUILDERUSESCORE = t_XF_BUILDER_PFJG_list.get(0).getBUILDERUSESCORE(); //楼房用途
            float YEARSCORE = t_XF_BUILDER_PFJG_list.get(0).getYEARSCORE(); //楼房年龄 50%
            float HIGHSCORE = t_XF_BUILDER_PFJG_list.get(0).getHIGHSCORE();  //建筑高度 10%
            float FIRELEVELSCORE = t_XF_BUILDER_PFJG_list.get(0).getFIRELEVELSCORE();  //耐火等级 20%
            float HIDDENLEVELSCORE = t_XF_BUILDER_PFJG_list.get(0).getHIDDENLEVELSCORE();  //隐患等级 20%
            float HZBJQDJPF = t_XF_BUILDER_PFJG_list.get(0).getHZBJQDJPF();//火灾报警器情况 25%
            float FIRECONTRLSCORE = t_XF_BUILDER_PFJG_list.get(0).getFIRECONTRLSCORE();//消防控制室 25%
            float security_management_3 = 50;//消防设备完好度 25%
            float security_management_4 = 50;//消防巡查 25%

            float TOTALSCORE = t_XF_BUILDER_PFJG_list.get(0).getTOTALSCORE(); //总分

            float weather_quota = Math.round(0.5 * WEATHERSCORE + 0.3 * TEMPERATURESCORE + 0.2 * WINDLEVELSCORE); //天气（10%）
            float build_purpose_quota = Math.round(BUILDERUSESCORE);//建筑用途（10%）-BUILDERUSESCORE
            float basic_quota = Math.round(0.5 * YEARSCORE + 0.1 * HIGHSCORE + 0.2 * FIRELEVELSCORE + 0.2 * HIDDENLEVELSCORE);//建筑基础属性 30%
            float security_management_quota = Math.round(0.5 * (HZBJQDJPF + FIRECONTRLSCORE));//建筑火灾安全管理50%
            map_BuildScoreAttribute_inside.put("TOTALSCORE", String.valueOf(TOTALSCORE));//总分
            map_BuildScoreAttribute_inside.put("WEATHERSCORE", String.valueOf(WEATHERSCORE));//天气状况（50%）
            map_BuildScoreAttribute_inside.put("TEMPERATURESCORE", String.valueOf(TEMPERATURESCORE));
            map_BuildScoreAttribute_inside.put("WINDLEVELSCORE", String.valueOf(WINDLEVELSCORE));
            map_BuildScoreAttribute_inside.put("weather_quota", String.valueOf(weather_quota));//天气（10%）
            map_BuildScoreAttribute_inside.put("BUILDERUSESCORE", String.valueOf(BUILDERUSESCORE));
            map_BuildScoreAttribute_inside.put("build_purpose_quota", String.valueOf(build_purpose_quota));//建筑用途（10%）
            map_BuildScoreAttribute_inside.put("YEARSCORE", String.valueOf(YEARSCORE));
            map_BuildScoreAttribute_inside.put("HIGHSCORE", String.valueOf(HIGHSCORE));
            map_BuildScoreAttribute_inside.put("FIRELEVELSCORE", String.valueOf(FIRELEVELSCORE));
            map_BuildScoreAttribute_inside.put("HIDDENLEVELSCORE", String.valueOf(HIDDENLEVELSCORE));
            map_BuildScoreAttribute_inside.put("basic_quota", String.valueOf(basic_quota));//建筑基础属性 30%
            map_BuildScoreAttribute_inside.put("HZBJQDJPF", String.valueOf(HZBJQDJPF));
            map_BuildScoreAttribute_inside.put("FIRECONTRLSCORE", String.valueOf(FIRECONTRLSCORE));
            map_BuildScoreAttribute_inside.put("security_management_quota", String.valueOf(security_management_quota));//建筑火灾安全管理50%
            //值模块
            String buildAge;   //楼栋年龄
            if(t_XF_BUILDER_PFJG_list.get(0).getFINSHTIME()==null){
                buildAge = "";
            }else{
                buildAge = String.valueOf(Integer.valueOf(LoadMyUtil.getMyTime("YEAR",0))- Integer.valueOf(t_XF_BUILDER_PFJG_list.get(0).getFINSHTIME().substring(0,4)));
            }
            String buildHIGH = t_XF_BUILDER_PFJG_list.get(0).getHIGH();//建筑高度
            String FIRELEVEL = t_XF_BUILDER_PFJG_list.get(0).getFIRELEVEL();//耐火等级
            String HIDDENLEVEL = t_XF_BUILDER_PFJG_list.get(0).getHIDDENLEVEL();//隐患等级
            String FIRETEALLPOLICE = t_XF_BUILDER_PFJG_list.get(0).getFIRETEALLPOLICE();//火灾报警器
            String FIRECONTROL = t_XF_BUILDER_PFJG_list.get(0).getFIRECONTROL();//消防控制室
            String BUILDERUSE = t_XF_BUILDER_PFJG_list.get(0).getBUILDERUSE();//建筑用途
            String WEATHER = t_XF_BUILDER_PFJG_list.get(0).getWEATHER();//天气状况
            String TEMPERATURE = t_XF_BUILDER_PFJG_list.get(0).getTEMPERATURE();//温差
            String WINDLEVELS = t_XF_BUILDER_PFJG_list.get(0).getWINDLEVELS();//风级

            map_BuildScoreAttribute_inside.put("buildAge", buildAge);
            map_BuildScoreAttribute_inside.put("buildHIGH", buildHIGH);
            map_BuildScoreAttribute_inside.put("FIRELEVEL", FIRELEVEL);
            map_BuildScoreAttribute_inside.put("HIDDENLEVEL", HIDDENLEVEL);
            map_BuildScoreAttribute_inside.put("FIRETEALLPOLICE", FIRETEALLPOLICE);
            map_BuildScoreAttribute_inside.put("FIRECONTROL", FIRECONTROL);
            map_BuildScoreAttribute_inside.put("BUILDERUSE", BUILDERUSE);
            map_BuildScoreAttribute_inside.put("WEATHER", WEATHER);
            map_BuildScoreAttribute_inside.put("TEMPERATURE", TEMPERATURE);
            map_BuildScoreAttribute_inside.put("WINDLEVELS", WINDLEVELS);
        }
        map_BuildScoreAttribute = map_BuildScoreAttribute_inside;
    }

}
*/
