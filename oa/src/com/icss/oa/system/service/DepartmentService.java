package com.icss.oa.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Department;

/**
 * ����ҵ��
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class DepartmentService {
	
	@Autowired
	private DepartmentMapper mapper;
	
	/**
	 * ���Ӳ���
	 */	
	public void addDept(Department dept) {	
		mapper.insert(dept);		
	}
	
	/**
	 * �޸Ĳ���
	 */
	public void updateDept(Department dept) {
		mapper.update(dept);
	}
	
	/**
	 * ɾ������
	 */
	public void deleteDept(Integer deptId) {
		mapper.delete(deptId);
	}
	
	/**
	 * ����id��ѯ����
	 */
	@Transactional(readOnly=true)
	public Department queryDeptById(Integer deptId) {
		return mapper.queryById(deptId);
	}
	
	/**
	 * ��ѯ���в���
	 */
	@Transactional(readOnly=true)
	public List<Department> queryDept() {
		return mapper.query();
	}	

}