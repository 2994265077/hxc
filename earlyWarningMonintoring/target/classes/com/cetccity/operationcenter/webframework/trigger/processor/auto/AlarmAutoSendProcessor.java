/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmAutoSendProcessor
 * Author:   YHY
 * Date:     2019/5/15 15:20
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.processor.auto;

import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.common.Enums;
import com.cetccity.operationcenter.webframework.trigger.core.processor.AbstractAlarmAutoProcessor;

import java.util.Arrays;
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
public class AlarmAutoSendProcessor extends AbstractAlarmAutoProcessor {

    public AlarmAutoSendProcessor(Enums.AlarmAutoProcessTypeEnum typeEnum) {
        this.processType = typeEnum;
    }

    @Override
    public Optional<AlarmInformation> createProcessBean(AlarmInformation oldAlarm) {
        AlarmInformation sendAlarmBean = new AlarmInformation();
        sendAlarmBean.setOBJECT_ID(oldAlarm.getOBJECT_ID());
        sendAlarmBean.setSEND_STATE(0);
        return Optional.ofNullable(sendAlarmBean);
    }

    public List<AlarmInformation> sources() {
        List<String> alarmTypeLv2s = Arrays.stream(Enums.AlarmAutoProcessEnum.values())
                .filter(alarmAutoProcessEnum -> alarmAutoProcessEnum.getAutoProcessType().equals(processType))
                .map(Enums.AlarmAutoProcessEnum::getAlarmTypeLv2)
                .collect(Collectors.toList());
        return alarmInformationMapper.findAlarmingByAlarmTypeLv2s(alarmTypeLv2s, "1");
    }
}