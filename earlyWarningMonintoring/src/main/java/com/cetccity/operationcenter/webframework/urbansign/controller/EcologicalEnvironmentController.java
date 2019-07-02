package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.api.EcologicalEnvironmentApi;
import com.cetccity.operationcenter.webframework.urbansign.service.EcologicalEnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.controller
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/15 9:22
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/15 9:22
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@RestController
public class EcologicalEnvironmentController implements EcologicalEnvironmentApi {

    /**
     * 注入 EcologicalEnvironmentService
     */
    @Autowired
    private EcologicalEnvironmentService ecologicalEnvironmentService;

    /**
     * 生态环境-总统计
     * @return
     */
    @Override
    public HttpResponseModel<ChartDetailModel> totalEcological() {
        return ecologicalEnvironmentService.totalEcological();
    }

    /**
     * 河流水质状况-按季度
     * @return
     */
    public HttpResponseModel<ChartDetailModel> riverWaterQualityByQuarter(String year, String quarter){
        return ecologicalEnvironmentService.riverWaterQualityByQuarter(year, quarter);
    }
    /**
     * 空气质量指数-按天
     * @return
     */
    @Override
    public HttpResponseModel<ChartDetailModel> airAqiByDay() {
        return ecologicalEnvironmentService.airAqiByDay();
    }

    /**
     * 空气监测站超标统计
     * @param type 类型
     * @return
     */
    @Override
    public HttpResponseModel<ChartDetailModel> airExcessByStation(String type, String station){
        int num_x;
        if("MONTH".equals(type)){
            num_x = 12;
        }else{
            num_x = 30;
        }
        return ecologicalEnvironmentService.airExcessByStation(type, num_x, station);
    }

    /**
     * 监测站分布
     * @return
     */
    @Override
    public HttpResponseModel<ChartDetailModel> stationDistribution(){
        return ecologicalEnvironmentService.stationDistribution();
    }
    /*
    @Override
    public HttpResponseModel<ChartDetailModel> waterQualityByMonth() {
        return ecologicalEnvironmentService.waterQualityByMonth();
    }

    @Override
    public HttpResponseModel<ChartDetailModel> waterQualityByDay() {
        return ecologicalEnvironmentService.waterQualityByDay();
    }

    @Override
    public HttpResponseModel<ChartDetailModel> airByDay() {
        return ecologicalEnvironmentService.airByDay();
    }

    @Override
    public HttpResponseModel<ChartDetailModel> waterOnGroundByMonth() {
        return ecologicalEnvironmentService.waterOnGroundByMonth();
    }

    @Override
    public HttpResponseModel<ChartDetailModel> waterOnGroundByDay() {
        return ecologicalEnvironmentService.waterOnGroundByDay();
    }*/

}
