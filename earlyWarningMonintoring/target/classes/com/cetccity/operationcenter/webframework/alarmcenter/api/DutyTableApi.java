package com.cetccity.operationcenter.webframework.alarmcenter.api;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameTypeDataModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.api
 * 项目名称:   cetc-professional-capability
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 10:42 2019-03-14
 * Updater:     heliangming
 * Update_Date：10:42 2019-03-14
 * 更新描述:    heliangming 补充
 **/

/**
 *排班表
 */
@Api(tags = "预警中心--排班表")
@RequestMapping("/alarmcenter")
public interface DutyTableApi {

    @ApiImplicitParams({
            @ApiImplicitParam(value = "fileCode", name = "fileCode", dataType = "string", paramType = "path", required = true)
    })
    @RequestMapping(value="/isExist/{fileCode}/local", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    HttpResponseModel uploadImgLocalIsExist(@RequestParam("file") MultipartFile file, HttpServletRequest request, @PathVariable("fileCode") String fileCode);

    @ApiOperation(value = "排班表文件上传", notes =
            "建筑风险SDK-fileCode--SDK001  " +
                    "建筑风险PDF使用说明-fileCode--PDF001  "
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "fileCode", name = "fileCode", dataType = "string", paramType = "path", required = true)
    })
    @RequestMapping(value="/upload/{fileCode}/local", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    String uploadImgLocal(@RequestParam("file") MultipartFile file, HttpServletRequest request, @PathVariable("fileCode") String fileCode) throws IOException;

    @ApiOperation(value = "排班表文件下载", notes =
            "建筑风险SDK-fileCode--SDK001  " +
                    "建筑风险PDF使用说明-fileCode--PDF001  "
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "fileCode", name = "fileCode", dataType = "string", paramType = "path", required = true)
    })
    @RequestMapping(value="/download/{fileCode}/local", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    String downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileCode") String fileCode)throws IOException;

    @ApiImplicitParams({
            @ApiImplicitParam(value = "fileCode", name = "fileCode", dataType = "string", paramType = "path", required = true)
    })
    @RequestMapping(value="/delete/{fileCode}/local", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    String deleteFile(@PathVariable("fileCode") String fileCode);

    @ApiOperation(value = "排班表文件修改", notes =
            "排班表-fileCode--2019-03  "
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "fileCode", name = "fileCode", dataType = "string", paramType = "path", required = true)
    })
    @RequestMapping(value="/update/{fileCode}/local", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    String updateFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileCode") String fileCode)throws IOException;

    @ApiImplicitParams({
            @ApiImplicitParam(value = "2019-03", name = "fileCode", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value="/excel/to/db", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    String excelToDb(MultipartFile file,String fileCode);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "fileCode", name = "fileCode", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value="/db/to/excel", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    String dbToExcel(HttpServletResponse response,String fileCode);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "day", name = "day", dataType = "string", paramType = "query", required = true)
    })
    @RequestMapping(value="/find/duty", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    NameTypeDataModel findDuty(String day);

    @ApiOperation(value = "排班表模板下载", notes = "排班表模板下载"
    )
    @RequestMapping(value="/find/duty/template", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    String findDutyTemplate (HttpServletResponse response);
}
