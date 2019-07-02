package com.cetccity.operationcenter.webframework.hiddendanger.tools.build;

import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;

@Component
public class BuildSurroundingEnvironmentToCache {
	@Autowired
	private CommonInstance commonInstance;
    @Autowired
    OracleOperateService oracleOperateService;

    public static String WeatherForecastOfToday;

    public static Map<String,Float> map_attribute_build_Weather_information;

    public void getWeatherForecastOfToday() {
        String source;
        String urlStr = commonInstance.getWeatherForecastURL();
        source = "{\"title\":\"未来十天天气预报\",\"from\":\"data.szmb.gov.cn\",\"pubdate\":\"08月28日 15:32\",\"imgpre\":\"https://data.szmb.gov.cn/design/images/weather_icon2/\",\"data\":{\"report\":\"阴天，有暴雨局部大暴雨；气温25-29℃；西南风3级，阵风5-6级；相对湿度75%-95% \",\"trend\":\"预计受季风低压影响，29-30日有暴雨局部大暴雨，并伴有6-8级短时大风，31日降雨减弱，转多云，天气趋于炎热\",\"ifNext\":\"1\",\"maxh\":\"95\",\"minh\":\"75\",\"maxt\":\"29.0\",\"mint\":\"25.0\",\"r\":\"48\",\"timeframe\":\"晚上到明天\",\"reporttime\":\"2018-08-28 16:00:00\",\"ws\":\"3\",\"wd\":\"西南\",\"img\":\"10.png\",\"forecast10d\":{\"list\":[{\"time\":\"2018-08-29\",\"img\":\"10\",\"weather\":\"阴天，有暴雨局部大暴雨\",\"maxt\":\"29\",\"mint\":\"25\",\"maxh\":95,\"minh\":75,\"ws\":\"3\",\"wd\":\"西南\"},{\"time\":\"2018-08-30\",\"img\":\"10\",\"weather\":\"阴天，有暴雨，局部大暴雨\",\"maxt\":\"29\",\"mint\":\"25\",\"maxh\":95,\"minh\":75,\"ws\":\"3-4\",\"wd\":\"南\"},{\"time\":\"2018-08-31\",\"img\":\"07\",\"weather\":\"阴天间多云，有阵雨\",\"maxt\":\"30\",\"mint\":\"26\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-01\",\"img\":\"02_2\",\"weather\":\"多云，局部偶有有阵雨\",\"maxt\":\"31\",\"mint\":\"27\",\"maxh\":95,\"minh\":60,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-02\",\"img\":\"02_2\",\"weather\":\"多云，大部分时间可见阳光\",\"maxt\":\"32\",\"mint\":\"27\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-03\",\"img\":\"02_2\",\"weather\":\"多云，局部有短时阵雨\",\"maxt\":\"32\",\"mint\":\"28\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-04\",\"img\":\"04\",\"weather\":\"多云，有短时阵雨\",\"maxt\":\"32\",\"mint\":\"27\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-05\",\"img\":\"04\",\"weather\":\"多云，有短时阵雨\",\"maxt\":\"32\",\"mint\":\"27\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-06\",\"img\":\"02_2\",\"weather\":\"多云，局部有短时阵雨\",\"maxt\":\"32\",\"mint\":\"27\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"}]}}}";
        /*try {
            source = UrlApiToSource.doJsonGetHttps(urlStr);
        } catch (Exception e) {
            logger.info("不能连接"+CommonInstance.getWeatherForecastURL());
            source = "{\"title\":\"未来十天天气预报\",\"from\":\"data.szmb.gov.cn\",\"pubdate\":\"08月28日 15:32\",\"imgpre\":\"https://data.szmb.gov.cn/design/images/weather_icon2/\",\"data\":{\"report\":\"阴天，有暴雨局部大暴雨；气温25-29℃；西南风3级，阵风5-6级；相对湿度75%-95% \",\"trend\":\"预计受季风低压影响，29-30日有暴雨局部大暴雨，并伴有6-8级短时大风，31日降雨减弱，转多云，天气趋于炎热\",\"ifNext\":\"1\",\"maxh\":\"95\",\"minh\":\"75\",\"maxt\":\"29.0\",\"mint\":\"25.0\",\"r\":\"48\",\"timeframe\":\"晚上到明天\",\"reporttime\":\"2018-08-28 16:00:00\",\"ws\":\"3\",\"wd\":\"西南\",\"img\":\"10.png\",\"forecast10d\":{\"list\":[{\"time\":\"2018-08-29\",\"img\":\"10\",\"weather\":\"阴天，有暴雨局部大暴雨\",\"maxt\":\"29\",\"mint\":\"25\",\"maxh\":95,\"minh\":75,\"ws\":\"3\",\"wd\":\"西南\"},{\"time\":\"2018-08-30\",\"img\":\"10\",\"weather\":\"阴天，有暴雨，局部大暴雨\",\"maxt\":\"29\",\"mint\":\"25\",\"maxh\":95,\"minh\":75,\"ws\":\"3-4\",\"wd\":\"南\"},{\"time\":\"2018-08-31\",\"img\":\"07\",\"weather\":\"阴天间多云，有阵雨\",\"maxt\":\"30\",\"mint\":\"26\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-01\",\"img\":\"02_2\",\"weather\":\"多云，局部偶有有阵雨\",\"maxt\":\"31\",\"mint\":\"27\",\"maxh\":95,\"minh\":60,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-02\",\"img\":\"02_2\",\"weather\":\"多云，大部分时间可见阳光\",\"maxt\":\"32\",\"mint\":\"27\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-03\",\"img\":\"02_2\",\"weather\":\"多云，局部有短时阵雨\",\"maxt\":\"32\",\"mint\":\"28\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-04\",\"img\":\"04\",\"weather\":\"多云，有短时阵雨\",\"maxt\":\"32\",\"mint\":\"27\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-05\",\"img\":\"04\",\"weather\":\"多云，有短时阵雨\",\"maxt\":\"32\",\"mint\":\"27\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"},{\"time\":\"2018-09-06\",\"img\":\"02_2\",\"weather\":\"多云，局部有短时阵雨\",\"maxt\":\"32\",\"mint\":\"27\",\"maxh\":95,\"minh\":65,\"ws\":\"2-3\",\"wd\":\"南\"}]}}}";
            e.printStackTrace();
        }*/
        Map maps = (Map) JSON.parse(source);
        WeatherForecastOfToday = maps.get("data").toString();
    }

    public void getAttributeBuildWeatherInformation(String tableName, String PID) {
        String sql = "select MAIN_ID,NAME,WEIGHT,PID FROM "+tableName+" WHERE PID = '"+PID+"'";
        List<LinkedHashMap> map_attribute_list = oracleOperateService.querySql(sql);
        Map<String,Float> map_attribute = new HashMap();
        for (LinkedHashMap map:map_attribute_list) {
            map_attribute.put((String)map.get("MAIN_ID"),Float.valueOf((String)map.get("WEIGHT")));
        }
        map_attribute_build_Weather_information = map_attribute;
    }

}
