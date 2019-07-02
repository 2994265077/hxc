package com.cetccity.operationcenter.webframework.web.dao;

import java.util.List;
import com.cetccity.operationcenter.webframework.web.model.incident.DisposalProcess;
import com.cetccity.operationcenter.webframework.web.model.incident.DisposalProcessExample;
import org.apache.ibatis.annotations.Param;

public interface DisposalProcessMapper {
    long countByExample(DisposalProcessExample example);

    int deleteByExample(DisposalProcessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DisposalProcess record);

    int insertSelective(DisposalProcess record);

    List<DisposalProcess> selectByExample(DisposalProcessExample example);

    DisposalProcess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DisposalProcess record, @Param("example") DisposalProcessExample example);

    int updateByExample(@Param("record") DisposalProcess record, @Param("example") DisposalProcessExample example);

    int updateByPrimaryKeySelective(DisposalProcess record);

    int updateByPrimaryKey(DisposalProcess record);
}