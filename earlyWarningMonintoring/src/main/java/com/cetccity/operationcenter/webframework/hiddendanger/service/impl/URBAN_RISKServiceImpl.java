package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.MyPageInfoModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TheadTbodyModel;
import com.cetccity.operationcenter.webframework.core.tools.ESOperate;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.UrbanRiskMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.TipDetailOfList;
import com.cetccity.operationcenter.webframework.web.model.incident.*;
import com.cetccity.operationcenter.webframework.web.model.incident.loadmap.URBAN_RISK;
import com.cetccity.operationcenter.webframework.hiddendanger.service.URBAN_RISKService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("uRBAN_RISKService")
public class URBAN_RISKServiceImpl implements URBAN_RISKService {

    @Autowired
    UrbanRiskMapper uRBAN_RISKMapper;

    public TipDetailOfList getURBAN_RISKDetailInformation(String id){
        TipDetailOfList uRBAN_RISKDetailOfList = new TipDetailOfList();
        List<URBAN_RISK> uRBAN_RISK_list = uRBAN_RISKMapper.getURBAN_RISKObj(id);
        //城区风险详情--基本信息
        LinkedHashMap map_basic = new LinkedHashMap();
        LinkedHashMap map_location = new LinkedHashMap();
        map_location.put("lon",uRBAN_RISK_list.get(0).getJD84());
        map_location.put("lat",uRBAN_RISK_list.get(0).getWD84());
        map_basic.put("危险源名称",uRBAN_RISK_list.get(0).getDANGER_NAME());
        map_basic.put("机构名称",uRBAN_RISK_list.get(0).getORG_NAME());
        map_basic.put("对象类型",uRBAN_RISK_list.get(0).getOBJECT_TYPE());
        map_basic.put("风险地址",uRBAN_RISK_list.get(0).getRISK_ADDRESS());
        map_basic.put("详细地址",uRBAN_RISK_list.get(0).getORG_ADDRESS());
        map_basic.put("经纬度",map_location);
        map_basic.put("审核人",uRBAN_RISK_list.get(0).getORG_CONTACTS());
        //责任主体
        LinkedHashMap map_SubjectOfResponsibility = new LinkedHashMap();
        map_SubjectOfResponsibility.put("主体名称",uRBAN_RISK_list.get(0).getLIABILITY_SUBJECT());
        map_SubjectOfResponsibility.put("负责人",uRBAN_RISK_list.get(0).getLIABILITY_CONTACTS());
        map_SubjectOfResponsibility.put("联系电话",uRBAN_RISK_list.get(0).getLIABILITY_TEL());
        map_SubjectOfResponsibility.put("主体地址",uRBAN_RISK_list.get(0).getDETAIL_ADDRESS());
        map_SubjectOfResponsibility.put("详细地址",uRBAN_RISK_list.get(0).getRISK_ADDRESS());
        map_SubjectOfResponsibility.put("添加时间",uRBAN_RISK_list.get(0).getRISK_ASSESS_DATE());

        uRBAN_RISKDetailOfList.setBuildName(uRBAN_RISK_list.get(0).getDANGER_NAME());
        uRBAN_RISKDetailOfList.setBasicDetail(map_basic);
        uRBAN_RISKDetailOfList.setSubjectDetail(map_SubjectOfResponsibility);
        return uRBAN_RISKDetailOfList;
    }
    //监管机构
    public MyPageInfoModel getURBAN_RISKDetailInformationOfListRegulatoryAgency(String id,Integer pageNum,Integer pageSize){
        List<URBAN_RISK> uRBAN_RISK_list = uRBAN_RISKMapper.getURBAN_RISKObj(id);

        PageHelper.startPage(pageNum, pageSize);
        URBAN_RISK uRBAN_RISK_return = new URBAN_RISK();
        uRBAN_RISK_return.setDANGER_NAME(uRBAN_RISK_list.get(0).getDANGER_NAME());
        List<URBAN_RISK> uRBAN_RISK_list_return = uRBAN_RISKMapper.getURBAN_RISKList(uRBAN_RISK_return);
        PageInfo<URBAN_RISK> pageInfo = new PageInfo(uRBAN_RISK_list_return);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String regulatoryAgency[] = {"机构名称","联系人","联系电话","地址"};
        List<String[]> regulatoryAgency_list = new ArrayList();
        for (URBAN_RISK uRBAN_RISK:uRBAN_RISK_list_return) {
            String RegulatoryAgency_tbody[] = {uRBAN_RISK.getORG_NAME(),uRBAN_RISK.getORG_CONTACTS(),uRBAN_RISK.getORG_TEL(),uRBAN_RISK.getRISK_ADDRESS()};
            regulatoryAgency_list.add(RegulatoryAgency_tbody);
        }
        TheadTbodyModel theadTbodyModel_regulatoryAgency = new TheadTbodyModel();
        theadTbodyModel_regulatoryAgency.setThead(regulatoryAgency);
        theadTbodyModel_regulatoryAgency.setTbody(regulatoryAgency_list);
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setList(theadTbodyModel_regulatoryAgency);
        return pageInfo_return;
    }

    //风险评估
    public MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAssessment(String id,Integer pageNum,Integer pageSize){
        List<URBAN_RISK> uRBAN_RISK_list = uRBAN_RISKMapper.getURBAN_RISKObj(id);

        PageHelper.startPage(pageNum, pageSize);
        URBAN_RISK uRBAN_RISK_return = new URBAN_RISK();
        uRBAN_RISK_return.setDANGER_NAME(uRBAN_RISK_list.get(0).getDANGER_NAME());
        List<URBAN_RISK> uRBAN_RISK_list_return = uRBAN_RISKMapper.getURBAN_RISKList(uRBAN_RISK_return);
        PageInfo<URBAN_RISK> pageInfo = new PageInfo(uRBAN_RISK_list_return);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String riskAssessment[] = {"评估人","风险类型","风险等级","风险分值","审核人","审核时间"};
        List<String[]> riskAssessment_list = new ArrayList();
        for (URBAN_RISK uRBAN_RISK:uRBAN_RISK_list_return) {
            String riskAssessment_tbody[] = {uRBAN_RISK.getAUDITOR(),uRBAN_RISK.getPLACE_TYPE(),
                    uRBAN_RISK.getRISK_ASSESS_LV(), uRBAN_RISK.getRISK_ASSESS_CSCORE(),
                    uRBAN_RISK.getRISK_ASSESS_MAN_NAME(), uRBAN_RISK.getRISK_ASSESS_AUDIT_DATE()};
            riskAssessment_list.add(riskAssessment_tbody);
        }
        TheadTbodyModel theadTbodyModel_riskAssessment = new TheadTbodyModel();
        theadTbodyModel_riskAssessment.setThead(riskAssessment);
        theadTbodyModel_riskAssessment.setTbody(riskAssessment_list);
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setList(theadTbodyModel_riskAssessment);
        return pageInfo_return;
    }

    //风险隐患
    public MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskDanger(String id, Integer pageNum, Integer pageSize){
        List<URBAN_RISK> uRBAN_RISK_list = uRBAN_RISKMapper.getURBAN_RISKObj(id);
        if(uRBAN_RISK_list == null || uRBAN_RISK_list.size() == 0)
        	return null;

        PageHelper.startPage(pageNum, pageSize);
        URBAN_RISK uRBAN_RISK_return = new URBAN_RISK();
        uRBAN_RISK_return.setDANGER_NAME(uRBAN_RISK_list.get(0).getDANGER_NAME());
        List<URBAN_RISK> uRBAN_RISK_list_return = uRBAN_RISKMapper.getURBAN_RISKList(uRBAN_RISK_return);
        if(uRBAN_RISK_list_return == null || uRBAN_RISK_list_return.size() == 0)
        	return null;
        PageInfo<URBAN_RISK> pageInfo = new PageInfo(uRBAN_RISK_list_return);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String riskDanger[] = {"隐患发现人","隐患描述","措施描述","责任主体","提交时间"};
        List<String[]> riskDanger_list = new ArrayList();
        for (URBAN_RISK uRBAN_RISK:uRBAN_RISK_list_return) {
            String riskDanger_tbody[] = {uRBAN_RISK.getRISK_ASSESS_MAN_NAME(),uRBAN_RISK.getRISK_ASSESS_TYPE_NAMES(),
                    uRBAN_RISK.getRISK_ASSESS_TYPE_NAME(), uRBAN_RISK.getLIABILITY_SUBJECT(),
                    uRBAN_RISK.getRISK_ASSESS_DATE()};
            riskDanger_list.add(riskDanger_tbody);
        }
        TheadTbodyModel theadTbodyModel_riskDanger = new TheadTbodyModel();
        theadTbodyModel_riskDanger.setThead(riskDanger);
        theadTbodyModel_riskDanger.setTbody(riskDanger_list);
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setList(theadTbodyModel_riskDanger);
        return pageInfo_return;
    }
    //风险预警
    public MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAlarm(String id, Integer pageNum, Integer pageSize){
        List<URBAN_RISK> uRBAN_RISK_list = uRBAN_RISKMapper.getURBAN_RISKObj(id);

        PageHelper.startPage(pageNum, pageSize);
        URBAN_RISK uRBAN_RISK_return = new URBAN_RISK();
        uRBAN_RISK_return.setDANGER_NAME(uRBAN_RISK_list.get(0).getDANGER_NAME());
        List<URBAN_RISK> uRBAN_RISK_list_return = uRBAN_RISKMapper.getURBAN_RISKList(uRBAN_RISK_return);
        PageInfo<URBAN_RISK> pageInfo = new PageInfo(uRBAN_RISK_list_return);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String riskAlarm[] = {"预警人","预警原因","预警时间","开始时间","结束时间"};
        List<String[]> riskAlarm_list = new ArrayList();
        for (URBAN_RISK uRBAN_RISK:uRBAN_RISK_list_return) {
            String riskAlarm_tbody[] = {uRBAN_RISK.getRISK_ASSESS_MAN_NAME(),uRBAN_RISK.getRISK_ASSESS_TYPE_NAMES(),
                    uRBAN_RISK.getRISK_ASSESS_AUDIT_DATE(), uRBAN_RISK.getRISK_ASSESS_DATE(),uRBAN_RISK.getRISK_ASSESS_DATE()};
            riskAlarm_list.add(riskAlarm_tbody);
        }
        TheadTbodyModel theadTbodyModel_riskAlarm = new TheadTbodyModel();
        theadTbodyModel_riskAlarm.setThead(riskAlarm);
        theadTbodyModel_riskAlarm.setTbody(riskAlarm_list);
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setList(theadTbodyModel_riskAlarm);
        return pageInfo_return;
    }
    //风险事故
    public MyPageInfoModel getURBAN_RISKDetailInformationOfListRiskAccident(String id, Integer pageNum, Integer pageSize){
        List<URBAN_RISK> uRBAN_RISK_list = uRBAN_RISKMapper.getURBAN_RISKObj(id);

        PageHelper.startPage(pageNum, pageSize);
        URBAN_RISK uRBAN_RISK_return = new URBAN_RISK();
        uRBAN_RISK_return.setDANGER_NAME(uRBAN_RISK_list.get(0).getDANGER_NAME());
        List<URBAN_RISK> uRBAN_RISK_list_return = uRBAN_RISKMapper.getURBAN_RISKList(uRBAN_RISK_return);
        PageInfo<URBAN_RISK> pageInfo = new PageInfo(uRBAN_RISK_list_return);

        MyPageInfoModel pageInfo_return = new MyPageInfoModel();
        String riskAccident[] = {"发布人","直接原因","事故描述","发生时间"};
        List<String[]> riskAccident_list = new ArrayList();
        for (URBAN_RISK uRBAN_RISK:uRBAN_RISK_list_return) {
            String riskAccident_tbody[] = {uRBAN_RISK.getRISK_ASSESS_MAN_NAME(),uRBAN_RISK.getRISK_ASSESS_TYPE_NAMES(),
                    uRBAN_RISK.getRISK_ASSESS_TYPE_NAMES(), uRBAN_RISK.getRISK_ASSESS_DATE()};
            riskAccident_list.add(riskAccident_tbody);
        }
        TheadTbodyModel theadTbodyModel_riskAccident = new TheadTbodyModel();
        theadTbodyModel_riskAccident.setThead(riskAccident);
        theadTbodyModel_riskAccident.setTbody(riskAccident_list);
        pageInfo_return.setPageNum(pageInfo.getPageNum());
        pageInfo_return.setPageSize(pageInfo.getPageSize());
        pageInfo_return.setPages(pageInfo.getPages());
        pageInfo_return.setTotal(pageInfo.getTotal());
        pageInfo_return.setList(theadTbodyModel_riskAccident);
        return pageInfo_return;
    }

    public NameColorDataModel getChartOfRightOne(String street_code){
        NameColorDataModel nameColorDataModel = new NameColorDataModel();
        Map map = new HashMap();
        if("all".equals(street_code)) {
            map.put("STREET_CODE", null);
        }else {
            map.put("STREET_CODE", street_code);
        }
        //城区风险评估总数
        Integer count = uRBAN_RISKMapper.getCount(map);
        //三小场所
        map.put("PLACE_TYPE_CODE", "1");
        Integer count_sanxiaoplace = uRBAN_RISKMapper.getCount(map);
        //工业企业
        map.put("PLACE_TYPE_CODE", "2");
        Integer count_gongyeplace = uRBAN_RISKMapper.getCount(map);
        //人员密集场所
        map.put("PLACE_TYPE_CODE", "3");
        Integer count_renyuanplace = uRBAN_RISKMapper.getCount(map);
        //公共基础设施
        map.put("PLACE_TYPE_CODE", "4");
        Integer count_basicplace = uRBAN_RISKMapper.getCount(map);
        //其他风险单位
        map.put("PLACE_TYPE_CODE", "5");
        Integer count_otherplace = uRBAN_RISKMapper.getCount(map);
        String[] Color = {"#5bf385", "#007cee", "#ff7300", "#00c6ef", "#0086ef"};
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        /*NameValueModel nameValueModel_count_sanxiaoplace = new NameValueModel();
           nameValueModel_count_sanxiaoplace.setName("三小场所");
           nameValueModel_count_sanxiaoplace.setValue(String.valueOf(count_sanxiaoplace));*/
        NameValueModel nameValueModel_count_sanxiaoplace = NameValueModel.builder().name("三小场所").value(String.valueOf(count_sanxiaoplace)).build();
        /*NameValueModel nameValueModel_count_gongyeplace = new NameValueModel();
           nameValueModel_count_gongyeplace.setName("工业企业");
           nameValueModel_count_gongyeplace.setValue(String.valueOf(count_gongyeplace));*/
        NameValueModel nameValueModel_count_gongyeplace = NameValueModel.builder().name("工业企业").value(String.valueOf(count_gongyeplace)).build();
        /*NameValueModel nameValueModel_count_renyuanplace = new NameValueModel();
           nameValueModel_count_renyuanplace.setName("人员密集场所");
           nameValueModel_count_renyuanplace.setValue(String.valueOf(count_renyuanplace));*/
        NameValueModel nameValueModel_count_renyuanplace = NameValueModel.builder().name("人员密集场所").value(String.valueOf(count_renyuanplace)).build();
        /*NameValueModel nameValueModel_count_basicplace = new NameValueModel();
        nameValueModel_count_basicplace.setName("公共基础设施");
        nameValueModel_count_basicplace.setValue(String.valueOf(count_basicplace));*/
        NameValueModel nameValueModel_count_basicplace = NameValueModel.builder().name("公共基础设施").value(String.valueOf(count_basicplace)).build();
        /*NameValueModel nameValueModel_count_otherplace = new NameValueModel();
        nameValueModel_count_otherplace.setName("其他风险单位");
        nameValueModel_count_otherplace.setValue(String.valueOf(count_otherplace));*/
        NameValueModel nameValueModel_count_otherplace = NameValueModel.builder().name("其他风险单位").value(String.valueOf(count_otherplace)).build();

        nameValueModel_list.add(nameValueModel_count_sanxiaoplace);
        nameValueModel_list.add(nameValueModel_count_gongyeplace);
        nameValueModel_list.add(nameValueModel_count_renyuanplace);
        nameValueModel_list.add(nameValueModel_count_basicplace);
        nameValueModel_list.add(nameValueModel_count_otherplace);

        nameColorDataModel.setName("区域风险总数");
        nameColorDataModel.setValue(String.valueOf(count));
        nameColorDataModel.setColor(Color);
        nameColorDataModel.setData(nameValueModel_list);
        return nameColorDataModel;
    }

    public NameColorDataModel getChartOfRightTwo(String street_code){
        NameColorDataModel nameColorDataModel = new NameColorDataModel();
        Map map = new HashMap();
        if("all".equals(street_code)) {
            map.put("STREET_CODE", null);
        }else {
            map.put("STREET_CODE", street_code);
        }
        //火灾
        map.put("RISK_ASSESS_TYPE_NAME","火灾");
        Integer count_huozai = uRBAN_RISKMapper.getCount(map);
        //触电
        map.put("RISK_ASSESS_TYPE_NAME","触电");
        Integer count_chudian = uRBAN_RISKMapper.getCount(map);
        //其他
        map.put("RISK_ASSESS_TYPE_NAME","其他");
        Integer count_other = uRBAN_RISKMapper.getCount(map);

        String[] Color ={"#5bf385", "#007cee","#ff7300"};
        List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
        /*NameValueModel nameValueModel_count_huozai = new NameValueModel();
        nameValueModel_count_huozai.setName("火灾");nameValueModel_count_huozai.setValue(String.valueOf(count_huozai));*/
        NameValueModel nameValueModel_count_huozai = NameValueModel.builder().name("火灾").value(String.valueOf(count_huozai)).build();
        /*NameValueModel nameValueModel_count_chudian = new NameValueModel();
        nameValueModel_count_chudian.setName("触电");nameValueModel_count_chudian.setValue(String.valueOf(count_chudian));*/
        NameValueModel nameValueModel_count_chudian = NameValueModel.builder().name("触电").value(String.valueOf(count_chudian)).build();
        /*NameValueModel nameValueModel_count_count_other = new NameValueModel();
        nameValueModel_count_count_other.setName("其他");nameValueModel_count_count_other.setValue(String.valueOf(count_other));*/
        NameValueModel nameValueModel_count_count_other = NameValueModel.builder().name("其他").value(String.valueOf(count_other)).build();
        nameValueModel_list.add(nameValueModel_count_huozai);
        nameValueModel_list.add(nameValueModel_count_chudian);
        nameValueModel_list.add(nameValueModel_count_count_other);

        nameColorDataModel.setName("城区风险类型");
        nameColorDataModel.setValue("");
        nameColorDataModel.setColor(Color);
        nameColorDataModel.setData(nameValueModel_list);
        return nameColorDataModel;
    }

    public NameColorXDataYDataModel getChartOfRightThree(String street_string)throws IOException {
        NameColorXDataYDataModel nameColorXDataYDataModel = new NameColorXDataYDataModel();
        String[] street = street_string.split(",");
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        String [] color = {"#00c6f0","#54e97d","#0090ff","#ff7e00","#ff1e00"};
        String [] PLACE_TYPE = {"三小场所","工业企业","人员密集场所","公共基础设施","其他风险单位"};
        for (int j=1;j<6;j++) {
            NameDataModel nameDataModel = new NameDataModel();
            List value = new ArrayList() ;
            //园岭街道,南园街道,福田街道,沙头街道,梅林街道,华富街道,香蜜湖街道,莲花街道,华强北街道,福保街道
            for (int i=0;i<street.length;i++) {
                InputStream inputStream = ESOperate.class.getResourceAsStream("/street.properties");
                Properties properties = new Properties();
                properties.load(new InputStreamReader(inputStream, "UTF-8"));
                String street_code = properties.getProperty(street[i]);
                Map map = new HashMap();
                map.put("STREET_CODE",street_code);
                map.put("PLACE_TYPE_CODE",j);
                Integer count_street_type = uRBAN_RISKMapper.getCount(map);
                value.add(count_street_type);
            }
            nameDataModel.setName(PLACE_TYPE[j-1]);
            nameDataModel.setData(value);
            nameDataModel_list.add(nameDataModel);
        }
        nameColorXDataYDataModel.setName("街道风险分布");
        nameColorXDataYDataModel.setColor(color);
        nameColorXDataYDataModel.setXdata(street);
        nameColorXDataYDataModel.setYdata(nameDataModel_list);
        return nameColorXDataYDataModel;
    }

    public NameColorDataModel getChartOfRightFour(String street_code){
        NameColorDataModel nameColorDataModel = new NameColorDataModel();
        String year="";
        String month="";
        String dete = "";
        String risk_type[] = {"火灾","触电","其他"};
        String color[] = {"#ff9000","#008311","#44a1df"};
        long count = 0;
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();

        for (int j = 0; j < risk_type.length; j++) {
            List<NameValueModel> nameValueModel_list = new ArrayList<NameValueModel>();
            NameDataModel nameDataModel = new NameDataModel();
            nameDataModel.setName(risk_type[j]);
            for(int i = 7;i>-1;i--) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -i);
            String date = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
            year = date.split("-")[0];
            month = date.split("-")[1];
            if ("0".equals(month.substring(0, 1))) {
                month = month.split("0")[1];
            }
            dete = year + "-" + month;
                /*NameValueModel nameValueModel = new NameValueModel();*/
                Map map = new HashMap();
                map.put("STREET_CODE", street_code);
                map.put("RISK_ASSESS_TYPE_NAME", risk_type[j]);
                map.put("PLACE_TYPE_CODE","1");
                map.put("RISK_ASSESS_DATE", dete);
                Integer count_street_type = uRBAN_RISKMapper.getCount(map);
                count += count_street_type;
                /*nameValueModel.setName(dete);
                nameValueModel.setValue(String.valueOf(count_street_type));*/
                nameValueModel_list.add(NameValueModel.builder().name(dete).value(String.valueOf(count_street_type)).build());
                nameDataModel.setData(nameValueModel_list);
            }
            nameDataModel_list.add(nameDataModel);
        }
        nameColorDataModel.setName("三小场所概览");
        nameColorDataModel.setValue(String.valueOf(count));
        nameColorDataModel.setColor(color);
        nameColorDataModel.setData(nameDataModel_list);
        return nameColorDataModel;
    }
}
