package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.hiddendanger.dao.ZHUJIAN_BIANPOMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.ZHUJIAN_BIANPO;
import com.cetccity.operationcenter.webframework.hiddendanger.service.ZHUJIAN_BIANPOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.hiddendanger.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 17:09 2019-07-03
 * Updater:     heliangming
 * Update_Date：17:09 2019-07-03
 * 更新描述:    heliangming 补充
 **/
@Service
public class ZHUJIAN_BIANPOServiceImpl implements ZHUJIAN_BIANPOService {

    @Autowired
    ZHUJIAN_BIANPOMapper zHUJIAN_BIANPOMapper;

    public ZHUJIAN_BIANPO getObject(String id){
        return zHUJIAN_BIANPOMapper.getObject(id);
    }
}
