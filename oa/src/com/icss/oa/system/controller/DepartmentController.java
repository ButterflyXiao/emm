package com.icss.oa.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService service;
	
	@RequestMapping("/dept/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Department dept){
		service.addDept(dept);
	}
	
	@RequestMapping("/dept/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Department dept){
		service.updateDept(dept);
	}
	
	@RequestMapping("/dept/delete")
	public void update(HttpServletRequest request,HttpServletResponse response,Integer deptId){
		service.deleteDept(deptId);
	}
	
	@RequestMapping("/dept/query")
	@ResponseBody
	public List<Department> query(HttpServletRequest request,HttpServletResponse response){
		return service.queryDept();
	}
	
	//µ•Ãı≤È—Ø
	
	@RequestMapping("/dept/get")
	@ResponseBody
	public Department get(HttpServletRequest request,HttpServletResponse response,Integer deptId){
		return service.queryDeptById(deptId);
	}
}
