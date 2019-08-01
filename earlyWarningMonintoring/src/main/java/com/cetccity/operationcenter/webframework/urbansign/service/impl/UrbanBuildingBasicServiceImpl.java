package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.BlkSanxiaoPlaceMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BLK_SANXIAO_PLACE;
import com.cetccity.operationcenter.webframework.urbansign.api.model.HeatMap;
import com.cetccity.operationcenter.webframework.urbansign.api.model.MapLegalPerson;
import com.cetccity.operationcenter.webframework.urbansign.dao.BLK_BUILDINGMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.BLK_HOUSEMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.COMMUNITY_CODEMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.BLK_BUILDING;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.BLK_HOUSE;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.COMMUNITY_CODE;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanBuildingBasicService;
import com.cetccity.operationcenter.webframework.urbansign.tools.StreetTool;
import com.cetccity.operationcenter.webframework.urbansign.tools.UrbanMapReturnUtil;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
@Slf4j
public class UrbanBuildingBasicServiceImpl implements UrbanBuildingBasicService {

    @Autowired
    BLK_BUILDINGMapper bLK_BUILDINGMapper;

    @Autowired
    BlkSanxiaoPlaceMapper bLK_SANXIAO_PLACEMapper;

    @Autowired
    COMMUNITY_CODEMapper cOMMUNITY_CODEMapper;

    @Autowired
    BLK_HOUSEMapper bLK_HOUSEMapper;

    @Autowired
    UrbanMapReturnUtil urbanMapReturnUtil;

    @Autowired
    CommunityInfoService communityInfoService;

    @Autowired
    StreetTool streetTool;

    public NameValueModel leftOne(String street){
        String streetCode;
        BLK_BUILDING bLK_BUILDING = new BLK_BUILDING();
        bLK_BUILDING.setSJYT("2");
        if(StringUtils.isEmpty(street)){
            streetCode = null;
        }else{
            streetCode = cOMMUNITY_CODEMapper.getCommunity_CODE(street).get(0).getSTREET_CODE();
        }
        bLK_BUILDING.setJDDM(streetCode);
        long num = bLK_BUILDINGMapper.getCount(bLK_BUILDING);
        /*NameValueModel nameValueModel = new NameValueModel();
        nameValueModel.setName("居住建筑数量");
        nameValueModel.setValue(String.valueOf(num));*/
        return NameValueModel.builder().name("居住建筑数量").value(String.valueOf(num)).build();
    }

    public NameValueModel leftTwo(String street){
        String streetCode;
        BLK_BUILDING bLK_BUILDING = new BLK_BUILDING();
        bLK_BUILDING.setSJYT("3");
        if(StringUtils.isEmpty(street)){
            streetCode = null;
        }else{
            streetCode = cOMMUNITY_CODEMapper.getCommunity_CODE(street).get(0).getSTREET_CODE();
        }
        bLK_BUILDING.setJDDM(streetCode);
        long num = bLK_BUILDINGMapper.getCount(bLK_BUILDING);
        /*NameValueModel nameValueModel = new NameValueModel();
        nameValueModel.setName("商业建筑数量");
        nameValueModel.setValue(String.valueOf(num));*/
        return NameValueModel.builder().name("商业建筑数量").value(String.valueOf(num)).build();
    }

    public NameValueModel leftThree(String street){
        String streetCode;
        BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE = new BLK_SANXIAO_PLACE();
        if (StringUtils.isNotBlank(street)) {
            streetCode = communityInfoService.streetCodeByName(street);
            bLK_SANXIAO_PLACE.setSTREET_CODE(Objects.nonNull(streetCode) ? streetCode : "-1");
        }
        long num = bLK_SANXIAO_PLACEMapper.getBLK_SANXIAO_PLACECount(bLK_SANXIAO_PLACE);
        return NameValueModel.builder().name("棚旧改项目数量").value(String.valueOf(num)).build();
    }

    public List<NameValueModel> leftFour(String street){
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        if(StringUtils.isEmpty(street)){
            List<COMMUNITY_CODE> cOMMUNITY_CODE_list = cOMMUNITY_CODEMapper.getStreet_CODE();
            for (COMMUNITY_CODE cOMMUNITY_CODE:cOMMUNITY_CODE_list){
                BLK_BUILDING bLK_BUILDING = new BLK_BUILDING();
                bLK_BUILDING.setSTREET_CODE(cOMMUNITY_CODE.getSTREET_CODE());
                long num = bLK_BUILDINGMapper.getCount(bLK_BUILDING);
               /* NameValueModel nameValueModel = new NameValueModel();
                nameValueModel.setName(cOMMUNITY_CODE.getSTREET_NAME());
                nameValueModel.setValue(String.valueOf(num));
                nameValueModel_list.add(nameValueModel);*/
                nameValueModel_list.add(NameValueModel.builder().name(cOMMUNITY_CODE.getSTREET_NAME()).value(String.valueOf(num)).build());
            }
        }else{
            List<COMMUNITY_CODE> cOMMUNITY_CODE_list = cOMMUNITY_CODEMapper.getCommunity_CODE(street);
            for (COMMUNITY_CODE cOMMUNITY_CODE:cOMMUNITY_CODE_list) {
                BLK_BUILDING bLK_BUILDING = new BLK_BUILDING();
                bLK_BUILDING.setCOMMUNITY_CODE(cOMMUNITY_CODE.getCOMMUNITY_CODE());
                long num = bLK_BUILDINGMapper.getCount(bLK_BUILDING);
                /*NameValueModel nameValueModel = new NameValueModel();
                nameValueModel.setName(cOMMUNITY_CODE.getCOMMUNITY_NAME());
                nameValueModel.setValue(String.valueOf(num));
                nameValueModel_list.add(nameValueModel);*/
                nameValueModel_list.add(NameValueModel.builder().name(cOMMUNITY_CODE.getCOMMUNITY_NAME()).value(String.valueOf(num)).build());
            }
        }
       return nameValueModel_list;
    }
    //居住 BLK_BUILDING.SJYT=2  商业BLK_BUILDING.SJYT=3 BLK_BUILDING.JZLX 1:超高层楼宇2:高层建宇3:中层楼宇4:小高层楼宇5:多层建筑
    public List<NameDataModel> rightOne(String street){
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        String JZLX[] = {"1","2","3","4","5"};
        String JZLXName[] = {"超高层楼宇","高层建宇","中层楼宇","小高层楼宇","多层建筑"};
        String SJYT[] = {"2","3"};
        String SJYTName[] = {"居住建筑","商业建筑"};
        if(street==null){
            for (int i = 0;i<SJYT.length;i++) {
                NameDataModel nameDataModel = new NameDataModel();
                List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
                for (int j = 0; j < JZLX.length; j++) {
                    BLK_BUILDING bLK_BUILDING = new BLK_BUILDING();
                    bLK_BUILDING.setSJYT(SJYT[i]);
                    bLK_BUILDING.setJZLX(JZLX[j]);
                    long num = bLK_BUILDINGMapper.getCount(bLK_BUILDING);
                    /*NameValueModel nameValueModel = new NameValueModel();
                    nameValueModel.setName(JZLXName[j]);
                    nameValueModel.setValue(String.valueOf(num));
                    nameValueModel_list.add(nameValueModel);*/
                    nameValueModel_list.add(NameValueModel.builder().name(JZLXName[j]).value(String.valueOf(num)).build());
                }
                nameDataModel.setName(SJYTName[i]);
                nameDataModel.setData(nameValueModel_list);
                nameDataModel_list.add(nameDataModel);
            }
        }else{
            for (int i = 0;i<SJYT.length;i++) {
                NameDataModel nameDataModel = new NameDataModel();
                List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
                for (int j = 0; j < JZLX.length; j++) {
                    BLK_BUILDING bLK_BUILDING = new BLK_BUILDING();
                    bLK_BUILDING.setJD(street);
                    bLK_BUILDING.setSJYT(SJYT[i]);
                    bLK_BUILDING.setJZLX(JZLX[j]);
                    long num = bLK_BUILDINGMapper.getCount(bLK_BUILDING);
                    /*NameValueModel nameValueModel = new NameValueModel();
                    nameValueModel.setName(JZLXName[j]);
                    nameValueModel.setValue(String.valueOf(num));
                    nameValueModel_list.add(nameValueModel);*/
                    nameValueModel_list.add(NameValueModel.builder().name(JZLXName[j]).value(String.valueOf(num)).build());
                }
                nameDataModel.setName(SJYTName[i]);
                nameDataModel.setData(nameValueModel_list);
                nameDataModel_list.add(nameDataModel);
            }
        }
        return nameDataModel_list;
    }

    public List<NameValueModel> rightTwo(String street){
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        String SJYT[] = {"1","2","3","4","5","6","7","9"};
        String SJYTName[] = {"综合","住宅","商业","厂房","仓房","办公","临时建筑","其它"};
           for (int i=0;i<SJYT.length;i++){
               BLK_BUILDING bLK_BUILDING = new BLK_BUILDING();
               bLK_BUILDING.setJD(street);
               bLK_BUILDING.setSJYT(SJYT[i]);
               Long num = bLK_BUILDINGMapper.getSumArea(bLK_BUILDING);
               /*NameValueModel nameValueModel = new NameValueModel();
               nameValueModel.setName(SJYTName[i]);
               nameValueModel.setValue(String.valueOf(num));
               nameValueModel_list.add(nameValueModel);*/
               nameValueModel_list.add(NameValueModel.builder().name(SJYTName[i]).value(String.valueOf(num)).build());
           }
        return nameValueModel_list;
    }

    public List<NameValueModel> rightThree(String street){
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        String SJYT[] = {"1","2","3","4","5","6","7","9"};
        String SJYTName[] = {"综合","住宅","商业","厂房","仓房","办公","临时建筑","其它"};
        for (int i=0;i<SJYT.length;i++){
            BLK_BUILDING bLK_BUILDING = new BLK_BUILDING();
            bLK_BUILDING.setJD(street);
            bLK_BUILDING.setSJYT(SJYT[i]);
            long num = bLK_BUILDINGMapper.getCount(bLK_BUILDING);
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(SJYTName[i]);
            nameValueModel.setValue(String.valueOf(num));
            nameValueModel_list.add(nameValueModel);*/
            nameValueModel_list.add(NameValueModel.builder().name(SJYTName[i]).value(String.valueOf(num)).build());
        }
        return nameValueModel_list;
    }

    public List<NameValueModel> rightFour(String street){
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        String SYQK[] = {"1","2","3","4","5","9"};
        String SYQKName[] = {"自用","出租","部分出租","闲置","公共设施","其他"};
        for (int i=0;i<SYQK.length;i++){
            BLK_HOUSE bLK_HOUSE = new BLK_HOUSE();
            bLK_HOUSE.setSTREET_CODE(street);
            bLK_HOUSE.setSYQK(SYQK[i]);
            long num = bLK_HOUSEMapper.getCount(bLK_HOUSE);
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(SYQKName[i]);
            nameValueModel.setValue(String.valueOf(num));
            nameValueModel_list.add(nameValueModel);*/
            nameValueModel_list.add(NameValueModel.builder().name(SYQKName[i]).value(String.valueOf(num)).build());
        }
        return nameValueModel_list;
    }

    public HeatMap buildNumGradientLoadMap(String streetName){
        HeatMap heatMap = new HeatMap();
        List<MapLegalPerson> mapLegalPerson_list = new ArrayList<MapLegalPerson>();
        BLK_BUILDING bLK_BUILDING = new BLK_BUILDING();
        Map<String,Integer> map_hight_vaule = new HashMap<>();   //得出各街道值
        int num;
        if("".equals(streetName)||streetName==null) {
            List<COMMUNITY_CODE> Street_list = streetTool.getStreet_CODE();
            for (COMMUNITY_CODE cOMMUNITY_CODE:Street_list){
                bLK_BUILDING.setJDDM(cOMMUNITY_CODE.getSTREET_CODE());
                num = bLK_BUILDINGMapper.getCount(bLK_BUILDING);
                map_hight_vaule.put(cOMMUNITY_CODE.getSTREET_CODE(),num);
            }
            mapLegalPerson_list = urbanMapReturnUtil.loadMapModelOfStreet(Street_list,map_hight_vaule);
        }else{
            List<COMMUNITY_CODE> Street_community_list = streetTool.getCommunity_CODE(streetName);
            for (COMMUNITY_CODE cOMMUNITY_CODE:Street_community_list){
                bLK_BUILDING.setSQDM(cOMMUNITY_CODE.getCOMMUNITY_CODE());
                num = bLK_BUILDINGMapper.getCount(bLK_BUILDING);
                map_hight_vaule.put(cOMMUNITY_CODE.getCOMMUNITY_CODE(),num);
            }
            mapLegalPerson_list = urbanMapReturnUtil.loadMapModelOfCommunity(Street_community_list,map_hight_vaule);
        }
        heatMap.setName("建筑数量分布图");
        heatMap.setMax(mapLegalPerson_list.get(0).getMax());
        heatMap.setMin(mapLegalPerson_list.get(0).getMin());
        heatMap.setData(mapLegalPerson_list);
        return heatMap;
    }

    public HeatMap houseNumGradientLoadMap(String streetName){
        HeatMap heatMap = new HeatMap();
        List<MapLegalPerson> mapLegalPerson_list = new ArrayList<MapLegalPerson>();
        BLK_HOUSE bLK_HOUSE = new BLK_HOUSE();
        Map<String,Integer> map_hight_vaule = new HashMap<>();   //得出各街道值
        int num;
        if("".equals(streetName)||streetName==null) {
            List<COMMUNITY_CODE> Street_list = streetTool.getStreet_CODE();
            for (COMMUNITY_CODE cOMMUNITY_CODE:Street_list){
                bLK_HOUSE.setJDDM(cOMMUNITY_CODE.getSTREET_CODE());
                num = bLK_HOUSEMapper.getCount(bLK_HOUSE);
                map_hight_vaule.put(cOMMUNITY_CODE.getSTREET_CODE(),num);
            }
            mapLegalPerson_list = urbanMapReturnUtil.loadMapModelOfStreet(Street_list,map_hight_vaule);
        }else{
            List<COMMUNITY_CODE> Street_community_list = streetTool.getCommunity_CODE(streetName);
            for (COMMUNITY_CODE cOMMUNITY_CODE:Street_community_list){
                bLK_HOUSE.setSQDM(cOMMUNITY_CODE.getCOMMUNITY_CODE());
                num = bLK_HOUSEMapper.getCount(bLK_HOUSE);
                map_hight_vaule.put(cOMMUNITY_CODE.getCOMMUNITY_CODE(),num);
            }
             mapLegalPerson_list = urbanMapReturnUtil.loadMapModelOfCommunity(Street_community_list,map_hight_vaule);
        }
        heatMap.setName("房屋数量分布图");
        heatMap.setMax(mapLegalPerson_list.get(0).getMax());
        heatMap.setMin(mapLegalPerson_list.get(0).getMin());
        heatMap.setData(mapLegalPerson_list);
        return heatMap;
    }

    public Map buildNumGradientTip(String id){
        String tableName = "";
        int num;
        double area;
        double areaUnit;
        Map return_map;

        LinkedHashMap<String,String> map = new LinkedHashMap();
        try {
            COMMUNITY_CODE cOMMUNITY_CODE = new COMMUNITY_CODE();
            BLK_BUILDING bLK_BUILDING = new BLK_BUILDING();
            COMMUNITY_CODE cOMMUNITY_CODE_return;
            if(id.length()==9) {
                tableName = " ";
                cOMMUNITY_CODE.setSTREET_CODE(id);
                cOMMUNITY_CODE_return = streetTool.getCOMMUNITY_CODE(cOMMUNITY_CODE).get(0);
                bLK_BUILDING.setSTREET_CODE(id);
                area = streetTool.getCommunity_Area(cOMMUNITY_CODE_return.getCOMMUNITY_CODE());
            }else {
                tableName = "BLK_BUILDING_Community";
                cOMMUNITY_CODE.setCOMMUNITY_CODE(id);
                cOMMUNITY_CODE_return = streetTool.getCOMMUNITY_CODE(cOMMUNITY_CODE).get(0);
                bLK_BUILDING.setCOMMUNITY_CODE(id);
                area = streetTool.getCommunity_Area(cOMMUNITY_CODE_return.getCOMMUNITY_CODE());
            }
            areaUnit = area/1000000;
            num = bLK_BUILDINGMapper.getCount(bLK_BUILDING);
            map.put("id",id);
            map.put("STREETNAME",cOMMUNITY_CODE_return.getSTREET_NAME());
            map.put("COMMUNITYNAME",cOMMUNITY_CODE_return.getCOMMUNITY_NAME());
            map.put("VAULE",String.valueOf(num)+" 栋");
            map.put("AREA",String.valueOf(LoadMyUtil.retainToPoint(areaUnit,3))+" 平方公里");
            InputStream inputStream =ESOperate.class.getResourceAsStream("/tip.properties");
            Properties properties = new Properties();
            properties.load(new InputStreamReader(inputStream,"UTF-8"));

            String tableKey = properties.getProperty(ESOperate.dbName + "." + tableName + "Key");
            String tableValue = properties.getProperty(ESOperate.dbName + "." + tableName + "Value").toUpperCase();
            String HasDetail = "false";
            Boolean hasDetailInfo = Boolean.valueOf(HasDetail);
            String[] key = tableKey.split(","); //注意分隔符是需要转译滴...
            String[] value = tableValue.split(",");

            List result = urbanMapReturnUtil.tipContent(key,value,map);
            return_map = Tooltip.toolTipListToMap(result, hasDetailInfo);
            return_map.put("info_alert","0");
            return return_map;
        }catch (Exception e){
        	log.error(e.toString());
            return null;
        }
    }

    public Map houseNumGradientTip(String id){
        String tableName = "";
        int num;
        double area;
        double areaUnit;
        Map return_map;

        LinkedHashMap<String,String> map = new LinkedHashMap();
        try {
            COMMUNITY_CODE cOMMUNITY_CODE = new COMMUNITY_CODE();
            BLK_HOUSE bLK_HOUSE = new BLK_HOUSE();
            COMMUNITY_CODE cOMMUNITY_CODE_return;
            if(id.length()==9) {
                tableName = "BLK_HOUSE";
                bLK_HOUSE.setSTREET_CODE(id);
                cOMMUNITY_CODE.setSTREET_CODE(id);
                cOMMUNITY_CODE_return = streetTool.getCOMMUNITY_CODE(cOMMUNITY_CODE).get(0);
                area = streetTool.getStreet_Area(cOMMUNITY_CODE_return.getSTREET_NAME());
            }else{
                tableName = "BLK_HOUSE_Community";
                bLK_HOUSE.setCOMMUNITY_CODE(id);
                cOMMUNITY_CODE.setCOMMUNITY_CODE(id);
                cOMMUNITY_CODE_return = streetTool.getCOMMUNITY_CODE(cOMMUNITY_CODE).get(0);
                area = streetTool.getCommunity_Area(cOMMUNITY_CODE_return.getCOMMUNITY_CODE());
            }
            areaUnit = area/1000000;
            num = bLK_HOUSEMapper.getCount(bLK_HOUSE);
            map.put("id",id);
            map.put("STREETNAME",cOMMUNITY_CODE_return.getSTREET_NAME());
            map.put("COMMUNITYNAME",cOMMUNITY_CODE_return.getCOMMUNITY_NAME());
            map.put("VAULE",String.valueOf(num)+" 栋");
            map.put("AREA",String.valueOf(LoadMyUtil.retainToPoint(areaUnit,3))+" 平方公里");

            InputStream inputStream =ESOperate.class.getResourceAsStream("/tip.properties");
            Properties properties = new Properties();
            properties.load(new InputStreamReader(inputStream,"UTF-8"));

            String tableKey = properties.getProperty(ESOperate.dbName + "." + tableName + "Key");
            String tableValue = properties.getProperty(ESOperate.dbName + "." + tableName + "Value").toUpperCase();
            String HasDetail = "false";
            Boolean hasDetailInfo = Boolean.valueOf(HasDetail);
            String[] key = tableKey.split(","); //注意分隔符是需要转译滴...
            String[] value = tableValue.split(",");

            List result = urbanMapReturnUtil.tipContent(key,value,map);
            return_map = Tooltip.toolTipListToMap(result, hasDetailInfo);
            return_map.put("info_alert","0");
            return return_map;
        }catch (Exception e){
        	log.error(e.toString());
            return null;
        }
    }

}
