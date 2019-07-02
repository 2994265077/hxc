package com.cetccity.operationcenter.webframework.web.service.impl;

import com.cetccity.operationcenter.webframework.urbansign.dao.HISTORY_FIREMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.HISTORY_FIRE;
import com.cetccity.operationcenter.webframework.web.service.HISTORY_FIREService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("hISTORY_FIREService")
public class HISTORY_FIREServiceImpl implements HISTORY_FIREService {

    @Autowired
    HISTORY_FIREMapper hISTORY_FIREMapper;

    public List<HISTORY_FIRE> getHISTORY_FIREOfMonth(){
        List<HISTORY_FIRE> hISTORY_FIRE_list = hISTORY_FIREMapper.getHISTORY_FIREOfMonth();
        return hISTORY_FIRE_list;
    }
}
