package com.cetccity.operationcenter.webframework.web.controller.sanfang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.web.util.GeoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * Description：
 * Created by luolinjie on 2018/6/15.
 */
//@Api(tags  = "道路病害")
//@RestController
public class RoadDisease {

    private static Logger logger = LoggerFactory.getLogger(RoadDisease.class);
    @Autowired
    OracleOperateService oracleOperateService;
    /**
     病害编号   number
     病害类型   disease_type
     平面范围   plane_range
     距地面    depth
     风险等级   risk_grade
     图片URL： photo_url

     图片关联查询
     雷达图： 1张图片  radar_pic.jpg
     地图位置 1张图片  map_location.jpg
     现场位置 1张图片  scene_location.jpg
     现场图片 2张图片  scene_pic_1.jpg scene_pic_2.jpg
     位置描述 address_desc
     * @return
     */
    @ApiOperation(value = "获取道路病害详细信息",notes = "包含返回文字和图片路径", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "道路病害编号", required = true, dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "/road_disease/getDetail", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getDetail(String id) throws IOException {
        String tableName = "TB_ROAD_DISEASE";
        JSONObject result = new JSONObject();
        List _sourceList = new ArrayList();

        String sql = "SELECT ID,NUMBER,DISEASE_TYPE,ADDRESS_DESC,PLANE_RANGE,DEPTH,RISK_GRADE,PHOTO_URL " +
                "FROM  "+tableName+ " WHERE ID=\'"+id+"\'";

        List<LinkedHashMap> organizationList = oracleOperateService.querySql(sql);

        Iterator<LinkedHashMap> iterator = organizationList.iterator();
        HashMap resObj = new HashMap();
        while (iterator.hasNext()) {
            LinkedHashMap<String,String> source =  iterator.next();
            String id1 = source.get("ID");
            String number = source.get("NUMBER");
            String disease_type = source.get("DISEASE_TYPE");
            String address_desc = source.get("ADDRESS_DESC");
            String plane_range = source.get("PLANE_RANGE");
            String depth = source.get("DEPTH");
            String risk_grade = source.get("RISK_GRADE");
            String photo_url = source.get("PHOTO_URL");

            //拼接图片路径url
            HashMap photo_urlMap = new HashMap();
            ArrayList list1 = new ArrayList();
            list1.add(photo_url+"/scene_pic_1.jpg");
            list1.add(photo_url + "/scene_pic_2.jpg");
            photo_urlMap.put("现场",list1);
            ArrayList list2 = new ArrayList();
            list2.add(photo_url+"/map_location.jpg");
            list2.add(photo_url+"/scene_location.jpg");
            photo_urlMap.put("地图位置", list2);
            photo_urlMap.put("雷达图谱",photo_url+"/radar_pic.jpg");

            resObj.put("id", id1);
            resObj.put("number", number);
            resObj.put("address_desc", address_desc);
            resObj.put("disease_type", disease_type);
            resObj.put("plane_range", plane_range);
            resObj.put("depth", depth);
            resObj.put("risk_grade", risk_grade);
            resObj.put("photo_url", photo_urlMap);

        }
        result.put("data", resObj);
        return result;
    }


    public JSONObject getDetailByES(String id) throws IOException {
        String tableName = "tb_road_disease";
        JSONObject result = new JSONObject();
        List _sourceList = new ArrayList();
        _sourceList.add("id");
        _sourceList.add("number");
        _sourceList.add("disease_type");
        _sourceList.add("address_desc");
        _sourceList.add("plane_range");
        _sourceList.add("depth");
        _sourceList.add("risk_grade");
        _sourceList.add("photo_url");

        HashMap matchPhraseMap = new HashMap();

        matchPhraseMap.put("id",id);
        String response = GeoUtil.getListInfoAndMatchphrase(tableName, _sourceList, matchPhraseMap, 0, 1);

        JSONObject jsonResponse = JSON.parseObject(response);
        JSONObject hits1 = jsonResponse.getJSONObject("hits");
        if (null == hits1) {
            result.put("error", "empty response Set hits");
            return result;
        }
        JSONArray hits = hits1.getJSONArray("hits");

        if (null == hits) {
            result.put("error", "empty response Set hits.hits");
            return result;
        }

        Iterator<Object> iterator = hits.iterator();
        HashMap resObj = new HashMap();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            JSONObject source = next.getJSONObject("_source");
            String id1 = source.getString("id");
            String number = source.getString("number");
            String disease_type = source.getString("disease_type");
            String address_desc = source.getString("address_desc");
            String plane_range = source.getString("plane_range");
            String depth = source.getString("depth");
            String risk_grade = source.getString("risk_grade");
            String photo_url = source.getString("photo_url");

            //拼接图片路径url
            HashMap photo_urlMap = new HashMap();
            ArrayList list1 = new ArrayList();
            list1.add(photo_url+"/scene_pic_1.jpg");
            list1.add(photo_url + "/scene_pic_2.jpg");
            photo_urlMap.put("现场",list1);
            ArrayList list2 = new ArrayList();
            list2.add(photo_url+"/map_location.jpg");
            list2.add(photo_url+"/scene_location.jpg");
            photo_urlMap.put("地图位置", list2);
            photo_urlMap.put("雷达图谱",photo_url+"/radar_pic.jpg");

            resObj.put("id", id1);
            resObj.put("number", number);
            resObj.put("address_desc", address_desc);
            resObj.put("disease_type", disease_type);
            resObj.put("plane_range", plane_range);
            resObj.put("depth", depth);
            resObj.put("risk_grade", risk_grade);
            resObj.put("photo_url", photo_urlMap);

        }
        result.put("data", resObj);
        return result;
    }

}
