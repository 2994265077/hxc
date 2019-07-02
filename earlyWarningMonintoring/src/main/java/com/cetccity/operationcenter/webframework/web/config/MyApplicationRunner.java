package com.cetccity.operationcenter.webframework.web.config;

import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.*;
import com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.task.BuildScoreCalculationJob;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by llj on 2018/7/18.
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    BuildScoreCalculationJob buildScoreCalculationJob;

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    BuildScoreTool buildScoreTool;

    @Autowired
    BuildSurroundingEnvironmentToCache buildSurroundingEnvironmentToCache;

    @Autowired
    BuildBasicToCache buildBasicToCache;

    @Autowired
    BuildSecurityManagementToCache buildSecurityManagementToCache;

    @Autowired
    BuildPurposeToCache buildPurposeToCache;

    @Autowired
    BuildAttributeRateForOneToCache buildAttributeRateForOneToCache;

    @Override
    public void run(ApplicationArguments var1) throws Exception{

        //初始化--建筑风险评估元数据，用于三小场所评分等
        buildBasicToCache.getAllbuildBasicInformation("T_BUILD_INFO_V");
        buildBasicToCache.getAttributeBuildBasicInformation("BUILD_SCORE_WEIGHT","001");
        buildSecurityManagementToCache.getAllbuildSecurityManagementBuildInfo("T_BUILD_INFO_V");
        buildSecurityManagementToCache.getAllbuildSecurityManagementCheckControl();
        buildSecurityManagementToCache.getAllbuildSecurityManagementCheckdayDanger();
        buildSecurityManagementToCache.getAttributeBuildSecurityManagementInformation("BUILD_SCORE_WEIGHT","002");
        buildSurroundingEnvironmentToCache.getWeatherForecastOfToday();
        buildSurroundingEnvironmentToCache.getAttributeBuildWeatherInformation("BUILD_SCORE_WEIGHT","004");
        buildPurposeToCache.getAllbuildPurpose();
        buildPurposeToCache.getAttributeBuildPurposeInformation("BUILD_SCORE_WEIGHT","003");
        buildAttributeRateForOneToCache.getAttributeBuildOneInformation();
        buildAttributeRateForOneToCache.getNameBuildOneInformation();

        /*若今天无建筑评分数据-启动建筑评分并排序*/
        int current_buildScore_size = oracleOperateService.queryCount("SELECT COUNT(*) FROM BUILD_SCORE WHERE CALCULATION_TIME = '"+ LoadMyUtil.getMyTime("DATE",0)+"'");
        if(current_buildScore_size==0) {
            buildScoreCalculationJob.getBuildScoreRank();
        }
    }
}