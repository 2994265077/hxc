package com.cetc.cloud.datasynch.api.model.traffic;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/6/21.
 */
@Data
@TableName("ROAD_ADMIN_VEHICLE_PERMIT")
@KeySequence("SEQ_ROAD_ADMIN_VEHICLE_PERMIT")
public class VehiclePermitModel {
    @TableId(value = "OBJECT_ID", type = IdType.INPUT)
    private Long objectId;
    @TableField("PUBLISH_TIME")
    private Date publishTime;
    @TableField("KEY")
    private String perimitName;
    @TableField("VALUE")
    private int permitNum;

}
