/*
package com.cetccity.operationcenter.webframework.web.controller.incident;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
//import com.cetccity.operationcenter.webframework.web.config.WebSocketServer;
import com.cetccity.operationcenter.webframework.web.dao.video.VideoIflyMapper;
import com.cetccity.operationcenter.webframework.web.model.KdxfOrderModel;
import com.cetccity.operationcenter.webframework.web.model.incident.LoadMap;
import com.cetccity.operationcenter.webframework.web.model.video.VideoIflyModel;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.LoadMapUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping(value = "/ifly")
public class IflyController {

    Logger logger = LoggerFactory.getLogger(IflyController.class);

    @Autowired
    LoadMapUtil loadMapUtil;

    @Autowired
    VideoIflyMapper videoIflyMapper ;

    @ApiOperation(value = "语音视频落图--推送[福田口岸,皇岗口岸,中心公园,笔架山,华强北]", notes = "语音视频--推送--{\"intent\":\"open_theme\",\"theme\":\"市民中心\"}")
    @RequestMapping(value = "/askTheme",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LoadMap> askTheme(@RequestBody String parameter)throws IOException{
        JSONObject result = new JSONObject();
        JSONObject parameterObj = JSON.parseObject(parameter);
        String intent = parameterObj.getString("intent");
        String theme = parameterObj.getString("theme");
        String tableName = "VIDEO_POLICE";
        InputStream inputStream =ESOperate.class.getResourceAsStream("/loadmap.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,"UTF-8"));
        String layerid = properties.getProperty(ESOperate.dbName+"."+tableName);
        logger.info("layerid-->" + layerid);
        List<LoadMap> loadMap = loadMapUtil.loadMapByKdxfDBVedio(tableName, layerid,theme);
        return loadMap;
    }

    @ApiOperation(value = "语音视频落图--推送", notes = "语音视频落图--推送")
    @RequestMapping(value = "/video/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LoadMap> kdxfVideoLoadMap() {
        return LoadMapUtil.loadMapList_kdxf;
    }

    @ApiOperation(value = "语音视频弹框--推送", notes = "语音视频弹框--推送--{\"intent\":\"open_cam\",\"cam_no\":\"1\"}")
    @RequestMapping(value = "/askCamNo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public KdxfOrderModel askCamNo(@RequestBody  String parameter ) {
        JSONObject result = new JSONObject();
        JSONObject parameterObj = JSON.parseObject(parameter);
        String intent = parameterObj.getString("intent");
        String cam_no = parameterObj.getString("cam_no");

        List<LoadMap> loadMap_list = LoadMapUtil.loadMapList_kdxf;
        Integer num = Integer.valueOf(cam_no);
        String video_code = loadMap_list.get(num).getId();
        KdxfOrderModel kdxfOrderModel = new KdxfOrderModel();
        kdxfOrderModel.setVoiceMsg(cam_no);
        kdxfOrderModel.setType("playVideo");
        kdxfOrderModel.setLayerid(video_code);
        try {
            WebSocketServer.sendInfo(JsonUtils.objectToJson(kdxfOrderModel));
        }catch (IOException e) {
        }
        return kdxfOrderModel;
    }

    public void saveData(String intent,String para){
        VideoIflyModel videoIflyModel = new VideoIflyModel();
        videoIflyModel.setIfly1(intent);
        videoIflyModel.setIfly2(para);
        videoIflyMapper.insertSelective(videoIflyModel);
    }

}
*/
