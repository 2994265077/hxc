package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.api.CityComponentApi;
import com.cetccity.operationcenter.webframework.urbansign.service.impl.CityComponentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.controller
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/27 10:02
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/27 10:02
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@RestController
public class CityComponentController implements CityComponentApi{

    @Autowired
    CityComponentServiceImpl cityComponentService;

    @Override
    public HttpResponseModel<ChartDetailModel> totalCityComponent() {
        return cityComponentService.totalCityComponent();
    }

    @Override
    public HttpResponseModel<ChartDetailModel> iotCityComponent() {
        return cityComponentService.iotCityComponent();
    }

    @Override
    public HttpResponseModel<ChartDetailModel> siteCityComponent() {
        return cityComponentService.siteCityComponent();
    }

    @Override
    public HttpResponseModel<ChartDetailModel> cameraCityComponent() {
        return cityComponentService.cameraCityComponent();
    }

    @Override
    public HttpResponseModel<ChartDetailModel> cityComponentCityComponent() {
        return cityComponentService.cityComponentCityComponent();
    }
}
