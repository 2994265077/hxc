package com.cetccity.operationcenter.webframework.backstage.interfaces.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysService;
import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysServiceInfo;
import com.cetccity.operationcenter.webframework.backstage.interfaces.service.SysServiceService;
import com.cetccity.operationcenter.webframework.backstage.interfaces.vo.SysServiceInfoVo;
import com.cetccity.operationcenter.webframework.backstage.interfaces.vo.SysServiceVo;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.UserInfoUtils;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.cetccity.operationcenter.webframework.web.util.PageResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ZHUTONGYU
 * Description: 接口管理
 * 2019年5月8日
 *
 */
@RestController
@Api(tags = "接口管理")
@RequestMapping("/service")
@Slf4j
public class SysServiceController {
    @Autowired
    private SysServiceService sysServiceService;
    @Autowired
    private UserInfoUtils userInfoUtils;
    private ObjectMapper objectMapper = new ObjectMapper();


    @ApiOperation(value = "保存服务概览")
    @PostMapping("/saveOrUpdateServiceInfo")
    public ResponseEntity<Long> saveOrUpdateServiceInfo(@RequestBody SysServiceInfo sysServiceInfo, HttpServletRequest request) throws Exception{
    	sysServiceInfo.setCommonField(userInfoUtils.getUserId(request));
    	log.info("SysServiceController|saveOrUpdateServiceInfo|num:{}|input:{}",objectMapper.writeValueAsString(sysServiceInfo) , "" );
    	userInfoUtils.getUserInfo(request);
        return new ResponseEntity<Long>(sysServiceService.saveOrUpdateServiceInfo(sysServiceInfo), HttpStatus.OK);
    }

    @ApiOperation(value = "保存服务配置")
    @PostMapping("/saveOrUpdateService")
    public ResponseEntity<SysServiceVo>  saveOrUpdateService(@RequestBody SysServiceVo sysServiceView){
        return new ResponseEntity<SysServiceVo>(sysServiceService.saveOrUpdateService(sysServiceView), HttpStatus.OK);
    }

    @ApiOperation(value = "删除服务")
    @PostMapping("/deleteService")
    public ResponseEntity<Integer> deleteService(@RequestBody SysServiceInfo sysServiceInfo){
        return new ResponseEntity<Integer>(sysServiceService.deleteService(sysServiceInfo.getServiceId()), HttpStatus.OK);
    }

    @ApiOperation(value = "修改服务状态")
    @PostMapping("/updateServiceStatus")
    public ResponseEntity<Integer> updateServiceStatus(@RequestBody SysService sysService){
        return new ResponseEntity<Integer>(sysServiceService.updateServiceStatus(sysService.getServiceId(),sysService.getStatus()), HttpStatus.OK);
    }

    @ApiOperation(value = "服务分页查询")
    @PostMapping("/findServiceByPage")
    public ResponseEntity<PageResult<SysServiceInfoVo>> findServiceByPage(@RequestBody PageParam<SysServiceInfoVo> param){
        return new ResponseEntity<PageResult<SysServiceInfoVo>>(sysServiceService.findServiceByPage(param), HttpStatus.OK);
    }
}
