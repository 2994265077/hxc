package com.cetccity.operationcenter.webframework.core.chart.engine.model;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import lombok.Data;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.core.chart.engine
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/28 16:56
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/28 16:56
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Data
public class ChartDetailModel<T> {

    List<NameValueTypeModel<T>> detail;

    List<BarOrLineModel> chart;
}
