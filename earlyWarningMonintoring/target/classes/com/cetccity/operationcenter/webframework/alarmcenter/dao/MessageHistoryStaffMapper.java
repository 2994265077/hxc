package com.cetccity.operationcenter.webframework.alarmcenter.dao;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.MessageHistoryStaff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
@Mapper
public interface MessageHistoryStaffMapper {
    long save(MessageHistoryStaff messageHistoryStaff);

    long countMessageSend();

    List<String> queryStaffIdsByMessageHistory(@Param("message_history_id") String messageHistoryId);

}
