package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.BLK_LEGAL_PERSON;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BLK_LEGAL_PERSONMapper {

    int getCount(BLK_LEGAL_PERSON bLK_LEGAL_PERSON);

    List<BLK_LEGAL_PERSON> getBLK_LEGAL_PERSON(BLK_LEGAL_PERSON bLK_LEGAL_PERSON);

}
