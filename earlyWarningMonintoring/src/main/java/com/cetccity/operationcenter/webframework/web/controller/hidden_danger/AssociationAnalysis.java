package com.cetccity.operationcenter.webframework.web.controller.hidden_danger;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by huangzezhou on 2018/6/13.
 * 隐患一张图中的关联关系分析
 * baseUrl: /hiden_danger/association_analysis/
 */
@RestController
@RequestMapping("/hd/")
@Api(tags = "安全隐患-关联分析[暂未使用]")
public class AssociationAnalysis {

    @Autowired
    OracleOperateService oracleOperateService;

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AssociationAnalysis.class);

    private static String tbFire="T_XIAOFANG_BUWEI".toLowerCase();/*消防落图表*/
    private static String xiaofang_yinhuan="xiaofang_yinhuan";/*消防隐患-消防关联表*/
    private static String qxfj_build_info_v="qxfj_build_info_v";/*建筑基本信息-消防关联表*/
    private static String qxfj_build_ztzr_v="qxfj_build_ztzr_v";/*建筑责任信息-消防关联表*/
    private static String qxfj_unit_info_v="qxfj_unit_info_v";/*企业信息-消防关联表*/
    private static String qxfj_build_image_v="qxfj_build_image_v";/*单位图纸表-消防关联表*/
    private static String qxfj_build_rfid_check_v="qxfj_build_rfid_check_v";/*电子巡更表-消防关联表*/
    private static String qxfj_wl_cgq_info_v="qxfj_wl_cgq_info_v";/*物联传感器信息列表-消防关联表*/

    /**
     * 消防，落图点击查询同经纬度点的隐患列表
     * @param red
     * @param orange
     * @param yellow
     * @return
     */
    @ApiOperation(value = "消防隐患三色，落图点击查询同经纬度点的隐患列表", notes = "消防隐患三色，落图点击查询同经纬度点的隐患列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "red", value = "true/false，表示是否查询红色隐患[已废弃，可以传任意值]", paramType = "query"),
            @ApiImplicitParam(name = "orange", value = "true/false，表示是否查询橙色隐患[已废弃，可以传任意值]", paramType = "query"),
            @ApiImplicitParam(name = "yellow", value = "true/false，表示是否查询黄色隐患[已废弃，可以传任意值]", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "建筑id：1", paramType = "query")
    })
    @RequestMapping(value = "/fire/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LinkedHashMap> fireList(boolean red, boolean orange, boolean yellow, String id){
        List<LinkedHashMap> result = new ArrayList<LinkedHashMap>();
        try {
            /* /fire/list 消防列表，通过消防id 找到经纬度，然后找到同经纬度的点，然后在 xiaofang_yinhuan 找到隐患详细信息 */
            String sql = "SELECT b.\"lb\", b.\"yhbw\", b.\"yhwt\", b.\"yhdj\"\n" +
                    "FROM \"t_xiaofang_buwei\" a, \"xiaofang_yinhuan\" b\n" +
                    "WHERE a.\"jd84\"=(SELECT \"jd84\" FROM \"t_xiaofang_buwei\" WHERE \"id\"='&ID')\n" +
                    "AND a.\"wd84\" = (SELECT \"wd84\" FROM \"t_xiaofang_buwei\" WHERE \"id\"='&ID')\n" +
                    "AND a.\"unit_code\"=b.\"unit_code\"";
            sql = sql.replaceAll("&ID",id);
            List<LinkedHashMap> allData = oracleOperateService.querySql(sql);
            for (int i = 0; i < allData.size(); ++i) {
                try {
                        LinkedHashMap hd = allData.get(i);

                        LinkedHashMap map = new LinkedHashMap();
                        map.put("隐患类别", hd.get("lb"));
                        map.put("隐患部位", hd.get("yhbw"));
                        map.put("隐患问题", hd.get("yhwt"));
                        map.put("隐患等级", hd.get("yhdj"));
                    ESOperate.dataToDic(map, "xiaofang_yinhuan");
                        result = ESOperate.emptyOperate(result);
                        if ( hd.get("yhdj").toString().equalsIgnoreCase("红色")) {
                            result.add(map);
                        } else if ( hd.get("yhdj").toString().equalsIgnoreCase("橙色")) {
                            result.add(map);
                        } else if ( hd.get("yhdj").toString().equalsIgnoreCase("黄色")) {
                            result.add(map);
                        }

                }catch (Exception e){
                    logger.error("query oracle error!");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            logger.error("query oracle is null!", e);
        }

        return result;
    }

    /**
     * 消防-关联查询建筑基本信息
     * @param id
     * @return
     */
    @ApiOperation(value = "消防隐患三色，关联查询建筑基本信息", notes = "消防隐患三色，关联查询建筑基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id:1", paramType = "query")
    })
    @RequestMapping(value = "/fire/building_basic", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LinkedHashMap> buildingBasic(String id){
        List<LinkedHashMap> result = new ArrayList<LinkedHashMap>();
        try {
            LinkedHashMap build = null;
            String sql = "SELECT b.\"UUID\", b.\"BUILD_LX\", b.\"BUILD_ADDR\", b.\"BZCMJ\", b.\"CREATEDATE\", b.\"DLQK\", b.\"BUILDID\", b.\"JG_DATE\",b.\"DSMJ\"\n" +
                    "FROM \"t_xiaofang_buwei\" a, \"qxfj_build_info_v\" b\n" +
                    "WHERE a.\"build_id\"=b.\"UUID\"\n" +
                    "AND a.\"id\"='&ID'";
            sql = sql.replaceAll("&ID",id);
            List<LinkedHashMap> buildList = oracleOperateService.querySql(sql);
            if (buildList.size() > 0)
                build = buildList.get(0);
            else
                return null;

            LinkedHashMap temp = new LinkedHashMap();

            temp.put("建筑名称",build.get("build_name"));
            temp.put("建筑类型", build.get("build_lx"));
            temp.put("建筑地址", build.get("build_addr"));
            temp.put("标准层面积", build.get("bzcmj"));
            temp.put("建筑创建时间", build.get("createdate"));
            temp.put("地理情况", build.get("dlqk"));
            temp.put("建筑编号", build.get("buildid"));
            temp.put("竣工时间", build.get("jg_date"));
            temp.put("地上面积", build.get("dsmj"));
//            temp = ESOperate.emptyOperate(temp);
            ESOperate.dataToDic(temp, "qxfj_build_info_v");
            LinkedHashMap div1 = new LinkedHashMap();
            div1.put("name","基础信息");
            div1.put("value",temp);

            LinkedHashMap temp2 = new LinkedHashMap();

            temp2.put("建筑产权使用情况", build.get("jzcqjsyqk"));
            temp2.put("建筑分类", build.get("jzfl"));
            temp2.put("建筑高度", build.get("jzgd"));
            temp2.put("建筑结构", build.get("jzjg"));
            temp2.put("建筑面积", build.get("jzmj"));
            temp2.put("建筑门牌号", build.get("jzmph"));
//            temp2 = ESOperate.emptyOperate(temp2);
            ESOperate.dataToDic(temp2, "qxfj_build_info_v");
            LinkedHashMap div2 = new LinkedHashMap();
            div2.put("name","工商信息");
            div2.put("value",temp2);

            result.add(div1);
            result.add(div2);
        } catch (IOException e) {
            logger.error("query es error! tbname = "+ qxfj_build_info_v, e);
        }catch (IndexOutOfBoundsException e) {
            logger.error("query result is null!",e);
        }

        return result;
    }

    /**
     * 消防-关联查询建筑责任信息
     * TODO 建筑责任信息应该有多个数据，返回List
     * @param id
     * @return
     */
    @ApiOperation(value = "消防隐患三色，关联查询建筑责任信息", notes = "消防隐患三色，关联查询建筑责任信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id：1", paramType = "query")
    })
    @RequestMapping(value = "/fire/building_res", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public LinkedHashMap buildingRes(String id){
        LinkedHashMap result = new LinkedHashMap();
        try {
            String sql = "SELECT c.\"build_id\", c.\"id\", c.\"unit_name\", c.\"adq_update_time\", c.\"ztzr_name\", c.\"ztzr_tel\"\n" +
                    "FROM \"t_xiaofang_buwei\" a, \"qxfj_build_info_v\" b, \"qxfj_build_ztzr_v\" c\n" +
                    "WHERE a.\"build_id\"=b.\"UUID\"\n" +
                    "AND b.\"UUID\"=c.\"build_id\"\n" +
                    "AND a.\"id\"='&ID'";
            sql = sql.replaceAll("&ID",id);
            List<LinkedHashMap> buildResList = oracleOperateService.querySql(sql);
            LinkedHashMap buildRes = buildResList.get(0);

            result.put("建筑编号", buildRes.get("build_id"));
            result.put("编号", buildRes.get("build_id"));
            result.put("单位名称", buildRes.get("unit_name"));
            result.put("更新时间", buildRes.get("adq_update_time"));
            result.put("责任人", buildRes.get("ztzr_name"));
            result.put("责任人联系方式", buildRes.get("ztzr_tel"));
//            result = ESOperate.emptyOperate(result);
            ESOperate.dataToDic(result, "qxfj_build_ztzr_v");
        } catch (IndexOutOfBoundsException e) {
            logger.error("query result is null!tbName=["+tbFire+","+qxfj_build_info_v+","+qxfj_build_ztzr_v+",]",e);
//            result.put("error", "query result is null!");
        } catch (IOException e) {
            logger.error("format dictionary error!",e);
        }
        return result;
    }

    /**
     * 消防-关联查询建筑企业信息
     * @param id
     * @return
     */
    @ApiOperation(value = "消防隐患三色，关联查询建筑企业信息", notes = "消防隐患三色，关联查询建筑企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id：1", paramType = "query")
    })
    @RequestMapping(value = "/fire/building_enter", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public LinkedHashMap buildingEnter(String id, int pageNum, int pageSize){
        LinkedHashMap result = new LinkedHashMap();
        int pageTotal = 0;
        int totalRecords = 0;
        try {
            String sql = "SELECT b.\"id\", b.\"unit_name\", b.\"unit_type\", b.\"updatetime\"\n" +
                    "FROM \"t_xiaofang_buwei\" a, \"qxfj_unit_info_v\" b\n" +
                    "WHERE a.\"build_id\"=b.\"id\"\n" +
                    "AND a.\"id\"='&ID'";
            sql = sql.replaceAll("&ID",id);

            List<LinkedHashMap> buildEnter = oracleOperateService.querySql(sql);

            List<LinkedHashMap> list = new ArrayList<LinkedHashMap>();
            for (int i = 0; i < buildEnter.size(); ++i){
                LinkedHashMap temp = new LinkedHashMap();

                temp.put("序号",buildEnter.get(i).get("id"));
                temp.put("企业名称",buildEnter.get(i).get("unit_name"));
                temp.put("企业类型",buildEnter.get(i).get("unit_type"));
                temp.put("更新时间",buildEnter.get(i).get("updatetime"));
//                temp = ESOperate.emptyOperate(temp);
                ESOperate.dataToDic(temp, "qxfj_unit_info_v");

                list.add(temp);
            }

            result.put("data",list);

            totalRecords = list.size();
            pageTotal = (totalRecords - 1) / pageSize + 1;
        } catch (IndexOutOfBoundsException e) {
            logger.error("query result is null!",e);
        } catch (IOException e) {
            logger.error("format dictionary error!", e);
        }

        result.put("pageTotal",pageTotal);
        result.put("totalRecords",totalRecords);
        result.put("pageNum",pageNum);
        return result;
    }

    /**
     * 消防-关联查询建筑平面图纸
     * TODO 平面图纸也有多个，因为有多层楼，需要返回List
     * @param id
     * @return
     */
    @ApiOperation(value = "消防隐患三色，关联查询建筑平面图纸", notes = "消防隐患三色，关联查询建筑平面图纸")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id：1", paramType = "query")
    })
    @RequestMapping(value = "/fire/building_pic", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public LinkedHashMap buildingPic(String id){
        LinkedHashMap result = new LinkedHashMap();
        try {


            LinkedHashMap buildingPic = null;
            String sql = "SELECT b.\"filepath\", b.\"name\", b.\"type\", b.\"describe\", b.\"floor\", b.\"userid\"\n" +
                    "FROM \"t_xiaofang_buwei\" a, \"qxfj_build_image_v\" b\n" +
                    "WHERE a.\"build_id\"=b.\"build_id\"\n" +
                    "AND a.\"id\"='&ID'";
            sql = sql.replaceAll("&ID",id);
            List<LinkedHashMap> buildingPicList = oracleOperateService.querySql(sql);
            if (buildingPicList.size() > 0)
                buildingPic = buildingPicList.get(0);
            else
                return null;

            result.put("picture",buildingPic.get("filepath"));
            result.put("图纸名称",buildingPic.get("name"));
            result.put("图纸类型",buildingPic.get("type"));
            result.put("图纸描述",buildingPic.get("describe"));
            result.put("楼层号",buildingPic.get("floor"));
            result.put("创建人",buildingPic.get("userid"));
            result = ESOperate.emptyOperate(result);

        } catch (IndexOutOfBoundsException e) {
            logger.error("query result is null!",e);
        }

        return result;
    }

    /**
     * 消防-关联查询电子巡更记录
     * @param id
     * @return
     */
    @ApiOperation(value = "消防隐患三色，关联查询电子巡更记录", notes = "消防隐患三色，关联查询电子巡更记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id：9", paramType = "query")
    })
    @RequestMapping(value = "/fire/building_rec", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public LinkedHashMap buildingRec(String id){
        LinkedHashMap result = new LinkedHashMap();

        try {
            String sql = "SELECT b.\"createdate\",b.\"rfid_id\",b.\"user_id\"\n" +
                    "FROM \"t_xiaofang_buwei\" a, \"qxfj_build_rfid_check_v\" b\n" +
                    "WHERE a.\"build_id\"=b.\"build_id\"\n" +
                    "AND a.\"id\"='&ID'";
            sql = sql.replaceAll("&ID",id);
            List<LinkedHashMap> buildingRecList = oracleOperateService.querySql(sql);
            LinkedHashMap buildingRec = null;
            if (buildingRecList.size()>0)
                buildingRec = buildingRecList.get(0);
            else
                return null;

            result.put("检查日期",buildingRec.get("createdate"));
            result.put("电子标签编号",buildingRec.get("rfid_id"));
            result.put("检查人员",buildingRec.get("user_id"));

            result.put("信息备注","无该字段");
            result = ESOperate.emptyOperate(result);

        }  catch (IndexOutOfBoundsException e) {
            logger.error("query result is null!",e);
        }

        return result;
    }


    /**
     * 消防-关联查询建筑隐患信息
     * @param id  隐患id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "消防隐患三色，关联查询建筑隐患信息", notes = "消防隐患三色，关联查询建筑隐患信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id", paramType = "query")
    })
    @RequestMapping(value = "/fire/building_hd", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public LinkedHashMap buildingHD(String id, int pageNum, int pageSize){
        LinkedHashMap result = new LinkedHashMap();
        List<LinkedHashMap> list = new ArrayList<LinkedHashMap>();
        int pageTotal = 0;
        int totalRecords = 0;
        try {

            String sql = "SELECT b.\"lb\", b.\"yhbw\", b.\"yhwt\", b.\"yhdj\"\n" +
                    "FROM \"t_xiaofang_buwei\" a, \"xiaofang_yinhuan\" b\n" +
                    "WHERE a.\"build_id\"=(SELECT \"build_id\" FROM \"t_xiaofang_buwei\" WHERE \"id\"='&ID')\n" +
                    "AND a.\"unit_code\"=b.\"unit_code\"";
            sql = sql.replaceAll("&ID", id);
            List<LinkedHashMap> hdList = oracleOperateService.querySql(sql);


            for (int i = 0; i < hdList.size(); ++i){
                try {
                    LinkedHashMap temp = new LinkedHashMap();
                    LinkedHashMap hd = hdList.get(i);


                    temp.put("隐患类别", hd.get("lb"));
                    temp.put("隐患部位", hd.get("yhbw"));
                    temp.put("隐患问题", hd.get("yhwt"));
                    temp.put("隐患等级", hd.get("yhdj"));
                    result = ESOperate.emptyOperate(result);

                    temp = ESOperate.emptyOperate(temp);

                    list.add(temp);
                }catch (Exception e){
                    logger.error("query es error!");
                }
            }

            result.put("data",list);

            totalRecords = list.size();
            pageTotal = (totalRecords - 1) / pageSize + 1;
        } catch (IndexOutOfBoundsException e) {
            logger.error("query result is null!",e);
        }

        result.put("pageTotal",pageTotal);
        result.put("totalRecords",totalRecords);
        result.put("pageNum",pageNum);
        return result;
    }

    /**
     * 消防-关联查询建筑企业信息
     * @param id
     * @return
     */
    @ApiOperation(value = "消防隐患三色，关联查询建筑企业信息", notes = "消防隐患三色，关联查询建筑企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id", paramType = "query")
    })
    @RequestMapping(value = "/fire/building_wl", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public LinkedHashMap buildingWL(String id, int pageNum, int pageSize){
        LinkedHashMap result = new LinkedHashMap();
        LinkedHashMap point = null;
        int pageTotal = 0;
        int totalRecords = 0;
        try {
            point = ESOperate.queryObjectByField(
                    ESOperate.getIndexName(ESOperate.dbName, tbFire),
                    "id", id, 0
            ).get(0);
            String enterId = (String) ESOperate.queryObjectByField(
                    ESOperate.getIndexName(ESOperate.dbName, qxfj_build_info_v),
                    "id",point.get("build_id").toString(),0
            ).get(0).get("id");
            List<LinkedHashMap> buildWL = ESOperate.queryObjectByField(
                    ESOperate.getIndexName(ESOperate.dbName, qxfj_wl_cgq_info_v),
                    "buildid", enterId, 0
            );

            List<LinkedHashMap> list = new ArrayList<LinkedHashMap>();
            for (int i = 0; i < buildWL.size(); ++i){
                LinkedHashMap temp = new LinkedHashMap();

                temp.put("序号",buildWL.get(i).get("id"));/*序号改为->时间*/
                temp.put("设备名称",buildWL.get(i).get(ESOperate.dataDictionary(ESOperate.dbName, "QXFJ_WL_CGQ_INFO_V","type")));
                temp.put("设备编号",buildWL.get(i).get("DEVICE_ID".toLowerCase()));
                temp.put("授权编码",buildWL.get(i).get("updatetime"));
                temp.put("通道总数",buildWL.get(i).get("id"));
                temp.put("创建人",buildWL.get(i).get("USERID".toLowerCase()));
                temp = ESOperate.emptyOperate(temp);

                list.add(temp);
            }

            result.put("data",list);

            totalRecords = list.size();
            pageTotal = (totalRecords - 1) / pageSize + 1;
        } catch (IOException e) {
            logger.error("query es error!",e);
        }catch (IndexOutOfBoundsException e) {
            logger.error("query result is null!",e);
        }

        result.put("pageTotal",pageTotal);
        result.put("totalRecords",totalRecords);
        result.put("pageNum",pageNum);
        return result;
    }


    /**
     * 采用查询数据库，而不是es的方式
     * 安监，落图点击查询同经纬度点的隐患列表
     * 去掉自查的相关逻辑，只落巡查
     * @param id 编号
     * @return
     */
    @ApiOperation(value = "安监隐患巡查，落图点击查询同经纬度点的隐患列表", notes = "安监隐患巡查，落图点击查询同经纬度点的隐患列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id:40d40160f5c2453da7ea1db3b39a1ae7", paramType = "query")
    })
    @RequestMapping(value = "/check/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LinkedHashMap> checkList(String id) {
        List<LinkedHashMap> result = new ArrayList<LinkedHashMap>();
        /*check_list 根据id，查询同经纬度的隐患信息详情列表*/
        String sql = "select b.\"updatetime\", b.\"islevel\", b.\"inspectcontent\", b.\"basecase\", a.\"jd84\", a.\"wd84\" from \"t_insrecord\" a, \"qajj_instroubleregcheck_v\" b \n" +
                "where a.\"id\"=b.\"insrecordid\" and a.\"jd84\"=(select \"jd84\" from \"t_insrecord\" a where a.\"id\"='&ID') \n" +
                "and a.\"wd84\"=(select \"wd84\" from \"t_insrecord\" a where a.\"id\"='&ID')";
        sql = sql.replaceAll("&ID",id);
        List<LinkedHashMap> datas = oracleOperateService.querySql(sql);
        for (int i = 0; i < datas.size(); ++i){
            LinkedHashMap row = datas.get(i);
            String[] tmp = row.get(oracleOperateService.formateCol("updatetime")).toString().split(":");
            String date = tmp[0]+":"+tmp[1]+":"+tmp[2];
            /*处理特殊时间：1899/12/31 00:00:00:000000*/
            if (row.get(oracleOperateService.formateCol("updatetime")).toString().equalsIgnoreCase("1899/12/31 00:00:00:000000"))
                date=null;

            LinkedHashMap h = new LinkedHashMap();

            h.put("序号",date);/*将序号改为时间*/
            h.put("隐患等级", row.get(oracleOperateService.formateCol("islevel")));
            h.put("隐患内容", row.get(oracleOperateService.formateCol("inspectcontent")));
            h.put("隐患基本情况", row.get(oracleOperateService.formateCol("basecase")));
            h.put("巡查方式", "巡查");

//            h = ESOperate.emptyOperate(h);
            try {
                ESOperate.dataToDic(h, "qajj_instroubleregcheck_v");
                result.add(h);
            } catch (IOException e) {
                logger.error("format error!");
            }

        }

        return result;
    }

    /**
     * 安监-关联查询企业基本信息
     * 去掉自查逻辑，只留巡查
     * @param id
     * @return
     */
    @ApiOperation(value = "安监隐患巡查，关联查询企业基本信息", notes = "安监隐患巡查，关联查询企业基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id:40d40160f5c2453da7ea1db3b39a1ae7", paramType = "query")
    })
    @RequestMapping(value = "/check/enterprise", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LinkedHashMap> enterprise(String id){
        List<LinkedHashMap> result=new ArrayList<LinkedHashMap>();
        try {
            LinkedHashMap enterprise = null;
            /* enterprise, 根据隐患id获取企业innerid，然后找到qajj_pucentp_v中企业的采集信息 */
            String sql = "select b.\"entpname\", b.\"enterprisestate\", b.\"entpsize\", b.\"empqty\", b.\"entpcode\", b.\"industrytype\", b.\"fillentpname\", b.\"fixedassets\",\n" +
                    "b.\"policyname\", b.\"policytel\", b.\"registeraddr\", b.\"registerid\", b.\"registerdept\", b.\"registermonty\", b.\"registersafeengineer\", b.\"registeruser\"\n" +
                    "from \"t_insrecord\" a , \"qajj_pucentp_v\" b where a.\"id\"='&ID' \n" +
                    "and a.\"innerid\"=b.\"innerid\"";
            sql = sql.replaceAll("&ID",id);
            List<LinkedHashMap> enterpriseList = oracleOperateService.querySql(sql);
            if (enterpriseList.size()>0)
                enterprise=enterpriseList.get(0);
            else
                return null;
            LinkedHashMap basic = new LinkedHashMap();
            basic.put("企业名称",enterprise.get(oracleOperateService.formateCol("ENTPNAME")));
            basic.put("企业状态",enterprise.get(oracleOperateService.formateCol("ENTERPRISESTATE")));
            basic.put("企业规模",enterprise.get(oracleOperateService.formateCol("ENTPSIZE")));
            basic.put("从业人数",enterprise.get(oracleOperateService.formateCol("EMPQTY")));
            basic.put("组织机构代码",enterprise.get(oracleOperateService.formateCol("ENTPCODE")));
            basic.put("行业类别",enterprise.get(oracleOperateService.formateCol("INDUSTRYTYPE")));
            basic.put("法人单位名称", enterprise.get(oracleOperateService.formateCol("FILLENTPNAME")));
            basic.put("固定资产总值", enterprise.get(oracleOperateService.formateCol("FIXEDASSETS")));
//            basic = ESOperate.emptyOperate(basic);
            ESOperate.dataToDic(basic, "qajj_pucentp_v");
            LinkedHashMap div1 = new LinkedHashMap();
            div1.put("name", "基础信息");
            div1.put("value", basic);
            result.add(div1);

            LinkedHashMap other = new LinkedHashMap();
            other.put("法人代表姓名",enterprise.get(oracleOperateService.formateCol("POLICYNAME")));
            other.put("法人代表电话", enterprise.get(oracleOperateService.formateCol("POLICYTEL")));
            other.put("工商注册地址", enterprise.get(oracleOperateService.formateCol("REGISTERADDR")));
            other.put("工商注册代码", enterprise.get(oracleOperateService.formateCol("REGISTERID")));
            other.put("注册部门",enterprise.get(oracleOperateService.formateCol("REGISTERDEPT")));
            other.put("注册资金",enterprise.get(oracleOperateService.formateCol("REGISTERMONTY")));
            other.put("注册安全工程师人数",enterprise.get(oracleOperateService.formateCol("REGISTERSAFEENGINEER")));
            other.put("注册用户",enterprise.get(oracleOperateService.formateCol("REGISTERUSER")));
//            other = ESOperate.emptyOperate(other);
            ESOperate.dataToDic(other, "qajj_pucentp_v");
            LinkedHashMap div2 = new LinkedHashMap();
            div2.put("name","工商信息");
            div2.put("value",other);
            result.add(div2);

        }catch (Exception e){
            logger.error("query es error!",e);
        }


        return result;
    }

    /**
     * 安监-关联查询安全生产责任
     * 修正关联关系逻辑：安监-企业信息采集表->落图表->详细信息表，改为：QAJJ_PUCENTP_V-》QAJJ_INSRECORD_V-》QAJJ_INSTROUBLEREGCHECK_V
     * 因为落图表已经被去重了，所以导致信息被过滤。并且不写自查逻辑，只写巡查逻辑
     * 去掉自查逻辑，只留巡查
     * @param id
     * @return
     */
    @ApiOperation(value = "安监隐患巡查，关联查询安全生产责任", notes = "安监隐患巡查，关联查询安全生产责任")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id:40d40160f5c2453da7ea1db3b39a1ae7", paramType = "query")
    })
    @RequestMapping(value = "/check/responsible", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LinkedHashMap> responsible( String id){

        List<LinkedHashMap> result=new ArrayList<LinkedHashMap>();
        try {

            /*安全生产责任人*/
            LinkedHashMap produceResponse = null;
            	/* responsible, 根据隐患id获取企业innerid，找到 qajj_pucmainmanager_v 中的 安全责任人 */
            String sql = "select b.\"certifydate\", b.\"certifydept\", b.\"certifyname\", b.\"certifyno\", b.\"certifyvalidity\", b.\"servicecut\", b.\"tel\"\n" +
                    "from \"t_insrecord\" a , \"qajj_pucmainmanager_v\" b where a.\"id\"='&ID' \n" +
                    "and a.\"innerid\"=b.\"innerid\"";
            sql = sql.replaceAll("&ID",id);
            List<LinkedHashMap> produceResponseList = oracleOperateService.querySql(sql);
            if (produceResponseList.size()>0)
                produceResponse=produceResponseList.get(0);
            else
                return null;

            LinkedHashMap pr = new LinkedHashMap();
            pr.put("发证时间",produceResponse.get(oracleOperateService.formateCol("CERTIFYDATE")));
            pr.put("发证机关",produceResponse.get(oracleOperateService.formateCol("CERTIFYDEPT")));
            pr.put("证书名称",produceResponse.get(oracleOperateService.formateCol("CERTIFYNAME")));
            pr.put("证书编号",produceResponse.get(oracleOperateService.formateCol("CERTIFYNO")));
            pr.put("证书有效期",produceResponse.get(oracleOperateService.formateCol("CERTIFYVALIDITY")));
            pr.put("责任划分",produceResponse.get(oracleOperateService.formateCol("SERVICECUT")));
            pr.put("联系电话",produceResponse.get(oracleOperateService.formateCol("TEL")));
//            pr = ESOperate.emptyOperate(pr);
            ESOperate.dataToDic(pr,"qajj_pucmainmanager_v");
            LinkedHashMap div1 = new LinkedHashMap();
            div1.put("name","安全生产责任人");
            div1.put("value",pr);
            result.add(div1);
            /*安全生产管理人员*/
            LinkedHashMap produceManage = null;
	        /* responsible, 根据隐患id获取企业innerid，找到 qajj_pucsafemanagerman_v 中的 安全生产管理人员 */
            sql = "select b.\"certifydate\", b.\"certifydept\", b.\"certifyname\", b.\"certifyno\", b.\"certifyvalidity\", b.\"duty\", b.\"job\", b.\"name\", b.\"tel\"\n" +
                    "from \"t_insrecord\" a , \"qajj_pucsafemanagerman_v\" b where a.\"id\"='&ID' \n" +
                    "and a.\"innerid\"=b.\"innerid\"";
            sql = sql.replaceAll("&ID",id);
            List<LinkedHashMap> produceManageList = oracleOperateService.querySql(sql);
            if (produceManageList.size()>0)
                produceManage=produceManageList.get(0);
            else
                return null;

            LinkedHashMap pm = new LinkedHashMap();
            pm.put("发证时间", produceManage.get(oracleOperateService.formateCol("CERTIFYDATE")));
            pm.put("发证机关", produceManage.get(oracleOperateService.formateCol("CERTIFYDEPT")));
            pm.put("证书名称", produceManage.get(oracleOperateService.formateCol("CERTIFYNAME")));
            pm.put("证书编号", produceManage.get(oracleOperateService.formateCol("CERTIFYNO")));
            pm.put("证书有效期", produceManage.get(oracleOperateService.formateCol("CERTIFYVALIDITY")));
            pm.put("职责", produceManage.get(oracleOperateService.formateCol("DUTY")));
            pm.put("职务", produceManage.get(oracleOperateService.formateCol("JOB")));
            pm.put("姓名", produceManage.get(oracleOperateService.formateCol("NAME")));
            pm.put("手机号码", produceManage.get(oracleOperateService.formateCol("TEL")));
//            pm = ESOperate.emptyOperate(pm);
            ESOperate.dataToDic(pm, "qajj_pucsafemanagerman_v");
            LinkedHashMap div2 = new LinkedHashMap();
            div2.put("name","安全生产管理人员");
            div2.put("value",pm);
            result.add(div2);

            /*安全生产管理机构*/
            LinkedHashMap produceOrgan = null;
            	/* responsible, 根据隐患id获取企业innerid，找到 qajj_pucsafemanagerorgan_v 中的 安全生产管理机构 */
            sql = "select b.\"organname\", b.\"organhandtel\", b.\"organpeople\", b.\"organmail\", b.\"organman\", b.\"organmanduty\"\n" +
                    "from \"t_insrecord\" a , \"qajj_pucsafemanagerorgan_v\" b where a.\"id\"='&ID' \n" +
                    "and a.\"innerid\"=b.\"innerid\"";
            sql = sql.replaceAll("&ID",id);
            List<LinkedHashMap> produceOrganList = oracleOperateService.querySql(sql);
            if (produceManageList.size()>0)
                produceOrgan=produceOrganList.get(0);
            else
                return null;

            LinkedHashMap po = new LinkedHashMap();
            po.put("机构名称",produceOrgan.get(oracleOperateService.formateCol("ORGANNAME")));
            po.put("办公电话",produceOrgan.get(oracleOperateService.formateCol("ORGANHANDTEL")));
            po.put("成员",produceOrgan.get(oracleOperateService.formateCol("ORGANPEOPLE")));
            po.put("联系邮箱",produceOrgan.get(oracleOperateService.formateCol("ORGANMAIL")));
            po.put("机构负责人",produceOrgan.get(oracleOperateService.formateCol("ORGANMAN")));
            po.put("职务",produceOrgan.get(oracleOperateService.formateCol("ORGANMANDUTY")));
//            po = ESOperate.emptyOperate(pm);
            ESOperate.dataToDic(po, "qajj_pucsafemanagerorgan_v");
            LinkedHashMap div3 = new LinkedHashMap();
            div3.put("name","安全生产管理机构");
            div3.put("value",po);
            result.add(div3);

        }catch (Exception e){
            logger.error("query es error!",e);
        }
        return result;
    }


    /**
     * 安监-关联查询隐患信息-查询该企业所有的隐患，包括巡查和自查
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "安监隐患巡查，关联查询该企业所有的隐患", notes = "安监隐患巡查，关联查询该企业所有的隐患")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "建筑id:40d40160f5c2453da7ea1db3b39a1ae7", paramType = "query")
    })
    @RequestMapping(value = "/check/hd_info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public LinkedHashMap hdInfo(String id, int pageNum, int pageSize){
        LinkedHashMap result = new LinkedHashMap();
        try {
            List<LinkedHashMap> data = new ArrayList<LinkedHashMap>();

            LinkedHashMap checkD = null;
            /* hd_info 根据隐患innerID企业id, 然后找到隐患集合，最后找到 QAJJ_INSRECORD_V 中隐患的详细信息 */
            String sql = "select b.\"checkdate\", b.\"islevel\", b.\"inspectcontent\", b.\"basecase\"\n" +
                    "from \"t_insrecord\" a , \"qajj_instroubleregcheck_v\" b where \n" +
                    "a.\"innerid\"=(select \"innerid\" from \"t_insrecord\" a where a.\"id\"='&ID')\n" +
                    "and a.\"id\"=b.\"insrecordid\"";
            sql = sql.replaceAll("&ID", id);
            List<LinkedHashMap> checkDList = oracleOperateService.querySql(sql);

            for (int i = 0; i < checkDList.size(); ++i) {
                checkD = checkDList.get(i);
                LinkedHashMap h = new LinkedHashMap();
//                            h.put("序号", checkD.get("id"));/*将序号改为时间*/
                String[] tmp = checkD.get(oracleOperateService.formateCol("CHECKDATE")).toString().split(":");
                String date = tmp[0] + ":" + tmp[1] + ":" + tmp[2];

                    /*处理特殊时间：1899/12/31 00:00:00:000000*/
                if (checkD.get(oracleOperateService.formateCol("CHECKDATE")).toString().equalsIgnoreCase("1899/12/31 00:00:00:000000"))
                    date = null;

                h.put("序号", date);/*将序号改为时间*/
                h.put("隐患等级", checkD.get(oracleOperateService.formateCol("islevel")));
                h.put("隐患内容", checkD.get(oracleOperateService.formateCol("inspectcontent")));
                h.put("隐患基本情况", checkD.get(oracleOperateService.formateCol("basecase")));
                h.put("巡查方式", "巡查");
                ESOperate.dataToDic(h, "qajj_instroubleregcheck_v");
                data.add(h);
            }

            int totalRecords = data.size();
            int fromIndex = (pageNum-1)*pageSize;
            int endIndex = pageNum*pageSize+1 < data.size() ? pageNum*pageSize+1 : data.size();

            data = data.subList(fromIndex,endIndex);
            data = ESOperate.emptyOperate(data);
            int pageTotal = (totalRecords - 1) / pageSize + 1;
            result.put("data",data);

            result.put("pageTotal",pageTotal);
            result.put("totalRecords",totalRecords);
            result.put("pageNum", pageNum);
        } catch (Exception e) {
            logger.error("query es error!",e);
        }
        return result;
    }
}
