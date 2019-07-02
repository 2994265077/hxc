package com.cetccity.operationcenter.webframework.urbansign.tools;

import com.cetccity.operationcenter.webframework.urbansign.api.model.LevelColor;
import com.cetccity.operationcenter.webframework.urbansign.dao.COMMUNITY_CODEMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.COMMUNITY_CODE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StreetTool {

    @Autowired
    COMMUNITY_CODEMapper cOMMUNITY_CODEMapper;

    //获取福田区所有（街道、街道编码）
    public List<COMMUNITY_CODE> getStreet_CODE(){
        List<COMMUNITY_CODE> cOMMUNITY_CODE_list = cOMMUNITY_CODEMapper.getStreet_CODE();
        return cOMMUNITY_CODE_list;
    }

    //获取福田区所有（街道、街道编码）
    public List<COMMUNITY_CODE> getCOMMUNITY_CODE(COMMUNITY_CODE cOMMUNITY_CODE){
        List<COMMUNITY_CODE> cOMMUNITY_CODE_list = cOMMUNITY_CODEMapper.getCOMMUNITY_CODE(cOMMUNITY_CODE);
        return cOMMUNITY_CODE_list;
    }
    //获取福田区街道面积
    public Double getStreet_Area(String streetName){
        double area = cOMMUNITY_CODEMapper.getStreet_Area(streetName);
        return area;
    }

    //根据街道名获取--下辖社区
    public List<COMMUNITY_CODE> getCommunity_CODE(String streetName){
        List<COMMUNITY_CODE> cOMMUNITY_CODE_list = cOMMUNITY_CODEMapper.getCommunity_CODE(streetName);
        return cOMMUNITY_CODE_list;
    }

    //获取福田区社区面积
    public Double getCommunity_Area(String communityCode){
        double area = cOMMUNITY_CODEMapper.getCommunity_Area(communityCode);
        return area;
    }

    //获取福田区社区面积
    public LevelColor getPopulationBasicRGB(Map<String,Double> map_hight_vaule,double density){
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(map_hight_vaule.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            // 降序排序
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });
        double max = Double.valueOf(list.get(0).getValue());  //最大值
        double min = Double.valueOf(list.get(list.size()-1).getValue());//最小值*/
        LevelColor levelColor = new LevelColor();
        String RGB[] ={
                "136, 207, 250,0.7",
                "141, 209, 238,0.7",
                "148, 211, 223,0.7",
                "161, 217, 195,0.7",
                "181, 225, 155,0.7",
                "196, 231, 125,0.7",
                "213, 238, 84,0.7",
                "230, 245, 52,0.7",
                "241, 249, 30,0.7",
                "255, 255, 00,0.7",
                "255, 226, 00,0.7",
                "255, 210, 00,0.7",
                "255, 190, 00,0.7",
                "255, 171, 00,0.7",
                "255, 151, 00,0.7",
                "255, 126, 00,0.7",
                "255, 110, 00,0.7",
                "255, 95, 00,0.7",
                "255, 83, 00,0.7",
                "255, 76, 00,0.7"};
        int t = (int)Math.round((density-min)/((max-min)/19));
        levelColor.setLevel(t);
        levelColor.setColor(RGB[t]);
        levelColor.setMax(String.valueOf(max));
        levelColor.setMin(String.valueOf(min));
        return levelColor;
    }

    public LevelColor getLegalPersonRGB(int max,int min,double num){
        LevelColor levelColor = new LevelColor();
        String RGB[] ={
                "136, 207, 250,0.7",
                "141, 209, 238,0.7",
                "148, 211, 223,0.7",
                "161, 217, 195,0.7",
                "181, 225, 155,0.7",
                "196, 231, 125,0.7",
                "213, 238, 84,0.7",
                "230, 245, 52,0.7",
                "241, 249, 30,0.7",
                "255, 255, 00,0.7",
                "255, 226, 00,0.7",
                "255, 210, 00,0.7",
                "255, 190, 00,0.7",
                "255, 171, 00,0.7",
                "255, 151, 00,0.7",
                "255, 126, 00,0.7",
                "255, 110, 00,0.7",
                "255, 95, 00,0.7",
                "255, 83, 00,0.7",
                "255, 76, 00,0.7"};
        int t = (int)Math.round((num-min)/((max-min)/19));
        levelColor.setLevel(t);
        levelColor.setColor(RGB[t]);
        levelColor.setMax(String.valueOf(max));
        levelColor.setMin(String.valueOf(min));
        return levelColor;
    }
}
