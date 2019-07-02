package com.cetccity.operationcenter.webframework.trigger.core.processor;

import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInformation;
import com.cetccity.operationcenter.webframework.trigger.common.Enums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.service
 * @Project: futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2019/5/15 16:00
 * @Updater: huangzezhou
 * @Update_Date: 2019/5/15 16:00
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Slf4j
public abstract class AbstractAlarmAutoProcessor extends AbstractPersistableProcessor {
    @Autowired
    protected AlarmInformationV1Mapper alarmInformationMapper;

    protected Enums.AlarmAutoProcessTypeEnum processType;

    public void process() {
        List<AlarmInformation> shouldAutoProcessAlarms = sources();
        List<AlarmInformation> autoProcessAlarms = genericProcessAlarmBeans(shouldAutoProcessAlarms);
        doPersistence(autoProcessAlarms);
    }

    private List<AlarmInformation> genericProcessAlarmBeans(List<AlarmInformation> alarmInformations) {
        return alarmInformations.stream()
                .map(this::genericProcessAlarmBean)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }


    protected Optional<AlarmInformation> genericProcessAlarmBean(AlarmInformation alarmInformation) {
        try {
                Optional<Enums.AlarmAutoProcessEnum> autoProcessEnumOptional = Enums.AlarmAutoProcessEnum.getAutoCancelByType(alarmInformation.getALARM_TYPE_LV2(), processType);

                // 已配置此事件类型的自动取消
                if (autoProcessEnumOptional.isPresent()) {
                    Enums.AlarmAutoProcessEnum autoProcessEnum = autoProcessEnumOptional.get();

                    Date preProcessTime;
                    // 特殊情况，此事件需要循环定时推送
                    if (Enums.AlarmAutoProcessTypeEnum.AUTO_LOOP_SEND.equals(autoProcessEnum.getAutoProcessType())) {
                        preProcessTime = alarmInformation.getYJJC_UPDATE_TIME();
                    } else {
                        preProcessTime = alarmInformation.getRELEASE_TIME();
                    }

                    LocalDateTime preProcessDateTime = LocalDateTime.ofInstant(preProcessTime.toInstant(), ZoneId.systemDefault());
                    // 计算时间间隔
                    long duration = autoProcessEnum.getChronoUnit().between(preProcessDateTime, LocalDateTime.now());
                    // 达到自动取消时间间隔
                    if (duration >= autoProcessEnum.getAutoCancelInterval()) {
                        return createProcessBean(alarmInformation);
                    }
                }
        } catch (Throwable throwable) {
            log.error("自动发送或取消预警失败", throwable);
        }
        return Optional.empty();
    }

    public abstract Optional<AlarmInformation> createProcessBean(AlarmInformation oldAlarm);

    public abstract List<AlarmInformation> sources();

}
