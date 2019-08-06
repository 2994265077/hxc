package com.cetccity.operationcenter.webframework.alarmcenter.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.IconTypeLoadMap;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;
import java.util.List;

@Api(tags = "预警中心--落图")
@RequestMapping("/alarmcenter")
public interface AlarmLoadMapApi {

    @ApiOperation(value = "今日预警--预警类型落图", notes = "今日预警--预警类型落图--表名ALARM_INFORMATION  \n"+
            "001001         气象预警  \n"+
            "001002         地质灾害报警  \n"+
            "001003         积涝点报警  \n"+
            "002001         三小场所-隐患巡查  \n"+
            "002002         危险源巡查  \n"+
            "002003         危化品气体报警  \n"+
            "002004         剩余电流  \n"+
            "003001         烟感报警  \n"+
            "003002         电器火灾报警  \n"+
            "003003         消防栓水压报警  \n"+
            "003004         建筑消防风险报警  \n"+
            "004001         劳资纠纷报警  \n"+
            "004002         企业外迁报警  \n"+
            "004003         金融预警  \n"+
            "005001         水环境报警  \n"+
            "005002         空气质量报警  \n"+
            "006001         应急突发事件  \n"+
            "006002         信访事件  \n"+
            "007001         发热  \n"+
            "007002         腹泻  \n"+
            "007003         外伤  \n"+
            "007004         流感  \n"+
            "003005         防火门传感器")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "预警编号--alarm_code-004001", name = "alarm_code", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "时间--date-2018-11-07", name = "date", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "开始时间--startTime-2018-12-07", name = "startTime", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "结束时间--endTime-2019-12-07", name = "endTime", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "{alarm_code}/selectByExample",method = RequestMethod.GET)
    List<LoadMap> alarmLoadMapLV2(@PathVariable("alarm_code") String alarm_code, String street, String date,String id,String startTime,String endTime, String type, String level)throws IOException;

    @ApiOperation(value = "今日预警--预警类型落图", notes = "今日预警--预警类型落图--表名ALARM_INFORMATION 危险源巡查 002002")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "时间--date-2018-11-07", name = "date", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "开始时间--startTime-2018-12-07", name = "startTime", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "结束时间--endTime-2019-12-07", name = "endTime", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "002002/selectByExample",method = RequestMethod.GET)
    List<IconTypeLoadMap> alarmLoadMap002002(String street, String date, String id, String startTime, String endTime, String type, String level)throws IOException;

    @ApiOperation(value = "今日预警--预警类型落图", notes = "今日预警--预警类型落图--表名ALARM_INFORMATION  \n"+
            "001	社会安全事件  \n"+
            "002	安监预警  \n"+
            "003	三防预警  \n"+
            "004	消防预警  \n"+
            "005	重点企业预警  \n"+
            "006	城市生命线  \n"+
            "007	生态环境预警  \n"+
            "008	气象预警  \n"+
            "009	卫计预警  \n"+
            "010	舆情预警  \n"+
            "011	地质预警  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "预警编号--alarm_code-001002", name = "alarm_code", dataType = "string", paramType = "path", required = true)
    })
    @RequestMapping(value = "{alarm_code}/leftTwo/selectByExample",method = RequestMethod.GET)
    List<LoadMap> alarmLoadMapLeftTwo(@PathVariable("alarm_code") String alarm_code,String date, String type, String level)throws IOException;


}
