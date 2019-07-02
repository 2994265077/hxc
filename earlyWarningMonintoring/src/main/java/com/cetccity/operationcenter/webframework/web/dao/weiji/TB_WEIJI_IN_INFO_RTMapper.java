package com.cetccity.operationcenter.webframework.web.dao.weiji;

import com.cetccity.operationcenter.webframework.web.model.incident.weiji.TB_WEIJI_IN_INFO_RT;
import com.cetccity.operationcenter.webframework.web.model.incident.weiji.TB_WEIJI_IN_INFO_RTExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TB_WEIJI_IN_INFO_RTMapper {
    long countByExample(TB_WEIJI_IN_INFO_RTExample example);

    int deleteByExample(TB_WEIJI_IN_INFO_RTExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TB_WEIJI_IN_INFO_RT record);

    int insertSelective(TB_WEIJI_IN_INFO_RT record);

    List<TB_WEIJI_IN_INFO_RT> selectByExample(TB_WEIJI_IN_INFO_RTExample example);

    TB_WEIJI_IN_INFO_RT selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TB_WEIJI_IN_INFO_RT record, @Param("example") TB_WEIJI_IN_INFO_RTExample example);

    int updateByExample(@Param("record") TB_WEIJI_IN_INFO_RT record, @Param("example") TB_WEIJI_IN_INFO_RTExample example);

    int updateByPrimaryKeySelective(TB_WEIJI_IN_INFO_RT record);

    int updateByPrimaryKey(TB_WEIJI_IN_INFO_RT record);
}