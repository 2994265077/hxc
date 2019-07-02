package com.cetccity.operationcenter.webframework.urbansign.dao;

import com.cetccity.operationcenter.webframework.urbansign.api.model.HISTORY_FIRE_NUM;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.HISTORY_FIRE;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface HISTORY_FIREMapper {

    //今日伤亡人数
    List<HISTORY_FIRE_NUM> casualtiesCount(@Param("time") String time);

    Integer selectCountOfToday(String time);

    List<HISTORY_FIRE> getHISTORY_FIREOfMonth();
}
