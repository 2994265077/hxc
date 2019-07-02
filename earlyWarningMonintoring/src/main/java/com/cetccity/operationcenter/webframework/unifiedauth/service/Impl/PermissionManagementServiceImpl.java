package com.cetccity.operationcenter.webframework.unifiedauth.service.Impl;

import com.cetccity.operationcenter.webframework.common.exception.CetcCommonException;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.core.frame.model.SysCode;
import com.cetccity.operationcenter.webframework.unifiedauth.api.model.http.AuthCode;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.PermissionMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.RolePermissionsMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionEntity;
import com.cetccity.operationcenter.webframework.unifiedauth.entity.PermissionNode;
import com.cetccity.operationcenter.webframework.web.util.PageParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Package: com.cetccity.operationcenter.webframework.unifiedauth.service.Impl
 * @Project: 31project-Apr4
 * @Description: //TODO
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/9 11:43
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/9 11:43
 * @Update_Description: huangzezhou 补充
 **/

@Service
@Slf4j
@Transactional
public class PermissionManagementServiceImpl {

    public static final String ROOT_PARENT_ID = "-1";

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionsMapper rolePermissionsMapper;

    @Autowired
    AuthCode authCode;

    public HttpResponseModel<List<PermissionEntity>> queryAllPermissions() {
        return new HttpResponseModel<List<PermissionEntity>>(SysCode.SYS_SUCCESS_CODE, permissionMapper.queryAll());
    }

    public List<PermissionNode> queryAllPermissionsTree(){
        List<PermissionNode> permissionNodes = permissionMapper.queryAllForTree();
        // perssionNode -->> <permissionId, perssionNode>
        Map<String, PermissionNode> permissionNodeMap = permissionNodes.stream()
                .collect(Collectors.toMap(permissionNode -> permissionNode.getPermission_id(), permissionNode -> permissionNode));
        // 将每一个节点都挂到它的父节点下面，然后将root节点提取出来
        List<PermissionNode> roots = permissionNodes.stream()
                .peek(permissionNode -> {
                    String parentId = permissionNode.getParent_id();
                    if (! ROOT_PARENT_ID.equals(parentId)) {
                        PermissionNode parentPermissionNode = permissionNodeMap.get(parentId);
                        if (Objects.nonNull(parentPermissionNode)) {
                            Collection<PermissionNode> children = parentPermissionNode.getChildren();
                            children.add(permissionNode);
                        }
                    }
                })
                .filter(permissionNode -> ROOT_PARENT_ID.equals(permissionNode.getParent_id()))
                .collect(Collectors.toList());
        return roots;
    }

    public PageInfo<PermissionEntity> page(PageParam pageParam, HttpServletRequest request) {
        Page<PermissionEntity> permissionEntitys = PageHelper.startPage(pageParam);
        permissionMapper.queryAll();
        return PageInfo.of(permissionEntitys);
    }

    public boolean save(PermissionEntity permissionEntity, HttpServletRequest request) {
        permissionEntity.setPermission_id(null);
        long rowNum = permissionMapper.save(permissionEntity);
        onUpdate(permissionEntity);
        return 1 == rowNum;
    }

    public boolean update(PermissionEntity permissionEntity, HttpServletRequest request) {
        if (StringUtils.isNotBlank(permissionEntity.getPermission_id())) {
            PermissionEntity oldPermission = permissionMapper.queryById(permissionEntity.getPermission_id());
            if (Objects.isNull(oldPermission)) {
                throw CetcCommonException.defaultException("修改失败，不存在该id权限数据");
            }
            long rowNum = permissionMapper.updateSelectedKey(permissionEntity);
            onDelete(oldPermission.getParent_id());
            onUpdate(permissionEntity);
            return rowNum == 1;

        }
        throw CetcCommonException.defaultException("更新失败，待更新数据必须有id");
    }

    /**
     * 功能描述: <br>
     * 〈更新时的附加同步操作，新增之后和更新之后都应调用〉
     *
     *  检查当前节点是否为根节点， 如果当前节点不是根节点，
     *  那么应该检查当前节点挂载的节点is_parent状态是否是期望的true
     *
     * @param permissionEntity
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/27 14:56
     */
    public void onUpdate(PermissionEntity permissionEntity) {
        String parent = permissionEntity.getParent_id();
        if (!ROOT_PARENT_ID.equals(parent)) {
            updateParentStatus(parent);
        }
    }

    public void updateParentStatus(String parent) {
        updateParentStatus(parent, true);
    }

    public void updateParentStatus(String parent , boolean isParent) {
        PermissionEntity parentPermission = permissionMapper.queryById(parent);
        if (Objects.isNull(parentPermission)) {
            throw CetcCommonException.defaultException("操作失败，父节点不存在");
        }
        // 父节点is_parent状态和要修改的状态不一样，修改状态
        if (isParent != parentPermission.isParent()) {
            PermissionEntity updateBean = PermissionEntity.builder()
                    .permission_id(parentPermission.getPermission_id())
                    .parent(isParent)
                    .build();
            permissionMapper.updateSelectedKey(updateBean);
        }

    }

    public boolean delete(String permissionId, HttpServletRequest request) {
        PermissionEntity toDelete = permissionMapper.queryById(permissionId);
        if (Objects.isNull(toDelete)) {
            throw CetcCommonException.defaultException("删除失败，未找到该权限项");
        }
        if (!toDelete.isParent()) {
            long rowNum = permissionMapper.delete(permissionId);
            onDelete(toDelete.getParent_id());
            rolePermissionsMapper.deleteByPermission(permissionId);
            return 1 == rowNum;
        }
        throw CetcCommonException.defaultException("删除失败，该节点下还有子节点");
    }

    /**
     * 功能描述: <br>
     * 〈删除时应同步调用的附加操作，在删除和修改之后都应该调用〉
     *
     *      检查当前节点是不是根节点，如果不是，检查当前节点的父节点是否还有子节点，
     *      如果父节点没有子节点了，检查父节点的is_parent状态是否是期望的false
     *
     * @param parentId
     * @return:void
     * @Author:yhy
     * @Date: 2019/5/27 14:59
     */
    public void onDelete(String parentId) {
        // 当前节点不是根节点，并且父节点不存在子节点
        if (!ROOT_PARENT_ID.equals(parentId) && !existChildren(parentId)) {
            updateParentStatus(parentId, false);
        }
    }

    public boolean delete(String... permissionIds) {
        for (String permissionId : permissionIds) {
            if (!delete(permissionId)) {
                throw CetcCommonException.defaultException("删除失败，未知错误");
            }
        }
        return true;
    }

    public boolean existPermission(String permissionId) {
        return 1 == permissionMapper.countById(permissionId);
    }

    public boolean existChildren(String parent) {
        return permissionMapper.countByParent(parent) > 0;
    }

}
