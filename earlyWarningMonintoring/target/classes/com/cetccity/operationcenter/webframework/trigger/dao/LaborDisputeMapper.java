package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.LaborDispute;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 劳资纠纷 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-01-16
 */
public interface LaborDisputeMapper {

    List<LaborDispute> querySourceData(Map map);
}
