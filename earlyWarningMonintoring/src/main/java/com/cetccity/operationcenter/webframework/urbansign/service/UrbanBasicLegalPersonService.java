package com.cetccity.operationcenter.webframework.urbansign.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.HeatMap;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.P2P_BUSINESS_GLOBAL;
import com.cetccity.operationcenter.webframework.web.model.citySign.UrbanBasicLegalPersonLeftOne;
import java.util.List;
import java.util.Map;

public interface UrbanBasicLegalPersonService {

    List<UrbanBasicLegalPersonLeftOne> getLeftOne(String tableName);

    List<UrbanBasicLegalPersonLeftOne> getLeftOne(String tableName, String column, String columnEntity);

    String getLeftTwo(String tableName, String column, String columnEntity);

    List<Map.Entry<String, Integer>> getRightOne();

    List<NameValueModel> rightThree();

    List<NameValueModel> rightFour();

    List<P2P_BUSINESS_GLOBAL> rightFive();

    List<P2P_BUSINESS_GLOBAL> rightSix();

    HeatMap LegalPersonThermalPower(String street);

    Map LegalPersonThermalPowerTip(String id);
}
