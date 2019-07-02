/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: Enums
 * Author:   YHY
 * Date:     2019/5/16 16:05
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.common.alarm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
public class Enums {

    @Getter
    @AllArgsConstructor
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum SendStateEnum {
        SENT  ("sent",   "已推送", "1"),
        UNSENT("unsent", "未推送", "0");
        private String code;
        private String name;
        private String value;
        public static String getNameByValue(String value) {
            Optional<SendStateEnum> any = Arrays.stream(values())
                    .filter(sendStateEnum -> sendStateEnum.getValue().equals(value))
                    .findAny();
            if (any.isPresent()) {
                return any.get().getName();
            }
            return "未知状态";
        }
    }

    @Getter
    @AllArgsConstructor
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum DisposalStateEnum {
        NOT_DISPOSAL("not_disposal", "未处置", "0" ),
        ACCEPTED    ("accepted",     "已受理", "1" ),
        PROCESSED   ("processed",    "已办结", "2" ),
        EVALUATED   ("evaluated",    "已评价", "3" ),
        ARCHIVED    ("archived",     "已归档", "4" ),
        HANGED_UP   ("hanged_up",    "已挂起", "50"),
        CANCELLED   ("cancelled",    "已作废", "80");
        private String code;
        private String name;
        private String value;
        public static String getNameByValue(String value) {
            Optional<DisposalStateEnum> any = Arrays.stream(values())
                    .filter(disposalStateEnum -> disposalStateEnum.getValue().equals(value))
                    .findAny();
            if (any.isPresent()) {
                return any.get().getName();
            }
            return "未知状态";
        }
    }

    @Getter
    @AllArgsConstructor
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum AlarmStateEnum {
        ALARMING("alarming",   "正在报警", "1"),
        CANCEL  ("over",       "已结束",   "0");
        private String code;
        private String name;
        private String value;

        public static String getNameByValue(String value) {
            Optional<AlarmStateEnum> any = Arrays.stream(values())
                    .filter(alarmStateEnum -> alarmStateEnum.getValue().equals(value))
                    .findAny();
            if (any.isPresent()) {
                return any.get().getName();
            }
            return "未知状态";
        }
    }

}