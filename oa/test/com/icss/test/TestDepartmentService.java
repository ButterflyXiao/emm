package com.icss.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.service.DepartmentService;

public class TestDepartmentService {
	ApplicationContext context=new ClassPathXmlApplicationContext();
	
	DepartmentService service=context.getBean(DepartmentService.class);
	
	@Test
	public void testAddDept(){
		
		Department department=new Department("aaa","AAA");
		
	}
}
