package com.cetccity.operationcenter.webframework.web.controller.weiji;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Description：医院排行
 * Created by luolinjie on 2018/5/25.
 */

@Api(tags = "获取医院排行")
@RestController
public class HospitalRank1 {

    private static final Logger logger = LoggerFactory.getLogger(HospitalRank1.class);

    @Autowired
    OracleOperateService oracleOperateService;

    @ApiOperation(value = "获取医院排行",notes = "", produces = "application/json")
    @RequestMapping(value = "/weiji1/getHospitalRank", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public LinkedHashMap getHospitalRank(){
        LinkedHashMap result = new LinkedHashMap();
//        String bed = "tb_weiji_organization";/*床位数*/
        String bed = "YJJC_QWJJ_ORG_V"; /*oralce 床位数*/
        List<LinkedHashMap> bedAllData = null;/*床位数表 所有数据*/
//        String out = "tb_weiji_out_visits_day";/*门诊人次*/
        String out = "YJJC_QWJJ_OUT_VISITS_DAY_V";/*oracle 门诊人次*/
        List<LinkedHashMap> outAlldata = null;/*门诊人次表 所有数据*/
//        String in = "tb_weiji_in_visits_day";/*住院人次*/
        String in = "YJJC_QWJJ_IN_VISITS_DAY_V"; /*oracle住院人次*/
        List<LinkedHashMap> inAlldata = null;/*住院人次表 所有数据*/

        /*统计床位数*/
        LinkedHashMap bedNumberHashMap = new LinkedHashMap();
        bedAllData = oracleOperateService.queryAllDataByTableName(bed);
        /*过滤最近一天的数据*/
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(0);
        arrayList.add(0);
        arrayList.add(0);
        /*找top 1*/
        for (int i = 0; i < bedAllData.size(); ++i){
            String date = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);
            if (year > arrayList.get(0)){
                arrayList.set(0,year);
                arrayList.set(1,month);
                arrayList.set(2,day);
            }else if (year == arrayList.get(0) && month > arrayList.get(1)){
                arrayList.set(0,year);
                arrayList.set(1,month);
                arrayList.set(2,day);
            }else if (year == arrayList.get(0) && month == arrayList.get(1) && day > arrayList.get(2)){
                arrayList.set(0,year);
                arrayList.set(1,month);
                arrayList.set(2,day);
            }
        }
        ArrayList<LinkedHashMap> lastDayListHashMap = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);
            /*过滤最近一天的数据*/
            if (year == arrayList.get(0) && month == arrayList.get(1) && day == arrayList.get(2)){
                /*分类*/
                String name = bedAllData.get(i).get("ORG_NAME").toString();
                int bedNum = Integer.parseInt(bedAllData.get(i).get("BED").toString());
                boolean exist = false;

                for (int j = 0; j < lastDayListHashMap.size(); ++j){
                    if(lastDayListHashMap.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap.get(j).put("value",Integer.parseInt(lastDayListHashMap.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }

                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap.add(linkedHashMap);
                }
            }
        }
        /*根据值排序*/
        for (int i = 0; i < lastDayListHashMap.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap.get(i).clone();
                    lastDayListHashMap.set(i, lastDayListHashMap.get(j));
                    lastDayListHashMap.set(j, linkedHashMap);
                }
            }
        }
        bedNumberHashMap.put("昨日", lastDayListHashMap);
        /*过滤最近一周数据*/
        ArrayList<LinkedHashMap> lastDayListHashMap1 = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);

            if (inWeek(year, month, day, arrayList)){
                /*分类*/
                String name = bedAllData.get(i).get("ORG_NAME").toString();
                int bedNum = Integer.parseInt(bedAllData.get(i).get("BED").toString());
                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap1.size(); ++j){
                    if(lastDayListHashMap1.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap1.get(j).put("value",Integer.parseInt(lastDayListHashMap1.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap1.add(linkedHashMap);
                }
            }
        }
        /*排序*/
        for (int i = 0; i < lastDayListHashMap1.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap1.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap1.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap1.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap1.get(i).clone();
                    lastDayListHashMap1.set(i, lastDayListHashMap1.get(j));
                    lastDayListHashMap1.set(j, linkedHashMap);
                }
            }
        }
        bedNumberHashMap.put("周",lastDayListHashMap1);

         /*过滤最近一月数据*/
        ArrayList<LinkedHashMap> lastDayListHashMap2 = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);

            if (inMonth(year, month, day, arrayList)){
                /*分类*/
                String name = bedAllData.get(i).get("ORG_NAME").toString();
                int bedNum = Integer.parseInt(bedAllData.get(i).get("BED").toString());
                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap2.size(); ++j){
                    if(lastDayListHashMap2.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap2.get(j).put("value",Integer.parseInt(lastDayListHashMap2.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap2.add(linkedHashMap);
                }
            }
        }
        /*排序*/
        for (int i = 0; i < lastDayListHashMap2.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap2.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap2.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap2.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap2.get(i).clone();
                    lastDayListHashMap2.set(i, lastDayListHashMap2.get(j));
                    lastDayListHashMap2.set(j, linkedHashMap);
                }
            }
        }
        bedNumberHashMap.put("月",lastDayListHashMap2);


        /*过滤最近一年数据*/
        ArrayList<LinkedHashMap> lastDayListHashMap3 = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);

            if (inYear(year, month, day, arrayList)){
                /*分类*/
                String name = bedAllData.get(i).get("ORG_NAME").toString();
                int bedNum = Integer.parseInt(bedAllData.get(i).get("BED").toString());
                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap3.size(); ++j){
                    if(lastDayListHashMap3.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap3.get(j).put("value",Integer.parseInt(lastDayListHashMap3.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap3.add(linkedHashMap);
                }
            }
        }
        /*排序*/
        for (int i = 0; i < lastDayListHashMap3.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap3.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap3.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap3.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap3.get(i).clone();
                    lastDayListHashMap3.set(i, lastDayListHashMap3.get(j));
                    lastDayListHashMap3.set(j, linkedHashMap);
                }
            }
        }
        bedNumberHashMap.put("年",lastDayListHashMap3);
        result.put("床位数",bedNumberHashMap);

        /*门急诊人次*/
        LinkedHashMap outNumberHashMap = new LinkedHashMap();
        bedAllData = oracleOperateService.queryAllDataByTableName(out);
        /*过滤最近一天的数据*/
        /*过滤最近一周的数据*/
        arrayList = new ArrayList();
        arrayList.add(0);
        arrayList.add(0);
        arrayList.add(0);
        /*找top 1*/
        for (int i = 0; i < bedAllData.size(); ++i){
            String date0 = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            String date = date0.replaceAll("-", "/");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);
            if (year > arrayList.get(0)){
                arrayList.set(0,year);
                arrayList.set(1,month);
                arrayList.set(2,day);
            }else if (year == arrayList.get(0) && month > arrayList.get(1)){
                arrayList.set(0,year);
                arrayList.set(1,month);
                arrayList.set(2,day);
            }else if (year == arrayList.get(0) && month == arrayList.get(1) && day > arrayList.get(2)){
                arrayList.set(0,year);
                arrayList.set(1,month);
                arrayList.set(2,day);
            }
        }
        ArrayList<LinkedHashMap> lastDayListHashMap_out = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date0 = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            String date = date0.replaceAll("-", "/");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);
            /*过滤最近一天的数据*/
            if (year == arrayList.get(0) && month == arrayList.get(1) && day == arrayList.get(2)){
                /*分类*/
                String name = (String) bedAllData.get(i).get("ORG_NAME");
//                int bedNum = Integer.parseInt(bedAllData.get(i).get("outpatient_emergency_no").toString());/* 门诊人数*/

                int bedNum = Integer.parseInt(bedAllData.get(i).get("EMERGENCY_EMERGENCY_NO").toString());/*oracle  门诊人数*/
                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap_out.size(); ++j){
                    if(lastDayListHashMap_out.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap_out.get(j).put("value",Integer.parseInt(lastDayListHashMap_out.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap_out.add(linkedHashMap);
                }
            }
        }
        /*根据值排序*/
        for (int i = 0; i < lastDayListHashMap_out.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap_out.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap_out.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap_out.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap_out.get(i).clone();
                    lastDayListHashMap_out.set(i, lastDayListHashMap_out.get(j));
                    lastDayListHashMap_out.set(j, linkedHashMap);
                }
            }
        }
        outNumberHashMap.put("昨日", lastDayListHashMap_out);
        /*过滤最近一周数据*/
        ArrayList<LinkedHashMap> lastDayListHashMap_out1 = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date0 = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            String date = date0.replaceAll("-", "/");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);

            if (inWeek(year, month, day, arrayList)){
                /*分类*/
                String name = (String) bedAllData.get(i).get("ORG_NAME");
//                int bedNum = Integer.parseInt(bedAllData.get(i).get("outpatient_emergency_no").toString());
                int bedNum = Integer.parseInt(bedAllData.get(i).get("EMERGENCY_EMERGENCY_NO").toString());/*oracle  门诊人数*/

                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap_out1.size(); ++j){
                    if(lastDayListHashMap_out1.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap_out1.get(j).put("value",Integer.parseInt(lastDayListHashMap_out1.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap_out1.add(linkedHashMap);
                }
            }
        }
        /*排序*/
        for (int i = 0; i < lastDayListHashMap_out1.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap_out1.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap_out1.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap_out1.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap_out1.get(i).clone();
                    lastDayListHashMap_out1.set(i, lastDayListHashMap_out1.get(j));
                    lastDayListHashMap_out1.set(j, linkedHashMap);
                }
            }
        }
        outNumberHashMap.put("周",lastDayListHashMap_out1);

         /*过滤最近一月数据*/
        ArrayList<LinkedHashMap> lastDayListHashMap_out2 = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date0 = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            String date = date0.replaceAll("-", "/");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);

            if (inMonth(year, month, day, arrayList)){
                /*分类*/
                String name = bedAllData.get(i).get("ORG_NAME").toString();
//                int bedNum = Integer.parseInt(bedAllData.get(i).get("outpatient_emergency_no").toString());
                int bedNum = Integer.parseInt(bedAllData.get(i).get("EMERGENCY_EMERGENCY_NO").toString());/*oracle  门诊人数*/

                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap_out2.size(); ++j){
                    if(lastDayListHashMap_out2.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap_out2.get(j).put("value",Integer.parseInt(lastDayListHashMap_out2.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap_out2.add(linkedHashMap);
                }
            }
        }
        /*排序*/
        for (int i = 0; i < lastDayListHashMap_out2.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap_out2.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap_out2.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap_out2.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap_out2.get(i).clone();
                    lastDayListHashMap_out2.set(i, lastDayListHashMap_out2.get(j));
                    lastDayListHashMap_out2.set(j, linkedHashMap);
                }
            }
        }
        outNumberHashMap.put("月",lastDayListHashMap_out2);


        /*过滤最近一年数据*/
        ArrayList<LinkedHashMap> lastDayListHashMap_out3 = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date0 = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            String date = date0.replaceAll("-", "/");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);

            if (inYear(year, month, day, arrayList)){
                /*分类*/
                String name = bedAllData.get(i).get("ORG_NAME").toString();
//                int bedNum = Integer.parseInt(bedAllData.get(i).get("outpatient_emergency_no").toString());
                int bedNum = Integer.parseInt(bedAllData.get(i).get("EMERGENCY_EMERGENCY_NO").toString());/*oracle  门诊人数*/

                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap_out3.size(); ++j){
                    if(lastDayListHashMap_out3.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap_out3.get(j).put("value",Integer.parseInt(lastDayListHashMap_out3.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap_out3.add(linkedHashMap);
                }
            }
        }
        /*排序*/
        for (int i = 0; i < lastDayListHashMap_out3.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap_out3.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap_out3.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap_out3.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap_out3.get(i).clone();
                    lastDayListHashMap_out3.set(i, lastDayListHashMap_out3.get(j));
                    lastDayListHashMap_out3.set(j, linkedHashMap);
                }
            }
        }
        outNumberHashMap.put("年",lastDayListHashMap_out3);
        result.put("门急诊人次",outNumberHashMap);

        /*住院人次*/
        LinkedHashMap inNumberHashMap = new LinkedHashMap();
        bedAllData = oracleOperateService.queryAllDataByTableName(in);
        /*过滤最近一天的数据*/
        /*过滤最近一周的数据*/
        arrayList = new ArrayList();
        arrayList.add(0);
        arrayList.add(0);
        arrayList.add(0);
        /*找top 1*/
        for (int i = 0; i < bedAllData.size(); ++i){
            String date0 = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            String date = date0.replaceAll("-", "/");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);
            if (year > arrayList.get(0)){
                arrayList.set(0,year);
                arrayList.set(1,month);
                arrayList.set(2,day);
            }else if (year == arrayList.get(0) && month > arrayList.get(1)){
                arrayList.set(0,year);
                arrayList.set(1,month);
                arrayList.set(2,day);
            }else if (year == arrayList.get(0) && month == arrayList.get(1) && day > arrayList.get(2)){
                arrayList.set(0,year);
                arrayList.set(1,month);
                arrayList.set(2,day);
            }
        }
        ArrayList<LinkedHashMap> lastDayListHashMap_in = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date0 = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            String date = date0.replaceAll("-", "/");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);
            /*过滤最近一天的数据*/
            if (year == arrayList.get(0) && month == arrayList.get(1) && day == arrayList.get(2)){
                /*分类*/
                String name = bedAllData.get(i).get("ORG_NAME").toString();
                int bedNum = Integer.parseInt(bedAllData.get(i).get("IN_H_NO").toString());
                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap_in.size(); ++j){
                    if(lastDayListHashMap_in.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap_in.get(j).put("value",Integer.parseInt(lastDayListHashMap_in.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap_in.add(linkedHashMap);
                }
            }
        }
        /*根据值排序*/
        for (int i = 0; i < lastDayListHashMap_in.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap_in.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap_in.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap_in.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap_in.get(i).clone();
                    lastDayListHashMap_in.set(i, lastDayListHashMap_in.get(j));
                    lastDayListHashMap_in.set(j, linkedHashMap);
                }
            }
        }
        inNumberHashMap.put("昨日", lastDayListHashMap_in);
        /*过滤最近一周数据*/
        ArrayList<LinkedHashMap> lastDayListHashMap_in1 = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date0 = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            String date = date0.replaceAll("-","/");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);

            if (inWeek(year, month, day, arrayList)){
                /*分类*/
                String name = bedAllData.get(i).get("ORG_NAME").toString();
                int bedNum = Integer.parseInt(bedAllData.get(i).get("IN_H_NO").toString());
                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap_in1.size(); ++j){
                    if(lastDayListHashMap_in1.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap_in1.get(j).put("value",Integer.parseInt(lastDayListHashMap_in1.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap_in1.add(linkedHashMap);
                }
            }
        }
        /*排序*/
        for (int i = 0; i < lastDayListHashMap_in1.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap_in1.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap_in1.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap_in1.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap_in1.get(i).clone();
                    lastDayListHashMap_in1.set(i, lastDayListHashMap_in1.get(j));
                    lastDayListHashMap_in1.set(j, linkedHashMap);
                }
            }
        }
        inNumberHashMap.put("周",lastDayListHashMap_in1);

         /*过滤最近一月数据*/
        ArrayList<LinkedHashMap> lastDayListHashMap_in2 = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date0 = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            String date = date0.replaceAll("-","/");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);

            if (inMonth(year, month, day, arrayList)){
                /*分类*/
                String name = bedAllData.get(i).get("ORG_NAME").toString();
                int bedNum = Integer.parseInt(bedAllData.get(i).get("IN_H_NO").toString());
                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap_in2.size(); ++j){
                    if(lastDayListHashMap_in2.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap_in2.get(j).put("value",Integer.parseInt(lastDayListHashMap_in2.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap_in2.add(linkedHashMap);
                }
            }
        }
        /*排序*/
        for (int i = 0; i < lastDayListHashMap_in2.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap_in2.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap_in2.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap_in2.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap_in2.get(i).clone();
                    lastDayListHashMap_in2.set(i, lastDayListHashMap_in2.get(j));
                    lastDayListHashMap_in2.set(j, linkedHashMap);
                }
            }
        }
        inNumberHashMap.put("月",lastDayListHashMap_in2);


        /*过滤最近一年数据*/
        ArrayList<LinkedHashMap> lastDayListHashMap_in3 = new ArrayList<LinkedHashMap>();
        for (int i = 0; i < bedAllData.size(); ++i){
            String date0 = (String) bedAllData.get(i).get("REC_CREATE_TIME");
            String date = date0.replaceAll("-","/");
            int year = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]);
            int day = Integer.parseInt(date.split("/")[2].split(" ")[0]);

            if (inYear(year, month, day, arrayList)){
                /*分类*/
                String name = bedAllData.get(i).get("ORG_NAME").toString();
                int bedNum = Integer.parseInt(bedAllData.get(i).get("IN_H_NO").toString());
                boolean exist = false;
                for (int j = 0; j < lastDayListHashMap_in3.size(); ++j){
                    if(lastDayListHashMap_in3.get(j).get("name").toString().equalsIgnoreCase(name)){
                        exist = true;
                        lastDayListHashMap_in3.get(j).put("value",Integer.parseInt(lastDayListHashMap_in3.get(j).get("value").toString())+bedNum);
                        break;
                    }
                }
                if(!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",bedNum);
                    linkedHashMap.put("name",name);
                    lastDayListHashMap_in3.add(linkedHashMap);
                }
            }
        }
        /*排序*/
        for (int i = 0; i < lastDayListHashMap_in3.size(); ++ i){
            for (int j = i + 1; j < lastDayListHashMap_in3.size(); ++j){
                int iValue = Integer.parseInt(lastDayListHashMap_in3.get(i).get("value").toString());
                int jValue = Integer.parseInt(lastDayListHashMap_in3.get(j).get("value").toString());
                if (iValue < jValue){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) lastDayListHashMap_in3.get(i).clone();
                    lastDayListHashMap_in3.set(i, lastDayListHashMap_in3.get(j));
                    lastDayListHashMap_in3.set(j, linkedHashMap);
                }
            }
        }
        inNumberHashMap.put("年",lastDayListHashMap_in3);
        result.put("住院数",inNumberHashMap);

        return result;
    }

    /**
     * 判断两个时间相差是否在一周以内
     * @param year
     * @param month
     * @param day
     * @param arrayList 最近一天的时间
     * @return
     */
    private boolean inWeek(int year, int month, int day, ArrayList<Integer> arrayList){
        boolean result = false;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(arrayList.get(0), arrayList.get(1), arrayList.get(2));
        long time = calendar1.getTimeInMillis() - calendar.getTimeInMillis();
        if (time <= 7*24*3600*1000L){
            return true;
        }
        return result;
    }
    /**
     * 判断两个时间相差是否在一月以内
     * @param year
     * @param month
     * @param day
     * @param arrayList
     * @return
     */
    private boolean inMonth(int year, int month, int day, ArrayList<Integer> arrayList){
        boolean result = false;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(arrayList.get(0), arrayList.get(1), arrayList.get(2));
        long time = calendar1.getTimeInMillis() - calendar.getTimeInMillis();
        if (time <= 30*24*3600*1000L){
            return true;
        }
        return result;
    }
    /**
     * 判断两个时间相差是否在一年以内
     * @param year
     * @param month
     * @param day
     * @param arrayList
     * @return
     */
    private boolean inYear(int year, int month, int day, ArrayList<Integer> arrayList){
        boolean result = false;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(arrayList.get(0), arrayList.get(1), arrayList.get(2));
        long time = calendar1.getTimeInMillis() - calendar.getTimeInMillis();
        if (time <= 365*24*3600*1000L){
            return true;
        }
        return result;
    }
}
