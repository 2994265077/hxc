package com.cetccity.operationcenter.webframework.hiddendanger.tools;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.*;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 12:01 2019-06-17
 * Updater:     heliangming
 * Update_Date：12:01 2019-06-17
 * 更新描述:    heliangming 补充
 **/
@UtilityClass
public class LecMethodUtil {

    //风险D=LEC
    public NameScoreDataModel getLecScore(double likelihood, double exposure, double consequence){
        double likelihood_score, exposure_score, consequence_score; //事故发生的可能性 likelihood_score
        String likelihood_reson, exposure_reson, consequence_reson;
        if(likelihood<10){
            likelihood_score = 0.1;
            likelihood_reson = "极不可能";
        }else if(likelihood>=10 && likelihood<30){
            likelihood_score = 0.5;
            likelihood_reson = "很不可能";
        }else if(likelihood>=30 && likelihood<40){
            likelihood_score = 1;
            likelihood_reson = "可能性小";
        }else if(likelihood>=40 && likelihood<50){
            likelihood_score = 3;
            likelihood_reson = "可能，但不经常";
        }else if(likelihood>=50 && likelihood<60){
            likelihood_score = 6;
            likelihood_reson = "相当可能";
        }else {
            likelihood_score = 10;
            likelihood_reson = "完全可以预料";
        }

        //人员暴露于危险环境中的频繁程度 exposure_score
        if(exposure<10){
            exposure_score = 0.5;
            exposure_reson = "非常罕见暴露";
        }else if(exposure>=10 && exposure<20){
            exposure_score = 1;
            exposure_reson = "每年几次暴露";
        }else if(exposure>=20 && exposure<30){
            exposure_score = 2;
            exposure_reson = "每月一次暴露";
        }else if(exposure>=30 && exposure<40){
            exposure_score = 3;
            exposure_reson = "每周一次或偶尔暴露";
        }else if(exposure>=40 && exposure<50){
            exposure_score = 6;
            exposure_reson = "每天工作时间内暴露";
        }else {
            exposure_score = 10;
            exposure_reson = "连续暴露";
        }

        //一旦发生事故可能造成的后果 consequence_score
        if(consequence<10){
            consequence_score = 1;
            consequence_reson = "引人注意";
        }else if(consequence>=10 && consequence<20){
            consequence_score = 3;
            consequence_reson = "重大、伤残";
        }else if(consequence>=20 && consequence<30){
            consequence_score = 7;
            consequence_reson = "严重";
        }else if(consequence>=30 && consequence<50){
            consequence_score = 15;
            consequence_reson = "1-2人死亡";
        }else if(consequence>=50 && consequence<60){
            consequence_score = 40;
            consequence_reson = "3-9人死亡";
        }else {
            consequence_score = 100;
            consequence_reson = "10人以上死亡";
        }

        NameScoreDataModel nameScoreDataModel = new NameScoreDataModel();
        List<NameValueModel> list = new ArrayList<>();
        list.add(NameValueModel.builder().name("事故发生可能性").value(String.valueOf(likelihood_score)).build());
        list.add(NameValueModel.builder().name("人员暴露的频繁程度").value(String.valueOf(exposure_score)).build());
        list.add(NameValueModel.builder().name("发生事故后果的严重性").value(String.valueOf(consequence_score)).build());
        nameScoreDataModel.setName("scoreLec");
        nameScoreDataModel.setScore(String.valueOf(LoadMyUtil.retainToPoint(likelihood_score*exposure_score*consequence_score,2)));
        nameScoreDataModel.setData(list);
        return nameScoreDataModel;
    }
}
