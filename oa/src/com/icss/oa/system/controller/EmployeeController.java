package com.icss.oa.system.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;

/**
 * Ա��������
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
		
	/**
	 * ��¼��֤
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/login")	 
	@ResponseBody
	public Integer login(HttpServletRequest request,HttpServletResponse response,String empLoginName,String empPwd) {
		
		//�������
//		empPwd = new Sha256Hash(empPwd,"����һֻС��è",10).toBase64();
		
		//��װ�û���������
		UsernamePasswordToken upToken = new UsernamePasswordToken(empLoginName, empPwd);
		
		//Shiro��½
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(upToken);
		} catch (UnknownAccountException e) {
			return 1;
		} catch (IncorrectCredentialsException e) {
			return 2;
		}
				
		//�����¼�ɹ���session�м�¼��ǰ�û��ĵ�¼�����û�id��		
		HttpSession session = request.getSession();
		session.setAttribute("empLoginName", empLoginName); //��¼��¼��
		
		Employee emp = service.queryEmpByName(empLoginName);
		session.setAttribute("empId",emp.getEmpId()); //��¼�û�id						
				
		return 3;
	}	
	
	
	/**
	 * ���ص�¼��
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/getLoginName")	
	@ResponseBody
	public String getLoginName(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		return empLoginName;
	}
	
	
	/**
	 * ����Ա��
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/add")	 
	public void add(HttpServletRequest request,HttpServletResponse response,Employee emp) {		
		//�������
		String empPwd = new Sha256Hash(emp.getEmpPwd(),"����һֻС��è",10).toBase64();
		emp.setEmpPwd(empPwd);
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
	 */
	@RequestMapping("emp/query") 
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request,HttpServletResponse responsep,Integer pageNum,Integer pageSize) {
		
		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = 10;		
		
		Pager pager = new Pager(service.getEmpCount(), pageSize, pageNum);
		
		List<Employee> list = service.queryEmpByPage(pager);
		
		//��Map�����д洢��ҳ���ݺ�Ա������
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;		
	}
	
	/**
	 * ������ҳ��ѯԱ��
	 */
	@RequestMapping("emp/search") 
	@ResponseBody
	public HashMap<String, Object> search(HttpServletRequest request,HttpServletResponse responsep,Integer pageNum,
			Integer pageSize,Integer deptId,Integer jobId,String empName) {
		
		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = 10;		
		
		Pager pager = new Pager(service.getCountByCondition(deptId, jobId, empName), pageSize, pageNum);
		
		List<Employee> list = service.queryEmpByCondition(pager, deptId, jobId, empName);
		
		//��Map�����д洢��ҳ���ݺ�Ա������
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;		
	}
	
	/**
	 * �ж��û���¼�Ƿ����
	 */
	@RequestMapping("emp/checkLoginName")
	@ResponseBody
	public boolean checkEmpLoginName(HttpServletRequest request,HttpServletResponse response,String empLoginName) {
		
		Employee emp = service.queryEmpByName(empLoginName);
		
		//�����ڷ���true
		if (emp == null) 
			return true;
		
		//���ڷ���false
		return false;		
	}	
	
	
	/**
	 * ͨ��id����Ա��
	 */
	@RequestMapping("emp/get")
	@ResponseBody
	public Employee get(HttpServletRequest request,HttpServletResponse response,Integer empId) {
		
		Employee emp = service.queryEmpById(empId);
		
		return emp;				
	}
	
	/**
	 * ����ͷ��
	 */
	@RequestMapping("emp/updateHead")	
	public void get(HttpServletRequest request,HttpServletResponse response,String empPhoto) {
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
				
		service.updateEmpHead(empLoginName, empPhoto);
	}	
	
	
	/**
	 * ����ͷ��
	 */
	@RequestMapping("emp/queryHead")
	@ResponseBody
	public String queryHead(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
				
		return service.queryEmpHead(empLoginName);
	}	
	
	/**
	 * ȫ�ļ���Ա��
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	@RequestMapping("emp/queryByIndex")
	@ResponseBody
	public List<Employee> queryByIndex(HttpServletRequest request,HttpServletResponse response,String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {
		
		return service.queryEmpByIndex(queryStr);		
	}
	
	/**
	 * ����ɾ��
	 */
	@RequestMapping("emp/deleteMany")	
	public void deleteMany(HttpServletRequest request,HttpServletResponse response,Integer[] ids) throws ParseException, IOException, InvalidTokenOffsetsException {
		
		System.out.println(Arrays.toString(ids));
				
	}
		
	
}