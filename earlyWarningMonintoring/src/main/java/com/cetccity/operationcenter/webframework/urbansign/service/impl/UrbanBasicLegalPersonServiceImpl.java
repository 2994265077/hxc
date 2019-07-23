package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.urbansign.api.model.HeatMap;
import com.cetccity.operationcenter.webframework.urbansign.api.model.MapDensity;
import com.cetccity.operationcenter.webframework.urbansign.api.model.MapLegalPerson;
import com.cetccity.operationcenter.webframework.urbansign.dao.COMMUNITY_CODEMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.P2P_BUSINESS_ADDRMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.P2P_BUSINESS_GLOBALMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.COMMUNITY_CODE;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.P2P_BUSINESS_ADDR;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.P2P_BUSINESS_GLOBAL;
import com.cetccity.operationcenter.webframework.urbansign.tools.StreetTool;
import com.cetccity.operationcenter.webframework.urbansign.tools.UrbanMapReturnUtil;
import com.cetccity.operationcenter.webframework.web.dao.BLK_LEGAL_PERSONMapper;
import com.cetccity.operationcenter.webframework.web.model.citySign.UrbanBasicLegalPersonLeftOne;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanBasicLegalPersonService;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.BLK_LEGAL_PERSON;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.web.util.RateUtil;
import com.cetccity.operationcenter.webframework.web.util.TableNameCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("urbanBasicLegalPersonService")
@Slf4j
public class UrbanBasicLegalPersonServiceImpl implements UrbanBasicLegalPersonService {

    @Autowired
    TableNameCountUtil tableNameCountUtil;

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    BLK_LEGAL_PERSONMapper bLK_LEGAL_PERSONMapper;

    @Autowired
    P2P_BUSINESS_GLOBALMapper p2P_BUSINESS_GLOBALMapper;

    @Autowired
    COMMUNITY_CODEMapper cOMMUNITY_CODEMapper;

    @Autowired
    P2P_BUSINESS_ADDRMapper p2P_BUSINESS_ADDRMapper;

    @Autowired
    StreetTool streetTool;

    @Autowired
    UrbanMapReturnUtil urbanMapReturnUtil;

    public List<UrbanBasicLegalPersonLeftOne> getLeftOne(String tableName) {
        //企业数据总量
        String enterprise_count = tableNameCountUtil.getCount(tableName);
        //获取当前月份和上个月份的数据总量
        Calendar cal = Calendar.getInstance();
        String date_current_month = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
        Calendar cal_upper_month = Calendar.getInstance();
        cal_upper_month.add(Calendar.MONTH, -1);
        String date_upper_month = new SimpleDateFormat("yyyy-MM").format(cal_upper_month.getTime());
        Calendar cal_next_month = Calendar.getInstance();
        cal_next_month.add(Calendar.MONTH, 1);
        String date_next_month = new SimpleDateFormat("yyyy-MM").format(cal_next_month.getTime());
        //上个月的数量
        String sql_upper = "select count(*) as COUNT from BLK_LEGAL_PERSON where YSBGXSJ >=to_date('"+date_upper_month+"' ,'yyyy-mm') and YSBGXSJ <=to_date('"+date_current_month+"' ,'yyyy-mm')";
        List<LinkedHashMap> map_list_upper = oracleOperateService.querySql(sql_upper);
        Integer upper_num = Integer.valueOf((String) map_list_upper.get(0).get("COUNT"));
        if(upper_num == null){ upper_num = 0; }
        //本月的数量
        String sql_current = "select count(*) as COUNT from BLK_LEGAL_PERSON where YSBGXSJ >=to_date('"+date_current_month+"' ,'yyyy-mm') and YSBGXSJ <=to_date('"+date_next_month+"' ,'yyyy-mm')";
        List<LinkedHashMap> map_list_current = oracleOperateService.querySql(sql_current);
        Integer current_num = Integer.valueOf((String) map_list_current.get(0).get("COUNT"));

        LinkedHashMap<String,String> map = RateUtil.getLeftOneModel(current_num, upper_num);
        String news_enterprise_rate = map.get("rate");
        String news_enterprise_trend = map.get("trend");
        List<UrbanBasicLegalPersonLeftOne> rbanBasicLegalPersonLeftOne_list = getLeftOneModel(enterprise_count,current_num,news_enterprise_rate,news_enterprise_trend);
        return rbanBasicLegalPersonLeftOne_list;
    }

    public List<UrbanBasicLegalPersonLeftOne> getLeftOne(String tableName,String column,String columnEntity) {
        //企业数据总量
        String enterprise_count = tableNameCountUtil.getCount(tableName, column, columnEntity);
        //获取当前月份和上个月份的数据总量
            Calendar cal = Calendar.getInstance();
            String date_current_month = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
            Calendar cal_upper_month = Calendar.getInstance();
            cal_upper_month.add(Calendar.MONTH, -1);
            String date_upper_month = new SimpleDateFormat("yyyy-MM").format(cal_upper_month.getTime());
            Calendar cal_next_month = Calendar.getInstance();
            cal_next_month.add(Calendar.MONTH, 1);
            String date_next_month = new SimpleDateFormat("yyyy-MM").format(cal_next_month.getTime());
            //上个月的数量
            String sql_upper = "select count(*) as COUNT from BLK_LEGAL_PERSON where "+column+" = '"+columnEntity+"' and YSBGXSJ >=to_date('"+date_upper_month+"' ,'yyyy-mm') and YSBGXSJ <=to_date('"+date_current_month+"' ,'yyyy-mm')";
            List<LinkedHashMap> map_list_upper = oracleOperateService.querySql(sql_upper);
            Integer upper_num = Integer.valueOf((String) map_list_upper.get(0).get("COUNT"));
            if(upper_num == null){ upper_num = 0; }
            //本月的数量
            String sql_current = "select count(*) as COUNT from BLK_LEGAL_PERSON where "+column+" = '"+columnEntity+"' and YSBGXSJ >=to_date('"+date_current_month+"' ,'yyyy-mm') and YSBGXSJ <=to_date('"+date_next_month+"' ,'yyyy-mm')";
            List<LinkedHashMap> map_list_current = oracleOperateService.querySql(sql_current);
            Integer current_num = Integer.valueOf((String) map_list_current.get(0).get("COUNT"));

        LinkedHashMap<String,String> map = RateUtil.getLeftOneModel(current_num, upper_num);
        String news_enterprise_rate = map.get("rate");
        String news_enterprise_trend = map.get("trend");
        List<UrbanBasicLegalPersonLeftOne> rbanBasicLegalPersonLeftOne_list = getLeftOneModel(enterprise_count,current_num,news_enterprise_rate,news_enterprise_trend);
        return rbanBasicLegalPersonLeftOne_list;
    }

    public List<UrbanBasicLegalPersonLeftOne> getLeftOneModel(String enterprise_count,Integer news_enterprise_count,String news_enterprise_rate,String news_enterprise_trend) {
        List<UrbanBasicLegalPersonLeftOne> rbanBasicLegalPersonLeftOne_list = new ArrayList<UrbanBasicLegalPersonLeftOne>();
        UrbanBasicLegalPersonLeftOne urbanBasicLegalPersonLeftOne_enterprise = new UrbanBasicLegalPersonLeftOne();
        urbanBasicLegalPersonLeftOne_enterprise.setName("企业总数");
        urbanBasicLegalPersonLeftOne_enterprise.setValue(enterprise_count);
        urbanBasicLegalPersonLeftOne_enterprise.setUnit("个");
        urbanBasicLegalPersonLeftOne_enterprise.setRate("");
        urbanBasicLegalPersonLeftOne_enterprise.setTrend("");
        rbanBasicLegalPersonLeftOne_list.add(urbanBasicLegalPersonLeftOne_enterprise);

        UrbanBasicLegalPersonLeftOne urbanBasicLegalPersonLeftOne_register = new UrbanBasicLegalPersonLeftOne();
        urbanBasicLegalPersonLeftOne_register.setName("本月新注册企业数");
        urbanBasicLegalPersonLeftOne_register.setValue(String.valueOf(news_enterprise_count));
        urbanBasicLegalPersonLeftOne_register.setUnit("个");
        urbanBasicLegalPersonLeftOne_register.setRate(news_enterprise_rate);
        urbanBasicLegalPersonLeftOne_register.setTrend(news_enterprise_trend);
        rbanBasicLegalPersonLeftOne_list.add(urbanBasicLegalPersonLeftOne_register);
        return rbanBasicLegalPersonLeftOne_list;
    }

    public String getLeftTwo(String tableName, String column, String columnEntity){
        String sql = "select count(*) as COUNT from BLK_LEGAL_PERSON where "+column+" = '"+columnEntity+"' ";
        List<LinkedHashMap> map_list_current = oracleOperateService.querySql(sql);
        String current_num = (String) map_list_current.get(0).get("COUNT");
        return current_num;
    }

    public List<Map.Entry<String, Integer>> getRightOne(){
        Map<String,Integer> map = new HashMap();
        String key[] = {"5210","7200","5120","5810","6230","5310","4000","2110","4500","2200","2130","4120","2000","6150",
                "1152","5000","4420","5110","2100","6000","1100","6810","1000","6130","6210","5200","5820","8000","7120"};
        BLK_LEGAL_PERSON bLK_LEGAL_PERSON = new BLK_LEGAL_PERSON();
        bLK_LEGAL_PERSON.setYYZT("1");    //YYZT = '1'   经营状态
        for(int i=0;i<key.length;i++) {
            bLK_LEGAL_PERSON.setHYFL(key[i]);
            int count = bLK_LEGAL_PERSONMapper.getCount(bLK_LEGAL_PERSON);
            map.put(LoadMyUtil.getPropertiesVauleOfKey("dataType.properties","BLK_LEGAL_PERSON.HYFL."+key[i]),count);
        }
        //排序
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(
                map.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return  o2.getValue()-o1.getValue();
            }
        });
        return infoIds;
    }

    public List<NameValueModel> rightThree(){
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        List<COMMUNITY_CODE> streetCode_list = cOMMUNITY_CODEMapper.getStreet_CODE();
        for (COMMUNITY_CODE cOMMUNITY_CODE: streetCode_list) {
            P2P_BUSINESS_ADDR p2P_BUSINESS_ADDR = new P2P_BUSINESS_ADDR();
            p2P_BUSINESS_ADDR.setSTREET_CODE(cOMMUNITY_CODE.getSTREET_CODE());
            int num = p2P_BUSINESS_ADDRMapper.selectCount(p2P_BUSINESS_ADDR);
            nameValueModel_list.add(NameValueModel.builder().name(cOMMUNITY_CODE.getSTREET_NAME()).value(String.valueOf(num)).build());
        }
        return nameValueModel_list;
    }

    public List<NameValueModel> rightFour(){
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        String RISK_TYPE[] ={"出险","未出险","停业","立案","非P2P平台"};
        for (int i = 0;i<RISK_TYPE.length;i++) {
            P2P_BUSINESS_GLOBAL p2P_BUSINESS_GLOBAL = new P2P_BUSINESS_GLOBAL();
            p2P_BUSINESS_GLOBAL.setRISK_TYPE(RISK_TYPE[i]);
            int count = p2P_BUSINESS_GLOBALMapper.selectCount(p2P_BUSINESS_GLOBAL);
            nameValueModel_list.add(NameValueModel.builder().name(RISK_TYPE[i]).value(String.valueOf(count)).build());
        }
        return nameValueModel_list;
    }

    public List<P2P_BUSINESS_GLOBAL> rightFive(){
        List<P2P_BUSINESS_GLOBAL> p2P_BUSINESS_GLOBAL_list_return = new ArrayList<P2P_BUSINESS_GLOBAL>();
        List<P2P_BUSINESS_GLOBAL> p2P_BUSINESS_GLOBAL_list_res = new ArrayList<P2P_BUSINESS_GLOBAL>();
        List<P2P_BUSINESS_GLOBAL> p2P_BUSINESS_GLOBAL_list = p2P_BUSINESS_GLOBALMapper.getSmokeIndex();
        p2P_BUSINESS_GLOBAL_list.forEach(p2P_BUSINESS_GLOBAL->{
            if(p2P_BUSINESS_GLOBAL.getSMOKE_INDEX()!=null){
                p2P_BUSINESS_GLOBAL_list_res.add(p2P_BUSINESS_GLOBAL);
            }
        });
        for (int i=0;i<10;i++){
           p2P_BUSINESS_GLOBAL_list_return.add(p2P_BUSINESS_GLOBAL_list_res.get(i));
        }
        return p2P_BUSINESS_GLOBAL_list_return;
    }

    public List<P2P_BUSINESS_GLOBAL> rightSix(){
        List<P2P_BUSINESS_GLOBAL> p2P_BUSINESS_GLOBAL_list_return = new ArrayList<P2P_BUSINESS_GLOBAL>();
        List<P2P_BUSINESS_GLOBAL> p2P_BUSINESS_GLOBAL_list_res = new ArrayList<P2P_BUSINESS_GLOBAL>();
        List<P2P_BUSINESS_GLOBAL> p2P_BUSINESS_GLOBAL_list = p2P_BUSINESS_GLOBALMapper.getRepayment();
        p2P_BUSINESS_GLOBAL_list.forEach(p2P_BUSINESS_GLOBAL_return->{
            if(p2P_BUSINESS_GLOBAL_return.getTOTAL_COMPENSATION()!=null){
                p2P_BUSINESS_GLOBAL_list_res.add(p2P_BUSINESS_GLOBAL_return);
            }
        });
        for (int i=0;i<10;i++){
            p2P_BUSINESS_GLOBAL_list_return.add(p2P_BUSINESS_GLOBAL_list_res.get(i));
        }
        return p2P_BUSINESS_GLOBAL_list_return;
    }

    public HeatMap LegalPersonThermalPower(String streetName){
        HeatMap heatMap = new HeatMap();
        List<MapLegalPerson> mapLegalPerson_list = new ArrayList<MapLegalPerson>();
        BLK_LEGAL_PERSON bLK_LEGAL_PERSON = new BLK_LEGAL_PERSON();
        Map<String,Integer> map_hight_vaule = new HashMap<>();   //得出各街道值
        int num;
        if("".equals(streetName)||streetName==null) {
            List<COMMUNITY_CODE> Street_list = streetTool.getStreet_CODE();
            for (COMMUNITY_CODE cOMMUNITY_CODE:Street_list){
                bLK_LEGAL_PERSON.setSTREET_CODE(cOMMUNITY_CODE.getSTREET_CODE());
                num = bLK_LEGAL_PERSONMapper.getCount(bLK_LEGAL_PERSON);
                map_hight_vaule.put(cOMMUNITY_CODE.getSTREET_CODE(),num);
            }
            mapLegalPerson_list = urbanMapReturnUtil.loadMapModelOfStreet(Street_list,map_hight_vaule);
        }else{
            List<COMMUNITY_CODE> Street_community_list = streetTool.getCommunity_CODE(streetName);
            for (COMMUNITY_CODE cOMMUNITY_CODE:Street_community_list){
                bLK_LEGAL_PERSON.setCOMMUNITY_CODE(cOMMUNITY_CODE.getCOMMUNITY_CODE());
                num = bLK_LEGAL_PERSONMapper.getCount(bLK_LEGAL_PERSON);
                map_hight_vaule.put(cOMMUNITY_CODE.getCOMMUNITY_CODE(),num);
            }
            mapLegalPerson_list = urbanMapReturnUtil.loadMapModelOfCommunity(Street_community_list,map_hight_vaule);
        }
        heatMap.setName("法人数量分布图");
        heatMap.setMax(mapLegalPerson_list.get(0).getMax());
        heatMap.setMin(mapLegalPerson_list.get(0).getMin());
        heatMap.setData(mapLegalPerson_list);
        return heatMap;
    }

    public Map LegalPersonThermalPowerTip(String id){
        String tableName = "";
        int num;
        double density;
        double area;
        double areaUnit;
        Map return_map;

        LinkedHashMap<String,String> map = new LinkedHashMap();
        try {
            BLK_LEGAL_PERSON bLK_LEGAL_PERSON = new BLK_LEGAL_PERSON();
            COMMUNITY_CODE cOMMUNITY_CODE_return;
            COMMUNITY_CODE cOMMUNITY_CODE = new COMMUNITY_CODE();
            if(id.length()==9) {
                cOMMUNITY_CODE.setSTREET_CODE(id);
                cOMMUNITY_CODE_return = streetTool.getCOMMUNITY_CODE(cOMMUNITY_CODE).get(0);
                area = streetTool.getStreet_Area(cOMMUNITY_CODE_return.getSTREET_NAME());
                bLK_LEGAL_PERSON.setSTREET_CODE(id);
                tableName = "LegalPerson";
            }else{
                cOMMUNITY_CODE.setCOMMUNITY_CODE(id);
                cOMMUNITY_CODE_return = streetTool.getCOMMUNITY_CODE(cOMMUNITY_CODE).get(0);
                area = streetTool.getCommunity_Area(cOMMUNITY_CODE_return.getCOMMUNITY_CODE());
                bLK_LEGAL_PERSON.setCOMMUNITY_CODE(id);
                tableName = "LegalPersonCommunity";
            }
            areaUnit = area/1000000;
            num = bLK_LEGAL_PERSONMapper.getCount(bLK_LEGAL_PERSON);
            MapDensity mapDensity = new MapDensity();
            map.put("id",id);
            map.put("STREETNAME",cOMMUNITY_CODE_return.getSTREET_NAME());
            map.put("COMMUNITYNAME",cOMMUNITY_CODE_return.getCOMMUNITY_NAME());
            map.put("VAULE",String.valueOf(num)+" 人");
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
