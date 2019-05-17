package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;

/**
 * 员工业务测试类
 * @author Administrator
 *
 */
public class TestEmployeeService {
	
	// 创建Spring容器核心对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//获得service对象
	EmployeeService service = context.getBean(EmployeeService.class);
	
	@Test
	public void testCheckLogin() {
		int result = service.checkLogin("zhangsan", "123456");
		System.out.println(result);		
	}	
	
	@Test
	public void testQueryEmpByPage() {
		
		Pager pager = new Pager(service.getEmpCount(), 7, 1);
		
		List<Employee> list = service.queryEmpByPage(pager);
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
		
	}

}