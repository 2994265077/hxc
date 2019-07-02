package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.IOT_EVENT;
import com.cetccity.operationcenter.webframework.web.model.iot.IotEventModel;
import com.cetccity.operationcenter.webframework.web.model.iot.IotEventModelExample;
import com.cetccity.operationcenter.webframework.web.model.iot.IotEventModelWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface IotEventMapper extends BaseMapper<IOT_EVENT>{

    List<IOT_EVENT> getIOT_EVENT_list(@Param("EVENT_CODE")String EVENT_CODE);

    List<IOT_EVENT> getIOT_EVENT(IOT_EVENT iOT_EVENT);

    List<IOT_EVENT> pageInfoIOT_EVENT(IOT_EVENT iOT_EVENT);

    long countByExample(IotEventModelExample example);

    int deleteByExample(IotEventModelExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(IotEventModelWithBLOBs record);

    int insertSelective(IotEventModelWithBLOBs record);

    List<IotEventModelWithBLOBs> selectByExampleWithBLOBs(IotEventModelExample example);

    List<IotEventModel> selectByExample(IotEventModelExample example);

    IotEventModelWithBLOBs selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") IotEventModelWithBLOBs record, @Param("example") IotEventModelExample example);

    int updateByExampleWithBLOBs(@Param("record") IotEventModelWithBLOBs record, @Param("example") IotEventModelExample example);

    int updateByExample(@Param("record") IotEventModel record, @Param("example") IotEventModelExample example);

    int updateByPrimaryKeySelective(IotEventModelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(IotEventModelWithBLOBs record);

    int updateByPrimaryKey(IotEventModel record);
}
