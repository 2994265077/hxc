package com.cetccity.operationcenter.webframework.web.controller.weiji;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.web.util.DataProcessUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description：卫计-统计分析
 * Created by luolinjie on 2018/5/30.
 */
@Api(tags = "卫计-统计分析")
@RestController
@RequestMapping("/weiji")
public class StatisticalAnalysis {
    private static final Logger logger = LoggerFactory.getLogger(StatisticalAnalysis.class);

    @Autowired
    OracleOperateService oracleOperateService;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");

    private static final String timeField = "REC_CREATE_TIME";
    private static final String timeField_sdm = "DIAG_TIME";

    @ApiOperation(value = "床位数-按街道分组统计",notes = "", produces = "application/json")
    @RequestMapping(value = "/getHospitalBedNumGroupByStreet", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public HashMap getHospitalBedNumGroupByStreet() {
        HashMap res = new HashMap();

        List<LinkedHashMap> organizationList = oracleOperateService.queryAllDataByTableName("YJJC_QWJJ_ORG_V");

        List<HashMap> bedList = new ArrayList();
        List<HashMap> freebedList = new ArrayList();

        //todo:根据街道筛选统计
        for (int i = 0; i < organizationList.size(); i++) {
            String street = (String) organizationList.get(i).get("STREET");
            int freebed = Integer.parseInt((String) organizationList.get(i).get("FREE_BED"));
            int bed = Integer.parseInt((String) organizationList.get(i).get("BED"));

            boolean isExists = false;
            //遍历结果集中是否存在street key，若存在就对其value+1，若不存在就添加这个记录
            for (int j = 0; j < bedList.size(); j++) {
                if (bedList.get(j).get("name").equals(street)) {
                    isExists = true;
                    bedList.get(j).put("value", Integer.parseInt((String) organizationList.get(j).get("BED")) + bed);
                    freebedList.get(j).put("value", Integer.parseInt((String) organizationList.get(j).get("FREE_BED")) + freebed);
                    break;
                }
            }
            if (!isExists) {
                HashMap linkedHashMap = new HashMap();
                linkedHashMap.put("value", bed);
                linkedHashMap.put("name", street);
                bedList.add(linkedHashMap);

                HashMap linkedHashMap1 = new HashMap();
                linkedHashMap1.put("value", freebed);
                linkedHashMap1.put("name", street);
                freebedList.add(linkedHashMap1);
            }
        }

        int totalBedNum = 0;
        int totalFreeBedNum = 0;
        for (HashMap map : bedList) {
            totalBedNum += (Integer) map.get("value");
            totalFreeBedNum += (Integer) map.get("value");
        }

        HashMap totalbedMap = new HashMap();
        HashMap freebedMap = new HashMap();

        totalbedMap.put("totalNum", totalBedNum);
        totalbedMap.put("name", "床位数");
        totalbedMap.put("data", bedList);

        freebedMap.put("totalNum", totalFreeBedNum);
        freebedMap.put("name", "空闲床位数");
        freebedMap.put("data", freebedList);

        res.put("total", totalbedMap);
        res.put("free", freebedMap);
        res.put("name", "床位统计");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String dateStr = format.format(Calendar.getInstance().getTime());
        res.put("time", dateStr);
        return res;
    }
    @ApiOperation(value = "门急诊-按街道分组统计",notes = "", produces = "application/json")
    @RequestMapping(value = "/getHospitalMenjizhenGroupByDate", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public HashMap getHospitalMenjizhenGroupByDate() throws ParseException {
        HashMap res = new HashMap();
        String valueField = "EMERGENCY_EMERGENCY_NO";
//        List<LinkedHashMap> organizationList = oracleOperateService.queryAllDataByTableName("yjjc_qwjj_out_visits_day_v");

        /*通过关联，加上street字段*/
        String sql = "SELECT DISTINCT a.\"ID\" , a.\""+valueField+"\", a.\"REC_CREATE_TIME\", b.\"STREET\" \n" +
                "FROM \"YJJC_QWJJ_OUT_VISITS_DAY_V\" a, \"YJJC_QWJJ_ORG_V\" b\n" +
                "WHERE a.\"ORG_NAME\"=b.\"ORG_NAME\"\n" +
                "AND a.\"DEPT_NAME\"=b.\"DEPT_NAME\"\n" +
                "AND a.\""+valueField+"\">=0";
        List<LinkedHashMap> organizationList = oracleOperateService.querySql(sql);


        Date newestDate = null;


        //获取最新记录时间
        Collections.sort(organizationList, new Comparator<HashMap>() {
            @Override
            public int compare(HashMap o1, HashMap o2) {
                String time1 = ((String) o1.get(StatisticalAnalysis.timeField));
                String time2 = ((String) o2.get(StatisticalAnalysis.timeField));

                time1 = time1.replaceAll("/", "-");
                time2 = time2.replaceAll("/", "-");
                Date parse1 = null;
                Date parse2 = null;
                try {
                    parse1 = format.parse(time1);
                    parse2 = format.parse(time2);
                } catch (ParseException e) {
                    logger.error("format error!\n", e);
                }
                if (parse1.equals(parse2)) return 0;

                return parse1.before(parse2) ? -1 : 1;
            }
        });
        newestDate = format.parse(ESOperate.formateDate((String) organizationList.get(organizationList.size() - 1).get(StatisticalAnalysis.timeField)));


        //TODO:全区按时间统计
        //过滤最近5年
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年");
        LinkedList<HashMap> yearList = filterByDate(organizationList,valueField, 365 * 5, newestDate, format2,StatisticalAnalysis.timeField);
        //过滤最近5月
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy年MM月");
        LinkedList<HashMap> monthList = filterByDate(organizationList,valueField, 30 * 5, newestDate, format3,StatisticalAnalysis.timeField);
        //过滤最近5天
        SimpleDateFormat format4 = new SimpleDateFormat("MM月dd日");
        LinkedList<HashMap> dayIn5List = filterByDate(organizationList,valueField, 5, newestDate, format4,StatisticalAnalysis.timeField);

        //分时统计
        List<HashMap> groupByTime_5year = groupByTime(yearList);
        HashMap last5year = new HashMap();
        last5year.put("timeInterval", "年");
        last5year.put("data", groupByTime_5year);

        List<HashMap> groupByTime_5month = groupByTime(monthList);
        HashMap last5month = new HashMap();
        last5month.put("timeInterval", "月");
        last5month.put("data", groupByTime_5month);

        List<HashMap> groupByTime_5day = groupByTime(dayIn5List);
        HashMap last5days = new HashMap();
        last5days.put("timeInterval", "近5日");
        last5days.put("data", groupByTime_5day);

        HashMap region = new HashMap();

        ArrayList allregionDateDataList = new ArrayList();
        allregionDateDataList.add(last5days);
        allregionDateDataList.add(last5month);
        allregionDateDataList.add(last5year);

        region.put("name", "全区");
        region.put("details", allregionDateDataList);

        //TODO：分街道统计
        LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet_5year = groupByStreet(yearList, format2, "year");
        HashMap last5year2 = new HashMap();
        last5year2.put("timeInterval", "年");
        last5year2.put("data", groupByStreet_5year);

        LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet_5month = groupByStreet(monthList, format3, "month");
        HashMap last5month2 = new HashMap();
        last5month2.put("timeInterval", "月");
        last5month2.put("data", groupByStreet_5month);

        LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet_5day = groupByStreet(dayIn5List, format4, "day");
        HashMap last5days2 = new HashMap();
        last5days2.put("timeInterval", "近5日");
        last5days2.put("data", groupByStreet_5day);


        ArrayList allStreetGroupByDateDataList = new ArrayList();
        allStreetGroupByDateDataList.add(last5days2);
        allStreetGroupByDateDataList.add(last5month2);
        allStreetGroupByDateDataList.add(last5year2);

        HashMap allstreet = new HashMap();

        allstreet.put("name", "各街道");
        allstreet.put("details", allStreetGroupByDateDataList);

        //TODO：汇总
        LinkedList reslinkedList = new LinkedList();
        reslinkedList.add(region);
        reslinkedList.add(allstreet);
        res.put("name", "门急诊人次");
        res.put("data", reslinkedList);

        return res;
    }
    @ApiOperation(value = "住院人数-按街道分组统计",notes = "", produces = "application/json")
    @RequestMapping(value = "/getInHospitalGroupByDate", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public HashMap getInHospitalGroupByDate() throws ParseException {
        HashMap res = new HashMap();

        String valueField = "IN_H_NO";
//        List<LinkedHashMap> organizationList = oracleOperateService.queryAllDataByTableName("yjjc_qwjj_out_visits_day_v");

        /*通过关联，加上street字段*/
        String sql = "SELECT DISTINCT a.\"ID\" , a.\""+valueField+"\", a.\"REC_CREATE_TIME\", b.\"STREET\" \n" +
                    "FROM \"YJJC_QWJJ_IN_INFO_V\" a, \"YJJC_QWJJ_ORG_V\" b\n" +
                    "WHERE a.\"ORG_NAME\"=b.\"ORG_NAME\"\n" +
                    "AND a.\"DEPT_NAME\"=b.\"DEPT_NAME\"\n" +
                    "AND a.\""+valueField+"\">=0";
        List<LinkedHashMap> organizationList = oracleOperateService.querySql(sql);


        Date newestDate = null;


        //获取最新记录时间
        Collections.sort(organizationList, new Comparator<HashMap>() {
            @Override
            public int compare(HashMap o1, HashMap o2) {
                String time1 = ((String) o1.get(StatisticalAnalysis.timeField));
                String time2 = ((String) o2.get(StatisticalAnalysis.timeField));

                time1 = time1.replaceAll("/", "-");
                time2 = time2.replaceAll("/", "-");
                Date parse1 = null;
                Date parse2 = null;
                try {
                    parse1 = format.parse(time1);
                    parse2 = format.parse(time2);
                } catch (ParseException e) {
                    logger.error("format error!\n", e);
                }
                if (parse1.equals(parse2)) return 0;

                return parse1.before(parse2) ? -1 : 1;
            }
        });
        newestDate = format.parse(ESOperate.formateDate((String) organizationList.get(organizationList.size() - 1).get(StatisticalAnalysis.timeField)));


        //TODO:全区按时间统计
        //过滤最近5年
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年");
        LinkedList<HashMap> yearList = filterByDate(organizationList,valueField, 365 * 5, newestDate, format2,StatisticalAnalysis.timeField);
        //过滤最近5月
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy年MM月");
        LinkedList<HashMap> monthList = filterByDate(organizationList,valueField, 30 * 5, newestDate, format3,StatisticalAnalysis.timeField);
        //过滤最近5天
        SimpleDateFormat format4 = new SimpleDateFormat("MM月dd日");
        LinkedList<HashMap> dayIn5List = filterByDate(organizationList,valueField, 5, newestDate, format4,StatisticalAnalysis.timeField);

        //分时统计
        List<HashMap> groupByTime_5year = groupByTime(yearList);
        HashMap last5year = new HashMap();
        last5year.put("timeInterval", "年");
        last5year.put("data", groupByTime_5year);

        List<HashMap> groupByTime_5month = groupByTime(monthList);
        HashMap last5month = new HashMap();
        last5month.put("timeInterval", "月");
        last5month.put("data", groupByTime_5month);

        List<HashMap> groupByTime_5day = groupByTime(dayIn5List);
        HashMap last5days = new HashMap();
        last5days.put("timeInterval", "近5日");
        last5days.put("data", groupByTime_5day);

        HashMap region = new HashMap();

        ArrayList allregionDateDataList = new ArrayList();
        allregionDateDataList.add(last5days);
        allregionDateDataList.add(last5month);
        allregionDateDataList.add(last5year);

        region.put("name", "全区");
        region.put("details", allregionDateDataList);

        //TODO：分街道统计
        LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet_5year = groupByStreet(yearList, format2, "year");
        HashMap last5year2 = new HashMap();
        last5year2.put("timeInterval", "年");
        last5year2.put("data", groupByStreet_5year);

        LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet_5month = groupByStreet(monthList, format3, "month");
        HashMap last5month2 = new HashMap();
        last5month2.put("timeInterval", "月");
        last5month2.put("data", groupByStreet_5month);

        LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet_5day = groupByStreet(dayIn5List, format4, "day");
        HashMap last5days2 = new HashMap();
        last5days2.put("timeInterval", "近5日");
        last5days2.put("data", groupByStreet_5day);


        ArrayList allStreetGroupByDateDataList = new ArrayList();
        allStreetGroupByDateDataList.add(last5days2);
        allStreetGroupByDateDataList.add(last5month2);
        allStreetGroupByDateDataList.add(last5year2);

        HashMap allstreet = new HashMap();

        allstreet.put("name", "各街道");
        allstreet.put("details", allStreetGroupByDateDataList);

        //TODO：汇总
        LinkedList reslinkedList = new LinkedList();
        reslinkedList.add(region);
        reslinkedList.add(allstreet);
        res.put("name", "住院人数");
        res.put("data", reslinkedList);

        return res;
    }

    /**
     * 病例统计： 发热、腹泻、外伤
     * @param caseType
     *                  1.发热
     *                  2.腹泻
     *                  3.外伤
     * @return
     * @throws ParseException
     */
    @ApiOperation(value = "特殊病种-按街道分组统计",notes = "病种类型：1.发热，2.腹泻，3.外伤", produces = "application/json")
    @ApiImplicitParam(name = "caseType", value = "病种类型(1.发热，2.腹泻，3.外伤)", required = true, dataType = "int", paramType = "query" , example="1")
    @RequestMapping(value = "/getSpecialCaseGroupByDate", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public HashMap getSpecialCaseGroupByDate(int caseType) throws ParseException {
        HashMap res = new HashMap();

        String valueField = "value";
        String caseTypeName = null;
        if (caseType==1) {
            caseTypeName ="发热";
        }else if(caseType==2){
            caseTypeName ="腹泻";
        }else if(caseType==3){
            caseTypeName ="外伤";
        }

        /*通过关联，加上street字段*/
        String sql = "SELECT DISTINCT a.\"ID\" , a.\"DIAG_TIME\" as \"REC_CREATE_TIME\", b.\"STREET\",1 as \"value\"\n" +
                "FROM \"YJJC_QWJJ_SDM_INFO_V\" a, \"YJJC_QWJJ_ORG_V\" b\n" +
                "WHERE a.\"ORG_NAME\"=b.\"ORG_NAME\" \n" +
                "AND a.\"DEPT_NAME\"=b.\"DEPT_NAME\" \n" +
                "AND a.\"DIAG_NAME_INHOS\" LIKE '%"+caseTypeName+"%'";
        List<LinkedHashMap> organizationList = oracleOperateService.querySql(sql);


        Date newestDate = null;


        //获取最新记录时间
        Collections.sort(organizationList, new Comparator<HashMap>() {
            @Override
            public int compare(HashMap o1, HashMap o2) {
                String time1 = ((String) o1.get(StatisticalAnalysis.timeField));
                String time2 = ((String) o2.get(StatisticalAnalysis.timeField));

                time1 = time1.replaceAll("/", "-");
                time2 = time2.replaceAll("/", "-");
                Date parse1 = null;
                Date parse2 = null;
                try {
                    parse1 = format.parse(time1);
                    parse2 = format.parse(time2);
                } catch (ParseException e) {
                    logger.error("format error!\n", e);
                }
                if (parse1.equals(parse2)) return 0;

                return parse1.before(parse2) ? -1 : 1;
            }
        });
        newestDate = format.parse(ESOperate.formateDate((String) organizationList.get(organizationList.size() - 1).get(StatisticalAnalysis.timeField)));


        //TODO:全区按时间统计
        //过滤最近5年
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年");
        LinkedList<HashMap> yearList = filterByDate(organizationList,valueField, 365 * 5, newestDate, format2,StatisticalAnalysis.timeField);
        //过滤最近5月
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy年MM月");
        LinkedList<HashMap> monthList = filterByDate(organizationList,valueField, 30 * 5, newestDate, format3,StatisticalAnalysis.timeField);
        //过滤最近5天
        SimpleDateFormat format4 = new SimpleDateFormat("MM月dd日");
        LinkedList<HashMap> dayIn5List = filterByDate(organizationList,valueField, 5, newestDate, format4,StatisticalAnalysis.timeField);

        //分时统计
        List<HashMap> groupByTime_5year = groupByTime(yearList);
        HashMap last5year = new HashMap();
        last5year.put("timeInterval", "年");
        last5year.put("data", groupByTime_5year);

        List<HashMap> groupByTime_5month = groupByTime(monthList);
        HashMap last5month = new HashMap();
        last5month.put("timeInterval", "月");
        last5month.put("data", groupByTime_5month);

        List<HashMap> groupByTime_5day = groupByTime(dayIn5List);
        HashMap last5days = new HashMap();
        last5days.put("timeInterval", "近5日");
        last5days.put("data", groupByTime_5day);

        HashMap region = new HashMap();

        ArrayList allregionDateDataList = new ArrayList();
        allregionDateDataList.add(last5days);
        allregionDateDataList.add(last5month);
        allregionDateDataList.add(last5year);

        region.put("name", "全区");
        region.put("details", allregionDateDataList);

        //TODO：分街道统计
        LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet_5year = groupByStreet(yearList, format2, "year");
        HashMap last5year2 = new HashMap();
        last5year2.put("timeInterval", "年");
        last5year2.put("data", groupByStreet_5year);

        LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet_5month = groupByStreet(monthList, format3, "month");
        HashMap last5month2 = new HashMap();
        last5month2.put("timeInterval", "月");
        last5month2.put("data", groupByStreet_5month);

        LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet_5day = groupByStreet(dayIn5List, format4, "day");
        HashMap last5days2 = new HashMap();
        last5days2.put("timeInterval", "近5日");
        last5days2.put("data", groupByStreet_5day);


        ArrayList allStreetGroupByDateDataList = new ArrayList();
        allStreetGroupByDateDataList.add(last5days2);
        allStreetGroupByDateDataList.add(last5month2);
        allStreetGroupByDateDataList.add(last5year2);

        HashMap allstreet = new HashMap();

        allstreet.put("name", "各街道");
        allstreet.put("details", allStreetGroupByDateDataList);

        //TODO：汇总
        LinkedList reslinkedList = new LinkedList();
        reslinkedList.add(region);
        reslinkedList.add(allstreet);
        res.put("name", caseTypeName);
        res.put("data", reslinkedList);

        return res;
    }


    private LinkedList<HashMap> filterByDate(List<LinkedHashMap> organizationList,String valueField, int days, Date newestDate, SimpleDateFormat format2,String timeField) throws ParseException {
        LinkedList<HashMap> filterdList = new LinkedList<HashMap>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        long millisArea = days * 24L * 3600L * 1000L;
        for (HashMap map : organizationList) {
            Date createDate = format.parse(ESOperate.formateDate((String) map.get(timeField)));
            if (newestDate.getTime() - createDate.getTime() < millisArea) {
                HashMap hashMap = new HashMap();
                hashMap.put("time", format2.format(createDate));
                hashMap.put("value", Integer.parseInt((String) map.get(valueField)));
                hashMap.put("STREET", map.get("STREET"));
                filterdList.add(hashMap);
            }
        }

        return filterdList;

    }

    private LinkedList<HashMap> groupByTime(LinkedList<HashMap> organizationList) throws ParseException {

        LinkedList<HashMap> resultList = new LinkedList<HashMap>();
        for (int i = 0; i < organizationList.size(); i++) {
            String time = (String) organizationList.get(i).get("time");
            int value = Integer.parseInt(String.valueOf(organizationList.get(i).get("value")));

            boolean isExists = false;
            //遍历结果集中是否存在street key，若存在就对其value+1，若不存在就添加这个记录
            try {
                for (int j = 0; j < resultList.size(); j++) {
                    if (resultList.get(j).get("time").equals(time)) {
                        isExists = true;

                        resultList.get(j).put("value", Integer.parseInt(String.valueOf(organizationList.get(j).get("value")) + value));
                        break;
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            }
            if (!isExists) {
                HashMap linkedHashMap = new HashMap();
                linkedHashMap.put("time", time);
                linkedHashMap.put("value", value);
                resultList.add(linkedHashMap);

            }
        }
        return resultList;
    }

    private List<HashMap> groupByTimeWithStreet(LinkedList<HashMap> organizationList) throws ParseException {

        List<HashMap> resultList = new ArrayList<HashMap>();

        //遍历，将每个街道中的数据按日期进行划分


        for (int i = 0; i < organizationList.size(); i++) {
            String time = (String) organizationList.get(i).get("time");
            int value = Integer.parseInt(String.valueOf(organizationList.get(i).get("value")));
            String street = (String) organizationList.get(i).get("STREET");

            boolean isExists = false;
            //遍历结果集中是否存在street key，若存在就对其value+1，若不存在就添加这个记录
            for (int j = 0; j < resultList.size(); j++) {
                if (resultList.get(j).get("time").equals(time)) {
                    isExists = true;
                    resultList.get(j).put("value", Integer.parseInt(String.valueOf(organizationList.get(j).get("value")) + value));
                    break;
                }
            }
            if (!isExists) {
                HashMap linkedHashMap = new HashMap();
                linkedHashMap.put("time", time);
                linkedHashMap.put("value", value);
                resultList.add(linkedHashMap);

            }
        }
        return resultList;
    }

    /**
     * 输入：按时间筛选过的数据list
     * 1.按照街道进行分类
     * 2.对每个街道中的数据按时间进行分类
     */
    private LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet(LinkedList<HashMap> organizationList) throws ParseException {

        LinkedList<LinkedList<HashMap>> allStreetList = new LinkedList<LinkedList<HashMap>>();
        LinkedList<HashMap<String, LinkedList<HashMap>>> datasGroupByTimeList = new LinkedList<HashMap<String, LinkedList<HashMap>>>();

        //todo:遍历,按街道分组存放
        for (int i = 0; i < organizationList.size(); i++) {
            String street = (String) organizationList.get(i).get("STREET");
            int value = Integer.parseInt(String.valueOf(organizationList.get(i).get("value")));
            String time = (String) organizationList.get(i).get("time");

            //遍历查找allstreet中匹配街道字段的index,如果存在street则添加至该list，如果不存在则add一个list
            boolean isExists = false;
            for (int k = 0; k < allStreetList.size(); k++) {
                if (allStreetList.get(k).get(0).get("STREET").equals(street)) {
                    isExists = true;
                    HashMap hashMap = new HashMap();
                    hashMap.put("STREET", street);
                    hashMap.put("value", value);
                    hashMap.put("time", time);
                    allStreetList.get(k).add(hashMap);
                    break;//添加完就break，后面不会再出现相同的了
                }
            }
            //若不存在就添加这个street列表，并将其添加到对应index下的list中
            if (!isExists) {
                //一个街道的数据
                LinkedList<HashMap> oneStreetList = new LinkedList<HashMap>();
                HashMap map = new HashMap();
                map.put("STREET", street);
                map.put("value", value);
                map.put("time", time);
                oneStreetList.add(map);
                allStreetList.add(oneStreetList);
            }
        }
        //todo:遍历,对每个list元素按时间进行分组
        for (int i = 0; i < allStreetList.size(); i++) {
            HashMap onstreetHashMap = new HashMap();
            String street = (String) allStreetList.get(i).get(0).get("STREET");
            LinkedList<HashMap> hashMaps1 = allStreetList.get(i);
            List<HashMap> timeList = groupByTime(hashMaps1);

            onstreetHashMap.put("name", street);
            onstreetHashMap.put("data", timeList);

            datasGroupByTimeList.add(onstreetHashMap);
        }

        return datasGroupByTimeList;
    }

    /**
     * 输入：按时间筛选过的数据list
     * 1.按照街道进行分类
     * 2.对每个街道中的数据按时间进行分类
     */
    private LinkedList<HashMap<String, LinkedList<HashMap>>> groupByStreet(LinkedList<HashMap> organizationList, SimpleDateFormat formatter, String dimention) throws ParseException {

        LinkedList<LinkedList<HashMap>> allStreetList = new LinkedList<LinkedList<HashMap>>();
        LinkedList<HashMap<String, LinkedList<HashMap>>> datasGroupByTimeList = new LinkedList<HashMap<String, LinkedList<HashMap>>>();

        //todo:遍历,按街道分组存放
        for (int i = 0; i < organizationList.size(); i++) {
            String street = (String) organizationList.get(i).get("STREET");
            int value = Integer.parseInt(String.valueOf(organizationList.get(i).get("value")));
            String time = (String) organizationList.get(i).get("time");

            //遍历查找allstreet中匹配街道字段的index,如果存在street则添加至该list，如果不存在则add一个list
            boolean isExists = false;
            for (int k = 0; k < allStreetList.size(); k++) {
                if (allStreetList.get(k).get(0).get("STREET").equals(street)) {
                    isExists = true;
                    HashMap hashMap = new HashMap();
                    hashMap.put("STREET", street);
                    hashMap.put("value", value);
                    hashMap.put("time", time);
                    allStreetList.get(k).add(hashMap);
                    break;//添加完就break，后面不会再出现相同的了
                }
            }
            //若不存在就添加这个street列表，并将其添加到对应index下的list中
            if (!isExists) {
                //一个街道的数据
                LinkedList<HashMap> oneStreetList = new LinkedList<HashMap>();
                HashMap map = new HashMap();
                map.put("STREET", street);
                map.put("value", value);
                map.put("time", time);
                oneStreetList.add(map);
                allStreetList.add(oneStreetList);
            }
        }
        //todo:遍历,对每个list元素按时间进行分组
        for (int i = 0; i < allStreetList.size(); i++) {
            HashMap onstreetHashMap = new HashMap();
            String street = (String) allStreetList.get(i).get(0).get("STREET");
            LinkedList<HashMap> hashMaps1 = allStreetList.get(i);
            List<HashMap> timeList = groupByTime(hashMaps1);
            List<HashMap> timeList2 = null;

            //todo: 填充空余日期
            timeList2 = DataProcessUtil.fillOtherDay(timeList, formatter, dimention, "time", null, "time", "value");

            onstreetHashMap.put("name", street);
            onstreetHashMap.put("data", timeList2);

            datasGroupByTimeList.add(onstreetHashMap);
        }

        return datasGroupByTimeList;
    }

}
