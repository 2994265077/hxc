package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.AlarmInfoCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-01-16
 */
@Mapper
public interface AlarmInfoCodeMapper {

    List<AlarmInfoCode> selectList();
}
