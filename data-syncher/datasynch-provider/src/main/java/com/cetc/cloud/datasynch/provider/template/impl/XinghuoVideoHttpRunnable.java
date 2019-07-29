package com.cetc.cloud.datasynch.provider.template.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetc.cloud.datasynch.api.model.VideoModel;
import com.cetc.cloud.datasynch.provider.config.SpringContextUtil;
import com.cetc.cloud.datasynch.provider.mapper.VideoPoliceMapper;
import com.cetc.cloud.datasynch.provider.template.OuterJobRunnableTemplate;
import com.cetc.cloud.datasynch.provider.util.HttpClientUtil2;
import com.cetc.cloud.datasynch.provider.util.entity.PostModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * PackageName:   com.cetc.cloud
 * projectName:   dataSyncher
 * Description:   LuoLinjie 补充
 * Creator:     by LuoLinjie
 * Create_Date: 2019/3/5 15:02
 * Updater:     by Administrator
 * Update_Date: 2019/3/5 15:02
 * Update_Description: Administrator 补充
 */
//@Service
@Slf4j
public class XinghuoVideoHttpRunnable implements OuterJobRunnableTemplate {

    VideoPoliceMapper videoPoliceMapper;

    private final String IP_PORT = "10.192.76.202:8082";
    private final String URL_getPlatformCount = "http://" + IP_PORT + "/xEyeWeb/channel/getPlatformCount.do";
    private final String URL_getPlatformListProto = "http://" + IP_PORT + "/xEyeWeb/channel/getPlatformListProto.do";
    private final String URL_getCameraInfoById = "http://" + IP_PORT + "/xEyeWeb/channel/getCameraInfoById.do";
    private final String xueliangProjectPlatId = "9ca58a521af846ecb7e7ff1d8e021099";

    public XinghuoVideoHttpRunnable() {
        this.videoPoliceMapper = (VideoPoliceMapper) SpringContextUtil.getBean("videoPoliceMapper");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("XinghuoVideo");
        updateVideoInfoInDB();
    }

    public void updateVideoInfoInDB() {
        List<String> platformIds = getPlatformList();
        int count = 0;
        int totalVideoPointNum = 0;
        for (String platformId : platformIds) {
            List videoListOnline;
            log.info("handling:platformId -- " + platformId);
            if (xueliangProjectPlatId.equals(platformId)) {
                List<String> platformListProto = getVideoListOnlineByPlatformId(xueliangProjectPlatId);
                for (String p : platformListProto) {
                    String subPlatformId = extractPlatformId(p);
                    videoListOnline = getVideoListOnlineByPlatformId(subPlatformId);
                    totalVideoPointNum += videoListOnline.size();
                    count += insertVideoListIntoDB(videoListOnline);
                }
            } else {
                videoListOnline = getVideoListOnlineByPlatformId(platformId);
                if (null != videoListOnline) {
                    count += insertVideoListIntoDB(videoListOnline);
                }
                totalVideoPointNum += videoListOnline.size();
            }

        }
        log.info("Finished! total VideoPointNum :" + totalVideoPointNum);
        log.info("Finished! total success:" + count);
    }

    public List getPlatformList() {
        ArrayList<String> filteredPlatformIds = new ArrayList<>();
        String platformIds = getPlatformListOnline();
        JSONObject parsedObj = JSONObject.parseObject(platformIds);
        //去除“删除”和“升级改造”2类
        JSONArray platformArr = parsedObj.getJSONArray("result");
        Iterator<Object> iterator = platformArr.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            String name = next.getString("name");
//            log.info("\nplatform"+i+++":"+next.toJSONString());
            if ("删除".equals(name) || "升级改造（无图）".equals(name)
                    || "福田分局".equals(name)
                    || "深圳市公安局".equals(name)) continue;
            filteredPlatformIds.add(next.getString("id"));
        }
        return filteredPlatformIds;
    }

    private String getPlatformListOnline() {
        PostModel postModel = new PostModel();
        postModel.setUrl(URL_getPlatformCount);
        JSONObject body = new JSONObject();
        body.put("userid", "xJphzu9nOdmzoJLA08U");
        body.put("platformid", "");
        body.put("pageno", 1);
        body.put("pagesize", 50);
        body.put("constcategoryid", 0);
        body.put("cameraquality", 0);
        body.put("cameratype", 0);
        body.put("labelids", "");
        body.put("client", 0);
        postModel.setBody(body);
        JSONObject header = new JSONObject();
//        header.put("Content-Type", "text/html;charset=utf-8");
//        header.put("Host", IP_PORT);
////        header.put("Content-Length","151");
//        header.put("Expect", "100-continue");
        postModel.setHeader(header);
        JSONObject res = HttpClientUtil2.doPostWithPostModel(postModel);
        if (res.getIntValue("code") == 200) {
            String data = res.getString("data");
            return data;
        }
        return "";
    }

    public List getVideoListOnlineByPlatformId(String platformId) {
        PostModel postModel = new PostModel();
        postModel.setUrl(URL_getPlatformListProto);
        JSONObject body = new JSONObject();
        body.put("UserId", "xJphzu9nOdmzoJLA08U");
        body.put("PlatformId", platformId);
        body.put("PageNo", 1);
        body.put("PageSize", 3000);
        body.put("Keyword", "");
        body.put("Constcategoryid", 0);
        body.put("Cameraquality", 0);
        body.put("Cameratype", 0);
        body.put("AutoSortName", "");
        postModel.setBody(body);
        JSONObject res = HttpClientUtil2.doPostWithPostModel(postModel);
        List simpleList = new ArrayList<String>();
        if (res.getIntValue("code") == 200) {
            String data = res.getString("data");
            String[] splits = data.split("\n");
            int i = 0;
            for (String line : splits) {
                if (i++ >= 2) {
                    simpleList.add(line);
                }
            }
            log.info("received videoModel line:" + (splits.length - 2));
        }
        return simpleList;
    }


    private int insertVideoListIntoDB(List<String> simpleList) {
        List<VideoModel> videoList = new ArrayList<VideoModel>();
        int i = 0;
        for (String line : simpleList) {
            if (i++ <= 2) continue;
            VideoModel videoModel = parse2VideoModel(line);
            if (videoModel == null) continue;
            videoList.add(videoModel);
        }
        int successCount = 0;
        int failCount = 0;
        for (VideoModel model : videoList) {
            VideoModel videoDetailInfo = getDetailInfo(model.getCameraId());
            if (null != videoDetailInfo) {
                try {
                    videoDetailInfo.setUpdateTime(new Date());
                    int insert = videoPoliceMapper.insert(videoDetailInfo);
                    if (insert > 0) {
                        successCount++;
                    } else {
                        failCount++;
                    }
                } catch (Exception e) {
                    log.error(e.getCause().getMessage());
                    log.error("duplicate:cameraid:" + model.getCameraId());
                }

            }
        }
        log.info("successCount:" + successCount);
        log.info("failCount:" + failCount);
        return successCount;
    }

    private VideoModel getDetailInfo(String cameraId) {
        PostModel postModel = new PostModel();
        postModel.setUrl(URL_getCameraInfoById);
        JSONObject body = new JSONObject();
        body.put("user_id", "xJphzu9nOdmzoJLA08U");
        body.put("cameraid", cameraId.replaceAll(" ", ""));
        postModel.setBody(body);
        JSONObject res = HttpClientUtil2.doPostWithPostModel(postModel);
        if (res.getIntValue("code") == 200) {

            String data = res.getString("data");
            return extractDetailModel(data);
        }

        return null;
    }

    private VideoModel extractDetailModel(String data) {
        JSONObject detailJSON = JSONObject.parseObject(data);
        VideoModel videoModel = new VideoModel();
        if ("000".equals(detailJSON.getString("retCode"))) {
            JSONObject camerainfosVo = detailJSON.getJSONObject("camerainfosVo");
            videoModel.setCameraName(camerainfosVo.getString("cameraname"));
            String[] jd_wd = getJwd(camerainfosVo.getString("jwd"));
            if (jd_wd.length != 2) {
                log.info("jd、wd is null!");
            }
            videoModel.setJd84(jd_wd[0]);
            videoModel.setWd84(jd_wd[1]);
            videoModel.setAddress(camerainfosVo.getString("cameraaddress"));
            videoModel.setConstcategory(camerainfosVo.getString("constcategory"));
            videoModel.setOrgName(camerainfosVo.getString("orgname"));
            videoModel.setGzsb(camerainfosVo.getString("gzsb"));
            videoModel.setMaintunit(camerainfosVo.getString("maintunit"));
            videoModel.setCameratypedesc(camerainfosVo.getString("cameratypedesc"));
            videoModel.setCameraId(camerainfosVo.getString("id"));
            videoModel.setDevicechanid(camerainfosVo.getString("devicechanid"));
            videoModel.setGbCode(camerainfosVo.getString("gb_code"));
            videoModel.setJkfw(camerainfosVo.getString("jkfw"));
            return videoModel;
        } else {
            return null;
        }
    }

    private String[] getJwd(String jwd) {
        String[] jd_wd = new String[2];
        try {
            String[] split = jwd.split(",");
            jd_wd[0] = split[0];
            jd_wd[1] = split[1];
            return jd_wd;
        } catch (Exception e) {
            return new String[0];
        }
    }

    private VideoModel parse2VideoModel(String line) {

        VideoModel videoModel = new VideoModel();
//        String regex = "^\\s[\\d]{32}";
//        Pattern pattern = Pattern.compile(regex);
//        if (pattern.matcher(line).matches()) {
//            String[] split = line.replaceAll(" ","").split("\u0012\f");
        if (line.length() >= 33) {
            String cameraId = line.substring(1, 33);
            if (cameraId.length() == 32) {
                videoModel.setCameraId(cameraId);
                return videoModel;
            } else {
                log.info("cannot execute: parse2VideoModel:" + line);
                return null;
            }
        }
        return null;

//        if (split.length >= 2) {
//
//                videoModel.setCameraId(split[0]);
//            } else {
//                log.info("cannot execute: parse2VideoModel:" + line);
//                return null;
//            }
//        }
    }

    private String extractPlatformId(String line) {
        if (line.length() >= 33) {
            String platId = line.substring(1, 33);
            if (platId.length() == 32) {
                return platId;
            } else {
                log.info("cannot execute: parse2VideoModel:" + line);
                return null;
            }
        }
        return null;
    }


}
