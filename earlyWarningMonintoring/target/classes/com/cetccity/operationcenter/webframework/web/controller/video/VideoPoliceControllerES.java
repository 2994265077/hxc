package com.cetccity.operationcenter.webframework.web.controller.video;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.web.util.GeoUtil;
import com.cetccity.operationcenter.webframework.web.util.VideoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller
//@RequestMapping(value = "/videop0")
@Api(tags = "视频播放服务")
public class VideoPoliceControllerES {

    @ApiOperation(value = "视频落点", notes = "视频落点", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "视频编号", paramType = "query")

    })
   // @RequestMapping(value = "/selectByExample", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray selectByExample(String id) {
        List testList = new ArrayList();
        String tableName = "video_police";

        testList.add("GB_CODE"); //输出为cameraID
        testList.add("address");
        testList.add("jd");
        testList.add("wd");
        testList.add("category");
        testList.add("name");
        testList.add("district");
        String response ;

        response = VideoUtil.getAll(tableName, testList);

        JSONObject jsonResponse = JSON.parseObject(response);
        JSONArray array = jsonResponse.getJSONObject("hits").getJSONArray("hits");

        JSONArray resArr = new JSONArray();
        Iterator<Object> iterator = array.iterator();

        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            JSONObject source = next.getJSONObject("_source");
            id = source.getString("GB_CODE");
            String address = source.getString("address");
            String jd = source.getString("jd");
            String wd = source.getString("wd");
            String category = source.getString("category");
            String name = source.getString("name");
            String district = source.getString("district");

            JSONObject resObj = new JSONObject();

            resObj.put("tableName", tableName);
            resObj.put("id", id);
            resObj.put("jd", jd);
            resObj.put("wd", wd);
            resObj.put("address", address);
            resObj.put("category", category);
            resObj.put("name", name);
            resObj.put("district", district);

            resArr.add(resObj);

        }

        return resArr;

    }

    //@RequestMapping(value = "/summaryInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map summaryInfo(String id) {

        Map resulttmp = new HashMap();
        List tableList = new ArrayList();

//-----------------------------------------------------
        List testList = new ArrayList();
        String tableName = "video_police";

        testList.add("address");
        testList.add("category");
        testList.add("name");
        testList.add("GB_CODE");

        String response = VideoUtil.conditionQuery(tableName, testList,"GB_CODE",id);
        JSONObject jsonResponse = JSON.parseObject(response);
        JSONArray array = jsonResponse.getJSONObject("hits").getJSONArray("hits");

        JSONArray resArr = new JSONArray();
        Iterator<Object> iterator = array.iterator();
        JSONObject next = (JSONObject) iterator.next();
        JSONObject source = next.getJSONObject("_source");

        String address = source.getString("address");
        String category = source.getString("category");
        String name = source.getString("name");

//-------------------------------------------------------
            Map temp = new HashMap();

            temp.put("isPlay","1");
            temp.put("videoId",id);
            temp.put("videoName",name);

            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            list.add(temp);




        List tempaddress = new ArrayList();
        tempaddress.add("地址");
        tempaddress.add(address);
        tableList.add(tempaddress);

        List tempCategory = new ArrayList();
        tempCategory.add("视频来源");
        tempCategory.add(category);
        tableList.add(tempCategory);

        List tempname = new ArrayList();
        tempname.add("视频名称");
        tempname.add(name);
        tableList.add(tempname);


        resulttmp.put("video",list);
        resulttmp.put("title","视频详情");
        resulttmp.put("hasDetailInfo", false);
        resulttmp.put("value", tableList);

        return resulttmp;

    }

   // @RequestMapping(value = "/getVideobyId", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map getVideobyId(String id) {
        Map resulttmp = new HashMap();

        List videoPlaceList = new ArrayList();
        String tableName = "video_police";


        videoPlaceList.add("GB_CODE");
        videoPlaceList.add("name");

        String response = VideoUtil.conditionQuery(tableName, videoPlaceList,"GB_CODE",id);

        JSONObject jsonResponse = JSON.parseObject(response);
        JSONArray array = jsonResponse.getJSONObject("hits").getJSONArray("hits");

        Iterator<Object> iterator = array.iterator();

        List tableList = new ArrayList();

            JSONObject next = (JSONObject) iterator.next();
            JSONObject source1 = next.getJSONObject("_source");
            Map temp = new HashMap();
            List tempp = new ArrayList();



            String videoName = source1.getString("name");

            tempp.add("isPlay");
            tempp.add("1");

            tempp.add("videoId");
            tempp.add(id);

            tempp.add("videoName");
            tempp.add(videoName);

            tableList.add(tempp);


        resulttmp.put("hasDetailInfo", true);
        resulttmp.put("title", "视频");
        resulttmp.put("value", tableList);

        return resulttmp;
    }


   // @RequestMapping(value = "/video_police/getAll", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject getAll(String address, int pageNum, int pageSize) throws IOException {
        String tableName = "video_police";
        JSONObject result = new JSONObject();
        List _sourceList = new ArrayList();

        _sourceList.add("name");
        _sourceList.add("address");
        _sourceList.add("GB_CODE");
        _sourceList.add("jd");
        _sourceList.add("wd");
        _sourceList.add("category");
        _sourceList.add("district");


        HashMap<String, String> matchMap = new HashMap<String, String>();

        if (address != null) {
            matchMap.put("address", address);
        }
        else
        {
            matchMap.put("address", "深圳");
        }

        String response = GeoUtil.queryListInfoAndFilter(tableName, _sourceList, matchMap, pageNum, pageSize);

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

        JSONArray resArr = new JSONArray();
        Iterator<Object> iterator = hits.iterator();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            JSONObject source = next.getJSONObject("_source");
            String name = source.getString("name");
            String addressAll = source.getString("address");
            String GB_CODE = source.getString("GB_CODE");
            String jd = source.getString("jd");
            String wd = source.getString("wd");
            String category = source.getString("category");
            String district = source.getString("district");


            //拼接url
            JSONObject resObj = new JSONObject();
            resObj.put("tableName", tableName);
            resObj.put("name", pro(name));
            resObj.put("address", pro(addressAll));
            resObj.put("GB_CODE", GB_CODE);
            resObj.put("jd", jd);
            resObj.put("wd", wd);
            resObj.put("category", category);
            resObj.put("district", district);

            resArr.add(resObj);
        }

        result.put("data", resArr);
        result.put("layerid", "layer_keyinformation_realtimevideo_gonganshipin");
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);
        result.put("total", jsonResponse.getJSONObject("hits").getString("total"));

        return result;
    }


     String  pro(String address) {

           char add[]=address.toCharArray() ;

           int flag=0 ;
           int k=0;

         for(int i=0;i<add.length;i++){
             if(add[i]=='4' && add[i+1]=='4'&& add[i+2]=='0'){
               flag =1 ;
               k=i ;
               break ;
             }
         }
         if(flag==1){
             char abb[] =new char[add.length-12]  ;

             for(int j=0 ;j<add.length-12;j++)
             {
                 if(j>=k && j<k+12)
                 {
                     abb[j]=add[j+12];
                 }
                 else
                 {
                     abb[j]=add[j];
                 }

             }
             address= String.valueOf(abb);

         }
         else{
             address=String.valueOf(add);

         }
         return address ;

    }

    //用于显示整数等等
    @ApiOperation(value = "视频总数统计", notes = "视频总数统计", produces = "application/json")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "pageNum", value = "页码", paramType = "query" , example="1"),
            @ApiImplicitParam(name = "pageSize", value = "页总数", paramType = "query" , example="10")

    })
  //  @RequestMapping(value = "/pageInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject pageInfo(int pageNum,int pageSize) {

        String tableName = "video_police";
        JSONObject result = new JSONObject();
        List _sourceList = new ArrayList();

        _sourceList.add("name");
        _sourceList.add("address");
        _sourceList.add("GB_CODE");

        HashMap<String, String> matchMap = new HashMap<String, String>();



        String response = GeoUtil. getListInfo( tableName,  _sourceList, pageNum, pageSize);

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

        JSONArray resArr = new JSONArray();
        Iterator<Object> iterator = hits.iterator();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            JSONObject source = next.getJSONObject("_source");
            String name = source.getString("name");
            String addressAll = source.getString("address");
            String GB_CODE = source.getString("GB_CODE");

            //拼接url

            HashMap<String, String> Map = new HashMap<String, String>();
            Map.put("id", GB_CODE);
            Map.put("name",  pro(name));
            Map.put("address", addressAll);

            resArr.add(Map);
        }
        String total = jsonResponse.getJSONObject("hits").getString("total");
        int pages ;
        if(pageSize>0)        {
             pages = Integer.parseInt(total)/pageSize;
        }
        else{
            pages=0 ;
        }

        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);
        result.put("total", total);
        result.put("pages",pages);
        result.put("list", resArr);

        return result;

    }

}
