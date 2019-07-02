package com.cetc.cloud.datasynch.api.service;

import com.cetc.cloud.datasynch.api.model.DsScheduleModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Api(tags = "定时任务管理服务")
public interface ScheduleRemoteService {

    @RequestMapping(value = "/schedule/job/create", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "新增一条同步任务", notes = "新增一条同步任务", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "connType", value = "连接类型(0-数据库;1-接口)", required = true, dataType = "int", paramType = "query",example = "0"),
            @ApiImplicitParam(name = "source", value = "源-表名(例：QAJJ_PUCENTP_V)/源-URL(例：http://10.190.55.62:8080/GetLeadRota/v1/getLeadRotaByDate.action)", required = true, dataType = "String", paramType = "query",example = ""),
            @ApiImplicitParam(name = "srcDs", value = "【数据库】数据源类别：0：readOnly；1：third", required = false, dataType = "String", paramType = "query",example = ""),
            @ApiImplicitParam(name = "isPagingQuery", value = "是否为分页查询（0-不分页;1.分页）", required = true, dataType = "int", paramType = "query",example = "0"),
            @ApiImplicitParam(name = "orderByColumnName", value = "【数据库】排序字段名,若不排序则填:rownum", required = false, dataType = "String", paramType = "query",example = ""),
            @ApiImplicitParam(name = "httpParamExpression", value = "【接口】http入参表达式(例:StartDate=2018/9/24&EndDate=2018/9/30)", required = false, dataType = "String", paramType = "query",example = ""),
            @ApiImplicitParam(name = "httpToken", value = "【接口】http Token表达式(例:Authorization:Bearer e2d40b3d-54a7-3d57-8288-ce6e9bf95cb6)", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "httpPagingType", value = "【接口】分页参数组织类型(填数字)（1:pageNum+pageSize;2:startPosition+maxCount）", required = false, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "httpQueryMethod", value = "【接口】接口请求方式(默认GET)【GET/POST】", required = false, dataType = "String", paramType = "query",example = "GET"),
            @ApiImplicitParam(name = "httpSignType", value = "【接口】签名类型【0:不签名 1:清洁护河签名类型】", required = false, dataType = "int", paramType = "query",example = "0"),
            @ApiImplicitParam(name = "httpJsonExtractRule", value = "【接口】httpJson解析规则(例:data.resultSet.*)", required = false, dataType = "String", paramType = "query",example = ""),
            @ApiImplicitParam(name = "targetTableName", value = "目标表名称", required = true, dataType = "String", paramType = "query",example = ""),
            @ApiImplicitParam(name = "needsTruncateTargetTb", value = "是否要清空目标表【0:不清空 1:清空】", required = true, dataType = "int", paramType = "query",example = "0"),
            @ApiImplicitParam(name = "pageSize", value = "页大小【默认1000】", required = true, dataType = "String", paramType = "query",example = "1000"),
            @ApiImplicitParam(name = "cronExpression", value = "cron表达式 例：【每30秒请求一次：0/30 \\* \\* \\* \\* ?】【每1分钟请求一次 0 0/1 \\* \\* \\* ?】", required = true, dataType = "String", paramType = "query",example = "")
    })
    HashMap createScheduleJob(int connType, String source, String srcDs, int isPagingQuery,
                              String orderByColumnName,
                              String httpParamExpression, String httpToken, int httpPagingType,
                              String httpQueryMethod,int httpSignType,String httpJsonExtractRule,
                              String targetTableName, int needsTruncateTargetTb, String pageSize, String cronExpression) throws SQLException;

    @RequestMapping(value = "/schedule/job/querylist", produces = "application/json", method = RequestMethod.GET)
    @ApiOperation(value = "查询表同步任务List", notes = "查询表同步任务List", produces = "application/json")
    List<DsScheduleModel> queryScheduleJobList();



    @RequestMapping(value = "/schedule/job/start/byJobId", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "根据jobID启动任务", notes = "根据jobID启动任务", produces = "application/json")
    HashMap<String, String> startScheduleJobByJobId(int jobId);

    @RequestMapping(value = "/schedule/job/trigger/byTableName", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "根据tableName触发执行单次任务", notes = "根据tableName触发执行单次任务", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "目标表名称", required = true, dataType = "String", paramType = "query")
    })
    HashMap<String, String> triggerOnceJobByTargetTableName(String tableName);


    @RequestMapping(value = "/schedule/job/triggerOnce/outerJob/byJobSimpleClassName", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "根据jobName启动单次自定义任务", notes = "\"ChengguanEventAttachRunnable\";\n\"FinancialRunnable\";\n" +
            "\"RefreshSanxiaoListRunnable\";\n\"SanxiaoCalcRunnable\";\n\"TrafficRunnable\";\n\"WaterAQIRunnable\";" +
            "\n\"WeatherAlarmRunnable\";\nXinfangGetRunnable\";\nXinghuoVideoHttpRunnable\"", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jobClassSimpleName", value = "只能在以上集合中选取类名", required = true, dataType = "String", paramType = "query"),
    })
    HashMap<String, String> triggerOnceOuterJobByJobClassName(String jobClassSimpleName) throws ClassNotFoundException, InstantiationException, IllegalAccessException;

    @RequestMapping(value = "/schedule/job/start/array", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "根据启动任务List", notes = "job1,job2,...,jobN", produces = "application/json")
    HashMap<String, String> startScheduleJobArrayByJobId(String jobs);

    @RequestMapping(value = "/schedule/job/start/all", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "启动所有将要运行的scheduleJob+outerJob", notes = "", produces = "application/json")
    HashMap<String, String> startAllDSJobs();

    @RequestMapping(value = "/schedule/job/start/allDsJobs", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "启动job列表中所有处于Enable状态的任务", notes = "", produces = "application/json")
    HashMap<String, String> startAllEnabledScheduleJobs();

    @RequestMapping(value = "/schedule/job/disable", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "根据jobID Disable任务（定时任务空转）", notes = "根据jobID Disable任务", produces = "application/json")
    HashMap<String, String> disableJobStatusByJobId(int jobId);

    @RequestMapping(value = "/schedule/job/enable", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "根据jobID Enable任务", notes = "根据jobID Enable任务", produces = "application/json")
    HashMap<String, String> enableJobStatusByJobId(int jobId);

    HashMap<String, String> deleteScheduleJobByJobId(int jobId);

    @RequestMapping(value = "/schedule/job/alter", produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "修改一条表同步任务的更新频率--仅修改定时表达式", notes = "修改一条表同步任务的更新频率--仅修改定时表达式", produces = "application/json")
    HashMap<String, String> alterScheduleJobCron(int jobId, String cron);


    @RequestMapping(value = "/futures/get", produces = "application/json", method = RequestMethod.GET)
    @ApiOperation(value = "获取正在运行的任务列表", notes = "获取正在运行的任务列表", produces = "application/json")
    Map<String, Future> getRunningFutures();

}
