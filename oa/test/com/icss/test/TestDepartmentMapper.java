package com.icss.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Department;

/**
 * 测试部门dao类
 * @author Administrator
 *
 */
public class TestDepartmentMapper {
	
	//获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//获得Mapper对象（dao对象）
	DepartmentMapper mapper = context.getBean(DepartmentMapper.class);
	

	@Test
	public void testInsert() {	
		//创建pojo对象
		Department dept = new Department("fff", "fff");		
		//调用数据操作
		mapper.insert(dept);
	}
	
	@Test
	public void testUpdate() {			
		//创建pojo对象
		Department dept = new Department(7,"信息安全部", "信息安全保密");		
		//调用数据操作
		mapper.update(dept);
	}
	
	@Test
	public void testDelete() {	
		//调用数据操作
		mapper.delete(8);
	}
	
	@Test
	public void testQueryById() {	
		//数据查询操作
		Department dept = mapper.queryById(16);
		System.out.println(dept);
	}
	
	@Test
	public void testQuery() {	
		List<Department> list = mapper.query();
		
		for (Department dept : list) {
			System.out.println(dept);
		}
	}
	
	@Test
	public void testQuery2() {	
		List<Map<String, Object>> list = mapper.query2();
		
		for (Map dept : list) {
			System.out.println(dept);
		}
	}
	
}