package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.alibaba.fastjson.JSON;
import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotEventMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.IotMonitoringDataEncodeMapper;
import com.cetccity.operationcenter.webframework.web.model.incident.Iot_Data_Code_Value;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TheadTbodyModel;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.IOT_EVENT;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.IOT_MONITORING_DATA_ENCODE;
import com.cetccity.operationcenter.webframework.hiddendanger.service.IotDetailsService;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("iotDetailsService")
public class IotDetailsServiceImpl implements IotDetailsService {
    @Autowired
	private CommonInstance commonInstance;
    Logger logger = LoggerFactory.getLogger(IotDetailsServiceImpl.class);

    @Autowired
    IotMonitoringDataEncodeMapper iOT_MONITORING_DATA_ENCODEMapper;

    @Autowired
    IotEventMapper iOT_EVENTMapper;

    public List<LinkedHashMap> findIndexDetectionDetails(String device_code){
        List<LinkedHashMap> map_list = new ArrayList<LinkedHashMap>();
        String urlPath = commonInstance.getIotdataQueryUrl();
        String Json = "{\n" +
                "  \"device_code\":\""+device_code+"\"\n" +
                "}";
        String header[] = {"token", "sys_id"};
        String headerValue[] = {"c94c0cea596342ae9d39fe06cc0660f9", "220"};
        List<NameValueModel> httpURLConnection_list = new ArrayList<NameValueModel>();
        for (int i = 0 ;i < header.length; i++) {
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(header[i]);
            nameValueModel.setValue(headerValue[i]);
            httpURLConnection_list.add(nameValueModel);*/
            httpURLConnection_list.add(NameValueModel.builder().name(header[i]).value(headerValue[i]).build());
        }
        String source = UrlApiToSource.doJsonPost(urlPath,httpURLConnection_list,Json);
        if("".equals(source)) {
                LinkedHashMap map = new LinkedHashMap();
                map.put("device_code", device_code);
                map.put("DataName", "");
                map.put("ENCODE_DATA", "暂无数据");
                map.put("Value", "暂无数据");
                map.put("unit", "");
                map.put("alarm", "0");
                map_list.add(map);
        }else {
            Map source_return = (Map) JSON.parse(source);
            String realData_json = source_return.get("realData").toString();
            Map map_realData = (Map) JSON.parse(realData_json);
            String datas = map_realData.get("datas").toString();
            if("[]".equals(datas)){
                LinkedHashMap map = new LinkedHashMap();
                map.put("device_code", device_code);
                map.put("DataName", "");
                map.put("ENCODE_DATA", "暂无数据");
                map.put("Value", "暂无数据");
                map.put("unit", "");
                map.put("alarm", "0");
                map_list.add(map);
            }else {
                List<Iot_Data_Code_Value> iot_Data_Code_Value_list = JsonUtils.jsonToList(datas, Iot_Data_Code_Value.class);
                for (Iot_Data_Code_Value iot_Data_Code_Value : iot_Data_Code_Value_list) {
                    IOT_MONITORING_DATA_ENCODE iOT_MONITORING_DATA_ENCODE = new IOT_MONITORING_DATA_ENCODE();
                    iOT_MONITORING_DATA_ENCODE.setENCODE_DATA(iot_Data_Code_Value.getData_code());
                    List<IOT_MONITORING_DATA_ENCODE> iOT_MONITORING_DATA_ENCODE_list = iOT_MONITORING_DATA_ENCODEMapper.getIOT_MONITORING_DATA_ENCODE(iOT_MONITORING_DATA_ENCODE);
                    //今天是否报警
                    Calendar cal = Calendar.getInstance();
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
                    IOT_EVENT iOT_EVENT = new IOT_EVENT();
                    iOT_EVENT.setPRODUCE_TIME(date);
                    iOT_EVENT.setDEVICE_CODE(device_code);
                    iOT_EVENT.setDATA_CODE(iot_Data_Code_Value.getData_code());
                    List<IOT_EVENT> iOT_EVENT_list = iOT_EVENTMapper.getIOT_EVENT(iOT_EVENT);
                    boolean alarm;
                    alarm = iOT_EVENT_list.size() != 0;
                    LinkedHashMap map = new LinkedHashMap();
                    map.put("device_code", device_code);
                    map.put("DataName", iOT_MONITORING_DATA_ENCODE_list.get(0).getDATA_NAME());
                    map.put("ENCODE_DATA", iot_Data_Code_Value.getData_code());
                    map.put("Value", iot_Data_Code_Value.getData_value());
                    map.put("unit", iOT_MONITORING_DATA_ENCODE_list.get(0).getUNIT());
                    map.put("alarm", alarm);
                    map_list.add(map);
                }
            }
        }
        return map_list;
    }

    public MyPageInfoModel findIndexDetectionDetailsPageInfo(Integer pageNum, Integer pageSize,String device_code,String alarm_type_code){
        PageHelper.startPage(pageNum, pageSize);
        String current_time = LoadMyUtil.getMyTime("DATE",0);
        IOT_EVENT iOT_EVENT = new IOT_EVENT();
        iOT_EVENT.setEVENT_CODE(alarm_type_code);
        iOT_EVENT.setDEVICE_CODE(device_code);
        iOT_EVENT.setPROCESS_TIME(current_time);
        List<IOT_EVENT> iOT_EVENT_list = iOT_EVENTMapper.getIOT_EVENT(iOT_EVENT);
        PageInfo<IOT_EVENT> pageInfo = new PageInfo(iOT_EVENT_list);

        String top[] = {"时间","设备编码","监测名称","设备类型","事件名称","事件类型","事件等级","状态","处置环节"};
        List<String[]> list = new ArrayList();
        for (IOT_EVENT iOT_EVENT_return:iOT_EVENT_list) {
            String tbody[] = {iOT_EVENT_return.getPRODUCE_TIME(),iOT_EVENT_return.getDEVICE_CODE(),
                    iOT_EVENT_return.getEVENT_NAME(),iOT_EVENT_return.getEVENT_NAME(),
                    iOT_EVENT_return.getEVENT_NAME(),iOT_EVENT_return.getEVENT_NAME(),
                    iOT_EVENT_return.getEVENT_LEVEL(),iOT_EVENT_return.getEVENT_STATE(),iOT_EVENT_return.getEVENT_LEVEL()
            };
            list.add(tbody);
        }
        TheadTbodyModel theadTbodyModel = new TheadTbodyModel();
        theadTbodyModel.setThead(top);
        theadTbodyModel.setTbody(list);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setList(theadTbodyModel);
        return pageInfo_return;
    }
}
