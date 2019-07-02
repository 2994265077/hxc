package com.cetccity.operationcenter.webframework.web.model.citySign;

public class PublicBudget<T> {
    //公共预算
    private String name;
    //一般性公共预算收入
    private T revenue;
    //一般性公共预算收入
    private T revenue_line;

    //一般性公共预算支出
    private T expenditure;
    //一般性公共预算支出
    private T expenditure_line;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getRevenue() {
        return revenue;
    }

    public void setRevenue(T revenue) {
        this.revenue = revenue;
    }

    public T getRevenue_line() {
        return revenue_line;
    }

    public void setRevenue_line(T revenue_line) {
        this.revenue_line = revenue_line;
    }

    public T getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(T expenditure) {
        this.expenditure = expenditure;
    }

    public T getExpenditure_line() {
        return expenditure_line;
    }

    public void setExpenditure_line(T expenditure_line) {
        this.expenditure_line = expenditure_line;
    }
}
