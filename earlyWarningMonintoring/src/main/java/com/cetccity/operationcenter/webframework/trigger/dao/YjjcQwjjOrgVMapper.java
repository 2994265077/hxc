package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.YjjcQwjjOrgV;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 卫计_单位信息表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-03-03
 */
@Mapper
public interface YjjcQwjjOrgVMapper {

    @Select("select * FROM YJJC_QWJJ_ORG_V")
    List<YjjcQwjjOrgV> selectList();
}
