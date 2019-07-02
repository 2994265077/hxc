package com.cetccity.operationcenter.webframework.rundisplay.controller.map;

import com.cetccity.operationcenter.webframework.hiddendanger.controller.map.TooltipController;
import com.cetccity.operationcenter.webframework.rundisplay.api.map.CityComponentTooltipApi;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipUtil;
import com.cetccity.operationcenter.webframework.rundisplay.util.HospitalTooltip;
import com.cetccity.operationcenter.webframework.web.dao.video.VideoPoliceMapper;
import com.cetccity.operationcenter.webframework.web.model.video.VideoPoliceModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@Slf4j
public class CityComponentTooltipController implements CityComponentTooltipApi {

    @Autowired
    TipUtil tipUtil;

    @Autowired
    TooltipController tooltipController;

    @Autowired
    HospitalTooltip hospitalTooltip;

    @Autowired
    private VideoPoliceMapper videoPoliceMapper;

    public Map summaryInfoVideo(@PathVariable("tableNameUrl") String tableNameUrl, String id) {
        Map resulttmp = new HashMap();
        List<VideoPoliceModel> publicVideoLists = videoPoliceMapper.selectsummaryInfo(id);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String address="";String category="";String name=""; String videoCode="";
        List tableList = new ArrayList();
        if (publicVideoLists.size() > 0) {
            for (VideoPoliceModel videoPoliceModel : publicVideoLists) {
                Map temp = new HashMap();
                name = videoPoliceModel.getName();
                address = videoPoliceModel.getName();
                category = videoPoliceModel.getCategory();
                videoCode = videoPoliceModel.getGbCode();
                temp.put("isPlay","1");
                temp.put("videoId",id);
                temp.put("videoName",name);
                list.add(temp);
                break ;
            }
        }
        List tempaddress = new ArrayList();
        tempaddress.add("地址");
        tempaddress.add(address);
        tableList.add(tempaddress);
        List tempCategory = new ArrayList();
        tempCategory.add("视频来源");
        tempCategory.add(category);
        tableList.add(tempCategory);
        List tempname = new ArrayList();
        tempname.add("视频编号");
        tempname.add(videoCode);
        tableList.add(tempname);
        resulttmp.put("hasDetailInfo", false);
        resulttmp.put("title", name);
        resulttmp.put("value", tableList);
        resulttmp.put("video", list);
        return resulttmp;
    }

    public Map getVideobyId(@PathVariable("tableNameUrl") String tableNameUrl,String id) {
        Map resulttmp = new HashMap();
        List<VideoPoliceModel> publicVideoLists = videoPoliceMapper.selectsummaryInfo(id);
        List tableList = new ArrayList();
        if (publicVideoLists.size() > 0) {
            for (VideoPoliceModel videoPoliceModel : publicVideoLists) {
                List tempp = new ArrayList();
                String name = videoPoliceModel.getName();
                tempp.add("isPlay");
                tempp.add("1");
                tempp.add("videoId");
                tempp.add(id);
                tempp.add("videoName");
                tempp.add(name);
                tableList.add(tempp);
            }
        }
        resulttmp.put("hasDetailInfo", true);
        resulttmp.put("title", "公安视频");
        resulttmp.put("value", tableList);
        return resulttmp;
    }

    public Map summaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String id) {
        Map return_map = tooltipController.summaryInfo(tableNameUrl, id);
        if(tipUtil.cityComponentImagetip(tableNameUrl, id)!=null) {
            return_map.put("img", tipUtil.cityComponentImagetip(tableNameUrl, id));
        }
        return return_map;
    }

    public Map hospitalSummaryInfo(@PathVariable("tableNameUrl") String tableNameUrl, String id){
        Map return_map = hospitalTooltip.summaryInfo(tableNameUrl,id);
        return return_map;
    }

}
