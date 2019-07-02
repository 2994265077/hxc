package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.urbansign.dao.entity.P2P_BUSINESS_GLOBAL;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface P2P_BUSINESS_GLOBALMapper {

    List<P2P_BUSINESS_GLOBAL> getPlatDetail(@Param("id") String id);

    int selectCount(P2P_BUSINESS_GLOBAL p2P_BUSINESS_GLOBAL);

    List<P2P_BUSINESS_GLOBAL> getSmokeIndex();

    List<P2P_BUSINESS_GLOBAL> getRepayment();

}
