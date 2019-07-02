package com.cetccity.operationcenter.webframework.trigger.entity;

import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.entity
 * @Project: futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2019/5/8 9:28
 * @Updater: huangzezhou
 * @Update_Date: 2019/5/8 9:28
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RiskPointDangerLevel {

    RED("red", "红", 1L, ChronoUnit.MONTHS),
    ORANGE("orange", "橙", 2L, ChronoUnit.MONTHS),
    YELLOW("yellow", "黄" , 3L, ChronoUnit.MONTHS);

    /**
     * 危险源等级编码
     **/
    private String code;
    /**
     * 危险源等级值
     **/
    private String value;
    /**
     * 推送时间间隔
     **/
    private Long pushInterval;
    /**
     * 推送时间间隔单位
     **/
    private ChronoUnit chronoUnit;

    public static RiskPointDangerLevel getByValue(String value) {
        return Arrays.stream(values())
                .filter(riskPointDangerLevel -> riskPointDangerLevel.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> CetcCommonException.defaultException("不存在该等级" + value));
    }
}
