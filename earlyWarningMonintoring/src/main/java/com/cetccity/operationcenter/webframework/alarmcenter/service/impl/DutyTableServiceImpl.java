package com.cetccity.operationcenter.webframework.alarmcenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.DUTY_TABLEMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.DUTY_TABLE_FILE_PATHMapper;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.DUTY_TABLE;
import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.DUTY_TABLE_FILE_PATH;
import com.cetccity.operationcenter.webframework.alarmcenter.service.DutyTableService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameTypeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.service.impl
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:49 2019-03-14
 * Updater:     heliangming
 * Update_Date：10:49 2019-03-14
 * 更新描述:    heliangming 补充
 **/
@Slf4j
@Service
public class DutyTableServiceImpl implements DutyTableService {

    @Autowired
    DUTY_TABLE_FILE_PATHMapper dUTY_TABLE_FILE_PATHMapper;

    @Autowired
    DUTY_TABLEMapper dUTY_TABLEMapper;

    public HttpResponseModel uploadImgLocalIsExist(MultipartFile file, String fileCode) {
        HttpResponseModel httpResponseModel = new HttpResponseModel();
        List<DUTY_TABLE_FILE_PATH> dUTY_TABLE_FILE_PATH_list = dUTY_TABLE_FILE_PATHMapper.getDUTY_TABLE_FILE_PATH(fileCode);
        if (dUTY_TABLE_FILE_PATH_list.isEmpty()) {
            httpResponseModel.setCode(200);
            httpResponseModel.setMessage("SUCCESS");
            httpResponseModel.setData(null);
        } else {
            httpResponseModel.setCode(201);
            httpResponseModel.setMessage("文件已存在，是否确定要覆盖");
            httpResponseModel.setData(null);
        }
        return httpResponseModel;
    }

    //上传文件
    @Transactional
    public String uploadImgLocal(MultipartFile file, String fileCode) throws IOException {
        /*File pathlocal = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!pathlocal.exists()) pathlocal = new File("");
        System.out.println("path:"+pathlocal.getAbsolutePath());*/
        //检查文件是否已存在
        List<DUTY_TABLE_FILE_PATH> dUTY_TABLE_FILE_PATH_list = dUTY_TABLE_FILE_PATHMapper.getDUTY_TABLE_FILE_PATH(fileCode);
        if (!dUTY_TABLE_FILE_PATH_list.isEmpty()) {
            deleteFile(fileCode);
        }
        File pathlocal = new File("");
        File upload = new File(pathlocal.getAbsolutePath(), "config/upload/");
        System.out.println("path:" + upload.getAbsolutePath());
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "/duty/" + fileCode + "/";
            String path = filePath + fileName;
            File dest = new File(upload + path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            //保存记录
            DUTY_TABLE_FILE_PATH save = new DUTY_TABLE_FILE_PATH();
            save.setOBJECT_ID(dUTY_TABLE_FILE_PATHMapper.getSEQ());
            save.setFILE_CODE(fileCode);
            save.setFILE_NAME(fileName);
            save.setFILE_SUFFIX(suffixName);
            save.setPATH(upload + path);
            dUTY_TABLE_FILE_PATHMapper.insert(save);
            return "上传成功";
        } catch (IllegalStateException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }
        return "上传失败";
    }

    public String downloadFile(HttpServletRequest request, HttpServletResponse response, String fileCode) throws IOException {
        List<DUTY_TABLE_FILE_PATH> dUTY_TABLE_FILE_PATH_list = dUTY_TABLE_FILE_PATHMapper.getDUTY_TABLE_FILE_PATH(fileCode);
        String fileName = dUTY_TABLE_FILE_PATH_list.get(0).getFILE_NAME()+".xls";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File(dUTY_TABLE_FILE_PATH_list.get(0).getPATH());
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    log.error(e.toString());
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            log.error(e.toString());
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            log.error(e.toString());
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String deleteFile(String fileCode) {
        List<DUTY_TABLE_FILE_PATH> pC_FILE_INFORMATION_list = dUTY_TABLE_FILE_PATHMapper.getDUTY_TABLE_FILE_PATH(fileCode);
        String fileName = pC_FILE_INFORMATION_list.get(0).getFILE_NAME();// 文件名
        String filePath = pC_FILE_INFORMATION_list.get(0).getPATH();// 文件路径
        try {
            dUTY_TABLE_FILE_PATHMapper.deleteFile(fileCode);
            File file = new File(filePath);
            if (file.delete()) {
                log.info(file.getName() + "is deleted");
                return "true";
            } else {
                log.info("Delete failed.");
                return "false";
            }
        } catch (Exception e) {
            log.info("Exception occured");
            log.error(e.toString());
            return "false";
        }
    }

    public String excelToDb(MultipartFile file, String fileCode) {
        String sheetName = "Sheet1";
        //判断是否存在该月份的值班表
        List<DUTY_TABLE> dUTY_TABLE_list = dUTY_TABLEMapper.getDUTY_TABLE(DUTY_TABLE.builder().DUTY_CODE(fileCode).build());
        if(dUTY_TABLE_list.size()!=0){
            dUTY_TABLEMapper.delete(fileCode);
        }
            JSONObject res = new JSONObject();
            // 解析Excel，生成List<Model>
            List<DUTY_TABLE> modelList = new ArrayList<>();
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
                String col[] = {"", "", "", "", "", "", "", "", "", "", ""};
                for (int s = 0; s < 11; s++) {
                    if (null != row.getCell(s) || "".equals(row.getCell(s))) {
                        row.getCell(s).setCellType(Cell.CELL_TYPE_STRING);  //把内容转换成string
                        col[s] = row.getCell(s).getStringCellValue();
                    }
                }
                DUTY_TABLE dUTY_TABLE = DUTY_TABLE.builder().build();
                /**源，源字段名，目标字段名，目标表*/
                dUTY_TABLE.setOBJECT_ID(dUTY_TABLEMapper.getSEQ_DUTY_TABLE());
                dUTY_TABLE.setSCHEDULE_TIME(col[0]);
                dUTY_TABLE.setDEPT_NAME(col[1]);
                dUTY_TABLE.setDUTY_LEADER(col[2]);
                dUTY_TABLE.setDUTY_LEADER_POSITION(col[3]);
                dUTY_TABLE.setDUTY_LEADER_PHONE(col[4]);
                dUTY_TABLE.setDUTY_SEC(col[5]);
                dUTY_TABLE.setDUTY_SEC_POSITION(col[6]);
                dUTY_TABLE.setDUTY_SEC_PHONE(col[7]);
                dUTY_TABLE.setDUTY_CLERK(col[8]);
                dUTY_TABLE.setDUTY_CLERK_POSITION(col[9]);
                dUTY_TABLE.setDUTY_CLERK_PHONE(col[10]);
                dUTY_TABLE.setDUTY_CODE(fileCode);
                dUTY_TABLE.setDUTY_DAY(col[0].substring(0, 10));
                modelList.add(dUTY_TABLE);
            }
            if (modelList.size() == 0) {
                log.error("data content is null!");
                res.put("faild", "faild imported file" + originalFilename + ":data content is null!");
                return res.toJSONString();
            }
            modelList.stream().forEach(u -> dUTY_TABLEMapper.save(u));
            res.put("success", "successfully imported file" + originalFilename);
            res.put("count", modelList.size());
            return res.toJSONString();
    }


    public String dbToExcel(HttpServletResponse response, String fileCode){
        List<DUTY_TABLE> list = dUTY_TABLEMapper.getDUTY_TABLE(DUTY_TABLE.builder().DUTY_CODE(fileCode).build());
        util(response,fileCode,list);
        return "下载完成";
    }

    public void util(HttpServletResponse response, String fileCode,List<DUTY_TABLE> list) {
        String filename;
        if("2019-03".equals(fileCode)) {
            filename = "值班安排表--模板.xls";
        }else{
            filename = fileCode + "值班安排表.xls";
        }
        //创建一工作空间
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一表单
        HSSFSheet sheet = workbook.createSheet(filename);
        //创建第一行表题行
        HSSFRow headerRowtitle = sheet.createRow(0);
        HSSFRow headerRow = sheet.createRow(1);
        int p = 0;
        HSSFCell headerNameCell = headerRowtitle.createCell(p);
        headerNameCell.setCellValue(filename);
        int k = 0;
        //第二行表头
        String table_top[] = {"值班时间","值班单位","值班领导","值班领导职务","值班领导电话","值班科长","值班科长职务","值班科长电话","值班员","值班员职务","值班员电话"};
        for(int n=0;n<table_top.length;n++){
            HSSFCell headerTopCell = headerRow.createCell(k);
            headerTopCell.setCellValue(table_top[n]);
            k++;
        }
        //第三行之后的内容
        for (int i = 0; i < list.size(); i++) {
            DUTY_TABLE tblfutian = list.get(i);
            String table_content[] = {tblfutian.getSCHEDULE_TIME(),tblfutian.getDEPT_NAME(),
                    tblfutian.getDUTY_LEADER(),tblfutian.getDUTY_LEADER_POSITION(),tblfutian.getDUTY_LEADER_PHONE(),
                    tblfutian.getDUTY_SEC(),tblfutian.getDUTY_SEC_POSITION(),tblfutian.getDUTY_SEC_PHONE(),
                    tblfutian.getDUTY_CLERK(),tblfutian.getDUTY_CLERK_POSITION(),tblfutian.getDUTY_CLERK_PHONE()};
            int j = 0;
            HSSFRow row = sheet.createRow(i + 2);
            for(int s=0;s<table_content.length;s++){
                HSSFCell empNameContentCell = row.createCell(j);
                j++;
                empNameContentCell.setCellValue(table_content[s]);
            }
        }
        response.setContentType("application/xls");
        ServletOutputStream sos = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes(), "ISO8859-1"));
            sos = response.getOutputStream();
            workbook.write(sos);
        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            if (sos != null) {
                try {
                    sos.flush();
                    sos.close();
                } catch (IOException e) {
                    log.error(e.toString());
                }
            }
        }
    }

    public NameTypeDataModel findDuty(String day){
        List<DUTY_TABLE> dUTY_TABLE_list = dUTY_TABLEMapper.getDUTY_TABLE(DUTY_TABLE.builder().DUTY_DAY(day+" 00:00:00").build());
        NameTypeDataModel nameTypeDataModel = new NameTypeDataModel();
        dUTY_TABLE_list.stream()
                .filter(u->u.getDEPT_NAME().equals("区值班领导"))
                .forEach(u->{
                    nameTypeDataModel.setName(u.getDUTY_LEADER());
                    nameTypeDataModel.setType(u.getDEPT_NAME());
                    nameTypeDataModel.setSmooth(u.getDUTY_LEADER_POSITION());
                });
        // 排班表查询排序规则   规则为区领导 > 街道办 > 其他
        List<DUTY_TABLE> dUTY_TABLE_list_new = dUTY_TABLE_list.stream()
                .filter(u -> !"区值班领导".equals(u.getDEPT_NAME()))
                .sorted(Comparator.comparingInt(u -> {
                    if (StringUtils.contains(u.getDEPT_NAME(), "区值班领导")) {
                        return 0;
                    } else if (StringUtils.contains(u.getDEPT_NAME(), "街道办事处")) {
                        return 1;
                    } else {
                        return 2;
                    }
                }))
                .collect(Collectors.toList());
        nameTypeDataModel.setData(dUTY_TABLE_list_new);
        return nameTypeDataModel;
    }
}
