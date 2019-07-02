package com.cetccity.operationcenter.webframework.core.chart.engine.constant;

/**
 * @Package: com.cetccity.operationcenter.webframework.core.chart.engine
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/4 14:34
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/4 14:34
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
public interface RegexCommonConstantsInterface {

    //只包含数字和英文字母,实际是除了([0-9a-zA-Z_])还包含了希腊字母，俄文的字母等；
    String ONLY_NUMBER_AND_ENGLISH = "^\\w+$";

}
