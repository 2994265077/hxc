package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BLK_SANXIAO_PLACE;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BlkSanxiaoPlaceMapper {

    long getBLK_SANXIAO_PLACECount(BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE);

    List<BLK_SANXIAO_PLACE> getBLK_SANXIAO_PLACE(BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE);

    List<HashMap> urbanSignProductionSafetyBi();
}
