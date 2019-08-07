/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: NewAlarmService
 * Author:   YHY
 * Date:     2019/8/2 16:55
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.alarmcenter.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.NewAlarmMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.vo.AlarmLevelCount;
import com.cetccity.operationcenter.webframework.alarmcenter.vo.AlarmTypeModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.urbansign.api.model.NameValueDataModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/8/2
 * @since 1.0.0
 */
@Service
public class NewAlarmService {
    @Autowired
    private NewAlarmMapper newAlarmMapper;

    private List<NameValueModel> levels = Arrays.asList(new NameValueModel("一级", "一级-红"),
            new NameValueModel("二级", "二级-橙"), new NameValueModel("三级", "三级-黄"), new NameValueModel("四级", "四级-蓝")
            );

    public List<AlarmLevelCount> levels(LocalDate localDate, String type, String typeV1, String typeV2) {
        String alarmCondition = getCondition(type);
        Map<String, Integer> queryCounts;
        if (Objects.nonNull(localDate)) {
            queryCounts = newAlarmMapper.countByLevel(
                        localDate.atStartOfDay(),
                        localDate
                        .plusDays(1)
                        .atStartOfDay(),
                        alarmCondition,
                        typeV1,
                        typeV2
                    )
                    .stream()
                    .collect(Collectors.toMap(NameValueTypeModel::getName, NameValueTypeModel::getValue));
        } else {
            queryCounts = newAlarmMapper.countByLevel(null, null, alarmCondition, typeV1, typeV2).stream().collect(Collectors.toMap(NameValueTypeModel::getName, NameValueTypeModel::getValue));
        }
        Map<String, Integer> todayCount = newAlarmMapper.countByLevel( LocalDate.now().atStartOfDay(), LocalDate.now().plusDays(1).atStartOfDay(), alarmCondition, typeV1, typeV2).stream().collect(Collectors.toMap(NameValueTypeModel::getName, NameValueTypeModel::getValue));
        List<AlarmLevelCount> collect = levels.stream()
                .map(param -> AlarmLevelCount.builder()
                        .name(param.getName())
                        .value(param.getValue())
                        .count(queryCounts.containsKey(param.getValue()) ? queryCounts.get(param.getValue()) : 0)
                        .todayCount(todayCount.containsKey(param.getValue()) ? todayCount.get(param.getValue()) : 0)
                        .build())
                .collect(Collectors.toList());
        Integer queryTotal = queryCounts.values().stream().collect(Collectors.summingInt(obj -> obj));
        Integer todayTotal = todayCount.values().stream().collect(Collectors.summingInt(obj -> obj));
        collect.add(0, AlarmLevelCount.builder().name("全部").value("").count(queryTotal).todayCount(todayTotal).build());
        return collect;
    }

    public List<NameValueDataModel<Integer>> alarmTypeLv1s(LocalDate localDate, String type, String level) {
        String alarmCondition = getCondition(type);
        LocalDateTime begin = null;
        LocalDateTime end = null;
        if (Objects.nonNull(localDate)) {
            end = localDate.plusDays(1L).atStartOfDay();
        }
        if (StringUtils.isNotBlank(level)) {
            level = Arrays.stream(level.split(","))
                    .map(str -> "'" + str + "'")
                    .collect(Collectors.joining(","));
        }
        List<NameValueDataModel<Integer>> nameValueDataModels = newAlarmMapper.countByTypeLv1s(begin, end, alarmCondition, level);
        nameValueDataModels.sort(Comparator.comparingInt(obj -> Integer.valueOf(obj.getName())));
        return nameValueDataModels;
    }

    public List<AlarmTypeModel> alarmTypeLv2s(LocalDate localDate, String type, String level, String typeV1) {
        String alarmCondition = getCondition(type);
        LocalDateTime begin = null;
        LocalDateTime end = null;
        if (Objects.nonNull(localDate)) {
            end = localDate.plusDays(1L).atStartOfDay();
        }
        if (StringUtils.isNotBlank(level)) {
            level = Arrays.stream(level.split(","))
                    .map(str -> "'" + str + "'")
                    .collect(Collectors.joining(","));
        }
        List<AlarmTypeModel> res = newAlarmMapper.countByTypeLv2s(begin, end, alarmCondition, level, typeV1);
        res.sort(Comparator.comparingInt(alarmtype -> Integer.valueOf(alarmtype.getCode())));
        int sum = res.stream().peek(obj -> obj.setLayerid(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName+"."+obj.getCode()))).mapToInt(AlarmTypeModel::getCount).sum();
        AlarmTypeModel total = AlarmTypeModel.builder().name("全部").code("").count(sum).layerid("").build();

        res.add(0, total);
        return res;
    }

    public List types() {
        List<NameValueModel> types = newAlarmMapper.types();
        List<String> warn = types.stream()
                .filter(nameValueModel -> nameValueModel.getName().endsWith("预警"))
                .map(NameValueModel::getValue)
                .map(str -> LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName+"."+str))
                .collect(Collectors.toList());
        List<String> monitor = types.stream()
                .filter(nameValueModel -> nameValueModel.getName().endsWith("报警"))
                .map(NameValueModel::getValue)
                .map(str -> LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties",ESOperate.dbName+"."+str))
                .collect(Collectors.toList());
        List<String> total = Stream.concat(warn.stream(), monitor.stream()).collect(Collectors.toList());
        return Arrays.asList(new NameValueDataModel("全部事件", "", total), new NameValueDataModel("监测事件", "0", monitor), new NameValueDataModel("预警事件", "1", warn));
    }

    private String getCondition(String type) {
        String alarmCondition = null;
        if ("1".equals(type)) {
            alarmCondition = "%预警%";
        }
        if ("0".equals(type)) {
            alarmCondition = "%报警%";
        }
        return alarmCondition;
    }

}