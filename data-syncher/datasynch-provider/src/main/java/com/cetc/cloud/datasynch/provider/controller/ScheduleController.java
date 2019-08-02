package com.cetc.cloud.datasynch.provider.controller;

import com.cetc.cloud.datasynch.api.model.DsScheduleModel;
import com.cetc.cloud.datasynch.api.service.ScheduleRemoteService;
import com.cetc.cloud.datasynch.provider.common.CommonInstance;
import com.cetc.cloud.datasynch.provider.config.MyApplicationRunner;
import com.cetc.cloud.datasynch.provider.core.JobManageService;
import com.cetc.cloud.datasynch.provider.service.impl.*;
import com.cetc.cloud.datasynch.provider.template.OuterJobRunnableTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Future;

/**
 * Description：数据库（前置机）定时任务管理
 * Created by luolinjie on 2018/10/9.
 */
@RestController
@Slf4j
public class ScheduleController implements ScheduleRemoteService {
    @Autowired
    DbOperateService dbOperateService;

    @Autowired
    JobManageService jobManageService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    DbQueryService dbQueryService;

    @Autowired
    MyApplicationRunner runner;

    @Override
    public List<DsScheduleModel> queryScheduleJobList() {
        List<DsScheduleModel> list = scheduleService.queryScheduleJobList();
        return list;
    }

    @Override
    public HashMap createScheduleJob(int connType, String source, String srcDs, int isPagingQuery,
                                     String orderByColumnName,
                                     String httpParamExpression, String httpToken,
                                     int httpPagingType,
                                     String httpQueryMethod, int httpSignType, String httpJsonExtractRule,
                                     String targetTableName, int needsTruncateTargetTb, String pageSize, String cronExpression) throws SQLException {
        HashMap res = new HashMap();
        //共有参数完整性校验
        if (null == source || null == targetTableName || null == cronExpression || null == pageSize) {
            res.put("result", "fail");
            res.put("msg", "param error! source,targetTableName,cronExpression,pageSize cannot be null!");
            return res;
        }
        //独有参数完整性校验
        if (connType == CommonInstance.ACCESS_TYPE_DB) {
            if (null == orderByColumnName) {//做分页时一定要有排序字段
                res.put("result", "fail");
                res.put("msg", "param error! orderByColumnName cannot be null!");
                return res;
            }
            if (isPagingQuery != CommonInstance.DO_PAGING) {
                res.put("result", "fail");
                res.put("msg", "param error! isPagingQuery can not be otherValue, available value is:0 or 1");
                return res;
            } else if (isPagingQuery == CommonInstance.NO_PAGING) {//如果不做分页，则需要指定rownum为排序字段
                if (null == orderByColumnName || !"rownum".equalsIgnoreCase(orderByColumnName)) {
                    res.put("result", "fail");
                    res.put("msg", "param error! orderByColumnName cannot be null,you can figure it by input:\"rownum\"");
                    return res;
                }
            }
        } else if (connType == CommonInstance.ACCESS_TYPE_INTERFACE) {
            if (isPagingQuery == CommonInstance.DO_PAGING) {
                if (null == httpParamExpression || null == httpJsonExtractRule) {
                    res.put("result", "fail");
                    res.put("msg", "param error! httpParamExpression,httpParamPageSize,httpParamPageNum,httpJsonExtractRule cannot be null!");
                    return res;
                }
            } else if (isPagingQuery == CommonInstance.NO_PAGING) {
                if (null == httpJsonExtractRule || "".equals(httpJsonExtractRule)) {
                    res.put("result", "fail");
                    res.put("msg", "param error! httpJsonExtractRule cannot be null!");
                    return res;
                }
            }
            if (StringUtils.isEmpty(httpQueryMethod)) {
                res.put("result", "fail");
                res.put("msg", "param error! httpQueryMethod cannot be null!");
                return res;
            }
            if (httpPagingType < 1 || httpPagingType > 2) {
                res.put("result", "fail");
                res.put("msg", "param error! httpPagingType should be legal value:1 OR 2 !");
                return res;
            }
        }
        // 验证参数合法性
        if (CommonInstance.ACCESS_TYPE_DB == connType) {
            if (false == dbQueryService.checkIfTableExists_readOnly(source)) {
                res.put("result", "fail");
                res.put("msg", "failed,source Table:" + source + " doesn't exists!");
                return res;
            } else if (false == dbOperateService.checkIfTableExists(targetTableName)) {
                res.put("result", "fail");
                res.put("msg", "failed,Target table:" + targetTableName + " doesn't Exists!");
                return res;
            }
        } else if (CommonInstance.ACCESS_TYPE_INTERFACE == connType) {
            if (false == dbOperateService.checkIfTableExists(targetTableName)) {
                res.put("result", "fail");
                res.put("msg", "failed,Target table:" + targetTableName + " doesn't Exists!");
                return res;
            }
        }

        //检查shceduleModel是否存在，如果已经存在，则提示需要先删除后再创建
        DsScheduleModel model = scheduleService.queryModelByTableName(targetTableName);
        if (null != model) {
            res.put("result", "failed");
            res.put("msg", "job for " + targetTableName + " already exists! please delete job first and create again");
            return res;
        }
        try {
            CronTrigger trigger = new CronTrigger(cronExpression);
        } catch (Exception e) {
            log.error("cron trigger syntax error! please check and try again!");
            res.put("result", "failed");
            res.put("msg", "cron trigger syntax error! please check and try again!");
            return res;
        }
        DsScheduleModel dsScheduleModel = new DsScheduleModel();
        dsScheduleModel.setConnType(connType);
        dsScheduleModel.setSource(source);
        try {
            dsScheduleModel.setSrcDs(Integer.valueOf(srcDs));
        } catch (Exception e) {
            log.error("cannot parse srcDs from String to Integer:" + srcDs + ",will set a default value:0 on it");
            dsScheduleModel.setSrcDs(0);
        }
        dsScheduleModel.setIsPagingQuery(isPagingQuery);
        dsScheduleModel.setOrderByColumnName(orderByColumnName);
        dsScheduleModel.setHttpParamExpression(httpParamExpression);
        dsScheduleModel.setHttpToken(httpToken);
        dsScheduleModel.setHttpPagingType(httpPagingType);
        dsScheduleModel.setHttpJsonExtractRule(httpJsonExtractRule);
        dsScheduleModel.setHttpSignType(httpSignType);
        dsScheduleModel.setTargetTableName(targetTableName);
        dsScheduleModel.setNeedsTruncateTargetTb(needsTruncateTargetTb);
        dsScheduleModel.setPageSize(Integer.parseInt(pageSize));
        dsScheduleModel.setCronExpression(cronExpression);
        dsScheduleModel.setHttpQueryMethod(httpQueryMethod);
        //将创建的任务记录在数据库schedule表中
        int jobId = scheduleService.addScheduleInstance(dsScheduleModel);
        if (jobId == -1) {
            res.put("result", "fail");
            res.put("msg", "failed,error when creating schedule job");
            return res;
        }
        res.put("result", "success");
        res.put("msg", "create job:" + jobId + " success!");
        return res;
    }

    @Override
    public HashMap<String, String> startScheduleJobByJobId(int jobId) {
        HashMap res = new HashMap();
        DsScheduleModel dsScheduleModel = scheduleService.queryModelByJobId(jobId);
        //启动任务
        int jobid = jobManageService.startScheduledJob(dsScheduleModel);
        if (-1 != jobid) {
            //修改状态
            int i = scheduleService.enableStatusByJobId(jobId);
            if (jobid == jobId && i > 0) {
                res.put("result", "success");
                res.put("msg", "start job:" + jobid + " success!");
            }
        } else {
            res.put("result", "fail");
            res.put("msg", "start job:" + jobid + " failed!");
        }
        return res;
    }

    @Override
    public HashMap<String, String> triggerOnceJobByTargetTableName(String tableName) {
        HashMap res = new HashMap();
        DsScheduleModel dsScheduleModel = scheduleService.queryModelByTableName(tableName);
        if (null == dsScheduleModel) {
            res.put("msg", "error! job doesn't exists!");
            return res;
        }
        dsScheduleModel.setIsEnabled(CommonInstance.JOB_ENABLED);
        //启动任务
        int jobid = jobManageService.startOnceJob(dsScheduleModel);
        if (-1 != jobid) {
            //修改状态
            if (jobid == dsScheduleModel.getId()) {
                res.put("result", "success");
                res.put("msg", "start job:" + jobid + " success!");
            }
        } else {
            res.put("result", "fail");
            res.put("msg", "start job:" + jobid + " failed!");
        }
        return res;
    }

    public HashMap<String, String> startOuterScheduleJob(Class clazz, CronTrigger trigger) throws IllegalAccessException, InstantiationException {
        HashMap res = new HashMap();
        boolean startResult = false;
        OuterJobRunnableTemplate myOuterRunnable;
        myOuterRunnable = (OuterJobRunnableTemplate) clazz.newInstance();
        if (trigger != null) {
            startResult = jobManageService.startOuterScheduledJob(clazz.getSimpleName(), myOuterRunnable, trigger);
        }
        log.info("\n\n>>>>\n\n  >>>> scheduling job:" + clazz.getSimpleName() + " started! --- cronExpression：" + trigger.getExpression());
        if (startResult != false) {
            res.put("result", "success");
            res.put("msg", "start Outer job:" + clazz.getSimpleName() + " success! job ID:" + startResult);
        } else {
            res.put("result", "failed");
            res.put("msg", "start Outer job:" + clazz.getSimpleName() + " faild!");
        }
        return res;
    }

    public HashMap<String, String> startOuterScheduleJobNow(Class clazz) {
        HashMap res = new HashMap();
        OuterJobRunnableTemplate myOuterRunnable;
        try {
            myOuterRunnable = (OuterJobRunnableTemplate) clazz.newInstance();
            Thread thread = new Thread(myOuterRunnable);
            thread.start();
            res.put("result", "success");
            res.put("msg", "start Outer job:" + clazz.getSimpleName() + " success! " + myOuterRunnable.getClass().getSimpleName());
            log.info("\n\n>>>>\n\n  >>>> scheduling job:" + clazz.getSimpleName() + " started! --- cronExpression： now");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            res.put("result", "failed");
            res.put("msg", "start Outer job:" + clazz.getSimpleName() + " faild!");
            return res;
        }
    }

    @Override
    public HashMap<String, String> triggerOnceOuterJobByJobClassName(String jobClassSimpleName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.cetc.cloud.datasynch.provider.template.impl." + jobClassSimpleName);
        HashMap<String, String> map = startOuterScheduleJobNow(clazz);
        return map;
    }

    @Override
    public HashMap<String, String> startScheduleJobArrayByJobId(String jobs) {
        HashMap res = new HashMap();
        if (null == jobs && "".equals(jobs)) {
            res.put("res", "fail");
            res.put("msg", "param: jobs cannot be null!");
        }
        if (StringUtils.isNotEmpty(jobs)) {
            String[] split = jobs.split(",");
            List<String> jobList = Arrays.asList(split);
            if (jobList.size() >= 1) {
                for (int i = 0; i < jobList.size(); i++) {
                    startScheduleJobByJobId(Integer.parseInt(jobList.get(i)));
                }
                res.put("res", "success");
                res.put("msg", "jobs " + jobs + " started!");
                return res;
            } else {
                res.put("res", "fail");
                res.put("msg", "param: jobs cannot be null!");
                return res;
            }
        }
        res.put("res", "fail");
        res.put("msg", "param: jobs cannot be null!");
        return res;
    }

    @Override
    public HashMap<String, String> startAllDSJobs() {
        HashMap res = new HashMap();
        try {
            runner.startAllJobs();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        res.put("res", "started");
        return res;
    }

    @Override
    public HashMap<String, String> startAllEnabledScheduleJobs() {
        HashMap res = new HashMap();
        List<Integer> list = scheduleService.queryEnabledJobIdList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                startScheduleJobByJobId(list.get(i));
            }
            res.put("res", "success");
            res.put("msg", "start all jobs successful!" + list.toString());
        } else {
            res.put("res", "fail");
            res.put("msg", "jobIds is null!");
        }
        return res;
    }

    @Override
    public HashMap<String, String> disableJobStatusByJobId(int jobId) {
        HashMap res = new HashMap();
        //更新当前任务状态
        int updateRes = scheduleService.alterJobStatusByJobId(jobId, CommonInstance.JOB_DISABLED);
        if (updateRes > 0) {
            res.put("result", "success");
            res.put("msg", "disable JobStatus By JobId:" + jobId + " success!");
        } else {
            res.put("result", "fail");
            res.put("msg", "disable JobStatus By JobId:" + jobId + " failed!");
        }
        return res;
    }

    @Override
    public HashMap<String, String> enableJobStatusByJobId(int jobId) {
        HashMap res = new HashMap();
        //更新当前任务状态
        int updateRes = scheduleService.alterJobStatusByJobId(jobId, CommonInstance.JOB_ENABLED);
        if (updateRes > 0) {
            res.put("result", "success");
            res.put("msg", "enable JobStatus By JobId:" + jobId + " success!");
        } else {
            res.put("result", "fail");
            res.put("msg", "enable JobStatus By JobId :" + jobId + " failed!");
        }
        return res;
    }

    @Override
    public HashMap<String, String> deleteScheduleJobByJobId(int jobId) {
        HashMap result = new HashMap<String, String>();
        //停止当前任务
        int s = jobManageService.removeJob(jobId);
        if (s == 1) {
            //从列表中删除当前定时任务
            int res = scheduleService.deleteScheduleByJobId(jobId);
            if (res == 1) {
                result.put("result", "success");
                result.put("msg", "deleteScheduleJobByJobId success,job:" + jobId);
                return result;
            } else {
                result.put("result", "fail");
                result.put("mag", "deleteScheduleJobByJobId fail job:" + jobId);
                return result;
            }
        } else {
            result.put("result", "fail");
            result.put("msg", "deleteScheduleJobByJobId job:" + jobId + ",job cannot be canceled!");
            return result;
        }
    }


    @Override
    public HashMap<String, String> alterScheduleJobCron(int jobId, String cron) {
        HashMap result = new HashMap<String, String>();
        //停止当前任务
        int s = jobManageService.removeJob(jobId);
        if (s == 1) {
            //更新cron表达式至数据库
            int res = scheduleService.updateCronByJobId(jobId, cron);
        }
        //重新启动当前任务--通过JobID（前提是必须之前有这个Job）
        HashMap<String, String> restartRes = startScheduleJobByJobId(jobId);
        if ("success".equals(restartRes.get("result"))) {
            //更改当前任务状态为Disabled
            scheduleService.alterJobStatusByJobId(jobId, CommonInstance.JOB_DISABLED);
            result.put("result" + jobId, "success");
            result.put("msg", "alterScheduleJobCron:" + jobId + ", success!");
            return result;
        } else {
            result.put("result", "fail");
            result.put("msg", "alterScheduleJobCron:" + jobId + ", failed!");
            return result;
        }
    }

    @Override
    public Map<String, Future> getRunningFutures() {
        return jobManageService.getRunningFutures();
    }
}
