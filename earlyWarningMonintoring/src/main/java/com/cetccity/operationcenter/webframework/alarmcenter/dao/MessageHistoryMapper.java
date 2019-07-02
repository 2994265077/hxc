package com.cetccity.operationcenter.webframework.alarmcenter.dao;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.MessageHistory;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.MessageHistoryUnion;
import com.cetccity.operationcenter.webframework.backstage.message.qo.MessageHistoryQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 短信发送历史管理
 */
@Mapper
public interface MessageHistoryMapper {

    long save(MessageHistory messageHistory);

    String selectId();

    List<MessageHistory> select();

    long countMessageAlarm();

    List<MessageHistoryUnion> selectUnion(MessageHistoryQuery messageHistoryQuery);

    List<MessageHistory> selectByAlarmId(@Param("alarm_id") String alarmId);

}
