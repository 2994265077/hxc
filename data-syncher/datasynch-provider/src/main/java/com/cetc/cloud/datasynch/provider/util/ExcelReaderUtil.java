package com.cetc.cloud.datasynch.provider.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * Created by luolinjie on 2018/8/29.
 */
public class ExcelReaderUtil {
    private  static Logger logger = LoggerFactory.getLogger(ExcelReaderUtil.class);
    /**
     * 将Excel文件读取为List
     *
     * @param filePath
     * @throws Exception
     */
    public static List readExcel2List(String filePath, String sheetName,int startRow, int endRow, int startColumn, int endColumn){
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
        } catch (IOException e) {
            logger.error("filePath does not exists!");
            e.printStackTrace();
        }
        HSSFSheet sheet = workbook.getSheet(sheetName);
        ArrayList<ArrayList> list_rows = new ArrayList<ArrayList>();

        for (int j = startRow-1; j < endRow; j++) {

            HSSFRow row = sheet.getRow(j);

            ArrayList<String> list_cols = new ArrayList<String>();
            logger.info("row:"+j);
            for (int k = 0; k < endColumn; k++){
                if (null==row.getCell(k)){

                    logger.info("col:"+k);
                }
                HSSFCell cell = row.getCell(k);
                if(k==0) logger.info(cell.getStringCellValue());
                if (cell==null) {
                    list_cols.add(null);
                    continue;
                }else {
                    String col = cell.getStringCellValue();
                    list_cols.add(col);
                }
            }
            list_rows.add(list_cols);
        }
        return list_rows;
    }
}
