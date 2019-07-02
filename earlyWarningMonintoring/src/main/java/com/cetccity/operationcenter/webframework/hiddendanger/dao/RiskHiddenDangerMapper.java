package com.cetccity.operationcenter.webframework.hiddendanger.dao;

import com.cetccity.operationcenter.webframework.hiddendanger.api.model.RiskHiddenDanger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RiskHiddenDangerMapper {

    List<RiskHiddenDanger> getRiskHiddenDanger(RiskHiddenDanger riskHiddenDanger);
}
