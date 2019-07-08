package com.cetccity.operationcenter.webframework.urbansign.api;

import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueUnitModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.XYAxisData;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.Tbl_pojo_futianApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Api(tags = "城市体征--人口基本面")
@RequestMapping("/urbansign")
@CacheConfig(cacheNames = "urbanPopulationBasicApi")
public interface UrbanPopulationBasicApi {


    @ApiOperation(value = "人口基本面--户籍人口、流动人口", notes = "人口基本面--户籍人口、流动人口-表名(BLK_POPULATION)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/leftOne",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'leftOne'+#street + '_' + #community")
    List<NameValueUnitModel> leftOne(String street, String community) throws IOException;

    @ApiOperation(value = "人口基本面--户籍人口、流动人口", notes = "人口基本面--户籍人口、流动人口-表名(BLK_POPULATION)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/leftTwo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'leftTwo'+#street")
    XYAxisData leftTwo(String street);

    @ApiOperation(value = "人口基本面--户籍人口数量--时间", notes = "人口基本面--户籍人口数量-表名(BLK_POPULATION-CJRQ创建日期)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightOne",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightOne'+#street")
    HttpResponseModel<ChartDetailModel> rightOne(String street);

    @ApiOperation(value = "人口基本面--外来人口分布Top-10", notes = "人口基本面--外来人口分布-表名(BLK_POPULATION)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightTwo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightTwo'+#street + '_' + #community")
    NameDataModel rightTwo(String street, String community);

    @ApiOperation(value = "人口基本面--年龄结构金字塔", notes = "人口基本面--年龄结构金字塔-表名(BLK_POPULATION)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightThree",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightThree'+#street")
    NameDataModel rightThree(String street);

    @ApiOperation(value = "人口基本面--近五年人才引进情况", notes = "人口基本面--近五年人才引进情况-表名(TBL_MXSYS_FUTIAN)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightFour",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightFour'+#street")
    NameDataModel rightFour(String street);

    @ApiOperation(value = "人口基本面--人才补贴籍贯地", notes = "人口基本面--人才补贴籍贯-表名(TBL_MXSYS_FUTIAN)")
    @RequestMapping(value = "/population/basic/rightFive",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightFive'+#street + '_' + #community")
    NameDataModel rightFive(String street, String community);

    /*@ApiOperation(value = "人口基本面--人员通勤情况<未开发>", notes = "人口基本面--人员通勤情况-表名(BLK_POPULATION)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightFive",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel rightFive(String street, String community);*/


    @ApiOperation(value = "人口基本面--人才补贴发放金额", notes = "人口基本面--人才补贴发放金额-表名(TBL_MXSYS_FUTIAN)")
    @RequestMapping(value = "/population/basic/rightSix",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightSix'+#street")
    NameDataModel rightSix(String street);

    @ApiOperation(value = "人口基本面--失业人口走势", notes = "人口基本面--失业人口走势-表名(BLK_POPULATION)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightSeven",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightSeven'+#street + '_' + #community")
    NameDataModel rightSeven(String street, String community);

    @ApiOperation(value = "人口基本面--引进人口学历构成", notes = "人口基本面--引进人口学历构成-表名(TBL_MXSYS_FUTIAN)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightEight",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightEight'+#street + '_' + #community")
    LinkedHashMap rightEight(String street, String community);

    @ApiOperation(value = "人口基本面--残疾人群数量及增长率", notes = "人口基本面--残疾人群数量及增长率-表名(BLK_POPULATION)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightNight",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightNight'+#street + '_' + #community")
    NameDataModel rightNight(String street, String community);

    @ApiOperation(value = "人口基本面--老年人口数量及增长率", notes = "人口基本面--老年人口数量及增长率-表名(BLK_POPULATION)--年龄大于60岁")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightTen",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightTen'+#street")
    NameDataModel rightTen(String street);

    @ApiOperation(value = "人口基本面--儿童数量--按街道统计", notes = "人口基本面--儿童数量-表名(BLK_POPULATION-年龄<=17（由CSRQ计算）)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightEleven",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightEleven'+#street + '_' + #community")
    NameDataModel rightEleven(String street, String community);

    @ApiOperation(value = "人口基本面--孕妇数量及增长率<未开发>", notes = "人口基本面--孕妇数量及增长率-表名(BLK_POPULATION)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "社区--滨江(可不填)", name = "community", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightTwelve",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightTwelve'+#street + '_' + #community")
    NameDataModel rightTwelve(String street, String community);

    @ApiOperation(value = "人口基本面--人才补贴api", notes = "人口基本面--人才补贴api")
    @RequestMapping(value = "/population/labour/pool/api",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'labourPool'")
    List<Tbl_pojo_futianApi> labourPool();


    @ApiOperation(value = "人口基本面--详细人口", notes = "人口基本面--详细人口")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "类型1-关爱人群、2-优抚人群、3-优待人群、4-关注人群、5-管控人群", name = "type", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightThirteen",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    @Cacheable(key = "'rightThirteen'+#street+#type")
    HttpResponseModel<ChartDetailModel> rightThirteen(String street, String type);

    @ApiOperation(value = "人口基本面--详细人口-按街道按社区显示", notes = "人口基本面--详细人口-按街道按社区显示")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "街道--南园街道(可不填)", name = "street", dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "入参", name = "name", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/population/basic/rightThirteen/drill",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel<ChartDetailModel> rightThirteenDrillDown(String street, String name);






}
