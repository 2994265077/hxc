package com.cetccity.operationcenter.webframework.core.chart.engine.model;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.api.model
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/17 16:18
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/17 16:18
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Data
@AllArgsConstructor
public class PieModel {
    // 使用Long会更好
    int total;
    List<NameValueTypeModel> data;

}
