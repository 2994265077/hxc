package com.cetccity.operationcenter.webframework.alarmcenter.dao;

import com.cetccity.operationcenter.webframework.alarmcenter.dao.entity.LAB_CITYMANAGE_EVENT;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.alarmcenter.dao
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 14:33 2019-07-10
 * Updater:     heliangming
 * Update_Date：14:33 2019-07-10
 * 更新描述:    heliangming 补充
 **/
@Mapper
public interface LAB_CITYMANAGE_EVENTMapper {

    @Select("select SEQ_LAB_CITYMANAGE_EVENT.nextVal from dual")
    Long getIncrementId();

    @Insert("insert into LAB_CITYMANAGE_EVENT(OBJECT_ID,CAMERA_ID,EVENT_TYPE,IMAGE_URL,VIDEO_URL,LOG_TIME,YJJC_CREATE_TIME,YJJC_UPDATE_TIME) "
            + "values (#{objectId,jdbcType=BIGINT},#{cameraId,jdbcType=VARCHAR},#{eventType,jdbcType=INTEGER},#{imageUrl,jdbcType=VARCHAR},#{videoUrl,jdbcType=VARCHAR},#{logTime,jdbcType=VARCHAR},sysdate,sysdate)")
    int receiveLabCityManageEvent(LAB_CITYMANAGE_EVENT labCitymanageEvent);

    List<LAB_CITYMANAGE_EVENT> getList(@Param("objectId") String objectId);
}
