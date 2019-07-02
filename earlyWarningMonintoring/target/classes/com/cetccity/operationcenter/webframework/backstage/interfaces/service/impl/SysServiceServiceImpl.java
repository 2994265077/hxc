package com.cetccity.operationcenter.webframework.backstage.interfaces.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cetccity.operationcenter.webframework.backstage.interfaces.dao.SysServiceMapper;
import com.cetccity.operationcenter.webframework.backstage.interfaces.dao.SysServiceInfoMapper;
import com.cetccity.operationcenter.webframework.backstage.interfaces.dao.SysServiceParamMapper;
import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysService;
import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysServiceInfo;
import com.cetccity.operationcenter.webframework.backstage.interfaces.entity.SysServiceParam;
import com.cetccity.operationcenter.webframework.backstage.interfaces.service.SysServiceService;
import com.cetccity.operationcenter.webframework.backstage.interfaces.vo.SysServiceInfoVo;
import com.cetccity.operationcenter.webframework.backstage.interfaces.vo.SysServiceVo;
import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.UserMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.utils.UserInfoUtils;
import com.cetccity.operationcenter.webframework.web.util.CodeEnum;
import com.cetccity.operationcenter.webframework.web.util.CommonPo;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.cetccity.operationcenter.webframework.web.util.PageResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ZHUTONGYU
 * Description: 接口管理
 * 2019年5月8日
 *
 */
@Slf4j
@Service
public class SysServiceServiceImpl implements SysServiceService {
    @Autowired
    private SysServiceMapper sysServiceDao;

    @Autowired
    private SysServiceInfoMapper sysServiceInfoDao;

    @Autowired
    private SysServiceParamMapper sysServiceParamDao;
    
    @Autowired
    private UserInfoUtils userInfoUtils;

    @Override
    @Transactional
    public Long saveOrUpdateServiceInfo(SysServiceInfo sysServiceInfo) {
        Long serviceId = sysServiceInfo.getServiceId();
        if(serviceId==null){
            //获取序列id
            serviceId = sysServiceInfoDao.createServiceId();
            sysServiceInfo.setServiceId(serviceId);
            sysServiceInfo.setSort(serviceId);
            //计算资料完成百分比
            Float schedule = this.countSchedule(sysServiceInfo,null);
            sysServiceInfo.setSchedule(schedule);
            //保存
            sysServiceInfoDao.save(sysServiceInfo);
            return serviceId;
        }
        SysService sysService = sysServiceDao.findById(serviceId);
        //计算资料完成百分比
        Float schedule = this.countSchedule(sysServiceInfo,sysService);
        sysServiceInfo.setSchedule(schedule);
        sysServiceInfoDao.update(sysServiceInfo);
        return serviceId;
    }

    @Override
    @Transactional
    public SysServiceVo saveOrUpdateService(SysServiceVo sysServiceVo) {
        if(sysServiceVo.getServiceId()==null){
        	throw CetcCommonException.defaultException("接口概览还未保存！");
        }
        SysServiceInfo sysServiceInfo = sysServiceInfoDao.findById(sysServiceVo.getServiceId());
        if(sysServiceInfo==null){
        	throw CetcCommonException.defaultException("接口概览还未保存！");
        }
        
        SysService sysServiceOld = sysServiceDao.findById(sysServiceVo.getServiceId());
        if(sysServiceOld==null){
            sysServiceDao.save(sysServiceVo);
        }else{
            sysServiceDao.update(sysServiceVo);
        }
        //更新请求参数
        this.saveOrUpdateServiceParam(sysServiceVo,sysServiceVo.getParamList());
        //计算资料完成百分比
        Float schedule = this.countSchedule(sysServiceInfo,sysServiceVo);
        sysServiceInfo.setSchedule(schedule);
        sysServiceInfoDao.update(sysServiceInfo);
        //重新查询获得最新数据
        sysServiceOld = sysServiceDao.findById(sysServiceVo.getServiceId());
        BeanUtils.copyProperties(sysServiceOld,sysServiceVo);
        List<SysServiceParam> paramList = sysServiceParamDao.findByServiceId(sysServiceVo.getServiceId());
        sysServiceVo.setParamList(paramList);
        return sysServiceVo;
    }

    private void saveOrUpdateServiceParam(SysService service, List<SysServiceParam> paramList){
        if(paramList==null||paramList.isEmpty()){
            sysServiceParamDao.deleteByServiceId(service.getServiceId());
            return;
        }
        List<Long> keyList = new ArrayList<>();
        for(SysServiceParam param:paramList){
            param.setServiceId(service.getServiceId());
            param.setCreateDate(service.getCreateDate());
            param.setCreateUserId(service.getCreateUserId());
            param.setUpdateDate(service.getUpdateDate());
            param.setUpdateUserId(service.getUpdateUserId());
            if(param.getParamId()==null){
                sysServiceParamDao.save(param);
            }else{
                sysServiceParamDao.update(param);
            }
            keyList.add(param.getParamId());
        }
        if(!keyList.isEmpty()){
            sysServiceParamDao.deleteOther(service.getServiceId(),keyList);
        }
    }

    @Override
    @Transactional
    public Integer deleteService(Long serviceId) {
        Integer result = sysServiceInfoDao.delete(serviceId);
        sysServiceDao.delete(serviceId);
        sysServiceParamDao.deleteByServiceId(serviceId);
        sysServiceInfoDao.deleteUserService(serviceId);
        return result;
    }

    /**
     * 修改服务状态
     *
     * @param serviceId
     * @param status
     * @return
     */
    @Override
    public Integer updateServiceStatus(Long serviceId, Integer status) {
        return sysServiceDao.updateStatusById(serviceId,status);
    }

    /**
     * 分页查询
     *
     * @param param 查询条件
     * @return
     */
    @Override
    public PageResult<SysServiceInfoVo> findServiceByPage(PageParam<SysServiceInfoVo> param) {
        Long count = sysServiceInfoDao.count(param);
        List<SysServiceInfoVo> list = sysServiceInfoDao.findByPageParam(param);
        return PageResult.<SysServiceInfoVo>builder().data(list).code(CodeEnum.SUCCESS.getCode()).total(count).build() ;
    }

    /**
     * 计算资料完成度百分比
     * @param sysServiceInfo
     * @param sysService
     * @return
     */
    private Float countSchedule(SysServiceInfo sysServiceInfo,SysService sysService){
        if(sysServiceInfo==null&&sysService==null){
            return 0f;
        }
        int doneNum = 0;
        int allNum = 10;
        if(sysServiceInfo!=null){
            if(StringUtils.isNotEmpty(sysServiceInfo.getName())){
                doneNum++;
            }
            if(StringUtils.isNotEmpty(sysServiceInfo.getDescription())){
                doneNum++;
            }
            if(sysServiceInfo.getCataId()!=null){
                doneNum++;
            }
            if(sysServiceInfo.getCompanyId()!=null){
                doneNum++;
            }
            if(sysServiceInfo.getSerId()!=null){
                doneNum++;
            }
            if(sysServiceInfo.getSysId()!=null){
                doneNum++;
            }

        }
        if(sysService!=null){
            if(StringUtils.isNotEmpty(sysService.getPath())){
                doneNum++;
            }
            if(StringUtils.isNotEmpty(sysService.getUrl())){
                doneNum++;
            }
            if(sysService.getAllowDeptId()!=null){
                doneNum++;
            }
            if(sysService.getType()!=null){
                doneNum++;
            }
        }
        return doneNum*100f/allNum;
    }
}
