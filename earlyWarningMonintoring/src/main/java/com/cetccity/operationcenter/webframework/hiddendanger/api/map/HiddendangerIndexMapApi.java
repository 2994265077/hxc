/*
package com.cetccity.operationcenter.webframework.hiddendanger.api.map;

import com.cetccity.operationcenter.webframework.web.model.incident.NearbyResourcesModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.List;

@Api(tags = "安全隐患一张图--报警落图")
@RequestMapping("/hiddendanger")
public interface HiddendangerIndexMapApi {

    @ApiOperation(value = "根据表名DB落图", notes =
            "物联网-烟雾传感器--IOT_DEVICE@0003  \n"+
            "物联网-电气火灾监测设备--IOT_DEVICE@0024  \n"+
            "物联监测--气体传感器--IOT_DEVICE@0021  \n"+
            "物联监测--地质灾害监测设备--IOT_DEVICE@0023  \n"+
            "物联网-消防栓水压传感器--IOT_DEVICE@0025  \n"+
            "物联网-内涝监测设备--易涝点--水位传感器--IOT_DEVICE@0026  \n"+
            "安监-三小场所(小作坊PLACE_TYPE=60022)--BLK_SANXIAO_PLACE@60022  \n" +
            "安监-三小场所(小档口PLACE_TYPE=60021)--BLK_SANXIAO_PLACE@60021  \n" +
            "安监-三小场所(小娱乐场所PLACE_TYPE=60023)--BLK_SANXIAO_PLACE@60023  \n"
    )
    @RequestMapping(value = "/alarmcenter/IconType/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NearbyResourcesModel> findIconTypeByDB(String street) throws IOException;

}
*/
