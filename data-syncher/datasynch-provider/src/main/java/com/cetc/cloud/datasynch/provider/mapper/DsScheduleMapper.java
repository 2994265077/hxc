package com.cetc.cloud.datasynch.provider.mapper;

import com.cetc.cloud.datasynch.api.model.DsScheduleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description：同步任务管理DAO层定义
 * Created by luolinjie on 2018/10/10.
 */
@Mapper
public interface DsScheduleMapper {

    List<DsScheduleModel> queryScheduleJobList();

    int deleteJobByJobId(@Param("jobId")int jobId);

    int updateCronByJobId(@Param("jobId")int jobId,@Param("cron")String cron);

    int updateEnableStatusByJobId(@Param("jobId")int jobId, @Param("isEnabled")int isEnabled);

    DsScheduleModel queryModelByJobId(@Param("jobId")int jobId);

    int addScheduleInstance( DsScheduleModel dsScheduleModel);

    int getStatusByJobId(@Param("jobId")int jobId);

    DsScheduleModel queryModelByTableName(@Param("tableName")String tableName);
}
