/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: MapService
 * Author:   YHY
 * Date:     2019/6/3 11:38
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.common.map.service;

import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.common.map.dao.MapMapper;
import com.cetccity.operationcenter.webframework.common.map.entity.Group;
import com.cetccity.operationcenter.webframework.common.map.entity.Map;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yhy
 * @create 2019/6/3
 * @since 1.0.0
 */
@Service
@Slf4j
@Transactional
public class MapService {

    @Autowired
    private XmlMapper xmlMapper;

    @Autowired
    private MapMapper mapMapper;

    public List<Group> groupsTree() {
        List<Group> groups = mapMapper.queryAll();
        List<Group> root = groups.stream()
                .filter(group -> "-1".equals(group.getParentId()))
                .collect(Collectors.toList());
        java.util.Map<String, Group> groupMap = groups.stream()
                .collect(Collectors.toMap(Group::getObjectId, group -> group));
        for (Group group : groups) {
            Group parent = groupMap.get(group.getParentId());
            if (Objects.nonNull(parent)) {
                parent.getGroup().add(group);
            }
        }
        return root;
    }

    public Map map() {
        Map root = new Map();
        List<Group> roots = groupsTree();
        prepareXmlSeri(roots);
        Optional<Group> optionalRoot = roots.stream().findAny();
        optionalRoot.ifPresent(group -> {
            BeanCopier beanCopier = BeanCopier.create(Group.class, Map.class, false);
            beanCopier.copy(group, root, null);
        });
        return root;
    }

    private void prepareXmlSeri(List<Group> groups) {
        if (Objects.nonNull(groups)) {
            for (Group group : groups) {
                group.setObjectId(null);
                group.setParentId(null);
                group.setDescription(null);
                prepareXmlSeri(group.getGroup());
            }
        }
    }

    public boolean save(Group group) {
        group.setObjectId(null);
        String parentId = group.getParentId();
        if (StringUtils.isBlank(parentId)) {
            group.setParentId("-1");
        } else {
            long count = mapMapper.countByPri(parentId);
            if (1 > count) {
                throw CetcCommonException.defaultException("添加失败， 父节点不存在");
            }
        }
        return 1 == mapMapper.save(group);
    }

    public boolean update(Group group) {
        if (StringUtils.isBlank(group.getObjectId())) {
            throw CetcCommonException.defaultException("更新失败， 要更新的主键不能为空");
        }
        return 1 == mapMapper.update(group);
    }

    public boolean delete(String objectId) {
        return 1 == mapMapper.deleteByPri(objectId);
    }


    public void loadFromInputStream(InputStream inputStream, boolean override) {
        if (override) {
            mapMapper.deleteAll();
        }
        Map map = readFromInputStream(inputStream);
        saveGroups(Arrays.asList(map), "-1");
    }


    public void loadFromClassPath(String path, boolean override) {
        if (override) {
            mapMapper.deleteAll();
        }
        Map map = readFromClassPath(path);
        saveGroups(Arrays.asList(map), "-1");
    }

    public void loadFromFileSystem(String filePath, boolean override) {
        if (override) {
            mapMapper.deleteAll();
        }
        Map map = readFromFileSystem(filePath);
        saveGroups(Arrays.asList(map), "-1");
    }

    public void saveGroups(List<Group> groups, String parentId) {
        if (CollectionUtils.isNotEmpty(groups)) {
            for (Group group : groups) {
                String objectId = mapMapper.getId();
                group.setObjectId(objectId);
                group.setParentId(parentId);
                mapMapper.save(group);
                saveGroups(group.getGroup(), objectId);
            }
        }
    }

    public Map readFromInputStream(InputStream inputStream) {
        try {
            return xmlMapper.readValue(inputStream, Map.class);
        } catch (Exception e) {
            log.error("读取xml数据失败",e);
            throw CetcCommonException.defaultException("读取xml数据失败", e);
        }
    }

    public Map readFromClassPath(String path) {
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
            return readFromInputStream(classPathResource.getInputStream());
        } catch (Exception e) {
            log.error("读取类路径下文件{}失败", path, e);
            throw CetcCommonException.defaultException("读取类路径下文件失败", e);
        }
    }

    public Map readFromFileSystem(String filePath) {
        try {
            FileSystemResource fileSystemResource = new FileSystemResource(filePath);
            return readFromInputStream(fileSystemResource.getInputStream());
        } catch (Exception e) {
            log.error("读取类路径下文件{}失败", filePath);
            throw CetcCommonException.defaultException("读取类路径下文件失败", e);
        }
    }

}