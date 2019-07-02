package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.urbansign.api.model.NameTodayDataModel;
import com.cetccity.operationcenter.webframework.urbansign.api.model.NameValueDataModel;
import com.cetccity.operationcenter.webframework.web.dao.MACRO_ECONOMYMapper;
import com.cetccity.operationcenter.webframework.web.model.citySign.*;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.urbansign.service.MacroEconomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

@Service("macroEconomyService")
public class MacroEconomyServiceImpl implements MacroEconomyService {

    @Autowired
    MACRO_ECONOMYMapper mACRO_ECONOMYMapper;

    //宏观经济
    public NameDataModel macroEconomy(){
        NameDataModel nameDataModel = new NameDataModel();
        MacroEconomy macroEconomy = new MacroEconomy();
        String year="";
        String month="";
        String dete = "";
        int i = 0;
        List<MACRO_ECONOMY> economy_list = new ArrayList<MACRO_ECONOMY>();
        List<MACRO_ECONOMY> economy_list_lastYear = new ArrayList<MACRO_ECONOMY>();

        for (i=0;i<20;i++) {
            MACRO_ECONOMY mACRO_ECONOMY = new MACRO_ECONOMY();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -i);
            String date = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
            year = date.split("-")[0];
            month = date.split("-")[1];
            dete = year+"-"+month;
            if ("0".equals(month.substring(0,1))) {
                month = month.split("0")[1];
            }
            mACRO_ECONOMY.setCreateYear(year);
            mACRO_ECONOMY.setCreateMonth(month);
            mACRO_ECONOMY.setIndexType("10");//地区生产总值
            mACRO_ECONOMY.setCalculationType("1");
            economy_list = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            //上一年同月
            mACRO_ECONOMY.setCreateYear(String.valueOf(Integer.parseInt(year)-1));
            economy_list_lastYear = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            if(economy_list.size()!=0){
                break;
            }
        }
        String region_GDP = economy_list.get(0).getValue();
        String region_GDP_lastYear = economy_list_lastYear.get(0).getValue();
        Long trend_GDP = Long.parseLong(region_GDP) - Long.parseLong(region_GDP_lastYear);
     /*   System.out.println("--dete--"+dete);
        System.out.println("--region_GDP--"+region_GDP);
        System.out.println("--region_GDP_lastYear--"+region_GDP_lastYear);*/

        //人均可支配收入
        String per_capita_domination = "";
        List<MACRO_ECONOMY> economy_list_per_capita_domination = new ArrayList<MACRO_ECONOMY>();
        MACRO_ECONOMY per_capita_domination_economy = new MACRO_ECONOMY();
        per_capita_domination_economy.setCreateYear(year);
        per_capita_domination_economy.setCreateMonth(month);
        per_capita_domination_economy.setIndexType("8");
        per_capita_domination_economy.setCalculationType("1");
        economy_list_per_capita_domination = mACRO_ECONOMYMapper.selectObjByTime(per_capita_domination_economy);
        if(economy_list_per_capita_domination.size()==0){
            per_capita_domination = "无数据";
        }else{
            per_capita_domination = economy_list_per_capita_domination.get(0).getValue();
        }
        //一般公共预算增长率
        String public_budget_growth_rate = "";
        List<MACRO_ECONOMY> economy_list_Public_budget_growth_rate = new ArrayList<MACRO_ECONOMY>();
        MACRO_ECONOMY Public_budget_growth_rate_economy = new MACRO_ECONOMY();
        Public_budget_growth_rate_economy.setCreateYear(year);
        Public_budget_growth_rate_economy.setCreateMonth(month);
        Public_budget_growth_rate_economy.setIndexType("15");
        Public_budget_growth_rate_economy.setCalculationType("0");
        economy_list_Public_budget_growth_rate = mACRO_ECONOMYMapper.selectObjByTime(Public_budget_growth_rate_economy);
        if(economy_list_Public_budget_growth_rate.size()==0){
            public_budget_growth_rate = "无数据";
        }else{
            public_budget_growth_rate = economy_list_Public_budget_growth_rate.get(0).getValue()+"%";
        }

        //福田区月度GDP
        List<NameValueModel> economy_list_month_reverse = new ArrayList<NameValueModel>();
        List<NameValueModel> economy_list_month = new ArrayList<NameValueModel>();
        List<MACRO_ECONOMY> economy_list_month_line = new ArrayList<MACRO_ECONOMY>();
        for (int j=i;j<i+12;j++) {
            NameValueModel nameValueModel_month_line = NameValueModel.builder().build();
            MACRO_ECONOMY mACRO_ECONOMY = new MACRO_ECONOMY();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -j);
            String date = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
            year = date.split("-")[0];
            month = date.split("-")[1];
            dete = year+"-"+month;
            if ("0".equals(month.substring(0,1))) {
                month = month.split("0")[1];
            }
            mACRO_ECONOMY.setCreateYear(year);
            mACRO_ECONOMY.setCreateMonth(month);
            mACRO_ECONOMY.setIndexType("10");//地区生产总值
            mACRO_ECONOMY.setCalculationType("1");
            economy_list_month_line = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            nameValueModel_month_line.setName(dete);
            if(economy_list_month_line.size()==0){
                nameValueModel_month_line.setValue("0");
            }else {
                String num = economy_list_month_line.get(0).getValue();
                String return_num = num.substring(0, num.length() - 8) + "." + num.substring(num.length() - 8, num.length());
                nameValueModel_month_line.setValue(return_num);
            }
            economy_list_month.add(nameValueModel_month_line);
        }

        //福田区年度GDP
        List<NameValueModel> economy_list_year_reverse = new ArrayList<NameValueModel>();
        List<NameValueModel> economy_list_year = new ArrayList<NameValueModel>();
        List<MACRO_ECONOMY> economy_list_year_line = new ArrayList<MACRO_ECONOMY>();
        for(int s=0;s<8;s++){
            NameValueModel nameValueModel_year_line = NameValueModel.builder().build();
            MACRO_ECONOMY mACRO_ECONOMY = new MACRO_ECONOMY();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -s);
            String date = new SimpleDateFormat("yyyy").format(cal.getTime());
            year = date;

            mACRO_ECONOMY.setCreateYear(year);
            mACRO_ECONOMY.setCreateMonth("12");
            mACRO_ECONOMY.setIndexType("10");//地区生产总值
            mACRO_ECONOMY.setCalculationType("1");
            economy_list_year_line = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            nameValueModel_year_line.setName(year);
            if(economy_list_year_line.size()==0){
                nameValueModel_year_line.setValue("0");
            }else {
                nameValueModel_year_line.setValue(economy_list_year_line.get(0).getValue());
            }
            economy_list_year.add(nameValueModel_year_line);
        }
        String region_GDP_add_point_unit = region_GDP.substring( 0, region_GDP.length()-8)+"."+region_GDP.substring( region_GDP.length()-8,region_GDP.length()-6)+"亿元";
        GrossRegionalProduct grossRegionalProduct = new GrossRegionalProduct();
        NameTodayDataModel nameTodayDataModel = new NameTodayDataModel();
        List<NameDataModel> nameDataModel_region_list = new ArrayList<NameDataModel>();
        NameDataModel nameDataModel_region_month = new NameDataModel();
        NameDataModel nameDataModel_region_year = new NameDataModel();
        nameDataModel_region_month.setName("近一年地区生产总值");
        //economy_list_month   反向
        ListIterator<NameValueModel> lis2 = economy_list_month.listIterator();
        for (lis2 = economy_list_month.listIterator(); lis2.hasNext();) {
            lis2.next();
        }
        for (int n = economy_list_month.size() - 1; n >= 0; n--) {
            economy_list_month_reverse.add(economy_list_month.get(n));
        }
        nameDataModel_region_month.setData(economy_list_month_reverse);
        nameDataModel_region_year.setName("近十年地区生产总值");
        //economy_list_year 反向
        ListIterator<NameValueModel> lis3 = economy_list_year.listIterator();
        for (lis3 = economy_list_year.listIterator(); lis3.hasNext();) {
            lis3.next();
        }
        for (int n = economy_list_year.size() - 1; n >= 0; n--) {
            economy_list_year_reverse.add(economy_list_year.get(n));
        }
        nameDataModel_region_year.setData(economy_list_year_reverse);
        nameDataModel_region_list.add(nameDataModel_region_month);
        nameDataModel_region_list.add(nameDataModel_region_year);

        List<NameValueModel> nameValueModel_list_me = new ArrayList<NameValueModel>();
        /*NameValueModel nameValueModel_GDP_total = new NameValueModel();
        nameValueModel_GDP_total.setName("地区生产总值");
        nameValueModel_GDP_total.setValue(region_GDP_add_point_unit);
        NameValueModel nameValueModel_con_total = new NameValueModel();
        nameValueModel_con_total.setName("人均可支配收入");
        nameValueModel_con_total.setValue(per_capita_domination);
        NameValueModel nameValueModel_public_budget_growth_rate = new NameValueModel();
        nameValueModel_public_budget_growth_rate.setName("一般公共预算增长率");
        nameValueModel_public_budget_growth_rate.setValue(public_budget_growth_rate);
        nameValueModel_list_me.add(nameValueModel_GDP_total);
        nameValueModel_list_me.add(nameValueModel_con_total);
        nameValueModel_list_me.add(nameValueModel_public_budget_growth_rate);*/

        nameValueModel_list_me.add(NameValueModel.builder().name("地区生产总值").value(region_GDP_add_point_unit).build());
        nameValueModel_list_me.add(NameValueModel.builder().name("人均可支配收入").value(per_capita_domination).build());
        nameValueModel_list_me.add(NameValueModel.builder().name("一般公共预算增长率").value(public_budget_growth_rate).build());

        nameTodayDataModel.setName("地区生产总值");
        nameTodayDataModel.setToday(nameValueModel_list_me);
        nameTodayDataModel.setData(nameDataModel_region_list);

        grossRegionalProduct.setGrossRegionalProduct(nameTodayDataModel);
        nameDataModel.setName("宏观经济");
        nameDataModel.setData(grossRegionalProduct);
        return nameDataModel;
    }

    public MacroEconomyDetail macroEconomyDetail(){
        //详情部分----------------------
        MacroEconomyDetail macroEconomyDetail = new MacroEconomyDetail();
        //年度GDP总量
        String year="";
        String month="";
        String dete = "";
        List<MACRO_ECONOMY> economy_list_gdpTotal = new ArrayList<MACRO_ECONOMY>();
        List<MACRO_ECONOMY> economy_list_gdprate = new ArrayList<MACRO_ECONOMY>();

        for (int i=0;i<20;i++) {
            MACRO_ECONOMY mACRO_ECONOMY = new MACRO_ECONOMY();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -i);
            String date = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
            year = date.split("-")[0];
            month = date.split("-")[1];
            dete = year+"-"+month;
            if ("0".equals(month.substring(0,1))) {
                month = month.split("0")[1];
            }
            mACRO_ECONOMY.setCreateYear(year);
            mACRO_ECONOMY.setCreateMonth(month);
            mACRO_ECONOMY.setIndexType("10");//地区生产总值
            mACRO_ECONOMY.setCalculationType("1");//GDP总额
            economy_list_gdpTotal = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            mACRO_ECONOMY.setCalculationType("0");//GDP增长率
            economy_list_gdprate = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            if(economy_list_gdpTotal.size()!=0){
                break;
            }
        }
        String region_GDP_total = economy_list_gdpTotal.get(0).getValue();//GDP总额
        String region_GDP_add_point_unit = region_GDP_total.substring( 0, region_GDP_total.length()-8)+"."+region_GDP_total.substring( region_GDP_total.length()-8,region_GDP_total.length()-6)+"亿元";
        String region_GDP_rate = economy_list_gdprate.get(0).getValue()+"%";//GDP增长率
        //进出口总额
        List<MACRO_ECONOMY> economy_list_Import_and_exportTotal = new ArrayList<MACRO_ECONOMY>();
        int s;
        for (s=0;s<20;s++) {
            MACRO_ECONOMY mACRO_ECONOMY = new MACRO_ECONOMY();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -s);
            String date = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
            year = date.split("-")[0];
            month = date.split("-")[1];
            dete = year+"-"+month;
            if ("0".equals(month.substring(0,1))) {
                month = month.split("0")[1];
            }
            mACRO_ECONOMY.setCreateYear(year);
            mACRO_ECONOMY.setCreateMonth(month);
            mACRO_ECONOMY.setIndexType("12");//地区生产总值
            mACRO_ECONOMY.setCalculationType("1");
            economy_list_Import_and_exportTotal = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            if(economy_list_Import_and_exportTotal.size()!=0){
                break;
            }
        }
        //福田区月度进出口总额
        List<NameDataModel> nameDataModel_ie_list = new ArrayList<NameDataModel>();
        List<NameValueModel> economy_list_ie_month = new ArrayList<NameValueModel>();
        List<NameValueModel> economy_list_ie_month_reverse = new ArrayList<NameValueModel>();
        List<MACRO_ECONOMY> economy_list_ie_month_line = new ArrayList<MACRO_ECONOMY>();
        for (int j=s;j<s+8;j++) {
            NameValueModel nameValueModel_ie_month_line = NameValueModel.builder().build();
            MACRO_ECONOMY mACRO_ECONOMY = new MACRO_ECONOMY();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -j);
            String date = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
            year = date.split("-")[0];
            month = date.split("-")[1];
            dete = year+"-"+month;
            if ("0".equals(month.substring(0,1))) {
                month = month.split("0")[1];
            }
            mACRO_ECONOMY.setCreateYear(year);
            mACRO_ECONOMY.setCreateMonth(month);
            mACRO_ECONOMY.setIndexType("12");//地区生产总值
            mACRO_ECONOMY.setCalculationType("1");
            economy_list_ie_month_line = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            nameValueModel_ie_month_line.setName(dete);
            if(economy_list_ie_month_line.size()==0){
                nameValueModel_ie_month_line.setValue("0");
            }else {
                String num = economy_list_ie_month_line.get(0).getValue();
                if(num.length()>=9) num = num.substring( 0, num.length()-8)+"."+num.substring( num.length()-8,num.length()-6);
                nameValueModel_ie_month_line.setValue(num);
            }
            economy_list_ie_month.add(nameValueModel_ie_month_line);
        }
        String region_Ie_total_add_point_unit = economy_list_ie_month.get(0).getValue()+"亿元";
        //economy_list_ie_month   反向
        ListIterator<NameValueModel> li = economy_list_ie_month.listIterator();
        for (li = economy_list_ie_month.listIterator(); li.hasNext();) {
            li.next();
        }
        for (int i = economy_list_ie_month.size() - 1; i >= 0; i--) {
            economy_list_ie_month_reverse.add(economy_list_ie_month.get(i));
        }

        //福田区年度消费--城镇居民人均可支配收入
        List<NameDataModel> nameDataModel_control_list = new ArrayList<NameDataModel>();
        List<NameValueModel> economy_list_control_year = new ArrayList<NameValueModel>();
        List<MACRO_ECONOMY> economy_list_control_year_line = new ArrayList<MACRO_ECONOMY>();
        for(int y=7;y>=0;y--){
            NameValueModel nameValueModel_control_year_line = NameValueModel.builder().build();
            MACRO_ECONOMY mACRO_ECONOMY = new MACRO_ECONOMY();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -y);
            String date = new SimpleDateFormat("yyyy").format(cal.getTime());
            year = date;
            mACRO_ECONOMY.setCreateYear(year);
            mACRO_ECONOMY.setCreateMonth("12");
            mACRO_ECONOMY.setIndexType("8");//城镇居民人均可支配收入
            mACRO_ECONOMY.setCalculationType("1");
            economy_list_control_year_line = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            nameValueModel_control_year_line.setName(year);
            if(economy_list_control_year_line.size()==0){
                nameValueModel_control_year_line.setValue("0");
            }else {
                nameValueModel_control_year_line.setValue(economy_list_control_year_line.get(0).getValue());
            }
            economy_list_control_year.add(nameValueModel_control_year_line);
        }
        List<MACRO_ECONOMY> economy_list_control_year_rate = new ArrayList<MACRO_ECONOMY>();
        //获取最近一年的城镇居民人均可支配收入增长率
        MACRO_ECONOMY mACRO_ECONOMY_8rate = new MACRO_ECONOMY();
        mACRO_ECONOMY_8rate.setCreateYear(LoadMyUtil.getMyTime("YEAR",0));
        mACRO_ECONOMY_8rate.setCreateMonth("12");
        mACRO_ECONOMY_8rate.setIndexType("8");//城镇居民人均可支配收入
        mACRO_ECONOMY_8rate.setCalculationType("0");
        economy_list_control_year_rate = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY_8rate);

        //福田区月度一般性公共预算
        //最近一个月的一般性公共预算收入
        String publicBudget_reverse_Lately;
        //最近一个月的一般性公共预算支出
        String publicBudget_expenditure_Lately;
        PublicBudget publicBudget = new PublicBudget();
        publicBudget.setName("公共预算");
        //福田区月度一般性公共预算收入
        List<NameValueModel> economy_list_Public_budget_revenue_year = new ArrayList<NameValueModel>();
        List<NameValueModel> economy_list_Public_budget_revenue_year_reverse = new ArrayList<NameValueModel>();
        List<MACRO_ECONOMY> economy_list_Public_budget_revenue_year_line = new ArrayList<MACRO_ECONOMY>();
        for(int y=s;y<s+12;y++){
            NameValueModel nameValueModel_Public_budget_revenue_year_line = NameValueModel.builder().build();
            MACRO_ECONOMY mACRO_ECONOMY = new MACRO_ECONOMY();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -y);
            String date = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
            year = date.split("-")[0];
            month = date.split("-")[1];
            dete = year+"-"+month;
            if ("0".equals(month.substring(0,1))) {
                month = month.split("0")[1];
            }
            mACRO_ECONOMY.setCreateYear(year);
            mACRO_ECONOMY.setCreateMonth(month);
            mACRO_ECONOMY.setIndexType("15");//城镇居民人均可支配收入
            mACRO_ECONOMY.setCalculationType("1");
            economy_list_Public_budget_revenue_year_line = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            nameValueModel_Public_budget_revenue_year_line.setName(dete);
            if(economy_list_Public_budget_revenue_year_line.size()==0){
                nameValueModel_Public_budget_revenue_year_line.setValue("0");
            }else {
                String num = economy_list_Public_budget_revenue_year_line.get(0).getValue();
                if(num.length()>=9)
                    num = num.substring( 0, num.length()-8)+"."+num.substring( num.length()-8,num.length()-6);
                nameValueModel_Public_budget_revenue_year_line.setValue(num);
            }
            economy_list_Public_budget_revenue_year.add(nameValueModel_Public_budget_revenue_year_line);
        }
        publicBudget_reverse_Lately = economy_list_Public_budget_revenue_year.get(0).getValue()+"亿元";
        //economy_list_Public_budget_revenue_year   反向
        ListIterator<NameValueModel> li3 = economy_list_Public_budget_revenue_year.listIterator();
        for (li3 = economy_list_Public_budget_revenue_year.listIterator(); li3.hasNext();) {
            li3.next();
        }
        for (int i = economy_list_Public_budget_revenue_year.size() - 1; i >= 0; i--) {
            economy_list_Public_budget_revenue_year_reverse.add(economy_list_Public_budget_revenue_year.get(i));
        }
        //福田区月度一般性公共预算支出
        List<NameValueModel> economy_list_Public_budget_expenditure_year = new ArrayList<NameValueModel>();
        List<NameValueModel> economy_list_Public_budget_expenditure_year_reverse = new ArrayList<NameValueModel>();
        List<MACRO_ECONOMY> economy_list_Public_budget_expenditure_year_line = new ArrayList<MACRO_ECONOMY>();
        for(int y=s;y<s+12;y++){
            NameValueModel nameValueModel_Public_budget_expenditure_year_line = NameValueModel.builder().build();
            MACRO_ECONOMY mACRO_ECONOMY = new MACRO_ECONOMY();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -y);
            String date = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
            year = date.split("-")[0];
            month = date.split("-")[1];
            dete = year+"-"+month;
            if ("0".equals(month.substring(0,1))) {
                month = month.split("0")[1];
            }
            mACRO_ECONOMY.setCreateYear(year);
            mACRO_ECONOMY.setCreateMonth(month);
            mACRO_ECONOMY.setIndexType("16");//城镇居民人均可支配收入
            mACRO_ECONOMY.setCalculationType("1");
            economy_list_Public_budget_expenditure_year_line = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            nameValueModel_Public_budget_expenditure_year_line.setName(dete);
            if(economy_list_Public_budget_expenditure_year_line.size()==0){
                nameValueModel_Public_budget_expenditure_year_line.setValue("0");
            }else {
                String num = economy_list_Public_budget_expenditure_year_line.get(0).getValue();
                if(num.length()>=9) num = num.substring( 0, num.length()-8)+"."+num.substring( num.length()-8,num.length()-6);
                nameValueModel_Public_budget_expenditure_year_line.setValue(num);
            }
            economy_list_Public_budget_expenditure_year.add(nameValueModel_Public_budget_expenditure_year_line);
        }
        publicBudget_expenditure_Lately = economy_list_Public_budget_expenditure_year.get(0).getValue()+"亿元";
        //economy_list_Public_budget_expenditure_year   反向
        ListIterator<NameValueModel> li4 = economy_list_Public_budget_expenditure_year.listIterator();
        for (li4 = economy_list_Public_budget_expenditure_year.listIterator(); li4.hasNext();) {
            li4.next();
        }
        for (int i = economy_list_Public_budget_expenditure_year.size() - 1; i >= 0; i--) {
            economy_list_Public_budget_expenditure_year_reverse.add(economy_list_Public_budget_expenditure_year.get(i));
        }

        //固定资产投资总额-月度统计
        List<NameDataModel> nameDataModel_fixed_assets_list = new ArrayList<NameDataModel>();
        List<NameValueModel> economy_list_fixed_assets_year_reverse = new ArrayList<NameValueModel>();
        List<NameValueModel> economy_list_fixed_assets_year = new ArrayList<NameValueModel>();
        List<MACRO_ECONOMY> economy_list_fixed_assets_year_line = new ArrayList<MACRO_ECONOMY>();
        for(int y=s;y<s+12;y++){
            NameValueModel nameValueModel_fixed_assets_year_line = NameValueModel.builder().build();
            MACRO_ECONOMY mACRO_ECONOMY = new MACRO_ECONOMY();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -y);
            String date = new SimpleDateFormat("yyyy-MM").format(cal.getTime());
            year = date.split("-")[0];
            month = date.split("-")[1];
            dete = year+"-"+month;
            if ("0".equals(month.substring(0,1))) {
                month = month.split("0")[1];
            }
            mACRO_ECONOMY.setCreateYear(year);
            mACRO_ECONOMY.setCreateMonth(month);
            mACRO_ECONOMY.setIndexType("11");//固定资产投资总额
            mACRO_ECONOMY.setCalculationType("1");
            economy_list_fixed_assets_year_line = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY);
            nameValueModel_fixed_assets_year_line.setName(dete);
            if(economy_list_fixed_assets_year_line.size()==0){
                nameValueModel_fixed_assets_year_line.setValue("0");
            }else {
                String num = economy_list_fixed_assets_year_line.get(0).getValue();
                if(num.length()>=9)num = num.substring( 0, num.length()-8)+"."+num.substring( num.length()-8,num.length()-6);
                nameValueModel_fixed_assets_year_line.setValue(num);
            }
            economy_list_fixed_assets_year.add(nameValueModel_fixed_assets_year_line);
        }
        String fixed_assets_total_add_point_unit = economy_list_fixed_assets_year.get(0).getValue()+"亿元";
        //economy_list_fixed_assets_year   反向
        ListIterator<NameValueModel> li5 = economy_list_fixed_assets_year.listIterator();
        for (li5 = economy_list_fixed_assets_year.listIterator(); li5.hasNext();) {
            li5.next();
        }
        for (int i = economy_list_fixed_assets_year.size() - 1; i >= 0; i--) {
            economy_list_fixed_assets_year_reverse.add(economy_list_fixed_assets_year.get(i));
        }
        List<MACRO_ECONOMY> economy_list_fixed_assets_year_rate = new ArrayList<MACRO_ECONOMY>();
        String cur_year = economy_list_fixed_assets_year.get(0).getName();
        String cur_month = "";
        MACRO_ECONOMY mACRO_ECONOMY_rate = new MACRO_ECONOMY();
        mACRO_ECONOMY_rate.setCreateYear(cur_year.split("-")[0]);
        if ("0".equals(economy_list_fixed_assets_year.get(0).getName().split("-")[1].substring(0,1))) {
            cur_month = economy_list_fixed_assets_year.get(0).getName().split("-")[1].split("0")[1];
        }
        mACRO_ECONOMY_rate.setCreateMonth(cur_month);
        mACRO_ECONOMY_rate.setIndexType("11");//固定资产投资总额
        mACRO_ECONOMY_rate.setCalculationType("0");
        economy_list_fixed_assets_year_rate = mACRO_ECONOMYMapper.selectObjByTime(mACRO_ECONOMY_rate);

        //宏观经济--进出口总额
        MacroEconomyFirst macroEconomyFirst = new MacroEconomyFirst();
        macroEconomyFirst.setName("宏观经济");
        List<NameValueModel> nameValueModel_first_list = new ArrayList<NameValueModel>();
        /*NameValueModel nameValueModel_GDP = new NameValueModel();
        nameValueModel_GDP.setName("年度GDP总额");
        nameValueModel_GDP.setValue(region_GDP_add_point_unit);
        nameValueModel_first_list.add(nameValueModel_GDP);*/
        nameValueModel_first_list.add(NameValueModel.builder().name("年度GDP总额").value(region_GDP_add_point_unit).build());
        /*NameValueModel nameValueModel_GDP_rate = new NameValueModel();
        nameValueModel_GDP_rate.setName("GDP增长率");
        nameValueModel_GDP_rate.setValue(region_GDP_rate);
        nameValueModel_first_list.add(nameValueModel_GDP_rate);*/
        nameValueModel_first_list.add(NameValueModel.builder().name("GDP增长率").value(region_GDP_rate).build());
        macroEconomyFirst.setGeneralSituation(nameValueModel_first_list);
        NameValueDataModel nameValueDataModel_macroEconomy = new NameValueDataModel();
        nameValueDataModel_macroEconomy.setName("进出口总额");
        nameValueDataModel_macroEconomy.setValue(region_Ie_total_add_point_unit);
        nameValueDataModel_macroEconomy.setData(economy_list_ie_month_reverse);
        macroEconomyFirst.setData(nameValueDataModel_macroEconomy);
        macroEconomyDetail.setMacroEconomy(macroEconomyFirst);
        //宏观经济--城镇居民人均可支配收入
        NameDataModel nameDataModel_control = new NameDataModel();
        nameDataModel_control.setName("消费");
        NameValueDataModel nameValueDataModel_control = new NameValueDataModel();
        nameValueDataModel_control.setName("城镇居民人均可支配收入增长率");
        String control_vaule;
        if(economy_list_control_year_rate.isEmpty()){
            control_vaule = "0";
        }else{
            control_vaule = economy_list_control_year_rate.get(0).getValue();
        }
        nameValueDataModel_control.setValue(control_vaule+"%");
        NameDataModel nameDataModel_control_line = new NameDataModel();
        nameDataModel_control_line.setName("城镇居民人均可支配收入趋势");
        nameValueDataModel_control.setData(economy_list_control_year);
        nameDataModel_control.setData(nameValueDataModel_control);
        macroEconomyDetail.setDisposable_income(nameDataModel_control);
        //宏观经济--一般性公共预算
        NameDataModel nameDataModel_Public_budget = new NameDataModel();
        nameDataModel_Public_budget.setName("公共预算");
        List<NameValueDataModel> nameValueDataModel_list = new ArrayList<NameValueDataModel>();
        NameValueDataModel nameValueDataModel_revenue = new NameValueDataModel();
        nameValueDataModel_revenue.setName("一般性公共预算收入");
        nameValueDataModel_revenue.setValue(publicBudget_reverse_Lately);
        nameValueDataModel_revenue.setData(economy_list_Public_budget_revenue_year_reverse);
        nameValueDataModel_list.add(nameValueDataModel_revenue);
        NameValueDataModel nameValueDataModel_expenditure = new NameValueDataModel();
        nameValueDataModel_expenditure.setName("一般性公共预算支出");
        nameValueDataModel_expenditure.setValue(publicBudget_expenditure_Lately);
        nameValueDataModel_expenditure.setData(economy_list_Public_budget_expenditure_year_reverse);
        nameValueDataModel_list.add(nameValueDataModel_expenditure);
        nameDataModel_Public_budget.setData(nameValueDataModel_list);
        macroEconomyDetail.setPublic_budget(nameDataModel_Public_budget);
        //宏观经济--固定资产投资总额
        MacroEconomyFirst macroEconomy_fixed = new MacroEconomyFirst();
        macroEconomy_fixed.setName("固定资产投资");
        List<NameValueModel> nameValueModel_fixed_list = new ArrayList<NameValueModel>();
        /*NameValueModel nameValueModel_YearTotal = new NameValueModel();
        nameValueModel_YearTotal.setName("年度累计");
        nameValueModel_YearTotal.setValue(fixed_assets_total_add_point_unit);
        nameValueModel_fixed_list.add(nameValueModel_YearTotal);*/
        nameValueModel_fixed_list.add(NameValueModel.builder().name("年度累计").value(fixed_assets_total_add_point_unit).build());
        /*NameValueModel nameValueModel_fixed_rate = new NameValueModel();
        nameValueModel_fixed_rate.setName("增速");
        nameValueModel_fixed_rate.setValue(economy_list_fixed_assets_year_rate.get(0).getValue()+"%");
        nameValueModel_fixed_list.add(nameValueModel_fixed_rate);*/
        nameValueModel_fixed_list.add(NameValueModel.builder().name("增速").value(economy_list_fixed_assets_year_rate.get(0).getValue()+"%").build());
        macroEconomy_fixed.setGeneralSituation(nameValueModel_fixed_list);
        macroEconomy_fixed.setData(economy_list_fixed_assets_year_reverse);
        macroEconomyDetail.setFixed_assets(macroEconomy_fixed);

        return macroEconomyDetail;

    }
}
