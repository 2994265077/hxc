/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: WeijiAggregateConverter
 * Author:   YHY
 * Date:     2019/5/22 17:20
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.converter;

import com.cetccity.operationcenter.webframework.trigger.core.converter.AbstractAggregatesConverter;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.StrDateRange;
import com.cetccity.operationcenter.webframework.trigger.entity.YjjcQwjjSdmInfoV;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/22
 * @since 1.0.0
 */
public abstract class WeijiAggregateConverter extends AbstractAggregatesConverter<YjjcQwjjSdmInfoV> {

    protected long thresholdCount;

    protected String thresholdString;

    protected static final DateTimeFormatter DEFAUL_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private AlarmInformationV1Mapper alarmInformationMapper;




    @Override
    public Collection<AlarmInformation> convert(Collection<YjjcQwjjSdmInfoV> sources) {
        Optional<AlarmInformation> rencentAlarm = rencentAlarm();
        if (!rencentAlarm.isPresent()) {
            return newAlarm(sources);
        }
        return updateAlarm(sources, rencentAlarm.get());
    }

    protected Optional<AlarmInformation> rencentAlarm() {
        LocalDate localDate = LocalDate.now();
        return queryLastAlarm(
                getAlarmTypeLv2(),
                localDate.atStartOfDay().format(DEFAUL_TIME_FORMATTER),
                localDate.plusDays(1).atStartOfDay().format(DEFAUL_TIME_FORMATTER));
    }

    protected Collection<AlarmInformation> updateAlarm(Collection<YjjcQwjjSdmInfoV> sources, AlarmInformation rencentAlarm) {
        AlarmInformation updateAlarmBean = new AlarmInformation();
        updateAlarmBean.setOBJECT_ID(rencentAlarm.getOBJECT_ID());
        fillAlarmCommon(sources, updateAlarmBean);
        return Arrays.asList(updateAlarmBean);
    }

    protected Collection<AlarmInformation> newAlarm(Collection<YjjcQwjjSdmInfoV> sources) {

        AlarmInformation alarmInformation = defaultAlarmInformation();
        fillAlarmCommon(sources, alarmInformation);
        alarmInformation.setCONTENTS( thresholdString + "预警");
        alarmInformation.setALARM_LEVEL("特殊病情");
        alarmInformation.setCHANNEL("卫计局");

        return Arrays.asList(alarmInformation);
    }

    protected void fillAlarmCommon(Collection<YjjcQwjjSdmInfoV> sources, AlarmInformation alarmInformation) {
        sources = sources.stream().filter(this::predicate).collect(Collectors.toList());
        Optional<Date> max = sources
                .stream()
                .map(YjjcQwjjSdmInfoV::getDIAG_TIME)
                .filter(Objects::nonNull)
                .max(Date::compareTo);
        Instant instant = max.get().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);

        String mutiAddress = mutiAddress(sources);
        String mutiDetail = generateMutiDetail(localDateTime, mutiAddress, sources.size(), thresholdString);
        alarmInformation.setRELEASE_TIME(max.get());
        alarmInformation.setALARM_OBJECT(mutiDetail+"");
        alarmInformation.setALARM_TYPE_LV1("007");
    }

    public abstract String getAlarmTypeLv2();

    @Override
    protected String getOriginTableName() {
        return "YJJC_QWJJ_SDM_INFO_V";
    }

    @Override
    public boolean isSupport(Collection<YjjcQwjjSdmInfoV> sources) {
        long count = sources.stream().filter(this::predicate).count();
        return count >= thresholdCount;
    }

    protected boolean predicate(YjjcQwjjSdmInfoV yjjcQwjjSdmInfoV) {
        String diagNameInhos = yjjcQwjjSdmInfoV.getDIAG_NAME_INHOS();
        return StringUtils.isNotBlank(diagNameInhos) && diagNameInhos.contains(thresholdString);
    }

    protected Optional<AlarmInformation> queryLastAlarm(@NotNull String alarmTypeLv2, @NotNull String beginTime, @NotNull String endTime) {
        Assert.notNull(alarmTypeLv2, "查询上次数据时事件二级类型不能为空");
        Assert.notNull(beginTime, "查询数据时开始时间不能为空");
        Assert.notNull(endTime, "查询数据时结束时间不能为空");

        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(getOriginTableName());
        alarmInformation.setALARM_TYPE_LV2(alarmTypeLv2);

        StrDateRange strDateRange = new StrDateRange();
        strDateRange.setBegin(beginTime);
        strDateRange.setEnd(endTime);

        PageHelper.startPage(1, 1, false);
        List<AlarmInformation> alarmInformations = alarmInformationMapper.selectByAlarmInformationAndDateRange(alarmInformation, strDateRange);
        if (CollectionUtils.isEmpty(alarmInformations)) {
            return Optional.empty();
        }
        return Optional.ofNullable(alarmInformations.get(0));
    }


    /**
     * 功能描述: <br>
     * 〈聚合生成详情〉
     *
     * @param countDate 统计时间
     * @param address 事件发生地址
     * @param eventSize
     * @param type
     * @return:java.lang.String
     * @Author:yhy
     * @Date: 2019/4/25 14:17
     */
    protected String generateMutiDetail(LocalDateTime countDate, String address, int eventSize, String type) {
        return MessageFormat.format("今日截至：{0}, 在{1}等地共计发生{2}起{3}病例", countDate.format(DateTimeFormatter.ofPattern("HH:mm:ss")), address, eventSize, type);
    }

    /**
     * 功能描述: <br>
     * 〈聚合医院地址〉
     *
     * @param sources
     * @return:java.lang.String
     * @Author:yhy
     * @Date: 2019/4/25 11:14
     */
    protected String mutiAddress(Collection<YjjcQwjjSdmInfoV> sources) {
        // concat the string  YjjcQwjjSdmInfoV's ORG_NAME with '、'
        return sources
                .stream()
                .map(YjjcQwjjSdmInfoV::getORG_NAME)
                .distinct()
                .limit(3)
                .collect(Collectors.joining("、"));
    }
}