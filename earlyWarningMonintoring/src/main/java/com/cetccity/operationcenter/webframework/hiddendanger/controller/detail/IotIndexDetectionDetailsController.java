package com.cetccity.operationcenter.webframework.hiddendanger.controller.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.detail.IotIndexDetectionDetailsApi;
import com.cetccity.operationcenter.webframework.hiddendanger.service.IotDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class IotIndexDetectionDetailsController implements IotIndexDetectionDetailsApi {

    Logger logger = LoggerFactory.getLogger(IotIndexDetectionDetailsController.class);

    @Autowired
    IotDetailsService iotDetailsService;

    public List<LinkedHashMap> findIndexDetectionDetails(String device_code) throws Exception{
        List<LinkedHashMap> map_list = iotDetailsService.findIndexDetectionDetails(device_code);
        return map_list;
    }

    public MyPageInfoModel findIndexDetectionDetailsPageInfo(Integer pageNum, Integer pageSize,String device_code,String alarm_type_code) throws Exception{
        MyPageInfoModel pageInfo = iotDetailsService.findIndexDetectionDetailsPageInfo(pageNum,pageSize,device_code,alarm_type_code);
        return pageInfo;
    }
}
