/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: CommunityService
 * Author:   YHY
 * Date:     2019/5/16 11:29
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.community.service;

import com.cetccity.operationcenter.webframework.backstage.community.dao.CommunityInfoMapper;
import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo;
import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.RegionNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.StreetNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.cetccity.operationcenter.webframework.backstage.community.util.CommunityTreeUtil.communityInfoTree;

/**
 * 〈一句话功能简述〉<br> 
 * 〈街道、社区列表查询〉
 *
 * @author yhy
 * @create 2019/5/16
 * @since 1.0.0
 */
@Service
public class CommunityInfoService {
    @Autowired
    private CommunityInfoMapper communityInfoMapper;

    /**
     * 功能描述: <br>
     * 〈查询所有社区〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo>
     * @Author:yhy
     * @Date: 2019/5/16 11:31
     */
    public List<CommunityInfo> queryCommunities() {
        return communityInfoMapper.queryAll();
    }

    /**
     * 功能描述: <br>
     * 〈查询所有街道〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo>
     * @Author:yhy
     * @Date: 2019/5/16 11:31
     */
    public List<CommunityInfo> queryStreets() {
        return communityInfoMapper.queryStreets();
    }


    public List<RegionNode> communityTree() {
        List<CommunityInfo> communityInfos = communityInfoMapper.queryAll();
        return communityInfoTree(communityInfos);
    }

    public String streetCodeByName(String streetName) {
        String streetCode = null;
        if (StringUtils.isNotBlank(streetName)) {
            streetCode = communityInfoMapper.queryStreetCodeByName(streetName);
        }
        return streetCode;
    }

}