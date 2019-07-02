package com.cetccity.operationcenter.webframework.web.dao.weiji;

import com.cetccity.operationcenter.webframework.web.model.incident.weiji.TB_WEIJI_OUT_INFO;
import com.cetccity.operationcenter.webframework.web.model.incident.weiji.TB_WEIJI_OUT_INFOExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TB_WEIJI_OUT_INFOMapper {
    long countByExample(TB_WEIJI_OUT_INFOExample example);

    int deleteByExample(TB_WEIJI_OUT_INFOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TB_WEIJI_OUT_INFO record);

    int insertSelective(TB_WEIJI_OUT_INFO record);

    List<TB_WEIJI_OUT_INFO> selectByExample(TB_WEIJI_OUT_INFOExample example);

    TB_WEIJI_OUT_INFO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TB_WEIJI_OUT_INFO record, @Param("example") TB_WEIJI_OUT_INFOExample example);

    int updateByExample(@Param("record") TB_WEIJI_OUT_INFO record, @Param("example") TB_WEIJI_OUT_INFOExample example);

    int updateByPrimaryKeySelective(TB_WEIJI_OUT_INFO record);

    int updateByPrimaryKey(TB_WEIJI_OUT_INFO record);
}