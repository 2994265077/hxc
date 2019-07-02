package com.cetccity.operationcenter.webframework.oa.service;

import java.util.List;

import com.cetccity.operationcenter.webframework.oa.model.Dept;
import com.cetccity.operationcenter.webframework.oa.model.DeptNode;
import com.cetccity.operationcenter.webframework.oa.model.NameBase;
import com.cetccity.operationcenter.webframework.oa.model.DeptUserInfo;
import com.cetccity.operationcenter.webframework.oa.model.Employee;
import com.cetccity.operationcenter.webframework.oa.model.UserInfo;

/**
 *
 * @author Administrator
 * @date 2019年6月10日
 * @desc 福田OA用户信息
 */
public interface EmployeeInfoService {
	/**
	 * 根据用户Id查询用户信息
	 * @param userId
	 * @return
	 */
	public Employee queryByUserId(String userId);

	/**
	 * 根据部门Id查询用户列表信息
	 * @param deptId 不能为空
	 * @return
	 */
	List<UserInfo> queryUserByDeptId(String deptId);

	/**
	 * 功能描述: <br>
	 * 〈查询所有组织结构及其下面的用户数据〉
	 *
	 * @return:
	 * @Author:yhy
	 * @Date: 2019/6/11 17:39
	 **/
	List<DeptNode> queryDeptWithUser();

	/**
	 * 功能描述: <br>
	 * 〈根据userId列表查询联系人列表〉
	 *
	 * @param userIds
	 * @return:
	 * @Author:yhy
	 * @Date: 2019/6/10 15:55
	 **/
	List<Employee> queryByUserIds(List<String> userIds);


	/**
	 * 功能描述: <br>
	 * 〈查询部门列表〉
	 *
	 * @return:
	 * @Author:yhy
	 * @Date: 2019/6/11 17:34
	 **/
	List<Dept> queryDeptList(String parent);

	/**
	 * 根据用户名模糊查询联系人
	 * @param name
	 * @return
	 */
	List<DeptNode> deptByUserName(String name);

	/**
	 * 功能描述: <br>
	 * 〈根据联系人名称查询联系人〉
	 *
	 * @param name  联系人名称
	 * @return:	联系人列表
	 * @Author:yhy
	 * @Date: 2019/6/12 16:31
	 **/
	List<UserInfo> employeeByName(String name);


	/**
	 * 同步用户信息
	 * @return
	 */
	public int synEmployee();

	/**
	 * 同步部门信息
	 * @return
	 */
	public int synDept();
}
