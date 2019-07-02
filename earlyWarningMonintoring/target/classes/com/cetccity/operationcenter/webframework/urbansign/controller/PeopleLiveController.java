package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.api.PeopleLiveApi;
import com.cetccity.operationcenter.webframework.urbansign.service.impl.PeopleLiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.controller
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/7 17:28
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/7 17:28
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@RestController
public class PeopleLiveController implements PeopleLiveApi {

    @Autowired
    PeopleLiveService peopleLiveService;

    @Override
    public HttpResponseModel<Object> peopleLiveTotal() {
        return peopleLiveService.peopleLiveTotal();
    }


    @Override
    public HttpResponseModel<Object> littleSchool() {
        return peopleLiveService.littleSchool();
    }

    @Override
    public HttpResponseModel<Object> smallSchool() {
        return peopleLiveService.smallSchool();
    }

    @Override
    public HttpResponseModel<Object> professionalSchool() {
        return peopleLiveService.professionalSchool();
    }

    @Override
    public HttpResponseModel<Object> middleSchool() {
        return peopleLiveService.middleSchool();
    }

    @Override
    public HttpResponseModel<Object> feedOld() {
        return peopleLiveService.feedOld();
    }

    @Override
    public HttpResponseModel<Object> hospitalBed() {
        return peopleLiveService.hospitalBed();
    }
}
