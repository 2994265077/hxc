package com.cetccity.operationcenter.webframework.hiddendanger.api.detail;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.SanXiaoTip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "安全隐患一张图--三小场所详情")
@RequestMapping("/hiddendanger")
public interface BLK_SANXIAO_PLACEDetailsApi {

    @ApiOperation(value = "三小场所弹框", notes = "三小场所详情 表名BLK_SANXIAO_PLACE")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "主键id=493EC789E2A5C730E0530142BE0AE8C9 ", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/BLK_SANXIAO_PLACE@60021/IconType/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    SanXiaoTip findBLK_SANXIAO_PLACETipOne(String id) throws Exception;

    @ApiOperation(value = "三小场所弹框", notes = "三小场所详情 表名BLK_SANXIAO_PLACE")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "主键id=493EC789E2A5C730E0530142BE0AE8C9 ", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/BLK_SANXIAO_PLACE@60022/IconType/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    SanXiaoTip findBLK_SANXIAO_PLACETipTwo(String id) throws Exception;

    @ApiOperation(value = "三小场所弹框", notes = "三小场所详情 表名BLK_SANXIAO_PLACE")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "主键id=493EC789E2A5C730E0530142BE0AE8C9 ", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/BLK_SANXIAO_PLACE@60023/IconType/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    SanXiaoTip findBLK_SANXIAO_PLACETipThree(String id) throws Exception;

    @ApiOperation(value = "三小场所详情", notes = "三小场所详情 表名BLK_SANXIAO_PLACE")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "主键id=ylE50F85EF4332440EABB639BBFE5BDFEA", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/sanxiao/placeDetails",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel findBLK_SANXIAO_PLACEDetails(String id) throws Exception;

    @ApiOperation(value = "三小场所弹框", notes = "三小场所详情 表名BLK_SANXIAO_PLACE")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "主键id=493EC789E2A5C730E0530142BE0AE8C9 ", name = "id", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value = "/sanxiao/placeScore",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameDataModel findBLK_SANXIAO_PLACEScore(String id) throws Exception;
}
