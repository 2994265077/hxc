/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MapController
 * Author:   YHY
 * Date:     2019/6/3 13:34
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.map.controller;

import com.cetccity.operationcenter.webframework.common.map.entity.Group;
import com.cetccity.operationcenter.webframework.common.map.entity.Map;
import com.cetccity.operationcenter.webframework.common.map.service.MapService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/3
 * @since 1.0.0
 */
@RestController
@RequestMapping("/map")
@Api(tags = "后台接口--地图管理")
public class MapController {

    @Autowired
    private MapService mapService;

    @PostMapping("/")
    @ApiOperation("新增")
    public HttpResponseModel<Boolean> save(@RequestBody Group group) {
        return HttpResponseModel.defaultSuccess(mapService.save(group));
    }

    @PutMapping("/")
    @ApiOperation("修改")
    public HttpResponseModel<Boolean> update(@RequestBody Group group) {
        return HttpResponseModel.defaultSuccess(mapService.update(group));
    }

    @DeleteMapping("/{object_id}")
    @ApiOperation("根据主键删除")
    public HttpResponseModel<Boolean> delete(@PathVariable("object_id") String objectId) {
        return HttpResponseModel.defaultSuccess(mapService.delete(objectId));
    }

    @PostMapping("/file")
    @ApiOperation("从上传的文件中读取")
    public void fromFile(@RequestParam("file") MultipartFile multipartFile, boolean override) throws IOException {
        mapService.loadFromInputStream(multipartFile.getInputStream(), override);
    }

    @PostMapping("/file/class")
    @ApiOperation("从指定的类路径文件中读取")
    public void fromClassPath(String path, boolean override) {
        mapService.loadFromClassPath(path, override);
    }

    @PostMapping("/file/system")
    @ApiOperation("从指定的文件系统路径文件中读取")
    public void fromSystemPath(String path, boolean override) {
        mapService.loadFromFileSystem(path, override);
    }

    @GetMapping(value = "/tree", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("查询group树")
    public HttpResponseModel<List<Group>> queryTree() {
        return HttpResponseModel.defaultSuccess(mapService.groupsTree());
    }

    @GetMapping(value = "/map", produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation("获取地图图层xml")
    public Map queryMap() {
        return mapService.map();
    }
}