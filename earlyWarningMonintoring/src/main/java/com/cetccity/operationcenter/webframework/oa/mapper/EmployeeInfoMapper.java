package com.cetccity.operationcenter.webframework.oa.mapper;

import java.util.Collection;
import java.util.List;

import com.cetccity.operationcenter.webframework.oa.model.Dept;
import com.cetccity.operationcenter.webframework.oa.model.DeptNode;
import com.cetccity.operationcenter.webframework.oa.model.NameBase;
import com.cetccity.operationcenter.webframework.oa.model.DeptInfo;
import com.cetccity.operationcenter.webframework.oa.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cetccity.operationcenter.webframework.oa.model.DeptUserInfo;
import com.cetccity.operationcenter.webframework.oa.model.Employee;
/**
 * 
 * @author zhutongyu
 * @date 2019年6月10日
 * @desc oa用户信息
 */
@Mapper
public interface EmployeeInfoMapper {
    @Select("select EMPLOYEE_GUID id, DEPARTMENT_GUID id, EMPLOYEE_LOGINNAME loginName, EMPLOYEE_FULLNAME name, EMPLOYEE_EMAIL email from ft_ysbdts.NDRC_employee where EMPLOYEE_GUID = #{id} and Employee_IsDeleted = 0")
 	public Employee queryByUserId(@Param("id") String userId);

    List<Employee> queryByUserIds(@Param("userIds") List<String> userIds);
    
    public List<Employee> queryUserInfo();


    public List<DeptInfo> queryDeptInfo();

    List<DeptNode> queryDeptNode(@Param("ignoreDeptList") Collection<String> ignoreDeptList);

    List<UserInfo> queryByDeptId(@Param("id") String deptId);

    List<DeptUserInfo> queryDeptWithUser(@Param("ignoreDeptList") Collection<String> ignoreDeptList);

    List<Dept> queryDeptList(@Param("parent") String parent);

    List<DeptUserInfo> queryDeptWithUserByUserName(@Param("name") String name);

    List<UserInfo> queryEmployeeByName(@Param("name") String name);

    List<UserInfo> queryUserByIgnoreDept(@Param("ignoreDeptList") Collection<String> ignoreDeptList);

}
