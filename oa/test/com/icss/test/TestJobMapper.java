package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.JobMapper;
import com.icss.oa.system.pojo.Job;

/**
 * 职务dao测试类
 * @author Administrator
 *
 */
public class TestJobMapper {
	
	//获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//获得mapper对象（dao对象）
	JobMapper mapper = context.getBean(JobMapper.class);
	
	@Test
	public void testInsert() {		
		Job job = new Job("xxxx", 111, 222);		
		mapper.insert(job);		
	}	
	
	@Test
	public void testUpdate() {		
		Job job = new Job(6,"市场推广", 4600, 8100);		
		mapper.update(job);	
	}
	
	@Test
	public void testDelete() {		
		mapper.delete(8);	
	}
	
	@Test
	public void testQueryById() {		
		
		Job job = mapper.queryById(4);
		System.out.println(job);
		
	}
	
	@Test
	public void testQuery() {		
		List<Job> list = mapper.query();		
		System.out.println(list);		
	}

}