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
 * 员工控制器
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
		
	/**
	 * 登录验证
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/login")	 
	@ResponseBody
	public Integer login(HttpServletRequest request,HttpServletResponse response,String empLoginName,String empPwd) {
		
		//密码加密
//		empPwd = new Sha256Hash(empPwd,"我有一只小花猫",10).toBase64();
		
		//封装用户名和密码
		UsernamePasswordToken upToken = new UsernamePasswordToken(empLoginName, empPwd);
		
		//Shiro登陆
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(upToken);
		} catch (UnknownAccountException e) {
			return 1;
		} catch (IncorrectCredentialsException e) {
			return 2;
		}
				
		//如果登录成功，session中记录当前用户的登录名（用户id）		
		HttpSession session = request.getSession();
		session.setAttribute("empLoginName", empLoginName); //记录登录名
		
		Employee emp = service.queryEmpByName(empLoginName);
		session.setAttribute("empId",emp.getEmpId()); //记录用户id						
				
		return 3;
	}	
	
	
	/**
	 * 返回登录名
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
	 * 增加员工
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/add")	 
	public void add(HttpServletRequest request,HttpServletResponse response,Employee emp) {		
		//密码加密
		String empPwd = new Sha256Hash(emp.getEmpPwd(),"我有一只小花猫",10).toBase64();
		emp.setEmpPwd(empPwd);
		service.addEmp(emp);		
	}
	
	/**
	 * 修改员工
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/update")	 
	public void update(HttpServletRequest request,HttpServletResponse response,Employee emp) {
		service.updateEmp(emp);		
	}
	
	/**
	 * 删除员工
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/emp/delete")	 
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer empId) {
		service.deleteEmp(empId);	
	}
		
	/**
	 * 分页查询员工
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
		
		//在Map集合中存储分页数据和员工数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;		
	}
	
	/**
	 * 条件分页查询员工
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
		
		//在Map集合中存储分页数据和员工数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;		
	}
	
	/**
	 * 判断用户登录是否存在
	 */
	@RequestMapping("emp/checkLoginName")
	@ResponseBody
	public boolean checkEmpLoginName(HttpServletRequest request,HttpServletResponse response,String empLoginName) {
		
		Employee emp = service.queryEmpByName(empLoginName);
		
		//不存在返回true
		if (emp == null) 
			return true;
		
		//存在返回false
		return false;		
	}	
	
	
	/**
	 * 通过id返回员工
	 */
	@RequestMapping("emp/get")
	@ResponseBody
	public Employee get(HttpServletRequest request,HttpServletResponse response,Integer empId) {
		
		Employee emp = service.queryEmpById(empId);
		
		return emp;				
	}
	
	/**
	 * 更新头像
	 */
	@RequestMapping("emp/updateHead")	
	public void get(HttpServletRequest request,HttpServletResponse response,String empPhoto) {
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
				
		service.updateEmpHead(empLoginName, empPhoto);
	}	
	
	
	/**
	 * 返回头像
	 */
	@RequestMapping("emp/queryHead")
	@ResponseBody
	public String queryHead(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
				
		return service.queryEmpHead(empLoginName);
	}	
	
	/**
	 * 全文检索员工
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
	 * 批量删除
	 */
	@RequestMapping("emp/deleteMany")	
	public void deleteMany(HttpServletRequest request,HttpServletResponse response,Integer[] ids) throws ParseException, IOException, InvalidTokenOffsetsException {
		
		System.out.println(Arrays.toString(ids));
				
	}
		
	
}