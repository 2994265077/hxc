package com.cetccity.operationcenter.webframework.web.model.citySign;

public class MacroEconomy<T> {

    //地区生产总值
    private String region_GDP;
    //趋势
    private String trend_GDP;
    //人均可支配收入
    private String per_capita_domination;
    //一般公共预算增长率
    private String public_budget_growth_rate;
    //地区生产总值按月
    private T economy_month_Line;
    //地区生产总值按年
    private T economy_year_Line;

    public String getRegion_GDP() {
        return region_GDP;
    }

    public void setRegion_GDP(String region_GDP) {
        this.region_GDP = region_GDP;
    }

    public String getTrend_GDP() {
        return trend_GDP;
    }

    public void setTrend_GDP(String trend_GDP) {
        this.trend_GDP = trend_GDP;
    }

    public String getPer_capita_domination() {
        return per_capita_domination;
    }

    public void setPer_capita_domination(String per_capita_domination) {
        this.per_capita_domination = per_capita_domination;
    }

    public String getPublic_budget_growth_rate() {
        return public_budget_growth_rate;
    }

    public void setPublic_budget_growth_rate(String public_budget_growth_rate) {
        this.public_budget_growth_rate = public_budget_growth_rate;
    }

    public T getEconomy_month_Line() {
        return economy_month_Line;
    }

    public void setEconomy_month_Line(T economy_month_Line) {
        this.economy_month_Line = economy_month_Line;
    }

    public T getEconomy_year_Line() {
        return economy_year_Line;
    }

    public void setEconomy_year_Line(T economy_year_Line) {
        this.economy_year_Line = economy_year_Line;
    }
}
