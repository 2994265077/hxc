package com.cetccity.operationcenter.webframework.urbansign.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Package: com.cetccity.operationcenter.webframework.urbansign.dao.entity
 * @Project: futian
 * @Creator: huangzezhou
 * @Create_Date: 2019/6/3 9:51
 * @Updater: huangzezhou
 * @Update_Date: 2019/6/3 9:51
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MacroEconomyDimensions {
    // 深圳各区地区GDP = 深圳各区地区生产总值     福田 指标 历史数据   各区 指标 历史数据
    REGION_PRODUCT                      ("福田当年地区生产总值趋势图",             "福田地区生产总值", "region_product"),
    CITY_PRODUCT                        ("深圳各季度各区生产总值对比",             "深圳各区地区生产总值", "city_product"),
    REGION_FIXED_INVESTMENTS            ("福田当年固定资产投资情况",               "福田当年固定资产投资情况", "region_fixed_investments"),
    CITY_FIXED_INVESTMENTS              ("深圳各季度各区固定资产投资情况",          "深圳各区固定资产投资", "city_fixed_investments"),
    REGION_RETAIL                       ("福田当年社消零总额情况",                 "福田当年社消零总额情况", "region_retail"),
    CITY_RETAIL                         ("深圳各区各季度社消零总额对比",            "深圳各区社消零总额", "city_retail"),
    REGION_EXPORT                       ("福田当年出口总额趋势图",                 "出口总额", "region_export"),
    REGION_IMPORT                       ("福田当年进口总额趋势图",                 "进口总额", "region_import"),
    CITY_INVESTMENT                     ("深圳各区R&D经费投入比较",                "深圳各区R&D经费投入比较", "city_investment"),
    CITY_INVESTMENT_SP                  ("深圳各区R&D经费及比重",                  "深圳各区R&D经费及比重", "city_investment_sp"),
    REGION_INVESTMENT_LEVEL             ("福田历年R&D投入强度情况",                 "福田历年R&D投入强度情况", "region_investment_level"),
    INVESTMENT_CITY                     ("深圳各区R&D人员对比",                    "深圳各区R&D人员", "investment_city"),
    CITY_INVESTMENT_PATENTS             ("深圳各区专利申请量对比",                  "深圳各区专利申请量", "city_investment_patents"),
    REGION_INVESTMENT_STAFF             ("福田历年R&D人数变化情况",                 "福田区历年R&D人数变化情况", "region_investment_staff"),
    REGION_INVESTMENT_PATENTS           ("福田历年专利申请量变化情况",               "福田区历年专利申请量变化情况", "region_investment_patents"),
//    REGION_INDUSTRY_INCR                ("福田各季度各行业增加值情况",               "", "region_industry_incr"),
    REGION_INDUSTRY_INCR                ("独角兽企业利润行业分布",               "独角兽企业利润行业分布", "region_unicorn_company_profit_number"),
    REGION_NON_PROFIT_SERVICE_INCR      ("福田历年各季度非营利性服务业增加值情况",     "非营利性服务业增加值及增速", "region_non_profit_service_incr"),
    REGION_FARMING_INCR                 ("福田历年各季度农业牧渔业增加值情况",         "农林牧渔业增加值及增速", "region_farming_incr"),
    REGION_INDUSTRIAL_ENGINEERING_INCR  ("福田历年各季度规上工业增加值情况",           "规上工业增加值情况及增速", "region_industrial_engineering_incr"),
    REGION_UNICORN_COMPANY_NUMBER       ("福田独角兽企业数量行业分布",               "独角兽企业数量行业分布", "region_unicorn_company_number"),
    FINANCIAL_REVENUE_TREND             ("金融业累积营收趋势",                      "金融业累计营收增加值及增速", "financial_revenue_trend"),
    REGION_RETAIL_INCR                  ("福田历年各季度批发与零售业增加值情况",       "批发和零售业增加值及增速", "region_retail_incr"),
    REGION_CONSTRUCTION_INDUSTRY_INCR   ("福田历年各季度建筑业增加值情况",             "建筑业增加值及增速", "region_construction_industry_incr"),
    REGION_UNICORN_COMPANY_PROFIT       ("福田独角兽企业利润行业分布",               "独角兽企业利润行业分布", "region_unicorn_company_profit"),
    REGION_ACCOMMODATION_CATERING_INCR  ("福田历年各季度住宿与餐饮业增加值情况",       "住宿和餐饮业增加值及增速", "region_accommodation_catering_incr"),
    REGION_TRANSPORTATION_INCR          ("福田历年各季度交通运输仓储与邮政业增加值情况", "交通运输、仓储和邮政业增加值及增速", "region_transportation_incr"),
    REGION_FOR_PROFIT_SERVICES_INCR     ("福田历年各季度营利性服务业增加值情况",        "营利性服务业增加值及增速", "region_for_profit_services_incr"),
    REGION_REAL_ESTATE_INDUSTRY_INCR    ("福田历年各季度房地产业增加值情况",           "房地产增加值及增速", "region_real_estate_industry_incr"),
    SPECIALTY_INDUSTRY_INCR             ("2018第二季度特色产业增加值及增速",          "特色产业增加值及增速", "specialty_industry_incr"),
    HEADQUARTER_ENTERPRISE_INCR         ("总部企业增加值及增速",                     "总部企业增加值及增速", "headquarter_enterprise_incr"),
    FINANCIAL_INCR                      ("金融业增加值及增速",                       "金融业增加值及增速", "financial_incr"),
    SPECIALIZED_SERVICES_INCR           ("专门专业服务业增加值及增速（季度）",          "专门专业服务业增加值及增速", "specialized_services_incr"),
    STRATEGIC_EMERGING_INDUSTRY_INCR    ("战略性新兴产业增加值及增速（历年）",          "战略新兴产业增加值及增速", "strategic_emerging_industry_incr"),
    WHOLESALE_RETAIL_INCR               ("批发和零售业增加值及增速（季度）",            "批发和零售业增加值及增速(季度)", "wholesale_retail_incr"),
    ACCOMMODATION_CATERING_INCR         ("住宿和餐饮业增加值及增速（季度）",            "住宿和餐饮业增加值及增速(季度)", "accommodation_catering_incr");

    public static Map<String, MacroEconomyDimensions> cache = Arrays.stream(values())
            .collect(Collectors.toMap(MacroEconomyDimensions::getCode, obj -> obj));

    /**
     * 图表标题
     */
    private String title;
    /**
     * 数据库查询条件
     */
    private String query;
    /**
     * code
     */
    private String code;

    public static Optional<MacroEconomyDimensions> getByCode(String code) {
        MacroEconomyDimensions macroEconomyDimensions = cache.get(code);
        return Optional.ofNullable(macroEconomyDimensions);
    }

}
