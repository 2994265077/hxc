package com.cetc.cloud.datasynch.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.cetc.cloud.datasynch.api.model.DsColumnCommentModel;
import com.cetc.cloud.datasynch.api.model.DsTableCommentModel;
import com.cetc.cloud.datasynch.api.service.CommentManageRemoteService;
import com.cetc.cloud.datasynch.provider.common.CommonInstance;
import com.cetc.cloud.datasynch.provider.service.impl.CommentManageService;
import com.cetc.cloud.datasynch.provider.service.impl.DbOperateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * PackageName:   com.cetc.cloud.datasynch.provider.controller
 * projectName:   dataSyncher
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2018/11/6 20:21
 * Updater:     by luolinjie
 * Update_Date: 2018/11/6
 * Update_Description: luolinjie 补充
 **/
@Controller
@Slf4j
public class CommentManageController implements CommentManageRemoteService {

    @Autowired
    CommentManageService commentManageService;
    @Autowired
    DbOperateService dbOperateService;

    @Override
    public List<DsTableCommentModel> importTableCommentExcel(MultipartFile file, String sheetName) {

        // 解析Excel，生成List<Model>
        List<DsTableCommentModel> modelList = new ArrayList<DsTableCommentModel>();
        int startRow = CommonInstance.DEFAULT_EXCEL_STARTWROW;
        Workbook workbook = null;
        String originalFilename = file.getOriginalFilename();
        try {
            if (originalFilename.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("error when analyzing File:" + originalFilename);
        }

        // 根据sheet名称获取sheet
        Sheet sheet = workbook.getSheet(sheetName);
        // getLastRowNum，获取最后一行的行标
        log.debug(String.valueOf(sheet.getLastRowNum()));
        for (int j = startRow; j <= sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j);
            String col1 = "";
            String col2 = "";
            if (null != row.getCell(0) || "".equals(row.getCell(0))) {
                col1 = row.getCell(0).getStringCellValue();
            }
            if (null != row.getCell(1) || "".equals(row.getCell(1))) {
                col2 = row.getCell(1).getStringCellValue();
            }

            DsTableCommentModel model = new DsTableCommentModel();
            /**源，源字段名，目标字段名，目标表*/
            model.setTableName(col1);
            model.setTableComment(col2);

            modelList.add(model);
        }
        List<DsTableCommentModel> successList = commentManageService.addTableCommentList(modelList);
        return successList;
    }

    @Override
    public String importColumnCommentExcel(MultipartFile file, String sheetName) {

        // 解析Excel，生成List<Model>
        List<DsColumnCommentModel> modelList = new ArrayList<DsColumnCommentModel>();
        int startRow = CommonInstance.DEFAULT_EXCEL_STARTWROW;
        Workbook workbook = null;
        String originalFilename = file.getOriginalFilename();
        try {
            if (originalFilename.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("error when analyzing File:" + originalFilename);
        }

        // 根据sheet名称获取sheet
        Sheet sheet = workbook.getSheet(sheetName);
        // getLastRowNum，获取最后一行的行标
        log.info("\n>>>>> getLastRowNum:" + String.valueOf(sheet.getLastRowNum()));
        for (int j = startRow; j <= sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j);
            String tableName = "";
            String columnName = "";
            String columnComment = "";
            if (null != row.getCell(0) && !"".equals(row.getCell(0))
                    && null != row.getCell(1) && !"".equals(row.getCell(1))
                    && null != row.getCell(2) && !"".equals(row.getCell(2))) {
                tableName = row.getCell(0).getStringCellValue();
                columnName = row.getCell(1).getStringCellValue();
                columnComment = row.getCell(2).getStringCellValue();
            } else {
                continue;
            }

            DsColumnCommentModel model = new DsColumnCommentModel();
            /**源，源字段名，目标字段名，目标表*/
            model.setTableName(tableName);
            model.setColumnName(columnName);
            model.setColumnComment(columnComment);

            modelList.add(model);
        }

        boolean exists = dbOperateService.checkIfTableExists(modelList.get(0).getTableName());
        if (exists==false){
            return null;
        }

        List<DsColumnCommentModel> successList = commentManageService.addColumnCommentList(modelList);
        JSONObject res = new JSONObject();
        res.put("result","success");
        res.put("resultSize",successList.size());
        return res.toJSONString();
    }

    @Override
    public String getTableAndColCommentByTableName(String tableName) {
        JSONObject res = new JSONObject();
        String tableComments = dbOperateService.getTableComments(tableName);
        res.put("table_comment", tableComments);
        List<HashMap> columnCommentsByTableName = dbOperateService.getColumnCommentsByTableName(tableName);
        res.put("comment_comment", columnCommentsByTableName);
        return res.toJSONString();
    }

}
