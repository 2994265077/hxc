package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.T_XF_BUILDER_PFJG;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TXfBuilderPfjgMapper {

    Integer getT_XF_BUILDER_PFJGCount(T_XF_BUILDER_PFJG t_XF_BUILDER_PFJG);

    List<T_XF_BUILDER_PFJG> getT_XF_BUILDER_PFJG(T_XF_BUILDER_PFJG t_XF_BUILDER_PFJG);
}
