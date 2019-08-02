package com.cetccity.operationcenter.webframework.hiddendanger.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.BarOrLineModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.ThreeSmallHiddenDangerInfo;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.hiddendanger
 * @Project: 31project-Apr4
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/20 10:58
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/20 10:58
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/
@Api(tags = "安全隐患一张图--全区隐患--图表")
@RequestMapping("/hiddendanger")
public interface AllRegionHiddenDangerChartApi {

    /**
     * 全区隐患--饼图
     * @return
     */
    @ApiOperation(value = "安全隐患一张图--全区隐患--饼图", notes = "安全隐患一张图--全区隐患--饼图")
    @RequestMapping(value = "/allrigionhiddendangerchart/pie", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<PieModel> pie(String street);

    /**
     * 全区隐患--条形图
     * @return
     */
    @ApiOperation(value = "安全隐患一张图--全区隐患--条形图", notes = "安全隐患一张图--全区隐患--条形图")
    @RequestMapping(value = "/allrigionhiddendangerchart/bar", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<List<BarOrLineModel>> bar(String street, String startTime, String endTime);

    /**
     * 全区隐患--列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageNum 页码
     * @param pageSize 行
     * @param placeType 类型
     * @return
     */
    @ApiOperation(value = "安全隐患一张图--全区隐患--列表", notes = "安全隐患一张图--全区隐患--列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "placeType--60021", name = "placeType", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/allrigionhiddendanger/hiddendanger/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<MyPageInfoModel<List<ThreeSmallHiddenDangerInfo>>> list(String startTime, String endTime, int pageNum, int pageSize, String placeType, String street);
}
