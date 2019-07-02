package com.cetccity.operationcenter.webframework.web.dao;

import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.T_BUILD_CHECKDAY_DANGER;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface T_BUILD_CHECKDAY_DANGERMapper {

    List<T_BUILD_CHECKDAY_DANGER> getT_BUILD_CHECKDAY_DANGER(T_BUILD_CHECKDAY_DANGER t_BUILD_CHECKDAY_DANGER);
}
