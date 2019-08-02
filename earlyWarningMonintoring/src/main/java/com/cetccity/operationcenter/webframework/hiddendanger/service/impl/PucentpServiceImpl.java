/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: PucentpServiceImpl
 * Author:   YHY
 * Date:     2019/8/1 16:14
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.backstage.community.service.CommunityInfoService;
import com.cetccity.operationcenter.webframework.core.chart.engine.model.PieModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.PucentpMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.service.PucentpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/8/1
 * @since 1.0.0
 */
@Service
public class PucentpServiceImpl implements PucentpService {

    @Autowired
    private CommunityInfoService communityInfoService;

    @Autowired
    private PucentpMapper pucentpMapper;

    @Override
    public PieModel pieByStreet(String streetName) {
        String streetCode = communityInfoService.streetCodeByName(streetName);
        List<NameValueTypeModel> nameValueModels = pucentpMapper.pieByStreet(streetCode);
        int sum = nameValueModels.stream().mapToInt(obj -> (int) obj.getValue()).sum();
        return new PieModel(sum, nameValueModels);
    }

    @Override
    public PieModel pieByCommunity(String streetName) {
        String streetCode = communityInfoService.streetCodeByName(streetName);
        List<NameValueTypeModel> nameValueModels = pucentpMapper.pieByCommunity(streetCode);
        int sum = nameValueModels.stream().mapToInt(obj -> (int) obj.getValue()).sum();
        return new PieModel(sum, nameValueModels);
    }
}