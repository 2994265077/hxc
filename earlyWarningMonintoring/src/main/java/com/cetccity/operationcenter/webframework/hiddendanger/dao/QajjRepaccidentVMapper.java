package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.QAJJ_REPACCIDENT_V;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface QajjRepaccidentVMapper {

    List<QAJJ_REPACCIDENT_V> getQAJJ_REPACCIDENT_V(QAJJ_REPACCIDENT_V qAJJ_REPACCIDENT_V);
}
