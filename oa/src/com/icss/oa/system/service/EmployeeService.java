package com.icss.oa.system.service;

import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.EmployeeMapper;
import com.icss.oa.system.pojo.Employee;

/**
 * 员工业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper mapper;
	
	/**
	 * 登录验证
	 * 返回1 用户名不存在  2密码错误  3登录成功
	 */
	@Transactional(readOnly=true)
	public int checkLogin(String empLoginName,String empPwd) {
		
		Employee emp = mapper.queryByName(empLoginName);
		
		if (emp == null)
			return 1;
		else if (!empPwd.equals(emp.getEmpPwd()))
			return 2;
		else 
			return 3;
	}
	
	/**
	 *增加员工
	 */
	public void addEmp(Employee emp) {
		mapper.insert(emp);
	}	
	
	/**
	 * 分页查询员工
	 */
	@Transactional(readOnly=true)
	public List<Employee> queryEmpByPage(Pager pager) {
		return mapper.queryByPage2(pager.getStart(), pager.getPageSize());
	}	
	
	/**
	 * 条件分页查询员工
	 */
	@Transactional(readOnly=true)
	public List<Employee> queryEmpByCondition(Pager pager,Integer deptId,Integer jobId,String empName) {
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(),deptId,jobId,empName);
	}
	
	/**
	 * 返回员工总记录数
	 */
	@Transactional(readOnly=true)
	public int getEmpCount() {
		return mapper.getCount();
	}
	
	/**
	 * 返回满足条件员工总记录数
	 */
	@Transactional(readOnly=true)
	public int getEmpCountByCondition(Integer deptId,Integer jobId,String empName) {
		return mapper.getCountByCondition(deptId, jobId, empName);
	}	
	
	/**
	 * 根据登录名查询员工
	 */
	@Transactional(readOnly=true)
	public Employee queryEmpByName(String empLoginName) {
		return mapper.queryByName(empLoginName);
	}
	
	/**
	 * 根据id查询员工
	 */
	@Transactional(readOnly=true)
	public Employee queryEmpById(Integer empId) {
		return mapper.queryById(empId);
	}

}