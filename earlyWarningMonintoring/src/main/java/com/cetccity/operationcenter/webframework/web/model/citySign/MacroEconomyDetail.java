package com.cetccity.operationcenter.webframework.web.model.citySign;

public class MacroEconomyDetail<T> {

    private T macroEconomy;//宏观经济

    private T disposable_income;//城镇居民人均可支配收入

    private T public_budget;//一般性公共预算

    private T fixed_assets;//固定资产投资总额

    public T getMacroEconomy() {
        return macroEconomy;
    }

    public void setMacroEconomy(T macroEconomy) {
        this.macroEconomy = macroEconomy;
    }

    public T getDisposable_income() {
        return disposable_income;
    }

    public void setDisposable_income(T disposable_income) {
        this.disposable_income = disposable_income;
    }

    public T getPublic_budget() {
        return public_budget;
    }

    public void setPublic_budget(T public_budget) {
        this.public_budget = public_budget;
    }

    public T getFixed_assets() {
        return fixed_assets;
    }

    public void setFixed_assets(T fixed_assets) {
        this.fixed_assets = fixed_assets;
    }
}
