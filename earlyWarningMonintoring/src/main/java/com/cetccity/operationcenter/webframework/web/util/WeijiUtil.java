package com.cetccity.operationcenter.webframework.web.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.cetccity.operationcenter.webframework.web.config.CommonStaticInstance;
import com.cetccity.operationcenter.webframework.web.model.incident.ElasticHits2Model;
import com.cetccity.operationcenter.webframework.web.model.incident.weiji.StreetModel;
import com.cetccity.operationcenter.webframework.web.model.incident.weiji.TB_WEIJI_IN_INFO_RT;
import com.cetccity.operationcenter.webframework.web.model.incident.weiji.TB_WEIJI_OUT_INFO_RT;
import com.cetccity.operationcenter.webframework.web.model.incident.weiji.TypeModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WeijiUtil {

    static Map<String, Integer> map = Collections.emptyMap();// 如果这个map被置为不可用

    public List<Map> findWeijiInByEs(String region,Integer num){
        String[] streets = {"园岭街道","南园街道","福田街道","沙头街道","梅林街道","华富街道","香蜜湖街道","福保街道","华强北街道","莲花北街道"};
        String[] hospitalHumanFlow_1 = {"入院人次","出院人次","在院人次"};
        List<Map> mapTypeList = new ArrayList<Map>();
        Integer admit_no = 0;
        Integer leave_h_no = 0;
        Integer in_h_no = 0;
        //Integer k = 0;
        for(int i=0;i<hospitalHumanFlow_1.length;i++) {
            Map mapType = new HashMap();
            Map<String, Integer> mapList = new HashMap<String, Integer>();
            List<StreetModel> streetModelList = new ArrayList<StreetModel>();
            for (int j = 0; j < streets.length; j++) {
                List<TB_WEIJI_IN_INFO_RT> tB_WEIJI_IN_INFO_RTList = new ArrayList<TB_WEIJI_IN_INFO_RT>();
                String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/tb_weiji_in_info_rt@31project_april/_doc/_search";
                String json = "{\n" +
                        "  \"from\":\"0\",\n" +
                        "  \"size\":\"9999\",\n" +
                        "  \"query\":{\n" +
                        "        \"bool\":{\n" +
                        "            \"must\":[\n" +
                        "              {\"match_phrase\":{\"region\":\"" + region + "\"}},\n" +
                        "              {\"match_phrase\":{\"street\":\"" + streets[j] + "\"}}\n" +
                        "             ]\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
                String source = UrlApiToSource.doJsonPost(urlStr, json);
                for (ElasticHits2Model elasticHits2Model : EsToEntityUtil.esToEntity(source)) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        String str = objectMapper.writeValueAsString(elasticHits2Model.get_source());
                        TB_WEIJI_IN_INFO_RT tB_WEIJI_IN_INFO_RT = JsonUtils.jsonToBean(str, TB_WEIJI_IN_INFO_RT.class);
                        tB_WEIJI_IN_INFO_RTList.add(tB_WEIJI_IN_INFO_RT);
                    } catch (JsonProcessingException e) {
                        log.error(e.toString());
                    }
                }
                //遍历**区**街道的医院信息
                StreetModel streetModel = new StreetModel();
                Integer admit_no_s = 0;
                Integer leave_h_no_s = 0;
                Integer in_h_no_s = 0;
                Map<String, Integer> map = new HashMap<String, Integer>();
                if (tB_WEIJI_IN_INFO_RTList.size() == 0) {
                    streetModel.setName(streets[j]);
                    //streetModel.setValue(map);
                    streetModelList.add(streetModel);
                } else {
                    for (TB_WEIJI_IN_INFO_RT tB_WEIJI_IN_INFO_RT : tB_WEIJI_IN_INFO_RTList) {
                        if(i==0) {
                            admit_no += tB_WEIJI_IN_INFO_RT.getAdmitNo();
                            streetModel.setName(streets[j]);
                            streetModel.setValue(0);
                        if (map.get(tB_WEIJI_IN_INFO_RT.getOrgName()) != null) {
                            map.put(tB_WEIJI_IN_INFO_RT.getOrgName(), map.get(tB_WEIJI_IN_INFO_RT.getOrgName()) + tB_WEIJI_IN_INFO_RT.getAdmitNo());
                            admit_no_s += map.get(tB_WEIJI_IN_INFO_RT.getOrgName()) + tB_WEIJI_IN_INFO_RT.getAdmitNo();
                        } else {
                            map.put(tB_WEIJI_IN_INFO_RT.getOrgName(), tB_WEIJI_IN_INFO_RT.getAdmitNo());
                            admit_no_s += tB_WEIJI_IN_INFO_RT.getAdmitNo();
                        }
                            streetModel.setValue(admit_no_s);
                        }else if(i==1){
                            leave_h_no+=tB_WEIJI_IN_INFO_RT.getLeaveHNo();
                            streetModel.setName(streets[j]);
                            if (map.get(tB_WEIJI_IN_INFO_RT.getOrgName()) != null) {
                                map.put(tB_WEIJI_IN_INFO_RT.getOrgName(), map.get(tB_WEIJI_IN_INFO_RT.getOrgName()) + tB_WEIJI_IN_INFO_RT.getLeaveHNo());
                                leave_h_no_s += map.get(tB_WEIJI_IN_INFO_RT.getOrgName()) + tB_WEIJI_IN_INFO_RT.getLeaveHNo();
                            } else {
                                map.put(tB_WEIJI_IN_INFO_RT.getOrgName(), tB_WEIJI_IN_INFO_RT.getLeaveHNo());
                                leave_h_no_s += tB_WEIJI_IN_INFO_RT.getLeaveHNo();
                            }
                            streetModel.setValue(leave_h_no_s);
                        }else if(i==2){
                            in_h_no+=tB_WEIJI_IN_INFO_RT.getInHNo();
                            streetModel.setName(streets[j]);
                            if (map.get(tB_WEIJI_IN_INFO_RT.getOrgName()) != null) {
                                map.put(tB_WEIJI_IN_INFO_RT.getOrgName(), map.get(tB_WEIJI_IN_INFO_RT.getOrgName()) + tB_WEIJI_IN_INFO_RT.getInHNo());
                                in_h_no_s += map.get(tB_WEIJI_IN_INFO_RT.getOrgName()) + tB_WEIJI_IN_INFO_RT.getInHNo();
                            } else {
                                map.put(tB_WEIJI_IN_INFO_RT.getOrgName(), tB_WEIJI_IN_INFO_RT.getInHNo());
                                in_h_no_s += tB_WEIJI_IN_INFO_RT.getInHNo();
                            }
                            streetModel.setValue(in_h_no_s);
                        }
                    }
                    streetModelList.add(streetModel);

                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        mapList.put(entry.getKey(),entry.getValue());
                    }

                }
            }
            TypeModel typeModel = new TypeModel();
            if(i==0) {
                typeModel.setTotal(admit_no);
            }else if(i==1){
                typeModel.setTotal(leave_h_no);
            }else if(i==2){
                typeModel.setTotal(in_h_no);
            }

            Map mapOut = new LinkedHashMap();
            map = new TreeMap<String, Integer>(new Comparator<String>() {
                public int compare(String obj1, String obj2) {
                    // 降序,默认升序,但Key必须是能进行比较的。
                    return obj2.compareTo(obj1);
                }
            });
            for (Map.Entry<String, Integer> entry : mapList.entrySet()) {
                map.put(entry.getKey(),entry.getValue());
            }
            // 这里将map.entrySet()转换成list
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
                    map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                // 降序排序
                public int compare(Map.Entry<String, Integer> o1,
                                   Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            Integer k = 0;
            for (Map.Entry<String, Integer> mapping : list) {
                System.out.println(mapping.getKey() + ":" + mapping.getValue());
                mapOut.put(mapping.getKey(),mapping.getValue());
                ++k;
                if(k == num){break;}
            }
            System.out.println("********");

            typeModel.setStreet_data(streetModelList);
            typeModel.setRank_data(mapOut);
            mapType.put(hospitalHumanFlow_1[i], typeModel);
            mapTypeList.add(mapType);
        }
        return mapTypeList;
    }

    public List<Map> findWeijiOutByEs(String region,Integer num) {
        String[] streets = {"园岭街道", "南园街道", "福田街道", "沙头街道", "梅林街道", "华富街道", "香蜜湖街道", "福保街道", "华强北街道", "莲花北街道"};
        String[] hospitalHumanFlow_2 = {"就诊人次", "候诊人次", "挂号人次"};
        List<Map> mapTypeList = new ArrayList<Map>();
        Integer visit_no = 0;
        Integer waiting_no = 0;
        Integer register_no = 0;
        for (int i = 0; i < hospitalHumanFlow_2.length; i++) {
            Map mapType = new HashMap();
            Map<String, Integer> mapList = new HashMap<String, Integer>();
            List<StreetModel> streetModelList = new ArrayList<StreetModel>();
            for (int j = 0; j < streets.length; j++) {
                List<TB_WEIJI_OUT_INFO_RT> tB_WEIJI_OUT_INFO_RTList = new ArrayList<TB_WEIJI_OUT_INFO_RT>();
                String urlStr = "http://" + CommonStaticInstance.elasticsearchIp + ":" + CommonStaticInstance.elasticsearchPort + "/tb_weiji_out_info_rt@31project_april/_doc/_search";
                String json = "{\n" +
                        "  \"from\":\"0\",\n" +
                        "  \"size\":\"9999\",\n" +
                        "  \"query\":{\n" +
                        "        \"bool\":{\n" +
                        "            \"must\":[\n" +
                        "              {\"match_phrase\":{\"region\":\"" + region + "\"}},\n" +
                        "              {\"match_phrase\":{\"street\":\"" + streets[j] + "\"}}\n" +
                        "             ]\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
                String source = UrlApiToSource.doJsonPost(urlStr, json);
                for (ElasticHits2Model elasticHits2Model : EsToEntityUtil.esToEntity(source)) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        String str = objectMapper.writeValueAsString(elasticHits2Model.get_source());
                        TB_WEIJI_OUT_INFO_RT tB_WEIJI_OUT_INFO_RT = JsonUtils.jsonToBean(str, TB_WEIJI_OUT_INFO_RT.class);
                        tB_WEIJI_OUT_INFO_RTList.add(tB_WEIJI_OUT_INFO_RT);
                    } catch (JsonProcessingException e) {
                        log.error(e.toString());
                    }
                }
                //遍历**区**街道的医院信息
                StreetModel streetModel = new StreetModel();
                Integer visit_no_s = 0;
                Integer waiting_no_s = 0;
                Integer register_no_s = 0;
                Map<String, Integer> map = new HashMap<String, Integer>();
                if (tB_WEIJI_OUT_INFO_RTList.size() == 0) {
                    streetModel.setName(streets[j]);
                    streetModelList.add(streetModel);
                } else {
                    for (TB_WEIJI_OUT_INFO_RT tB_WEIJI_OUT_INFO_RT : tB_WEIJI_OUT_INFO_RTList) {
                        if (i == 0) {
                            visit_no += tB_WEIJI_OUT_INFO_RT.getVisitNo();
                            streetModel.setName(streets[j]);
                            if (map.get(tB_WEIJI_OUT_INFO_RT.getOrgName()) != null) {
                                map.put(tB_WEIJI_OUT_INFO_RT.getOrgName(), map.get(tB_WEIJI_OUT_INFO_RT.getOrgName()) + tB_WEIJI_OUT_INFO_RT.getVisitNo());
                                visit_no_s += map.get(tB_WEIJI_OUT_INFO_RT.getOrgName()) + tB_WEIJI_OUT_INFO_RT.getVisitNo();
                            } else {
                                map.put(tB_WEIJI_OUT_INFO_RT.getOrgName(), tB_WEIJI_OUT_INFO_RT.getVisitNo());
                                visit_no_s += tB_WEIJI_OUT_INFO_RT.getVisitNo();
                            }
                            streetModel.setValue(visit_no_s);
                        } else if (i == 1) {
                            waiting_no += tB_WEIJI_OUT_INFO_RT.getWaitingNo();
                            streetModel.setName(streets[j]);
                            if (map.get(tB_WEIJI_OUT_INFO_RT.getOrgName()) != null) {
                                map.put(tB_WEIJI_OUT_INFO_RT.getOrgName(), map.get(tB_WEIJI_OUT_INFO_RT.getOrgName()) + tB_WEIJI_OUT_INFO_RT.getWaitingNo());
                                waiting_no_s += map.get(tB_WEIJI_OUT_INFO_RT.getOrgName()) + tB_WEIJI_OUT_INFO_RT.getWaitingNo();
                            } else {
                                map.put(tB_WEIJI_OUT_INFO_RT.getOrgName(), tB_WEIJI_OUT_INFO_RT.getWaitingNo());
                                waiting_no_s += tB_WEIJI_OUT_INFO_RT.getWaitingNo();
                            }
                            streetModel.setValue(waiting_no_s);
                        } else if (i == 2) {
                            register_no += tB_WEIJI_OUT_INFO_RT.getRegisterNo();
                            streetModel.setName(streets[j]);
                            if (map.get(tB_WEIJI_OUT_INFO_RT.getOrgName()) != null) {
                                map.put(tB_WEIJI_OUT_INFO_RT.getOrgName(), map.get(tB_WEIJI_OUT_INFO_RT.getOrgName()) + tB_WEIJI_OUT_INFO_RT.getRegisterNo());
                                register_no_s += map.get(tB_WEIJI_OUT_INFO_RT.getOrgName()) + tB_WEIJI_OUT_INFO_RT.getRegisterNo();
                            } else {
                                map.put(tB_WEIJI_OUT_INFO_RT.getOrgName(), tB_WEIJI_OUT_INFO_RT.getRegisterNo());
                                register_no_s += tB_WEIJI_OUT_INFO_RT.getRegisterNo();
                            }
                            streetModel.setValue(register_no_s);
                        }
                        //streetModel.setValue(map);
                    }
                    streetModelList.add(streetModel);

                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        mapList.put(entry.getKey(),entry.getValue());
                    }

                }
            }
            TypeModel typeModel = new TypeModel();
            if (i == 0) {
                typeModel.setTotal(visit_no);
            } else if (i == 1) {
                typeModel.setTotal(waiting_no);
            } else if (i == 2) {
                typeModel.setTotal(register_no);
            }

            Map mapOut = new LinkedHashMap();
            map = new TreeMap<String, Integer>(new Comparator<String>() {
                public int compare(String obj1, String obj2) {
                    // 降序,默认升序,但Key必须是能进行比较的。
                    return obj2.compareTo(obj1);
                }
            });
            for (Map.Entry<String, Integer> entry : mapList.entrySet()) {
                map.put(entry.getKey(),entry.getValue());
            }
            // 这里将map.entrySet()转换成list
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
                    map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                // 降序排序
                public int compare(Map.Entry<String, Integer> o1,
                                   Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            Integer k = 0;
            for (Map.Entry<String, Integer> mapping : list) {
                System.out.println(mapping.getKey() + ":" + mapping.getValue());
                mapOut.put(mapping.getKey(),mapping.getValue());
                ++k;
                if(k == num){break;}
            }
            System.out.println("********");

            typeModel.setStreet_data(streetModelList);
            typeModel.setRank_data(mapOut);
            mapType.put(hospitalHumanFlow_2[i], typeModel);
            mapTypeList.add(mapType);
        }
        return mapTypeList;
    }
}
