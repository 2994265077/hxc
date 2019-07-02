package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QHSJ_ENTERPRISE_FACTOR_CODE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface QHSJ_ENTERPRISE_FACTOR_CODEMapper {

    List<QHSJ_ENTERPRISE_FACTOR_CODE> getWaterFactorCode();

    List<QHSJ_ENTERPRISE_FACTOR_CODE> getWaterFactorCodeToName(@Param("FACTOR_CODE") String FACTOR_CODE);
}
