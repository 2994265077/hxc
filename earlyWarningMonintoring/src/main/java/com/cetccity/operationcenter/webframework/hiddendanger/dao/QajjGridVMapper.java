package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.QAJJ_GRID_V;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface QajjGridVMapper {

    List<QAJJ_GRID_V> getQAJJ_GRID_V(QAJJ_GRID_V qAJJ_GRID_V);
}
