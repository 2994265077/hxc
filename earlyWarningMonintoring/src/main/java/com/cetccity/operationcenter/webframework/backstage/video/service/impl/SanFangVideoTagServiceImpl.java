package com.cetccity.operationcenter.webframework.backstage.video.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.DUTY_TABLE;
import com.cetccity.operationcenter.webframework.backstage.video.dao.DistrictVideoClassMapper;
import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DistrictVideoClass;
import com.cetccity.operationcenter.webframework.backstage.video.service.SanFangVideoTagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 9:58 2019-07-31
 * Updater:     heliangming
 * Update_Date：9:58 2019-07-31
 * 更新描述:    heliangming 补充
 **/
@Service
@Slf4j
public class SanFangVideoTagServiceImpl implements SanFangVideoTagService {

    @Autowired
    DistrictVideoClassMapper districtVideoClassMapper;

    public String saveVideoTag(MultipartFile file){
        String sheetName = "Sheet1";
        JSONObject res = new JSONObject();
        // 解析Excel，生成List<Model>
        List<DistrictVideoClass> modelList = new ArrayList<>();
        int startRow = 2;
        Workbook workbook = null;
        String originalFilename = file.getOriginalFilename();
        try {
            if (originalFilename.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (IOException e) {
            log.error(e.toString());
            log.error("error when analyzing File:" + originalFilename);
        }
        // 获取第一个sheet
        Sheet sheet = workbook.getSheet(sheetName);
        // getLastRowNum，获取最后一行的行标
        log.debug(String.valueOf(sheet.getLastRowNum()));
        for (int j = startRow; j < sheet.getLastRowNum() + 1; j++) {
            Row row = sheet.getRow(j);
            String col[] = {"", "", "", "", "", "", "", "", ""};
            for (int s = 0; s < 9; s++) {
                if (null != row.getCell(s) || "".equals(row.getCell(s))) {
                    row.getCell(s).setCellType(Cell.CELL_TYPE_STRING);  //把内容转换成string
                    col[s] = row.getCell(s).getStringCellValue();
                }
            }
            DistrictVideoClass u = DistrictVideoClass.builder().build();
            /**源，源字段名，目标字段名，目标表*/
            String classIdName = col[2];
            Integer classId = null;
            switch(classIdName){
                case "三防易涝点" : classId = 405;break;
                case "重要路段" : classId = 406;break;
                case "路口桥洞" : classId = 404;break;
            }
            u.setOBJECT_ID(districtVideoClassMapper.maxId());
            u.setCAMERAID(col[0]);
            u.setGB_CODE(col[1]);
            u.setCLASS_ID(classId);
            u.setUSER_NAME("admin");
            modelList.add(u);
        }
        if (modelList.size() == 0) {
            log.error("data content is null!");
            res.put("faild", "faild imported file" + originalFilename + ":data content is null!");
            return res.toJSONString();
        }
        modelList.stream().forEach(u -> districtVideoClassMapper.videoAddTag(u));
        res.put("success", "successfully imported file" + originalFilename);
        res.put("count", modelList.size());
        return res.toJSONString();
    }
}
