/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MessageService
 * Author:   YHY
 * Date:     2019/6/6 11:13
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.alarmcenter.service.impl;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.MessageHistoryMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.MessageHistoryStaffMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.MessageHistory;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.MessageHistoryStaff;
import com.cetccity.operationcenter.webframework.alarmcenter.vo.MessageSend;
import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.common.support.UserRolePermissionSupport;
import com.cetccity.operationcenter.webframework.oa.model.Employee;
import com.cetccity.operationcenter.webframework.oa.service.EmployeeInfoService;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.UserEntity;
import com.cetccity.operationcenter.webframework.web.util.apollo.ApolloPropertiesLoadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.Sms;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
public class MessageService {

    @Autowired
    private UserRolePermissionSupport userRolePermissionSupport;

    @Autowired
    private EmployeeInfoService employeeInfoService;

    @Autowired
    private MessageHistoryMapper messageHistoryMapper;

    @Autowired
    private MessageHistoryStaffMapper messageHistoryStaffMapper;

    public boolean sendMessage(MessageSend messageSend) {
        UserEntity user = userRolePermissionSupport.user();
        Objects.requireNonNull(user, "当前用户未登录");
        String account = user.getAccount();
        // 查询联系人电话号码
        List<Employee> employees = employeeInfoService.queryByUserIds(messageSend.getUserIds());
        if (CollectionUtils.isEmpty(employees)) {
            throw CetcCommonException.defaultException("发送失败，不存在有效联系人");
        }
        List<String> tels = employees.stream().map(Employee::getMobile).distinct().collect(Collectors.toList());
        boolean success = false;
        String enableMessageSend = ApolloPropertiesLoadUtils.readProperties("application", "message.enable");
        if ("true".equals(enableMessageSend)) {
            try {
                success = Sms.sendMsgGroup(tels, messageSend.getContent());
            } catch (Exception e) {
                log.error("短信发送失败", e);
                throw CetcCommonException.defaultException("短信发送失败", e);
            }
        }
        messageSend.setSuccess(success);
        saveHistory(messageSend, account);
        return success;
    }

    public boolean saveHistory(MessageSend messageSend, String account) {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.setContent(messageSend.getContent());
        messageHistory.setCreateUser(account);
        messageHistory.setAlarmId(messageSend.getAlarmId());
        String messageId = messageHistoryMapper.selectId();
        messageHistory.setObjectId(messageId);
        long save = messageHistoryMapper.save(messageHistory);
        HashSet<String> userIds = new HashSet<>(messageSend.getUserIds());
        if (1 == save) {
            userIds.stream()
                    .map(userId -> MessageHistoryStaff.builder()
                            .messageHistoryId(messageId)
                            .staffId(userId)
                            .build())
                    .forEach(messageHistoryStaff -> messageHistoryStaffMapper.save(messageHistoryStaff));

            return true;
        } else {
            log.error("短信历史存失败，内容为：{}", messageHistory);
            return false;
        }
    }

}