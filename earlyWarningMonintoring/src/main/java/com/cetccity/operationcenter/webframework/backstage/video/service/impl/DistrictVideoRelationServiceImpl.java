package com.cetccity.operationcenter.webframework.backstage.video.service.impl;

import com.cetccity.operationcenter.webframework.backstage.utils.AuthenticationUser;
import com.cetccity.operationcenter.webframework.backstage.video.dao.DistrictVideoClassMapper;
import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DistrictVideoClass;
import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DistrictVideoClassNode;
import com.cetccity.operationcenter.webframework.backstage.video.service.DistrictVideoRelationService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.search.api.model.NearSearchLoadMap;
import com.cetccity.operationcenter.webframework.search.service.PolygonSearchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.backstage.video.service.impl
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 18:35 2019-05-08
 * Updater:     heliangming
 * Update_Date：18:35 2019-05-08
 * 更新描述:    heliangming 补充
 **/
@Service
@Slf4j
public class DistrictVideoRelationServiceImpl implements DistrictVideoRelationService {

    @Autowired
    AuthenticationUser authenticationUser;

    /**
     * 注入districtVideoClassMapper
     */
    @Autowired
    DistrictVideoClassMapper districtVideoClassMapper;

    @Autowired
    PolygonSearchService polygonSearchService;

    /**
     * 查询视屏关联标签
     * @param tagName 标签名称
     * @param user 用户名
     * @param pageNum 页码
     * @param pageSize 行数
     * @return
     */
    public HttpResponseModel<PageInfo> findVideoRelationTag(String tagName, String user, String street, Integer pageNum, Integer pageSize) {
        DistrictVideoClassNode districtVideoClassNode = new DistrictVideoClassNode();
        districtVideoClassNode.setTAGNAME(tagName);
        districtVideoClassNode.setUSER_NAME(user);
        districtVideoClassNode.setSTREET_CODE(StringUtils.isEmpty(street)? null : LoadMyUtil.getPropertiesVauleOfKey("street.properties",street).split(",")[0]);
        PageHelper.startPage(pageNum, pageSize);
        List<DistrictVideoClassNode> districtVideoClassList = districtVideoClassMapper.findVideoRelationTagList(districtVideoClassNode);
        PageInfo pageInfo = new PageInfo(districtVideoClassList);
        return new HttpResponseModel(0,pageInfo);
    }

    public HttpResponseModel findVideoRelationByCameraId(String cameraId, ServletRequest servletRequest){
        DistrictVideoClassNode districtVideoClassNode = new DistrictVideoClassNode();
        districtVideoClassNode.setCAMERAID(cameraId);
        districtVideoClassNode.setUSER_NAME(authenticationUser.getUser(servletRequest));
        List<DistrictVideoClassNode> districtVideoClassList = districtVideoClassMapper.findVideoRelationTagList(districtVideoClassNode);
        List<NameValueModel> tagNameList = new ArrayList<>();
        districtVideoClassList.stream().forEach(u-> tagNameList.add(NameValueModel.builder().name(u.getTAGNAME()).value(String.valueOf(u.getCLASS_ID())).build()));
        return new HttpResponseModel(0,tagNameList);
    }

    /**
     * 给视屏添加标签
     * @param rings 视屏编号
     * @param tagId 标签id
     */
    public HttpResponseModel<Boolean> videoAddTag(String rings, Integer tagId, ServletRequest servletRequest) {
        String[] table = {"video_police"};
        String user = authenticationUser.getUser(servletRequest);
        List<NearSearchLoadMap> list = new ArrayList<>();
        try {
            list = (List<NearSearchLoadMap>)polygonSearchService.findPolygonResourceObj(table,rings).get(0).getData();
        }catch (Exception e){
        	log.error(e.toString());
        }
        if(list.size()!=0) {
            for(NearSearchLoadMap u:list){
                districtVideoClassMapper.videoAddTag(DistrictVideoClass.builder().OBJECT_ID(districtVideoClassMapper.maxId())
                .CLASS_ID(tagId).CAMERAID(u.getId()).USER_NAME(user).build());
            }
        }
        return new HttpResponseModel<>(0,true);
    }

    public HttpResponseModel<Boolean> videoAddTagToCameraId(String cameraId, Integer tagId, ServletRequest servletRequest){
        districtVideoClassMapper.videoAddTag(DistrictVideoClass.builder().OBJECT_ID(districtVideoClassMapper.maxId())
                        .CLASS_ID(tagId).CAMERAID(cameraId).USER_NAME(authenticationUser.getUser(servletRequest)).build());
        return new HttpResponseModel<>(0,true);
    }
    /**
     * 删除有标签的视屏
     * @param object_ID id
     */
    public HttpResponseModel<Integer> videoRemoveTag(Integer object_ID) {
        int s = districtVideoClassMapper.videoRemoveTag(DistrictVideoClass.builder().OBJECT_ID(object_ID).build());
        return new HttpResponseModel<>(0,s);
    }

    /**
     * 给视屏删除标签
     * @param cameraId 视屏编号
     * @param tagId 标签id
     */
    public HttpResponseModel<Integer> videoRemoveTagToCameraId(String cameraId,Integer tagId){
        int s = districtVideoClassMapper.videoRemoveTag(DistrictVideoClass.builder().CAMERAID(cameraId).CLASS_ID(tagId).build());
        return new HttpResponseModel<>(0,s);
    }

    public void loadMapVideoRelationByClassId(Integer classId){
        Map map = new HashMap();
        districtVideoClassMapper.loadMapVideoRelationByClassId(map);
    }
}
