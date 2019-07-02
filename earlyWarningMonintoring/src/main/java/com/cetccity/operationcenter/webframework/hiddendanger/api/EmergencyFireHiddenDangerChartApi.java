package com.cetccity.operationcenter.webframework.hiddendanger.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TheadTbodyPlus;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger.api
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/19 20:41
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/19 20:41
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Api(tags = "安全隐患一张图--应急资源图表")
@RequestMapping("/hiddendanger")
public interface EmergencyFireHiddenDangerChartApi {

    @ApiOperation(value = "安全隐患一张图--应急资源图表--统计五项数据", notes = "安全隐患一张图--应急资源图表--统计五项数据：避难场所、救援队伍数、风险源、应急仓库数、应急物资数")
    @RequestMapping(value = "/emergencyhiddendangerchart/count5num",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<List<NameValueTypeModel>> count5Num(String street);

    @ApiOperation(value = "安全隐患一张图--应急资源图表-左侧饼图", notes = "安全隐患一张图--应急资源图表-左侧饼图")
    @RequestMapping(value = "/emergencyhiddendangerchart/leftpie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<PieModel> leftPie(String street);

    @ApiOperation(value = "安全隐患一张图--应急资源图表-右侧饼图", notes = "安全隐患一张图--应急资源图表-右侧饼图")
    @RequestMapping(value = "/emergencyhiddendangerchart/rightpie",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<PieModel> rightPie(String street);

    @ApiOperation(value = "安全隐患一张图--应急资源图表-表格", notes = "安全隐患一张图--应急资源图表-表格")
    @RequestMapping(value = "/emergencyhiddendangerchart/table",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<TheadTbodyPlus> table(String street);
}
