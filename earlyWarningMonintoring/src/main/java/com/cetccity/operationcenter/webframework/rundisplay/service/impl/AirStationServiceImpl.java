package com.cetccity.operationcenter.webframework.rundisplay.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.AlarmInformationMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.ALARM_INFORMATION;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.rundisplay.api.model.AqiDateDetailModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.*;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TimeValueModel;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.*;
import com.cetccity.operationcenter.webframework.rundisplay.service.AirStationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Service("airStationService")
@CacheConfig(cacheNames = "ecoEnvironmentChart")
@Slf4j
public class AirStationServiceImpl implements AirStationService {

    @Autowired
    private QhsjAirMonitorHourAndDayDataMapper qhsjAirMonitorHourAndDayDataMapper;

    @Autowired
    private QhsjAirFactorCodeMapper qhsjAirFactorCodeMapper;

    @Autowired
    private QhsjTAirBasicInfoMapper qhsjTAirBasicinfoMapper;

    @Autowired
    private AlarmInformationMapper alarmInformationMapper;

    @Autowired
    private QhsjAirMonitorDayDataMapper qhsjAirMonitorDayDataMapper;

    @Autowired
    private QhsjAqiInfoMapper qhsjAqiInfoMapper;

    //空气质量当前详情(最近时间)
    public NameDataModel getDetailAirStationDataOfLatelyTime(String siteCode){
        NameDataModel nameDataModel = new NameDataModel();
        List<NameDataModel> nameDataModelAllList = new ArrayList<NameDataModel>();
        NameDataModel nameDataModelDetail = new NameDataModel();
        NameDataModel nameDataModelAllAttribute = new NameDataModel();
        List<QhsjTAirBasicInfo> qhsjTAirBasicInfos = qhsjTAirBasicinfoMapper.getAirBasicInfo(siteCode);
        String airStationName  = qhsjTAirBasicInfos.get(0).getSITE_NAME();

        List<NameValueUnitModel> nameValueUnitModelList = qhsjAirMonitorHourAndDayDataMapper.recentAirValueBySite(siteCode);

        LinkedHashMap mapDetail = new LinkedHashMap();
        mapDetail.put("时间", LocalDate.now().toString());
        for (NameValueUnitModel nameValueUnitModel : nameValueUnitModelList) {
            if ("风速".equals(nameValueUnitModel.getName())) {
                mapDetail.put("风速", nameValueUnitModel.getValue() + " " + nameValueUnitModel.getUnit());
            }
            if ("温度".equals(nameValueUnitModel.getName())) {
                mapDetail.put("温度", nameValueUnitModel.getValue()+ " " + nameValueUnitModel.getUnit());
            }
        }
        nameDataModelDetail.setName("当前详情");
        nameDataModelDetail.setData(mapDetail);
        nameDataModelAllList.add(nameDataModelDetail);
        nameDataModelAllAttribute.setName("全部指标数值");
        nameDataModelAllAttribute.setData(nameValueUnitModelList);
        nameDataModelAllList.add(nameDataModelAllAttribute);
        nameDataModel.setName(airStationName);
        nameDataModel.setData(nameDataModelAllList);
        return nameDataModel;
    }

    //获取折线图
    public NameDataModel getLineAirStationDataOfTime(Map<String,String> map_time){
        NameDataModel nameDataModel_obj = new NameDataModel();

        List<QhsjTAirBasicInfo> qhsjTAirBasicInfoList = qhsjTAirBasicinfoMapper.getAirBasicInfo(map_time.get("id"));

        List<NameDataModel> nameDataModelList = new LinkedList<>();
        //获取监测站的检测字段 -二氧化硫、氮氧化物等
        List<QhsjAirFactorCode> qhsjAirFactorCodeList = qhsjAirFactorCodeMapper.getAirFactorCode();
        if("day".equals(map_time.get("type"))){
            for (QhsjAirFactorCode qhsjAirFactorCode:qhsjAirFactorCodeList) {
                NameDataModel nameDataModel = new NameDataModel();
                List<TimeValueModel> timeValueModel_list = new ArrayList<TimeValueModel>();
                map_time.put("MONITOR_FACTOR_CODE",qhsjAirFactorCode.getFACTOR_CODE());
                List<QhsjAirMonitorHourAndDayData> qhsjAirMonitorHourAndDayDataList = qhsjAirMonitorHourAndDayDataMapper
                        .getAirStationDataOfDay(map_time);
                for (QhsjAirMonitorHourAndDayData qhsjAirMonitorHourAndDayData : qhsjAirMonitorHourAndDayDataList) {
                    TimeValueModel timeValueModel = new TimeValueModel();
                    timeValueModel.setTime(LoadMyUtil.dateToString(qhsjAirMonitorHourAndDayData.getMONITOR_TIME()));
                    timeValueModel.setValue(qhsjAirMonitorHourAndDayData.getMONITOR_VALUE());
                    timeValueModel_list.add(timeValueModel);
                }
                nameDataModel.setName(qhsjAirFactorCode.getFACTOR_NAME());
                nameDataModel.setData(timeValueModel_list);
                nameDataModelList.add(nameDataModel);
            }
        }else {
            for (QhsjAirFactorCode qHSJ_AIR_FACTOR_CODE : qhsjAirFactorCodeList) {
                NameDataModel nameDataModel = new NameDataModel();
                List<TimeValueModel> timeValueModel_list = new ArrayList<TimeValueModel>();
                map_time.put("MONITOR_FACTOR_CODE", qHSJ_AIR_FACTOR_CODE.getFACTOR_CODE());
                List<QhsjAirMonitorHourAndDayData> qhsjAirMonitorHourAndDayDataList = qhsjAirMonitorHourAndDayDataMapper
                        .getAirStationDataOfTime(map_time);
                for (QhsjAirMonitorHourAndDayData qHSJ_AIR_MONITOR_HOUR_ANDDAY_DATA : qhsjAirMonitorHourAndDayDataList) {
                    TimeValueModel timeValueModel = new TimeValueModel();
                    timeValueModel.setTime(LoadMyUtil.dateToString(qHSJ_AIR_MONITOR_HOUR_ANDDAY_DATA.getMONITOR_TIME()));
                    timeValueModel.setValue(qHSJ_AIR_MONITOR_HOUR_ANDDAY_DATA.getMONITOR_VALUE());
                    timeValueModel_list.add(timeValueModel);
                }
                nameDataModel.setName(qHSJ_AIR_FACTOR_CODE.getFACTOR_NAME());
                nameDataModel.setData(timeValueModel_list);
                nameDataModelList.add(nameDataModel);
            }
        }
        nameDataModel_obj.setName(qhsjTAirBasicInfoList.get(0).getSITE_NAME());
        nameDataModel_obj.setData(nameDataModelList);
        return nameDataModel_obj;
    }

    //获取列表

    public List<Map<String, Object>> getListAirStationDataOfTime(Map<String,String> map_time){
        //获取监测站的检测字段 -二氧化硫、氮氧化物等
        List<QhsjAirFactorCode> qhsjAirFactorCodeList = qhsjAirFactorCodeMapper.getAirFactorCode();
        List<QhsjAirMonitorHourAndDayData> qhsjAirMonitorHourAndDayDataList = new LinkedList<>();
        if("day".equals(map_time.get("type"))){
            qhsjAirMonitorHourAndDayDataList = qhsjAirMonitorHourAndDayDataMapper.getAirStationDataOfDay(map_time);
        }else{
            qhsjAirMonitorHourAndDayDataList = qhsjAirMonitorHourAndDayDataMapper.getAirStationDataOfTime(map_time);
        }
        Map<Date, List<QhsjAirMonitorHourAndDayData>> timeMap = qhsjAirMonitorHourAndDayDataList.parallelStream()
                .collect(Collectors.groupingBy(QhsjAirMonitorHourAndDayData::getMONITOR_TIME));
        List<Map<String, Object>> collect = timeMap.entrySet()
                .parallelStream()
                .map(entry -> {
                    List<QhsjAirMonitorHourAndDayData> value = entry.getValue();
                    Map<String, String> factorCodeMap = value.stream()
                            .collect(Collectors.toMap(QhsjAirMonitorHourAndDayData::getMONITOR_FACTOR_CODE, QhsjAirMonitorHourAndDayData::getMONITOR_VALUE, (o1, o2) -> o1));

                    LinkedHashMap<String, Object> resultMap = qhsjAirFactorCodeList.stream()
                            .collect(Collectors.toMap(
                                    qhsjAirFactorCode -> qhsjAirFactorCode.getFACTOR_NAME() + "(" + qhsjAirFactorCode.getFACTOR_UNIT() + ")",
                                    qhsjAirFactorCode -> {
                                        String monitorValue = factorCodeMap.get(qhsjAirFactorCode.getFACTOR_CODE());
                                        monitorValue = StringUtils.isNotBlank(monitorValue) ? monitorValue : "无数据";
                                        return monitorValue;
                                    },
                                    (o1, o2) -> o1,
                                    () -> linkedMapSupplier(entry.getKey())
                                    )
                            );

                    return resultMap;
                })
                .sorted(Comparator.comparing(map -> (Date) map.get("监测时间")))
                .collect(Collectors.toList());
        return collect;
    }

    private LinkedHashMap<String, Object> linkedMapSupplier(Object monitorTime) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("监测时间", monitorTime);
        return resultMap;
    }

    @Cacheable(key="'AirStationServiceImpl.alarmAirStatistics'")
    public NameDataModel alarmAirStatistics(){
        NameDataModel nameDataModel = new NameDataModel();
        //1、获取监测站
        Map map_airStation = new HashMap();
        List<QhsjTAirBasicInfo> qHSJ_T_AIR_BASICINFO_list = qhsjTAirBasicinfoMapper.getAirBasicInfo(null);
        List<NameDataModel> nameDataModel_baseInfo_list = new ArrayList<>();
        for (QhsjTAirBasicInfo qHSJ_T_AIR_BASICINFO : qHSJ_T_AIR_BASICINFO_list) {
            NameDataModel nameDataModel_baseInfo = new NameDataModel();
            List<NameValueModel> nameValueModel_list = new ArrayList<>();
            for(int i=10;i>0;i--) {
                String DATA = LoadMyUtil.getMyTime("DATE", -i);
                ALARM_INFORMATION aLARM_INFORMATION = new ALARM_INFORMATION();
                aLARM_INFORMATION.setALARM_TYPE_LV2("007001");
                aLARM_INFORMATION.setALARM_OBJECT(qHSJ_T_AIR_BASICINFO.getSITE_NAME());
                aLARM_INFORMATION.setRELEASE_TIME(DATA);
                int num = alarmInformationMapper.getCount(aLARM_INFORMATION);
                nameValueModel_list.add(NameValueModel.builder().name(DATA).value(String.valueOf(num)).build());
            }
            nameDataModel_baseInfo.setName(qHSJ_T_AIR_BASICINFO.getSITE_NAME());
            nameDataModel_baseInfo.setData(nameValueModel_list);
            nameDataModel_baseInfo_list.add(nameDataModel_baseInfo);
        }
        nameDataModel.setName("空气质量监测站报警数统计");
        nameDataModel.setData(nameDataModel_baseInfo_list);
        return nameDataModel;
    }

    //最近十天各个空气质量监测站按天检测的平均值
    //SO2 NO2 PM10 CO O3 PM2.5
    @Cacheable(key="'AirStationServiceImpl.airStationAverageLineDay'")
    public NameDataModel airStationAverageLineDay(){
        LocalDateTime tenDaysAge = LocalDateTime.now().minusDays(10);
        String tag[] = {"a21026","a21004","a34002","a21005","a21029","a34004"};
        String tagName[] = {"二氧化硫","二氧化氮","PM10","一氧化碳","O3","PM2.5"};
        // 从数据库统计各指标当月每天平均数据
        List<NameValueTypeModel<List<NameValueModel>>> nameValueTypeModels = qhsjAirMonitorDayDataMapper.countAirAvgValue(tenDaysAge);
        // 数据库查询到的数据转化为方便查询的数据结构
        Map<String, Map<String, String>> codeCountMap = nameValueTypeModels.stream()
                .collect(Collectors.toMap(NameValueTypeModel::getName, nameValueTypeModel -> nameValueTypeModel.getValue().stream()
                        .collect(Collectors.toMap(NameValueModel::getName, NameValueModel::getValue))));
        // 获取近十天每天日期的格式化（yyyy-MM-dd）字符串
        List<String> dates = Stream.iterate(tenDaysAge.toLocalDate(), localDate -> localDate.plusDays(1L))
                .limit(Duration.between(tenDaysAge, LocalDateTime.now()).toDays() + 1)
                .sorted()
                .map(LocalDate::toString)
                .collect(Collectors.toList());
        // 创建所有指标的近十天每天数据项
        List<NameDataModel> dataModels = IntStream.range(0, tag.length)
                .mapToObj(index -> {
                    Map<String, String> valueMap = codeCountMap.get(tag[index]);
                    // 创建每个指标的当月每天数据项，无数据的填0
                    List<NameValueModel> models = dates.stream()
                            .map(date -> {
                                String value = "0";
                                if (MapUtils.isNotEmpty(valueMap)) {
                                    String realValue = valueMap.get(date);
                                    value = StringUtils.isBlank(realValue) ? value : realValue;
                                }
                                return new NameValueModel(date, value);
                            }).collect(Collectors.toList());
                    return new NameDataModel(tagName[index], models);
                }).collect(Collectors.toList());

        return new NameDataModel("空气质量关键指标走势", dataModels);
    }

    @Cacheable(key="'AirStationServiceImpl.airStationAverageAQILineDay'")
    public NameDataModel airStationAverageAQILineDay() {
        // 查询十天前的数据
        LocalDateTime minMinotorTime = LocalDate.now().minusDays(10).atStartOfDay();
        // 查询有数量的数据
        List<AirLevelCount> airLevelCounts = qhsjAqiInfoMapper.selectAirLevelDayCount(minMinotorTime);
        Map<String, List<AirLevelCount>> levelMap = airLevelCounts.stream()
                .collect(Collectors.groupingBy(AirLevelCount::getAqiLevel));
        // 生成十天的默认数据（填充数量0） 或者组装数据
        String levels[] = {"优", "良", "轻度污染", "中度污染", "重度污染", "严重污染"};
        List<String> dateStrings = LongStream.range(1, 10)
                .mapToObj(item -> LocalDate.now().minusDays(item))
                .sorted()
                .map(localDate -> localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .collect(Collectors.toList());
        List<AqiDateDetailModel> aqiDateDetailModels = Arrays.stream(levels)
                .map(level -> {
                    AqiDateDetailModel aqiDateDetailModel = new AqiDateDetailModel();
                    aqiDateDetailModel.setName(level);
                    List<NameValueModel> data;
                    List<AirLevelCount> levelList = levelMap.get(level);

                    if (Objects.nonNull(levelList)) {
                        // 该等级有数据，按数据填充，没有补0
                        Map<String, String> dateCount = levelList.stream()
                                .collect(Collectors.toMap(AirLevelCount::getMonitorTime, AirLevelCount::getCount));
                        data = dateStrings.stream()
                                .map(date -> dateNameValueModel(date, dateCount))
                                .collect(Collectors.toList());
                    } else {
                        // 该等级没有数据，全部补0
                        data = dateStrings.stream()
                                .map(date -> dateNameValueModel(date, null))
                                .collect(Collectors.toList());
                    }
                    aqiDateDetailModel.setData(data);
                    return aqiDateDetailModel;
                }).collect(Collectors.toList());
        // 数据结构拼接
        NameDataModel nameDataModel = new NameDataModel();
        nameDataModel.setName("空气质量指数(AQI)分布统计");
        nameDataModel.setData(aqiDateDetailModels);
        return nameDataModel;
    }

    private NameValueModel dateNameValueModel(String date, Map<String, String> dateCount) {
        String value = "0";
        if (Objects.nonNull(dateCount)) {
            String count = dateCount.get(date);
            value = StringUtils.isBlank(count) ? value : count;
        }
        NameValueModel nameValueModel = new NameValueModel();
        nameValueModel.setName(date);
        nameValueModel.setValue(value);
        return nameValueModel;
    }

}
