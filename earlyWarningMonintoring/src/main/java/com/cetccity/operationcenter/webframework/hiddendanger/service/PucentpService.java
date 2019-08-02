package com.cetccity.operationcenter.webframework.hiddendanger.service;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.service
 * @Project: futian
 * @Creator: yhy
 * @Create_Date: 2019/8/1 16:13
 * @Updater: yhy
 * @Update_Date: 2019/8/1 16:13
 * @Update_Description: yhy 补充
 * @Description:
 **/
public interface PucentpService {

    PieModel pieByStreet(String streetName);

    PieModel pieByCommunity(String streetName);
}
