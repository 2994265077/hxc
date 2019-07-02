package com.cetccity.operationcenter.webframework.alarmcenter.service;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameTypeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.service
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:45 2019-03-14
 * Updater:     heliangming
 * Update_Date：10:45 2019-03-14
 * 更新描述:    heliangming 补充
 **/
public interface DutyTableService {

    HttpResponseModel uploadImgLocalIsExist(MultipartFile file, String fileCode);

    String uploadImgLocal(MultipartFile file,String fileCode)throws IOException;

    String downloadFile(HttpServletRequest request, HttpServletResponse response, String fileCode) throws IOException;

    String deleteFile(String fileCode);

    String excelToDb(MultipartFile file,String fileCode);

    String dbToExcel(HttpServletResponse response,String fileCode);

    NameTypeDataModel findDuty(String day);
}
