package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.EnterpriseMigration;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业外迁 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-01-16
 */
@Mapper
public interface EnterpriseMigration2Mapper  {

    List<EnterpriseMigration> querySourceData(Map map);
}
