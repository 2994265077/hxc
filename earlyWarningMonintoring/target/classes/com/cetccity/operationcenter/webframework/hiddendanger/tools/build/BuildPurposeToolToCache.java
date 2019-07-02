package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import com.cetccity.operationcenter.webframework.web.util.DataOfProperityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BuildPurposeToolToCache {

    @Autowired
    BuildPurposeToCache buildPurposeToCache;

    public Map<String ,String> buildPurposeScoreToCache(String buildid) {
        Map<String ,String> map_purpose_score = new HashMap<String ,String>();
        BuildPurposeScoreTool buildPurposeScoreTool = new BuildPurposeScoreTool();
        List<LinkedHashMap> list_build_purpose_score = new ArrayList<LinkedHashMap>();
        DataOfProperityUtil dataOfProperityUtil_blk = new DataOfProperityUtil();
        List<LinkedHashMap> list_build_purpose_cache =  buildPurposeToCache.list_build_purpose_information;

        double score_003001;
        double score_purpose_attributes;
        String SYYT = "2";

        /*建筑用途 100%*/
        for (int i=0;i<list_build_purpose_cache.size();i++){
            if(buildid.equals(list_build_purpose_cache.get(i).get("LDDM"))){
                list_build_purpose_score.add(list_build_purpose_cache.get(i));
                break;
            }
        }
        if(list_build_purpose_score.size()==0){
            score_003001 = 60;
        }else {
            if("".equals(list_build_purpose_score.get(0).get("SYYT"))||list_build_purpose_score.get(0).get("SYYT")==null){
                SYYT = "2";
            }else{
                SYYT = (String) list_build_purpose_score.get(0).get("SYYT");
            }
            score_003001 = buildPurposeScoreTool.getBuildPurposeScore(SYYT);
        }

        /*计算建筑用途  */
        score_purpose_attributes = buildPurposeScoreTool.getBuildPurposeScore(score_003001);

        map_purpose_score.put("score_003001",String.valueOf(score_003001));
        map_purpose_score.put("value_003001",dataOfProperityUtil_blk.dataDictionary("dataDictionary_blkdata.properties","BLK_BUILDING","SYYT",SYYT));
        map_purpose_score.put("score_003",String.valueOf(score_purpose_attributes));

        return map_purpose_score;
    }
}
