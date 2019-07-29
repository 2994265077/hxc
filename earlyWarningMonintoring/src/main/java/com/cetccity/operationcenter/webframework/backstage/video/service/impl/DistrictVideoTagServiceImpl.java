package com.cetccity.operationcenter.webframework.backstage.video.service.impl;

import com.cetccity.operationcenter.webframework.backstage.utils.AuthenticationUser;
import com.cetccity.operationcenter.webframework.backstage.video.dao.DistrictClassMapper;
import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DISTRICT_CLASS;
import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DISTRICT_CLASSNode;
import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DISTRICT_CLASS_GROUP;
import com.cetccity.operationcenter.webframework.backstage.video.service.DistrictVideoTagService;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 11:20 2019-05-08
 * Updater:     heliangming
 * Update_Date：11:20 2019-05-08
 * 更新描述:    heliangming 补充
 **/
@Service
@Slf4j
public class DistrictVideoTagServiceImpl implements DistrictVideoTagService {

    @Autowired
    AuthenticationUser authenticationUser;

    @Autowired
    DistrictClassMapper districtClassMapper;

    /**
     * 查询视频标签层级
     * @param servletRequest
     * @return
     */
    public HttpResponseModel<List<DISTRICT_CLASSNode>> findVideoTagHierarchy(ServletRequest servletRequest){
        List<DISTRICT_CLASS> districtClassList = districtClassMapper.findVideoList(DISTRICT_CLASS.builder().USER_NAME(authenticationUser.getUser(servletRequest)).STATUS(1).build());
        List<DISTRICT_CLASSNode> res = districtClassList.stream()
                .filter(u -> u.getMAIN_ID().equals(0))
                .map(u -> covert(u,districtClassList)).collect(Collectors.toList());
        return new HttpResponseModel(0,res);
    }

    /**
     * 将标签转换为带有子级的标签对象
     * 当找不到子级权限的时候map操作不会再递归调用covert
     */
    private DISTRICT_CLASSNode covert(DISTRICT_CLASS permission,List<DISTRICT_CLASS> permissionList){
        DISTRICT_CLASSNode node = new DISTRICT_CLASSNode();
        BeanUtils.copyProperties(permission,node);
        List<DISTRICT_CLASSNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getMAIN_ID().equals(permission.getOBJECT_ID()))
                .map(subPermission -> covert(subPermission,permissionList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    public ResponseEntity<Integer> addVideoTagGroup(String tagGroupName,ServletRequest servletRequest) {
        //1、查看该标签名对否存在
        Map map = new HashMap();
        map.put("NAME",tagGroupName);
        if(districtClassMapper.isExistTagName(map).size() != 0)
            return new ResponseEntity("error-标签"+tagGroupName+"已存在",HttpStatus.PRECONDITION_FAILED);
        DISTRICT_CLASS dISTRICT_CLASS = DISTRICT_CLASS.builder().OBJECT_ID(districtClassMapper.maxId())
                .NAME(tagGroupName).USER_NAME(authenticationUser.getUser(servletRequest)).MAIN_ID(0).STATUS(1).build();
        return new ResponseEntity<>(districtClassMapper.save(dISTRICT_CLASS),HttpStatus.OK);
    }

    public ResponseEntity<Integer> addVideoTag(Integer tagGroupId, String tagName, ServletRequest servletRequest){
        Map map = new HashMap();
        map.put("MAIN_ID",tagGroupId);
        map.put("NAME",tagName);
        if(districtClassMapper.isExistTagName(map).size() != 0)
            return new ResponseEntity("error-标签"+tagName+"已存在",HttpStatus.PRECONDITION_FAILED);
        DISTRICT_CLASS dISTRICT_CLASS = DISTRICT_CLASS.builder().OBJECT_ID(districtClassMapper.maxId()).NAME(tagName)
                .USER_NAME(authenticationUser.getUser(servletRequest)).MAIN_ID(tagGroupId).STATUS(1).build();
                return new ResponseEntity<>(districtClassMapper.save(dISTRICT_CLASS), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> updateVideoTag(Integer object_ID, String tagName, Integer status){
        DISTRICT_CLASS dISTRICT_CLASS = DISTRICT_CLASS.builder().OBJECT_ID(object_ID).NAME(tagName).STATUS(status).build();
        return new ResponseEntity<>(districtClassMapper.updateById(dISTRICT_CLASS),HttpStatus.OK);
    }

    public HttpResponseModel<PageInfo> findVideoTagGroup(String tagGroupName, String user, Integer pageNum, Integer pageSize){
        DISTRICT_CLASS dISTRICT_CLASS = DISTRICT_CLASS.builder().NAME(tagGroupName).USER_NAME(user).MAIN_ID(0).build();
        PageHelper.startPage(pageNum, pageSize);
        List<DISTRICT_CLASS> dISTRICT_CLASS_list = districtClassMapper.findVideoList(dISTRICT_CLASS);
        PageInfo pageInfo = new PageInfo(dISTRICT_CLASS_list);
        return new HttpResponseModel(0,pageInfo);
    }

    public HttpResponseModel<PageInfo> findVideoTag(String tagGroupId, String tagName, String user, Integer pageNum, Integer pageSize){

        //查看是否有禁用的标签组
        List<DISTRICT_CLASS> menu = districtClassMapper.findVideoList(DISTRICT_CLASS.builder().MAIN_ID(0).STATUS(0).build());
        DISTRICT_CLASS dISTRICT_CLASS;
        if(StringUtils.isEmpty(tagGroupId)) {
            dISTRICT_CLASS = DISTRICT_CLASS.builder().NAME(tagName).USER_NAME(user).build();
        }else {
            dISTRICT_CLASS = DISTRICT_CLASS.builder().MAIN_ID(Integer.valueOf(tagGroupId)).NAME(tagName).USER_NAME(user).build();
        }
        List<DISTRICT_CLASS_GROUP> dISTRICT_CLASS_list;
        PageHelper.startPage(pageNum, pageSize);
        if(menu.size() == 0) { //没有禁用的标签组
            dISTRICT_CLASS_list = districtClassMapper.findVideoAsListNo(dISTRICT_CLASS);
        } else{
            dISTRICT_CLASS_list = districtClassMapper.findVideoAsList(dISTRICT_CLASS);
        }
        PageInfo pageInfo = new PageInfo(dISTRICT_CLASS_list);
        return new HttpResponseModel(0,pageInfo);
    }

    /**
     * 删除视屏标签组
     * @param object_ID 主键id
     */
    @Transactional
    public ResponseEntity<Boolean> deleteVideoTagGroup(Integer object_ID){
        //1、先获取改标签组下面的所有二级标签然后删除视屏标签库对应的该标签
        List<Integer> tgIdList = new ArrayList<>();
        List<DISTRICT_CLASS> dISTRICT_CLASS_list = districtClassMapper.findVideoList(DISTRICT_CLASS.builder().MAIN_ID(object_ID).build());
        dISTRICT_CLASS_list.stream().forEach(u-> tgIdList.add(u.getOBJECT_ID()));
        tgIdList.stream().forEach(u-> districtClassMapper.removeVideoTagById(u));
        //删除标签组对应的标签
        tgIdList.stream().forEach(u-> districtClassMapper.removeClassById(u));
        //删除标签库对应的标签组
        districtClassMapper.removeClassById(object_ID);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    /**
     * 删除视屏标签
     * @param object_ID 主键id
     */
    @Transactional
    public ResponseEntity<Boolean> deleteVideoTag(Integer object_ID){
        //1、删除视屏对应的该标签
        districtClassMapper.removeVideoTagById(object_ID);
        //2、删除标签库对应的标签
        districtClassMapper.removeClassById(object_ID);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
