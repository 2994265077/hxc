package com.cetccity.operationcenter.webframework.web.controller.weiji;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.web.service.db.MysqlOperateService;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 卫计一张图
 * 预警模块,暂时无数据，按照表结构进行开发，时间排序无法做，不知道时间格式
 */
@RestController
@RequestMapping("/early_alram_weiji")
@Api(tags = "卫计-预警信息")
@Slf4j
public class EarlyAlarmWeiJi {
    private static final Logger logger = LoggerFactory.getLogger(EarlyAlarmWeiJi.class);

    @Autowired
    MysqlOperateService mysqlOperateService;

    @Autowired
    OracleOperateService oracleOperateService;

    /**
     * 预警列表
     * @return
     */
    @ApiOperation(value = "预警列表",notes = "卫计预警-特殊病种列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "season", value = "季度", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "string", paramType = "query" , example="1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "string", paramType = "query" , example="10")
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public LinkedHashMap simpleList(int season, int pageNum, int pageSize) {
        if (season == 0 || pageNum == 0 || pageSize == 0)return null;
        LinkedHashMap result = new LinkedHashMap();
//        String tbName = "tb_weiji_sdm_info_rt";
        String tbName="YJJC_QWJJ_SDM_INFO_V";/*更改为oracle表名*/

        List<LinkedHashMap> allData = null;
        /*数据库访问方式*/
//        allData = mysqlOperateService.queryAllDataByTableName(tbName);
        /*改为oracle数据库*/
//        allData = oracleOperateService.queryAllDataByTableName(tbName);
        allData = oracleOperateService.queryAllDataByTableName(tbName);

        /*es访问方式*/
//        try {
//            allData = ESOperate.queryObjectbyIndex(ESOperate.getIndexName(ESOperate.dbName,tbName));
//        } catch (IOException e) {
//            logger.error("query es error!\n",e);
//        }
        if (allData!=null){
            /*处理时间字段*/
            for (int i = 0; i < allData.size(); ++i){
                allData.get(i).put("LAST_EDITED_TIME",((String) allData.get(i).get("LAST_EDITED_TIME")).replace("/","-"));
            }

            ArrayList arrayList = new ArrayList();
            int pageTotal = 0;
            int totalRecords = 0;

            /*按时间排序*/
//            for (int i = 0; i < allData.size(); ++i){
//                for (int j = i+1; j < allData.size(); ++j){
//                    Calendar calendar = Calendar.getInstance();
//                    Calendar calendar1 = Calendar.getInstance();
//                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date;
//                    Date date1;
//                    try {
//                        date =df.parse((String) allData.get(i).get("last_edited_time"));
//                        calendar.setTime(date);
//                        date1 =df.parse((String) allData.get(j).get("last_edited_time"));
//                        calendar1.setTime(date1);
//                    } catch (ParseException e) {
//                        logger.error("time format error!\n",e);
//                    }
//                    if ( calendar.getTimeInMillis() < calendar1.getTimeInMillis()){
//                        /*交换*/
//                        LinkedHashMap linkedHashMap = (LinkedHashMap) allData.get(i).clone();
//                        allData.set(i, allData.get(j));
//                        allData.set(j, linkedHashMap);
//                    }
//                }
//            }
            Collections.sort(allData, new Comparator<LinkedHashMap>() {
                @Override
                public int compare(LinkedHashMap o1, LinkedHashMap o2) {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date1;
                    Date date2;
                    try {
                        date1 =df.parse((String) o1.get("LAST_EDITED_TIME"));
                        date2 =df.parse((String) o2.get("LAST_EDITED_TIME"));
                        Calendar calendar1 = Calendar.getInstance();
                        Calendar calendar2 = Calendar.getInstance();
                        calendar1.setTime(date1);
                        calendar2.setTime(date2);
                        if (calendar1.getTimeInMillis() < calendar2.getTimeInMillis())
                            return 1;
                        else if(calendar1.getTimeInMillis() > calendar2.getTimeInMillis())
                            return -1;
                        else return 0;
                    } catch (ParseException e) {
                    	log.error(e.toString());
                    }
                    return 0;
                }
            });



            /*处理数据*/
            for (int i = 0; i < allData.size(); ++i){

                Calendar calendar = Calendar.getInstance();
                Date date;
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int dataSeason = 0;
                try {
                    date =df.parse((String) allData.get(i).get("LAST_EDITED_TIME"));
                    calendar.setTime(date);
                    int month = calendar.get(Calendar.MONTH)+1;
                    dataSeason = (month + 2) / 3;
                } catch (ParseException e) {
                    logger.error("time format error!\n", e);
                }
                /*判断季度 时间格式为 yyyy-MM-dd HH:mm:ss*/
                /*卫计没有season筛选，先去掉*/
//                if(dataSeason == season) {
                    /*当前季度，总条数*/
                    totalRecords++;
                    /*翻页,当前页*/
                    if (((totalRecords - 1) / pageSize + 1) == pageNum) {
                        allData.get(i);
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        linkedHashMap.put("id", allData.get(i).get("ID"));
                        linkedHashMap.put("time", allData.get(i).get("LAST_EDITED_TIME"));
                        linkedHashMap.put("name", allData.get(i).get("DIAG_NAME_INHOS"));
                        linkedHashMap.put("address", allData.get(i).get("ADDRESS"));
                        linkedHashMap.put("status", "无该字段");
                        arrayList.add(linkedHashMap);
                    }
//                }
            }
            ESOperate.emptyOperate(arrayList);
            pageTotal = (totalRecords - 1) / pageSize + 1;
            result.put("data",arrayList);
            result.put("pageTotal",pageTotal);
            result.put("totalRecords",totalRecords);
            result.put("pageNum",pageNum);
        }
        return result;
    }

    /**
     * 预警事件走势，折线图
     */
    @ApiOperation(value = "卫计预警-折线图",notes = "卫计-特殊病种", produces = "application/json")
    @RequestMapping(value = "/line", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public List<LinkedHashMap> line(){
        List<LinkedHashMap> result = new ArrayList<LinkedHashMap>();
//        String tbName = "tb_weiji_sdm_info_rt";
        String tbName = "YJJC_QWJJ_SDM_INFO_V";/*oracle数据库表名*/

        List<LinkedHashMap> allData = null;
        /*数据库访问方式*/
//        allData = mysqlOperateService.queryAllDataByTableName(tbName);
        /*oracle访问方式*/
        allData = oracleOperateService.queryAllDataByTableName(tbName);

        /*es访问方式*/
//        try {
//            allData = ESOperate.queryObjectbyIndex(ESOperate.getIndexName(ESOperate.dbName,tbName));
//        } catch (IOException e) {
//            logger.error("query es error!\n",e);
//        }
        if (allData!=null) {
            /*处理时间字段*/
            for (int i = 0; i < allData.size(); ++i){
                allData.get(i).put("LAST_EDITED_TIME",((String) allData.get(i).get("LAST_EDITED_TIME")).replace("/","-"));
            }

            ArrayList<LinkedHashMap> arrayList = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("year", 0);
            linkedHashMap.put("month", 0);
            arrayList.add(linkedHashMap);
            arrayList.add((LinkedHashMap) linkedHashMap.clone());
            arrayList.add((LinkedHashMap) linkedHashMap.clone());
            /*找到最近三个月的 年 和 月，top N 问题*/
            for (int i = 0; i < allData.size(); ++i) {
                Calendar calendar = Calendar.getInstance();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date;
                try {
                    date = df.parse((String) allData.get(i).get("LAST_EDITED_TIME"));
                    calendar.setTime(date);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int year = calendar.get(Calendar.YEAR);
                    for (int j = 0; j < arrayList.size(); ++j) {
                        int arrayYear = Integer.parseInt(arrayList.get(j).get("year").toString());
                        int arrayMonth = Integer.parseInt(arrayList.get(j).get("month").toString());
                        if (year > arrayYear) {
                            /*往后移动一个位置*/
                            for (int k = j + 1; k < arrayList.size(); ++k){
//                                arrayList.set(k,arrayList.get(k-1));
                                /*需要深拷贝 移动*/
                                arrayList.set(k, (LinkedHashMap) arrayList.get(k-1).clone());
                            }
                            arrayList.get(j).put("year", year);
                            arrayList.get(j).put("month", month);
                            break;
                        } else if (year == arrayYear && month > arrayMonth) {
                            /*往后移动一个位置*/
                            for (int k = j + 1; k < arrayList.size(); ++k){
//                                arrayList.set(k,arrayList.get(k-1));
                                /*需要深拷贝 移动*/
                                arrayList.set(k, (LinkedHashMap) arrayList.get(k-1).clone());
                            }
                            arrayList.get(j).put("year", year);
                            arrayList.get(j).put("month", month);
                            break;
                        }else if (year == arrayYear && month == arrayMonth){
                            break;
                        }
                    }
                } catch (ParseException e) {
                    logger.error("time format error!\n", e);
                }

            }

            /*根据找到的最近几个月，初始化result*/
            for (int i = 0; i < arrayList.size(); ++i){
                if (Integer.parseInt(arrayList.get(i).get("year").toString()) == 0) break;
                int arrayYear = Integer.parseInt(arrayList.get(i).get("year").toString());
                int arrayMonth = Integer.parseInt(arrayList.get(i).get("month").toString());
                LinkedHashMap linkedHashMap1 = new LinkedHashMap();
                linkedHashMap1.put("value",0);
                linkedHashMap1.put("name",arrayYear+""+arrayMonth);
                result.add(linkedHashMap1);
            }
            /*统计最近几个月的时间数量*/
            for (int i = 0; i < allData.size(); ++i) {
                Calendar calendar = Calendar.getInstance();
                Date date;
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    date = df.parse((String) allData.get(i).get("LAST_EDITED_TIME"));
                    calendar.setTime(date);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int year = calendar.get(Calendar.YEAR);
                    /*遍历result，找到对应的值*/
                    for (int j = 0; j < result.size(); ++j ){
                        if(result.get(j).get("name").toString().equalsIgnoreCase(year+""+month)){
                            int value = Integer.parseInt(result.get(j).get("value").toString())+1;
                            result.get(j).put("value",value);
                            break;
                        }
                    }

                } catch (ParseException e) {
                    logger.error("time format error\n",e);
                }
            }
            /*格式化月份，将 年月 改为 月*/
            for (int i = 0; i < result.size(); ++i){
                result.get(i).put("name",result.get(i).get("name").toString().substring(4)+"月");
            }
        }
        return result;
    }

    /**
     * 预警事件走势，折线图
     */
    public static String YJJC_QWJJ_SDM_INFO_STREET_V_SELECT ="SELECT A.\"AGE\",A.\"DATE_OF_BIRTH\",A.\"DEPT_CODE\",A.\"DEPT_NAME\",A.\"DIAG_CODE_INHOS\",A.\"DIAG_NAME_INHOS\",A.\"DIAG_SNO\",A.\"DIAG_TIME\",A.\"DIAG_TYPE\",A.\"DIAG_TYPE_CODE\",A.\"DOCTOR_CODE\",A.\"DOCTOR_NAME\",A.\"ID\",A.\"LAST_EDITED_TIME\",A.\"ORG_CODE\",A.\"ORG_NAME\",A.\"OUT_SNO\",A.\"SEX_CODE\",A.\"UPLOAD_TIME\",A.\"YXY_UPDATEDTIME\",A.\"ADQ_UPDATE_TIME\" \n" +
            ", \"B_STREET\" AS \"STREET\"\n" +
            "FROM \"YJJC_QWJJ_SDM_INFO_V\" A\n" +
            "LEFT JOIN \n" +
            "(SELECT DISTINCT \"STREET\" AS \"B_STREET\", \"ORG_CODE\" AS \"B_ORG_CODE\"\n" +
            "FROM \"YJJC_QWJJ_ORG_V\"\n" +
            ")\n" +
            "ON A.\"ORG_CODE\"=\"B_ORG_CODE\"";
    public static String YJJC_QWJJ_SDM_INFO_STREET_V = "CREATE view \"YJJC_QWJJ_SDM_INFO_STREET_V\" AS\n" + YJJC_QWJJ_SDM_INFO_STREET_V_SELECT;

    @ApiOperation(value = "饼状图",notes = "卫计预警-特殊病种", produces = "application/json")
    @RequestMapping(value = "/pie", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public List<LinkedHashMap> pie(){
        List<LinkedHashMap> result = new ArrayList<LinkedHashMap>();
//        String tbName = "tb_weiji_sdm_info_rt";

        String tbName = "YJJC_QWJJ_SDM_INFO_V"; /*oracle数据库名*/
//        List<LinkedHashMap> allData = oracleOperateService.queryAllDataByTableName(tbName);
        List<LinkedHashMap> allData = oracleOperateService.querySql(YJJC_QWJJ_SDM_INFO_STREET_V_SELECT);
        /*数据库访问方式*/
//        allData = mysqlOperateService.queryAllDataByTableName(tbName);
        /*oracle访问方式*/
//        allData = oracleOperateService.queryAllDataByTableName(tbName);



        if (allData != null){
            for (int i = 0; i < allData.size(); ++i){
                String street = (String) allData.get(i).get("street");
                if (street == null || street.equalsIgnoreCase(""))continue;
                boolean exist = false;
                for (int j = 0; j < result.size(); ++j){
                    if (result.get(j).get("name").equals(street)) {
                        exist = true;
                        result.get(j).put("value",Integer.parseInt(result.get(j).get("value").toString())+1);
                    }
                }
                if (!exist){
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("value",1);
                    linkedHashMap.put("name",street);
                    result.add(linkedHashMap);
                }
            }
        }
        return result;
    }
}
