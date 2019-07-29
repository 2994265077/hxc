package com.cetccity.operationcenter.webframework.hiddendanger.controller;

import com.cetccity.operationcenter.webframework.hiddendanger.api.ThreeSmallPlaceChartApi;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.hiddendanger.service.impl.ThreeSmallPlaceChartServiceImpl;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.controller
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/17 16:41
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/17 16:41
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/

@RestController
@CacheConfig(cacheNames = "hiddendanger")
public class ThreeSmallPlaceChartController implements ThreeSmallPlaceChartApi {

    @Autowired
    ThreeSmallPlaceChartServiceImpl threeSmallPlaceChartService;

    @Override
    @Cacheable(key = "'hiddendanger_threesmallchart_pie' + #street")
    public HttpResponseModel<PieModel> pie(String street) {
        return HttpResponseModel.defaultSuccess(threeSmallPlaceChartService.pie(street));
    }

    @Override
    @Cacheable(key = "'hiddendanger_threesmallchart_line' + #street")
    public HttpResponseModel<List<BarOrLineModel>> line(String street) {
        return HttpResponseModel.defaultSuccess(threeSmallPlaceChartService.line(street));
    }

    @Override
    @Cacheable(key = "'hiddendanger_threesmallchart_bar' + #street")
    public HttpResponseModel<List<BarOrLineModel>> bar(String street) {
        return HttpResponseModel.defaultSuccess(threeSmallPlaceChartService.bar(street));
    }

    @Override
    @Cacheable(key = "'hiddendanger_threesmallchart_streetBar' + #street")
    public HttpResponseModel<List<BarOrLineModel>> streetBar(String street) {
        return HttpResponseModel.defaultSuccess(threeSmallPlaceChartService.streetBar(street));
    }
}
