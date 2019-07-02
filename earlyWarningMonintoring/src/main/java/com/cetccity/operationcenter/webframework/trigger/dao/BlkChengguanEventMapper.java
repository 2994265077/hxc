package com.cetccity.operationcenter.webframework.trigger.dao;

import com.cetccity.operationcenter.webframework.trigger.entity.BlkChengguanEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.cetccity.operationcenter.webframework.trigger.dao
 * @Project: futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2019/5/9 10:22
 * @Updater: huangzezhou
 * @Update_Date: 2019/5/9 10:22
 * @Update_Description: huangzezhou 补充
 * @Description:
 **/
@Mapper
public interface BlkChengguanEventMapper {

    List<BlkChengguanEvent> queryAllByCondition();

    List<BlkChengguanEvent> queryAllByObjectId(@Param("object_id") String objectId);

}
