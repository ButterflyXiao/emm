package com.icss.test;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.EmployeeMapper;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.pojo.Job;

/**
 * 员工dao测试类
 * @author Administrator
 *
 */
public class TestEmployeeMapper {
	
	// 创建Spring容器核心对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//mapper对象
	EmployeeMapper mapper = context.getBean(EmployeeMapper.class);
	
	@Test
	public void testInsert() {
		
		Department dept = new Department();
		dept.setDeptId(5);
		
		Job job = new Job();
		job.setJobId(7);
		
		Employee emp = new Employee("张三1", "zhangsan1", "123456", "男",
				Date.valueOf("1987-10-21"), "13912345678", "zhangsan1@icss.com", 7500, "无", dept, job);
		
		mapper.insert(emp);				
	}
	
	@Test
	public void testInsertMany() {
		
		Department dept = new Department();
		dept.setDeptId(7);
		
		Job job = new Job();
		job.setJobId(10);
		
		for (int i = 1;i <= 10;i ++) {
			Employee emp = new Employee("王五" + i, "wangwu" + i, "123456", "男",
					Date.valueOf("1987-10-21"), "13312345678", "wangwu" + i +"@icss.com", 8300, "无", dept, job);
			
			mapper.insert(emp);
		}		
						
	}	

	@Test
	public void testQueryByPage() {
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", 0);
		map.put("pageSize", 7);
		
		List<Employee> list = mapper.queryByPage(map);
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
	}	
	
	@Test
	public void testQueryByPage2() {
			
		List<Employee> list = mapper.queryByPage2(0,7);
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
	}	
	
	@Test
	public void testGetCount() {		
		int count = mapper.getCount();
		System.out.println(count);			
	}	
	
	@Test
	public void testQueryById() {		
		Employee emp = mapper.queryById(1);
		System.out.println(emp);
	}	
	
	@Test
	public void testQueryByName() {		
		Employee emp = mapper.queryByName("zhangsan");
		System.out.println(emp);
	}
	
	@Test
	public void testQueryByCondition() {		
		List<Employee> list = mapper.queryByCondition(0,5,5, null, "");
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
	}
	
	@Test
	public void testGetCountByCondition() {		
		
		int count = mapper.getCountByCondition(7, 1, "");
		System.out.println(count);
		
	}
		
}