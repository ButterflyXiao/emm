package com.icss.oa.system.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
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
	 * �û���¼
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/login")
	@ResponseBody
	public int login(HttpServletRequest request,HttpServletResponse response,String empLoginName,String empPwd) {
		
		int flag = service.checkLogin(empLoginName, empPwd);
		
		if (flag == 3) {
			//��¼�ɹ�����session�б����û������û�id
			HttpSession session = request.getSession();
			session.setAttribute("empLoginName", empLoginName);
			
			Employee emp = service.queryEmpByName(empLoginName);
			session.setAttribute("empId", emp.getEmpId());
		}
		
		return flag;		
	}
	
	
	/**
	 * ����Ա��
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Employee emp) {
		service.addEmp(emp);
	}
	
	/**
	 * �޸�Ա��
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Employee emp) {
		service.updateEmp(emp);
	}
	
	/**
	 * ɾ��Ա��
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer empId) {
		service.deleteEmp(empId);
	}
	
	/**
	 * ��ҳ��ѯԱ��
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
	 * ��ҳ��ѯԱ��
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
	 * ����û����Ƿ����
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
	 * ͨ��id����Ա��
	 */
	@RequestMapping("/emp/get")
	@ResponseBody
	public Employee get(HttpServletRequest request,HttpServletResponse response,Integer empId) {		
		Employee emp = service.queryEmpById(empId);
		return emp;			
	}
	
	
	/**
	 * �޸�ͷ��
	 */
	@RequestMapping("/emp/updateHead")
	public void updateHead(HttpServletRequest request,HttpServletResponse response,String empPhoto) {		
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		service.updateEmpHead(empLoginName, empPhoto);		
	}
	
	/**
	 * ����ͷ��
	 */
	@RequestMapping("/emp/queryHead")
	@ResponseBody
	public String queryHead(HttpServletRequest request,HttpServletResponse response) {		
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		return service.queryEmpHead(empLoginName);			
	}
	
	/**
	 * �����û���
	 */
	@RequestMapping("/emp/getLoginName")
	@ResponseBody
	public String getLoginName(HttpServletRequest request,HttpServletResponse response) {		
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		return empLoginName;			
	}
	
	/**
	 * ȫ�ļ�����ѯԱ��	
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	@RequestMapping("/emp/queryByIndex")
	@ResponseBody
	public List<Employee> queryByIndex(HttpServletRequest request,HttpServletResponse response,String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {
		
		return service.queryEmpByIndex(queryStr);
	}

}