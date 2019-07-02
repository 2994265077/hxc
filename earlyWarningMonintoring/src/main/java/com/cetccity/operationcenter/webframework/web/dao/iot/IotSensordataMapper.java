package com.cetccity.operationcenter.webframework.web.dao.iot;

import com.cetccity.operationcenter.webframework.web.model.iot.IotSensordataModel;
import com.cetccity.operationcenter.webframework.web.model.iot.IotSensordataModelExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IotSensordataMapper {
    long countByExample(IotSensordataModelExample example);

    int deleteByExample(IotSensordataModelExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(IotSensordataModel record);

    int insertSelective(IotSensordataModel record);

    List<IotSensordataModel> selectByExample(IotSensordataModelExample example);

    IotSensordataModel selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") IotSensordataModel record, @Param("example") IotSensordataModelExample example);

    int updateByExample(@Param("record") IotSensordataModel record, @Param("example") IotSensordataModelExample example);

    int updateByPrimaryKeySelective(IotSensordataModel record);

    int updateByPrimaryKey(IotSensordataModel record);
}