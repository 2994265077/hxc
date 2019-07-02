package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.WeeklyXinfangEvent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-01-03
 */
@Mapper
public interface WeeklyXinfangEventMapper {

    List<WeeklyXinfangEvent> querySourceData(Map map);
}
