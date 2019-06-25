package com.icss.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.service.DepartmentService;


/**
 * 测试部门业务
 * 
 * @author Administrator
 *
 */
public class TestDepartmentService {

	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//获得service对象
	DepartmentService service = context.getBean(DepartmentService.class);
	
	@Test
	public void testAddDept() {
		Department dept = new Department("aaa","aaa");
		service.addDept(dept);
	}

	@Test
	public void testExportExcel() throws IOException {
		
		FileOutputStream fos = new FileOutputStream("e:\\部门数据.xls");
		
		service.exportExcel(fos);
		
	}
	
}