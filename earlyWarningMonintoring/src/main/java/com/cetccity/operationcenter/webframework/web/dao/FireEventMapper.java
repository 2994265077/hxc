package com.cetccity.operationcenter.webframework.web.dao;

import java.util.List;
import com.cetccity.operationcenter.webframework.web.model.incident.FireEvent;
import com.cetccity.operationcenter.webframework.web.model.incident.FireEventExample;
import org.apache.ibatis.annotations.Param;

public interface FireEventMapper {
    long countByExample(FireEventExample example);

    int deleteByExample(FireEventExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FireEvent record);

    int insertSelective(FireEvent record);

    List<FireEvent> selectByExample(FireEventExample example);

    FireEvent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FireEvent record, @Param("example") FireEventExample example);

    int updateByExample(@Param("record") FireEvent record, @Param("example") FireEventExample example);

    int updateByPrimaryKeySelective(FireEvent record);

    int updateByPrimaryKey(FireEvent record);
}