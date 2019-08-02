package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.backstage.community.dao.CommunityInfoMapper;
import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.HiddenDangerThreeSmallChartMapper;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.service
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/17 16:42
 * @Updater: yhy
 * @Update_Date: 2018/11/17 16:42
 * @Update_Description: 接口过慢优化，添加按Street查询支持
 * @Description: //TODO
 **/
@Service
public class ThreeSmallPlaceChartServiceImpl {

    @Autowired
    HiddenDangerThreeSmallChartMapper hiddenDangerThreeSmallChartMapper;

    @Autowired
    private CommunityInfoService communityInfoService;

    private static final Map<String, String> THREE_SMALL_PLACE_TYPE = new HashMap<>();

    static {
        THREE_SMALL_PLACE_TYPE.put("60021", "小档口");
        THREE_SMALL_PLACE_TYPE.put("60022", "小作坊");
        THREE_SMALL_PLACE_TYPE.put("60023", "小娱乐场所");
    }

    /**
     * 字段：PLACE_TYPE
     * 60021小档口，60022小作坊，60023小娱乐场所
     */
    public PieModel pie(String street) {
        List<NameValueTypeModel> countThreeSmallType = hiddenDangerThreeSmallChartMapper.countThreeSmallType(street);
        // 将place_type 翻译成中文
        countThreeSmallType = countThreeSmallType.stream()
                .filter(nameValueTypeModel -> THREE_SMALL_PLACE_TYPE.containsKey(nameValueTypeModel.getName()))
                .peek(nameValueTypeModel -> nameValueTypeModel.setName(THREE_SMALL_PLACE_TYPE.get(nameValueTypeModel.getName())))
                .collect(Collectors.toList());
        int total = countThreeSmallType.stream().mapToInt(nameValue -> (int) nameValue.getValue()).sum();
        return new PieModel(total, countThreeSmallType);
    }

    public List<BarOrLineModel> line(String street, LocalDateTime begin, LocalDateTime end) {
        String streetCode = communityInfoService.streetCodeByName(street);
        List<BarOrLineModel> barOrLineModels = hiddenDangerThreeSmallChartMapper.countAlarmByDateAndStreet(begin, end, streetCode);
        Map<String, List<NameValueTypeModel<Integer>>> placeTypeMap = barOrLineModels.stream()
                .collect(Collectors.toMap(BarOrLineModel::getName, BarOrLineModel::getData));
        return THREE_SMALL_PLACE_TYPE.entrySet().stream().map(entry -> {
            List<NameValueTypeModel<Integer>> nameValueTypeModels = placeTypeMap.get(entry.getKey());
            List<NameValueTypeModel<Integer>> resNameValues = createAndFillDefaultNameValue(nameValueTypeModels, begin, end);
            return BarOrLineModel.builder().name(entry.getValue()).data(resNameValues).build();
        }).collect(Collectors.toList());
    }



    private List<NameValueTypeModel<Integer>> createAndFillDefaultNameValue(List<NameValueTypeModel<Integer>> paramNameValues,  LocalDateTime begin, LocalDateTime end) {
        Map<String, NameValueTypeModel<Integer>> paramMap = null;
        if (Objects.nonNull(paramNameValues)) {
            paramMap = paramNameValues.stream().collect(Collectors.toMap(NameValueTypeModel::getName, nvtm -> nvtm));
        }
        return createAndFillDefaultNameValue(paramMap, begin, end);
    }


    private List<NameValueTypeModel<Integer>> createAndFillDefaultNameValue(Map<String, NameValueTypeModel<Integer>> paramMap, LocalDateTime begin, LocalDateTime end) {
        long monthCount = Period.between(begin.toLocalDate(), end.toLocalDate().plusMonths(1)).toTotalMonths();
        return LongStream.range(0, monthCount + 1)
                .mapToObj(item -> end.toLocalDate()
                        .minusMonths(monthCount - item)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM")))
                .map(yearMonth -> {
                    if (Objects.nonNull(paramMap) && paramMap.containsKey(yearMonth)) {
                        return paramMap.get(yearMonth);
                    }
                    return new NameValueTypeModel<>(yearMonth, 0);
                }).collect(Collectors.toList());
    }



    //三小场所隐患类型统计：

    /**
     * 判断事件状态是否为已整改
     * 2|3表示已整改
     * 0受理中，1已分拨，2已办结，3已归档
     */
    public List<BarOrLineModel> bar(String street, LocalDateTime begin, LocalDateTime end) {
        PageHelper.startPage(1, 5);
        List<NameValueTypeModel<Integer>> total = hiddenDangerThreeSmallChartMapper.countThreeSmallEventByType(street, begin, end);
        List<String> eventTypes = total.stream().map(NameValueTypeModel::getName).collect(Collectors.toList());
        List<NameValueTypeModel<Integer>> modified = hiddenDangerThreeSmallChartMapper.countUndisposedByType(eventTypes, street, begin, end);

        //序列一： 按类型统计总数
        BarOrLineModel totalCountByType = new BarOrLineModel("隐患总数", total);
        //序列二：按类型统计已完成数量
        BarOrLineModel finishedCountByType = new BarOrLineModel("已整改", modified);

        List<BarOrLineModel> result = new ArrayList<BarOrLineModel>();
        result.add(totalCountByType);
        result.add(finishedCountByType);
        return result;
    }

    public List<BarOrLineModel> streetBar(String street, LocalDateTime begin, LocalDateTime end) {
        List<NameValueTypeModel<Integer>> totalCount = hiddenDangerThreeSmallChartMapper.countThreeSmallPlaceByStreet(street, null);
        BarOrLineModel totalCountModel = new BarOrLineModel();
        totalCountModel.setName("三小场所数量");
        totalCountModel.setData(totalCount);

        List<NameValueTypeModel<Integer>> hasTroubleCount = hiddenDangerThreeSmallChartMapper.countThreeSmallPlaceByStreetAndDate(street, "1", begin, end);
        BarOrLineModel hasTroubleCountModel = new BarOrLineModel();
        hasTroubleCountModel.setName("隐患数量");
        hasTroubleCountModel.setData(hasTroubleCount);

        return Arrays.asList(totalCountModel, hasTroubleCountModel);
    }

}
