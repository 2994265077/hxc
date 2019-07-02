package com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.web.util.DataOfProperityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao
 * 项目名称:   futian-EarlyWarningMonitoring
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:34 2019-01-15
 * Updater:     heliangming
 * Update_Date：17:34 2019-01-15
 * 更新描述:    heliangming 补充
 **/
@Component
public class BuildLegal_PersonToolToCache_SanXiao {

    @Autowired
    BuildLegal_PersonScoreTool_SanXiao buildLegal_PersonScoreTool_SanXiao;

    @Autowired
    OracleOperateService oracleOperateService;

    public Map<String ,String> buildLegal_PersonScoreToCache(String sanxiao_id){
        Map<String ,String> map_Legal_Person_score = new HashMap<String ,String>();
        double score_004;
        String value_004001="";
        String value_004002="是三小场所";
        String value_004003; //消防隐患历史情况

        double score_004001; //法人经营业务得分
        double score_004002; //是否三小场所得分
        double score_004003; //消防隐患历史情况得分

        score_004001 = buildLegal_PersonScoreTool_SanXiao.buildscore_004001(value_004001);
        score_004002 = buildLegal_PersonScoreTool_SanXiao.buildscore_004002(value_004002);

        String sql = "select COUNT(*) FROM BLK_SANXIAO_PLACE where ID = '"+sanxiao_id+"' AND HAS_TROUBLE='1'";
        int num = oracleOperateService.queryCount(sql);
        score_004003 = buildLegal_PersonScoreTool_SanXiao.buildscore_004003(num);
        if(num ==0) {
            value_004003 = "无记录";
        }else{
            value_004003 = "有历史隐患记录";
        }
        /*建筑基础属性得分 楼房年龄*0.35+建筑高度*0.2+耐火等级*0.2+隐患等级*0.15*/
        score_004 = buildLegal_PersonScoreTool_SanXiao.getLegal_PersonAttributes(score_004001, score_004002, score_004003);

        map_Legal_Person_score.put("score_004001",String.valueOf(score_004001));
        map_Legal_Person_score.put("value_004001",String.valueOf(value_004001));
        map_Legal_Person_score.put("score_004002",String.valueOf(score_004002));
        map_Legal_Person_score.put("value_004002",String.valueOf(value_004002));
        map_Legal_Person_score.put("score_004003",String.valueOf(score_004003));
        map_Legal_Person_score.put("value_004003",String.valueOf(value_004003));
        map_Legal_Person_score.put("score_004",String.valueOf(score_004));

        return map_Legal_Person_score;
    }
}
