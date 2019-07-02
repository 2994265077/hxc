package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.web.model.citySign.MACRO_ECONOMY;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MACRO_ECONOMYMapper {

    List<MACRO_ECONOMY> selectObjByTime(MACRO_ECONOMY mACRO_ECONOMY);
}
