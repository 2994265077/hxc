package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotHiddenDangerChartMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * 安全隐患一张图--物联设备统计图表
 */
@Service
public class IotHiddenDangerChartServiceImpl {

    @Autowired
    private IotHiddenDangerChartMapper iotHiddenDangerChartMapper;
    @Autowired
    private CommunityInfoService communityInfoService;

    public PieModel leftPie(String streetName) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        List<NameValueTypeModel> nameValueTypeModels = iotHiddenDangerChartMapper.countCameraAndIot(streetCode);
        Integer total = nameValueTypeModels.stream().collect(Collectors.summingInt(nameValueTypeModel -> Integer.valueOf(nameValueTypeModel.getValue().toString())));
        return new PieModel(total, nameValueTypeModels);
    }

    public PieModel rightPie(String streetName) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        List<NameValueTypeModel> nameValueTypeModels = iotHiddenDangerChartMapper.countIotByType(streetCode);
        Integer total = nameValueTypeModels.stream().collect(Collectors.summingInt(nameValueTypeModel -> Integer.valueOf(nameValueTypeModel.getValue().toString())));
        return new PieModel(total, nameValueTypeModels);
    }

    public List<BarOrLineModel> line(String streetName, LocalDateTime begin, LocalDateTime end) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        List<BarOrLineModel> barOrLineModels = iotHiddenDangerChartMapper.countDeviceEventByMonth(YearMonth.from(begin), YearMonth.from(end), streetCode);

        barOrLineModels.stream()
                .forEach(barOrLineModel -> {
                    // 最近6月没数据的月份填充0
                    Map<String, NameValueTypeModel<Integer>> nameValueTypeModelMap = barOrLineModel.getData().stream()
                            .collect(Collectors.toMap(NameValueTypeModel::getName, nameValueTypeModel -> nameValueTypeModel));
                    long months = Period.between(begin.toLocalDate(), end.toLocalDate()).toTotalMonths();
                    List<NameValueTypeModel<Integer>> nameValueTypeModelList = LongStream.range(0, months)
                            .mapToObj(item -> YearMonth.from(end).minusMonths(item))
                            .sorted()
                            .map(yearMonth -> {
                                String yearMonthStr = yearMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));
                                // 有数据的月份返回数据库查询到的数据
                                NameValueTypeModel<Integer> nameValueTypeModel = nameValueTypeModelMap.get(yearMonthStr);
                                if (Objects.nonNull(nameValueTypeModel)) {
                                    return nameValueTypeModel;
                                }
                                // 没数据的月份填充0值
                                nameValueTypeModel = new NameValueTypeModel<>();
                                nameValueTypeModel.setName(yearMonthStr);
                                nameValueTypeModel.setValue(0);
                                return nameValueTypeModel;
                            })
                            .collect(Collectors.toList());
                    // 填充了0值之后的月份数据回填到lineModel
                    barOrLineModel.setData(nameValueTypeModelList);
                });
        return barOrLineModels;
    }
}
