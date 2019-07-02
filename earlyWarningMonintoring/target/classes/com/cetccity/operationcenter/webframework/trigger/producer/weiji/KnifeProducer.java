/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: KnifeProducer
 * Author:   YHY
 * Date:     2019/5/22 15:23
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.trigger.producer.weiji;

import com.cetccity.operationcenter.webframework.trigger.core.producer.IdProducer;
import com.cetccity.operationcenter.webframework.trigger.dao.AlarmInformationV1Mapper;
import com.cetccity.operationcenter.webframework.trigger.dao.YjjcQwjjSdmInfoVMapper;
import com.cetccity.operationcenter.webframework.trigger.entity.YjjcQwjjSdmInfoV;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/22
 * @since 1.0.0
 */
public class KnifeProducer implements IdProducer<YjjcQwjjSdmInfoV> {

    @Autowired
    private AlarmInformationV1Mapper alarmInformationMapper;
    @Autowired
    private YjjcQwjjSdmInfoVMapper sourceMapper;

    @Override
    public String previousId() {
        return alarmInformationMapper.selectLastFRowId(getOriginTableName(), getAlarmTypeLv2());
    }

    @Override
    public Collection<YjjcQwjjSdmInfoV> produce() {
        return sourceMapper.queryByMinId(previousId());
    }

    @Override
    public String getOriginTableName() {
        return "YJJC_QWJJ_SDM_INFO_V";
    }


    public List<String> getAlarmTypeLv2() {
        return Arrays.asList("007003");
    }
}