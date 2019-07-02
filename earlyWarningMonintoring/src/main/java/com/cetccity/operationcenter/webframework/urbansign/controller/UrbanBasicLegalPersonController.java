package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.urbansign.api.UrbanBasicLegalPersonApi;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.P2P_BUSINESS_GLOBAL;
import com.cetccity.operationcenter.webframework.web.model.citySign.UrbanBasicLegalPersonLeftOne;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanBasicLegalPersonService;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@RestController
public class UrbanBasicLegalPersonController implements UrbanBasicLegalPersonApi {

    Logger logger = LoggerFactory.getLogger(UrbanBasicLegalPersonController.class);

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    UrbanBasicLegalPersonService urbanBasicLegalPersonService;

    public List<UrbanBasicLegalPersonLeftOne> leftOne(String street,String community) throws IOException {
        List<UrbanBasicLegalPersonLeftOne> urbanBasicLegalPersonLeftOne_list;
        String tableName = "BLK_LEGAL_PERSON";
        InputStream inputStream =ESOperate.class.getResourceAsStream("/street.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,"UTF-8"));
        if(street==null && community==null){
            urbanBasicLegalPersonLeftOne_list = urbanBasicLegalPersonService.getLeftOne(tableName);
        }else if(community == null){
            String street_code = properties.getProperty(street);
            logger.info("street-->" + street);
            String column = "STREET_CODE";
            String columnEntity = street_code;
            urbanBasicLegalPersonLeftOne_list = urbanBasicLegalPersonService.getLeftOne(tableName,column, columnEntity);
        }else {
            String community_code = properties.getProperty(community);
            logger.info("community-->" + community_code);
            String column = "COMMUNITY_CODE";
            String columnEntity = community_code;
            urbanBasicLegalPersonLeftOne_list = urbanBasicLegalPersonService.getLeftOne(tableName, column, columnEntity);
        }
        return urbanBasicLegalPersonLeftOne_list;
    }

    public List<NameValueModel> leftTwo() throws IOException {
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        String tableName = "BLK_LEGAL_PERSON";
        String street = LoadMyUtil.getPropertiesVauleOfKey("street.properties","street");
        String[] street_value = street.split(",");
        for (String street_name:street_value) {
            String street_code = LoadMyUtil.getPropertiesVauleOfKey("street.properties",street_name).split(",")[0];
            String column = "STREET_CODE";
            String columnEntity = street_code;
            String num = urbanBasicLegalPersonService.getLeftTwo(tableName,column, columnEntity);
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(street_name);
            nameValueModel.setValue(num);
            nameValueModel_list.add(nameValueModel);*/
            nameValueModel_list.add(NameValueModel.builder().name(street_name).value(num).build());
        }
        return nameValueModel_list;
    }

    public List<Map.Entry<String, Integer>> rightOne(){
        List<Map.Entry<String, Integer>> map_list = urbanBasicLegalPersonService.getRightOne();
        return map_list;
    }

    public void rightTwo(){
        urbanBasicLegalPersonService.getRightOne();
    }

    public List<NameValueModel> rightThree(){
        List<NameValueModel> nameValueModel_list = urbanBasicLegalPersonService.rightThree();
        return nameValueModel_list;
    }

    public List<NameValueModel> rightFour(){
        List<NameValueModel> nameValueModel_list = urbanBasicLegalPersonService.rightFour();
        return nameValueModel_list;
    }

    public List<P2P_BUSINESS_GLOBAL> rightFive(){
        List<P2P_BUSINESS_GLOBAL> p2P_BUSINESS_GLOBAL_list_return = urbanBasicLegalPersonService.rightFive();
        return p2P_BUSINESS_GLOBAL_list_return;
    }

    public List<P2P_BUSINESS_GLOBAL> rightSix(){
        List<P2P_BUSINESS_GLOBAL> p2P_BUSINESS_GLOBAL_list_return = urbanBasicLegalPersonService.rightSix();
        return p2P_BUSINESS_GLOBAL_list_return;
    }



}
