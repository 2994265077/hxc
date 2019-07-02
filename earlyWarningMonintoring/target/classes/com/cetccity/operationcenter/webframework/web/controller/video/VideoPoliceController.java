package com.cetccity.operationcenter.webframework.web.controller.video;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.web.util.GeoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/videop")
@Api(tags = "视频播放服务")
public class VideoPoliceController {

    /**
     * @return 用于搜索
     */
    @ApiOperation(value = "视频搜索", notes = "视频搜索", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "地址", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", paramType = "query" , example="1"),
            @ApiImplicitParam(name = "pageSize", value = "页总数", paramType = "query" , example="10")
    })
  //  @RequestMapping(value = "/video_police/getAll", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
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
        result.put("GB_CODE", "layer_keyinformation_realtimevideo_gonganshipin");
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);
        result.put("total", jsonResponse.getJSONObject("hits").getString("total"));

        return result;
    }

    //用于去除名字中编号，缩短名字
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

}
