package com.cetccity.operationcenter.webframework.unifiedauth.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.dao.entity
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/14 11:57
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/14 11:57
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Data
public class BaseEntity {
    @ApiModelProperty(value = "创建时间", hidden = true)
    Date create_time;//创建时间
    @ApiModelProperty(value = "更新时间", hidden = true)
    Date update_time;//创建时间
}
