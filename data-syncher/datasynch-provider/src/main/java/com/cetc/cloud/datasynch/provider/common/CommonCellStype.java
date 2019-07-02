package com.cetc.cloud.datasynch.provider.common;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Description：统一设定cell样式
 * Created by luolinjie on 2018/8/31.
 */
public class CommonCellStype {

    public static HSSFCellStyle cellStyle = null;

    public static CellStyle setCellStyle1(CellStyle cellStyle,HSSFFont font ) {

        //设置字体
        short height = 200;
        font.setFontHeight(height);
        cellStyle.setFont(font);
        //背景填充颜色
        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cellStyle.setFillBackgroundColor(HSSFColor.BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setWrapText(true);                           // 设置单元格内容是否自动换行

        return cellStyle;
    }


}
