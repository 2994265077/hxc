package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TheadTbodyPlus;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.EmergencyFireHiddenDangerChartMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.service.impl
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/19 21:06
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/19 21:06
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Service
public class EmergencyFireHiddenDangerChartServiceImpl {

    @Autowired
    private EmergencyFireHiddenDangerChartMapper emergencyFireHiddenDangerChartMapper;
    @Autowired
    private CommunityInfoService communityInfoService;

    public List<NameValueTypeModel> count5Num(String streetName) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        return emergencyFireHiddenDangerChartMapper.count5Num(streetCode);
    }

    public PieModel leftPie(String streetName) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        List<NameValueTypeModel> nameValueTypeModels = emergencyFireHiddenDangerChartMapper.queryEmergencyShelterCapacity(streetCode);
        Integer total = nameValueTypeModels.stream()
                .collect(Collectors.summingInt(nameValueTypeModel -> (Integer) nameValueTypeModel.getValue()));
        return new PieModel(total,nameValueTypeModels);
    }

    public PieModel rightPie(String streetName) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        List<NameValueTypeModel<Integer>> nameValueTypeModels = emergencyFireHiddenDangerChartMapper.queryEmergencyHarzardType(streetCode);
        // 计算总数
        Integer total = nameValueTypeModels.stream().collect(Collectors.summingInt(NameValueTypeModel::getValue));
        // 生成key的函数
        Function<NameValueTypeModel<Integer>, String> keyMapper = nameValueTypeModel -> {
            String name = nameValueTypeModel.getName();
            if (Objects.nonNull(name)) {
                if (name.startsWith("火灾")) {
                    return "火灾";
                }
                if (name.startsWith("雨涝")) {
                    return "雨涝";
                }
            }
            return "其他";
        };
        // 数据库中字段值后面多了其他干扰字符，且干扰字符不统一
        // 统一转成需要的三种key， 收集成map时，相同key的值相加
        List<NameValueTypeModel> resultList = nameValueTypeModels.stream()
                .collect(Collectors.toMap(keyMapper, NameValueTypeModel::getValue, (v1, v2) -> v1 + v2))
                .entrySet()
                .stream()
                .map(entry -> NameValueTypeModel.builder().name(entry.getKey()).value(entry.getValue()).build())
                .collect(Collectors.toList());

        return new PieModel(total,resultList);
    }

    public TheadTbodyPlus table(String streetName){
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        List<NameValueTypeModel> queryList = emergencyFireHiddenDangerChartMapper.queryEmergencyHouseAndSource(streetCode);
        List<List<Object>> tbody = queryList.stream()
                .map(nameValueTypeModel -> Arrays.asList(nameValueTypeModel.getName(), nameValueTypeModel.getValue()))
                .collect(Collectors.toList());
        return new TheadTbodyPlus(Arrays.asList("应急仓库", "物资数量"), tbody);
    }
}
