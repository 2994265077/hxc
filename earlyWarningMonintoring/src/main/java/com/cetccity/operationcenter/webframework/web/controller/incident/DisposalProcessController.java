package com.cetccity.operationcenter.webframework.web.controller.incident;

import com.cetccity.operationcenter.webframework.core.tools.InsertDisposalProcessEs;
import com.cetccity.operationcenter.webframework.core.tools.UpdateDisposalProcessEs;
import com.cetccity.operationcenter.webframework.core.tools.UpdateFireEventEs;
import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.cetccity.operationcenter.webframework.web.dao.DisposalProcessMapper;
import com.cetccity.operationcenter.webframework.web.dao.FireEventMapper;
import com.cetccity.operationcenter.webframework.web.model.incident.*;
import com.cetccity.operationcenter.webframework.core.tools.DisposalProcessEventEsUtil;
import com.cetccity.operationcenter.webframework.web.util.UrlApiToSource;
import com.cetccity.operationcenter.webframework.web.util.UuIdGeneratorUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消防事件-处理过程
 */
/*@Controller*/
@Slf4j
public class DisposalProcessController {
    @Autowired
    DisposalProcessMapper disposalProcessMapper;

    @Autowired
    FireEventMapper fireEventMapper;
    
    @Autowired
    private CommonInstance commonInstance;

    @RequestMapping(value = "/insertDisposalProcess",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String insertObject(DisposalProcess disposalProcess){
        String uuid = UuIdGeneratorUtil.getCetcCloudUuid("disposalProcess");
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        disposalProcess.setUuid(uuid);
        disposalProcess.setCreateTime(date);
        disposalProcess.setUpdateTime(date);
        int str = disposalProcessMapper.insert(disposalProcess);
        if("1".equals(disposalProcess.getDisposalStatus())){
            FireEvent fireEvent = new FireEvent();
            fireEvent.setDisposalStatus("1");
            FireEventExample example = new FireEventExample();
            example.createCriteria().andUuidEqualTo(disposalProcess.getEmergencyId());
            fireEventMapper.updateByExampleSelective(fireEvent,example);
            //消防事件状态同步ES
            List<FireEvent> fireEventList = new ArrayList<FireEvent>();
            disposalProcess.getEmergencyId();
            FireEventExample example2 = new FireEventExample();
            example2.createCriteria().andUuidEqualTo(disposalProcess.getEmergencyId());
            fireEventList = fireEventMapper.selectByExample(example2);
            UpdateFireEventEs.updateFireEvent(fireEventList.get(0));
        }
        //插入ES
        String source = InsertDisposalProcessEs.insertObject(disposalProcess);
        return "success";
    }

    @RequestMapping(value = "/deleteDisposalProcess",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteObject(String ordinal){
        //删除ES
        String urlPath = "http://"+ commonInstance.getElasticsearchIp()+":"+commonInstance.getElasticsearchPort()+"/tb_fire_emgc_disposal_process@31project_april/_doc/"+ordinal+"";
        try {
            String ss = UrlApiToSource.doJsonDelete(urlPath);
        } catch (IOException e) {
        	log.error(e.toString());
        }
        //删除MYSQL
        Integer id = Integer.valueOf(ordinal);
        int str = disposalProcessMapper.deleteByPrimaryKey(id);
        return "success";
    }

    @RequestMapping(value = "/updateDisposalProcess",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateObject(DisposalProcess disposalProcess){
        //更新MYSQL
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        disposalProcess.setUpdateTime(date);
        DisposalProcessExample example = new DisposalProcessExample();
        example.createCriteria().andIdEqualTo(disposalProcess.getId());
        int str = disposalProcessMapper.updateByExampleSelective(disposalProcess,example);
        //更新ES
        UpdateDisposalProcessEs.updateDisposalProcess(disposalProcess);
        return "success";
    }

    @RequestMapping(value = "/findDisposalProcessByEs",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<DisposalProcess> findObjectByEs(String emergency_id){
        return DisposalProcessEventEsUtil.findDisposalProcessEventEs(emergency_id);
    }

    @RequestMapping(value = "/findDisposalProcess",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<DisposalProcess> findObject(String emergency_id){
        DisposalProcessExample example = new DisposalProcessExample();
        example.createCriteria().andEmergencyIdEqualTo(emergency_id);
        List<DisposalProcess> list = disposalProcessMapper.selectByExample(example);
        return list;
    }
}
