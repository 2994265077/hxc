package com.cetccity.operationcenter.webframework.rundisplay.dao;

import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.QhsjTAirBasicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
@CacheConfig(cacheNames = "QhsjTAirBasicInfoMapper")
public interface QhsjTAirBasicInfoMapper {

    @Cacheable(key="#id + 'getAirBasicInfo'")
    List<QhsjTAirBasicInfo> getAirBasicInfo(@Param("id") String id);

}
