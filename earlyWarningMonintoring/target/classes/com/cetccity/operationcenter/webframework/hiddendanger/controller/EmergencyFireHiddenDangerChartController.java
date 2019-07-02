package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TheadTbodyPlus;
import com.cetccity.operationcenter.webframework.hiddendanger.api.EmergencyFireHiddenDangerChartApi;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.hiddendanger.service.impl.EmergencyFireHiddenDangerChartServiceImpl;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.controller
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/19 21:03
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/19 21:03
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@RestController
public class EmergencyFireHiddenDangerChartController implements EmergencyFireHiddenDangerChartApi {

    @Autowired
    EmergencyFireHiddenDangerChartServiceImpl emergencyFireHiddenDangerChartService;

    @Override
    public HttpResponseModel<List<NameValueTypeModel>> count5Num(String street) {
        return HttpResponseModel.defaultSuccess(emergencyFireHiddenDangerChartService.count5Num(street));
    }

    @Override
    public HttpResponseModel<PieModel> leftPie(String street) {
        return HttpResponseModel.defaultSuccess(emergencyFireHiddenDangerChartService.leftPie(street));
    }

    @Override
    public HttpResponseModel<PieModel> rightPie(String street) {
        return HttpResponseModel.defaultSuccess(emergencyFireHiddenDangerChartService.rightPie(street));
    }

    @Override
    public HttpResponseModel<TheadTbodyPlus> table(String street) {
        return HttpResponseModel.defaultSuccess(emergencyFireHiddenDangerChartService.table(street));
    }
}
