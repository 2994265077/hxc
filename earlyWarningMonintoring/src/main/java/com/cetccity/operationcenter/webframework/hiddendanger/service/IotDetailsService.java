package com.cetccity.operationcenter.webframework.hiddendanger.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;

import java.util.LinkedHashMap;
import java.util.List;

public interface IotDetailsService {

    List<LinkedHashMap> findIndexDetectionDetails(String device_code);

    MyPageInfoModel findIndexDetectionDetailsPageInfo(Integer pageNum, Integer pageSize, String device_code, String alarm_type_code);
}
