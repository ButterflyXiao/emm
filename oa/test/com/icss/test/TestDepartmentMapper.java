package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Department;

/**
 * 部门dao测试类
 * 
 * @author Administrator
 *
 */
public class TestDepartmentMapper {

	// 创建Spring容器核心对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	// 获得Mapper对象（dao对象）
	DepartmentMapper mapper = context.getBean(DepartmentMapper.class);

	@Test
	public void testInsert() {
		// 创建pojo对象
		Department dept = new Department("ddd", "ddd");
		// 调用数据操作
		mapper.insert(dept);
	}

	@Test
	public void testUpdate() {
		// 创建pojo对象
		Department dept = new Department(9,"咨询部","客服服务");
		// 调用数据操作
		mapper.update(dept);
	}
	
	@Test
	public void testDelete() {
		mapper.delete(10);
	}
	
	@Test
	public void testQueryById() {		
		Department dept = mapper.queryById(1);
		System.out.println(dept);		
	}
	
	@Test
	public void testQuery() {		
		List<Department> list = mapper.query();
		
		for (Department dept : list) {
			System.out.println(dept);
		}
	}	

}