package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartDetailModel;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.ChartFactory;
import com.cetccity.operationcenter.webframework.core.chart.factory.CetcFactoryProducer;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.*;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.trigger.service.scheduler.jobs.task.LabourPool;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.urbansign.api.model.HeatMap;
import com.cetccity.operationcenter.webframework.urbansign.api.model.LevelColor;
import com.cetccity.operationcenter.webframework.urbansign.api.model.MapDensity;
import com.cetccity.operationcenter.webframework.urbansign.api.model.NameValueDataModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.Tbl_pojo_futianApi;
import com.cetccity.operationcenter.webframework.urbansign.dao.*;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.BlkPopulation;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.COMMUNITY_CODE;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.TBL_MXSYS_FUTIAN;
import com.cetccity.operationcenter.webframework.urbansign.service.UrbanPopulationBasicService;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.urbansign.tools.RightThirteenDrillDownUtil;
import com.cetccity.operationcenter.webframework.urbansign.tools.StreetTool;
import com.cetccity.operationcenter.webframework.urbansign.tools.UrbanMapReturnUtil;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
@Slf4j
public class UrbanPopulationBasicServiceImpl implements UrbanPopulationBasicService {

    @Autowired
    private BlkPopulationMapper blkPopulationMapper;

    @Autowired
    private TblMxsysFutianMapper tblMxsysFutianMapper;

    @Autowired
    private CommunityInfoService communityInfoService;

    @Autowired
    XXZX_POPULATION_SUMMARYMapper xXZX_POPULATION_SUMMARYMapper;

    @Autowired
    StreetTool streetTool;

    @Autowired
    UrbanMapReturnUtil urbanMapReturnUtil;

    @Autowired
    LabourPool labourPool;

    @Autowired
    RightThirteenMapper rightThirteenMapper;

    @Autowired
    RightThirteenDrillDownUtil rightThirteenDrillDownUtil;

    public static final String[] ageName = {"0-3岁","3-6岁","6-14岁","14-28岁","28-35岁","35-45岁","45-60岁","60-70岁","70岁以上"};

    public List<NameValueUnitModel> getLeftOne(String street, String community){
        String street_code = null;
        String community_code =null;
        if(StringUtils.isNotEmpty(street))
            street_code = LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0];
        if(StringUtils.isNotEmpty(community))
            community_code = LoadMyUtil.getPropertiesVauleOfKey("street.properties", community);
        long registerPopulationCount;    //户籍人口
            BlkPopulation bLK_POPULATION_register = new BlkPopulation();
            bLK_POPULATION_register.setJDDM(street_code);
            bLK_POPULATION_register.setSQDM(community_code);
            bLK_POPULATION_register.setIS_CITIZEN("1");//深圳福田户籍人口
            registerPopulationCount = blkPopulationMapper.getPopulationCount(bLK_POPULATION_register);

        long floatingPopulationCount; //流动人口
            BlkPopulation bLK_POPULATION_floating = new BlkPopulation();
            bLK_POPULATION_floating.setJDDM(street_code);
            bLK_POPULATION_floating.setSQDM(community_code);
            bLK_POPULATION_floating.setIS_CITIZEN("0");//深圳福田户籍人口
            floatingPopulationCount = blkPopulationMapper.getPopulationCount(bLK_POPULATION_floating);
        List<NameValueUnitModel> nameValueUnitModel_list = new ArrayList<NameValueUnitModel>();
        String topName[] = {"户籍人口","流动人口"};
        String topVaule[] = {String.valueOf(registerPopulationCount),String.valueOf(floatingPopulationCount)};
        for (int i = 0;i<topName.length;i++) {
            nameValueUnitModel_list.add(NameValueUnitModel.builder().name(topName[i]).value(topVaule[i]).unit("人").build());
        }
        return nameValueUnitModel_list;
    }

    public XYAxisData getLeftTwo(String street){
        XYAxisData xyAxisData = new XYAxisData();
        List<NameTypeDataModel> nameDataModelList = new ArrayList<NameTypeDataModel>();
        NameTypeDataModel nameDataModelRegisterPopulation = new NameTypeDataModel();
        NameTypeDataModel nameDataModelFloatingPopulation = new NameTypeDataModel();
        List<NameValueModel> nameValueModelRegisterPopulationList = new ArrayList<NameValueModel>();
        List<NameValueModel> nameValueModelFloatingPopulationList = new ArrayList<NameValueModel>();
        String unit;
        if(StringUtils.isEmpty(street)){
            unit = "街道";
            String streetName[] = LoadMyUtil.getPropertiesVauleOfKey("street.properties", "street").split(",");
            for (int i=0;i<streetName.length;i++) {
                String streetCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties", streetName[i]).split(",")[0];
                BlkPopulation blkPopulation = new BlkPopulation();
                blkPopulation.setJDDM(streetCode);

                long registerPopulationCount;    //户籍人口
                blkPopulation.setIS_CITIZEN("1");//深圳福田户籍人口
                registerPopulationCount = blkPopulationMapper.getPopulationCount(blkPopulation);
                nameValueModelRegisterPopulationList.add(NameValueModel.builder().name(streetName[i]).value(String.valueOf(registerPopulationCount)).build());
                long floatingPopulationCount; //流动人口
                blkPopulation.setIS_CITIZEN("0");
                floatingPopulationCount = blkPopulationMapper.getPopulationCount(blkPopulation);
                nameValueModelFloatingPopulationList.add(NameValueModel.builder().name(streetName[i]).value(String.valueOf(floatingPopulationCount)).build());
            }
        }else{
            unit = "社区";
            String communityName[] = LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",");
            for (int i=1;i<communityName.length;i++) {
                String communityCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties", communityName[i]);
                BlkPopulation blkPopulation = new BlkPopulation();
                blkPopulation.setSQDM(communityCode);

                long registerPopulationCount;    //户籍人口
                blkPopulation.setIS_CITIZEN("1");//深圳福田户籍人口
                registerPopulationCount = blkPopulationMapper.getPopulationCount(blkPopulation);
                nameValueModelRegisterPopulationList.add(NameValueModel.builder().name(communityName[i]).value(String.valueOf(registerPopulationCount)).build());
                long floatingPopulationCount; //流动人口
                blkPopulation.setIS_CITIZEN("0");
                floatingPopulationCount = blkPopulationMapper.getPopulationCount(blkPopulation);
                nameValueModelFloatingPopulationList.add(NameValueModel.builder().name(communityName[i]).value(String.valueOf(floatingPopulationCount)).build());
            }
        }
        Map<String,String> mapXAxis = new HashMap();
        mapXAxis.put("name",unit);
        Map<String,String> mapYAxis = new HashMap();
        mapYAxis.put("name","人");
        xyAxisData.setXAxis(mapXAxis);
        xyAxisData.setYAxis(mapYAxis);

        nameDataModelRegisterPopulation.setName("深圳福田户籍人口");
        nameDataModelRegisterPopulation.setType("bar");
        nameDataModelRegisterPopulation.setSmooth("");
        nameDataModelRegisterPopulation.setData(nameValueModelRegisterPopulationList);
        nameDataModelList.add(nameDataModelRegisterPopulation);
        nameDataModelFloatingPopulation.setName("流动人口");
        nameDataModelFloatingPopulation.setType("bar");
        nameDataModelFloatingPopulation.setSmooth("");
        nameDataModelFloatingPopulation.setData(nameValueModelFloatingPopulationList);
        nameDataModelList.add(nameDataModelFloatingPopulation);
        xyAxisData.setData(nameDataModelList);
        return xyAxisData;
    }

    public HttpResponseModel<ChartDetailModel> getRightOne(String street){
        ChartFactory chartFactory = new ChartFactory() {
            @Override
            public List<HashMap> queryData() {
                List<HashMap> list = xXZX_POPULATION_SUMMARYMapper.getUrbanSignRightOne();
                //list反向
                Collections.reverse(list);
                return list;
            }

            @Override
            public List<String> initX() {
                List<String> x= new ArrayList<>();
                queryData().stream().forEach(u->x.add((String) u.get("X_TIME")));
                return x;
            }

            @Override
            public List<String> initY() {
                List<String> y = new ArrayList<String>();
                for (Object key: input.get(0).keySet()){
                    if ("X_TIME".equals(String.valueOf(key))) continue;
                    y.add(String.valueOf(key));
                }
                return y;
            }

            @Override
            public void match(HashMap row) {
                String month = (String) row.get("X_TIME");
                for(Object key: row.keySet()){
                    if ("X_TIME".equals(String.valueOf(key))) continue;
                    BigDecimal decimal = (BigDecimal) row.get(key);
                    dataMap.get(month).put(String.valueOf(key), decimal.intValue());
                }
            }
        };
        Map<String,String> map = new HashMap();
        map.put("type","bar");map.put("stack","人口");
        ChartDetailModel model = new ChartDetailModel();

        model.setChart(chartFactory.build(map));
        return new HttpResponseModel<ChartDetailModel>(SysCode.SYS_SUCCESS_CODE, SysCode.SYS_SUCCESS_MESSAGE, model);
    }

    public NameDataModel getRightTwo(String street, String community){
        NameDataModel nameDataModel = new NameDataModel();
        Integer num;
        BlkPopulation blkPopulation = new BlkPopulation();
        blkPopulation.setIS_CITIZEN("0");//流动人口
        if("".equals(street)||street==null) {

        }else if("".equals(community)||community==null){
            String streetCode = LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0];
            blkPopulation.setJDDM(streetCode);
        }else{
            String community_code = LoadMyUtil.getPropertiesVauleOfKey("street.properties", community);
            blkPopulation.setSQDM(community_code);
        }
        String province[] = LoadMyUtil.getPropertiesVauleOfKey("street.properties","province").split(",");
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        LinkedHashMap<String, Integer> map = new LinkedHashMap();
        for (int i =0;i<province.length;i++) {
            String identity = LoadMyUtil.getPropertiesVauleOfKey("street.properties",province[i]);
            blkPopulation.setSFZH(identity);
            num = blkPopulationMapper.getPopulationCount(blkPopulation);
            map.put(province[i],num);
        }
        //降序排序
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        int i = 0;
        for (Iterator it = list.iterator(); it.hasNext();) {
            i++;
            if(i>10) break;
            Map.Entry entry = (Map.Entry)it.next();
            nameValueModel_list.add(NameValueModel.builder().name(String.valueOf(entry.getKey())).value(String.valueOf(entry.getValue())).build());
        }
        nameDataModel.setName("外来人口分布Top-10");
        nameDataModel.setData(nameValueModel_list);
        return nameDataModel;
    }

    public NameDataModel getRightThree(String street){
        NameDataModel nameDataModel_return = new NameDataModel();
        Integer year = Integer.valueOf(LoadMyUtil.getMyTime("YEAR",0));
        Integer yearName[] = {year,year-3,year-6,year-14,year-28,year-35,year-45,year-60,year-70,year-200};
        String sexName[] = {"男","女","全部"};
        String sex[] = {"1","2",null};
        long num;
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        for(int i=0;i<sexName.length;i++) {
            NameDataModel nameDataModel = new NameDataModel();
            List<TimeValueModel> timeValueModel_list = new ArrayList<TimeValueModel>();
            for (int j = 0; j < ageName.length; j++) {
                TimeValueModel timeValueModel = new TimeValueModel();
                BlkPopulation bLK_POPULATION = new BlkPopulation();
                bLK_POPULATION.setXB(sex[i]);
                bLK_POPULATION.setStartTime(String.valueOf(yearName[j+1]));
                bLK_POPULATION.setEndTime(String.valueOf(yearName[j]));
                num = blkPopulationMapper.getPopulationCount(bLK_POPULATION);
                timeValueModel.setTime(ageName[j]);
                timeValueModel.setValue(String.valueOf(num));
                timeValueModel_list.add(timeValueModel);
            }
            nameDataModel.setName(sexName[i]);
            nameDataModel.setData(timeValueModel_list);
            nameDataModel_list.add(nameDataModel);
        }
        nameDataModel_return.setName("年龄结构金字塔");
        nameDataModel_return.setData(nameDataModel_list);
        return nameDataModel_return;
    }

    public NameDataModel getRightFour(String street){
        NameDataModel nameDataModel_return = new NameDataModel();
        TBL_MXSYS_FUTIAN tBL_MXSYS_FUTIAN = new TBL_MXSYS_FUTIAN();
        List<TimeValueModel> timeValueModel_list = new ArrayList<TimeValueModel>();
        List<TimeValueModel> timeValueModel_rate_list = new ArrayList<TimeValueModel>();
        long num;
        float num_front;
        float rate;
        for(int i =5;i>=0;i--) {
            TimeValueModel timeValueModel = new TimeValueModel();
            TimeValueModel timeValueModel_rate = new TimeValueModel();
            String year_front = LoadMyUtil.getMyTime("YEAR",-i-1);
            String year = LoadMyUtil.getMyTime("YEAR",-i);
            tBL_MXSYS_FUTIAN.setUPDATETIME(year);
            num = tblMxsysFutianMapper.getTBL_MXSYS_FUTIANCount(tBL_MXSYS_FUTIAN);
            tBL_MXSYS_FUTIAN.setUPDATETIME(year_front);
            num_front = tblMxsysFutianMapper.getTBL_MXSYS_FUTIANCount(tBL_MXSYS_FUTIAN);
            if (num_front==0) {
                rate =0;
            }else{
                rate =  num/(num-num_front);
            }
            timeValueModel.setTime(year);
            timeValueModel.setValue(String.valueOf(num));
            timeValueModel_rate.setTime(year);
            timeValueModel_rate.setValue(String.valueOf(rate));
            timeValueModel_list.add(timeValueModel);
            timeValueModel_rate_list.add(timeValueModel_rate);
        }
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        NameDataModel nameDataModel_num = new NameDataModel();
        NameDataModel nameDataModel_rate = new NameDataModel();
        nameDataModel_num.setName("近五年人才引进情况");
        nameDataModel_num.setData(timeValueModel_list);
        nameDataModel_list.add(nameDataModel_num);
        nameDataModel_rate.setName("近五年人才引进增长率");
        nameDataModel_rate.setData(timeValueModel_rate_list);
        nameDataModel_list.add(nameDataModel_rate);
        nameDataModel_return.setName("福田人才引进");
        nameDataModel_return.setData(nameDataModel_list);
        return nameDataModel_return;
    }

    public NameDataModel rightFive(String street,String community){
        List<NameValueTypeModel<Integer>> nameValueTypeModels = tblMxsysFutianMapper.countTblMxsysFutianByProvince();
        // 数据库查询已经拍好序
        List<Map> topTen = nameValueTypeModels
                .stream()
                .limit(10)
                .peek(nameValueTypeModel -> {
                    String province = LoadMyUtil.getPropertiesVauleOfKey("street.properties", nameValueTypeModel.getName());
                    nameValueTypeModel.setName(province);
                })
                .map(nameValueTypeModel -> {
                    HashMap map = new HashMap();
                    map.put(nameValueTypeModel.getName(), nameValueTypeModel.getValue());
                    return map;
                })
                .collect(Collectors.toList());
        Map<String, Integer> otherMap = nameValueTypeModels.stream()
                .skip(10)
                .collect(Collectors.toMap(obj -> "其他", NameValueTypeModel::getValue, (o1, o2) -> o1 + o2));
        topTen.add(otherMap);
        return new NameDataModel("人才补贴籍贯地", topTen);
    }

    public NameDataModel rightSix(String street){
        NameDataModel nameDataModel =new NameDataModel();
        List<TimeValueModel> timeValueModel_list = new ArrayList<TimeValueModel>();
        String yearMonthStr = YearMonth.now().minusMonths(11).format(DateTimeFormatter.ofPattern("yyyy-MM"));
        // 查询各月数据
        List<TimeValueModel> timeValueModels = tblMxsysFutianMapper.countMoneyByMonth(yearMonthStr);
        Map<String, String> timeValueMap = timeValueModels.stream()
                .collect(Collectors.toMap(TimeValueModel::getTime, TimeValueModel::getValue));
        List<TimeValueModel> resData = LongStream.range(0, 12)
                .mapToObj(index -> YearMonth.now().minusMonths(index))
                .sorted()
                .map(yearMonth -> {
                    String ym = yearMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));
                    String value = timeValueMap.get(ym);
                    if (Objects.isNull(value)) {
                        value = "0";
                    }
                    return new TimeValueModel(ym, value);
                }).collect(Collectors.toList());
        return new NameDataModel("人才补贴发放金额", resData);
    }

    public LinkedHashMap getRightEight(String street,String community){
        TBL_MXSYS_FUTIAN tBL_MXSYS_FUTIAN_edu = new TBL_MXSYS_FUTIAN();
        tBL_MXSYS_FUTIAN_edu.setEDUCATION("18");//硕士
        long num_shuoshi= tblMxsysFutianMapper.getTBL_MXSYS_FUTIANCount(tBL_MXSYS_FUTIAN_edu);
        tBL_MXSYS_FUTIAN_edu.setEDUCATION("19");//博士
        long num_boshi = tblMxsysFutianMapper.getTBL_MXSYS_FUTIANCount(tBL_MXSYS_FUTIAN_edu);
        TBL_MXSYS_FUTIAN tBL_MXSYS_FUTIAN_cate = new TBL_MXSYS_FUTIAN();
        tBL_MXSYS_FUTIAN_cate.setCATEGORY("0");//应届生
        long num_yinjie = tblMxsysFutianMapper.getTBL_MXSYS_FUTIANCount(tBL_MXSYS_FUTIAN_cate);
        tBL_MXSYS_FUTIAN_cate.setCATEGORY("1");//留学生
        long num_liuxue = tblMxsysFutianMapper.getTBL_MXSYS_FUTIANCount(tBL_MXSYS_FUTIAN_cate);
        LinkedHashMap map = new LinkedHashMap();
        map.put("硕士",num_shuoshi);
        map.put("博士",num_boshi);
        map.put("应届生",num_yinjie);
        map.put("留学生",num_liuxue);
        return map;
    }

    public NameDataModel getRightTen(String streetName) {
        return new NameDataModel("老年人口数量", countPopulation(streetName, 60));
    }

    private List<NameValueTypeModel<Integer>> countPopulation(String streetName, long minusYears) {
        LocalDateTime maxBirth = LocalDateTime.now().minusYears(minusYears);
        List<NameValueTypeModel<Integer>> nameValueDataModels;
        if (StringUtils.isBlank(streetName)) {
            return blkPopulationMapper.countPopulation(maxBirth);
        } else {
            String streetCode = communityInfoService.streetCodeByName(streetName);
            return blkPopulationMapper.countPopulationByCommunity(maxBirth, streetCode);
        }
    }

    public NameDataModel getRightEleven(String street,String community){

        return new NameDataModel("儿童数量", countPopulation(street, 60));
    }

    //落图
    public HeatMap populationDensity(String streetName, String tag){
        HeatMap heatMap = new HeatMap();
        String isCitizen = "";//0-人口密度、1-户籍人口密度、2-流动人口密度
        if("1".equals(tag)){
            isCitizen = "1";//深圳福田户籍人口
        }else if("2".equals(tag)){
            isCitizen = "0";
        }
        List<NameValueDataModel<String>> nameValueDataModels;
        if(StringUtils.isEmpty(streetName)) {
            nameValueDataModels = blkPopulationMapper.countPopulationForDestiny(isCitizen);
        }else{
            String streetCode = communityInfoService.streetCodeByName(streetName);
            nameValueDataModels = blkPopulationMapper.countPopulationForDestinyByCommunity(isCitizen, streetCode);
        }
        List<MapDensity> mapDensities = nameValueDataModels.stream()
                .map(nameValueDataModel -> {
                    Double area ;
                    if(StringUtils.isEmpty(streetName)) {
                        area = streetTool.getStreet_Area(nameValueDataModel.getName());
                    } else {
                        area = streetTool.getCommunity_Area(nameValueDataModel.getData());
                    }
                    MapDensity mapDensity = new MapDensity();
                    double density = Double.valueOf(nameValueDataModel.getValue()) / area;
                    mapDensity.setStreetName(nameValueDataModel.getName());
                    mapDensity.setId(nameValueDataModel.getData());
                    mapDensity.setValue(nameValueDataModel.getValue());
                    mapDensity.setArea(area);
                    mapDensity.setDensity(density);
                    return mapDensity;
                }).collect(Collectors.toList());

        Map<String, Double> mapHightValue = mapDensities.stream()
                .collect(Collectors.toMap(MapDensity::getId, MapDensity::getDensity));
        mapDensities.stream().forEach(mapDensity -> {
            LevelColor levelColor = streetTool.getPopulationBasicRGB(mapHightValue, mapDensity.getDensity());
            mapDensity.setLevel(levelColor.getLevel());
            mapDensity.setColor(levelColor.getColor());
            mapDensity.setMax(levelColor.getMax());
            mapDensity.setMin(levelColor.getMin());
        });
        heatMap.setName("人口密度分布图");
        heatMap.setMax(mapDensities.get(0).getMax());
        heatMap.setMin(mapDensities.get(0).getMin());
        heatMap.setData(mapDensities);
        return heatMap;
    }

    //弹框
    public Map populationDensityTip(String id,String tag){
        String tableName;
        int num;
        double density;
        double area;
        double areaUnit;
        Map return_map;

        LinkedHashMap<String,String> map = new LinkedHashMap();
        try {
            BlkPopulation bLK_POPULATION = new BlkPopulation();//0-人口密度、1-户籍人口密度、2-流动人口密度
            COMMUNITY_CODE cOMMUNITY_CODE = new COMMUNITY_CODE();
            COMMUNITY_CODE cOMMUNITY_CODE_return;
            if(id.length()==9) {
                cOMMUNITY_CODE.setSTREET_CODE(id);
                bLK_POPULATION.setJDDM(id);
                tableName = "PopulationDensity";
                cOMMUNITY_CODE_return = streetTool.getCOMMUNITY_CODE(cOMMUNITY_CODE).get(0);
                area = streetTool.getStreet_Area(cOMMUNITY_CODE_return.getSTREET_NAME());
            }else{
                cOMMUNITY_CODE.setCOMMUNITY_CODE(id);
                bLK_POPULATION.setSQDM(id);
                tableName = "PopulationDensityCommunity";
                cOMMUNITY_CODE_return = streetTool.getCOMMUNITY_CODE(cOMMUNITY_CODE).get(0);
                area = streetTool.getCommunity_Area(cOMMUNITY_CODE_return.getCOMMUNITY_CODE());
            }
            if("1".equals(tag)) {
                bLK_POPULATION.setIS_CITIZEN("1");//深圳福田户籍人口
            }else if("2".equals(tag)){
                bLK_POPULATION.setIS_CITIZEN("0");
            }
            num = blkPopulationMapper.getPopulationCount(bLK_POPULATION);
            areaUnit = area/1000000;
            density = (Double.valueOf(num)/10000)/(area/1000000);
            MapDensity mapDensity = new MapDensity();
            map.put("id",id);
            map.put("STREETNAME",cOMMUNITY_CODE_return.getSTREET_NAME());
            map.put("COMMUNITYNAME",cOMMUNITY_CODE_return.getCOMMUNITY_NAME());
            map.put("VAULE",String.valueOf(num)+" 人");
            map.put("AREA",String.valueOf(LoadMyUtil.retainToPoint(areaUnit,3))+" 平方公里");
            map.put("DENSITY",String.valueOf(LoadMyUtil.retainToPoint(density,3))+" 万人/平方公里");

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

    public List<Tbl_pojo_futianApi> labourPool(){
        if(labourPool.list==null){
            labourPool.getLabourPool();
        }
        List<Tbl_pojo_futianApi> tbl_pojo_futian_list = labourPool.list;
        return tbl_pojo_futian_list;
    }

    public HttpResponseModel<ChartDetailModel> rightThirteen(String street, String type){
        List<HashMap> data = null;
        Map map = new HashMap();
        map.put("streetCode",StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null);
        switch (type){
            case "1" : data = rightThirteenMapper.getRightThirteenOfOne(map); break;
            case "2" : data = rightThirteenMapper.getRightThirteenOfTwo(map); break;
            case "3" : data = rightThirteenMapper.getRightThirteenOfThree(map); break;
            case "4" : data = rightThirteenMapper.getRightThirteenOfFour(map); break;
            case "5" : data = rightThirteenMapper.getRightThirteenOfFive(map); break;
        }
        Map<String,String> map2 = new HashMap();
        map2.put("type","bar");
        return CetcFactoryProducer.init(data,"NAME_X",map2,false);
    }

    public HttpResponseModel<ChartDetailModel> rightThirteenDrillDown(String street, String name){
        List<HashMap> data = null;
        String streetCode = StringUtils.isNotEmpty(street) ? LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0] : null;
        switch (name){
            case "困难群众人员" : if(StringUtils.isEmpty(street)){
                data = rightThirteenDrillDownUtil.getBarNoStreet("BLK_DIFFICULT_PO",name);break;
            }else {
                data = rightThirteenDrillDownUtil.getBarHasStreet("BLK_DIFFICULT_PO",streetCode,name);break;
            }
            //case "2" : data = rightThirteenDrillDownMapper.getRightThirteenDrillDown(map); break;
            //case "3" : data = rightThirteenDrillDownMapper.getRightThirteenDrillDown(map); break;
            //case "4" : data = rightThirteenDrillDownMapper.getRightThirteenDrillDown(map); break;
            //case "5" : data = rightThirteenDrillDownMapper.getRightThirteenDrillDown(map); break;
        }
        Map<String,String> map2 = new HashMap();
        map2.put("type","bar");
        return CetcFactoryProducer.init(data,"NAME_X",map2,true);
    }
}
