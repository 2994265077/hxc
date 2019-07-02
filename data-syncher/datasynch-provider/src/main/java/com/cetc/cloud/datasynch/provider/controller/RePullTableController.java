package com.cetc.cloud.datasynch.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.cetc.cloud.datasynch.api.model.DsScheduleModel;
import com.cetc.cloud.datasynch.api.model.DsSynchJobLogInfoModel;
import com.cetc.cloud.datasynch.api.service.RePullTableRemoteService;
import com.cetc.cloud.datasynch.provider.common.CommonInstance;
import com.cetc.cloud.datasynch.provider.service.impl.DbOperateService;
import com.cetc.cloud.datasynch.provider.core.JobManageService;
import com.cetc.cloud.datasynch.provider.service.impl.ScheduleService;
import com.cetc.cloud.datasynch.provider.service.impl.SynchJobLogInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * PackageName:   com.cetc.cloud.datasynch.provider.controller
 * projectName:   dataSyncher
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/11/20 20:09
 * Updater:     by luolinjie
 * Update_Date: 2018/11/20
 * Update_Description: luolinjie 补充
 **/
@RestController
@Slf4j
public class RePullTableController implements RePullTableRemoteService {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    SynchJobLogInfoService synchJobLogInfoService;
    @Autowired
    DbOperateService dbOperateService;
    @Autowired
    JobManageService jobManageService;
    @Autowired
    ScheduleController scheduleController;
    @Autowired
    SequenceManagerController sequenceManagerController;

    @Override
    public String clearAndPullAgainTableByTableName(String tableName) {

        JSONObject res = new JSONObject();

        //1.根据表名查询JobId
        DsScheduleModel dsScheduleModel = scheduleService.queryModelByTableName(tableName);
        //disable任务
        scheduleService.alterJobStatusByJobId(dsScheduleModel.getId(), CommonInstance.JOB_DISABLED);

        //2.备份+清空表
        String copyName = dbOperateService.backUpTable(dsScheduleModel.getTargetTableName());
        log.info("\n>> BackUpTable:"+ dsScheduleModel.getTargetTableName()+"\nbackup tableName："+copyName);
        boolean clearRes = dbOperateService.truncateTableByTbName(dsScheduleModel.getTargetTableName());

        //3.序列校准-归0
        try {
            sequenceManagerController.exactSequenceByTbName(dsScheduleModel.getTargetTableName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //3.通过jobId清空日志
        if (clearRes) {
            int i = synchJobLogInfoService.deleteByJobId(dsScheduleModel.getId());
            if (i>0){
                log.info("delete schedule log success:"+i);
            }
            try {
                //ENABLE任务
                scheduleService.alterJobStatusByJobId(dsScheduleModel.getId(), CommonInstance.JOB_ENABLED);
                log.info("started once job:");
                //记录触发前时间
                Date triggerTime = new Date();
                scheduleController.triggerOnceJobByTargetTableName(dsScheduleModel.getTargetTableName());

                int loopcount =10;
                //trigger 完成之后，循环等待，通过查询该任务的执行结果
                while(true){
                    DsSynchJobLogInfoModel logInfoModel = null;
                    while (null == logInfoModel&&loopcount>=0){
                        if (loopcount==0){
                            roolback(dsScheduleModel,copyName);
                            res.put("res", "fail");
                            res.put("msg", "failed executed clearAndPullAgainTableByTableName, targetTable:" + dsScheduleModel.getTargetTableName()+
                            "\nreason: loop time out");
                            return res.toJSONString();
                        }
                        Thread.sleep(2000);
                        logInfoModel = synchJobLogInfoService.queryLatestInfoByJobId(dsScheduleModel.getId());
                        loopcount--;
                    }
                    if (logInfoModel.getCreateTime().after(triggerTime)) {
                        int queryResultSize = logInfoModel.getQueryResultSize();
                        if (0 == queryResultSize) {
                            //如果获取到的结果集为空，则回滚刚才备份的表
                            roolback(dsScheduleModel,copyName);
                            break;
                        } else {
                            //4.如果不为空，则放弃回滚
                            break;
                        }
                    }else {
                        Thread.sleep(1000);
                    }
                }
            }catch (Exception e){
                log.error("Error starting once job:triggerOnceJobByTargetTableName()"+ dsScheduleModel.getTargetTableName());
            }
            res.put("res", "success");
            res.put("msg", "success executed clearAndPullAgainTableByTableName, targetTable:" + dsScheduleModel.getTargetTableName());
            return res.toJSONString();
        } else {
            res.put("res", "fail");
            res.put("msg", "failed executed clearAndPullAgainTableByTableName, targetTable:" + dsScheduleModel.getTargetTableName());
            return res.toJSONString();
        }

    }

    private void roolback(DsScheduleModel dsScheduleModel,String copyName){
        //如果获取到的结果集为空，则回滚刚才备份的表
        // 1.drop 原表
        dbOperateService.dropTable(dsScheduleModel.getTargetTableName());
        // 2.copy 最新备份表--原表
        dbOperateService.backUpTable(copyName,dsScheduleModel.getTargetTableName());

        //3.序列校准
        try {
            sequenceManagerController.exactSequenceByTbName(dsScheduleModel.getTargetTableName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
