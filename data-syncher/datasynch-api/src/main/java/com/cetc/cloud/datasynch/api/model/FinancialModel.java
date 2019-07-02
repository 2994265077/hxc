package com.cetc.cloud.datasynch.api.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/6/13.
 */
@TableName("FINANCIAL_FUTIAN")
@Data
public class FinancialModel {
    @TableField("OBJECT_ID")
    int objectId;
    @TableField("NAME")
    String name;
    @TableField("JSON_VALUE")
    String jsonValue;
    @TableField("TYPE")
    String type;
    @TableField("YJJC_UPDATE_TIME")
    Date yjjcUpdateTime;

}
