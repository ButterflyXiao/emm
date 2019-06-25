package com.icss.test;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.EmployeeMapper;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.pojo.Job;

/**
 * 员工数据访问
 * @author Administrator
 *
 */
public class TestEmployeeMapper {
	
	//获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//mapper对象
	EmployeeMapper mapper = context.getBean(EmployeeMapper.class);
	
	@Test
	public void testInsert() {
		
		Department dept = new Department();
		dept.setDeptId(5);
		
		Job job = new Job();
		job.setJobId(7);
		
		Employee emp = new Employee("张三2", "zhangsan2", "123456", "男", Date.valueOf("1987-5-30"), 
				"13812345678", 7000, "zhangsan2@icss.com", "擅长java mysql python", dept, job);
		
		mapper.insert(emp);	
	}
	
	@Test
	public void testInsertMany() {
		
		Department dept = new Department();
		dept.setDeptId(4);
		
		Job job = new Job();
		job.setJobId(6);
		
		for (int i = 1;i <= 10;i ++) {
			Employee emp = new Employee("王五" + i, "wangwu" + i, "123456", "男", Date.valueOf("1996-9-15"), 
					"13312345678", 7700, "wangwu" + i + "@icss.com", "无", dept, job);
			
			mapper.insert(emp);
		}
					
	}
	
	@Test
	public void testQueryByPage() {
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", 10);
		map.put("pageSize", 10);
		
		List<Employee> list = mapper.queryByPage(map);
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
		
	}
	
	@Test
	public void testQueryByPage2() {
					
		List<Employee> list = mapper.queryByPage2(10,10);
		
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
		Employee emp = mapper.queryById(4);
		System.out.println(emp);
	}
	
	@Test
	public void testQueryByName() {		
		Employee emp = mapper.queryByName("lisi1");
		System.out.println(emp);
	}
	
	@Test
	public void testQueryByCondition() {		
				
		List<Employee> list = mapper.queryByCondition(0,10,5,9,"李");
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
	}
	
	@Test
	public void testQueryByCondition2() {		
				
		List<Employee> list = mapper.queryByCondition2(0,5,null,null,"%");
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
	}
	
	@Test
	public void testQueryByCondition3() {		
				
		List<Employee> list = mapper.queryByCondition3(0,10,null,null,"%");
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
	}
	
	@Test
	public void testGetCountByCondition() {		
		
		int count = mapper.getCountByCondition(null, null, "四");
		System.out.println(count);
		
	}
		
	@Test
	public void testString() {
		
		String string = "abcdefg";
		
		System.out.println( string.indexOf("xxxx") );
		
	}
	
	@Test
	public void testQueryByIdsCondition() {		
		
		Integer[] ids = {};
		
		List<Employee> list = mapper.queryByIds(ids);
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
		
	}
	
	@Test
	public void testUpdateHead() {		
		
		mapper.updateHead("lisi", "abcd");
		
	}
	
	@Test
	public void testQueryHead() {		
		
		String empPhoto = mapper.queryHead("lisi");
		System.out.println(empPhoto);
		
	}
	
	
}