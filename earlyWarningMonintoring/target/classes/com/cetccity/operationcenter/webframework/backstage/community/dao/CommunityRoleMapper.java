/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: CommunityRoleMapper
 * Author:   YHY
 * Date:     2019/5/28 16:33
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.backstage.community.dao;

import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo;
import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.RegionNode;
import com.cetccity.operationcenter.webframework.backstage.community.entity.StreetNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/5/28
 * @since 1.0.0
 */
@Mapper
public interface CommunityRoleMapper {

    long save(@Param("role_id") String roleId, @Param("community_id") String communityId);

    long delete(@Param("role_id") String roleId);

    List<String> selectCommunityIdsByRole(@Param("role_id") String roleId);

    List<CommunityNode> communityNamesByRole(@Param("role_ids") List<String> roleIds);

    List<StreetNode> streetNamesByRole(@Param("role_ids") List<String> roleIds);

    List<RegionNode> regionNamesByRole(@Param("role_ids") List<String> roleIds);

    List<CommunityInfo> communityTreeByRole(@Param("role_ids") List<String> roleIds);

    long countCommunityByRole(@Param("role_ids") List<String> roleIds);

}