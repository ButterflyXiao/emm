package com.icss.oa.system.dao;

import java.util.List;

import com.icss.oa.system.pojo.Department;

/**
 * 部门数据访问
 * @author Administrator
 *
 */
public interface DepartmentMapper {
	
	void insert(Department dept);
	
	void update(Department dept);
	
	void delete(Integer deptId);
	
	Department queryById(Integer deptId);

	List<Department> query();
}