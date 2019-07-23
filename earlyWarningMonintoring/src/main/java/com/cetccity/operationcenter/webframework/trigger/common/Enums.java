package com.cetccity.operationcenter.webframework.trigger.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.entity
 * @Project: futian-EarlyWarningMonitoring
 * @Creator: yhy
 * @Create_Date: 2019/5/14 16:48
 * @Updater: yhy
 * @Description:
 **/
public class Enums {

    @Getter
    @AllArgsConstructor
    public enum IotTypeEnum {

        WATER_LOGGING("积涝点报警", "001", "001003", new HashSet<>(Arrays.asList("00260001"))),
        GEOLOGY_ALARM("地质灾害报警", "001", "001002",
                              new HashSet<>(
                                      Arrays.asList(
                                              "00233001", "00233002", "00233003", "00233004", "00233005", "00233006", "00233007",
                                              "00233008", "00233009", "00233010", "00233011", "00233012", "00233013", "00233014",
                                              "00233015", "00233016", "00233017", "00233018", "00233019", ""
                                      )
                              )
        ),
        DANGER_AIR("危化品气体报警", "002", "002003", new HashSet<>(Arrays.asList("00213001"))),
        SMOKE_DETECTOR("烟感报警", "003", "003001", new HashSet<>(Arrays.asList("00031001","00033001","00033002","00033003"))),
        ELECTRICAL_FIRE("电器火灾报警", "003", "003002", new HashSet<>(Arrays.asList("00240006","00241001","00241002","00241003","00241004","00241005"))),
        HYDRANT_PRESSURE("消防栓水压报警", "003", "003003", new HashSet<>(Arrays.asList("00251001")));

        private String typeName;
        private String alarmTypeLv1;
        private String alarmTypeLv2;
        private Set<String> ObjectCodes;


    }

    @Getter
    @AllArgsConstructor
    public enum AlarmAutoProcessEnum {

        WATER_LOGGING_AUTO_CANCEL       ("积涝点报警自动取消 ",       AlarmAutoProcessTypeEnum.AUTO_CANCEL, "IOT_EVENT", "001", "001003", 3L, ChronoUnit.DAYS),
        DANGER_AIR_AUTO_CANCEL          ("危化品气体报警自动取消",     AlarmAutoProcessTypeEnum.AUTO_CANCEL, "IOT_EVENT", "002", "002003", 30L, ChronoUnit.DAYS),
        SMOKE_DETECTOR_AUTO_CANCEL      ("烟感报警自动取消",          AlarmAutoProcessTypeEnum.AUTO_CANCEL, "IOT_EVENT", "003", "003001", 3L, ChronoUnit.DAYS),
        HYDRANT_PRESSURE_AUTO_CANCEL    ("消防栓水压报警自动取消",     AlarmAutoProcessTypeEnum.AUTO_CANCEL, "IOT_EVENT", "003", "003003", 15L, ChronoUnit.DAYS),
        WEATHER_AUTO_CANCEL             ("气象预警自动取消",          AlarmAutoProcessTypeEnum.AUTO_CANCEL, "WEATHER_ALARM", "001", "001001", 2L, ChronoUnit.DAYS),
        LABOR_DISPUTE_AUTO_CANCEL       ("劳资纠纷报警自动取消",       AlarmAutoProcessTypeEnum.AUTO_CANCEL, "LABOR_DISPUTE", "004", "004001", 7L, ChronoUnit.DAYS),
        ENTERPRISE_MIGRATION_AUTO_CANCEL("企业外迁报警自动取消",      AlarmAutoProcessTypeEnum.AUTO_CANCEL, "ENTERPRISE_MIGRATION", "004", "004002", 30L, ChronoUnit.DAYS),
        EMERGENCY_CASE_AUTO_CANCEL      ("应急突发事件报警自动取消",   AlarmAutoProcessTypeEnum.AUTO_CANCEL, "WEEKLY_EMERGENCY_CASE", "006", "006001", 7L, ChronoUnit.DAYS),
        XINFANG_EVENT_AUTO_CANCEL       ("信访事件报警自动取消",      AlarmAutoProcessTypeEnum.AUTO_CANCEL, "WEEKLY_XINFANG_EVENT", "006", "006002", 7L, ChronoUnit.DAYS),
        KNIFE_AUTO_CANCEL               ("刀枪伤报警自动取消",        AlarmAutoProcessTypeEnum.AUTO_CANCEL, "YJJC_QWJJ_SDM_INFO_V", "007", "007003", 7L, ChronoUnit.DAYS),
        FEVER_AUTO_CANCEL               ("发热报警自动取消",          AlarmAutoProcessTypeEnum.AUTO_CANCEL, "YJJC_QWJJ_SDM_INFO_V", "007", "007001", 7L, ChronoUnit.DAYS),
        DIARRHEA_AUTO_CANCEL            ("腹泻报警自动取消",          AlarmAutoProcessTypeEnum.AUTO_CANCEL, "YJJC_QWJJ_SDM_INFO_V", "007", "007002", 7L, ChronoUnit.DAYS),
        INFLUENZA_AUTO_CANCEL           ("流感报警自动取消",          AlarmAutoProcessTypeEnum.AUTO_CANCEL, "YJJC_QWJJ_SDM_INFO_V", "007", "007004", 7L, ChronoUnit.DAYS),

        GEOLOGY_ALARM_AUTO_SEND         ("地质灾害报警自动推送",      AlarmAutoProcessTypeEnum.AUTO_SEND, "IOT_EVENT", "001", "001002", 15L, ChronoUnit.DAYS),
        DANGER_AIR_AUTO_SEND            ("危化品气体报警自动推送",    AlarmAutoProcessTypeEnum.AUTO_SEND, "IOT_EVENT", "001", "001002", 15L, ChronoUnit.DAYS),
        SMOKE_DETECTOR_AUTO_SEND        ("烟感报警报警自动推送",      AlarmAutoProcessTypeEnum.AUTO_SEND, "IOT_EVENT", "001", "001002", 15L, ChronoUnit.DAYS),
        HYDRANT_PRESSURE_AUTO_SEND      ("消防栓水压报警自动推送",    AlarmAutoProcessTypeEnum.AUTO_SEND, "IOT_EVENT", "001", "001002", 15L, ChronoUnit.DAYS),
        ELECTRICAL_FIRE_AUTO_SEND       ("电器火灾报警自动推送",      AlarmAutoProcessTypeEnum.AUTO_SEND, "IOT_EVENT", "003", "003002",  15L, ChronoUnit.DAYS);



        private String name;
        private AlarmAutoProcessTypeEnum autoProcessType;
        private String originTableName;
        private String alarmTypeLv1;
        private String alarmTypeLv2;
        private Long autoCancelInterval;
        private ChronoUnit chronoUnit;

        public static Optional<AlarmAutoProcessEnum> getAutoCancelByType(String alarmType, AlarmAutoProcessTypeEnum autoProcessType) {
            return Arrays.stream(values())
                    .filter(alarmAutoProcessEnum -> alarmAutoProcessEnum.getAutoProcessType().equals(autoProcessType))
                    .filter(alarmAutoProcessEnum ->
                            alarmAutoProcessEnum.getAlarmTypeLv2().equals(alarmType) || alarmAutoProcessEnum.getAlarmTypeLv1().equals(alarmType)
                    )
                    .findAny();
        }

    }

    @Getter
    @AllArgsConstructor
    public enum AlarmAutoProcessTypeEnum {
        AUTO_SEND("定时自动推送", "auto_send"),
        AUTO_LOOP_SEND("循环自动推送", "auto_loop_send"),  // 暂时不好处理
        AUTO_CANCEL("定时自动取消", "auto_cancel");
        private String name;
        private String code;
    }

    /**
     * 功能描述: <br>
     *      预警阈值                    预警等级
     *  *  data_value < 15              蓝 IV
     *  *  15 ≤ data_value＜30          黄 III
     *  *  30 ≤ data_value＜40          橙 II
     *  *  data_value > 40              红 I
     */
    @Getter
    @AllArgsConstructor
    public enum WatterLoggingLevel {
        CANCEL("取消预警", "cancel", 0, 0),
        BLUE("四级-蓝", "IV", 14, 1),
        YELLOW("四级-蓝", "III", 29, 15),
        ORANGE("四级-蓝", "II", 39, 30),
        RED("三级-黄", "I", Integer.MAX_VALUE, 40);

        /**
         *  预警等级名称
         */
        private String name;
        /**
         *  预警等级编码
         */
        private String code;
        /**
         *  预警等级上边界
         */
        private Integer highBound;
        /**
         *  预警等级下边界
         */
        private Integer lowBound;

        public static WatterLoggingLevel getLevelByDataValue(Integer dataValue) {
            Assert.isTrue(Objects.nonNull(dataValue) && dataValue > 0, "状态值必须为正整数");
            return Arrays.stream(values())
                    .filter(watterLoggingLevel -> watterLoggingLevel.lowBound <= dataValue && watterLoggingLevel.highBound >= dataValue)
                    .findAny().get();
        }
    }


}
