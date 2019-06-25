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
	
	void update(Employee emp);
	
	void delete(Integer empId);
	
	List<Employee> queryByPage(HashMap<String, Integer> map);
	
	List<Employee> queryByPage2(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	
	int getCount();
	
	Employee queryById(Integer employeeId);
	
	Employee queryByName(String empLoginName);
	
	List<Employee> queryByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("deptId") Integer deptId,@Param("jobId") Integer jobId,@Param("empName") String empName);
	
	List<Employee> queryByCondition2(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("deptId") Integer deptId,@Param("jobId") Integer jobId,@Param("empName") String empName);
	
	List<Employee> queryByCondition3(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("deptId") Integer deptId,@Param("jobId") Integer jobId,@Param("empName") String empName);
	
	int getCountByCondition(@Param("deptId") Integer deptId,@Param("jobId") Integer jobId,@Param("empName") String empName); 
	
	List<Employee> queryByIds(@Param("ids") Integer[] ids);
	
	void updateHead(@Param("empLoginName")String empLoginName,@Param("empPhoto")String empPhoto);
	
	String queryHead(@Param("empLoginName") String empLoginName);
	
	int getLastInsertId();
	
}