package com.cetccity.operationcenter.webframework.web.controller.incident;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import com.cetccity.operationcenter.webframework.core.tools.*;
import com.cetccity.operationcenter.webframework.core.tools.Tooltip;
import com.cetccity.operationcenter.webframework.web.config.CommonInstance;
import com.cetccity.operationcenter.webframework.web.dao.DisposalProcessMapper;
import com.cetccity.operationcenter.webframework.web.dao.FireEventMapper;
import com.cetccity.operationcenter.webframework.web.model.incident.*;
import com.cetccity.operationcenter.webframework.web.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消防事件-事件管理
 */
/*@Controller*/
@Slf4j
public class FireEventController {
    private static final Logger logger = LoggerFactory.getLogger(FireEventController.class);
    @Autowired
    FireEventMapper fireEventMapper;

    @Autowired
    DisposalProcessMapper disposalProcessMapper;
    
    @Autowired
    private CommonInstance commonInstance;

    /*@Autowired
    InsertFireEventEs insertFireEventEs;*/

    @RequestMapping(value = "/insertFireEvent",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String insertObject(FireEvent fireEvent){
        String uuid = UuIdGeneratorUtil.getCetcCloudUuid("fireEvent");
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fireEvent.setUuid(uuid);
        fireEvent.setDisposalStatus("0");
        fireEvent.setCreateTime(date);
        fireEvent.setUpdateTime(date);
        int str = fireEventMapper.insert(fireEvent);
        String source = InsertFireEventEs.insertObject(fireEvent);
        if(source!=null){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(value = "/deleteFireEvent",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteObject(String ordinal){
        String emergency_id = FireEventById.getUuidById("tb_fire_emergencies",ordinal);
        //删除关联的DisposalProcess  ES 1、查询tb_fire_emergencies中uuid 2、根据uuid查询DisposalProcess的id 3、删除DisposalProcess的id
        System.out.println("---"+emergency_id);
        DeleteRelationDisposalProcess.deleteByUuid(emergency_id);
        //删除关联的DisposalProcess  MYsql
        DisposalProcessExample example = new DisposalProcessExample();
        example.createCriteria().andEmergencyIdEqualTo(emergency_id);
        disposalProcessMapper.deleteByExample(example);
        //删除fireEvent ES实体
        String urlPath = "http://"+ commonInstance.getElasticsearchIp()+":"+commonInstance.getElasticsearchPort()+"/tb_fire_emergencies@31project_april/_doc/"+ordinal+"";
        Integer id = Integer.valueOf(ordinal);
        //删除fireEvent MYsql
        int str = fireEventMapper.deleteByPrimaryKey(id);
        try {
            String ss = UrlApiToSource.doJsonDelete(urlPath);
        } catch (IOException e) {
        	log.error(e.toString());
        }
        return "success";
    }

    @RequestMapping(value = "/updateFireEvent",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateObject(FireEvent fireEvent){
        //更新MYSQL
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        fireEvent.setUpdateTime(date);
        FireEventExample example = new FireEventExample();
        example.createCriteria().andIdEqualTo(fireEvent.getId());
        int str = fireEventMapper.updateByExampleSelective(fireEvent,example);
        //更新ES
        String source = UpdateFireEventEs.updateFireEvent(fireEvent);
        System.out.println("--source--"+source);
        if(source != null){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(value = "/findFireEventByEs",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public PageInfo<FireEventEs> findObjectByEs(String pageNum,String pageSize,String state) {
        Integer size = Integer.parseInt(pageSize);
        Integer page = (Integer.parseInt(pageNum)-1)*size;
        System.out.println("------>"+commonInstance.getElasticsearchIp());
        return FireEventEsUtil.fireEventEs(page,size,state);
    }



    @RequestMapping(value = "/findFireEvent",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public PageInfo<FireEvent> findObject(String pageNum,String pageSize,String state){
        List<FireEvent> fireEventlist = new ArrayList<FireEvent>();
        PageInfo<FireEvent> pageInfo;
            //TODO:1.查询judge表中满足条件的记录(分页)
            PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
            FireEventExample example = new FireEventExample();
            if(state == null){
                 pageInfo = new PageInfo(fireEventMapper.selectByExample(example));
            }else {
                 example.createCriteria().andDisposalStatusEqualTo(state);
                 pageInfo = new PageInfo(fireEventMapper.selectByExample(example));
            }
            return pageInfo;
        }

    @RequestMapping(value = "/FireEvent/selectByExample",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LoadMap> singlePointLoadMap(String id){
        List<LoadMap> loadMapList = new ArrayList<LoadMap>();
        FireEventExample example = new FireEventExample();
        example.createCriteria().andIdEqualTo(Integer.valueOf(id));
        List<FireEvent> fireEventList = fireEventMapper.selectByExample(example);

        for (FireEvent fireEvent:fireEventList) {
            LoadMap loadMap = new LoadMap();
            loadMap.setLayerid("layer_otherPoint_fireEventList_xiaofangshijianliebiao");
            loadMap.setId(String.valueOf(fireEvent.getId()));
            loadMap.setTableName("tb_fire_emergencies");
            loadMap.setJd(fireEvent.getJd84());
            loadMap.setWd(fireEvent.getWd84());
            loadMapList.add(loadMap);
        }
        return loadMapList;
    }

    @RequestMapping(value = "/FireEvent/selectByExample_PageInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<LoadMap> findObjectLoadMap(String pageNum,String pageSize,String state){
        List<LoadMap> loadMapList = new ArrayList<LoadMap>();
        PageInfo<FireEvent> pageInfo;
        //TODO:1.查询judge表中满足条件的记录(分页)
        PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
        FireEventExample example = new FireEventExample();
        if(state == null){
            pageInfo = new PageInfo(fireEventMapper.selectByExample(example));
        }else {
            example.createCriteria().andDisposalStatusEqualTo(state);
            pageInfo = new PageInfo(fireEventMapper.selectByExample(example));
        }
        for (FireEvent fireEvent:pageInfo.getList()) {
            LoadMap loadMap = new LoadMap();
            loadMap.setLayerid("layer_otherPoint_fireEventList_xiaofangshijianliebiao");
            loadMap.setId(String.valueOf(fireEvent.getId()));
            loadMap.setTableName("tb_fire_emergencies");
            loadMap.setJd(fireEvent.getJd84());
            loadMap.setWd(fireEvent.getWd84());
            loadMapList.add(loadMap);
        }
        return loadMapList;
    }

    @RequestMapping(value = "/FireEvent/summaryInfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map loadMapTip(String id) {
        FireEvent fireEvent;
        String tableName = "tb_fire_emergencies";
        String urlStr = "http://"+ commonInstance.getElasticsearchIp()+":"+commonInstance.getElasticsearchPort()+"/"+tableName+"@31project_april/_doc/"+id+"";
        String source = null;
        try {
            source = UrlApiToSource.doJsonGet(urlStr);
            /**
             *json转为实体
             */
            ElasticTipModel elasticTipModel = JsonUtils.jsonToBean(source, ElasticTipModel.class);
            String _source = elasticTipModel.get_source().toString();
            fireEvent = JsonUtils.jsonToBean(_source, FireEvent.class);
        } catch (Exception e) {
            logger.info("没有数据......");
            fireEvent = null;
        }
        List result = new ArrayList();

        result.add("title");
        result.add("消防事件");
        result.add("消防事件名称");
        result.add(fireEvent.getEmergencyName());
        result.add("消防事件地址");
        result.add(fireEvent.getEmergencyAddress());
        result.add("类型");
        result.add(fireEvent.getEmergencyCategory());
        result.add("处置状态");
        result.add(fireEvent.getDisposalStatus());
        result.add("事件描述");
        result.add(fireEvent.getEmergencyDesc());
        result.add("事件等级");
        result.add(fireEvent.getEmergencyGrade());
        result.add("事发时间");
        result.add(fireEvent.getEmergencyTime());
        result.add("报送单位");
        result.add(fireEvent.getReportingUnit());
        return Tooltip.toolTipListToMap(result, true);
    }
}
