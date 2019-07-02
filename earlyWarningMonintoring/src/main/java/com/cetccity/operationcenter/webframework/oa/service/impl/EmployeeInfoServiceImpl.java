package com.cetccity.operationcenter.webframework.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.cetccity.operationcenter.webframework.oa.model.Dept;
import com.cetccity.operationcenter.webframework.oa.model.DeptNode;
import com.cetccity.operationcenter.webframework.oa.model.NameBase;
import com.cetccity.operationcenter.webframework.oa.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cetccity.operationcenter.webframework.oa.mapper.EmployeeInfoMapper;
import com.cetccity.operationcenter.webframework.oa.model.Dept;
import com.cetccity.operationcenter.webframework.oa.model.DeptInfo;
import com.cetccity.operationcenter.webframework.oa.model.DeptUserInfo;
import com.cetccity.operationcenter.webframework.oa.model.Employee;
import com.cetccity.operationcenter.webframework.oa.service.EmployeeInfoService;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.DeptMapper;
import com.cetccity.operationcenter.webframework.unifiedauth.dao.UserMapper;

import io.jsonwebtoken.lang.Collections;


/**
 * @author zhutongyu
 * @date 2019年6月10日
 * @desc oa用户信息
 */
@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;

	@Autowired
	private DeptMapper deptMapper;

	@Autowired
	private UserMapper userMapper;

	public static final String ROOT_PARENT_ID = "{00000000-0000-0000-0000-000000000000}";

	public static final Map<String, String> DEPT_IGNORE_MAP = new HashMap<>();

	static {
        DEPT_IGNORE_MAP.put("{62A11116-B652-894A-B5C3-B5ED657C6E7F}", "已调离和离职干部");
        DEPT_IGNORE_MAP.put("{7F000001-0000-0000-13EC-BB0D00000001}", "历史调离");
        DEPT_IGNORE_MAP.put("{7F000001-0000-0000-08F8-76F700002485}", "已撤销部门");
        DEPT_IGNORE_MAP.put("{04C7DA0F-4DF1-AD40-A7ED-F2073918E202}", "机关党工委");
        DEPT_IGNORE_MAP.put("{4A2DE3ED-DD8D-4F42-8A22-998001E4D073}", "办公室");
        DEPT_IGNORE_MAP.put("{AC1D3316-0000-0000-6DD9-BF8F00000004}", "组宣部 ");
        DEPT_IGNORE_MAP.put("{AC1D3316-0000-0000-6DD9-F40700000005}", "纪工委");
        DEPT_IGNORE_MAP.put("{AC1D3316-0000-0000-6DDB-04FE00000006}", "工委会");
        DEPT_IGNORE_MAP.put("{AC1D3316-0000-0000-6DDB-3F2900000007}", "妇委会 ");
    }

    public Employee queryByUserId(String userId) {
        return employeeInfoMapper.queryByUserId(userId);
    }

    public List<UserInfo> queryUserByDeptId(String deptId) {
        return employeeInfoMapper.queryByDeptId(deptId);
    }


    @Override
    public List<DeptNode> queryDeptWithUser() {
        List<DeptNode> deptNodes = employeeInfoMapper.queryDeptNode(DEPT_IGNORE_MAP.keySet());
        List<UserInfo> userInfos = employeeInfoMapper.queryUserByIgnoreDept(DEPT_IGNORE_MAP.keySet());
        deptNodes = deptNodes.stream()
                .filter(Objects::nonNull)
                .filter(deptNode -> Objects.nonNull(deptNode.getId()))
                .collect(Collectors.toList());

        Map<String, DeptNode> deptNodeMap = deptNodes.stream()
                .collect(Collectors.toMap(DeptNode::getId, deptNode -> deptNode));
        // 用户挂载到组织机构
        userInfos.stream()
                .filter(Objects::nonNull)
                .filter(userInfo -> Objects.nonNull(userInfo.getId()))
                .forEach(userInfo -> {
                    DeptNode deptNode = deptNodeMap.get(userInfo.getDeptId());
                    if (Objects.nonNull(deptNode)) {
                        deptNode.getDatas().add(userInfo);
                    }
                });
        // 组织机构树形成, 删除没有子节点的组织机构
        deptNodes = deptNodes.stream()
                .peek(deptNode -> {
                    DeptNode parent = deptNodeMap.get(deptNode.getParent());
                    if (Objects.nonNull(parent)) {
                        parent.getDatas().add(deptNode);
                    }
                })
                .filter(deptNode -> CollectionUtils.isNotEmpty(deptNode.getDatas()))
                .collect(Collectors.toList());
        // 选出根节点
        List<DeptNode> rootDeptNodes = deptNodes.stream()
                .filter(deptNode -> ROOT_PARENT_ID.equals(deptNode.getParent()))
                .collect(Collectors.toList());

        return rootDeptNodes;
    }

	/**
	 * 同步福田OA用户到预警监测平台
	 */
	public int synEmployee() {
		List<Employee> employeeList = employeeInfoMapper.queryUserInfo();
		//只同步本地库账号不存在的账号
		List<String> localAccoutList = userMapper.queryAllUserAccount();
		List<Employee> newEmployeeList = new ArrayList<Employee>();
		for (Employee oaUser : employeeList) {
			if (!localAccoutList.contains(oaUser.getLoginName())) {
				newEmployeeList.add(oaUser);
			}
		}
		if(Collections.isEmpty(newEmployeeList))
			return 0;
		return userMapper.batchInsertUserInfo(newEmployeeList);
	}

	/**
	 * 同步福田OA用户到预警监测平台
	 */
	@Transactional
	public int synDept() {
		//来自OA的最新数据
		List<DeptInfo> deptInfoList = employeeInfoMapper.queryDeptInfo();
		//删除来自本系统的数据
		deptMapper.deleteDept();
		return deptMapper.batchInsertDeptInfo(deptInfoList);
	}

    @Override
    public List<Employee> queryByUserIds(List<String> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return new LinkedList<>();
        }
        return employeeInfoMapper.queryByUserIds(userIds);
    }

    @Override
    public List<Dept> queryDeptList(String parent) {
        return employeeInfoMapper.queryDeptList(parent);
    }

    @Override
    public List<DeptNode> deptByUserName(String name) {
        if (!name.startsWith("%")) {
            name = "%" + name;
        }
        if (!name.endsWith("%")) {
            name += "%";
        }
        List<UserInfo> userInfos = employeeInfoMapper.queryEmployeeByName(name);
        List<DeptNode> deptNodes = employeeInfoMapper.queryDeptNode(null);
        Map<String, DeptNode> deptNodeMap = deptNodes.stream()
                .collect(Collectors.toMap(DeptNode::getId, deptNode -> deptNode));
        // 树挂载
        for (DeptNode deptNode : deptNodes) {
            String depatParentId = deptNode.getParent();
            if (StringUtils.isNotBlank(depatParentId)) {
                DeptNode parentNode = deptNodeMap.get(depatParentId);
                if (Objects.nonNull(parentNode)) {
                    parentNode.getDatas().add(deptNode);
                }
            }
        }

        //用户挂载
        for (UserInfo userInfo : userInfos) {
            String deptId = userInfo.getDeptId();
            if (StringUtils.isNotBlank(deptId)) {
                DeptNode deptNode = deptNodeMap.get(deptId);
                if (Objects.nonNull(deptNode)) {
                    deptNode.getDatas().add(userInfo);
                }
            }
        }
        List<DeptNode> roots = deptNodes.stream()
                .filter(deptNode -> ROOT_PARENT_ID.equals(deptNode.getParent()))
                .filter(this::hasUser)
                .peek(this::deleteNoDateNode )
                .collect(Collectors.toList());
        return roots;
    }

    private void deleteNoDateNode(DeptNode deptNode) {
        List<NameBase> datas = deptNode.getDatas();
        if (CollectionUtils.isNotEmpty(datas)) {
            datas = datas
                    .stream()
                    .filter(nameBase -> {
                        if (nameBase instanceof DeptNode) {
                            return hasUser((DeptNode) nameBase);
                        }
                        return true;
                    })
                    .peek(nameBase -> {
                        if (nameBase instanceof DeptNode) {
                            deleteNoDateNode((DeptNode) nameBase);
                        }
                    })
                    .collect(Collectors.toList());
        }
        deptNode.setDatas(datas);
    }

    private boolean hasUser(DeptNode deptNode) {
        List<NameBase> datas = deptNode.getDatas();
        if (CollectionUtils.isNotEmpty(datas)) {
            if (datas.get(0) instanceof UserInfo) {
                return true;
            }
            for (NameBase data : datas) {
                if (data instanceof UserInfo) {
                    return true;
                }
                DeptNode node = (DeptNode) data;
                if (hasUser(node)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<UserInfo> employeeByName(String name) {
        if (!name.startsWith("%")) {
            name = "%" + name;
        }
        if (!name.endsWith("%")) {
            name += "%";
        }
        return employeeInfoMapper.queryEmployeeByName(name);
    }
}
