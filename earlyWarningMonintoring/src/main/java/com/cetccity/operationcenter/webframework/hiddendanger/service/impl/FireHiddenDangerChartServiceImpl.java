package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.backstage.community.dao.CommunityInfoMapper;
import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.FireHiddenDangerChartMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.StreetBuilderLevel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.dao
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/18 18:42
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/18 18:42
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/

/**
 * score_build < 35低风险
 * 70 > score_build >= 35
 * 高风险 > 70
 */
@Service
@Slf4j
public class FireHiddenDangerChartServiceImpl {

    public static HttpResponseModel<PieModel> pieCache;

    @Autowired
    FireHiddenDangerChartMapper fireHiddenDangerChartDao;

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    private CommunityInfoService communityInfoService;

    @Autowired
    private FireHiddenDangerChartMapper fireHiddenDangerChartMapper;


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public enum BuildDangerLevel{
        LOW_LEVEL(1, "低风险", 20),
        MIDDER_LOW_LEVEL(2, "中低风险", 60),
        MIDDER_LEVEL(3, "中风险", 70),
        MIDDER_HIGHT_LEVEL(4, "中高风险", 80),
        HIGHT_LEVEL(5, "高风险", Integer.MAX_VALUE);
        private Integer code;
        private String name;
        private Integer maxScore;
    }


    public PieModel pie(String street) {
        String streetCode = communityInfoService.streetCodeByName(street);
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<NameValueTypeModel> queryRes = fireHiddenDangerChartMapper.selectBuilderLevelCount(dateStr, streetCode, Arrays.asList(BuildDangerLevel.values()));
        // NameValueTypeModel<BigDecimal> --> Map<DangerLevel, NameValueTypeModel<Integer>>
        Map<String, NameValueTypeModel<Integer>> levelCountMap = queryRes
                .stream()
                .peek(nameValueTypeModel -> nameValueTypeModel.setValue(((BigDecimal) nameValueTypeModel.getValue()).intValue()))  // bigdecimal -> Integer
                .collect(Collectors.toMap(NameValueTypeModel::getName, obj -> obj));
        // 总数
        Long total = levelCountMap.values().stream()
                .mapToLong(NameValueTypeModel::getValue)
                .sum();
        // 生成风险等级数量列表，并填充具体数量
        List<NameValueTypeModel> data = Arrays.stream(BuildDangerLevel.values())
                .map(buildDangerLevel -> {
                    NameValueTypeModel nameValueTypeModel = levelCountMap.get(buildDangerLevel.getName());
                    if (Objects.isNull(nameValueTypeModel)) {
                        nameValueTypeModel = new NameValueTypeModel();
                        nameValueTypeModel.setName(buildDangerLevel.name);
                        nameValueTypeModel.setValue(0);
                    }
                    return nameValueTypeModel;
                })
                .collect(Collectors.toList());
        return new PieModel(total.intValue(), data);
    }


    /**
     * 功能描述: <br>
     * 〈查询今日楼栋高风险数量饼图 按街道名统计〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel>
     * @Author:yhy
     * @Date: 2019/5/7 10:50
     */
    public List<BarOrLineModel> bar(String street) {
        String streetCode = communityInfoService.streetCodeByName(street);
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<StreetBuilderLevel> queryRes = fireHiddenDangerChartMapper.selectStreetBuilderLevelCount(dateStr, streetCode, Arrays.asList(BuildDangerLevel.values()));
        return barOrLineFromBuilderLevel(queryRes);
    }

    /**
     * 功能描述: <br>
     * 〈根据街道名查询今日楼栋高风险数量饼图 按社区名统计〉
     *
     * @param streetName 街道名
     * @return:java.util.List<com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel>
     * @Author:yhy
     * @Date: 2019/5/7 10:51
     */
    public List<BarOrLineModel> barByStreetName(String streetName) {
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<StreetBuilderLevel> queryRes = fireHiddenDangerChartMapper.selectStreetBuilderLevelCountByStreet(dateStr, streetName, Arrays.asList(BuildDangerLevel.values()));
        return barOrLineFromBuilderLevel(queryRes);
    }

    /**
     * 功能描述: <br>
     * 〈〉
     *  入参 name（街道或者社区名称）  危险等级  数量
     *  按照危险等级分组
     *  危险等级为bar的name
     *  name（街道或者社区名称）为namevalue的name字段
     *  返回List<BarOrLineModel<NameValueTypeModel<Integer>>>
     * @param queryRes name（街道或者社区名称）  危险等级  数量
     * @return:java.util.List<com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel>
     * @Author:yhy
     * @Date: 2019/5/6 11:31
     */
    private List<BarOrLineModel> barOrLineFromBuilderLevel(List<StreetBuilderLevel> queryRes) {
        return queryRes.stream()
                .filter(streetBuilderLevel -> Objects.nonNull(streetBuilderLevel.getDangerLevel()))
                .collect(Collectors.groupingBy(StreetBuilderLevel::getDangerLevel))
                .entrySet()
                .stream()
                .map(entry -> {
                    String name = entry.getKey();
                    List<StreetBuilderLevel> value = entry.getValue();
                    List<NameValueTypeModel<Integer>> nameValueTypeModels = value.stream()
                            .sorted(Comparator.comparingInt(StreetBuilderLevel::getCount).reversed())
                            .map(streetBuilderLevel -> {
                                NameValueTypeModel<Integer> nameValueTypeModel = new NameValueTypeModel<>();
                                nameValueTypeModel.setName(streetBuilderLevel.getName());
                                nameValueTypeModel.setValue(streetBuilderLevel.getCount());
                                return nameValueTypeModel;
                            })
                            .collect(Collectors.toList());
                    return BarOrLineModel
                            .builder()
                            .name(name)
                            .data(nameValueTypeModels)
                            .build();
                })
                .collect(Collectors.toList());
    }

}
