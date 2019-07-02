package com.cetccity.operationcenter.webframework.web.controller.video;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/videop")
@Api(tags = "视频播放账号密码验证")
public class VideoUserController {

   // @Autowired
   // private VideoUserMapper videoUserMapper ;

    //用于显示整数等等
    @ApiOperation(value = "视频用户", notes = "视频用户", produces = "application/json")
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map user() {
//        VideoUserExample videoUserExample = new VideoUserExample();
//
//    //    List<VideoUser> videoUsers = videoUserMapper.selectByExample(videoUserExample);
//        VideoUser videoUser = videoUsers.get(tag);
//
//
//       // result.put("user",videoUser.getUser());
//        String password = videoUser.getPassword() ;
//        String password1=AESUtil.decrypt(password,"cetccity") ;
//     //   result.put("password",password1);
        Map result = new HashMap();
        result.put("user","ft");
        result.put("password","888888");
        return result ;
    }
}
