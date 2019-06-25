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
 * ���Բ���ҵ��
 * 
 * @author Administrator
 *
 */
public class TestDepartmentService {

	// ���Spring������������
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//���service����
	DepartmentService service = context.getBean(DepartmentService.class);
	
	@Test
	public void testAddDept() {
		Department dept = new Department("aaa","aaa");
		service.addDept(dept);
	}

	@Test
	public void testExportExcel() throws IOException {
		
		FileOutputStream fos = new FileOutputStream("e:\\��������.xls");
		
		service.exportExcel(fos);
		
	}
	
}