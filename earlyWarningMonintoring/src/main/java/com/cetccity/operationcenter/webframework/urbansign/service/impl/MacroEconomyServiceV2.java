/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MacroEconomyService
 * Author:   YHY
 * Date:     2019/6/3 9:41
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.urbansign.dao.MacroEconomyMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.MacroEconomyDimensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/3
 * @since 1.0.0
 */
@Service
public class MacroEconomyServiceV2 {

    @Autowired
    private MacroEconomyMapper macroEconomyMapper;

    public Map<String, Object> queryByCode(String code) {
        MacroEconomyDimensions macroEconomyDimensions = MacroEconomyDimensions.getByCode(code)
                .orElseThrow(() -> CetcCommonException.defaultException("查询失败，不存在此类型"));
        String value = macroEconomyMapper.queryValueByName(macroEconomyDimensions.getQuery());
        Map map = JSONObject.parseObject(value);
        if (Objects.isNull(map)) {
            map = new HashMap();
        }
        map.put("table_name", macroEconomyDimensions.getTitle());
        return map;
    }

}