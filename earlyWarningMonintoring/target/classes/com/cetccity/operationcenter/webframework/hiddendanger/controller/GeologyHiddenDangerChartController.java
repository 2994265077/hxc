package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.GeologyHiddenDangerChartApi;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.hiddendanger.service.impl.GeologyHiddenDangerChartServiceImpl;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.controller
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/20 9:29
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/20 9:29
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@RestController
public class GeologyHiddenDangerChartController implements GeologyHiddenDangerChartApi {

    @Autowired
    GeologyHiddenDangerChartServiceImpl geologyHiddenDangerChartService;

    @Override
    public HttpResponseModel<NameValueTypeModel> countAll(String street) {
        return HttpResponseModel.defaultSuccess(geologyHiddenDangerChartService.countAll(street));
    }

    @Override
    public HttpResponseModel<PieModel> leftTopPie(String street) {
        return HttpResponseModel.defaultSuccess(geologyHiddenDangerChartService.leftTopPie(street));
    }

    @Override
    public HttpResponseModel<PieModel> rightTopPie(String street) {
        return HttpResponseModel.defaultSuccess(geologyHiddenDangerChartService.rightTopPie(street));
    }

    @Override
    public HttpResponseModel<PieModel> bottomPie(String street) {
        return HttpResponseModel.defaultSuccess(geologyHiddenDangerChartService.bottomPie(street));
    }
}
