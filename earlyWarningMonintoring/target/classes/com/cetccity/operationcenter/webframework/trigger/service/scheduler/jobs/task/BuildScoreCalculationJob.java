package com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.task;

import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.build.*;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class BuildScoreCalculationJob {

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

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    BuildScoreTool buildScoreTool;

    @Scheduled(cron="0 0 1 * * ?")   //每天凌晨1点执行一次
    public void getBuildScoreRank(){

        //计算之前初始化--建筑风险评估元数据
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

        int current_buildScore_size = oracleOperateService.queryCount("SELECT COUNT(*) FROM BUILD_SCORE WHERE CALCULATION_TIME = '"+LoadMyUtil.getMyTime("DATE",0)+"'");

        int s = 0;
        int k = 0;
        long total = 0;/*总数量*/

        String buildid;
        List<LinkedHashMap> list_buildid = oracleOperateService.querySql("select BUILDID from T_BUILD_INFO_V order by BUILDID");
        if(current_buildScore_size!=0){
            log.info(LoadMyUtil.getMyTime("DATE",0)+"--计算结果已存在--");
            return;
        }
        log.info(LoadMyUtil.getMyTime("DATE",0)+"--开始计算建筑评分--");
        long start = System.currentTimeMillis();

        /**
         * 线程池初始化方法
         *
         * corePoolSize 核心线程池大小----16
         * maximumPoolSize 最大线程池大小----16
         * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----100+单位TimeUnit
         * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
         * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(16)====16容量的阻塞队列
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                16,
                16,
                100,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(16)     //等待队列
        );

        for (LinkedHashMap map_buildid:list_buildid) {
            buildid = (String)map_buildid.get("BUILDID");
            s++;
            k++;
            if (k%100==0)
            //log.info("[Table T_BUILD_INFO_V : "+(float)(s+1)*100+"%]"+"[Datas : "+((float)k/(float) (list_buildid.size()-1))*100+"%]");
            total++;
            executor.execute(new Thread(new SortOfBuildScoreThread(buildid,buildScoreTool)));
            //当线程数已经达到maxPoolSize，且队列已满，会拒绝新任务，此时利用缓冲机制来处理
            while(executor.getQueue().size()>=executor.getMaximumPoolSize()){
                log.debug("Thread waite ...Already maxThread. Now Thread nubmer:"+executor.getActiveCount());
                //log.debug("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+executor.getQueue().size()+"，已执行完别的任务数目："+executor.getCompletedTaskCount());
                long time = 100;
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    log.error("sleep error!",e);
                }
            }
        }
        /*等待线程执行结束*/
        while(executor.getActiveCount()!=0 || executor.getQueue().size()!=0){
            try {
                Thread.sleep(1000);
                log.debug("active thread count: "+executor.getActiveCount());
            } catch (InterruptedException e) {
                log.error("sleep error!\n",e);
            }
        }
        /*关闭线程池*/
        executor.shutdown();
        long end = System.currentTimeMillis();
        long second = ((end - start) / 1000) % 60;
        long minite = (end - start) / 1000 / 60 % 60;
        log.info("spend time : " + minite + " m" + second + " s");
        log.info("total--"+total+"map_size--" + list_buildid.size());
    }
}
