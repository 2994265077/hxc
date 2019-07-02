package com.cetccity.operationcenter.webframework.rundisplay.service.impl;

import com.cetccity.operationcenter.webframework.backstage.video.dao.DistrictVideoClassMapper;
import com.cetccity.operationcenter.webframework.backstage.video.dao.entity.DistrictVideoClassNode;
import com.cetccity.operationcenter.webframework.rundisplay.dao.VIDEO_POLICEMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.VIDEO_TAGMapper;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.VIDEO_POLICE;
import com.cetccity.operationcenter.webframework.rundisplay.dao.entity.VIDEO_TAG;
import com.cetccity.operationcenter.webframework.rundisplay.service.RunDisplayService;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.LoadMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunDisplayServiceImpl implements RunDisplayService {

    @Autowired
    VIDEO_POLICEMapper vIDEO_POLICEMapper;

    @Autowired
    DistrictVideoClassMapper districtVideoClassMapper;

    public List<LoadMap> loadVideoMapByDB(String tableName, String layerid, String name,String id,String street){
        List<LoadMap> loadMap_list = new ArrayList<LoadMap>();
        if(StringUtils.isEmpty(name)){
            VIDEO_POLICE vIDEO_POLICE = new VIDEO_POLICE();
            vIDEO_POLICE.setCAMERAID(id);
            List<VIDEO_POLICE> vIDEO_POLICE_list = vIDEO_POLICEMapper.getVIDEO_POLICE(vIDEO_POLICE);
            vIDEO_POLICE_list.stream().filter(u->StringUtils.isNotEmpty(u.getJD84())||StringUtils.isNotEmpty(u.getWD84())).collect(Collectors.toList()).stream().forEach(u->{
                loadMap_list.add(LoadMap.builder().tableName(tableName).id(u.getCAMERAID()).layerid(layerid).jd(u.getJD84()).wd(u.getWD84()).build());
            });
        }else {
            DistrictVideoClassNode districtVideoClassNode = new DistrictVideoClassNode();
            districtVideoClassNode.setTAGNAME(name);
            List<DistrictVideoClassNode> districtVideoClassList = districtVideoClassMapper.findVideoRelationTagList(districtVideoClassNode);
            districtVideoClassList.stream().filter(u->StringUtils.isNotEmpty(u.getJD84())||StringUtils.isNotEmpty(u.getWD84())).collect(Collectors.toList()).stream().forEach(u->{
                loadMap_list.add(LoadMap.builder().tableName(tableName).id(u.getCAMERAID()).layerid(layerid).jd(u.getJD84()).wd(u.getWD84()).build());
            });
        }
        return loadMap_list;
    }

    public List<LoadMap> loadVideoMapByDBForStreet(String tableName,String layerid,String streetCode,String name,String id,String street){
        List<LoadMap> loadMap_list = new ArrayList<LoadMap>();
        String jd84,wd84;
        if(StringUtils.isEmpty(name)){
            VIDEO_POLICE vIDEO_POLICE = new VIDEO_POLICE();
            vIDEO_POLICE.setSTREET_CODE(streetCode);
            vIDEO_POLICE.setCAMERAID(id);
            List<VIDEO_POLICE> vIDEO_POLICE_list = vIDEO_POLICEMapper.getVIDEO_POLICE(vIDEO_POLICE);
            vIDEO_POLICE_list.stream().filter(u->StringUtils.isNotEmpty(u.getJD84())||StringUtils.isNotEmpty(u.getWD84())).collect(Collectors.toList())
                    .stream().forEach(s->loadMap_list.add(LoadMap.builder().id(s.getCAMERAID()).tableName(tableName)
                    .layerid(layerid).jd(s.getJD84()).wd(s.getWD84()).build()));
        } else {
            DistrictVideoClassNode districtVideoClassNode = new DistrictVideoClassNode();
            districtVideoClassNode.setTAGNAME(name);
            districtVideoClassNode.setSTREET_CODE(streetCode);
            List<DistrictVideoClassNode> districtVideoClassList = districtVideoClassMapper.findVideoRelationTagList(districtVideoClassNode);
            districtVideoClassList.stream().filter(u->StringUtils.isNotEmpty(u.getJD84())||StringUtils.isNotEmpty(u.getWD84())).collect(Collectors.toList()).stream().forEach(u->{
                loadMap_list.add(LoadMap.builder().tableName(tableName).id(u.getCAMERAID()).layerid(layerid).jd(u.getJD84()).wd(u.getWD84()).build());
            });
        }
        return loadMap_list;
    }
}
