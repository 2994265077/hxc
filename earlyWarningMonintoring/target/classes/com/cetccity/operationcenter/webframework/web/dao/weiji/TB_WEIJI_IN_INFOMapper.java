package com.cetccity.operationcenter.webframework.web.dao.weiji;

import com.cetccity.operationcenter.webframework.web.model.incident.weiji.TB_WEIJI_IN_INFO;
import com.cetccity.operationcenter.webframework.web.model.incident.weiji.TB_WEIJI_IN_INFOExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TB_WEIJI_IN_INFOMapper {
    long countByExample(TB_WEIJI_IN_INFOExample example);

    int deleteByExample(TB_WEIJI_IN_INFOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TB_WEIJI_IN_INFO record);

    int insertSelective(TB_WEIJI_IN_INFO record);

    List<TB_WEIJI_IN_INFO> selectByExample(TB_WEIJI_IN_INFOExample example);

    TB_WEIJI_IN_INFO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TB_WEIJI_IN_INFO record, @Param("example") TB_WEIJI_IN_INFOExample example);

    int updateByExample(@Param("record") TB_WEIJI_IN_INFO record, @Param("example") TB_WEIJI_IN_INFOExample example);

    int updateByPrimaryKeySelective(TB_WEIJI_IN_INFO record);

    int updateByPrimaryKey(TB_WEIJI_IN_INFO record);
}