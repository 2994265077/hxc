package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.urbansign.dao.entity.QAJJ_REPACCIDENT_V;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface QAJJ_REPACCIDENT_V_SignMapper {

    //今日重大安全事故
    List<QAJJ_REPACCIDENT_V> safetyAccidentCount(@Param("time") String time);

    Integer selectCountOfToday(@Param("time")String time);
}
