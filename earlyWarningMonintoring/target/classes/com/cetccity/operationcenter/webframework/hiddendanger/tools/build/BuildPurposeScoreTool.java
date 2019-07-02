package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class BuildPurposeScoreTool {

    @Autowired
    BuildPurposeToCache buildPurposeToCache;

    public double getBuildPurposeScore(String SYYT) {
        double purpose_score;
        /* 1.易燃易爆工厂：100分 2.住宅：50分 3.其他为0分 */
        /*blk_building.SYYT.1=公共娱乐场所
        blk_building.JZYT.2=宾馆、饭店
        blk_building.JZYT.3=商场、市场
        blk_building.JZYT.4=体育场馆、会堂
        blk_building.JZYT.5=展览馆、博物馆
        blk_building.JZYT.6=民用机场航站楼，客运车站候车室、码头候船厅
        blk_building.JZYT.7=医院、疗养院
        blk_building.JZYT.8=养老院、福利院
        blk_building.JZYT.9=国家机关办公楼、电力调度楼、电信楼、邮政楼、防灾指挥调度楼、广播电视楼、档案楼*/
        if("1".equals(SYYT)){
            purpose_score = 50;
        }else if("2".equals(SYYT)){
            purpose_score = 30;
        }else{
            purpose_score = 0;
        }
        return purpose_score;
    }

    public double getBuildPurposeScore(double score_purpose) {
        Map<String,Float> map_attribute_build_Purpose_information = buildPurposeToCache.map_attribute_build_Purpose_information;
        float attribute_003001 = map_attribute_build_Purpose_information.get("003001");
        double score_build_Purpose_attributes = attribute_003001*score_purpose;
        return score_build_Purpose_attributes;
    }
}
