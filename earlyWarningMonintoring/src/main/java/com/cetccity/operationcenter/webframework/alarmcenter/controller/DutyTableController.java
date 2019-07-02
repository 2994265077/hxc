package com.cetccity.operationcenter.webframework.alarmcenter.controller;

import com.cetccity.operationcenter.webframework.alarmcenter.api.DutyTableApi;
import com.cetccity.operationcenter.webframework.alarmcenter.service.DutyTableService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameTypeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.controller
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:44 2019-03-14
 * Updater:     heliangming
 * Update_Date：10:44 2019-03-14
 * 更新描述:    heliangming 补充
 **/
@RestController
public class DutyTableController implements DutyTableApi {

    @Autowired
    DutyTableService dutyTableService;

    public HttpResponseModel uploadImgLocalIsExist(@RequestParam("file") MultipartFile file, HttpServletRequest request, @PathVariable("fileCode") String fileCode){
        return dutyTableService.uploadImgLocalIsExist(file,fileCode);
    }

    public String uploadImgLocal(@RequestParam("file") MultipartFile file, HttpServletRequest request,@PathVariable("fileCode") String fileCode) throws IOException {
        return dutyTableService.uploadImgLocal(file,fileCode);
    }

    public String downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileCode") String fileCode)throws IOException{
        return dutyTableService.downloadFile(request,response,fileCode);
    }

    public String deleteFile(@PathVariable("fileCode") String fileCode){
        return dutyTableService.deleteFile(fileCode);
    }

    public String updateFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileCode") String fileCode)throws IOException{
        return dutyTableService.deleteFile(fileCode);
    }

    public String excelToDb(MultipartFile file,String fileCode){
        return dutyTableService.excelToDb(file,fileCode);
    }

    public String dbToExcel(HttpServletResponse response,String fileCode){
        return dutyTableService.dbToExcel(response,fileCode);
    }

    public NameTypeDataModel findDuty(String day){
        return dutyTableService.findDuty(day);
    }

    public String findDutyTemplate (HttpServletResponse response){
        return dutyTableService.dbToExcel(response,"2019-03");
    }
}
