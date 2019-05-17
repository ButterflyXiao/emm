package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;

/**
 * Ա��ҵ�������
 * @author Administrator
 *
 */
public class TestEmployeeService {
	
	// ����Spring�������Ķ���
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//���service����
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