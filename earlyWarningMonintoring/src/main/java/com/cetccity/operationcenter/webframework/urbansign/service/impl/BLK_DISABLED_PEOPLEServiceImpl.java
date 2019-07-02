package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TimeValueModel;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.urbansign.dao.BLK_DISABLED_PEOPLEMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.BLK_DISABLED_PEOPLE;
import com.cetccity.operationcenter.webframework.urbansign.service.BLK_DISABLED_PEOPLEService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BLK_DISABLED_PEOPLEServiceImpl implements BLK_DISABLED_PEOPLEService {

    @Autowired
    BLK_DISABLED_PEOPLEMapper bLK_DISABLED_PEOPLEMapper;

    public NameDataModel getRightNight(String street, String community){
        NameDataModel nameDataModel = new NameDataModel();
        String street_code = null;
        String community_code =null;
        long num;
        if(StringUtils.isNotEmpty(street))
            street_code = LoadMyUtil.getPropertiesVauleOfKey("street.properties", street).split(",")[0];
        if(community!=null)
            community_code = LoadMyUtil.getPropertiesVauleOfKey("street.properties", community);
        List<TimeValueModel> timeValueModel_list = new ArrayList<TimeValueModel>();

        for(int i = 8;i>=0;i--) {
            TimeValueModel timeValueModel = new TimeValueModel();
            String year = LoadMyUtil.getMyTime("YEAR",-i);
            BLK_DISABLED_PEOPLE bLK_DISABLED_PEOPLE = new BLK_DISABLED_PEOPLE();
            bLK_DISABLED_PEOPLE.setCJSJ(year);
            bLK_DISABLED_PEOPLE.setJDDM(street_code);
            bLK_DISABLED_PEOPLE.setSQDM(community_code);
            num = bLK_DISABLED_PEOPLEMapper.getCount(bLK_DISABLED_PEOPLE);
            timeValueModel.setTime(year);
            timeValueModel.setValue(String.valueOf(num));
            timeValueModel_list.add(timeValueModel);
        }
        nameDataModel.setName("残疾人群数量");
        nameDataModel.setData(timeValueModel_list);
        return nameDataModel;
    }

}
