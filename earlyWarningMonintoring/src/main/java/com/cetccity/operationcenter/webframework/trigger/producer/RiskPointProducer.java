/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: RiskPointProducer
 * Author:   YHY
 * Date:     2019/5/21 16:45
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.producer;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.RiskPointMapper;
import com.cetccity.operationcenter.webframework.trigger.core.producer.IdProducer;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.entity.RiskPoint;
import com.cetccity.operationcenter.webframework.trigger.entity.RiskPointDangerLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/21
 * @since 1.0.0
 */
public class RiskPointProducer implements IdProducer<RiskPoint> {

    @Autowired
    private RiskPointMapper riskPointMapper;
    @Autowired
    private AlarmInformationV1Mapper alarmInformationMapper;

    @Override
    public String previousId() {
        return alarmInformationMapper.selectLastFRowId(getOriginTableName(), null);
    }

    @Override
    public Collection<RiskPoint> produce() {
        String previousId =previousId();
        previousId = StringUtils.isNoneBlank(previousId) ? previousId : "0";
        return riskPointMapper.selectByDangerLevelAndMinId(previousId, Arrays.asList(RiskPointDangerLevel.values()));
    }

    @Override
    public String getOriginTableName() {
        return "RISK_POINT";
    }
}