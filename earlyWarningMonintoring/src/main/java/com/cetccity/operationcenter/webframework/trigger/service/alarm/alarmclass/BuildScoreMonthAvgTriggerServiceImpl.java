/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: BuildScoreTriggerServiceImpl
 * Author:   YHY
 * Date:     2019/4/3 10:17
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.service.alarm.alarmclass;

import com.cetccity.operationcenter.webframework.trigger.dao.BuildScoreMonthAvgMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/4/3
 * @since 1.0.0
 * @Description: //建筑风险评分月平均值触发器
 */
@Service
@Slf4j
public class BuildScoreMonthAvgTriggerServiceImpl{

    @Autowired
    private BuildScoreMonthAvgMapper mapper;

    public void triggerAlarm() {
        YearMonth now = YearMonth.now();
        String yearMonth = now.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        mapper.avgMonthScore(yearMonth);
        mapper.deleteByYearMonth(yearMonth);
    }
}