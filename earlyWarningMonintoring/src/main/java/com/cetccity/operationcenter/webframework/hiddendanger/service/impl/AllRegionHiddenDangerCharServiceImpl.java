package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.ImageFileTool;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.ThreeSmallHiddenDangerInfo;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.ThreeSmallPageInfoModel;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.AllRegionHiddenDangerChartMapper;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.FireHiddenDangerChartMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.NameValuePlus;
import com.cetccity.operationcenter.webframework.web.model.incident.AlarmTodayType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.service.impl
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/20 11:05
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/20 11:05
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Service
@Slf4j
public class AllRegionHiddenDangerCharServiceImpl {

    @Autowired
    AllRegionHiddenDangerChartMapper allRegionHiddenDangerChartMapper;

    @Autowired
    FireHiddenDangerChartServiceImpl fireHiddenDangerChartService;

    @Autowired
    private FireHiddenDangerChartMapper fireHiddenDangerChartMapper;

    @Autowired
    private CommunityInfoService communityInfoService;

    private static final Map<String, String> THREE_SMALL_PLACE_TYPE = new HashMap<>();

    static {
        THREE_SMALL_PLACE_TYPE.put("60021", "小档口");
        THREE_SMALL_PLACE_TYPE.put("60022", "小作坊");
        THREE_SMALL_PLACE_TYPE.put("60023", "小娱乐场所");
    }

    public HttpResponseModel<PieModel> pie(String street) {
        String streetCode = communityInfoService.streetCodeByName(street);
        final HashMap<String,Object> queryMap = allRegionHiddenDangerChartMapper.countAll(streetCode);
        Object dangerSlope = queryMap.get("DANGER_SLOPE");
        Object roadDiseaseNum = queryMap.get("ROAD_DISEASE_NUM");
        dangerSlope = Objects.nonNull(dangerSlope) ? dangerSlope : "0";
        roadDiseaseNum = Objects.nonNull(roadDiseaseNum) ? roadDiseaseNum : "0";
        int dizhiNum = Integer.parseInt(dangerSlope.toString())+Integer.parseInt(roadDiseaseNum.toString());
        PieModel pieModel = new PieModel(0, new ArrayList<NameValueTypeModel>(){{
            add(NameValueTypeModel.builder().name("三小场所").value(queryMap.containsKey("SANXIAO_PLACE_NUM") ? queryMap.get("SANXIAO_PLACE_NUM") : 0).build());
            add(new NameValueTypeModel("地质", dizhiNum));
            add(new NameValueTypeModel("消防", queryMap.containsKey("IOT_FIRE_NUM") ? queryMap.get("IOT_FIRE_NUM") : 0));
            add(new NameValueTypeModel("危险源", queryMap.containsKey("RISK_POINT_NUM") ? queryMap.get("RISK_POINT_NUM") : 0));
        }});
        return new HttpResponseModel<PieModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, pieModel);
    }

    /**
     * 功能描述: <br>
     * 〈全区隐患 柱状图 按街道名统计〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel>
     * @Author:yhy
     * @Date: 2019/5/7 10:54
     */
    public List<BarOrLineModel> bar(String street, LocalDateTime begin, LocalDateTime end) {
        List<BarOrLineModel> result = new ArrayList<BarOrLineModel>();
        BarOrLineModel smallEventBar = threeSmallEventBar(street, begin, end);
        BarOrLineModel geologyNumBar = geologyNumBar(street);
        BarOrLineModel fireHiddenDangerBar = fireHiddenDangerBar(street);

        result.add(smallEventBar);
        result.add(geologyNumBar);
        result.add(fireHiddenDangerBar);
        return result;
    }

    /**
     * 功能描述: <br>
     * 〈根据街道名全区隐患 饼图 按社区名统计〉
     *
     * @param streetName
     * @return:java.util.List<com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel>
     * @Author:yhy
     * @Date: 2019/5/7 10:55
     */
    public List<BarOrLineModel> barByStreetName(String streetName, LocalDateTime begin, LocalDateTime end) {
        List<BarOrLineModel> result = new ArrayList<BarOrLineModel>();
        BarOrLineModel smallEventBar = threeSmallEventBarByStreetName(streetName, begin, end);
        BarOrLineModel geologyNumBar = geologyNumBarByStreetName(streetName);
        BarOrLineModel fireHiddenDangerBar = fireHiddenDangerBarByStreetName(streetName);

        result.add(smallEventBar);
        result.add(geologyNumBar);
        result.add(fireHiddenDangerBar);
        return result;
    }

    /**
     * 功能描述: <br>
     * 〈地质隐患饼图 按街道名统计〉
     *
     * @param
     * @return:com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel
     * @Author:yhy
     * @Date: 2019/5/7 10:56
     */
    public BarOrLineModel geologyNumBar(String street) {
        String streetCode = communityInfoService.streetCodeByName(street);
        List<NameValueTypeModel<Integer>> geologyNums = allRegionHiddenDangerChartMapper.queryGeologyNumByStreet(streetCode);
        return new BarOrLineModel("地质隐患", geologyNums);
    }

    /**
     * 功能描述: <br>
     * 〈根据街道名查询地质隐患饼图 按社区名统计〉
     *
     * @param streetName 街道名
     * @return:com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel
     * @Author:yhy
     * @Date: 2019/5/7 10:56
     */
    public BarOrLineModel geologyNumBarByStreetName(String streetName) {
        List<NameValueTypeModel<Integer>> geologyNums = allRegionHiddenDangerChartMapper.queryGeologyNumByStreetName(streetName);
        return new BarOrLineModel("地质隐患", geologyNums);
    }

    /**
     * 功能描述: <br>
     * 〈查询三小场所隐患饼图 按街道名统计〉
     *
     * @param
     * @return:com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel
     * @Author:yhy
     * @Date: 2019/5/7 10:56
     */
    public BarOrLineModel threeSmallEventBar(String street, LocalDateTime begin, LocalDateTime end) {
        String streetCode = communityInfoService.streetCodeByName(street);
        List<NameValueTypeModel<Integer>>  threeSmallEvents = allRegionHiddenDangerChartMapper.queryThreeSmallEvent(streetCode, begin, end);
        return new BarOrLineModel("三小场所隐患", threeSmallEvents);
    }

    /**
     * 功能描述: <br>
     * 〈根据街道名查询三小场所隐患饼图 按社区名统计〉
     *
     * @param streetName
     * @return:com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel
     * @Author:yhy
     * @Date: 2019/5/7 10:56
     */
    public BarOrLineModel threeSmallEventBarByStreetName(String streetName, LocalDateTime begin, LocalDateTime end) {
        List<NameValueTypeModel<Integer>>  threeSmallEvents = allRegionHiddenDangerChartMapper.queryThreeSmallEventByStreetName(streetName, begin, end);
        return new BarOrLineModel("三小场所隐患", threeSmallEvents);
    }

    /**
     * 功能描述: <br>
     * 〈查询今日消防隐患饼图 按街道名统计〉
     *
     * @param
     * @return:com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel
     * @Author:yhy
     * @Date: 2019/5/7 10:56
     */
    public BarOrLineModel fireHiddenDangerBar(String street) {
        String streetCode = communityInfoService.streetCodeByName(street);
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // 大于中风险的最大值，相当于中高，高风险
        List<NameValueTypeModel<Integer>> fireDangers = fireHiddenDangerChartMapper.countDangerByDate(dateStr, FireHiddenDangerChartServiceImpl.BuildDangerLevel.MIDDER_LEVEL.getMaxScore(), streetCode);
        return new BarOrLineModel("消防隐患", fireDangers);
    }

    /**
     * 功能描述: <br>
     * 〈根据街道名查询今日消防隐患饼图 按社区名统计〉
     *
     * @param streetName 街道名
     * @return:com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel
     * @Author:yhy
     * @Date: 2019/5/7 10:56
     */
    public BarOrLineModel fireHiddenDangerBarByStreetName(String streetName) {
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // 大于中风险的最大值，相当于中高，高风险
        List<NameValueTypeModel<Integer>> fireDangers = fireHiddenDangerChartMapper.countDangerByDateAndStreet(dateStr, FireHiddenDangerChartServiceImpl.BuildDangerLevel.MIDDER_LEVEL.getMaxScore(), streetName);
        return new BarOrLineModel("消防隐患", fireDangers);
    }

    public MyPageInfoModel<List<ThreeSmallHiddenDangerInfo>> list(String startTime, String endTime, int pageNum, int pageSize,String placeType, String street) {
        String streetCode = communityInfoService.streetCodeByName(street);
        PageHelper.startPage(pageNum, pageSize);
        List<ThreeSmallHiddenDangerInfo> threeSmallHiddenDangerInfoList = allRegionHiddenDangerChartMapper.queryThreeSmallHiddenDangerList(startTime, endTime, placeType, streetCode);
        if (CollectionUtils.isNotEmpty(threeSmallHiddenDangerInfoList)) {
            List<String> systemIds = threeSmallHiddenDangerInfoList.stream()
                    .map(ThreeSmallHiddenDangerInfo::getSystemId)
                    .collect(Collectors.toList());
            List<NameValueModel> nameValueModels = allRegionHiddenDangerChartMapper.picsBySystemIds(systemIds);
            Map<String, String> systemIdPicMap = nameValueModels.stream()
                    .collect(Collectors.toMap(NameValueModel::getName, NameValueModel::getValue, (o1, o2) -> o1));
            for (ThreeSmallHiddenDangerInfo info: threeSmallHiddenDangerInfoList) {
                String pics = systemIdPicMap.get(info.getSystemId());
                pics = Objects.nonNull(pics) ? pics : "";
                HttpResponseModel<String> imgUrl = ImageFileTool.getSanxiaoImageFileUrl(pics);
                info.setPicture(imgUrl.getData());
                info.setState(ThreeSmallHiddenDangerInfo.stateDic(info.getState()));
                info.setLayerid(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", ESOperate.dbName+".BLK_SANXIAO_PLACE@"+info.getPlace_type()).split("#")[0]);
            }
        }

        PageInfo<ThreeSmallHiddenDangerInfo> pageInfo = new PageInfo(threeSmallHiddenDangerInfoList);
        ThreeSmallPageInfoModel<List<ThreeSmallHiddenDangerInfo>> myPageInfoModel = new ThreeSmallPageInfoModel<List<ThreeSmallHiddenDangerInfo>>();
        myPageInfoModel.setPageNum(pageInfo.getPageNum());
        myPageInfoModel.setPages(pageInfo.getPages());
        myPageInfoModel.setPageSize(pageInfo.getPageSize());
        myPageInfoModel.setTotal(pageInfo.getTotal());
        myPageInfoModel.setList(threeSmallHiddenDangerInfoList);
        Map map = new HashMap();
        map.put("小档口","60021");
        map.put("小作坊","60022");
        map.put("小娱乐场所","60023");
        myPageInfoModel.setType(map);
        return myPageInfoModel;
    }

    /**
     * 功能描述: <br>
     * 〈根据类型统计三小隐患数量〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.NameValuePlus>
     * @Author:yhy
     * @Date: 2019/5/7 10:57
     */
    public List<NameValuePlus> countThreeSmallDangerByType(String street,String startTime,String endTime) {
        String streetCode = communityInfoService.streetCodeByName(street);

        List<NameValuePlus> nameValuePluses = allRegionHiddenDangerChartMapper.countThreeSmallDangerByType(streetCode,startTime,endTime);
        Map<String, NameValuePlus> nameValuePlusMap = nameValuePluses.stream()
                .collect(Collectors.toMap(NameValuePlus::getType, nameValuePlus -> nameValuePlus));
        nameValuePluses = THREE_SMALL_PLACE_TYPE
                .entrySet()
                .stream()
                .map(entry -> {
                    if (nameValuePlusMap.containsKey(entry.getKey())) {
                        return nameValuePlusMap.get(entry.getKey());
                    }
                    NameValuePlus nameValuePlus = new NameValuePlus();
                    nameValuePlus.setType(entry.getKey());
                    nameValuePlus.setName(entry.getValue());
                    nameValuePlus.setValue(0);
                    return nameValuePlus;
                })
                .collect(Collectors.toList());
        nameValuePluses.stream()
                .forEach(item -> {
                    String layerId = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", ESOperate.dbName + ".BLK_SANXIAO_PLACE@" + item.getType());
                    item.setLayerid(layerId);
                });
        return nameValuePluses;
    }

    /**
     * 功能描述: <br>
     * 〈根据类型统计报警（预警）数〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.NameValuePlus>
     * @Author:yhy
     * @Date: 2019/5/7 10:57
     */
    public List<NameValuePlus> countAlarmByType(String street, LocalDateTime begin, LocalDateTime end) {
        String streetCode = communityInfoService.streetCodeByName(street);
        List<NameValuePlus> nameValuePluses = allRegionHiddenDangerChartMapper.countAlarmsByType(streetCode, begin, end);
        nameValuePluses.stream()
                .forEach(item -> {
                    String layerId = LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", ESOperate.dbName + "." + item.getType());
                    item.setLayerid(layerId);
                });
        return nameValuePluses;
    }

    /**
     * 功能描述: <br>
     * 〈根据报警事件类型分页查询预警列表〉
     *
     * @param type 报警事件类型
     * @param pageNum 页码
     * @param pageSize 页码大小
     * @return:com.github.pagehelper.PageInfo<com.cetccity.operationcenter.webframework.web.model.incident.AlarmTodayType>
     * @Author:yhy
     * @Date: 2019/5/7 10:57
     */
    public PageInfo<AlarmTodayType> queryAlarmByType(String type, int pageNum, int pageSize, String street, LocalDateTime begin, LocalDateTime end) {
        PageHelper.startPage(pageNum, pageSize);
        String streetCode = communityInfoService.streetCodeByName(street);
        List<AlarmTodayType> res = allRegionHiddenDangerChartMapper.queryAlarmByType(type, streetCode, begin, end);
        // 塞入layerid
        res.stream()
                .forEach(alarmTodayType ->
                        alarmTodayType.setLayerid(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName+"."+alarmTodayType.getCode()))
                );
        PageInfo<AlarmTodayType> pageInfo = new PageInfo<>(res);
        return pageInfo;
    }
}
