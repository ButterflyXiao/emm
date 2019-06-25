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
 * ���ſ�����
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	/**
	 * ���Ӳ���
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Department dept) {
		service.addDept(dept);		
	}
	
	/**
	 * �޸Ĳ���
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Department dept) {
		service.updateDept(dept);
	}
	
	/**
	 * ɾ������
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer deptId) {
		service.deleteDept(deptId);
	}
	
	/**
	 * ͨ��id��ѯ����
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
	 * ��ѯ����
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
	 * ����ɾ������
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
	 * ����excel����
	 * @throws IOException 
	 */
	@RequestMapping("/dept/export")
	public void export(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// ����������Ը�����ʽ�������ݣ������ļ���
		response.setHeader("content-disposition", "attachment;filename=department.xls");		
		//��Ӧ�����
		OutputStream out = response.getOutputStream();
		//�������
		service.exportExcel(out);		
	}	

}