package com.cetc.cloud.datasynch.provider.service.impl;

import com.cetc.cloud.datasynch.api.model.DsSynchJobLogInfoModel;
import com.cetc.cloud.datasynch.provider.mapper.DsSynchJobLogInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库方式同步状态记录管理
 * Created by llj on 2018/10/10.
 */
@Service("synchJobLogInfoService")
public class SynchJobLogInfoService {

    @Autowired
    DsSynchJobLogInfoMapper synchJobLogInfoMapper;
    /**
     * 根据jobID查询最近一次成功请求的分页参数
     */
    public DsSynchJobLogInfoModel queryLatestInfoByJobId(int jobId) {
        return synchJobLogInfoMapper.queryLatestLogInfoByJobId(jobId);
    }
    /**
     * 查询最近一次分页参数，并以【pageNum,pageSize】返回
     * @param jobId
     * @return
     */
    public List<Integer> queryLatestPageParamsByJobId(int jobId) {
        DsSynchJobLogInfoModel synchJobLogInfoModel = synchJobLogInfoMapper.queryLatestPageParamsByJobID(jobId);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(synchJobLogInfoModel.getLastQueryPageNum());
        list.add(synchJobLogInfoModel.getCurrentPageSize());
        return list;
    }
    /**
     * 新增一条同步日志
     * @param synchJobLogInfoModel
     * @return
     */
    public int add(DsSynchJobLogInfoModel synchJobLogInfoModel) {
        return synchJobLogInfoMapper.add(synchJobLogInfoModel);
    }

    public int deleteByJobId(int jobId) {
        return synchJobLogInfoMapper.deleteByJobId(jobId);
    }
}
