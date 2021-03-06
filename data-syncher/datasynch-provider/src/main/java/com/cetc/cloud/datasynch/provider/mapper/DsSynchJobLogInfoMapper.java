package com.cetc.cloud.datasynch.provider.mapper;

import com.cetc.cloud.datasynch.api.model.DsSynchJobLogInfoModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description：同步任务执行日志查询Dao层定义
 * Created by luolinjie on 2018/10/10.
 */
@Mapper
public interface DsSynchJobLogInfoMapper {

    DsSynchJobLogInfoModel queryLatestLogInfoByJobId(int jobId);

    int add(DsSynchJobLogInfoModel synchJobLogInfoModel);

    DsSynchJobLogInfoModel queryLatestPageParamsByJobID(int jobId);

    int deleteByJobId(int jobId);
}
