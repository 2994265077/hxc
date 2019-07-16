package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.core.tools.XsteamUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.LoadAttributeMapTwo;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.MapConfig;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TopGroup;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TopGroupTwo;
import com.cetccity.operationcenter.webframework.urbansign.api.model.HeatMap;
import com.cetccity.operationcenter.webframework.urbansign.api.model.LoadMap;
import com.cetccity.operationcenter.webframework.urbansign.api.model.MapLegalPerson;
import com.cetccity.operationcenter.webframework.urbansign.dao.UrbanComponentMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.COMMUNITY_CODE;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanComponentService;
import com.cetccity.operationcenter.webframework.urbansign.tools.StreetTool;
import com.cetccity.operationcenter.webframework.urbansign.tools.UrbanMapReturnUtil;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.web.util.Constant;
import com.cetccity.operationcenter.webframework.web.util.apollo.ApolloPropertiesLoadUtils;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UrbanComponentServiceImpl implements UrbanComponentService {

    @Autowired
    LoadMap loadMap;

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    UrbanMapReturnUtil urbanMapReturnUtil;

    @Autowired
    StreetTool streetTool;

    @Autowired
    private UrbanComponentMapper urbanComponentMapper;

    public NameValueModel leftOne(String street) {
        int count = 0;
        String sql;
        List<String> table_list = new ArrayList<>();
        Map<String ,List<String>> map_type = getComponentTable();
        for(Map.Entry entry:map_type.entrySet()){
            table_list.addAll((List<String>)entry.getValue());
        }
        if (StringUtils.isEmpty(street)) {
            for (String table : table_list) {
                sql = "select count(*) from " + table;
                count += oracleOperateService.queryCount(sql);
            }
        } else {
            for (String table : table_list) {
                sql = "select count(*) from " + table + " where STREET_NAME = '" + street + "'";
                count += oracleOperateService.queryCount(sql);
            }
        }
        return NameValueModel.builder().name("部件数量").value(String.valueOf(count)).build();
    }

    public NameValueModel leftTwo() {
        long count = loadMap.keySet()
                .stream()
                .filter(Objects::nonNull)
                .filter(key -> key.toString().startsWith("BJ"))
                .count();
        return NameValueModel.builder().name("部件种类").value(String.valueOf(count)).build();
    }

    public List<NameValueModel> leftThree(String street) {
        List<String> collect = getComponentTable().entrySet()
                .stream()
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.toList());
        if (StringUtils.isBlank(street)) {
            return urbanComponentMapper.countStreetDeptComponent(collect);
        } else {
            List<NameValueModel> nameValueModels = urbanComponentMapper.countCommunityDeptComponent(street, collect);
            Map<String, String> codeMap = streetTool.getCommunity_CODE(street)
                    .stream()
                    .collect(Collectors.toMap(COMMUNITY_CODE::getCOMMUNITY_CODE, COMMUNITY_CODE::getCOMMUNITY_NAME, (o1, o2) -> o1));
            nameValueModels.stream().forEach(nameValueModel -> nameValueModel.setName(codeMap.get(nameValueModel.getName())));
            return nameValueModels;
        }
    }
    //各部门部件数量
    public NameDataModel rigthOne(String street){
        Map<String ,List<String>> map_type = getComponentTable();
        List<String> collect = map_type.entrySet()
                .stream()
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.toList());
        List<NameValueModel> nameValueModels = urbanComponentMapper.countDeptComponent(null, collect);
        return new NameDataModel("各部门部件数量", nameValueModels);
    }
    //设备完好度--七大类
    public List<NameValueModel> rigthTwo(String street)throws IOException{
        List<NameValueModel> nameValueModel_list = new ArrayList<>();
        Map<String ,List<String>> map_type = getComponentTable();
        String sql;
        if("".equals(street)||street==null){
            for (Map.Entry entry:map_type.entrySet()) {
                int num = 0;
                List<String> tableName = (List<String>)entry.getValue();
                for (String table: tableName) {
                    sql = "select count(*) from "+table+" where OBJSTATE = '完好'";
                    num += oracleOperateService.queryCount(sql);
                }
                nameValueModel_list.add(NameValueModel.builder().name((String) entry.getKey()).value(String.valueOf(num)).build());
            }
        }else{
            for (Map.Entry entry:map_type.entrySet()) {
                int num = 0;
                List<String> tableName = (List<String>)entry.getValue();
                for (String table: tableName) {
                    sql = "select count(*) from "+table+" where OBJSTATE = '完好' and STREET_NAME = '"+street+"'";
                    num += oracleOperateService.queryCount(sql);
                }
                nameValueModel_list.add(NameValueModel.builder().name((String) entry.getKey()).value(String.valueOf(num)).build());
            }
        }
        return nameValueModel_list;
    }
    //城市部件Top5
    public Map rigthThree(String street){
        List<String> tableName = new ArrayList<>();
        Map<String ,List<String>> map_type = getComponentTable();
        for(Map.Entry entry:map_type.entrySet()){
            tableName.addAll((List<String>)entry.getValue());
        }
        Map map_top = new HashMap();
        LinkedHashMap new_top = new LinkedHashMap();
        if("".equals(street)||street==null) {
            tableName.forEach(table -> {
                String sql = "select count(*) from " + table + "";
                int num = oracleOperateService.queryCount(sql);
                map_top.put(table, num);
            });
            //排序
            List<Map.Entry<String, Integer>> list = new ArrayList(map_top.entrySet());
            Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
            int s = 0;
            for (Map.Entry<String, Integer> map : list) {
                s++;
                new_top.put(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", map.getKey()), map.getValue());
                if (s == 5) {
                    break;
                }
            }
        }else{
            tableName.forEach(table -> {
                String sql = "select count(*) from " + table + " where STREET_NAME = '"+street+"'";
                int num = oracleOperateService.queryCount(sql);
                map_top.put(table, num);
            });
            //排序
            List<Map.Entry<String, Integer>> list = new ArrayList(map_top.entrySet());
            Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
            int s = 0;
            for (Map.Entry<String, Integer> map : list) {
                s++;
                new_top.put(LoadMyUtil.getPropertiesVauleOfKey("loadmap.properties", map.getKey()), map.getValue());
                if (s == 5) {
                    break;
                }
            }
        }
        return new_top;
    }

    public HeatMap componentNumGradientLoadMap(String streetName) {
        List<MapLegalPerson> mapLegalPerson_list;
        List<String> tableNames = getComponentTable().entrySet()
                .stream()
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.toList());
        if (StringUtils.isBlank(streetName)) {
            List<COMMUNITY_CODE> Street_list = streetTool.getStreet_CODE();
            List<NameValueTypeModel<Integer>> nameValueModels = urbanComponentMapper.countStreetCodeDeptComponent(tableNames);
            Map<String, Integer> codeCountMap = nameValueModels.stream()
                    .collect(Collectors.toMap(NameValueTypeModel::getName, NameValueTypeModel::getValue));
            mapLegalPerson_list = urbanMapReturnUtil.loadMapModelOfStreet(Street_list, codeCountMap);
            return new HeatMap("城市部件数量分布图", mapLegalPerson_list.get(0).getMax(), mapLegalPerson_list.get(0).getMin(), mapLegalPerson_list);
        } else {
            List<COMMUNITY_CODE> Street_community_list = streetTool.getCommunity_CODE(streetName);
            List<NameValueModel> nameValueModels = urbanComponentMapper.countCommunityDeptComponent(streetName, tableNames);
            Map<String, Integer> codeCountMap = nameValueModels.stream()
                    .collect(Collectors.toMap(NameValueModel::getName, nameValueModel -> Integer.valueOf(nameValueModel.getValue())));
            mapLegalPerson_list = urbanMapReturnUtil.loadMapModelOfCommunity(Street_community_list, codeCountMap);
            return new HeatMap("城市部件数量分布图", mapLegalPerson_list.get(0).getMax(), mapLegalPerson_list.get(0).getMin(), mapLegalPerson_list);
        }
    }

    public Map componentNumGradientTip(String id) {
        String tableName;
        int num;
        int count = 0;
        double area;
        double areaUnit;
        String sql;
        Map return_map;

        LinkedHashMap<String, String> map = new LinkedHashMap();
        Map<String ,List<String>> map_type = getComponentTable();
        List<String> tableName_list = new ArrayList<>();
        for(Map.Entry entry:map_type.entrySet()){
            tableName_list.addAll((List<String>)entry.getValue());
        }
        try {
            COMMUNITY_CODE cOMMUNITY_CODE = new COMMUNITY_CODE();
            COMMUNITY_CODE cOMMUNITY_CODE_return;
            if(id.length()==9) {
                tableName = "BLK_Component";
                cOMMUNITY_CODE.setSTREET_CODE(id);
                cOMMUNITY_CODE_return = streetTool.getCOMMUNITY_CODE(cOMMUNITY_CODE).get(0);
                area = streetTool.getStreet_Area(cOMMUNITY_CODE_return.getSTREET_NAME());
            }else{
                tableName = "BLK_Component_Community";
                cOMMUNITY_CODE.setCOMMUNITY_CODE(id);
                cOMMUNITY_CODE_return = streetTool.getCOMMUNITY_CODE(cOMMUNITY_CODE).get(0);
                area = streetTool.getCommunity_Area(cOMMUNITY_CODE_return.getCOMMUNITY_CODE());
            }

            for (String table : tableName_list) {
                if(id.length()==9) {
                    sql = "select count(*) from " + table + " where STREET_CODE = '" + id + "'";
                }else{
                    sql = "select count(*) from " + table + " where COMMUNITY_CODE = '" + id + "'";
                }
                    num = oracleOperateService.queryCount(sql);
                    count += num;
            }

            areaUnit = area/1000000;
            map.put("id", id);
            map.put("STREETNAME", cOMMUNITY_CODE_return.getSTREET_NAME());
            map.put("COMMUNITYNAME", cOMMUNITY_CODE_return.getCOMMUNITY_NAME());
            map.put("VAULE", String.valueOf(count) + " 个");
            map.put("AREA", String.valueOf(LoadMyUtil.retainToPoint(areaUnit,3)) + " 平方公里");

            InputStream inputStream = ESOperate.class.getResourceAsStream("/tip.properties");
            Properties properties = new Properties();
            properties.load(new InputStreamReader(inputStream, "UTF-8"));

            String tableKey = properties.getProperty(ESOperate.dbName + "." + tableName + "Key");
            String tableValue = properties.getProperty(ESOperate.dbName + "." + tableName + "Value").toUpperCase();
            String HasDetail = "false";
            Boolean hasDetailInfo = Boolean.valueOf(HasDetail);
            String[] key = tableKey.split(","); //注意分隔符是需要转译滴...
            String[] value = tableValue.split(",");

            List result = urbanMapReturnUtil.tipContent(key, value, map);
            return_map = Tooltip.toolTipListToMap(result, hasDetailInfo);
            return_map.put("info_alert", "0");
            return return_map;
        } catch (Exception e) {
        	log.error(e.toString());
            return null;
        }
    }

    //获取runDisplayMapConfig.xml中城市部件并去掉不存在的表
    public Map<String ,List<String>> getComponentTable(){
        // 读取XML文件
        Resource resource = ApolloPropertiesLoadUtils.readApolloToResource(Constant.RUN_DISPLAY_MAP_CONFIG);
        BufferedReader br = null;
        StringBuffer buffer = null;
        try {
            br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
            buffer = new StringBuffer();
            String line;
            while ((line = br.readLine()) !=null) {
                buffer.append(line);
            }
        } catch (IOException e) {
        	log.error(e.toString());
        }
        try {
            br.close();
        } catch (IOException e) {
        	log.error(e.toString());
        }
        // XML转为Java对象
        MapConfig mapConfig =  (MapConfig) XsteamUtil.toBean(MapConfig.class, buffer.toString());
        List<TopGroup> topGroupsList= mapConfig.getTopGroupList();
        List<TopGroupTwo> topGroupTwoList = new ArrayList<>();
        for(TopGroup topGroup:topGroupsList){
            if("城市部件".equals(topGroup.getLabel())){
                topGroupTwoList = topGroup.getTopGroupTwoList();
                break;
            }
        }
        Map<String ,List<String>> map_type = new HashMap();//七大类
        for(TopGroupTwo topGroupTwo:topGroupTwoList){
            List<LoadAttributeMapTwo> loadAttributeMapTwoList = topGroupTwo.getLoadAttributeMapTwoList();
            List<String> tableName = new ArrayList<>();
            for (LoadAttributeMapTwo loadAttributeMapTwo:loadAttributeMapTwoList) {
                if(!oracleOperateService.tableNameIsExist(loadAttributeMapTwo.getTable())){
                    continue;
                }
                tableName.add(loadAttributeMapTwo.getTable());
            }
            map_type.put(topGroupTwo.getLabel(),tableName);
        }
        return map_type;
    }
}
