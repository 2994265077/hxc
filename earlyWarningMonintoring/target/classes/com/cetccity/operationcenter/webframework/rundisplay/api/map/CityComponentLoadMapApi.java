package com.cetccity.operationcenter.webframework.rundisplay.api.map;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.List;

@Api(tags = "安全隐患一张图--落图")
@RequestMapping("/rundisplay")
public interface CityComponentLoadMapApi {

    @ApiOperation(value = "视频落图--支持标签落图", notes = "视频--VIDEO_POLICE  \n")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "视频--VIDEO_POLICE", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "标签--name", name = "name", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/video/tag/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LoadMap> findVideoByType(@PathVariable("tableNameUrl") String tableNameUrl, String street,String name,String id);

    @ApiOperation(value = "根据表名DB落图", notes =
            "BJ0101=上水井盖  \n" +
            "BJ0102=污水井盖  \n" +
            "BJ0103=雨水井盖  \n" +
            "BJ0104=雨水箅子  \n" +
            "BJ0105=电力井盖  \n" +
            "BJ0106=路灯井盖  \n" +
            "BJ0107=通讯井盖  \n" +
            "BJ0108=电视井盖  \n" +
            "BJ0109=网络井盖  \n" +
            "BJ0111=燃气井盖  \n" +
            "BJ0112=公安井盖  \n" +
            "BJ0113=消防设施  \n" +
            "BJ0114=无主井盖  \n" +
            "BJ0115=通讯交接箱  \n" +
            "BJ0116=电力设施  \n" +
            "BJ0117=立杆  \n" +
            "BJ0118=路灯  \n" +
            "BJ0119=地灯  \n" +
            "BJ0120=景观灯  \n" +
            "BJ0121=报刊亭  \n" +
            "BJ0122=电话亭  \n" +
            "BJ0123=邮筒  \n" +
            "BJ0124=信息亭  \n" +
            "BJ0125=自动售货机  \n" +
            "BJ0126=健身设施  \n" +
            "BJ0127=中水井盖  \n" +
            "BJ0128=公交井盖  \n" +
            "BJ0129=输油(气)井盖  \n" +
            "BJ0130=特殊井盖  \n" +
            "BJ0131=民用水井  \n" +
            "BJ0132=供水器  \n" +
            "BJ0133=高压线铁塔  \n" +
            "BJ0134=变压器(箱)  \n" +
            "BJ0135=燃气调压站(箱)  \n" +
            "BJ0136=监控电子眼  \n" +
            "BJ0137=售货亭  \n" +
            "BJ0138=治安岗亭  \n" +
            "BJ0201=停车场  \n" +
            "BJ0203=公交站亭  \n" +
            "BJ0204=出租车站牌  \n" +
            "BJ0205=过街天桥  \n" +
            "BJ0206=地下通道  \n" +
            "BJ0207=高架立交桥  \n" +
            "BJ0208=跨河桥  \n" +
            "BJ0209=交通标志牌  \n" +
            "BJ0210=交通信号灯  \n" +
            "BJ0211=交通护栏  \n" +
            "BJ0212=存车支架  \n" +
            "BJ0213=路名牌  \n" +
            "BJ0214=交通信号设施  \n" +
            "BJ0215=道路信息显示屏  \n" +
            "BJ0216=道路隔音屏  \n" +
            "BJ0217=交通岗亭  \n" +
            "BJ0301=公共厕所  \n" +
            "BJ0302=化粪池  \n" +
            "BJ0303=公厕指示牌  \n" +
            "BJ0304=垃圾间(楼)  \n" +
            "BJ0305=垃圾箱  \n" +
            "BJ0306=灯箱霓虹灯  \n" +
            "BJ0307=广告牌匾  \n" +
            "BJ0308=环保监测站  \n" +
            "BJ0309=气象监测站  \n" +
            "BJ0310=污水口监测站  \n" +
            "BJ0401=古树名木  \n" +
            "BJ0402=行道树  \n" +
            "BJ0403=护树设施  \n" +
            "BJ0404=花架花钵  \n" +
            "BJ0405=绿地  \n" +
            "BJ0406=雕塑  \n" +
            "BJ0407=街头坐椅  \n" +
            "BJ0408=绿地护栏  \n" +
            "BJ0409=绿地维护设施  \n" +
            "BJ0410=喷泉  \n" +
            "BJ0501=宣传栏  \n" +
            "BJ0502=人防工事  \n" +
            "BJ0601=重大危险源  \n" +
            "BJ0602=工地  \n" +
            "BJ0603=水域附属设施  \n" +
            "BJ0604=水域护栏  \n" +
            "BJ0606=防洪墙  \n" +
            "BJ2101=防蚊闸  \n" +
            "BJ2102=路灯灯光变压器  \n" +
            "BJ2103=路灯灯光配电房  \n" +
            "BJ2104=路灯灯光配电箱  \n" +
            "BJ2105=游乐场  \n" +
            "BJ2106=提款机  \n" +
            "BJ2108=主干道  \n" +
            "BJ2109=次干道  \n" +
            "BJ2110=支路  \n" +
            "BJ2111=街坊路  \n" +
            "BJ2112=地铁站指示牌  \n" +
            "BJ2113=地铁通风口  \n" +
            "BJ2114=地铁出入口  \n" +
            "BJ2115=泵站  \n" +
            "BJ2116=洗车场  \n" +
            "BJ2117=挡墙  \n" +
            "BJ2118=环卫工具房  \n" +
            "BJ2121=森林防火宣传牌  \n" +
            "BJ2123=口岸  \n" +
            "BJ2124=加油站  \n" +
            "BJ2125=公厕垃圾站  \n" +
            "BJ2126=灭蚊灯  \n" +
            "BJ2127=河湖堤坝  \n" +
            "BJ2132=压力管道  \n" +
            "BJ2133=电梯  \n" +
            "BJ2136=特殊部件  \n" +
            "BJ2137=线状花架花钵  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--TB_ROAD_DISEASE", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "主键--id", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/CityComponent/{tableNameUrl}/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LoadMap> findObjectByDB(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id) throws IOException;

    @ApiOperation(value = "根据表名DB落图", notes =
            "YJJC_QWJJ_ORG_V=医疗卫生  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "表名--YJJC_QWJJ_ORG_V", name = "tableNameUrl", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(value = "街道--street", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "主键--id", name = "id", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/{tableNameUrl}/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<LoadMap> findHospitalByDB(@PathVariable("tableNameUrl") String tableNameUrl, String street, String id) throws IOException;
}
