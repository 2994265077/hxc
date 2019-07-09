/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: KnifeConverter
 * Author:   YHY
 * Date:     2019/5/22 11:52
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.converter;

import com.cetccity.operationcenter.webframework.trigger.core.converter.AbstractSingleConverter;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.entity.StrDateRange;
import com.cetccity.operationcenter.webframework.trigger.entity.YjjcQwjjSdmInfoV;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈公共卫生-刀枪伤预警 预警转换器〉
 *
 * @author yhy
 * @create 2019/5/22
 * @since 1.0.0
 */
public class KnifeConverter extends AbstractSingleConverter<YjjcQwjjSdmInfoV> {


    @Autowired
    private AlarmInformationV1Mapper alarmInformationMapper;

    private static final DateTimeFormatter DEFAUL_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    protected String getAlarmLevel(YjjcQwjjSdmInfoV yjjcQwjjSdmInfoV) {
        return "三级-黄";
    }

    @Override
    protected Optional<AlarmInformation> recentAlarm(YjjcQwjjSdmInfoV yjjcQwjjSdmInfoV) {
        Double fRowId = yjjcQwjjSdmInfoV.getOBJECT_ID();
        LocalDateTime beginTime = LocalDate.now().atStartOfDay();
        LocalDateTime endTime = beginTime.plusDays(1);
        AlarmInformation alarmInformation = new AlarmInformation();
        alarmInformation.setORIGIN_TABLE_NAME(getOriginTableName());
        alarmInformation.setF_ROW_ID(fRowId);
        alarmInformation.setALARM_TYPE_LV2("007003");

        StrDateRange strDateRange = new StrDateRange();
        strDateRange.setBegin(beginTime.format(DEFAUL_TIME_FORMATTER));
        strDateRange.setEnd(endTime.format(DEFAUL_TIME_FORMATTER));

        PageHelper.startPage(1, 1, false);
        List<AlarmInformation> alarmInformations = alarmInformationMapper.selectByAlarmInformationAndDateRange(alarmInformation, strDateRange);
        if (CollectionUtils.isNotEmpty(alarmInformations)) {
            return Optional.ofNullable(alarmInformations.get(0));
        }
        return Optional.empty();
    }

    @Override
    protected AlarmInformation updateAlarmBean(AlarmInformation lastAlarm, YjjcQwjjSdmInfoV yjjcQwjjSdmInfoV) {
        // 刀枪伤预警不会取消 也不需要预警
        return null;
    }

    @Override
    protected AlarmInformation fillAlarmInformation(AlarmInformation alarmInformation) {
        alarmInformation = super.fillAlarmInformation(alarmInformation);
        alarmInformation.setSEND_STATE(1);
        alarmInformation.setALARM_LEVEL("特殊病情");
        alarmInformation.setALARM_TYPE_LV1("007");
        alarmInformation.setALARM_TYPE_LV2("007003");
        alarmInformation.setCHANNEL("卫计局");
        return alarmInformation;
    }

    @Override
    protected AlarmInformation newAlarmBean(YjjcQwjjSdmInfoV source) {
        AlarmInformation alarmInformation = super.newAlarmBean(source);
        alarmInformation.setALARM_OBJECT(source.getORG_NAME()+"");    //主体名
        alarmInformation.setF_OBJECT_ROW_ID(source.getOBJECT_ID());     //主体主键,事件没有主体，默认为-1
        alarmInformation.setF_ROW_ID(source.getOBJECT_ID());
        alarmInformation.setADDRESS(source.getORG_NAME()+"");
        alarmInformation.setCONTENTS(source.getORG_NAME() + "发现刀、枪伤患者， 患者" + source.getDIAG_NAME_INHOS());
        return alarmInformation;
    }

    @Override
    protected String getOriginTableName() {
        return "YJJC_QWJJ_SDM_INFO_V";
    }

    @Override
    protected boolean isExceededThreshold(YjjcQwjjSdmInfoV yjjcQwjjSdmInfoV) {
        // isSupport 已经完全过滤了
        return true;
    }

    @Override
    protected boolean shouldUpdate(Optional<AlarmInformation> recentAlarmOption) {
        // 不需要更新
        return false;
    }

    @Override
    public boolean isSupport(YjjcQwjjSdmInfoV yjjcQwjjSdmInfoV) {
        String diagNameInhos = yjjcQwjjSdmInfoV.getDIAG_NAME_INHOS();
        return Objects.nonNull(diagNameInhos) && StringUtils.containsAny(diagNameInhos, "刀", "枪");
    }
}