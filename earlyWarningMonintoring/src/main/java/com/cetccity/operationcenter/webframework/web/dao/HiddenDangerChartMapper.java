package com.cetccity.operationcenter.webframework.web.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HiddenDangerChartMapper {

    int getSanfangTB_ROAD_DISEASECount(String street_code);

    int getBLK_SANXIAO_PLACECount(String street_code);

    int getTB_YINHUANFENBU_002Count(String street_code);
}
