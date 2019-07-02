package com.cetccity.operationcenter.webframework.core.frame.basicmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.cetccity.operationcenter.webframework.core.frame.basicmodel
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/18 21:03
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/18 21:03
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NameValueTypeModel<T> {

    String name;

    T value;

}
