/*
package com.cetccity.operationcenter.webframework.web.controller.hidden_danger;

import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TimeValueModel;
import com.cetccity.operationcenter.webframework.hiddendanger.service.BLK_SANXIAO_PLACEService;
import com.cetccity.operationcenter.webframework.web.model.incident.TotalDataModel;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BLK_SANXIAO_PLACE;
import com.cetccity.operationcenter.webframework.web.model.iot.YilaodianAlarmModel;
import com.cetccity.operationcenter.webframework.web.service.HiddenDangerChartService;
import com.cetccity.operationcenter.webframework.web.service.IOT_EVENTService;
import com.cetccity.operationcenter.webframework.web.service.XIAOFANG_YINHUANService;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

*/
/**
 * 安全隐患一张图图表接口
 *//*

@CacheConfig(cacheNames = "hiddenDangerChart")
@RestController
@RequestMapping("hd_chart")
@Api(tags = "安全隐患一张图图表接口")
public class HiddenDangerChart {
    private static final Logger logger = LoggerFactory.getLogger(HiddenDangerChart.class);

    private static final String[] futianStreet =new String[]{"园岭街道办事处","南园街道办事处","福田街道办事处","沙头街道办事处","梅林街道办事处","华富街道办事处","香蜜湖街道办事处","莲花街道办事处","华强北街道办事处","福保街道办事处"};
    private static final int[] futianStreetCode = new int[]{440304002,440304001,440304003,440304004,440304006,440304008,440304005,440304007,440304009,440304010 };
    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    HiddenDangerChartService hiddenDangerChartService;

    @Autowired
    XIAOFANG_YINHUANService xIAOFANG_YINHUANService;

    @Autowired
    IOT_EVENTService iOT_EVENTService;

    @Autowired
    BLK_SANXIAO_PLACEService bLK_SANXIAO_PLACEService;

    */
/**
     * 区域安全隐患-饼图
     * @return
     *//*


    @Cacheable(key = "'allRegionHDPie'+#season")
    @ApiOperation(value = "区域安全隐患-饼图", notes = "区域安全隐患-饼图" +
            "\n三小场所隐患：BLK_SANXIAO_PLACE。PLACE_TYPE场所类型：60021小档口, 60022小作坊，60023小娱乐场所" +
            "\n安监类隐患：TB_YINHUANFENBU_002"
    )
    @ApiImplicitParam(name = "season", value = "季度(1、2、3、4）,由于数据不够，所以无论传多少，都是返回一年的数据", paramType = "query" , example="1")
    @RequestMapping(value = "/all_region_hd_pie", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public LinkedHashMap allRegionHDPie(int season){
        LinkedHashMap result = new LinkedHashMap();
        result.put("name","区域隐患");
        ArrayList list = new ArrayList();
        result.put("data",list);
        String safetyCheckHD = "TB_YINHUANFENBU_002";*/
/*安监类隐患*//*

        List<LinkedHashMap> safetyCheckAllData=null;*/
/*安监类隐患所有数据*//*

        int yzgyh = 0;*/
/*安监类隐患：已整改*//*

        int zgzyh = 0;*/
/*安监类隐患：整改中*//*

        int yqyh = 0; */
/*逾期未整改*//*

        String fireControlHD = "";*/
/*消防类隐患，TODO 暂无表结构*//*

        String threePreventHD = "";*/
/*三方类隐患，TODO 暂无表结构*//*

        String threeSmallPlaceHD = "BLK_SANXIAO_PLACE";*/
/*三小场所隐患*//*

        List<LinkedHashMap> threeSmallPlaceAllData=null;*/
/*三小场所隐患所有数据*//*

        int smallWorkShop = 0;*/
/*三小场所：小作坊*//*

        int smallEnterainment = 0;*/
/*三小场所：小娱乐场所*//*

        int smallshop = 0;*/
/*三小场所：小档口*//*


        try {
//            safetyCheckAllData = ESOperate.queryObjectbyIndex(ESOperate.getIndexName(ESOperate.dbName,safetyCheckHD));
//            threeSmallPlaceAllData = ESOperate.queryObjectbyIndex(ESOperate.getIndexName(ESOperate.dbName,threeSmallPlaceHD));
            safetyCheckAllData = oracleOperateService.queryAllDataByTableName(safetyCheckHD);
            threeSmallPlaceAllData = oracleOperateService.queryAllDataByTableName(threeSmallPlaceHD);
        } catch (Exception e) {
            logger.error("query es error!\n",e);
        }
        if (safetyCheckAllData!=null){
            */
/*遍历安监数据，统计小类数量*//*

              for (int i = 0; i < safetyCheckAllData.size(); ++i){
                yzgyh += Integer.parseInt((String) safetyCheckAllData.get(i).get("YZGYH"));*/
/*已整改隐患*//*

                yzgyh += Integer.parseInt((String) safetyCheckAllData.get(i).get("YZGYH2"));*/
/*已整改隐患2*//*

                zgzyh += Integer.parseInt((String) safetyCheckAllData.get(i).get("ZGZYH"));*/
/*整改中隐患*//*

                zgzyh += Integer.parseInt((String) safetyCheckAllData.get(i).get("ZGZYH2"));*/
/*整改中隐患2*//*

                yqyh += Integer.parseInt((String) safetyCheckAllData.get(i).get("YQYH"));*/
/*逾期隐患*//*

                yqyh += Integer.parseInt((String) safetyCheckAllData.get(i).get("YQYH2"));*/
/*逾期隐患2*//*

            }
            int total = yzgyh + zgzyh + yqyh;*/
/*安监类隐患总数*//*

            ArrayList arrayList = (ArrayList) result.get("data");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("value",total);
            ArrayList arrayList1 = new ArrayList();
            LinkedHashMap linkedHashMap1 = new LinkedHashMap();
            linkedHashMap1.put("value",yzgyh);
            linkedHashMap1.put("name","已整改隐患");
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put("value",zgzyh);
            linkedHashMap2.put("name","已整中隐患");
            LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            linkedHashMap3.put("value",yqyh);
            linkedHashMap3.put("name","逾期隐患");
            arrayList1.add(linkedHashMap1);
            arrayList1.add(linkedHashMap2);
            arrayList1.add(linkedHashMap3);
            linkedHashMap.put("name","安监类隐患");
            linkedHashMap.put("data",arrayList1);
            arrayList.add(linkedHashMap);
        }
        */
/*遍历三小场所类数据，统计小类数量*//*

        if (threeSmallPlaceAllData!=null){
            for (int i = 0; i < threeSmallPlaceAllData.size(); ++i){
                String csxz = (String) threeSmallPlaceAllData.get(i).get("PLACE_TYPE");
                if("60022".equalsIgnoreCase(csxz)) {
                    smallWorkShop ++;*/
/*三小场所：小作坊*//*

                }else if("60023".equalsIgnoreCase(csxz)) {
                    smallEnterainment ++;*/
/*三小场所：小娱乐场所*//*

                }else if("60021".equalsIgnoreCase(csxz)) {
                    smallshop ++;*/
/*三小场所：小档口*//*

                }
            }
            int total = smallWorkShop + smallEnterainment + smallshop;
            ArrayList arrayList = (ArrayList) result.get("data");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("value",total);
            ArrayList arrayList1 = new ArrayList();
            LinkedHashMap linkedHashMap1 = new LinkedHashMap();
            linkedHashMap1.put("value",smallWorkShop);
            linkedHashMap1.put("name","小作坊");
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put("value",smallEnterainment);
            linkedHashMap2.put("name","小娱乐场所");
            LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            linkedHashMap3.put("value",smallshop);
            linkedHashMap3.put("name","小档口");
            arrayList1.add(linkedHashMap1);
            arrayList1.add(linkedHashMap2);
            arrayList1.add(linkedHashMap3);
            linkedHashMap.put("name","三小场所类");
            linkedHashMap.put("data",arrayList1);
            arrayList.add(linkedHashMap);
        }
        return result;
    }

    */
/**
     * 区域隐患数量-条形图
     * @return
     *//*

    @Cacheable(key = "'regionHDNumLine'+#season")
    @ApiOperation(value = "区域隐患数量-条形图", notes = "区域隐患数量-条形图" +
            "\n三小场所隐患：BLK_SANXIAO_PLACE。PLACE_TYPE场所类型：60021小档口, 60022小作坊，60023小娱乐场所" +
            "\n安监类隐患：TB_YINHUANFENBU_002"
    )
    @ApiImplicitParam(name = "season", value = "季度(1、2、3、4）,由于数据不够，所以无论传多少，都是返回一年的数据", paramType = "query" , example = "1")
    @RequestMapping(value = "/region_hd_num_line", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public NameDataModel regionHDNumLine_season(int season)throws IOException{
        NameDataModel nameDataModel = new NameDataModel();
        InputStream inputStream =ESOperate.class.getResourceAsStream("/street.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,"UTF-8"));
        String street = properties.getProperty("street");
        String[] street_array = street.split(",");
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        //三防类--易涝点、道路病害
        NameDataModel NameDataModel_sanfang = new NameDataModel();
        List<NameValueModel> nameValueModel_list_sanfang = new ArrayList<NameValueModel>();
        for (int i =0;i<street_array.length;i++){
            String street_code = properties.getProperty(street_array[i]);
            int TB_ROAD_DISEASECount = hiddenDangerChartService.getSanfangTB_ROAD_DISEASECount(street_code);
            nameValueModel_list_sanfang.add(NameValueModel.builder().name(street_array[i]).value(String.valueOf(TB_ROAD_DISEASECount)).build());
        }
        NameDataModel_sanfang.setName("三防类");
        NameDataModel_sanfang.setData(nameValueModel_list_sanfang);
        //三小场所
        NameDataModel NameDataModel_sanxiaoplace = new NameDataModel();
        List<NameValueModel> nameValueModel_list_sanxiaoplace = new ArrayList<NameValueModel>();
        for (int i =0;i<street_array.length;i++){
            String street_code = properties.getProperty(street_array[i]);
            int TB_SANXIAO_PLACECount = hiddenDangerChartService.getBLK_SANXIAO_PLACECount(street_code);
            nameValueModel_list_sanxiaoplace.add(NameValueModel.builder().name(street_array[i]).value(String.valueOf(TB_SANXIAO_PLACECount)).build());
        }
        NameDataModel_sanxiaoplace.setName("三小场所");
        NameDataModel_sanxiaoplace.setData(nameValueModel_list_sanxiaoplace);

        //安监类-TB_YINHUANFENBU_002
        NameDataModel NameDataModel_anjian = new NameDataModel();
        List<NameValueModel> nameValueModel_list_anjian = new ArrayList<NameValueModel>();
        for (int i =0;i<street_array.length;i++){
            String street_code = properties.getProperty(street_array[i]);
            int tB_YINHUANFENBU_002Count = hiddenDangerChartService.getTB_YINHUANFENBU_002Count(street_code);
            nameValueModel_list_anjian.add(NameValueModel.builder().name(street_array[i]).value(String.valueOf(tB_YINHUANFENBU_002Count)).build());
        }
        NameDataModel_anjian.setName("安监类");
        NameDataModel_anjian.setData(nameValueModel_list_anjian);

        nameDataModel_list.add(NameDataModel_sanfang);
        nameDataModel_list.add(NameDataModel_sanxiaoplace);
        nameDataModel_list.add(NameDataModel_anjian);

        nameDataModel.setName("区域隐患数量");
        nameDataModel.setData(nameDataModel_list);
        return nameDataModel;
    }

    */
/**
     * 消防隐患数量-数值
     * @return
     *//*

    @ApiOperation(value = "消防隐患数量-数值-XIAOFANG_YINHUAN", notes = "消防隐患数量-数值-XIAOFANG_YINHUAN")
    @RequestMapping(value = "/xiaofang/yinhuan", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public YilaodianAlarmModel xIAOFANG_YINHUAN(String season){
        YilaodianAlarmModel yilaodianAlarmModel = new YilaodianAlarmModel();
        int XIAOFANG_YINHUAN_HongseCount = xIAOFANG_YINHUANService.getXIAOFANG_YINHUANTypeContent("红色");
        int XIAOFANG_YINHUAN_HuangseCount = xIAOFANG_YINHUANService.getXIAOFANG_YINHUANTypeContent("黄色");
        int XIAOFANG_YINHUAN_ChengseCount = xIAOFANG_YINHUANService.getXIAOFANG_YINHUANTypeContent("橙色");
        int total = XIAOFANG_YINHUAN_HongseCount+XIAOFANG_YINHUAN_HuangseCount+XIAOFANG_YINHUAN_ChengseCount;
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        nameValueModel_list.add(NameValueModel.builder().name("红色隐患").value(String.valueOf(XIAOFANG_YINHUAN_HongseCount)).build());
        nameValueModel_list.add(NameValueModel.builder().name("黄色隐患").value(String.valueOf(XIAOFANG_YINHUAN_HuangseCount)).build());
        nameValueModel_list.add(NameValueModel.builder().name("橙色隐患").value(String.valueOf(XIAOFANG_YINHUAN_ChengseCount)).build());

        yilaodianAlarmModel.setName("消防隐患");
        yilaodianAlarmModel.setTotal(total);
        yilaodianAlarmModel.setData(nameValueModel_list);
        return yilaodianAlarmModel;
    }

    */
/**
     * 三防隐患数量-易涝点数值
     * @return
     *//*

    @ApiOperation(value = "三防隐患数量-易涝点数值-IOT_EVENT", notes = "三防隐患数量-易涝点数值-IOT_EVENT")
    @RequestMapping(value = "/sanfang/yilaodian", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public YilaodianAlarmModel sanfangYilaodian(String season){
        YilaodianAlarmModel yilaodianAlarmModel = iOT_EVENTService.getIOT_EVENT_list("0026");
        return yilaodianAlarmModel;
    }

    */
/**
     * 区域隐患数量-条形图
     * @return
     *//*

    @Cacheable(key = "'regionHDNumLine'+#season")
    @ApiOperation(value = "区域隐患数量-条形图", notes = "区域隐患数量-条形图" +
            "\n三小场所隐患：BLK_SANXIAO_PLACE。PLACE_TYPE场所类型：60021小档口, 60022小作坊，60023小娱乐场所" +
            "\n安监类隐患：TB_YINHUANFENBU_002"
    )
    @ApiImplicitParam(name = "season", value = "季度(1、2、3、4）,由于数据不够，所以无论传多少，都是返回一年的数据", paramType = "query" , example = "1")
    @RequestMapping(value = "/region_hd_num_line_season", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public LinkedHashMap regionHDNumLine(int season){
        LinkedHashMap result = new LinkedHashMap();

        */
/*初始化返回值*//*

        ArrayList list = new ArrayList();
        result.put("name","区域隐患数量");
        result.put("data",list);

        String threeSmallPlacesTB = "BLK_SANXIAO_PLACE";
        String threeSmallPlacesType = "三小场所类";
        String securityCheckTB = "TB_YINHUANFENBU_002";
        String securityCheckType = "安监类";
        String[] typeArray = new String[]{"三小场所类","安监类"};

        */
/*初始化data结构*//*

        for (int i = 0; i < futianStreet.length; ++i){
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            String simpleStreet = futianStreet[i].substring(0,futianStreet[i].length()-5);
            linkedHashMap.put("name",simpleStreet);
            List list1 = new ArrayList();

            for (int j = 0; j < typeArray.length; ++j){
                LinkedHashMap linkedHashMap1 = new LinkedHashMap();
                linkedHashMap1.put("value",0);
                linkedHashMap1.put("name",typeArray[j]);
                list1.add(linkedHashMap1);
            }

            linkedHashMap.put("data",list1);
            list.add(linkedHashMap);
        }

        regionHDLineService(threeSmallPlacesTB,(List) result.get("data"),threeSmallPlacesType);
//        regionHDLineService(securityCheckTB,(List) result.get("data"),securityCheckType);
        regionHDLineServiceTemp(securityCheckTB, (List) result.get("data"),securityCheckType);

        return result;
    }

    */
/**
     * 判断该街道名是否属于福田区
     * @param streetName
     * @return
     *//*

    private boolean isFutian(String streetName){
        for (int i = 0; i < futianStreet.length; ++i){
            String simpleStreet = futianStreet[i].substring(0,futianStreet[i].length()-5);
            if (streetName.equalsIgnoreCase(futianStreet[i]) || streetName.equalsIgnoreCase(simpleStreet))
                return true;
        }

        return false;
    }

    */
/**
     * 判断街道的代码是否属于福田区
     * @param streetCode
     * @return
     *//*

    private boolean isFutian(int streetCode){
        for (int i = 0; i < futianStreet.length; ++i){
            if (streetCode == futianStreetCode[i])
                return true;
        }
        return false;
    }

    */
/**
     * 将街道代码转换为街道名
     * @param streetCode
     * @return
     *//*

    private String streetCode2Name(int streetCode){
        for (int i = 0; i < futianStreet.length; ++i){
            if (streetCode == futianStreetCode[i])
                return futianStreet[i].substring(0,futianStreet[i].length()-5);
        }
        return null;
    }

    */
/**
     * 区域隐患统计服务-通用表，需要有STREET_CODE字段
     * @param tbName
     * @param data
     * @param type
     *//*

    private void regionHDLineService(String tbName, List data, String type){
        */
/*查询所有数据*//*

        try {
//            List<LinkedHashMap> linkedHashMaps = ESOperate.queryObjectbyIndex(ESOperate.getIndexName(ESOperate.dbName,tbName));
            logger.info("---tbName---"+tbName);
            List<LinkedHashMap> linkedHashMaps = oracleOperateService.queryAllDataByTableName(tbName);
            for (int i = 0; i < linkedHashMaps.size(); ++i){
                */
/*排空*//*

                if (linkedHashMaps.get(i).get("STREET_CODE")==null)continue;
                int streetCode = Integer.parseInt(linkedHashMaps.get(i).get("STREET_CODE").toString());
                */
/*过滤出福田街道*//*

                if (!isFutian(streetCode)) continue;
                String streetName = streetCode2Name(streetCode);    //街道代码转换为街道名称，该方法仅限福田区
                int streetIndex = -1;
                for (int j = 0; j < data.size(); ++j){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) data.get(j);
                    if (linkedHashMap.get("name").toString().equalsIgnoreCase(streetName)) {
                        streetIndex = j;
                        break;
                    }
                }
                if (streetIndex==-1){*/
/*当前街道不存在*//*

                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("name",streetName);
                    ArrayList list = new ArrayList();
                    LinkedHashMap linkedHashMap1 = new LinkedHashMap();
                    linkedHashMap1.put("value",1);
                    linkedHashMap1.put("name",type);
                    list.add(linkedHashMap1);
                    linkedHashMap.put("data",list);
                    data.add(linkedHashMap);
                }else {
                    */
/*获取到街道里面的data*//*

                    List list = (List) ((LinkedHashMap)data.get(streetIndex)).get("data");
                    */
/*判断当前类别是否存在*//*

                    int typeIndex = -1;
                    for (int j = 0; j < list.size(); ++j){
                        if(((LinkedHashMap)list.get(j)).get("name").toString().equalsIgnoreCase(type)){
                            typeIndex = j;
                            break;
                        }
                    }
                    if (typeIndex==-1){
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        linkedHashMap.put("value",1);
                        linkedHashMap.put("name",type);
                        list.add(linkedHashMap);
                    }else {
                        LinkedHashMap linkedHashMap = (LinkedHashMap) list.get(typeIndex);
                        int value = Integer.parseInt(linkedHashMap.get("value").toString());
                        value++;
                        linkedHashMap.put("value",value);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("query oracle error! tbName: "+tbName+"\n",e);
        }
    }

    */
/**
     * 区域隐患统计服务-临时服务，安监隐患目前不是标准表
     * @param tbName
     * @param data
     * @param type
     *//*

    private void regionHDLineServiceTemp(String tbName, List data, String type){
        */
/*查询所有数据*//*

        try {
//            List<LinkedHashMap> linkedHashMaps = ESOperate.queryObjectbyIndex(ESOperate.getIndexName(ESOperate.dbName,tbName));
            List<LinkedHashMap> linkedHashMaps = oracleOperateService.queryAllDataByTableName(tbName);
            for (int i = 0; i < linkedHashMaps.size(); ++i){
                 */
/*排空*//*

                if (linkedHashMaps.get(i).get("STREET")==null)continue;
                String street = linkedHashMaps.get(i).get("STREET").toString();
                */
/*过滤出福田街道*//*

                if (!isFutian(street)) continue;
                int streetIndex = -1;
                for (int j = 0; j < data.size(); ++j){
                    LinkedHashMap linkedHashMap = (LinkedHashMap) data.get(j);
                    if (linkedHashMap.get("name").toString().equalsIgnoreCase(street)) {
                        streetIndex = j;
                        break;
                    }
                }
                if (streetIndex==-1){*/
/*当前街道不存在*//*

                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("name",street);
                    ArrayList list = new ArrayList();
                    LinkedHashMap linkedHashMap1 = new LinkedHashMap();
                    linkedHashMap1.put("value",1);
                    linkedHashMap1.put("name",type);
                    list.add(linkedHashMap1);
                    linkedHashMap.put("data",list);
                    data.add(linkedHashMap);
                }else {
                    */
/*获取到街道里面的data*//*

                    List list = (List) ((LinkedHashMap)data.get(streetIndex)).get("data");
                    */
/*判断当前类别是否存在*//*

                    int typeIndex = -1;
                    for (int j = 0; j < list.size(); ++j){
                        if(((LinkedHashMap)list.get(j)).get("name").toString().equalsIgnoreCase(type)){
                            typeIndex = j;
                            break;
                        }
                    }
                    if (typeIndex==-1){
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        linkedHashMap.put("value",1);
                        linkedHashMap.put("name",type);
                        list.add(linkedHashMap);
                    }else {
                        LinkedHashMap linkedHashMap = (LinkedHashMap) list.get(typeIndex);
                        int value = Integer.parseInt(linkedHashMap.get("value").toString());
                        value++;
                        linkedHashMap.put("value",value);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("query oracle error! tbName: "+tbName+"\n",e);
        }
    }

    */
/**
     * 三小场所折线图
     * @return
     *//*

    //@Cacheable(key = "'threeSmallPlacesHDLin'+#season")
    @ApiOperation(value = "三小场所折线图", notes = "三小场所折线图")
    @ApiImplicitParam(name = "season", value = "季度(1、2、3、4）,由于数据不够，所以无论传多少，都是返回一年的数据", paramType = "query" , example = "1")
    @RequestMapping(value = "/three_small_places_hd_line", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public TotalDataModel threeSmallPlacesHDLine(int season){
        TotalDataModel totalDataModel = new TotalDataModel();

        BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE = new BLK_SANXIAO_PLACE();
        long count = bLK_SANXIAO_PLACEService.getBLK_SANXIAO_PLACECount(bLK_SANXIAO_PLACE);

        String placeType[] = {"60021","60022","60023"};
        String placeType_line[] = {"redyh","orayh","yellowyh"};
        String month = "";
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        for (int i = 0;i<placeType.length;i++) {
            NameDataModel nameDataModel = new NameDataModel();
            List<TimeValueModel> timeValueModel_list = new ArrayList<TimeValueModel>();
            for (int j=11;j>=0;j--) {
                month = LoadMyUtil.getMyTime("MONTH", -j);
                BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE_month = new BLK_SANXIAO_PLACE();
                bLK_SANXIAO_PLACE_month.setPLACE_TYPE(placeType[i]);
                bLK_SANXIAO_PLACE_month.setCREATETIME(month);
                long count_month = bLK_SANXIAO_PLACEService.getBLK_SANXIAO_PLACECount(bLK_SANXIAO_PLACE_month);
                TimeValueModel timeValueModel = new TimeValueModel();
                timeValueModel.setTime(month);
                timeValueModel.setValue(String.valueOf(count_month));
                timeValueModel_list.add(timeValueModel);
            }
            nameDataModel.setName(placeType_line[i]);
            nameDataModel.setData(timeValueModel_list);
            nameDataModel_list.add(nameDataModel);
        }
        totalDataModel.setTotal(count);
        totalDataModel.setData(nameDataModel_list);
        return totalDataModel;
    }

    */
/**
     * 三小场所，时间字符串比较大小 判断>=
     * @param date1
     * @param date2
     * @return
     *//*

    private boolean compareDateStr(String date1, String date2){
        if (date2 ==null || date2.equalsIgnoreCase(""))return true;
        int year1 = Integer.parseInt(date1.split("-")[0]);
        int month1 = Integer.parseInt(date1.split("-")[1]);
        int year2 = Integer.parseInt(date2.split("-")[0]);
        int month2 = Integer.parseInt(date2.split("-")[1]);
        if (year1>year2)return true;
        if (year2>year1)return false;
        return month1 >= month2;
    }

    */
/**
     * 安监隐患统计
     * @return
     *//*

    @Cacheable(key = "'securityCheckHDCount'+#season")
    @ApiOperation(value = "安监隐患统计", notes = "安监隐患统计")
    @ApiImplicitParam(name = "season", value = "季度(1、2、3、4）,由于数据不够，所以无论传多少，都是返回一年的数据", paramType = "query" , example = "1")
    @RequestMapping(value = "/security_check_hd_count", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public LinkedHashMap securityCheckHDCount(int season){
        LinkedHashMap result = new LinkedHashMap();
        String tbName = "TB_YINHUANFENBU_002";
        List<LinkedHashMap> allData = null;
        try {
//            allData = ESOperate.queryObjectbyIndex(ESOperate.getIndexName(ESOperate.dbName,tbName));
            allData = oracleOperateService.queryAllDataByTableName(tbName);
        } catch (Exception e) {
            logger.error("es query error!\n indexName: "+ ESOperate.getIndexName(ESOperate.dbName,tbName),e);
        }
        if (allData==null)
            return null;

        int yhed = 0, yhing = 0, unyh = 0, total = 0;
        */
/*遍历数据*//*

        for (int i = 0; i < allData.size(); ++i){
            yhed += Integer.parseInt((String) allData.get(i).get("YZGYH"))+Integer.parseInt((String)allData.get(i).get("YZGYH2"));
            yhing += Integer.parseInt((String) allData.get(i).get("ZGZYH"))+Integer.parseInt((String)allData.get(i).get("ZGZYH"));
            unyh += Integer.parseInt((String) allData.get(i).get("YQYH"))+Integer.parseInt((String)allData.get(i).get("YQYH"));
        }
        total = yhed + yhing + unyh;
        result.put("total",total);*/
/*总数*//*

        result.put("yhed",yhed);*/
/*已整改   yzgyh + yzgyh2*//*

        result.put("yhing",yhing);*/
/*未整改  zgzyh + zgzyh2*//*

        result.put("unyh",unyh);*/
/*整改中  yqyh + yqyh2*//*

        return result;
    }
}
*/
