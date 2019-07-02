package com.cetccity.operationcenter.webframework.urbansign.controller;

import com.cetccity.operationcenter.webframework.urbansign.api.MacroEconomyApi;
import com.cetccity.operationcenter.webframework.web.model.citySign.MacroEconomyDetail;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.urbansign.service.MacroEconomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MacroEconomyController implements MacroEconomyApi {

    @Autowired
    MacroEconomyService macroEconomyService;

    public NameDataModel macroEconomy() {
        NameDataModel nameDataModel = macroEconomyService.macroEconomy();
        return nameDataModel;
    }

    public MacroEconomyDetail macroEconomyDetail() {
        MacroEconomyDetail macroEconomyDetail = macroEconomyService.macroEconomyDetail();
        return macroEconomyDetail;
    }

}
