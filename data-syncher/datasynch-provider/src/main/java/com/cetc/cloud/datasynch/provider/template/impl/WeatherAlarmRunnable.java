package com.cetc.cloud.datasynch.provider.template.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetc.cloud.datasynch.api.model.DsOuterJobModel;
import com.cetc.cloud.datasynch.api.model.Token;
import com.cetc.cloud.datasynch.provider.config.SpringContextUtil;
import com.cetc.cloud.datasynch.provider.service.impl.DbOperateService;
import com.cetc.cloud.datasynch.provider.service.impl.OuterUrlsService;
import com.cetc.cloud.datasynch.provider.template.OuterJobRunnableTemplate;
import com.cetc.cloud.datasynch.provider.tools.DbTools;
import com.cetc.cloud.datasynch.provider.util.HttpClientUtil2;
import com.cetc.cloud.datasynch.provider.util.entity.GetModel;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Description：创建定时 执行计算 实例
 * Created by luolinjie on 2018/10/10.
 */
@Slf4j
public class WeatherAlarmRunnable implements OuterJobRunnableTemplate {

    private DbOperateService dbOperateService;
    private OuterUrlsService outerUrlsService;

    public WeatherAlarmRunnable() {
        this.dbOperateService = (DbOperateService) SpringContextUtil.getBean("dbOperateService");
        this.outerUrlsService = (OuterUrlsService) SpringContextUtil.getBean("outerUrlsService");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("WEATHER_FORECAST_SZ");
        try {
            insertWeatherAlarmInfoNow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取预警信息并插入至数据库
     *
     * @throws SQLException
     */
    public void insertWeatherAlarmInfoNow() throws SQLException {
        log.info("Started Scheduled Job:insertWeatherAlarmInfoNow()");
        //考虑WarningContent为空 、或者json无法被解析的情况
        DsOuterJobModel weatherForecastModel = outerUrlsService.getModelByTableName("WEATHER_FORECAST_SZ");
        String url = weatherForecastModel.getUrl();
        String headers = weatherForecastModel.getHeaders();
        JSONObject token = HttpClientUtil2.parseTokenStr2JSONObj(headers);
        GetModel getModel = new GetModel();
        getModel.setHeader(token);
        getModel.setUrl(url);
        JSONObject res = HttpClientUtil2.doGetWithGetModel(getModel);
        int count = 0;
        if (res.getIntValue("code") == 200) {
            String data = res.getString("data");
            JSONArray array = JSON.parseArray(data);
            for (int i = 0; i < array.size(); i++) {
                JSONObject singleInfo = array.getJSONObject(i);
                try {
                    String warningContent = singleInfo.getString("WarningContent");
                    JSONArray warningInfo = singleInfo.getJSONArray("WarningInfo");
                    JSONObject warningInfoObj = warningInfo.getJSONObject(0);
                    String releaseTime = warningInfoObj.getString("ReleaseTime");
                    String releaseArea = warningInfoObj.getString("ReleaseArea");
                    String alertCategory = warningInfoObj.getString("AlertCategory");
                    String warningLevel = warningInfoObj.getString("WarningLevel");

                    if (releaseArea == null || "".equals(releaseArea)) {
                        log.error("Field:`releaseArea` cannot be null!");
                        continue;
                    }
                    if (releaseTime == null || "".equals(releaseTime)) {
                        log.error("Field:`releaseTime` cannot be null!");
                        continue;
                    }
                    if (alertCategory == null || "".equals(alertCategory)) {
                        log.error("Field:`alertCategory` cannot be null!");
                        continue;
                    }
//                    if (warningLevel == null || "".equals(warningLevel)) {
//                        log.error("Field:`warningLevel` cannot be null!");
//                        continue;
//                    }
                    if (warningContent == null || "".equals(warningContent)) {
                        log.error("Field:`warningContent` cannot be null!");
                        continue;
                    }
                    //获取 "字段-字段类型" 映射map
                    HashMap<String, String> tbStructureMap = dbOperateService.queryTableStructureByTableName2("WEATHER_ALARM");
                    //获取字段类型
                    String releasearea = tbStructureMap.get("RELEASEAREA");
                    String alertcategory = tbStructureMap.get("ALERTCATEGORY");
                    String warninglevel = tbStructureMap.get("WARNINGLEVEL");
                    String releasetime = tbStructureMap.get("RELEASETIME");
                    String warningcontent = tbStructureMap.get("WARNINGCONTENT");

                    String decoratedColumn_releaseArea = DbTools.getDecoratedColumn(releasearea, releaseArea);
                    String decoratedColumn_alertcategory = DbTools.getDecoratedColumn(alertcategory, alertCategory);
                    String decoratedColumn_warninglevel = DbTools.getDecoratedColumn(warninglevel, warningLevel);
                    String decoratedColumn_releasetime = DbTools.getDecoratedColumn(releasetime, releaseTime);
                    String decoratedColumn_warningcontent = DbTools.getDecoratedColumn(warningcontent, warningContent);
                    //判断是否数据已经存在，若已经存在则跳过
                    //1 查询该条件下的内容是否存在
                    String querySQL = "select count(*) COUNT from weather_alarm "
                            + " where RELEASEAREA=" + decoratedColumn_releaseArea
                            + " and ALERTCATEGORY=" + decoratedColumn_alertcategory
                            + " and WARNINGLEVEL=" + decoratedColumn_warninglevel
                            + " and RELEASETIME=" + decoratedColumn_releasetime;
                    List<HashMap> list = dbOperateService.oracleQuerySql(querySQL);
                    BigDecimal queryCount = null;
                    if (null != list) {
                        queryCount = (BigDecimal) list.get(0).get("COUNT");
                    }
                    if (null !=queryCount && queryCount.compareTo(new BigDecimal(0)) > 0) {
                        continue;
                    } else {
                        String sql = "insert into weather_alarm(OBJECT_ID,RELEASEAREA,ALERTCATEGORY,WARNINGLEVEL,RELEASETIME,WARNINGCONTENT)" +
                                " values( SEQ_WEATHER_ALARM.NEXTVAL,"
                                + decoratedColumn_releaseArea + ","
                                + decoratedColumn_alertcategory + ","
                                + decoratedColumn_warninglevel + ","
                                + decoratedColumn_releasetime + ","
                                + decoratedColumn_warningcontent
                                + ")";
                        int i1 = dbOperateService.oracleUpdateSql(sql);
                        if (i1 > 0) {
                            log.info("execute insert success! ");
                            count++;
                        } else {
                            log.info("execute failed! SQL :" + sql);
                        }
                    }
                } catch (Exception e) {
                    log.error("SQL error，Duplicated Data  ");
                }
            }
            log.info("execute batch insert finished! count:" + count);
        } else {
            log.error("error while get OnLine WeatherForecast Info:Model:" + weatherForecastModel.toString());
        }

    }

}


