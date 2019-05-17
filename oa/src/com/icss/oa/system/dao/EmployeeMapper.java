package com.icss.oa.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.system.pojo.Employee;

/**
 * 员工数据访问
 * @author Administrator
 *
 */
public interface EmployeeMapper {
	
	void insert(Employee emp);
	
	List<Employee> queryByPage(HashMap<String, Integer> map);
	
	List<Employee> queryByPage2(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	
	int getCount();
	
	Employee queryById(Integer empId);
	
	Employee queryByName(String empLoginName);
	
	List<Employee> queryByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("deptId") Integer deptId,@Param("jobId") Integer jobId,@Param("empName") String empName);
	
	int getCountByCondition(@Param("deptId") Integer deptId,@Param("jobId") Integer jobId,@Param("empName") String empName);

}