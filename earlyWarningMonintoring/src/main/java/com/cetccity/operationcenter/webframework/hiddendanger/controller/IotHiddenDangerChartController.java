package com.cetccity.operationcenter.webframework.hiddendanger.controller;


import com.cetccity.operationcenter.webframework.hiddendanger.api.IotHiddenDangerChartApi;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.hiddendanger.service.impl.IotHiddenDangerChartServiceImpl;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.cetccity.operationcenter.webframework.hiddendanger.controller.AllRegionHiddenDangerChartController.DEFAULT_FORMATTER;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.controller
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/19 17:42
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/19 17:42
 * @Update_Description: huangzezhou 补充
 * @Description:   //TODO
 **/
@RestController
public class IotHiddenDangerChartController implements IotHiddenDangerChartApi{

    @Autowired
    IotHiddenDangerChartServiceImpl iotHiddenDangerChartService;

    @Override
    public HttpResponseModel<PieModel> leftPie(String street) {
        return HttpResponseModel.defaultSuccess(iotHiddenDangerChartService.leftPie(street));
    }

    @Override
    public HttpResponseModel<PieModel> rightPie(String street) {
        return HttpResponseModel.defaultSuccess(iotHiddenDangerChartService.rightPie(street));
    }

    @Override
    public HttpResponseModel<List<BarOrLineModel>> line(String street, String startTime, String endTime) {
        LocalDateTime start = LocalDate.parse(startTime, DEFAULT_FORMATTER).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endTime, DEFAULT_FORMATTER).plusDays(1).atStartOfDay();
        return HttpResponseModel.defaultSuccess(iotHiddenDangerChartService.line(street, start, end));
    }
}
