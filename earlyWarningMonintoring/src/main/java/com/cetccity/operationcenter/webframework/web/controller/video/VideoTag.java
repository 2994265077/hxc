package com.cetccity.operationcenter.webframework.web.controller.video;


import com.alibaba.fastjson.JSONArray;
import com.cetccity.operationcenter.webframework.web.dao.video.VideoPoliceMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/videotag")
@Api(tags = "视频打标签功能")
public class VideoTag {

    @Autowired
    private VideoPoliceMapper videoPoliceMapper;

    @RequestMapping(value = "/tag", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray tag(String tag) {
        JSONArray resArr = new JSONArray();
        resArr.add("resArr");
        return resArr;
    }
}
