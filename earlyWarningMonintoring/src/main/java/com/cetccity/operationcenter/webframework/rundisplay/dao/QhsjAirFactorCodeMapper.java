package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QhsjAirFactorCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
@CacheConfig(cacheNames = "QhsjAirFactorCodeMapper")
public interface QhsjAirFactorCodeMapper {

    @Cacheable(key = "'getAirFactorCode'")
    List<QhsjAirFactorCode> getAirFactorCode();

    @Cacheable(key = "'getAirFactorCodeToName' + #FACTOR_CODE")
    List<QhsjAirFactorCode> getAirFactorCodeToName(@Param("FACTOR_CODE") String FACTOR_CODE);
}
