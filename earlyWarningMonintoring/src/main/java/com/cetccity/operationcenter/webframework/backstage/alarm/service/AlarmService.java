/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: AlarmService
 * Author:   YHY
 * Date:     2019/5/16 11:38
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.alarm.service;

import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.backstage.alarm.dao.AlarmVoMapper;
import com.cetccity.operationcenter.webframework.backstage.alarm.domain.AlarmVo;
import com.cetccity.operationcenter.webframework.backstage.alarm.qo.AlarmQueryObject;
import com.cetccity.operationcenter.webframework.common.alarm.Enums;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 〈一句话功能简述〉<br> 
 * 〈预警管理〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
@Service
public class AlarmService {

    @Autowired
    private AlarmVoMapper alarmVoMapper;

    public PageInfo<AlarmVo> query(AlarmQueryObject alarmQueryVo) {
        Page<AlarmVo> page = PageHelper.startPage(alarmQueryVo.getPageNum(), alarmQueryVo.getPageSize());
        alarmVoMapper.query(alarmQueryVo);
        page.getResult().stream().forEach(this::fillAlarmVo);
        return PageInfo.of(page);
    }

    public AlarmVo queryById(String id) {
        AlarmVo alarmVo = alarmVoMapper.queryById(id);
        fillAlarmVo(alarmVo);
        return alarmVo;
    }

    private void fillAlarmVo(AlarmVo alarmVo) {
        alarmVo.setSendStateName(Enums.SendStateEnum.getNameByValue(alarmVo.getSendState()));
        alarmVo.setAlarmStateName(Enums.AlarmStateEnum.getNameByValue(alarmVo.getAlarmState()));
        alarmVo.setDisposalStateName(Enums.DisposalStateEnum.getNameByValue(alarmVo.getDisposalState()));
    }

}