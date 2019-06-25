package com.icss.oa.system.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.service.DepartmentService;

/**
 * 部门控制器
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	/**
	 * 增加部门
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Department dept) {
		service.addDept(dept);		
	}
	
	/**
	 * 修改部门
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Department dept) {
		service.updateDept(dept);
	}
	
	/**
	 * 删除部门
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer deptId) {
		service.deleteDept(deptId);
	}
	
	/**
	 * 通过id查询部门
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/get")
	@ResponseBody
	public Department get(HttpServletRequest request,HttpServletResponse response,Integer deptId) {
		System.out.println("deptId=" + deptId);
		return service.queryDeptById(deptId);
	}
		
	/**
	 * 查询部门
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/dept/query")
	@ResponseBody
	public List<Department> query(HttpServletRequest request,HttpServletResponse response) {
		return service.queryDept();
	}
	
	/**
	 * 批量删除部门
	 */
	@RequestMapping("/dept/deleteMany")
	public void deleteMany(HttpServletRequest request,HttpServletResponse response,Integer[] deptIds) {
		
		for (Integer deptId : deptIds) {
			System.out.println(deptId);
		}
		
	}
	
	@RequestMapping("/dept/test")
	public void test(HttpServletRequest request,HttpServletResponse response,Boolean flag,@RequestParam(value="date",required=false) Date date) {
		System.out.println("flag=" + flag);
		System.out.println("date=" + date);
	}
	
	
	/**
	 * 下载excel报表
	 * @throws IOException 
	 */
	@RequestMapping("/dept/export")
	public void export(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// 设置浏览器以附件形式接收数据（下载文件）
		response.setHeader("content-disposition", "attachment;filename=department.xls");		
		//响应输出流
		OutputStream out = response.getOutputStream();
		//输出数据
		service.exportExcel(out);		
	}	

}