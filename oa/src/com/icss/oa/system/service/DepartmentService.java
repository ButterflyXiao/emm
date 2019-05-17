package com.icss.oa.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Department;

/**
 * 部门业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class DepartmentService {
	
	@Autowired
	private DepartmentMapper mapper;
	
	/**
	 * 增加部门
	 */	
	public void addDept(Department dept) {	
		mapper.insert(dept);		
	}
	
	/**
	 * 修改部门
	 */
	public void updateDept(Department dept) {
		mapper.update(dept);
	}
	
	/**
	 * 删除部门
	 */
	public void deleteDept(Integer deptId) {
		mapper.delete(deptId);
	}
	
	/**
	 * 根据id查询部门
	 */
	@Transactional(readOnly=true)
	public Department queryDeptById(Integer deptId) {
		return mapper.queryById(deptId);
	}
	
	/**
	 * 查询所有部门
	 */
	@Transactional(readOnly=true)
	public List<Department> queryDept() {
		return mapper.query();
	}	

}