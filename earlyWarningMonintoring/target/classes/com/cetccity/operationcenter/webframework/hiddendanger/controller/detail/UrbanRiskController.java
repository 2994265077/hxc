package com.cetccity.operationcenter.webframework.hiddendanger.controller.detail;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.hiddendanger.api.detail.UrbanRiskControllerApi;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TipDetailOfList;
import com.cetccity.operationcenter.webframework.web.model.incident.NameColorDataModel;
import com.cetccity.operationcenter.webframework.web.model.incident.NameColorXDataYDataModel;
import com.cetccity.operationcenter.webframework.hiddendanger.service.URBAN_RISKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@RestController
public class UrbanRiskController implements UrbanRiskControllerApi {

    @Autowired
    URBAN_RISKService uRBAN_RISKService;

    public TipDetailOfList getURBAN_RISKDetailInformation(String id){
        TipDetailOfList uRBAN_RISKDetailOfList = uRBAN_RISKService.getURBAN_RISKDetailInformation(id);
        return uRBAN_RISKDetailOfList;
    }

    public MyPageInfoModel getURBAN_RISKDetailInformationOfListRegulatoryAgency(String id,Integer pageNum,Integer pageSize){
        MyPageInfoModel pageInfo = uRBAN_RISKService.getURBAN_RISKDetailInformationOfListRegulatoryAgency(id,pageNum,pageSize);
        return pageInfo;
    }

    public MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAssessment(String id,Integer pageNum,Integer pageSize){
        MyPageInfoModel pageInfo = uRBAN_RISKService.getURBAN_RISKDetailInformationOfListRiskAssessment(id,pageNum,pageSize);
        return pageInfo;
    }

    public MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskDanger(String id,Integer pageNum,Integer pageSize){
        MyPageInfoModel pageInfo = uRBAN_RISKService.getURBAN_RISKDetailInformationOfListRiskDanger(id,pageNum,pageSize);
        return pageInfo;
    }

    public MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAlarm(String id,Integer pageNum,Integer pageSize){
        MyPageInfoModel pageInfo = uRBAN_RISKService.getURBAN_RISKDetailInformationOfListRiskAlarm(id,pageNum,pageSize);
        return pageInfo;
    }

    public MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAccident(String id,Integer pageNum,Integer pageSize){
        MyPageInfoModel pageInfo = uRBAN_RISKService.getURBAN_RISKDetailInformationOfListRiskAccident(id,pageNum,pageSize);
        return pageInfo;
    }

    public NameColorDataModel getChartOfRightOne(String street)throws IOException {
        InputStream inputStream =ESOperate.class.getResourceAsStream("/street.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,"UTF-8"));
        String street_code = properties.getProperty(street);
        NameColorDataModel nameColorDataModel = uRBAN_RISKService.getChartOfRightOne(street_code);
        return nameColorDataModel;
    }

    public NameColorDataModel getChartOfRightTwo(String street)throws IOException{
        InputStream inputStream =ESOperate.class.getResourceAsStream("/street.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,"UTF-8"));
        String street_code = properties.getProperty(street);
        NameColorDataModel nameColorDataModel = uRBAN_RISKService.getChartOfRightTwo(street_code);
        return nameColorDataModel;
    }

    public NameColorXDataYDataModel getChartOfRightThree()throws IOException{
        InputStream inputStream =ESOperate.class.getResourceAsStream("/street.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,"UTF-8"));
        String street_string = properties.getProperty("street");
        NameColorXDataYDataModel nameColorXDataYDataModel = uRBAN_RISKService.getChartOfRightThree(street_string);
        return nameColorXDataYDataModel;
    }

    public NameColorDataModel getChartOfRightFour(String street)throws IOException{
        InputStream inputStream =ESOperate.class.getResourceAsStream("/street.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,"UTF-8"));
        String street_code = properties.getProperty(street);
        NameColorDataModel NameColorDataModel = uRBAN_RISKService.getChartOfRightFour(street_code);
        return NameColorDataModel;
    }
}
