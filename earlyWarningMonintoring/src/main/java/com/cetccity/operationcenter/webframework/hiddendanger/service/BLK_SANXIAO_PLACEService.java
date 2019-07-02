package com.cetccity.operationcenter.webframework.hiddendanger.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.SanXiaoTip;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BLK_SANXIAO_PLACE;

public interface BLK_SANXIAO_PLACEService {

    long getBLK_SANXIAO_PLACECount(BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE);

    SanXiaoTip findBLK_SANXIAO_PLACETip(String id);

    NameDataModel findBLK_SANXIAO_PLACEDetails(String id);

    NameDataModel findBLK_SANXIAO_PLACEScore(String id);
}
