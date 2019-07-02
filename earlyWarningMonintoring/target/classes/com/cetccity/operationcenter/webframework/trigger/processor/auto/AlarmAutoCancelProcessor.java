/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmAutoCancelProcessor
 * Author:   YHY
 * Date:     2019/5/15 15:12
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.processor.auto;

import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.common.Enums;
import com.cetccity.operationcenter.webframework.trigger.core.processor.AbstractAlarmAutoProcessor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/15
 * @since 1.0.0
 */
public class AlarmAutoCancelProcessor extends AbstractAlarmAutoProcessor {


    public AlarmAutoCancelProcessor(Enums.AlarmAutoProcessTypeEnum typeEnum) {
        this.processType = typeEnum;
    }

    public Optional<AlarmInformation> createProcessBean(AlarmInformation oldAlarm) {
        AlarmInformation processAlarmBean = new AlarmInformation();
        processAlarmBean.setOBJECT_ID(oldAlarm.getOBJECT_ID());
        processAlarmBean.setALARM_STATE(0);
        processAlarmBean.setCANCEL_TIME(new Date());
        return Optional.ofNullable(processAlarmBean);
    }

    public List<AlarmInformation> sources() {
        List<String> alarmTypeLv2s = Arrays.stream(Enums.AlarmAutoProcessEnum.values())
                .filter(alarmAutoProcessEnum -> alarmAutoProcessEnum.getAutoProcessType().equals(processType))
                .map(Enums.AlarmAutoProcessEnum::getAlarmTypeLv2)
                .collect(Collectors.toList());
        return alarmInformationMapper.findAlarmingByAlarmTypeLv2s(alarmTypeLv2s, null);
    }

}