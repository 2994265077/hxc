package com.cetccity.operationcenter.webframework.web.controller.incident;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.CetcCloudResult;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotEventMapper;
import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.cetccity.operationcenter.webframework.web.dao.iot.IotSensordataMapper;
import com.cetccity.operationcenter.webframework.web.model.iot.*;
import com.cetccity.operationcenter.webframework.web.service.IOT_DEVICEService;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateIoTService;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.cetccity.operationcenter.webframework.web.util.UuIdGeneratorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.*;

@Controller
@RequestMapping(value = "/usp")
@Api(tags = "物联网推送接收服务")
@Slf4j
public class IotPushController {
    Logger logger = LoggerFactory.getLogger(IotPushController.class);

    @Autowired
    OracleOperateIoTService oracleOperateIoTService ;

    @Autowired
    IotEventMapper iotEventMapper ;

    @Autowired
    IotSensordataMapper iotSensordataMapper ;

    @Autowired
    IOT_DEVICEService iOT_DEVICEService;
    
    @Autowired
    private CommonInstance commonInstance;

    @ApiOperation(value = "更新物联网设备（落图IOT_DEVICE）", notes = "更新物联网设备（" +
            "消防栓水压-0025、烟雾传感器-0003、易涝点-0026、电气火灾设备-0024、" +
            "危化品（气体传感器）-0021、地质灾害监测设备-0023）", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_type", value = "物联网设备编号", paramType = "query")
    })
    @RequestMapping(value = "/update/device", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @Transactional
    public CetcCloudResult getIOT_DEVICE(String device_type) {
        CetcCloudResult cetcCloudResult = new CetcCloudResult();
        String urlPath = commonInstance.getIotDeviceListUrl();
        String Json = "{\n" +
                "\"device_type\":\""+device_type+"\"\n" +
                "}";
        String header[] = {"token","sys_id"};
        String headerValue[] = {"c94c0cea596342ae9d39fe06cc0660f9","220"};
        List<NameValueModel> httpURLConnection_list = new ArrayList<NameValueModel>();
        for (int i=0;i<header.length;i++) {
            httpURLConnection_list.add(NameValueModel.builder().name(header[i]).value(headerValue[i]).build());
        }
        String source = UrlApiToSource.doJsonPost(urlPath,httpURLConnection_list,Json);
        int count =0;
        Map<String,Object> map_source = (Map)JSON.parse(source);
        if("0".equals(map_source.get("result").toString())){
            List<IOT_DEVICE_PUSH> IOT_DEVICE_PUSH_list = JsonUtils.jsonToList(map_source.get("devices").toString(), IOT_DEVICE_PUSH.class);
            //删除device_code类型的传感器
            iOT_DEVICEService.deleteIOT_DEVICE(IOT_DEVICE.builder().DEVICE_TYPE(device_type).build());
            for (IOT_DEVICE_PUSH iOT_DEVICE_PUSH : IOT_DEVICE_PUSH_list) {
                count++;
                /*//1、检查device_code是否已存在
                IOT_DEVICE iOT_DEVICE = new IOT_DEVICE();
                iOT_DEVICE.setDEVICE_CODE(iOT_DEVICE_PUSH.getDevice_code());
                List<IOT_DEVICE> iOT_DEVICE_list = iOT_DEVICEService.getIOT_DEVICE_OBJ(iOT_DEVICE);*/
                //2、新增传感器
                //获取device_code
                String device_code = iOT_DEVICE_PUSH.getDevice_code();
                //获取设备名称
                String device_name = iOT_DEVICE_PUSH.getDevice_name();
                //获取设备经纬度
                List<LAT_LON_TYPE> location = iOT_DEVICE_PUSH.getLocation();
                //获取设备地址
                List<ADDRESS_INFO> address_info = iOT_DEVICE_PUSH.getAddress_info();
                int s = iOT_DEVICEService.insertIOT_DEVICE(IOT_DEVICE.builder().ID(UuIdGeneratorUtil.getCetcCloudUuid("device_type"))
                        .NAME(device_name).DEVICE_CODE(device_code)
                        .JD84(location.get(1).getLongitude()).WD84(location.get(1).getLatitude()).ADDRESS(address_info.get(0).getAddress())
                        .UUID(UuIdGeneratorUtil.getCetcCloudUuid("device_type"))
                        .DEVICE_TYPE(device_type).build());
                cetcCloudResult.setCode(0);
                cetcCloudResult.setMsg(count+"条数据更新正常");
                cetcCloudResult.setData(count+"条数据更新正常");
            }
        }else{
            cetcCloudResult.setCode(201);
            cetcCloudResult.setMsg("无法获取设备列表");
            cetcCloudResult.setData("无法获取设备列表");
        }
        return cetcCloudResult;
    }

    @ApiOperation(value = "接收实时报警数据", notes = "接收实时报警数据", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "alarm", value = "传感器实时报警数据", paramType = "query")
    })
    @RequestMapping(value = "/data/alarmReport", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject receiveAlarmPush(@RequestBody HashMap map) {
        String alarm = JSONObject.toJSONString(map);
        JSONObject result = new JSONObject();
        JSONObject error = new JSONObject();
        logger.info("------alarm------"+alarm);
        JSONArray  alarmReport;
        String device_code,event_name,event_level,event_code,datas,paras,data_count,para_count;
        try {
            result= JSONObject.parseObject(alarm); //将str转化为相应的JSONObject对象
            error.put("result", "empty response:alarm push none json format");
            alarmReport = result.getJSONArray("alarmReport");
            error.put("result", "empty response:alarm push  Not JsonArray");
            Iterator<Object> iterator = alarmReport.iterator();
            while (iterator.hasNext()) {
                JSONObject next = (JSONObject) iterator.next();
                device_code=next.getString("device_code");
                event_name=next.getString("event_name");
                event_level=next.getString("event_level");
                event_level=eventlevelFunction(event_level);
                event_code=next.getString("event_code");
                data_count=next.getString("data_count");
                if(Integer.parseInt(data_count)>0) {
                    JSONArray dataArray = next.getJSONArray("datas");
                    Iterator<Object> iteratordata = dataArray.iterator();
                    while (iteratordata.hasNext()) {
                        JSONObject next1 = (JSONObject) iteratordata.next();
                        String data_code = next1.getString("data_code") ;
                        String data_value = next1.getString("data_value") ;
                        //这里保存数据
                        IotEventModelWithBLOBs iotEventModelWithBLOBs = new IotEventModelWithBLOBs()  ;
                        Date nowdate = new Date(System.currentTimeMillis());
                        iotEventModelWithBLOBs.setProduceTime(nowdate);
                        iotEventModelWithBLOBs.setEventCode(event_code);
                        iotEventModelWithBLOBs.setDeviceCode(device_code);
                        iotEventModelWithBLOBs.setEventName(event_name);
                        iotEventModelWithBLOBs.setEventLevel(event_level);
                        iotEventModelWithBLOBs.setDataCode(data_code);
                        iotEventModelWithBLOBs.setDataValue(data_value);
                        iotEventMapper.insertSelective(iotEventModelWithBLOBs);
                    }
                }
                para_count=next.getString("para_count");
                if(Integer.parseInt(para_count)>0) {
                    JSONArray paraArray = next.getJSONArray("paras");
                    Iterator<Object> iteratorpara = paraArray.iterator();
                    while (iteratorpara.hasNext()) {
                        JSONObject next2 = (JSONObject) iteratorpara.next();
                        String para_code = next2.getString("data_code") ;
                        String para_value = next2.getString("data_value") ;
                        //这里保存数据
                    }
                }
            }
        } catch (Exception e) {
        	log.error(e.toString());
            return error;
        }
        if (null == alarmReport) {
            return result;
        }
        result.put("result", 0); //0代表成功，其他代表失败
        return result;
    }

    public String eventlevelFunction(String event_level){
        String temp =" ";
        if (event_level.equals("2")){
            temp="紧急" ;
        }
        else if(event_level.equals("1")){
            temp="重要";
        }
        else if(event_level.equals("0")){
            temp="一般";
        }
        return  temp ;
    }
}
