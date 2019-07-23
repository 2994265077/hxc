package com.cetc.cloud.datasynch.provider.service.impl;

import com.cetc.cloud.datasynch.api.model.DsScheduleModel;
import com.cetc.cloud.datasynch.provider.common.CommonInstance;
import com.cetc.cloud.datasynch.provider.mapper.DsScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：数据库方式的Job服务管理(数据库修改)
 * Created by luolinjie on 2018/10/10.
 */
@Service("scheduleService")
public class ScheduleService {

    @Autowired
    DsScheduleMapper dsScheduleMapper;

    public int addScheduleInstance(DsScheduleModel dsScheduleModel) {
        String source = dsScheduleModel.getSource();
        int pageSize = dsScheduleModel.getPageSize();
        String tableName = dsScheduleModel.getTargetTableName();
        String scheduleExpression = dsScheduleModel.getCronExpression();
        int isPagingQuery = dsScheduleModel.getIsPagingQuery();
        int srcDs = dsScheduleModel.getSrcDs();

        int connType = dsScheduleModel.getConnType();
        if (connType==CommonInstance.ACCESS_TYPE_DB) {
            if (null == source || pageSize < 1 || null == tableName || null == scheduleExpression ||
                    isPagingQuery < 0 || isPagingQuery > 1 || srcDs < 0) {
                return -1;
            }
        }else if (connType==CommonInstance.ACCESS_TYPE_INTERFACE){
            if (null == source || pageSize < 1 || null == tableName || null == scheduleExpression ||
                    isPagingQuery < 0 || isPagingQuery > 1 ) {
                return -1;
            }
        }
        int jobId = dsScheduleMapper.addScheduleInstance(dsScheduleModel);
        if (jobId > 0) {
            return dsScheduleModel.getId();
        } else {
            return -1;
        }
    }

    public int deleteScheduleByJobId(int jobId) {
        return dsScheduleMapper.deleteJobByJobId(jobId);
    }

    public List<DsScheduleModel> queryScheduleJobList() {
        return dsScheduleMapper.queryScheduleJobList();
    }

    public int updateCronByJobId(int jobId, String cron) {
        return dsScheduleMapper.updateCronByJobId(jobId, cron);
    }

    //修改任务运行状态  0-无任务   1-有任务
    public int alterJobStatusByJobId(int jobId, int statusToChange) {
        //获取运行状态
        int status = dsScheduleMapper.getStatusByJobId(jobId);
        if (statusToChange == status) {
            return 1;
        }
        //修改运行状态
        if (statusToChange == CommonInstance.JOB_ENABLED || statusToChange == CommonInstance.JOB_DISABLED) {
            return dsScheduleMapper.updateEnableStatusByJobId(jobId, statusToChange);
        } else {
            return -1;
        }
    }

    public int enableStatusByJobId(int jobId) {
        //获取运行状态
        int status = dsScheduleMapper.getStatusByJobId(jobId);
        if (CommonInstance.JOB_ENABLED == status) {
            return 2;
        } else {
            return dsScheduleMapper.updateEnableStatusByJobId(jobId, CommonInstance.JOB_ENABLED);
        }
    }


    public DsScheduleModel queryModelByJobId(int jobId) {
        return dsScheduleMapper.queryModelByJobId(jobId);
    }

    public DsScheduleModel queryModelByTableName(String tableName) {
        return dsScheduleMapper.queryModelByTableName(tableName);
    }

    public List<Integer> queryEnabledJobIdList() {
        List<DsScheduleModel> dsScheduleModels = queryScheduleJobList();
        ArrayList<Integer> jobList = new ArrayList<Integer>();
        for (DsScheduleModel model : dsScheduleModels) {
            int isEnabled = model.getIsEnabled();
            if (isEnabled == 1) {
                jobList.add(model.getId());
            }
        }
        return jobList;
    }
}
