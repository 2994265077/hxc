package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.urbansign.dao.entity.COMMUNITY_CODE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface COMMUNITY_CODEMapper {

    List<COMMUNITY_CODE> getCOMMUNITY_CODE(COMMUNITY_CODE cOMMUNITY_CODE);

    List<COMMUNITY_CODE> getStreet_CODE();

    double getStreet_Area(@Param("streetName") String streetName);

    List<COMMUNITY_CODE> getCommunity_CODE(@Param("streetName") String streetName);

    double getCommunity_Area(@Param("communityCode") String communityCode);

}
