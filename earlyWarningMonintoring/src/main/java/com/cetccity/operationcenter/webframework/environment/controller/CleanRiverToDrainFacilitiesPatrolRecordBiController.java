package com.cetccity.operationcenter.webframework.environment.controller;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.environment.api.CleanRiverToDrainFacilitiesPatrolRecordBiApi;
import com.cetccity.operationcenter.webframework.environment.api.model.PatrolRecordRightFour;
import com.cetccity.operationcenter.webframework.environment.service.CleanRiverToDrainFacilitiesPatrolRecordBiService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.environment.controller
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:27 2019-05-30
 * Updater:     heliangming
 * Update_Date：17:27 2019-05-30
 * 更新描述:    heliangming 补充
 **/
@RestController
public class CleanRiverToDrainFacilitiesPatrolRecordBiController implements CleanRiverToDrainFacilitiesPatrolRecordBiApi {

    @Autowired
    CleanRiverToDrainFacilitiesPatrolRecordBiService cleanRiverToDrainFacilitiesPatrolRecordBiService;

    public List<NameValueModel> rightOne(String SEWERATE_ID){
        return cleanRiverToDrainFacilitiesPatrolRecordBiService.rightOne(SEWERATE_ID);
    }

    public HttpResponseModel<ChartDetailModel> rightTwo(String SEWERATE_ID){
        return cleanRiverToDrainFacilitiesPatrolRecordBiService.rightTwo(SEWERATE_ID);
    }

    public HttpResponseModel<ChartDetailModel> rightThree(String street, String SEWERATE_ID, String hiddenDanger){
        return cleanRiverToDrainFacilitiesPatrolRecordBiService.rightThree(street, SEWERATE_ID, hiddenDanger);
    }

    public MyPageInfoModel rightFour(String street, String SEWERATE_ID, Integer pageNum, Integer pageSize){
        return cleanRiverToDrainFacilitiesPatrolRecordBiService.rightFour(street, SEWERATE_ID, pageNum, pageSize);
    }

}
