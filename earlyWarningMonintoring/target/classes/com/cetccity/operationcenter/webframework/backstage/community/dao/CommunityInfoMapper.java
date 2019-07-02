package com.cetccity.operationcenter.webframework.backstage.community.dao;

import com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.backstage.community.dao
 * @Project: futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2019/5/16 11:21
 * @Updater: huangzezhou
 * @Update_Date: 2019/5/16 11:21
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Mapper
public interface CommunityInfoMapper {

    /**
     * 功能描述: <br>
     * 〈查询所有在库社区列表〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo>
     * @Author:yhy
     * @Date: 2019/5/16 11:29
     */
    List<CommunityInfo> queryAll();

    /**
     * 功能描述: <br>
     * 〈查询所有在库街道列表〉
     *
     * @param
     * @return:java.util.List<com.cetccity.operationcenter.webframework.backstage.community.entity.CommunityInfo>
     * @Author:yhy
     * @Date: 2019/5/16 11:29
     */
    List<CommunityInfo> queryStreets();

    String queryStreetCodeByName(@Param("streetName") String streetName);

    long countAll();

}
