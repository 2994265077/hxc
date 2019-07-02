package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.urbansign.dao.entity.BLK_DISABLED_PEOPLE;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BLK_DISABLED_PEOPLEMapper {

    Long getCount(BLK_DISABLED_PEOPLE bLK_DISABLED_PEOPLE);

    List<BLK_DISABLED_PEOPLE> getBLK_DISABLED_PEOPLE(BLK_DISABLED_PEOPLE bLK_DISABLED_PEOPLE);
}
