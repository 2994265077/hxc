package com.cetccity.operationcenter.webframework.backstage.video.dao;

import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DistrictVideoClass;
import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DistrictVideoClassNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:37 2019-05-08
 * Updater:     heliangming
 * Update_Date：18:37 2019-05-08
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface DistrictVideoClassMapper {

    @Select("select SEQ_DISTRICT_VIDEO_CLASS.nextval from dual")
    Integer maxId();

    /**
     * 查询视屏关联标签
     * @return
     */
    List<DistrictVideoClassNode> findVideoRelationTagList(DistrictVideoClassNode districtVideoClassNode);

    /**
     * 给视屏添加标签
     * @param districtVideoClass model
     * @return
     */
    int videoAddTag(DistrictVideoClass districtVideoClass);

    /**
     * 根据标签id,根据视屏编号删除视屏
     * @param districtVideoClass model
     * @return
     */
    int videoRemoveTag(DistrictVideoClass districtVideoClass);

    /**
     * 根据标签id查詢视屏
     * @param districtVideoClass
     * @return
     */
    List<DistrictVideoClass> findVideoTagList(DistrictVideoClass districtVideoClass);

    List<DistrictVideoClass> loadMapVideoRelationByClassId(Map map);
}
