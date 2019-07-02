package com.cetccity.operationcenter.webframework.urbansign.dao.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.dao.entity
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/12/10 10:51
 * @Updater: huangzezhou
 * @Update_Date: 2018/12/10 10:51
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Data
@ApiModel("tableau视图")
public class TableauEntity {

    @ApiModelProperty("视图名")
    String view_name;
    @ApiModelProperty("视图url")
    String view_url;
    @ApiModelProperty(hidden = true)
    int object_id;

}
