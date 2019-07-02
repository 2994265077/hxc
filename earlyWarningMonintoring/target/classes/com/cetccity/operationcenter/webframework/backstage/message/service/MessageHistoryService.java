/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MessageHistoryService
 * Author:   YHY
 * Date:     2019/6/6 14:54
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.message.service;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.MessageHistoryMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.MessageHistoryStaffMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.MessageHistory;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.MessageHistoryUnion;
import com.cetccity.operationcenter.webframework.backstage.message.qo.MessageHistoryQuery;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.oa.model.Employee;
import com.cetccity.operationcenter.webframework.oa.model.UserInfo;
import com.cetccity.operationcenter.webframework.oa.service.EmployeeInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/6
 * @since 1.0.0
 */
@Service
@Slf4j
public class MessageHistoryService {

    @Autowired
    private MessageHistoryMapper messageHistoryMapper;

    @Autowired
    private EmployeeInfoService employeeInfoService;

    @Autowired
    private MessageHistoryStaffMapper messageHistoryStaffMapper;

    public List<MessageHistory> select() {
        return messageHistoryMapper.select();
    }

    public PageInfo<MessageHistoryUnion> selectUnion(MessageHistoryQuery messageHistoryQuery) {
        PageHelper.startPage(messageHistoryQuery.getPageNum(), messageHistoryQuery.getPageSize());
        if(Objects.isNull(messageHistoryQuery.getSendTimeBegin())) {
            messageHistoryQuery.setSendTimeBegin(LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault()));
        }
        // 处理联系人名称条件
        if (StringUtils.isNotBlank(messageHistoryQuery.getStaffName())) {
            String staffName = messageHistoryQuery.getStaffName();
            List<UserInfo> employees = employeeInfoService.employeeByName(staffName);
            if (CollectionUtils.isNotEmpty(employees)) {
                Set<String> staffIds = employees.stream().map(UserInfo::getId).collect(Collectors.toSet());
                messageHistoryQuery.setStaffIds(staffIds);
            }
        }
        List<MessageHistoryUnion> messageHistoryUnions = messageHistoryMapper.selectUnion(messageHistoryQuery);
        return PageInfo.of(messageHistoryUnions);
    }

    public List<MessageHistory> selectDetailByAlarm(String alarmId) {
        List<MessageHistory> messageHistories = messageHistoryMapper.selectByAlarmId(alarmId);
        for (MessageHistory messageHistory : messageHistories) {
            List<String> userIds = messageHistoryStaffMapper.queryStaffIdsByMessageHistory(messageHistory.getObjectId());
            messageHistory.setAccepter(employeeInfoService.queryByUserIds(userIds));
        }
        return messageHistories;
    }

    public List<NameValueTypeModel<Long>> nums() {
        long countMessageAlarm = messageHistoryMapper.countMessageAlarm();
        NameValueTypeModel alarmNameValue = new NameValueTypeModel();
        alarmNameValue.setName("已短信通知事件数");
        alarmNameValue.setValue(countMessageAlarm);
        long countMessageSend = messageHistoryStaffMapper.countMessageSend();
        NameValueTypeModel sendNameVlaue = new NameValueTypeModel();
        sendNameVlaue.setName("已发送短信数");
        sendNameVlaue.setValue(countMessageSend);
        return Arrays.asList(alarmNameValue, sendNameVlaue);
    }

}