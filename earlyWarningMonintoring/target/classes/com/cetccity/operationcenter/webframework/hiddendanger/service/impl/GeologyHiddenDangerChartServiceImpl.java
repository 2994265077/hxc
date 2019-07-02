package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.GeologyHiddenDangerChartMapper;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  安全隐患一张图--地质图表
 */
@Service
public class GeologyHiddenDangerChartServiceImpl {

    @Autowired
    private GeologyHiddenDangerChartMapper geologyHiddenDangerChartMapper;

    @Autowired
    private CommunityInfoService communityInfoService;

    private static final Map<String, String> BP_TYPES_MAP = new HashMap<>();
    private static final Map<String, String> CHECKED_CODE_MAP = new HashMap<>();
    private static final Map<String, String> FORECAST_DANGER_MAP = new HashMap<>();

    static {
        //BP_TYPE:边坡类型（0:建筑边坡;1:地灾隐患）1024:道路病害
        //IS_GOVERMANCED:是否已治理（1:已完成;0:未完成）
        //IS_CHECKED:是否已巡查（1-已巡查;0-未巡查）
        BP_TYPES_MAP.put("0", "建筑边坡");
        BP_TYPES_MAP.put("1", "地灾隐患");
        BP_TYPES_MAP.put("1024", "道路病害");
        CHECKED_CODE_MAP.put("0", "非检查点");
        CHECKED_CODE_MAP.put("1", "检查点");
        FORECAST_DANGER_MAP.put("小", "小");
        FORECAST_DANGER_MAP.put("中", "中");
        FORECAST_DANGER_MAP.put("已挖除", "无");
    }

    public NameValueTypeModel countAll(String streetName) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        int count = geologyHiddenDangerChartMapper.countAll(streetCode);
        return new NameValueTypeModel<Integer>("地质隐患总数",count);
    }

    public PieModel leftTopPie(String streetName) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        List<NameValueTypeModel> nameValueTypeModels = geologyHiddenDangerChartMapper.countHiddenType(streetCode);
        List<NameValueTypeModel> modelList = nameValueTypeModels.stream()
                .filter(nameValueTypeModel -> BP_TYPES_MAP.containsKey(nameValueTypeModel.getName()))
                .peek(nameValueTypeModel -> nameValueTypeModel.setName(BP_TYPES_MAP.get(nameValueTypeModel.getName())))
                .collect(Collectors.toList());
        // 原来的代码逻辑 pie的total是类型数而不是总count
        return new PieModel(modelList.size(), modelList);
    }

    public PieModel rightTopPie(String streetName) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        List<NameValueTypeModel> nameValueTypeModels = geologyHiddenDangerChartMapper.countCheckType(streetCode);
        List<NameValueTypeModel> modelList = nameValueTypeModels.stream()
                .filter(nameValueTypeModel -> CHECKED_CODE_MAP.containsKey(nameValueTypeModel.getName()))
                .peek(nameValueTypeModel -> nameValueTypeModel.setName(CHECKED_CODE_MAP.get(nameValueTypeModel.getName())))
                .collect(Collectors.toList());
        return new PieModel(modelList.size(), modelList);
    }

    /**
     * 小、中、无（已挖除、null）
     * @return
     */
    public PieModel bottomPie(String streetName) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoService.streetCodeByName(streetName);
        }
        List<NameValueTypeModel> nameValueTypeModels = geologyHiddenDangerChartMapper.countForecastDanger(streetCode);
        List<NameValueTypeModel> modelList = nameValueTypeModels.stream()
                .filter(nameValueTypeModel -> FORECAST_DANGER_MAP.containsKey(nameValueTypeModel.getName()))
                .peek(nameValueTypeModel -> nameValueTypeModel.setName(FORECAST_DANGER_MAP.get(nameValueTypeModel.getName())))
                .collect(Collectors.toList());
        return new PieModel(modelList.size(), modelList);
    }
}
