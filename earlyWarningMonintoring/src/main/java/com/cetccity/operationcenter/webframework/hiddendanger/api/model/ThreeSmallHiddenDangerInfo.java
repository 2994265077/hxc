package com.cetccity.operationcenter.webframework.hiddendanger.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.api.model
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2019/2/22 10:06
 * @Updater: huangzezhou
 * @Update_Date: 2019/2/22 10:06
 * @Update_Description: huangzezhou 补充
 * @Description: 三小场所隐患信息
 **/
@Data
public class ThreeSmallHiddenDangerInfo {

    @ApiModelProperty("落图编号")
    String id;

    @ApiModelProperty("图层编号")
    String layerid;

    @ApiModelProperty("三小场所类型编号")
    String place_type;

    @ApiModelProperty("场所名称")
    String name;


    @ApiModelProperty("事件描述")
    String eventContent;

    @ApiModelProperty("发生时间")
    Date eventTime;

    @ApiModelProperty("三小场所类型")
    String place_name;

//    状态(0、受理；1、分拨；2、办结；3、已评价；9、归档(字典项)；50、挂起；80、作废；)
    @ApiModelProperty("事件状态")
    String state;

    @ApiModelProperty("巡查图片")
    String picture;

    @ApiModelProperty("事件编号")
    String systemId;

    public static String stateDic(String state){
        if ("0".equals(state))
            return "受理";
        else if ("1".equals(state))
            return "分拨";
        else if ("2".equals(state))
            return "办结";
        else if ("3".equals(state))
            return "已评价";
        else if ("9".equals(state))
            return "归档";
        else if ("50".equals(state))
            return "挂起";
        else if ("80".equals(state))
            return "作废";
        return null;
    }

}
