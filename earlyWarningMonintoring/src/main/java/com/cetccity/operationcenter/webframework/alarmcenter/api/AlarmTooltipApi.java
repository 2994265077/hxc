package com.cetccity.operationcenter.webframework.alarmcenter.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Api(tags = "预警中心--弹框")
@RequestMapping("/alarmcenter")
public interface AlarmTooltipApi {

    @ApiOperation(value = "根据表名DB弹框", notes =
                    "三小事件预警	    001001  \n"+
                    "应急突发事件预警	001002  \n"+
                    "安监隐患事件预警	002001  \n"+
                    "城区风险预警	    002002  \n"+
                    "水库预警	        003001  \n"+
                    "河道预警	        003002  \n"+
                    "积劳点	        003003  \n"+
                    "烟感预警	        004001  \n"+
                    "建筑消防风险预警	004002  \n"+
                    "气体传感器	    004003  \n"+
                    "电器火灾监测设备	004004  \n"+
                    "消防栓水压传感器	004005  \n"+
                    "劳资纠纷预警	    005001  \n"+
                    "企业外迁预警	    005002  \n"+
                    "城市供水预警	    006001  \n"+
                    "城市供电预警	    006002  \n"+
                    "城市燃气供应预警	006003  \n"+
                    "空气质量预警	    007001  \n"+
                    "水环境预警	    007002  \n"+
                    "固废预警	        007003  \n"+
                    "噪声预警	        007004  \n"+
                    "高温预警	        008001  \n"+
                    "台风预警	        008002  \n"+
                    "雷暴预警	        008003  \n"+
                    "暴雨预警	        008004  \n"+
                    "大风预警	        008005  \n"+
                    "卫计预警	        009001  \n"+
                    "舆情预警	        010001  \n"+
                    "地质灾害监测预警	011001  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--ALARM_INFORMATION", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "主键--AAAUTNAAEAACOq3AAZ", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/{LV_2}/summaryInfo",method = RequestMethod.GET)
    @ResponseBody
    Map summaryInfo(@PathVariable("LV_2") String LV_2, String id);

}
