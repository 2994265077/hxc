package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.hiddendanger.api.FireHiddenDangerChartApi;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.hiddendanger.service.impl.FireHiddenDangerChartServiceImpl;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.controller
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/18 18:41
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/18 18:41
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/

@RestController
public class FireHiddenDangerChartController implements FireHiddenDangerChartApi{

    @Autowired
    FireHiddenDangerChartServiceImpl fireHiddenDangerChartService;


    @Override
    public HttpResponseModel<PieModel> pie(String street) {
        return HttpResponseModel.defaultSuccess(fireHiddenDangerChartService.pie(street));
    }


    @Override
    public HttpResponseModel<List<BarOrLineModel>> bar(String street) {
        return HttpResponseModel.defaultSuccess(fireHiddenDangerChartService.bar(street));
    }
}
