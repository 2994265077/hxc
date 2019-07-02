package com.cetccity.operationcenter.webframework.alarmcenter.api;

import com.cetccity.operationcenter.webframework.web.model.incident.AlarmTodayType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(tags = "预警中心--分页")
@RequestMapping("/alarmcenter")
public interface AlarmPageInfoApi {

    @ApiOperation(value = "今日预警--预警类型列表", notes = "今日预警--预警类型列表--表名ALARM_INFORMATION  \n"+
            "事件预警	    001  \n" +
            "安监预警	    002  \n" +
            "三防预警	    003  \n" +
            "消防预警	    004  \n" +
            "重点企业预警	005  \n" +
            "城市生命线	006  \n" +
            "生态环境预警	007  \n" +
            "气象预警	    008  \n" +
            "卫计预警	    009  \n" +
            "舆情预警	    010  \n" +
            "地质预警	    011  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "预警编号--alarm_code", name = "alarm_code", dataType = "string", paramType = "path", required = true)
    })
    @RequestMapping(value = "/{alarm_code}/left/three",method = RequestMethod.GET)
    List<AlarmTodayType> LeftThree(@PathVariable("alarm_code") String alarm_code,String date);
}
