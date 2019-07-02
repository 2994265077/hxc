package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.QXFJ_BUILD_ZTZR_V;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QXFJ_BUILD_ZTZR_VMapper {

    List<QXFJ_BUILD_ZTZR_V> getQXFJ_BUILD_ZTZR_V(QXFJ_BUILD_ZTZR_V qXFJ_BUILD_ZTZR_V);

}
