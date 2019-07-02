package com.cetccity.operationcenter.webframework.hiddendanger.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.web.model.build.EnterpriseModel;
import com.cetccity.operationcenter.webframework.web.model.build.LayerNumData;
import com.cetccity.operationcenter.webframework.web.model.build.ScoreTotleAttribute;

public interface BuildDetailService {

    NameDataModel buildBasic(String tableName,String buildid);

    NameDataModel buildRisk(String tableName,String buildid);

    MyPageInfoModel buildHiddenDanger(String buildid,Integer pageNum,Integer pageSize);

    EnterpriseModel buildEnterprise(String buildid,Integer pageNum,Integer pageSize);

    LayerNumData buildHouse(String tableName, String buildid);
}
