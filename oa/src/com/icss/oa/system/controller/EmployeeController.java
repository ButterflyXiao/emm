package com.icss.oa.system.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;

/**
 * 
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	/**
	 * 增加员工
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Employee emp) {
		service.addEmp(emp);
	}
	
	/**
	 * 分页查询员工
	 * @param request
	 * @param response
	 * @param emp
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/emp/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request,HttpServletResponse response,Integer pageSize,Integer pageNum) {
		
		if (pageSize == null)
			pageSize = 10;
		
		if (pageNum == null)
			pageNum = 1;
		
		Pager pager = new Pager(service.getEmpCount(), pageSize, pageNum);		
		List<Employee> list = service.queryEmpByPage(pager);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;
	}
	
	
	/**
	 * 分页查询员工
	 * @param request
	 * @param response
	 * @param emp
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/emp/search")
	@ResponseBody
	public HashMap<String, Object> search(HttpServletRequest request,HttpServletResponse response,Integer pageSize,Integer pageNum,
			Integer deptId,Integer jobId,String empName) {
		
		if (pageSize == null)
			pageSize = 10;
		
		if (pageNum == null)
			pageNum = 1;
		
		Pager pager = new Pager(service.getEmpCountByCondition(deptId, jobId, empName), pageSize, pageNum);		
		List<Employee> list = service.queryEmpByCondition(pager, deptId, jobId, empName);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;
	}
	
	
	/**
	 * 检查用户名是否存在
	 */
	@RequestMapping("/emp/checkLoginName")
	@ResponseBody
	public boolean checkLoginName(HttpServletRequest request,HttpServletResponse response,String empLoginName) {
		
		Employee emp = service.queryEmpByName(empLoginName);
		
		if (emp == null)
			return true;
		
		return false;		
	}
	
	/**
	 * 通过id返回员工
	 */
	@RequestMapping("/emp/get")
	@ResponseBody
	public Employee get(HttpServletRequest request,HttpServletResponse response,Integer empId) {		
		Employee emp = service.queryEmpById(empId);
		return emp;			
	}
	

}