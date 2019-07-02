package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.List;

/**
 * 城市基本面
 * 居住建筑数量	统计值：总数	BLK_BUILDING.SJYT=2
 商业建筑数量	统计值：总数	BLK_BUILDING.SJYT=3
 三小场所数量(替换“棚旧改项目数量”）	统计值：总数	BLK_SANXIAO_PLACE
 各街道-楼栋数量	分街道统计：街道、楼栋数量	BLK_BUILDING.STREET_CODE
 ----------------------------------------------------------------
 居住/商业 建筑层高类别统计	分层高类型统计：居住建筑层高类型，建筑数量	BLK_BUILDING.JZLX	1:超高层楼宇2:高层建宇3:中层楼宇4:小高层楼宇5:多层建筑
 居住/商业 建筑面积历史走势	分年份统计： 横轴：房屋用途 纵轴：各用途下的总面积	建筑面积BLK_BUILDING.JZMJ（横轴：SJYT设计用途）
 使用用途（替换房屋用途）	占比统计（饼图）	BLK_BUILDING.SJYT
 使用情况（替换使用属性）	占比统计（饼图）	BLK_BUILDING.SYQK

 */
@Api(value = "城市体征--楼栋基本面", tags = "城市体征--楼栋基本面")
@RequestMapping("/urbansign")
public interface UrbanBuildingBasicApi {

    @ApiOperation(value = "楼栋基本面--建筑--居住建筑数量", notes = "楼栋基本面--建筑--居住建筑数量-表名(BLK_BUILDING.SJYT=2)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/buildBasic/leftOne",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameValueModel leftOne(String street);

    @ApiOperation(value = "楼栋基本面--建筑--商业建筑数量", notes = "楼栋基本面--建筑--商业建筑数量-表名(BLK_BUILDING.SJYT=3)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/buildBasic/leftTwo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameValueModel leftTwo(String street);


    @ApiOperation(value = "楼栋基本面--建筑--棚旧改项目数量", notes = "楼栋基本面--建筑--棚旧改项目数量-表名(BLK_SANXIAO_PLACE)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/buildBasic/leftThree",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameValueModel leftThree(String street);


    @ApiOperation(value = "楼栋基本面--建筑--各街道-楼栋数量", notes = "楼栋基本面--建筑--各街道-楼栋数量-表名(BLK_BUILDING.STREET_CODE)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/buildBasic/leftFour",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> leftFour(String street);


    @ApiOperation(value = "楼栋基本面--建筑--居住/商业 建筑层高类别统计", notes = "楼栋基本面--建筑--居住/商业 建筑层高类别统计-表名(BLK_BUILDING.JZLX  1:超高层楼宇2:高层建宇3:中层楼宇4:小高层楼宇5:多层建筑)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/buildBasic/rightOne",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameDataModel> rightOne(String street);

    @ApiOperation(value = "楼栋基本面--建筑--居住/商业 SJYT设计用途的建筑面积", notes = "楼栋基本面--建筑--居住/商业 SJYT设计用途的建筑面积-表名(BLK_BUILDING.JZMJ)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/buildBasic/rightTwo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> rightTwo(String street);

    @ApiOperation(value = "楼栋基本面--建筑--使用用途占比统计（饼图）", notes = "楼栋基本面--建筑--使用用途占比统计（饼图）-表名(BLK_BUILDING.SJYT)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/buildBasic/rightThree",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> rightThree(String street);

    @ApiOperation(value = "楼栋基本面--房屋--使用情况占比统计（饼图）", notes = "楼栋基本面--房屋--使用情况占比统计（饼图）-表名(BLK_HOUSE.SYQK)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(440304001可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/buildBasic/rightFour",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    List<NameValueModel> rightFour(String street);

}
