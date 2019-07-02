package com.cetc.cloud.datasynch.api.model.traffic;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/6/22.
 */
@Data
@TableName("ROAD_ADMIN_OFFENCES")
@KeySequence("SEQ_ROAD_ADMIN_OFFENCES")
public class OffenceModel {
    @TableId(value = "OBJECT_ID", type = IdType.INPUT)
    private Long objectId;
    @TableField("PUBLISH_TIME")
    private Date publishTime;
    @TableField("VALUE")
    private int num;
}
