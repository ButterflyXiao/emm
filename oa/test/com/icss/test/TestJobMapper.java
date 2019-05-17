package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.JobMapper;
import com.icss.oa.system.pojo.Job;
import com.icss.oa.system.pojo.Job;

public class TestJobMapper {

	// 创建Spring容器核心对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	// 获得Mapper对象（dao对象）
	JobMapper mapper = context.getBean(JobMapper.class);

	@Test
	public void testInsert() {
		// 创建pojo对象
		Job job = new Job("ddd", 2,3);
		// 调用数据操作
		mapper.insert(job);
	}

	@Test
	public void testUpdate() {
		// 创建pojo对象
		Job job = new Job(9,"咨询部",2,3);
		// 调用数据操作
		mapper.update(job);
	}
	
	@Test
	public void testDelete() {
		mapper.delete(1);
	}
	
	@Test
	public void testQueryById() {		
		Job job = mapper.queryById(1);
		System.out.println(job);		
	}
	
	@Test
	public void testQuery() {		
		List<Job> list = mapper.query();
		
		for (Job job : list) {
			System.out.println(job);
		}
	}	
}
