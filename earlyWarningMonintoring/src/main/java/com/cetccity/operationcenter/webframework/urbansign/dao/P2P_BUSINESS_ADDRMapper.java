package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.urbansign.dao.entity.P2P_BUSINESS_ADDR;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface P2P_BUSINESS_ADDRMapper {

    int selectCount(P2P_BUSINESS_ADDR p2P_BUSINESS_ADDR);
}
