package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.QAJJ_PUCENTP_V;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface QajjPucentpVMapper {

    List<QAJJ_PUCENTP_V> getQAJJ_PUCENTP_V(QAJJ_PUCENTP_V qAJJ_PUCENTP_V);

    int getCount(Map map);
    
}
