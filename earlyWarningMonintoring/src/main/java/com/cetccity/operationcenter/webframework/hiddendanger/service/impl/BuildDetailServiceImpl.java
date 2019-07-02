package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.*;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.BuildScoreMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BUILD_SCORE;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.TXfBuilderPfjgMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.service.BuildDetailService;
//import com.cetccity.operationcenter.webframework.hiddendanger.tools.BuildScoreComment;
//import com.cetccity.operationcenter.webframework.hiddendanger.tools.BuildScoreDangerTool;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.map.TipUtil;
import com.cetccity.operationcenter.webframework.web.dao.BLK_LEGAL_PERSONMapper;
import com.cetccity.operationcenter.webframework.web.dao.T_BUILD_CHECKDAY_DANGERMapper;
import com.cetccity.operationcenter.webframework.web.model.build.*;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.BLK_LEGAL_PERSON;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.T_BUILD_CHECKDAY_DANGER;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import com.cetccity.operationcenter.webframework.web.util.DataOfProperityUtil;
import com.cetccity.operationcenter.webframework.web.util.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.jsonwebtoken.lang.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildDetailServiceImpl implements BuildDetailService {

    @Autowired
    TipUtil tipUtil;

    @Autowired
    TXfBuilderPfjgMapper t_XF_BUILDER_PFJGMapper;

    @Autowired
    T_BUILD_CHECKDAY_DANGERMapper t_BUILD_CHECKDAY_DANGERMapper;

    @Autowired
    BLK_LEGAL_PERSONMapper bLK_LEGAL_PERSONMapper;

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    BuildScoreMapper bUILD_SCOREMapper;

    private Environment env;

    public NameDataModel buildBasic(String tableName, String buildid){
        NameDataModel nameDataModel = new NameDataModel();
        DataOfProperityUtil dataOfProperityUtil_xiaoan = new DataOfProperityUtil();
        DataOfProperityUtil dataOfProperityUtil_blk = new DataOfProperityUtil();

        LinkedHashMap<String,String> map = tipUtil.loadMapBuildTip(tableName, buildid);
        /*基本信息模块*/
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        NameDataModel nameDataModel01 = new NameDataModel();
        nameDataModel01.setName("基本信息");
        List<NameValueModel> nameValueModel_list01 = new ArrayList<NameValueModel>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if("".equals(value)||value==null){
                map.put(key,"无数据");
            }
        }
        //建筑情况
        String JZQK = dataOfProperityUtil_xiaoan.dataDictionary("dataDictionary_xiaoan.properties","t_build_info","JZQK",map.get("JZQK"));
        if("".equals(JZQK)||JZQK==null){JZQK = "无数据";}
        //建筑用途
        String SYYT = dataOfProperityUtil_blk.dataDictionary("dataDictionary_blkdata.properties","BLK_BUILDING","SYYT",map.get("SYYT"));
        if("".equals(SYYT)||SYYT==null){SYYT = "无数据";}
        String Name[] = {"建筑名称","建筑编码","建筑地址","建筑情况","建筑用途","竣工时间","应急联系人","联系方式"};
        String Value[] = {map.get("BUILD_NAME"),map.get("BUILDID"),map.get("BUILD_ADDR"),JZQK,SYYT,map.get("JG_DATE"),
                map.get("ZTZR_NAME"),map.get("ZTZR_TEL")};
        for(int i = 0;i<Name.length;i++){
            //NameValueModel nameValueModel = new NameValueModel();
            nameValueModel_list01.add(NameValueModel.builder().name(Name[i]).value(Value[i]).build());
            //nameValueModel.setName(Name[i]);
            //nameValueModel.setValue(Value[i]);
            //nameValueModel_list01.add(nameValueModel);
        }
        nameDataModel01.setData(nameValueModel_list01);
        nameDataModel_list.add(nameDataModel01);

        /*建筑物体量及架构信息模块*/
        NameDataModel nameDataModel02 = new NameDataModel();
        nameDataModel02.setName("建筑物体量及架构信息");
        List<NameValueModel> nameValueModel_list02 = new ArrayList<NameValueModel>();
        String Name2[] = {"占地面积","标准层面积","地下面积","地上面积","建筑面积","建筑高度","地上层数","地下层数"};
        String Value2[] = {map.get("ZDMJ"),map.get("BZCMJ"),map.get("DXMJ"),map.get("DSMJ"),map.get("JZMJ"),
                map.get("JZGD"),map.get("DSCS"),map.get("DXCS")};
        for(int j=0;j<Name2.length;j++){
            /*NameValueModel nameValueModel = new NameValueModel();
            nameValueModel.setName(Name2[j]);
            nameValueModel.setValue(Value2[j]);
            nameValueModel_list02.add(nameValueModel);*/
            nameValueModel_list02.add(NameValueModel.builder().name(Name2[j]).value(Value2[j]).build());
        }
        nameDataModel02.setData(nameValueModel_list02);
        nameDataModel_list.add(nameDataModel02);
        nameDataModel.setName(map.get("BUILD_NAME"));
        nameDataModel.setData(nameDataModel_list);
        return nameDataModel;
    }

    public NameDataModel buildRisk(String tableName, String buildid){
        List<NameDataModel> list = new ArrayList<>();
        String current = LoadMyUtil.getMyTime("DATE", 0);
        BUILD_SCORE bUILD_SCORE = new BUILD_SCORE();
        bUILD_SCORE.setBUILD_ID(buildid);
        bUILD_SCORE.setCALCULATION_TIME(current);
        List<BUILD_SCORE> bUILD_SCORE_list = bUILD_SCOREMapper.getBuildScoreList(bUILD_SCORE);
        if(Collections.isEmpty(bUILD_SCORE_list))
        	return null;
        BUILD_SCORE bUILD_SCORE_return = bUILD_SCORE_list.get(0);
        NameCodeDataModel nameCodeDataModel = JsonUtils.jsonToBean(bUILD_SCORE_return.getSCORE_DETAIL(),NameCodeDataModel.class);
        List<NameCodeDataModel> nameCodeDataModel_list = (List<NameCodeDataModel>)nameCodeDataModel.getData();
        for(Object obj : nameCodeDataModel_list) {
            NameCodeDataModel u = JsonUtils.jsonToPojo(JsonUtils.objectToJson(obj), NameCodeDataModel.class);
            List<NameScoreValueModel> nameScoreValueModel_list = (List<NameScoreValueModel>) u.getData();
            NameDataModel nameDataModel = new NameDataModel();
            nameDataModel.setName(u.getName());
            nameDataModel.setData(nameScoreValueModel_list);
            list.add(nameDataModel);
        }
        NameDataModel nameDataModel = new NameDataModel();
        nameDataModel.setName("建筑评分详情");
        nameDataModel.setData(list);
        return nameDataModel;
    }

    public MyPageInfoModel buildHiddenDanger(String buildid, Integer pageNum, Integer pageSize){
        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String before_time = LoadMyUtil.getMyTime("DATE",-30)+" 00:00:00";
        String current_time = LoadMyUtil.getMyTime("SECOND",0);
        PageHelper.startPage(pageNum, pageSize);
        T_BUILD_CHECKDAY_DANGER t_BUILD_CHECKDAY_DANGER = new T_BUILD_CHECKDAY_DANGER();
        t_BUILD_CHECKDAY_DANGER.setBUILD_ID(buildid);
        t_BUILD_CHECKDAY_DANGER.setStart_time(before_time);
        t_BUILD_CHECKDAY_DANGER.setEnd_time(current_time);
        List<T_BUILD_CHECKDAY_DANGER> t_BUILD_CHECKDAY_DANGER_list = t_BUILD_CHECKDAY_DANGERMapper.getT_BUILD_CHECKDAY_DANGER(t_BUILD_CHECKDAY_DANGER);
        PageInfo<T_BUILD_CHECKDAY_DANGER> pageInfo = new PageInfo(t_BUILD_CHECKDAY_DANGER_list);

        String top[] = {"核查日期","核查人","整改状态","巡查日期","巡查员","录入日期"};
        List<String[]> body_list = new ArrayList();
        for (T_BUILD_CHECKDAY_DANGER t_BUILD_CHECKDAY_DANGER_return:t_BUILD_CHECKDAY_DANGER_list) {
            String tbody[] = {t_BUILD_CHECKDAY_DANGER_return.getHC_TIME(),
                    t_BUILD_CHECKDAY_DANGER_return.getHCR(),
                    t_BUILD_CHECKDAY_DANGER_return.getZG_STATUS(),
                    t_BUILD_CHECKDAY_DANGER_return.getCHECK_TIME(),
                    t_BUILD_CHECKDAY_DANGER_return.getUSERNAME(),
                    t_BUILD_CHECKDAY_DANGER_return.getLRDATE()};
            body_list.add(tbody);
        }
        TheadTbodyModel theadTbodyModel = new TheadTbodyModel();
        theadTbodyModel.setThead(top);
        theadTbodyModel.setTbody(body_list);

        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setList(theadTbodyModel);
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        return pageInfo_return;
    }

    public EnterpriseModel buildEnterprise(String buildid, Integer pageNum, Integer pageSize){
        EnterpriseModel enterpriseModel = new EnterpriseModel();
        PageHelper.startPage(pageNum, pageSize);
        BLK_LEGAL_PERSON bLK_LEGAL_PERSON = new BLK_LEGAL_PERSON();
        bLK_LEGAL_PERSON.setLDDM(buildid);
        List<BLK_LEGAL_PERSON> bLK_LEGAL_PERSON_list = bLK_LEGAL_PERSONMapper.getBLK_LEGAL_PERSON(bLK_LEGAL_PERSON);
        PageInfo<BLK_LEGAL_PERSON> pageInfo = new PageInfo(bLK_LEGAL_PERSON_list);
        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        List<LinkedHashMap> map_build_enterprise_list = new ArrayList<LinkedHashMap>();
        for (BLK_LEGAL_PERSON bLK_LEGAL_PERSON_return:pageInfo.getList()) {
            LinkedHashMap map_build_enterprise = new LinkedHashMap();
            map_build_enterprise.put("企业名称",  bLK_LEGAL_PERSON_return.getJGMC());
            map_build_enterprise.put("企业类型",  bLK_LEGAL_PERSON_return.getJGXX());
            map_build_enterprise.put("法人代表", bLK_LEGAL_PERSON_return.getFZRXM());
            map_build_enterprise.put("更新时间",  bLK_LEGAL_PERSON_return.getGXSJ());
            map_build_enterprise_list.add(map_build_enterprise);
        }
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setList(map_build_enterprise_list);
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setPageSize(pageInfo.getPageSize());

        enterpriseModel.setRegisterLegalPerson(pageInfo.getTotal());
        enterpriseModel.setLegalPerson(pageInfo.getTotal());
        enterpriseModel.setOtherPerson(0);
        enterpriseModel.setAlarmList(pageInfo_return);

        return enterpriseModel;
    }

    public LayerNumData buildHouse(String tableName, String buildid){
        LayerNumData layerNumData = new LayerNumData();
        List<LayerData> layerData_list = new ArrayList<LayerData>();

        String sql_build_info = "select \"BUILD_NAME\",\"BUILDID\",\"BUILD_ADDR\",\"JZQK\",\"JG_DATE\",\"ZDMJ\",\"BZCMJ\",\"DXMJ\",\"DSMJ\",\"JZMJ\",\"JZGD\",\"DSCS\",\"DXCS\" from T_BUILD_INFO_V where \"BUILDID\"='"+buildid+"'";
        List<LinkedHashMap> list_build_info = oracleOperateService.querySql(sql_build_info);
        LinkedHashMap map_build = list_build_info.get(0);
        String DSCS = (String)map_build.get("DSCS");
        Integer floor = Integer.valueOf(DSCS);
        layerNumData.setLayernum(DSCS);
        /*获取到建筑楼层floor*/
        for(int i =1;i<floor+1;i++){
            List<BuildHouseModel> buildHouseModel_list = new ArrayList<BuildHouseModel>();
            LayerData layerData = new LayerData();
            layerData.setLayer(String.valueOf(i));
            String sql_floor = "select * from \""+tableName+"\" where LDDM = '"+buildid+"' and FWSSLC = '"+i+"' order by FWDM";
            List<LinkedHashMap> list_build_floor = oracleOperateService.querySql(sql_floor);

            /*获取楼层floor的房间信息*/
            for (LinkedHashMap house_map: list_build_floor) {
                List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
                List pop_list = new ArrayList();
                BuildHouseModel buildHouseModel = new BuildHouseModel();

                buildHouseModel.setCode((String) house_map.get("SQDM"));
                buildHouseModel.setNum((String) house_map.get("FH"));

                List<NameValueModel> nameValueModel_list01 = new ArrayList<NameValueModel>();
                NameDataModel nameDataModel01 = new NameDataModel();
                NameDataModel nameDataModel02 = new NameDataModel();

                nameDataModel01.setName("房屋信息");
                /*NameValueModel nameValueModel01 = new NameValueModel();
                nameValueModel01.setName("房屋编码");
                nameValueModel01.setValue((String) house_map.get("FWDM"));
                nameValueModel_list01.add(nameValueModel01);*/
                nameValueModel_list01.add(NameValueModel.builder().name("房屋编码").value((String) house_map.get("FWDM")).build());
                /*NameValueModel nameValueModel02 = new NameValueModel();
                nameValueModel02.setName("房屋性质");
                nameValueModel02.setValue((String) house_map.get("GLFL"));
                nameValueModel_list01.add(nameValueModel02);*/
                nameValueModel_list01.add(NameValueModel.builder().name("房屋性质").value((String) house_map.get("GLFL")).build());
                /*NameValueModel nameValueModel03 = new NameValueModel();
                nameValueModel03.setName("房屋用途");
                nameValueModel03.setValue((String) house_map.get("GLFL"));
                nameValueModel_list01.add(nameValueModel03);*/
                nameValueModel_list01.add(NameValueModel.builder().name("房屋用途").value((String) house_map.get("GLFL")).build());
                nameDataModel01.setData(nameValueModel_list01);

                nameDataModel02.setName("人口信息");
                String sql_house_pop = "select XM,LXFS,SFZH,JG,HJS from BLK_POPULATION where HID = '"+house_map.get("FWDM")+"'";
                List<LinkedHashMap> list_build_house_pop = oracleOperateService.querySql(sql_house_pop);
                for (LinkedHashMap map_house_pop : list_build_house_pop) {
                    LinkedHashMap map_pop = new LinkedHashMap();
                    map_pop.put("name", map_house_pop.get("XM"));
                    map_pop.put("tel", map_house_pop.get("LXFS"));
                    map_pop.put("idcode", map_house_pop.get("SFZH"));
                    pop_list.add(map_pop);
                }
                nameDataModel02.setData(pop_list);
                nameDataModel_list.add(nameDataModel01);
                nameDataModel_list.add(nameDataModel02);
                buildHouseModel.setData(nameDataModel_list);
                buildHouseModel_list.add(buildHouseModel);
            }
            layerData.setData(buildHouseModel_list);
            layerData_list.add(layerData);
        }
        layerNumData.setData(layerData_list);
        return layerNumData;
    }
    
}
