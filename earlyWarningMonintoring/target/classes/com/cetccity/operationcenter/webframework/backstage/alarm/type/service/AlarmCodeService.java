/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmCodeService
 * Author:   YHY
 * Date:     2019/5/16 11:07
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.alarm.type.service;

import com.cetccity.operationcenter.webframework.backstage.alarm.type.dao.AlarmCodeMapper;
import com.cetccity.operationcenter.webframework.backstage.alarm.type.entity.AlarmCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈预警等级查询〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
@Service
public class AlarmCodeService {
    @Autowired
    private AlarmCodeMapper alarmCodeMapper;

    /**
     * 功能描述: <br>
     * 〈查询所有预警等级〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.backstage.alarm.type.entity.AlarmCode>
     * @Author:yhy
     * @Date: 2019/5/16 11:09
     */
    public List<AlarmCode> queryAll() {
        return alarmCodeMapper.queryAll();
    }

    /**
     * 功能描述: <br>
     * 〈查询所有一级预警等级〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.backstage.alarm.type.entity.AlarmCode>
     * @Author:dongxin
     * @Date: 2019/5/16 11:09
     */
    public List<AlarmCode> queryLv1s() {
        return alarmCodeMapper.queryLv1s();
    }

}